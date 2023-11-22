console.log("ResumeComponent.js chargé");

export default {

    template: `
      <div class="container mt-5">
        <h2>CV de {{ person.firstName }} {{ person.lastName }}</h2>
        <div v-if="person">
          <h3>Informations Personnelles</h3>
          <p>Email: {{ person.email }}</p>
          <p>Site Web: {{ person.website }}</p>
          <p>Date de naissance: {{ formatDate(person.birthDate) }}</p>

          <h3>Expériences et Formations</h3>
          <ul v-if="person.cv && person.cv.activities.length">
            <li v-for="activity in person.cv.activities" :key="activity.id">
              <strong>{{ activity.title }}</strong> ({{ activity.startYear }} - {{ activity.endYear }})
              <p>{{ activity.description }}</p>
            </li>
          </ul>
          <p v-else>Aucune activité de CV disponible.</p>
        </div>
        <div v-else>
          <p>Chargement...</p>
        </div>
      </div>
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
            const options = { year: 'numeric', month: 'long', day: 'numeric' };
            return new Date(dateString).toLocaleDateString(undefined, options);
        }
    }
};