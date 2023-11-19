# Questions :
+ Est-ce qu'il faut faire des méthodes update ?
+ Est-ce que nous avons les mêmes tests des services ?
+ Est-ce que nous avons le même peuplement ?
+ Est-ce que mon peuplement est bon ?
+ Pourrais-je utiliser la BDD de AABD ?
+ Est-ce que pour tester l'api, nous pouvons utiliser les données que nous insérons dans la base dans le @Service, ou 
devons-nous à chaque test lui créer ses propres éléments de test ?

# A NE PAS OUBLIER :
- Le chifrement des MDP pourrait poser problème dans le futur.

# A FAIRE :
+ README
+ Trouver pourquoi si je supprime app1 mon api ne marche plus
+ Recherche sur les tests de l'api pour les méthodes non GET
- Documenter l'API
- Tests fonctionnels avec un outil de QL
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
- Ajouter des éléments à la BDD (exemple ajouter "lieu" dans activities)
+ Revoir tous les tests
+ Couverture
+ Vérifier quand l'api est plus développée qu'il n'y a pas de soucis de boucles
+ Revoir tous les tests des services
+ Tester les trois BDD avec une api (3/3)
- Tout documenter en JavaDoc 
+ Traiter tous les get avec des requêtes SQL
- - Mettre en place des DTO plutôt que de mettre les entités en paramètre dans les controller api
+ Supprimer app1 et renommer app2 
+ Relire tous les tests 
+ Recherche sur les bonnes pratiques pour appeler des controllers (ceux qui font des recherches avec un paramètre)
- Pagination
- Ajouter un controller pour chacune des erreur HTTP
+ Supprimer la branche non utile du git
+ Vérifier en demandant à chatGPT si j'ai pas oublié une ou plusieurs @valid pour les attribut
+ Faire en sorte que name ne prenne que des valeurs alphabétiques
+ Demander à GPT si j'ai besoin d'un SpringConfiguration
- Tester les controllers (couverture trop basse pour le moment)
+ Redistribuer le répertoire de tests
- Prendre un ordi de la fac et tester les fichiers du rendu avec
+ Faire un git clean
+ Créer un Chat pour la JavaDoc
- Faire en sorte que tous les @Valid retournent un message clair
- Demander à GPT les diagrams qu'il estime importants dans le rapport 
- Rédiger le cahier des charges
- Nettoyer webapp des éléments de movie
- Recherche Outil pour savoir les éléments du pom.xml qui ne sont pas utilisés
- Pour le JwtProvider, demander à gpt "selon toi quelle clé secrète devrais-je choisir ?"
- Gérer les rôles
+ Tester getPersonByEmail
+ Demander au prof si utiliser XUser est quand même recommandé
- Changer secu-user par autre chose
- La méthode searchActivityByTitle cherche une activité par titre et non pas par une partie du titre
- Refactor les repository
- Faire en sorte que le back exécute npm run build pour ne pas avoir à lancer et back et front à chaque fois 
- Supprimer les package json qui se trouvent dans le répertoire parent et vérifier que tout marche bien quand même 

# REPRISE :

5. Faire un plan pour gérer les routes auxquelles il faudra s'authentifier


# A NE PAS OUBLIER DANS LE CAHIER DES CHARGES OU/ET LE RAPPORT
- Je me suis inspiré totalement de linkedin, c'est par rapport à ça que j'ai mis en place mes "@NotNull" par exemple
- PersonService est un service qui gère une personne en tant qu'entité, en revanche UserService gère une personne en tant qu'utilisateur qui se connecte