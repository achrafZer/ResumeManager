export default {
    template: `
    
    <div class="container mt-5">
        <h2>Modifier Profil</h2>
        <form @submit.prevent="updateProfile">
            <!-- Champs du formulaire pour modifier le profil -->
            <div class="mb-3">
                <label for="firstName">Prénom</label>
                <input type="text" id="firstName" v-model="user.firstName">
            </div>
            <div class="mb-3">
                <label for="lastName">Nom</label>
                <input type="text" id="lastName" v-model="user.lastName">
            </div>
            <!-- Autres champs sauf email -->
            <button type="submit" class="btn btn-success">Sauvegarder</button>
        </form>
    </div>
    `
    ,
    data() {
        return {
            user: {} // Données de l'utilisateur
        };
    },
    created() {
        this.fetchUserProfile();
    },
    methods: {
        fetchUserProfile() {
            // Récupérez les données de l'utilisateur depuis l'API
        },
        updateProfile() {
            // Logique pour mettre à jour le profil
        }
    }
}