import Vue from 'vue'
import Vuetify from 'vuetify'
import '@babel/polyfill'
import 'js/api/resource'
import router from "./router/router";
import 'vuetify/dist/vuetify.min.css'
import App from 'js/pages/App.vue'
import {connect} from './util/ws'
import store from 'js/store/store'

import * as Sentry from '@sentry/browser'
import {Vue as VueIntegration} from '@sentry/integrations'

Sentry.init({
    dsn: 'https://4b9d9508ee394f328a666df8fd07b123@o392302.ingest.sentry.io/5239645',
    integrations: [new VueIntegration({Vue, attachProps: true})],
});

Sentry.setUser({id: profile && profile.id, username: profile && profile.name});

if (profile) {
    connect()
}

Vue.use(Vuetify)

new Vue({
    el: '#app',
    store,
    router,
    vuetify: new Vuetify(),
    render: a => a(App)
})