---
title: Conception - Modèle de données
---

# Modèle de données

## Entités principales

### Utilisateur (User)

Représente un étudiant ou membre du personnel de l'Université de Montréal. Stocke les informations de profil, préférences et historique académique.

**Attributs principaux** :

- id (UUID), email*udem, nom, prénom, programme, cycle*études, préférences (JSON)

### Cours (Course)

Entité centrale représentant un cours offert à l'Université de Montréal avec ses métadonnées complètes.

**Attributs principaux** :

- sigle, titre, description, crédits, département, prérequis, coresquis

### Avis (Review)

Évaluation et commentaire d'un étudiant sur un cours qu'il a suivi. Inclut des métriques quantitatives et qualitatives.

**Attributs principaux** :

- note_générale, charge_travail, difficulté, qualité_enseignement, commentaire, date_soumission

### Recommandation (Recommendation)

Suggestion personnalisée de cours générée par le système pour un étudiant spécifique.

**Attributs principaux** :

- score_pertinence, motifs_recommandation, date_génération, statut

### Session (Session)

Période académique durant laquelle les cours sont offerts (Automne, Hiver, Été).

**Attributs principaux** :

- année, saison, date_début, date_fin

## Relations entre entités

- **1 Utilisateur** → **N Avis** (Un étudiant peut évaluer plusieurs cours)
- **1 Cours** → **N Avis** (Un cours peut recevoir plusieurs évaluations)
- **1 Utilisateur** → **N Recommandations** (Un étudiant reçoit plusieurs suggestions)
- **1 Cours** → **N Recommandations** (Un cours peut être recommandé à plusieurs étudiants)
- **1 Session** → **N Cours** (Une session propose plusieurs cours)
- **1 Utilisateur** → **N Cours suivis** (Historique des cours complétés)

## Contraintes métier

### Intégrité des données

- Un **Avis** ne peut être soumis que pour un cours que l'utilisateur a réellement suivi (vérification via données académiques)
- Les **notes** dans les avis doivent être dans une plage prédéfinie (1-5 étoiles)
- Un utilisateur ne peut soumettre qu'**un seul avis** par cours

### Validation des prérequis

- Les **Recommandations** doivent respecter les prérequis académiques de l'étudiant
- La **charge de travail** estimée ne peut dépasser les limites du programme

### Confidentialité et sécurité

- Les **données personnelles** doivent être anonymisées dans les statistiques agrégées
- Les **avis** sont modérés avant publication pour respecter les règles de conduite

### Fraîcheur des données

- Les **informations de cours** doivent être synchronisées avec Planifium au moins quotidiennement
- Les **recommandations** sont recalculées périodiquement pour refléter l'évolution du profil

## Évolution potentielle du modèle

### Court terme (Phase 2)

- Ajouter l'entité **Programme** pour une gestion fine des contraintes académiques
- Introduire **Favoris** pour permettre aux étudiants de sauvegarder des cours d'intérêt
- Ajouter **Statistiques Avancées** pour l'analyse des tendances

### Moyen terme (Phase 3)

- Support **Groupes d'étude** pour la planification collaborative
- Entité **Cheminement** pour modéliser les parcours académiques types
- **Intégration calendrier** pour la gestion des emplois du temps

### Long terme (Phase 4)

- **Apprentissage automatique** avec historique des prédictions et résultats
- **Analytics institutionnel** pour l'administration universitaire
- **API étendue** pour l'intégration avec d'autres systèmes universitaires

---

**Diagramme conceptuel** :
