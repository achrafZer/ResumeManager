import axios from './axios-config.js';
export default {
  template: `

<section class="vh-100 gradient-custom">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-12 col-md-8 col-lg-6 col-xl-5">
        <div class="card bg-dark text-white" style="border-radius: 1rem;">
          <div class="card-body p-5 text-center">
            <div class="mb-md-5 mt-md-4 pb-5">
              <h2 class="fw-bold mb-2 text-uppercase">Modifier le profil</h2>
              <p class="text-white-50 mb-5">Mettre à jour vos informations personnelles</p>

              <form @submit.prevent="updateProfile">
                <div class="form-outline form-white mb-4">
                  <label for="firstName" class="form-label">Prénom</label>
                  <input type="text" id="firstName" class="form-control form-control-lg" v-model="user.firstName">
                  <span class="text-danger">{{ errors.firstName }}</span>
                </div>

                <div class="form-outline form-white mb-4">
                  <label for="lastName" class="form-label">Nom</label>
                  <input type="text" id="lastName" class="form-control form-control-lg" v-model="user.lastName">
                  <span class="text-danger">{{ errors.lastName }}</span>
                </div>

                <div class="form-outline form-white mb-4">
                  <label for="website" class="form-label">Site Web</label>
                  <input type="text" id="website" class="form-control form-control-lg" v-model="user.website">
                  <span class="text-danger">{{ errors.website }}</span>
                </div>

                <div class="form-outline form-white mb-4">
                  <label for="birthDate" class="form-label">Date de Naissance</label>
                  <input type="date" id="birthDate" class="form-control form-control-lg" v-model="user.birthDate">
                  <span class="text-danger">{{ errors.birthDate }}</span>
                </div>

                <button type="submit" class="btn btn-outline-light btn-lg px-5">Appliquer les modifications</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>


    `, data() {
    return {
      user: {
        firstName: '',
        lastName: '',
        website: '',
        birthDate: null
      },
      errors: {}
    };
  },

  created() {
    this.fetchUserProfile();
  },

  methods: {
    async fetchUserProfile() {
      try {
        const userId = localStorage.getItem('userId');
        const response = await axios.get(`http://localhost:8081/api/persons/${userId}`);
        this.user = response.data;
        this.user.birthDate = this.user.birthDate.split('T')[0];
      } catch (error) {
        console.error('Erreur lors de la récupération du profil utilisateur', error);
      }
    },

    async updateProfile() {
      try {
        const userId = localStorage.getItem('userId');
        await axios.put(`http://localhost:8081/api/persons/${userId}`, {
          id: this.user.id,
          firstName: this.user.firstName,
          lastName: this.user.lastName,
          email: this.user.email,
          birthDate: this.user.birthDate,
          password: this.user.password,
          website: this.user.website
        });
        alert('La modification de votre profil a été effectuée avec succès.');
        this.$router.push(`/app/users/${this.user.id}/profile`);

      } catch (error) {
        if (error.response) {
          console.log("nous avons réceptionné une erreur");
        }
        if (error.response && error.response.status === 400) {

          this.errors = error.response.data;
        } else {
          console.error('Erreur lors de la mise à jour du profil', error);
        }
      }
    }
  }
}