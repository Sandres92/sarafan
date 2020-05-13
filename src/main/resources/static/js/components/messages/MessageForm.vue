<template>
    <v-layout row>
        <v-text-field
                label="new message"
                placeholder="write something"
                v-model="text"
                @keyup.enter="save"/>
        <v-btn @click="save">
            save
        </v-btn>
    </v-layout>
</template>

<script>

    import {mapActions} from 'vuex'
    import * as Sentry from '@sentry/browser'

    export default {
        props: ['messageAtr'],
        data() {
            return {
                id: null,
                text: ''
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
                //Sentry.captureMessage('start editing'); //это вывидется в Sentry

                const message = {id: this.id, text: this.text}

                if (this.id) {
                    this.updateMessageAction(message)

                } else {
                    this.addMessageAction(message)
                }

                this.id = null
                this.text = ''

                //throw new Error('bang!!') //это вывидется в Sentry как ошибка
            }
        }
    }
</script>

<style scoped>

</style>