---
title: EduGuide - Documentation Complète
---

# EduGuide - Plateforme d'Aide au Choix de Cours

## 📚 Table des Matières

1. [Cadre du Projet](#cadre-du-projet)
2. [Analyse des Exigences](#analyse-des-exigences)
3. [Conception](#conception)
4. [Implémentation](#implémentation)
5. [Tests et Évaluation](#tests-et-évaluation)
6. [Bilan du Projet](#bilan-du-projet)

---

## 🎯 Cadre du Projet

### Description du Projet

EduGuide est une plateforme web intelligente conçue pour les étudiants de l'Université de Montréal, visant à transformer le processus complexe du choix de cours en une expérience éclairée et personnalisée.

### Équipe de Développement

**Équipe 06 - IFT2255**

| Membre                         | Matricule | Rôle Principal | Tâches Responsables |
| ------------------------------ | --------- | -------------- | ------------------- |
| Franck Martial Nemadeu Ngamako | 20260750  | Tout les roles | Tout le projet      |

### Échéancier et Répartition des Tâches

#### Phase 1 - Analyse et Conception

| Tâche                       | Responsable | Statut     |
| --------------------------- | ----------- | ---------- |
| Analyse des besoins         | Franck      | ✅ Terminé |
| Spécification des exigences | Franck      | ✅ Terminé |
| Conception architecture     | Franck      | ✅ Terminé |
| Modèles de données          | Franck      | ✅ Terminé |

#### Phase 2 - Développement

| Tâche                     | Responsable | Statut     |
| ------------------------- | ----------- | ---------- |
| Service Authentification  | Franck      | ✅ Terminé |
| Service Cours & Planifium | Franck      | ✅ Terminé |
| Interface principale      | Franck      | ✅ Terminé |
| Service Recommandations   | Franck      | ✅ Terminé |

#### Phase 3 - Intégration et Tests

| Tâche                  | Responsable | Statut     |
| ---------------------- | ----------- | ---------- |
| Intégration complète   | Franck      | ✅ Terminé |
| Tests utilisateurs     | Franck      | ✅ Terminé |
| Déploiement production | Franck      | ✅ Terminé |

---

## 📋 Analyse des Exigences

### Description du Domaine

#### Fonctionnement

La plateforme agrège des données provenant de sources multiples :

- **Données officielles** de l'API Planifium UdeM
- **Avis et retours d'expérience** d'étudiants
- **Statistiques académiques** historiques anonymisées
- **Algorithmes de recommandation** personnalisés

#### Acteurs Principaux

- **Étudiant Authentifié** : Consulte, évalue, reçoit des recommandations
- **Utilisateur Invité** : Consultation limitée sans authentification
- **Administrateur** : Gestion données, modération, rapports
- **Systèmes Externes** : API Planifium, CAS UdeM, bases académiques

#### Dépendances

- **API Planifium** : Source de vérité pour les données cours
- **CAS UdeM** : Authentification centralisée
- **Infrastructure UdeM** : Hébergement et réseau

### Hypothèses de Travail

1. **Disponibilité données** : APIs UdeM accessibles et stables
2. **Participation étudiante** : Motivation à partager les retours d'expérience
3. **Performance** : Infrastructure supportant 500 utilisateurs simultanés
4. **Sécurité** : Conformité RGPD pour les données personnelles

### Glossaire

| Terme                 | Définition                                       |
| --------------------- | ------------------------------------------------ |
| **Planifium**         | Système officiel de planification des cours UdeM |
| **CAS UdeM**          | Système d'authentification centralisé            |
| **Tableau de bord**   | Vue agrégée des données d'un cours               |
| **Charge de travail** | Estimation temps requis hebdomadaire             |
| **Difficulté perçue** | Niveau complexité subjectif rapporté             |

### Analyse des Risques

#### Risque 1 - Indisponibilité API Planifium

**Probabilité** : Moyenne | **Impact** : Élevé
**Mitigation** : Cache Redis, données historiques, monitoring

#### Risque 2 - Biais collecte avis

**Probabilité** : Élevée | **Impact** : Moyen
**Mitigation** : Pondération profils, indicateurs confiance

#### Risque 3 - Performance recommandations

**Probabilité** : Moyenne | **Impact** : Élevé
**Mitigation** : Calculs asynchrones, cache résultats

#### Risque 4 - Confidentialité données

**Probabilité** : Faible | **Impact** : Très élevé
**Mitigation** : Anonymisation, chiffrement, audit

#### Risque 5 - Adoption utilisateurs

**Probabilité** : Moyenne | **Impact** : Moyen
**Mitigation** : Interface intuitive, promotion associative

### Exigences Fonctionnelles

#### Gestion Utilisateurs

- [x] **EF01** : Authentification via CAS UdeM
- [x] **EF02** : Gestion profil étudiant
- [x] **EF03** : Rôles et permissions

#### Gestion Cours

- [x] **EF04** : Synchronisation données Planifium
- [x] **EF05** : Consultation informations cours
- [x] **EF06** : Recherche et filtrage avancé

#### Système Avis

- [x] **EF07** : Soumission évaluations cours
- [x] **EF08** : Modération automatique et manuelle
- [x] **EF09** : Calcul statistiques agrégées

#### Recommandations

- [x] **EF10** : Génération suggestions personnalisées
- [x] **EF11** : Explication des recommandations
- [x] **EF12** : Historique des suggestions

### Exigences Non-Fonctionnelles

#### Performance

- [x] **ENF01** : Temps réponse < 2s (95% requêtes)
- [x] **ENF02** : Support 500 utilisateurs simultanés
- [x] **ENF03** : Recherche < 1s

#### Sécurité

- [x] **ENF04** : Authentification CAS UdeM
- [x] **ENF05** : Chiffrement données sensibles
- [x] **ENF06** : Audit des actions

#### Disponibilité

- [x] **ENF07** : 99% uptime heures ouvrables
- [x] **ENF08** : Sauvegardes automatiques
- [x] **ENF09** : Récupération < 4h

### Priorisation

#### Critique (MVP)

- EF01, EF04, EF05, EF07, EF10
- ENF01, ENF04, ENF07

#### Haute Priorité

- EF02, EF06, EF08, EF11
- ENF02, ENF05, ENF08

#### Moyenne Priorité

- EF03, EF09, EF12
- ENF03, ENF06, ENF09

---

## 🏗️ Conception

### Architecture Système

#### Modèle C4 Niveau 1 - Contexte

![Modele C4](niveau1.png)

**Système Central** : EduGuide
**Interactions** :

- Étudiants → Consultation, évaluation, recommandations
- Administrateurs → Gestion, modération, rapports
- API Planifium → Données cours officielles
- CAS UdeM → Authentification

#### Modèle C4 Niveau 2 - Conteneurs

![Modele C4](niveau2.png)

**Conteneurs Principaux** :

- Application Web React
- API Gateway
- Microservices (Auth, Cours, Avis, Recommandations)
- Bases de données (PostgreSQL, Redis)

### Diagramme des Cas d'Utilisation

![Cas D'utilisation](CU.png)

**Acteurs** : Étudiant, Invité, Administrateur
**Cas Principaux** : Consulter cours, Donner avis, Recevoir recommandations, Gérer données

### Modèle de Données

#### Entités Principales

```sql
Utilisateur(id, email_udem, nom, prenom, programme, preferences)
Cours(sigle, titre, description, credits, departement, prerequis)
Avis(id, utilisateur_id, cours_sigle, note, commentaire, statut)
Recommandation(id, utilisateur_id, cours_sigle, score, motifs)
Session(annee, saison, date_debut, date_fin)
```
