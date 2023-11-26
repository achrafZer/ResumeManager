console.log("home.js chargé");
import axios from './axios-config.js';

export default {
    template: `
      
            <section class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
        <div class="card bg-dark text-white" style="border-radius: 1rem;">
            <div class="card-body text-center">
                <h1 class="fw-bold mb-3 text-uppercase">Gestionnaire de CV</h1>
                <h2 class="mb-4">Liste des Personnes avec CV</h2>

                <form @submit.prevent="search" class="d-flex justify-content-center align-items-center mb-4">
                    <input type="text" class="form-control form-control-lg me-2" placeholder="Rechercher une personne"
                        v-model="searchQuery">
                    <button class="btn btn-primary" type="submit">Rechercher</button>
                </form>
            </div>
        </div>

        <div class="row mt-4">
            <div v-for="person in persons" :key="person.id" class="col-md-6 col-lg-4 mb-4">
                <div class="card h-100 ">
                    <div class="card-body">
    <h5 class="card-title">{{ person.firstName }} {{ person.lastName }}</h5>
    <ul class="list-group list-group-flush">
        <li v-if="person.cv && person.cv.activities && person.cv.activities.length" v-for="activity in person.cv.activities" :key="activity.id" class="list-group-item">
            {{ activity.title }} ({{ activity.startYear }} - {{ activity.endYear }})
        </li>
        <li v-if="!person.cv || !person.cv.activities || person.cv.activities.length === 0" class="list-group-item text-muted">
            Aucune activité de CV disponible.
        </li>
    </ul>
</div>
                    <div class="card-footer">
                        <button @click="goToResume(person.id)" class="btn btn-primary">Voir le CV</button>
                    </div>
                </div>
            </div>
        </div>

<nav aria-label="Page navigation" class="mt-4">
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
</section>


`, data() {
        return {
            allPersons: [], persons: [], axios: null, searchQuery: '', currentPage: 1, pageSize: 6, totalPersons: 0
        }
    },

    async created() {
        try {
            const response = await axios.get('http://localhost:8081/api/persons');
            this.allPersons = response.data;
            this.totalPersons = this.allPersons.length;
            this.updatePage();
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

        updatePage() {
            const startIndex = (this.currentPage - 1) * this.pageSize;
            const endIndex = startIndex + this.pageSize;
            this.persons = this.allPersons.slice(startIndex, endIndex);
        },

        goToResume(id) {
            this.$router.push(`/app/users/${id}`)
        },

        changePage(page) {
            if (page >= 1 && page <= this.totalPages) {
                this.currentPage = page;
                this.updatePage();
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