<template>
    <v-container>
        <v-card v-if="type==='href'" class="mx-auto"
                max-width="344">
            <v-img v-if="message.linkCover"
                   :src="message.linkCover"
                   height="200px">
            </v-img>
            <v-card-title>
                <a :herf="message.link">{{message.linkTitle || message.link}}</a>
            </v-card-title>
            <v-card-subtitle v-if="message.linkDescription">
                {{message.linkDescription}}
            </v-card-subtitle>
        </v-card>

        <v-card v-if="type==='image'" class="mx-auto"
                max-width="344">
            <v-img v-if="message.linkCover"
                   :src="message.linkCover"
                   height="200px">
            </v-img>
            <v-card-title>
                <a :herf="message.link">{{message.linkTitle || message.link}}</a>
            </v-card-title>
        </v-card>

        <v-card v-if="type==='youtube'" class="mx-auto"
                max-width="344">
            <you-tube :src="message.link"></you-tube>
        </v-card>
    </v-container>
</template>

<script>
    import YouTube from './YouTube.vue'

    export default {
        name: "Media",
        components: {
            YouTube
        },
        props: ['message'],
        data() {
            return {
                type: 'href'
            }
        },
        beforeMount() {
            if (this.message.link.indexOf('youtu') > -1) {
                this.type = 'youtube'
            } else if (this.message.link.match(/\.(jpeg|jpg|gif|png)$/) !== null) {
                this.type = 'image'
            } else {
                this.type = 'href'
            }
        }
    }
</script>

<style scoped>

</style>