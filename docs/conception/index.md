[file name]: index.md

# Présentation générale

## Conception

Cette section présente les grandes lignes de l'architecture et des choix de conception retenus pour la plateforme d'aide au choix de cours.

## Approche utilisée

### Architecture choisie : Application monolithique modulaire

**Pattern architectural** : Application centralisée avec séparation claire des responsabilités en modules internes.

**Justification du choix** :
- **Simplicité** : Parfaitement adaptée à la portée du projet étudiant et à une équipe réduite.
- **Maintenabilité** : Codebase unique plus facile à gérer, déboguer et faire évoluer.
- **Déploiement** : Infrastructure simplifiée avec un conteneur Docker unique.
- **Performance** : Réduction de la latence inter-modules grâce à l'intégration locale.
- **Alignement fonctionnel** : Répond exactement aux exigences de la phase 2 du projet.

**Justification des choix du design** :
- **Cohésion forte** : Chaque classe a une responsabilité unique et bien circonscrite :
  - Les modèles représentent les données métier.
  - Les services encapsulent la logique.
  - Les contrôleurs gèrent les entrées/sorties HTTP.
  - Les utilitaires s'occupent des préoccupations transversales.
- **Couplage réduit** :
  - Les services dépendent de classes concrètes simples plutôt que d'interfaces complexes.
  - Le GestionnaireFichiers centralise toutes les opérations de persistance.
  - Le ClientPlanifium isole les communications externes.
- **Encapsulation** :
  - Tous les attributs sont privés (-), protégeant l'état interne.
  - Les méthodes exposent uniquement les opérations métier nécessaires.
  - Les détails de persistance et de communication réseau sont masqués derrière des APIs simples.
- **Simplicité pragmatique** : Conformément au feedback reçu, l'architecture évite les interfaces inutiles pour les opérations de base, privilégiant des classes concrètes directes tout en maintenant une structure organisée.

### Découpage en modules

**Couche Présentation** :
- **Application Web** : Interface responsive avec navigation intuitive.
- **Interface Ligne de Commande** : Outil rapide pour utilisateurs techniques.
- **Bot Discord** : Canal conversationnel pour collecte d'avis.

**Couche Application** :
- **API REST Principale** : Point d'entrée unique avec routage et validation.
- **Module Authentification** : Gestion des sessions utilisateur simplifiée.
- **Module Cours** : Intégration complète avec l'API Planifium.
- **Module Avis** : Collecte, agrégation et calcul des métriques.
- **Module Profil** : Gestion des préférences étudiantes.

**Couche Données** :
- **Base SQLite** : Stockage unifié des avis, préférences et cache Planifium.
- **Fichiers de configuration** : Paramètres d'intégration et préférences système.

## Contraintes prises en compte

### Contraintes techniques

**Hébergement et Infrastructure** :
- **Environnement Docker** : Conteneurisation pour une portabilité optimale.
- **API Planifium** : Respect strict du rate limiting et des formats imposés.
- **Performance** : Temps de réponse < 2s grâce au cache stratégique des données.
- **Bot Discord** : Intégration native avec la plateforme de communication étudiante.

**Stack technologique** :
- **Python/Flask** : Framework léger, flexible et parfaitement adapté.
- **SQLite** : Base de données simple et performante pour le volume attendu.
- **Architecture REST** : Standard industry pour les intégrations futures.
- **API JSON** : Format universel pour l'échange de données.

### Contraintes imposées par les exigences

**Sécurité et Confidentialité** :
- **Anonymisation stricte** : Avis stockés sans données personnelles identifiantes.
- **Validation rigoureuse** : Contrôle complet de toutes les entrées utilisateur.
- **Sécurité API** : Protection intégrée contre les attaques courantes.

**Performance et Disponibilité** :
- **Cache intelligent** : Réduction significative de la charge sur l'API Planifium.
- **Agrégation optimisée** : Calculs métriques haute performance.
- **Seuil de fiabilité** : Affichage conditionnel basé sur n ≥ 5 avis minimum.

**Intégration et Interopérabilité** :
- **Standard REST** : Conformité aux meilleures pratiques industry.
- **Documentation complète** : API entièrement documentée pour la maintenance.
- **Format JSON** : Échange standardisé avec tous les systèmes externes.

**Expérience Utilisateur** :
- **Interfaces multiples** : Web, CLI et Discord adaptés à différents besoins.
- **Recherche avancée** : Filtres multiples (session, charge, difficulté).
- **Personnalisation** : Adaptation fine selon le profil de chaque étudiant.
- **Feedback immédiat** : Calcul et affichage en temps réel des métriques.

### Contraintes organisationnelles

**Calendrier académique** :
- **Périodes critiques** : Disponibilité garantie pendant les inscriptions.
- **Maintenance planifiée** : Interventions alignées sur les vacances académiques.

**Support utilisateur** :
- **Documentation adaptée** : Guides spécifiques pour chaque interface.
- **Support technique** : Procédures claires pour résolution des problèmes.

## Avantages pour les cas d'utilisation

**Pour la Recherche de cours (CU1)** :
- ServiceCours.rechercher() utilise le cache pour les performances.
- ClientPlanifium fournit les données fraîches lorsque nécessaire.
- Architecture optimisée pour les requêtes fréquentes.

**Pour la Consultation détaillée (CU2)** :
- ServiceCours.obtenirAvecDetails() agrège cours, avis et statistiques.
- ServiceAvis fournit les évaluations associées.
- Séparation claire entre données de base et données dérivées.

**Pour la Comparaison de cours (CU3)** :
- ServiceCours.comparer() réutilise les méthodes existantes.
- Structure flexible pour l'ajout de nouveaux critères de comparaison.
- Gestion centralisée de la logique de comparaison.

## Évolutivité et maintenabilité

**Extension facile** : Ajout de nouveaux critères de recherche ou de comparaison via modification localisée de ServiceCours.

**Testabilité** : Classes aux responsabilités claires, facilement mockables pour les tests unitaires.

**Remplacement aisé** : Le GestionnaireFichiers peut être remplacé par une base de données sans affecter les services.

**Support multi-canal** : L'architecture supporte déjà web et Discord, pouvant être étendue à d'autres interfaces.

**Correspondance avec l'implémentation réelle** : Cette conception théorique se traduit en pratique par une implémentation allégée dans le code, où les principes de séparation sont maintenus tout en évitant la sur-ingénierie. Les services implémentent directement les trois CU requis, utilisant les utilitaires fournis pour la persistance et la communication externe.