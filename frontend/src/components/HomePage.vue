<template>
    <div class="container mt-5">
        <h2>Liste des Personnes avec CV</h2>
        <!--Barre de recherche par nom, prénom ou titre d'activité-->
        <div class="mb-3">
            <input type="text" class="form-control" placeholder="Rechercher par nom, prénom ou titre d'activité"
                v-model="searchQuery">
            <button class="btn btn-primary mt-2" @click="search">Rechercher</button>
        </div>

        <!--Liste des personnes avec les titres des activités qu'elles ont dans le CV-->
        <div v-for="person in persons" :key="person.id" class="mb-4">
            <h3>{{ person.firstName }} {{ person.lastName }}</h3>
            <ul v-if="person.cv && person.cv.activities.length">
                <li v-for="activity in person.cv.activities" :key="activity.id">
                    {{ activity.title }} ({{ activity.startYear }} - {{ activity.endYear }})
                </li>
            </ul>
            <p v-else>Aucune activité de CV disponible.</p>
        </div>
    </div>
</template>
  
<script>
import axios from 'axios';

export default {
    data() {
        return {
            persons: [],
            searchQuery: ''
        };
    },

    methods: {

        async fetchPersons() {
            try {
                const response = await axios.get('http://localhost:8081/api/persons');
                this.persons = response.data;
            } catch (error) {
                console.error('Erreur lors de la récupération des personnes', error);
            }
        },

        async search() {
            try {
                let endpoint = 'http://localhost:8081/api/persons/search-by-activity-title';
                if (this.searchQuery.trim()) {
                    endpoint += `?activityTitle=${this.searchQuery}`;
                } else {
                    // Si aucune requête de recherche, récupérer la liste complète
                    this.fetchPersons();
                    return;
                }
                const response = await axios.get(endpoint);
                this.persons = response.data;
            } catch (error) {
                console.error('Erreur lors de la recherche', error);
            }
        }
    }
};
</script>
  