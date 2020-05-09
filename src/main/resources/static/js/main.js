import Vue from 'vue'
import Vuetify from 'vuetify'
import 'api/resource'
import 'vuetify/dist/vuetify.min.css'
import App from 'pages/App.vue'
import {connect} from './util/ws'

if (frontendData.profile) {
    connect()
}

Vue.use(Vuetify)

new Vue({
    el: '#app',
    vuetify: new Vuetify(),
    render: a => a(App)
})