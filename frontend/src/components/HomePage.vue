<template>
    <div class="container mt-5">
        <h2>Personnes avec CV</h2>
        <ul v-if="persons.length">
            <li v-for="person in persons" :key="person.id">{{ person.firstName }} {{ person.lastName }}</li>
        </ul>
        <p v-else>Aucune personne à afficher.</p>
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
            const response = await axios.get('http://localhost:8080/api/persons');
            this.persons = response.data;
        } catch (error) {
            console.error('Erreur lors de la récupération des personnes', error);
        }
    }
};
</script>
  