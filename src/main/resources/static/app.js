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
import CreateUserComponent from './CreateUserComponent.js';

function userGuard(to, from, next) {
    const loggedInUserId = localStorage.getItem('userId');
    const isUserLoggedIn = !!localStorage.getItem('user-token');
    const userIdInUrl = to.params.id || to.params.userId;

    if (!isUserLoggedIn || loggedInUserId !== userIdInUrl) {
        next('/app/login');
    } else {
        next();
    }
}

const routes = [
    {path: '/app', redirect: '/app/home'},
    { path: '/app/home', component: HomeComponent },
    { path: '/app/users/:id', component: ResumeComponent },
    { path: '/app/login', component: LoginComponent},
    { path: '/app/users/:id/home', component: UserHomeComponent, beforeEnter: userGuard},
    { path: '/app/users/:id/profile', component: UserProfileComponent, beforeEnter: userGuard},
    { path: '/app/users/:id/edit-profile', component: EditProfileComponent, beforeEnter: userGuard},
    { path: '/app/users/:id/edit-activities', component: EditActivitiesComponent, beforeEnter: userGuard},
    { path: '/app/users/:userId/edit-activities/:activityId', component: EditActivityComponent, beforeEnter: userGuard},
    { path: '/app/create-user', component: CreateUserComponent }

];

const router = VueRouter.createRouter({
    history: VueRouter.createWebHistory(),
    routes
});

const app = Vue.createApp({
    data() {
        return {
            isLoggedIn: false
        };
    },
    created() {
        this.checkLogin();
    },
    methods: {
        checkLogin() {
            this.isLoggedIn = !!localStorage.getItem('user-token');
        },
        goToLogin() {
            this.$router.push('/app/login');
        },
        logout() {
            localStorage.removeItem('user-token');
            localStorage.removeItem('userId');
            this.isLoggedIn = false;
            this.$router.push('/app/home');
        },
        goToProfile() {
            const userId = localStorage.getItem('userId');
            this.$router.push(`/app/users/${userId}/profile`);
        },
        goToCreateUser() {
            this.$router.push('/app/create-user');
        }
    },
    template: `

      <div>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
          <div class="container-fluid">
            <a class="navbar-brand" href="/app/home">Accueil</a>
            <div class="d-flex">
              <button v-if="!isLoggedIn" class="btn btn-primary" @click="goToLogin">Connexion</button>
              <button v-if="isLoggedIn" class="btn btn-primary" @click="goToProfile">Profil</button>
              <button v-if="isLoggedIn" class="btn btn-info" @click="goToCreateUser">Créer un utilisateur</button>
              <button v-if="isLoggedIn" class="btn btn-secondary" @click="logout">Déconnexion</button>
            </div>
          </div>
        </nav>
        <router-view></router-view>
      </div>
    `
});
app.use(router);
app.mount('#myApp');