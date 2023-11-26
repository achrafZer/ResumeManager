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
                            <h2 class="fw-bold mb-4 text-uppercase">Ajouter une nouvelle activité</h2>
                            
                            <form @submit.prevent="createActivity">
                                <div class="form-outline form-white mb-4">
                                    <label for="title" class="form-label">Titre</label>
                                    <input type="text" id="title" v-model="activity.title" class="form-control form-control-lg">
                                    <span class="text-danger">{{ errors.title }}</span>
                                </div>

                                <div class="form-outline form-white mb-4">
                                    <label for="description" class="form-label">Description</label>
                                    <textarea id="description" v-model="activity.description" class="form-control form-control-lg" style="height: 150px;"></textarea>
                                    <span class="text-danger">{{ errors.description }}</span>
                                </div>

                                <div class="form-outline form-white mb-4">
                                    <label for="startYear" class="form-label">Année de Début</label>
                                    <input type="number" id="startYear" v-model="activity.startYear" class="form-control form-control-lg">
                                    <span class="text-danger">{{ errors.startYear }}</span>
                                </div>

                                <div class="form-outline form-white mb-4">
                                    <label for="endYear" class="form-label">Année de Fin</label>
                                    <input type="number" id="endYear" v-model="activity.endYear" class="form-control form-control-lg">
                                    <span class="text-danger">{{ errors.endYear }}</span>
                                </div>

                                <span class="text-danger">{{ errors.startYearBeforeEndYear }}</span>
                                <button type="submit" class="btn btn-outline-light btn-lg px-5">Ajouter l'activité</button>
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
            activity: {
                title: '', description: '', startYear: '', endYear: ''
            }, errors: {}
        };
    }
    , methods: {
        async createActivity() {
            try {
                const userId = this.$route.params.userId;
                await axios.post(`http://localhost:8081/api/activities/user/${userId}`, this.activity);
                alert('Activité ajoutée avec succès.');
                this.$router.push(`/app/users/${userId}/edit-activities`);
            } catch (error) {
                if (error.response) {
                    console.log("nous avons réceptionné une erreur");
                }
                if (error.response && error.response.status === 500) {
                    this.errors = error.response.data;
                } else {
                    console.error("Erreur lors de la mise à jour de l'activité", error);
                }
            }
        }
    }
};
