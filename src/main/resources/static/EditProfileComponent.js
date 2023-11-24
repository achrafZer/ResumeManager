export default {
    template: `

      <div class="container mt-5">
        <h2>Modifier le profil</h2>
        <form @submit.prevent="updateProfile">
          <!-- Champs du formulaire pour modifier le profil -->
          <div class="mb-3">
            <label for="firstName">Modifier votre prénom</label>
            <input type="text" id="firstName" v-model="user.firstName">
          </div>
          <div class="mb-3">
            <label for="lastName">Modifier votre nom</label>
            <input type="text" id="lastName" v-model="user.lastName">
          </div>
          <div class="mb-3">
            <label for="website">Modifier votre site web</label>
            <input type="text" id="website" v-model="user.website">
          </div>
          <div class="mb-3">
            <label for="birthDate">Modifier votre date de naissance</label>
            <input type="date" id="birthDate" v-model="user.birthDate">
          </div>
          <button type="submit" class="btn btn-success">Appliquer les modifications</button>
        </form>
      </div>
    `, data() {
        return {
            user: {
                firstName: '', lastName: '', website: '', birthDate: null
            }
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

                console.log("fetchUserProfile userId : ", userId, " - user ", this.user);
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
                console.error('Erreur lors de la mise à jour du profil', error);
            }
        }
    }
}