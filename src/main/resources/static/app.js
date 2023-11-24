console.log("app.js chargé");

import HomeComponent from './home.js';
import ResumeComponent from './resume.js';
import LoginComponent from './login.js';
import axios from './axios-config.js';
import UserHomeComponent from './me.js';
import EditProfileComponent from './EditProfileComponent.js';
import UserProfileComponent from './UserProfileComponent.js';
import EditActivitiesComponent from './EditActivitiesComponent.js';
import EditActivityComponent from './EditActivityComponent.js'; // Supposons que vous créez ce composant



const routes = [
    // {path: '/app', component: PersonList},
    { path: '/app/home', component: HomeComponent },
    { path: '/app/users/:id', component: ResumeComponent },
    { path: '/app/login', component: LoginComponent},
    { path: '/app/users/:id/home', component: UserHomeComponent},
    { path: '/app/users/:id/profile', component: UserProfileComponent },
    { path: '/app/users/:id/edit-profile', component: EditProfileComponent },
    { path: '/app/users/:id/edit-activities', component: EditActivitiesComponent },
    { path: '/app/users/:userId/edit-activities/:activityId', component: EditActivityComponent }

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