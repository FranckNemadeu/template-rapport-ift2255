[file name]: index.md

## title: Présentation générale

# Conception

Cette section présente les grandes lignes de l'architecture et des choix de conception retenus pour la plateforme d'aide au choix de cours.

## Approche utilisée

### Architecture choisie : Application monolithique modulaire

**Pattern architectural** : Application centralisée avec séparation claire des responsabilités en modules internes

**Justification du choix** :

- **Simplicité** : Parfaitement adaptée à la portée du projet étudiant et à une équipe réduite
- **Maintenabilité** : Codebase unique plus facile à gérer, déboguer et faire évoluer
- **Déploiement** : Infrastructure simplifiée avec un conteneur Docker unique
- **Performance** : Réduction de la latence inter-modules grâce à l'intégration locale
- **Alignement fonctionnel** : Répond exactement aux exigences de la phase 2 du projet

### Découpage en modules

**Couche Présentation** :

- **Application Web** : Interface responsive avec navigation intuitive
- **Interface Ligne de Commande** : Outil rapide pour utilisateurs techniques
- **Bot Discord** : Canal conversationnel pour collecte d'avis

**Couche Application** :

- **API REST Principale** : Point d'entrée unique avec routage et validation
- **Module Authentification** : Gestion des sessions utilisateur simplifiée
- **Module Cours** : Intégration complète avec l'API Planifium
- **Module Avis** : Collecte, agrégation et calcul des métriques
- **Module Profil** : Gestion des préférences étudiantes

**Couche Données** :

- **Base SQLite** : Stockage unifié des avis, préférences et cache Planifium
- **Fichiers de configuration** : Paramètres d'intégration et préférences système

## Contraintes prises en compte

### Contraintes techniques

**Hébergement et Infrastructure** :

- **Environnement Docker** : Conteneurisation pour une portabilité optimale
- **API Planifium** : Respect strict du rate limiting et des formats imposés
- **Performance** : Temps de réponse < 2s grâce au cache stratégique des données
- **Bot Discord** : Intégration native avec la plateforme de communication étudiante

**Stack technologique** :

- **Python/Flask** : Framework léger, flexible et parfaitement adapté
- **SQLite** : Base de données simple et performante pour le volume attendu
- **Architecture REST** : Standard industry pour les intégrations futures
- **API JSON** : Format universel pour l'échange de données

### Contraintes imposées par les exigences

**Sécurité et Confidentialité** :

- **Anonymisation stricte** : Avis stockés sans données personnelles identifiantes
- **Validation rigoureuse** : Contrôle complet de toutes les entrées utilisateur
- **Sécurité API** : Protection intégrée contre les attaques courantes

**Performance et Disponibilité** :

- **Cache intelligent** : Réduction significative de la charge sur l'API Planifium
- **Agrégation optimisée** : Calculs métriques haute performance
- **Seuil de fiabilité** : Affichage conditionnel basé sur n ≥ 5 avis minimum

**Intégration et Interopérabilité** :

- **Standard REST** : Conformité aux meilleures pratiques industry
- **Documentation complète** : API entièrement documentée pour la maintenance
- **Format JSON** : Échange standardisé avec tous les systèmes externes

**Expérience Utilisateur** :

- **Interfaces multiples** : Web, CLI et Discord adaptés à différents besoins
- **Recherche avancée** : Filtres multiples (session, charge, difficulté)
- **Personnalisation** : Adaptation fine selon le profil de chaque étudiant
- **Feedback immédiat** : Calcul et affichage en temps réel des métriques

### Contraintes organisationnelles

**Calendrier académique** :

- **Périodes critiques** : Disponibilité garantie pendant les inscriptions
- **Maintenance planifiée** : Interventions alignées sur les vacances académiques

**Support utilisateur** :

- **Documentation adaptée** : Guides spécifiques pour chaque interface
- **Support technique** : Procédures claires pour résolution des problèmes
