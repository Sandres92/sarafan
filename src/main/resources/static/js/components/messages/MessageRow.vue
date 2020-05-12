<template>
    <v-card>
        <user-link :user="message.author" size="48"></user-link>
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
    import UserLink from "../UserLink.vue";

    export default {
        props: ['message', 'editMessage'],
        components: {
            UserLink,
            CommentList,
            Media
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