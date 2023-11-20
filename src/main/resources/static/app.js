console.log("app.js chargé");

const myApp = {

    data() {
        console.log("data");
        return {
            persons: [],
            axios: null,
            searchQuery: ''
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
        async search() {
            try {
                let endpoint = 'http://localhost:8081/api/persons/search-first-name';
                if (this.searchQuery.trim()) {
                    endpoint += `?firstName=${this.searchQuery}`;
                } else {
                    // Si aucune requête de recherche, récupérer la liste complète
                    this.created();
                    return;
                }
                const response = await axios.get(endpoint);
                this.persons = response.data;
            } catch (error) {
                console.error('Erreur lors de la recherche', error);
            }
        }
    }
}

const app = Vue.createApp(myApp);
app.mount('#myApp');