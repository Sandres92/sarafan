import Vue from 'vue'
import Vuetify from 'vuetify'
import '@babel/polyfill'
import 'api/resource'
import 'vuetify/dist/vuetify.min.css'
import App from 'pages/App.vue'
import {connect} from './util/ws'
import store from 'store/store'

if (frontendData.profile) {
    connect()
}

Vue.use(Vuetify)

new Vue({
    el: '#app',
    store,
    vuetify: new Vuetify(),
    render: a => a(App)
})