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

    import messagesApi from 'api/messages'

    export default {
        props: ['messages', 'messageAtr'],
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
            save() {

                const message = {id: this.id, text: this.text}

                if (this.id) {
                    messagesApi.update(message).then(result =>
                        result.json().then(data => {
                            const index = this.messages.findIndex(item => item.id === this.id)

                            this.messages.splice(index, 1, data)
                        })
                    )
                } else {
                    messagesApi.add(message).then(result =>
                        result.json().then(data => {
                            const index = this.messages.findIndex(item => item.id === this.id)
                            if (index > -1) {
                                this.messages.splice(index, 1, data)
                            } else {
                                console.log(index)
                                console.log('ccccccccc' + index)
                                this.messages.push(data)
                            }
                        })
                    )
                }

                this.id = ''
                this.text = ''
            }
        }
    }
</script>

<style scoped>

</style>