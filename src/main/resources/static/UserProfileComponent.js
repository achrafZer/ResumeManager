console.log("UserProfileComponent.js chargé");
import axios from './axios-config.js';
export default {

    template: `
      <section class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-15 col-md-15 col-lg-20 col-xl-12">
                <div class="card bg-dark text-white" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">
                        <div class="mb-md-5 mt-md-4 pb-5">
                            <div v-if="person">
                                <h2 class="fw-bold mb-2 text-uppercase">CV de {{ person.firstName }} {{ person.lastName }}</h2>
                                <h3 class="mb-4">Votre profil</h3>
                                <p class="text-white-50 mb-2">Email: {{ person.email }}</p>
                                <p class="text-white-50 mb-2">Site Web: {{ person.website }}</p>
                                <p class="text-white-50 mb-4">Date de naissance: {{ formatDate(person.birthDate) }}</p>

                                <button class="btn btn-outline-light btn-lg px-5 mb-2" @click="editProfile">Modifier le Profil</button>
                                <button class="btn btn-outline-light btn-lg px-5 mb-2" @click="editActivities">Modifier les activités</button>

                                <h3 class="mb-4">Le contenu de votre CV</h3>
                                <ul v-if="person.cv && person.cv.activities.length" class="list-unstyled">
                                    <li v-for="activity in person.cv.activities" :key="activity.id">
                                        <strong>{{ activity.title }}</strong> ({{ activity.startYear }} - {{ activity.endYear }})
                                        <p>{{ activity.description }}</p>
                                    </li>
                                </ul>
                                <p v-else class="text-white-50">Vous n'avez mis aucune activité encore</p>
                            </div>
                            <div v-else>
                                <p class="text-white-50">Chargement...</p>
                            </div>
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
            person: null
        };
    },

    created() {
        this.fetchPersonDetails();
    },

    methods: {
        async fetchPersonDetails() {
            try {
                const personId = this.$route.params.id;
                const response = await axios.get('http://localhost:8081/api/persons/' + personId);
                this.person = response.data;
            } catch (error) {
                console.error('Erreur lors de la récupération des détails de la personne', error);
            }
        },

        formatDate(dateString) {
            const options = {year: 'numeric', month: 'long', day: 'numeric'};
            return new Date(dateString).toLocaleDateString(undefined, options);
        },

        editProfile() {
            this.$router.push(`/app/users/${this.$route.params.id}/edit-profile`);
        },

        editActivities() {
            this.$router.push(`/app/users/${this.$route.params.id}/edit-activities`);
        }
    }
};