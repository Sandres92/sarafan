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
    import {mapActions} from 'vuex'

    export default {
        props: ['message', 'editMessage'],

        methods: {
            ...mapActions(['removeMessageAction']),
            edit() {
                this.editMessage(this.message)
            },

            del() {
                console.log("==== bbb =====")
                console.log(this.message.id)
                console.log(this.message.text)

                this.removeMessageAction(this.message)
            }
        }
    }
</script>

<style scoped>

</style>