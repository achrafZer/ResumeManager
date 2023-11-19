<template>
  <div>
    <!-- Barre de navigation -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="container-fluid">
        <!-- Logo -->
        <img src="./assets/logo.jpg" alt="Logo" class="navbar-brand" style="width: 100px; height: auto;" />

        <!-- Liens de navigation -->
        <div class="collapse navbar-collapse">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <router-link to="/home" class="nav-link">Accueil</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/login" class="nav-link">Connexion</router-link>
            </li>
            <!-- Vous pouvez ajouter plus de liens ici -->
          </ul>
        </div>
      </div>
    </nav>

    <!-- Vue de la route actuelle -->
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
/* Ajoutez votre style personnalisé ici si nécessaire */
</style>
