<template>
    <span></span>
</template>

<script>
    import {mapActions} from 'vuex'

    export default {
        name: "LazyLoader",
        methods: mapActions(['loadPageAction']),
        mounted() {
            window.onscroll = () => {
                const elem = document.documentElement
                const isButtonOfScreen = elem.scrollTop + window.innerHeight >= elem.offsetHeight - 3
                const a = elem.scrollTop + window.innerHeight

                if (isButtonOfScreen) {
                    this.loadPageAction()
                }
            }
        },
        beforeDestroy() {
            window.onscroll = null
        }
    }
</script>

<style scoped>

</style>