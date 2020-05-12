<template>
    <v-layout row>
        <v-text-field
                label="add comment"
                placeholder="write something"
                v-model="text"
                @keyup.enter="addComent"/>
        <v-btn @click="addComent">
            add
        </v-btn>
    </v-layout>
</template>

<script>
    import {mapActions} from 'vuex'

    export default {
        name: "CommentForm",
        props:['messageId'],
        data() {
            return {
                text: ''
            }
        },
        methods: {
            ...mapActions(['addCommentAction']),
            async addComent() {
                await this.addCommentAction({
                    text: this.text,
                    message:{
                        id: this.messageId
                    }
                })
                this.text=''
            }
        }
    }
</script>

<style scoped>

</style>