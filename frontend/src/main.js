import { createApp } from 'vue';
import { createRouter, createWebHistory } from 'vue-router';
import App from './App.vue';
import LoginForm from './components/LoginForm.vue';
import 'bootstrap/dist/css/bootstrap.min.css';
import HomePage from './components/HomePage.vue';


const routes = [
    { path: '/login', component: LoginForm },
    { path: '/home', component: HomePage }

    // Vous pouvez ajouter d'autres routes ici
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

const app = createApp(App);
app.use(router);
app.mount('#app');