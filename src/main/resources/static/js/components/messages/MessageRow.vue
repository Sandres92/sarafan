<template>
    <v-card class="my-2">
        <v-card-text primary-tittle>
            <i>({{message.id}})</i>{{message.text}}
        </v-card-text>

        <v-card-actions>
            <v-btn @click="edit" small text rounded>
                edit
            </v-btn>
            <v-btn icon @click="del" small>
                <v-icon>mdi-delete</v-icon>
            </v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
    export default {
        props: ['message', 'editMessage', 'deleteMessage', 'messages'],

        methods: {
            edit() {
                this.editMessage(this.message)
            },

            del() {
                this.deleteMessage(this.message)
            }
        }
    }
</script>

<style scoped>

</style>