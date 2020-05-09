<template>
    <v-layout row>
        <v-text-field
                label="new message"
                placeholder="write something"
                v-model="text"/>
        <v-btn @click="save">
            save
        </v-btn>
    </v-layout>
</template>

<script>

    import {mapActions} from 'vuex'

    export default {
        props: ['messageAtr'],
        data() {
            return {
                text: '',
                id: ''
            }
        },
        watch: {
            messageAtr(newVal, oldVal) {
                this.text = newVal.text
                this.id = newVal.id
            }
        },
        methods: {
            ...mapActions(['addMessageAction', 'updateMessageAction']),

            save() {

                const message = {id: this.id, text: this.text}

                if (this.id) {
                    this.updateMessageAction(message)

                } else {
                    this.addMessageAction(message)
                }

                this.id = ''
                this.text = ''
            }
        }
    }
</script>

<style scoped>

</style>