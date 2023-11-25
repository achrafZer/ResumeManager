export default {
    template: `
      <div class="container mt-5">
        <h2>Créer un Utilisateur</h2>
        <form @submit.prevent="submitForm">
          <div class="mb-3">
            <label for="firstName">Prénom</label>
            <input type="text" id="firstName" v-model="user.firstName" class="form-control">
          </div>
          <div class="mb-3">
            <label for="lastName">Nom</label>
            <input type="text" id="lastName" v-model="user.lastName" class="form-control">
          </div>
          <div class="mb-3">
            <label for="email">Adresse Mail</label>
            <input type="email" id="email" v-model="user.email" class="form-control">
          </div>
          <div class="mb-3">
            <label for="password">Mot-de-Passe</label>
            <input type="password" id="password" v-model="user.password" class="form-control">
          </div>
          <div class="mb-3">
            <label for="birthDate">Date de Naissance</label>
            <input type="date" id="birthDate" v-model="user.birthDate" class="form-control">
          </div>
          <button type="submit" class="btn btn-primary">Créer</button>
        </form>
        <p v-if="successMessage" class="text-success">{{ successMessage }}</p>
        <p v-if="errorMessage" class="text-danger">{{ errorMessage }}</p>
      </div>
    `,
    data() {
        return {
            user: {
                firstName: '',
                lastName: '',
                birthDate: '',
                email: '',
                password: '',
                roles: ["USER"]
            },
            successMessage: '',
            errorMessage: ''
        };
    },
    methods: {
        async submitForm() {
            try {
                const response = await axios.post('http://localhost:8081/secu-users/signup', this.user);
                this.successMessage = response.data;
                this.errorMessage = '';
                alert('La modification de votre profil a été effectuée avec succès.');
                this.$router.push(`/app/users/${localStorage.getItem('userId')}/profile`);            } catch (error) {
                console.error('Erreur lors de la création de l’utilisateur', error);
                this.errorMessage = error.response.data.message || 'Erreur lors de la création de l’utilisateur';
                this.successMessage = '';
            }
        }
    }
};
