<template>
    <div>
        <div v-if="!profile">log in through
            <a href="/login">google</a>
        </div>
        <div v-else>
            <div>{{profile.name}}&nbsp;<a href="/logout">log out</a></div>
            <messages-list :messages="messages"/>
        </div>
    </div>
</template>

<script>
    import MessagesList from 'components/messages/MessageList.vue'

    export default {
        components: {
            MessagesList
        },
        data() {
            return {
                messages: frontendData.messages,
                profile: frontendData.profile
            }
        }
    }
</script>

<style>

</style>