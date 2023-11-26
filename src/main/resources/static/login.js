console.log("login.js chargé");
import axios from './axios-config.js';
// import './login.css'
export default {
    template: `
    <section class="vh-100 gradient-custom">
        <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                    <div class="card bg-dark text-white" style="border-radius: 1rem;">
                          <div class="card-body p-5 text-center">
                             <div class="mb-md-5 mt-md-4 pb-5">
              <h2 class="fw-bold mb-2 text-uppercase">Connexion</h2>
              <p class="text-white-50 mb-5">Entrer l'adresse mail et le mot-de-passe</p>


                <form @submit.prevent="login">
                  <div class="form-outline form-white mb-4">
                    <label for="typeEmailX" class="form-label">Adresse mail</label>
                    <input type="email" class="form-control form-control-lg" id="typeEmailX" v-model="email" required>
                  </div>
                  <div class="form-outline form-white mb-4">
                    <label for="typePasswordX" class="form-label">Mot-de-passe</label>
                    <input type="password" class="form-control form-control-lg" id="typePasswordX" v-model="password" required>
                  </div>
                  <button type="submit" class="btn btn-outline-light btn-lg px-5">Login</button>
                  <div v-if="errorMessage" class="alert alert-danger mt-3">{{ errorMessage }}</div>
                </form>
              
              </div>
              </div>
              </div>
                </div>
            </div>
        </div>
    </section>
    

    `,

    data() {
        return {
            email: '', password: '', errorMessage: ''
        };
    },

    methods: {
        async login() {
            try {
                const response = await axios.post('http://localhost:8081/secu-users/login', {
                    username: this.email, password: this.password
                }, {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                });
                console.log('Login Successful', response.data);
                console.log('userId', response.data.userId)

                localStorage.setItem('user-token', response.data.jwt);
                console.log(localStorage.getItem('user-token'));
                localStorage.setItem('userId', response.data.userId);
                this.$router.push(`/app/users/${response.data.userId}/profile`).then(() => window.location.reload());

            } catch (error) {
                console.error('Erreur lors de la tentative de connexion: ', error);
                if (error.response) {
                    this.errorMessage = error.response.data.message || 'Erreur de connexion';
                } else {
                    this.errorMessage = 'Erreur de connexion';
                }
            }
        }

    }


}