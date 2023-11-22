console.log("app.js chargé");

import HomeComponent from './home.js';
import ResumeComponent from './resume.js';
import LoginComponent from './login.js';
import axios from './axios-config.js'

const routes = [
    // {path: '/app', component: PersonList},
    { path: '/app/home', component: HomeComponent },
    { path: '/app/users/:id', component: ResumeComponent },
    {path: '/app/login', component: LoginComponent}

];

const router = VueRouter.createRouter({
    history: VueRouter.createWebHistory(),
    routes
});



const myApp = {

}

const app = Vue.createApp(myApp);
app.use(router);
app.mount('#myApp');