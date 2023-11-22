console.log("ResumeComponent.js chargé");

export default {
    template: `
    <div class="container mt-5">
        <h2>Détails de la Personne</h2>
        <!-- Affichez ici les détails de la personne -->
    </div>
    `,
    data() {
        return {
            // Vos données ici
        };
    },
    created() {
        const personId = this.$route.params.id;
        console.log(personId);
    },
    methods: {
        // Méthodes supplémentaires si nécessaire
    }
};