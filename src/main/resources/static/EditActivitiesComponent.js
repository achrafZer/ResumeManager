// EditActivitiesComponent.js
export default {
    template: `
      <div class="container mt-5">
        <h2>Modifier vos Activités</h2>
        <ul>
          <li v-for="activity in activities" :key="activity.id">
            {{ activity.title }}
            <button class="btn btn-secondary" @click="editActivity(activity.id)">Modifier</button>
          </li>
        </ul>
      </div>
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
                // Gestion d'erreur appropriée
            }
        }, editActivity(activityId) {
            this.$router.push(`/app/users/${this.$route.params.id}/edit-activities/${activityId}`);
        }
    }
};
