<template>
    <v-card>
        <div  class="my-2">
            <v-avatar
                    v-if="message.author && message.author.userpic"
                    size="200px">
                <img
                        :src="message.author.userpic"
                        :alt="message.author.name">
            </v-avatar>
            <v-avatar v-else
                      color="indigo"
                      size="36px">
                <v-icon dark>mdi-account-circle</v-icon>
            </v-avatar>
            <span class="pl-3">{{authorName}}</span>
        </div>
        <div class="pt-3">
            <v-card-text primary-tittle>
                {{message.text}}
            </v-card-text>
        </div>
        <media v-if="message.link" :message="message"></media>
        <v-card-actions>
            <v-btn @click="edit" small text rounded>
                edit
            </v-btn>
            <v-btn icon @click="del" small>
                <v-icon>mdi-delete</v-icon>
            </v-btn>
        </v-card-actions>
        <comment-list
                :comments="message.comments"
                :message-id="message.id">
        </comment-list>
    </v-card>
</template>

<script>
    import {mapActions} from 'vuex'
    import Media from "../media/Media.vue";
    import CommentList from "../comment/CommentList.vue";

    export default {
        props: ['message', 'editMessage'],
        components: {
            CommentList,
            Media
        },
        computed: {
            authorName() {
                return this.message.author ? this.message.author.name : 'unlnown'
            }
        },
        methods: {
            ...mapActions(['removeMessageAction']),
            edit() {
                this.editMessage(this.message)
            },

            del() {
                this.removeMessageAction(this.message)
            }
        }
    }
</script>

<style scoped>

</style>