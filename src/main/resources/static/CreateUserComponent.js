export default {
  template: `
      <div class="container mt-5">
        <h2>Créer un Utilisateur</h2>
        <form @submit.prevent="submitForm">
          <div class="mb-3">
            <label for="firstName">Prénom</label>
            <input type="text" id="firstName" v-model="user.firstName" class="form-control">
            <span class="text-danger">{{ errors.firstName }}</span>
          </div>

          <div class="mb-3">
            <label for="lastName">Nom</label>
            <input type="text" id="lastName" v-model="user.lastName" class="form-control">
            <span class="text-danger">{{ errors.lastName }}</span>
            </div>

          <div class="mb-3">
            <label for="email">Adresse Mail</label>
            <input type="email" id="email" v-model="user.email" class="form-control">
            <span class="text-danger">{{ errors.email }}</span>
            </div>

          <div class="mb-3">
            <label for="password">Mot-de-Passe</label>
            <input type="password" id="password" v-model="user.password" class="form-control">
            <span class="text-danger">{{ errors.password }}</span>
            </div>

          <div class="mb-3">
            <label for="birthDate">Date de Naissance</label>
            <input type="date" id="birthDate" v-model="user.birthDate" class="form-control">
            <span class="text-danger">{{ errors.birthDate }}</span>
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
      errorMessage: '',
      errors: {}
    };
  },
  methods: {
    async submitForm() {
      try {
        const response = await axios.post('http://localhost:8081/secu-users/signup', this.user);
        this.successMessage = response.data;
        this.errorMessage = '';
        this.errors = {};
        alert('La modification de votre profil a été effectuée avec succès.');
        this.$router.push(`/app/users/${localStorage.getItem('userId')}/profile`);
      } catch (error) {
        if (error.response) {
          console.log("response");
        }
        if (error.response && (error.response.status === 500)) {
          this.errorMessage = "L'adresse mail existe déjà";
        }
        if (error.response && (error.response.status === 400)) {
          this.errors = error.response.data;
          this.errorMessage = 'Des erreurs de validation ont été détectées.';
        } else {
          console.error('Erreur lors de la création de l’utilisateur', error);
          this.errorMessage = error.response.data.message || 'Erreur lors de la création de l’utilisateur';
        }
        this.successMessage = '';
      }
    }
  }
};
