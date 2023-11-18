import { createApp } from 'vue';
import { createRouter, createWebHistory } from 'vue-router';
import App from './App.vue';
import LoginForm from './components/LoginForm.vue';
import 'bootstrap/dist/css/bootstrap.min.css';


const routes = [
    { path: '/login', component: LoginForm }
    // Vous pouvez ajouter d'autres routes ici
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

const app = createApp(App);
app.use(router);
app.mount('#app');