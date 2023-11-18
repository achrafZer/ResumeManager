<template>
  <div class="container mt-5">
    <form @submit.prevent="login">
      <div class="mb-3">
        <label for="email" class="form-label">Mail Adress</label>
        <input type="email" class="form-control" id="email" v-model="email" required>
      </div>
      <div class="mb-3">
        <label for="password" class="form-label">Password</label>
        <input type="password" class="form-control" id="password" v-model="password" required>
      </div>
      <button type="submit" class="btn btn-primary">Login</button>
      <div v-if="errorMessage" class="alert alert-danger mt-3">{{ errorMessage }}</div>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      email: '',
      password: '',
      errorMessage: '' // Pour afficher les messages d'erreur
    };
  },
  methods: {
    async login() {
      try {
        this.errorMessage = ''; // Réinitialiser le message d'erreur
        const response = await axios.post('http://localhost:8081/secu-users/login', {
          username: this.email,
          password: this.password
        });
        localStorage.setItem('user-token', response.data); // Stockage du JWT
        // Redirection ou mise à jour de l'UI ici
        // par exemple: this.$router.push('/dashboard');
      } catch (error) {
        if (error.response) {
          // Message d'erreur du serveur
          this.errorMessage = error.response.data.message || 'Erreur de connexion';
        } else {
          // Erreur de réseau ou autre
          this.errorMessage = 'Erreur de connexion';
        }
      }
    }
  }
};
</script>

<style>
/* Ajoutez des styles supplémentaires si nécessaire */
</style>
