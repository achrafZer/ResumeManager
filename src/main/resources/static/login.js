console.log("login.js chargé");
import axios from './axios-config.js'


export default {
    template: `

    <div class="container mt-5">
    <form @submit.prevent="login">
      <div class="mb-3">
        <label for="email" class="form-label">Mail Adress</label>
        <input type="email" class="form-control" id="email" v-model="email" required>
      </div>
      <div class="mb-3">
        <label for="password" class="form-label">Password</label>
        <input type="password" class="form-control" id="password" v-model="password" required>
      </div>
      <button type="submit" class="btn btn-primary">Login</button>
      <div v-if="errorMessage" class="alert alert-danger mt-3">{{ errorMessage }}</div>
    </form>
  </div>

    `,

    data() {
        return {
            email: '',
            password: '',
            errorMessage: '' // Pour afficher les messages d'erreur
        };
    },

    methods: {
        async login() {
            try {
                const response = await axios.post('http://localhost:8081/secu-users/login', {
                        username: this.email,
                        password: this.password
                    }, {
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    }
                );
                console.log('Login Successful', response.data);
                console.log('userId', response.data.userId)

                localStorage.setItem('user-token', response.data.jwt);
                localStorage.setItem('userId', response.data.userId);

                this.$router.push(`/app/users/${response.data.userId}/home`);

                // Gérez le succès de la connexion
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