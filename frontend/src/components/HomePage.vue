<template>
    <div class="container mt-5">
        <h2>Liste des Personnes avec CV</h2>
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
            persons: []
        };
    },
    async created() {
        try {
            const response = await axios.get('http://localhost:8081/api/persons');
            this.persons = response.data;
        } catch (error) {
            console.error('Erreur lors de la récupération des personnes', error);
        }
    }
};
</script>
  