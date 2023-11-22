console.log("app.js chargé");

import HomeComponent from './home.js';
import ResumeComponent from './resume.js';

const routes = [
    // {path: '/app', component: PersonList},
    { path: '/app/home', component: HomeComponent },
    { path: '/app/home/:id', component: ResumeComponent }

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