console.log("home.js chargé");
import axios from './axios-config.js';

export default {
    template: `
      <div id="myHome">
        <div class="container mt-5">
          <h1>Gestionnaire de CV</h1>
          <div class="d-flex justify-content-between align-items-center">
            <h2>Liste des Personnes avec CV</h2>
          </div>
          <!--Barre de recherche par nom, prénom ou titre d'activité-->
          <div class="mb-3">
            <input type="text" class="form-control" placeholder="Rechercher une personne"
                   v-model="searchQuery">
            <button class="btn btn-primary mt-2" @click="search">Rechercher</button>
            <button class="btn btn-primary mt-2" @click="resetSearch">Réinitialiser la recherche</button>

          </div>

          <div v-for="person in persons" :key="person.id" class="card mb-3">
            <div class="card-body" @click="goToResume(person.id)">
              <h5 class="card-title">{{ person.firstName }} {{ person.lastName }}</h5>
              <h6 class="card-subtitle mb-2 text-muted"
                  v-if="person.cv && person.cv.activities && person.cv.activities.length">Activités</h6>
              <ul class="list-group list-group-flush">
                <li v-for="activity in person.cv.activities" :key="activity.id" class="list-group-item"
                    v-if="person.cv && person.cv.activities">
                  {{ activity.title }} ({{ activity.startYear }} - {{ activity.endYear }})
                </li>
                <p v-else class="text-muted">Aucune activité de CV disponible.</p>
              </ul>
            </div>
          </div>

          <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
              <li class="page-item" :class="{ disabled: currentPage === 1 }">
                <button class="page-link" @click="changePage(currentPage - 1)" aria-label="Previous">
                  <span aria-hidden="true">&laquo;</span>
                </button>
              </li>
              <li class="page-item" v-for="page in totalPages" :key="page" :class="{ active: page === currentPage }">
                <button class="page-link" @click="changePage(page)">{{ page }}</button>
              </li>
              <li class="page-item" :class="{ disabled: currentPage === totalPages }">
                <button class="page-link" @click="changePage(currentPage + 1)" aria-label="Next">
                  <span aria-hidden="true">&raquo;</span>
                </button>
              </li>
            </ul>
          </nav>
        </div>

      </div>`, data() {
        console.log("data");
        return {
            allPersons: [], persons: [], axios: null, searchQuery: '', currentPage: 1, pageSize: 6, totalPersons: 0
        }
    },

    async created() {
        try {
            const response = await axios.get('http://localhost:8081/api/persons');
            this.allPersons = response.data;
            this.totalPersons = this.allPersons.length;
            this.persons = this.allPersons.slice(0, this.pageSize);
        } catch (error) {
            console.error('Erreur lors de la récupération des personnes', error);
        }
    },

    methods: {
        async search() {
            try {
                if (this.searchQuery === '') {
                    this.resetSearch();
                    return;
                }
                let endpoint = 'http://localhost:8081/api/persons/search';
                if (this.searchQuery.trim()) {
                    endpoint += `?query=${this.searchQuery}`;
                } else {
                    this.created();
                    return;
                }
                const response = await axios.get(endpoint);
                this.allPersons = response.data;
                this.totalPersons = this.allPersons.length;
                this.currentPage = 1; // Réinitialiser à la première page pour la nouvelle recherche
                this.persons = this.allPersons.slice(0, this.pageSize);
            } catch (error) {
                console.error('Erreur lors de la recherche', error);
            }
        },

        goToResume(id) {
            console.log("je suis goToResume");
            this.$router.push(`/app/users/${id}`)
        },

        changePage(page) {
            if (page >= 1 && page <= this.totalPages) {
                this.currentPage = page;
                const startIndex = (page - 1) * this.pageSize;
                this.persons = this.allPersons.slice(startIndex, startIndex + this.pageSize);
            }
        },

        async resetSearch() {
            try {
                const response = await axios.get('http://localhost:8081/api/persons');
                this.allPersons = response.data;
                this.totalPersons = this.allPersons.length;
                this.currentPage = 1;
                this.persons = this.allPersons.slice(0, this.pageSize);
                this.searchQuery = ''; // Réinitialiser la requête de recherche
            } catch (error) {
                console.error('Erreur lors de la réinitialisation de la recherche', error);
            }
        }
    },

    computed: {
        totalPages() {
            return Math.ceil(this.totalPersons / this.pageSize);
        }
    }

}