<template>
    <v-container>
        <v-layout align-space-around justify-start column>
            <message-form :messageAtr="message"/>
            <message-row v-for="message in sortedMessages"
                         :key="message.id"
                         :message="message"
                         :editMessage="editMessage"/>
            <lazy-loader></lazy-loader>
        </v-layout>
    </v-container>
</template>

<script>
    import {mapGetters} from 'vuex'
    import MessageRow from 'js/components/messages/MessageRow.vue'
    import MessageForm from 'js/components/messages/MessageForm.vue'
    import LazyLoader from 'js/components/LazyLoader.vue';

    export default {
        components: {
            LazyLoader,
            MessageRow,
            MessageForm
        },
        data() {
            return {
                message: null
            }
        },
        computed: mapGetters(['sortedMessages']),
        methods: {
            editMessage(message) {
                this.message = message
            }
        }
    }
</script>

<style>
</style>

