<template>
    <v-list-item>
        <!--<v-list-item-avatar>
            <v-img :src="item.avatar"></v-img>
        </v-list-item-avatar>-->

        <v-list-item-content>
            <!--<v-list-item-title v-html="item.title"></v-list-item-title>
            <v-list-item-subtitle v-html="item.subtitle">{{comment.text}}</v-list-item-subtitle>-->
            <v-list-item-subtitle>{{comment.text}}</v-list-item-subtitle>
        </v-list-item-content>
    </v-list-item>
</template>

<script>
    export default {
        name: "CommentItem",
        props:['comment']
    }
</script>

<style scoped>

</style>