// EditActivitiesComponent.js
export default {
    template: `
      <section class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card bg-dark text-white" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">
                        <div class="mb-md-5 mt-md-4 pb-5">
                            <h2 class="fw-bold mb-2 text-uppercase">Modifier vos Activités</h2>
                            <ul class="list-group">
                                <li v-for="activity in activities" :key="activity.id" class="list-group-item list-group-item-dark mb-2">
                                    {{ activity.title }}
                                    <button class="btn btn-secondary float-end" @click="editActivity(activity.id)">Modifier</button>
                                </li>

                            </ul>
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
            activities: [] // Les activités de l'utilisateur
        };
    },

    created() {
        this.fetchActivities();
    }, methods: {
        async fetchActivities() {
            try {
                const userId = this.$route.params.id; // Récupérer l'ID de l'utilisateur depuis les paramètres de route
                const response = await axios.get(`http://localhost:8081/api/activities/search-by-person-id`, {
                    params: {id: userId}
                });
                this.activities = response.data;
            } catch (error) {
                console.error('Erreur lors de la récupération des activités', error);
            }
        }, editActivity(activityId) {
            this.$router.push(`/app/users/${this.$route.params.id}/edit-activities/${activityId}`);
        }
    }
};
