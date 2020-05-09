<template>
    <v-app>
        <v-app-bar app>
            <v-toolbar-title>sarafan</v-toolbar-title>
            <v-spacer></v-spacer>

            <span v-if="profile"> {{profile.name}} </span>

            <v-btn v-if="profile" icon href="/logout">
                <v-icon>mdi-exit-to-app</v-icon>
            </v-btn>
        </v-app-bar>

        <v-content>
            <v-container v-if="!profile">log in through
                <a href="/login">google</a>
            </v-container>
            <v-container v-if="profile">
                <messages-list/>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import {mapState, mapMutations} from 'vuex'
    import MessagesList from 'components/messages/MessageList.vue'
    import {addHandler} from 'util/ws';

    export default {
        components: {
            MessagesList
        },
        computed: mapState(['profile']),
        methods: mapMutations(['addMessageMutation', 'updateMessageMutation', 'removeMessageMutation']),
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
                } else {
                    console.error('looks like OBJECT type is unknown    ' + data.body)
                }
            })
        }
    }
</script>

<style>

</style>