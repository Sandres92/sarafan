<template>
    <v-app>
        <v-app-bar app>
            <v-toolbar-title>sarafan</v-toolbar-title>
            <v-tab v-if="profile"
                   :disabled="$route.path === '/'"
                   @click="showMessages">
                messages
            </v-tab>

            <v-spacer></v-spacer>

            <v-tab v-if="profile"
                   :disabled="$route.path === '/user'"
                   @click="shoProfile"> {{profile.name}}
            </v-tab>

            <v-btn v-if="profile" icon href="/logout">
                <v-icon>mdi-exit-to-app</v-icon>
            </v-btn>
        </v-app-bar>

        <v-content>

            <router-view></router-view>
        </v-content>
    </v-app>
</template>

<script>
    import {mapState, mapMutations} from 'vuex'
    import MessagesList from 'js/pages/MessageList.vue'
    import {addHandler} from 'js/util/ws';

    export default {
        components: {
            MessagesList
        },
        computed: mapState(['profile']),
        methods: {
            ...mapMutations([
                'addMessageMutation',
                'updateMessageMutation',
                'removeMessageMutation',
                'addCommentMutation'
            ]),
            showMessages() {
                this.$router.push('/')
            },
            shoProfile() {
                this.$router.push('/user')
            }
        },
        created() {
            addHandler(data => {
                if (data.objectType === 'MESSAGE') {

                    switch (data.eventType) {
                        case 'CREATE':
                            this.addMessageMutation(data.body)
                            break
                        case 'UPDATE':
                            this.updateMessageMutation(data.body)
                            break
                        case 'REMOVE':
                            this.removeMessageMutation(data.body)
                            break
                        default:
                            console.error('looks like EVENT type is unknown "${data.eventType}"')
                    }
                } else if (data.objectType === 'COMMENT') {

                    switch (data.eventType) {
                        case 'CREATE':
                            this.addCommentMutation(data.body)
                            break
                        default:
                            console.error('looks like EVENT type is unknown "${data.eventType}"')
                    }
                } else {
                    console.error('looks like OBJECT type is unknown    ' + data.body)
                }
            })
        },
        beforeMount() {
            if (!this.profile) {
                this.$router.replace('/auth')
            }
        }
    }
</script>

<style>

</style>