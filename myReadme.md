# Questions :
+ Est-ce qu'il faut faire des méthodes update ?
+ Est-ce que nous avons les mêmes tests des services ?
+ Est-ce que nous avons le même peuplement ?
+ Est-ce que mon peuplement est bon ?
+ Pourrais-je utiliser la BDD de AABD ?
+ Est-ce que pour tester l'api, nous pouvons utiliser les données que nous insérons dans la base dans le @Service, ou 
devons-nous à chaque test lui créer ses propres éléments de test ?
  

# A NE PAS OUBLIER :
- Stocker les jetons dans le local storage

# FICHE DES TESTS :
- /app : page vide
- /app/home : rechercher "che"
- /app/home : ouvrir l'un des CVs 
- /app/home : appuyer sur login
- /app/home : s'identifier avec un mauvais mdr
- /app/home : s'identifier avec un bon mdr
- aller sur modifier le profil
- Modifier un élément
- Se déconnecter


# A FAIRE :
+ README
+ Trouver pourquoi si je supprime app1 mon api ne marche plus
+ Recherche sur les tests de l'api pour les méthodes non GET
+ Sécuriser les MDP
+ Gérer les exceptions (des intercepteurs pour capturer les exceptions et renvoyer des réponses d'erreur significatives)
+ Persister les données
+ Avancer la conception de l'entité Activity
+ Tester les controllers (MOCK) (0/2) (il manque Activity en entier, et DELETE, POST, PUT pour Person)
+ Traiter les cas du style : si je supprime une personne, je supprime son CV
+ Recherche relations en cascade en BDD
+ Faire un tour et regarder tout ce que sonarLint me demande de faire
+ Vérification de tous les paramètres (email, date...)
+ Rajouter les contraintes dans la BDD (not blanc / not null...)
+ Revoir tous les tests
+ Couverture
+ Vérifier quand l'api est plus développée qu'il n'y a pas de soucis de boucles
+ Revoir tous les tests des services
+ Tester les trois BDD avec une api (3/3)
* Tout documenter en JavaDoc
+ Traiter tous les get avec des requêtes SQL
+ Supprimer app1 et renommer app2
+ Relire tous les tests
+ Recherche sur les bonnes pratiques pour appeler des controllers (ceux qui font des recherches avec un paramètre)
+ Supprimer la branche non utile du git
+ Vérifier en demandant à chatGPT si j'ai pas oublié une ou plusieurs @valid pour les attribut
+ Faire en sorte que name ne prenne que des valeurs alphabétiques
+ Demander à GPT si j'ai besoin d'un SpringConfiguration
+ Tester les controllers (couverture trop basse pour le moment)
+ Redistribuer le répertoire de tests
+ Faire un git clean
+ Créer un Chat pour la JavaDoc
+ Tester getPersonByEmail
+ Demander au prof si utiliser XUser est quand même recommandé
+ La méthode searchActivityByTitle cherche une activité par titre et non pas par une partie du titre
+ Faire en sorte que le back exécute npm run build pour ne pas avoir à lancer et back et front à chaque fois
+ Définir un header
+ Pour le moment les controllers cherches les CV par prénom, nom ou activité, et c'est côté client que nous rassemblons les trois en même temps. Faire en sorte qu'il y'ait un controller qui fait ça dès le début
+ Ajouter des messages clairs lors des erreurs
+ Changer la validation du website (qu'on puisse commencer par www par exemple)
+ Popup pour dire que le profil a bien été modifié
+ Faire en sorte que sur la page de modif, les input contiennent de base la valeur actuelle de la personne
+ Faire en sorte que tous les @Valid retournent un message clair
+ Faire en sorte que /app renvoie directement à /app/home
+ Gérer les redirections :
  + /app : /app/home
  + Modifier le profil : /app/users/1/profile
  + Modifier une activité : /app/users/1/edit-activities
+ résoudre l'erreur du timeout 1000
+ Documenter l'API
+ Supprimer les rôles
+ Mettre en place une seule requête pour les trois opérations plutôt que trois requêtes combinées avec Java
+ Refactor les repository
+ Traiter le cas où la personns entre dans la barre de recherche "chef" plutôt que "Chef"
+ - - - Nettoyer webapp des éléments de movie
+ - - Régler le souci des accents
+ - - Faire une liste de tous les éléments donc nous auront besoin dans chacune des pages
  + Header : connexion ou déconnexion( de l'enlever de là où il est maintenant)
  + Header : bouton profil si on est connecté
  + Header : bouton créer un user si on est connecté
  + Home : 
    + Pagination
  + page de profil : 
    + le bouton d'accueil n'est pas adéquat (si on appuie dessus et qu'on est connecté on doit avoir la page home avec un bouton déconnexion, et avec un petit motif sur notre CV qui sera épinglé)
+ - - - Faire un traitement de toutes les routes normales + routes d'api qui doivent être accessible uniquement par authentification et vérifier que ça ne merche que si le JWT est donné avec la requête
+ - Faire en sorte qu'il y ait qu'un seul profile d'actif dans le properties
+ - - - Lors de la recherche, la deuxième page des résultats n'est pas incluses dans le tri
+ - - Supprimer les package json qui se trouvent dans le répertoire parent et vérifier que tout marche bien quand même
+ - Faire en sorte que entrée marche comme un appui sur le bouton rechercher
+ - - Faire un tour de code pour éliminer toutes les import avec '*'
+ - Réorganiser totalement le application.properties
+ - Faire en sorte que si je fais une recherche et qu'après je rend la barre de recherche à vide, toutes les personnes s'affichent







- - Faire une gestion des erreurs pour les formulaires

- - - Un tour bootstrap sur l'ensemble des pages

- - Suppression d'une activité
- - Demander à GPT de m'aider à trouver des failles de sécurité
- Tests fonctionnels avec un outil de QL
- Enlever les warning affichés dans la console
- Ajouter des éléments à la BDD (exemple ajouter "lieu" dans activities)
- - Mettre en place des DTO plutôt que de mettre les entités en paramètre dans les controller api
- Ajouter un controller pour chacune des erreur HTTP
- Prendre un ordi de la fac et tester les fichiers du rendu avec
- Demander à GPT les diagrams qu'il estime importants dans le rapport 
- - - Rédiger le cahier des charges
- Faire en sorte qu'après chaque recherche, les éléments affichés aient le mots cléf surligné pour afficher pourquoi l'élément a été choisi
- Ajouter des photos sur les profils 
- - Faire un tour pour appliquer la règle : tout ce qu'on peut faire en SQL faisons le en SQL
- Mettre en place un petit texte qui s'affiche quand le curseur est sur un élément
- - Faire une comparaison de toutes les routes avec celle de linkedin et rédiger une liste de modifications à faire pour être user friendly
- - - Mettre en place 1000 entités
- - Nettoyer les console.log
- Comparer tous les formulaires de l'application avec les classes dans model pour être sûr qu'aucun champ n'a été oublié
- Faire en sorte que tous les controller en cas de paramètre non valide, ils retournent une erreur 400 et non pas 500 comme c'est le cas maintenant
- - Mettre en place des * sur les éléments nécessaires dans les formulaires

# REPRISE :

L'authentification marche, mais on n'est pas redirigé à la page home après s'être connecté

# A NE PAS OUBLIER DANS LE CAHIER DES CHARGES OU/ET LE RAPPORT, LA SOUTENANCE
- Je me suis inspiré totalement de linkedin, c'est par rapport à ça que j'ai mis en place mes "@NotNull" par exemple
- PersonService est un service qui gère une personne en tant qu'entité, en revanche UserService gère une personne en tant qu'utilisateur qui se connecte
- Mettre les commentaires dans le mode interactif pour qu'ils soient moins gênants avant la soutenance
- Les rôles sont inutiles dans ce programme mais sont là pour les prochaines versions de l'application
- Les @Valid que nous utilisons, sont retournés comme erreur dans les formulaires et non pas des nouvelles erreurs purement front
- SonarLint

# A VERIFIER AVEC FAB ET JUBA
- Les routes sécurisées et celles publiques
- Les boutons présents dans chaque page
- Avons-nous toujours redirigé à login ?