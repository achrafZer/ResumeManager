console.log("app.js chargé");

const myApp = {

    data() {
        console.log("data");
        return {
            persons: [],
            axios: null
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
        // Place pour les futures méthodes
    }
}

const app = Vue.createApp(myApp);
app.mount('#myApp');