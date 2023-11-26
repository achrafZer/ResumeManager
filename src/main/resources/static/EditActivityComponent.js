import axios from './axios-config.js';

export default {
    template: `
      <div class="container mt-5">
        <h2>Modifier l'Activité</h2>
        <form @submit.prevent="updateActivity">
          <div class="mb-3">
            <label for="title">Titre</label>
            <input type="text" id="title" v-model="activity.title" class="form-control">
            <span class="text-danger">{{ errors.title }}</span>
          </div>
          
          <div class="mb-3">
            <label for="description">Description</label>
            <textarea id="description" v-model="activity.description" class="form-control"></textarea>
            <span class="text-danger">{{ errors.description }}</span>
          </div>
          
          <div class="mb-3">
            <label for="startYear">Année de Début</label>
            <input type="number" id="startYear" v-model="activity.startYear" class="form-control">
            <span class="text-danger">{{ errors.startYear }}</span>
          </div>
          
          <div class="mb-3">
            <label for="endYear">Année de Fin</label>
            <input type="number" id="endYear" v-model="activity.endYear" class="form-control">
            <span class="text-danger">{{ errors.endYear }}</span>
          </div>
          <span class="text-danger">{{ errors.startYearBeforeEndYear }}</span>
          <button type="submit" class="btn btn-success">Sauvegarder les modifications</button>
        </form>
      </div>
    `, data() {
        return {
            activity: {
                title: '', description: '', startYear: '', endYear: ''
            }, errors: {}
        };
    }, created() {
        this.fetchActivityDetails();
    }, methods: {
        async fetchActivityDetails() {
            try {
                const activityId = this.$route.params.activityId;
                const response = await axios.get(`http://localhost:8081/api/activities/${activityId}`);
                this.activity = response.data;
                console.log("activity : ", response);
            } catch (error) {
                console.error("Erreur lors de la récupération des détails de l'activité", error);
            }
        },

        async updateActivity() {
            try {
                const activityId = this.$route.params.activityId;
                await axios.put(`http://localhost:8081/api/activities/${activityId}`, this.activity);
                alert('La modification de l\'activité a été effectuée avec succès.');
                this.$router.push(`/app/users/${localStorage.getItem('userId')}/edit-activities`);


            } catch (error) {
                if (error.response) {
                    console.log("response");
                }
                if (error.response && error.response.status === 400) {
                    this.errors = error.response.data;
                } else {
                    console.error("Erreur lors de la mise à jour de l'activité", error);
                }
            }
        }
    }
};
