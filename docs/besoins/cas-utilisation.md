---
title: Analyse des besoins - Cas d'utilisation
---

# Cas d'utilisation

## Vue d’ensemble

Le système de plateforme d'aide au choix de cours permet aux étudiants de l'Université de Montréal de prendre des décisions éclairées concernant leur sélection de cours. Cette analyse présente les cas d'utilisation principaux qui décrivent les interactions entre les acteurs et le système.

## Liste des cas d'utilisation

| ID   | Nom                                   | Acteurs principaux           | Description                                                    |
| ---- | ------------------------------------- | ---------------------------- | -------------------------------------------------------------- |
| CU01 | Consulter les informations d'un cours | Étudiant, Utilisateur invité | Consultation des détails et statistiques d'un cours spécifique |
| CU02 | Comparer plusieurs cours              | Étudiant authentifié         | Comparaison côte à côte de différents cours                    |
| CU03 | Consulter les avis sur un cours       | Étudiant authentifié         | Consulter les avis sur un cours recherché                      |
| CU04 | Personnaliser le profil étudiant      | Étudiant authentifié         | Configuration des préférences et du profil académique          |
| CU05 | Rechercher cours                      | Étudiant authentifié         | Rchercher des cours selon les criteres                         |
| CU06 | Gérer les données des cours           | Administrateur               | Administration et mise à jour des informations des cours       |
| CU07 | Modérer les avis étudiants            | Administrateur               | Validation et modération des évaluations soumises              |
| CU08 | Générer des rapports étudiants        | Administrateur               | Production de statistiques et analyses des étudiants           |

![Diagramme du cas d:utilisation](CU.jpg)

## Détail

### CU01 - Consulter les informations d'un cours

**Acteurs** : Étudiant (principal), Utilisateur invité (secondaire)  
**Préconditions** : Le cours existe dans la base de données  
**Postconditions** : L'utilisateur a accès aux informations détaillées du cours  
**Déclencheur** : L'utilisateur recherche ou sélectionne un cours spécifique  
**Dépendances** : Accès aux données Planifium et aux statistiques académiques  
**But** : Fournir une vue complète et transparente des caractéristiques d'un cours pour faciliter la prise de décision
**Scénario principal** :

1. L'utilisateur recherche un cours par code, titre ou mots-clés
2. Le système affiche la liste des cours correspondants
3. L'utilisateur sélectionne un cours spécifique
4. Le système récupère les données depuis Planifium (horaire, préalables, crédits)
5. Le système affiche les informations complètes du cours
6. Pour les étudiants authentifiés, le système vérifie et affiche l'éligibilité

**Scénarios alternatifs** :

1. Cours non trouvé : Afficher "Aucun cours correspondant à votre recherche"
2. Données indisponibles : Afficher "Informations temporairement indisponibles"
3. Utilisateur invité : Afficher seulement les informations basiques sans éligibilité

### CU02 - Comparer plusieurs cours

**Acteurs** : Étudiant authentifié (principal)  
**Préconditions** : L'utilisateur est authentifié, au moins deux cours sont sélectionnés  
**Postconditions** : Affichage comparatif des cours sélectionnés  
**Déclencheur** : L'utilisateur demande une comparaison entre plusieurs cours  
**Dépendances** : Données disponibles pour tous les cours sélectionnés  
**But** : Permettre une analyse comparative objective entre différents cours basée sur des critères multiples
**Scénario principal** :

1. L'étudiant sélectionne 2 à 5 cours à comparer
2. Le système récupère les données pour chaque cours
3. Le système affiche un tableau comparatif avec :

   - Charge de travail estimée
   - Difficulté perçue
   - Résultats académiques (moyenne, échecs)
   - Préalables et corequis
   - Horaire des sessions

4. Le système calcule et affiche la charge de travail totale estimée
5. L'étudiant peut sauvegarder la comparaison

**Scénarios alternatifs** :

- Cours incompatible : Afficher un avertissement pour les horaires qui se chevauchent
- Données manquantes : Indiquer "Données non disponibles" pour les métriques manquantes
- Un seul cours sélectionné : Proposer d'ajouter d'autres cours à la comparaiso

### CU03 - Consulter les avis sur un cours

**Acteurs** : Étudiant authentifié (principal)
**Préconditions** :

- Le cours existe dans le système
- Au moins 5 avis distincts ont été collectés pour ce cours (n ≥ 5)
- Les données d'avis sont disponibles et à jour

**Postconditions** :

- L'utilisateur a accès aux avis agrégés et filtrés du cours
- Les métriques de charge de travail et difficulté sont affichées

**Déclencheur** : L'utilisateur consulte la fiche d'un cours et souhaite voir les retours d'expérience

**Dépendances** :

- Collecte régulière d'avis via le bot Discord
- Système d'agrégation des données fonctionnel
- Données de session académique à jour

**But** : Permettre à l'étudiant d'évaluer un cours grâce aux retours d'expérience authentiques d'autres étudiants, tout en garantissant la fiabilité grâce au seuil minimal d'avis

**Scénario principal** :

1. L'étudiant consulte la fiche d'un cours
2. Le système vérifie si au moins 5 avis sont disponibles
3. Le système affiche les avis agrégés avec :
   - Charge de travail moyenne
   - Difficulté perçue moyenne
   - Répartition des notes
   - Commentaires récents
4. L'étudiant peut filtrer les avis par session, charge de travail ou difficulté
5. Le système met à jour l'affichage selon les filtres appliqués

**Scénarios alternatifs** :

- **Avis insuffisants** : Si moins de 5 avis, afficher "Avis collectés insuffisants pour une consultation fiable"

### CU04 - Personnaliser le profil étudiant

**Acteurs** : Étudiant authentifié (principal)  
**Préconditions** : L'utilisateur possède un compte et est connecté  
**Postconditions** : Les préférences sont sauvegardées et utilisées pour les recommandations  
**Déclencheur** : L'étudiant accède à ses paramètres de profil  
**Dépendances** : Système d'authentification fonctionnel  
**But** : Adapter l'expérience utilisateur et les recommandations au profil individuel
**Scénario principal** :

1. L'étudiant accède à la section "Mon profil"
2. Le système affiche les préférences actuelles
3. L'étudiant modifie ses préférences :

   - Niveau préféré théorie/pratique (échelle 1-5)
   - Centres d'intérêt (sélection multiple)
   - Programme et cycle d'études
   - Contraintes horaires

4. L'étudiant sauvegarde les modifications
5. Le système confirme la mise à jour et adapte les suggestions

**Scénarios alternatifs** :

- Première configuration : Proposer un assistant de configuration du profil
- Données invalides : Afficher des messages d'erreur spécifiques
- Annulation : Retour au menu principal sans sauvegarder

### CU05 - Rechercher des cours

**Acteurs** : Utilisateur invité (principal), Étudiant Authentifié
**Préconditions** : Aucune (accessible sans authentification)
**Postconditions** : Liste des cours correspondants aux critères affichée
**Déclencheur** : L'utilisateur veut trouver des cours par critères
**But** : Trouver rapidement des cours pertinents selon différents critères
**Scénario principal** :

1. L'utilisateur saisit des critères (code, titre, mots-clés, département)
2. Le système interroge Planifium et la base locale
3. Affichage des résultats avec codes, titres et crédits
4. L'utilisateur peut affiner la recherche avec des filtres

### CU06 - Gérer les données des cours

**Acteurs** : Administrateur (principal)  
**Préconditions** : Accès administrateur validé  
**Postconditions** : Base de données des cours mise à jour  
**Déclencheur** : Changements dans l'offre de cours ou corrections nécessaires  
**Dépendances** : Synchronisation avec les sources de données officielles  
**But** : Maintenir l'exactitude et la fraîcheur des informations des cours

### CU07 - Modérer les avis étudiants

**Acteurs** : Administrateur (principal)  
**Préconditions** : Nouveaux avis en attente de modération  
**Postconditions** : Avis validés ou rejetés selon les règles établies  
**Déclencheur** : Soumission d'un nouvel avis par un étudiant  
**Dépendances** : Politique de modération définie  
**But** : Assurer la qualité et la pertinence des évaluations partagées

### CU08 - Générer des rapports étudiants

**Acteurs** : Administrateur (principal)  
**Préconditions** : Données d'utilisation collectées sur une période  
**Postconditions** : Rapports statistiques produits et accessibles  
**Déclencheur** : Demande d'analyse ou période de reporting  
**Dépendances** : Infrastructure de collecte de données opérationnelle  
**But** : Fournir des insights sur l'utilisation de la plateforme et son impact
