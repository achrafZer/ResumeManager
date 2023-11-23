console.log("me.js chargé");

export default {
    template: `
      <div id="me">
        <div class="container mt-5">
          <h1>Gestionnaire de CV</h1>
          <div class="d-flex justify-content-between align-items-center">
            <h2>Liste des Personnes avec CV</h2>
            <button class="btn btn-primary" @click="editProfile">Profil</button>
            <button class="btn btn-secondary" @click="logout">Se déconnecter</button>
          
          </div>
          <!--Barre de recherche par nom, prénom ou titre d'activité-->
          <div class="mb-3">
            <input type="text" class="form-control" placeholder="Rechercher une personne"
                   v-model="searchQuery">
            <button class="btn btn-primary mt-2" @click="search">Rechercher</button>
          </div>

          <div v-for="person in persons" :key="person.id" class="card mb-3">
            <div class="card-body" @click="goToResume(person.id)">
              <h5 class="card-title">{{ person.firstName }} {{ person.lastName }}</h5>
              <h6 class="card-subtitle mb-2 text-muted"
                  v-if="person.cv && person.cv.activities && person.cv.activities.length">Activite</h6>
              <ul class="list-group list-group-flush">
                <li v-for="activity in person.cv.activities" :key="activity.id" class="list-group-item"
                    v-if="person.cv && person.cv.activities">
                  {{ activity.title }} ({{ activity.startYear }} - {{ activity.endYear }})
                </li>
                <p v-else class="text-muted">Aucune activite de CV disponible.</p>
              </ul>
            </div>
          </div>
        </div>

      </div>`,
    data() {
        console.log("data");
        return {
            persons: [], axios: null, searchQuery: ''
        }
    },

    async created() {
        try {
            const response = await axios.get('http://localhost:8081/api/persons');
            this.persons = response.data;
        } catch (error) {
            console.error('Erreur lors de la récupération des personnes', error);
        }
    },

    methods: {
        async search() {
            try {
                let endpoint = 'http://localhost:8081/api/persons/search';
                if (this.searchQuery.trim()) {
                    endpoint += `?query=${this.searchQuery}`;
                } else {
                    this.created();
                    return;
                }
                const response = await axios.get(endpoint);
                this.persons = response.data;
            } catch (error) {
                console.error('Erreur lors de la recherche', error);
            }
        },

        goToResume(id) {
            console.log("je suis goToResume");
            this.$router.push(`/app/users/${id}`)
        },

        editProfile() {
            this.$router.push(`/app/users/${localStorage.getItem('userId')}/edit-profile`);
        },

        logout() {
            localStorage.removeItem('user-token');
            localStorage.removeItem('userId');
            this.$router.push('/app/home');
        }
    }

}