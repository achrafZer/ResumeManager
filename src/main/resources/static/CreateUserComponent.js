export default {
  template: `
<section class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card bg-dark text-white" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">
                        <div class="mb-md-5 mt-md-4 pb-5">
                            <h2 class="fw-bold mb-2 text-uppercase">Créer un Utilisateur</h2>
                            <p class="text-white-50 mb-5">Veuillez remplir les informations suivantes</p>

                            <form @submit.prevent="submitForm">
                                <div class="form-outline form-white mb-4">
                                    <label for="firstName" class="form-label">Prénom</label>
                                    <input type="text" id="firstName" v-model="user.firstName" class="form-control form-control-lg">
                                    <span class="text-danger">{{ errors.firstName }}</span>
                                </div>

                                <div class="form-outline form-white mb-4">
                                    <label for="lastName" class="form-label">Nom</label>
                                    <input type="text" id="lastName" v-model="user.lastName" class="form-control form-control-lg">
                                    <span class="text-danger">{{ errors.lastName }}</span>
                                </div>

                                <div class="form-outline form-white mb-4">
                                    <label for="email" class="form-label">Adresse Mail</label>
                                    <input type="email" id="email" v-model="user.email" class="form-control form-control-lg">
                                    <span class="text-danger">{{ errors.email }}</span>
                                </div>

                                <div class="form-outline form-white mb-4">
                                    <label for="password" class="form-label">Mot-de-Passe</label>
                                    <input type="password" id="password" v-model="user.password" class="form-control form-control-lg">
                                    <span class="text-danger">{{ errors.password }}</span>
                                </div>

                                <div class="form-outline form-white mb-4">
                                    <label for="birthDate" class="form-label">Date de Naissance</label>
                                    <input type="date" id="birthDate" v-model="user.birthDate" class="form-control form-control-lg">
                                    <span class="text-danger">{{ errors.birthDate }}</span>
                                </div>

                                <button type="submit" class="btn btn-outline-light btn-lg px-5">Créer</button>
                            </form>

                            <p v-if="successMessage" class="text-success">{{ successMessage }}</p>
                            <p v-if="errorMessage" class="text-danger">{{ errorMessage }}</p>
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
        alert('Le profil a été créé avec succès');
        this.$router.push(`/app/users/${localStorage.getItem('userId')}/profile`);
      } catch (error) {
        if (error.response) {
          console.log("Nous avons réceptionné une erreur ");
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
