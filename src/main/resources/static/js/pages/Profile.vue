<template>
    <v-container>
        <v-layout justify-space-around>
            <v-flex>
                <div class="title mb-3">user profile</div>
                <v-layout row justify-space-between>
                    <v-flex :xs2="!$vuetify.breakpoint.xsOnly" :xs4="$vuetify.breakpoint.xsOnly" class="px-1">
                        <v-img :src="profile.userpic"></v-img>
                    </v-flex>
                    <v-flex class="px-1">
                        <v-layout column>
                            <v-flex>{{profile.name}}</v-flex>
                            <v-flex>{{profile.locale}}</v-flex>
                            <v-flex>{{profile.gender}}</v-flex>
                            <v-flex>{{profile.email}}</v-flex>
                            <v-flex>{{profile.laseVisit}}</v-flex>
                        </v-layout>
                    </v-flex>
                </v-layout>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    import {mapState} from 'vuex'

    export default {
        name: "Profile",
        computed: mapState(['profile'])
    }
</script>

<style scoped>
    img {
        max-width: 100%;
        height: auto;
    }
</style>