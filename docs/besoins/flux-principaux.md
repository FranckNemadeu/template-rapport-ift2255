# Diagrammes

![Diagramme d'activité](activite.jpg)

## Description des flux complexes

### Flux 1 : Consultation des cours et des avis

**Objectif** : Permettre à l'utilisateur de consulter les informations des cours et leurs avis selon les disponibilités

**Séquences principales** :

1. **Accès initial** : L'utilisateur arrive sur la plateforme
2. **Vérification authentification** : Redirection vers le flux invité ou étudiant
3. **Recherche de cours** : Consultation du catalogue de cours
4. **Sélection et consultation** : Affichage des détails du cours sélectionné
5. **Vérification disponibilité avis** : Contrôle du seuil minimal (n ≥ 5 avis)
6. **Consultation conditionnelle** : Affichage des avis si disponibles, message d'indisponibilité sinon
7. **Filtrage optionnel** : Application de filtres sur les avis consultés

**Points de décision** :

- Authentification de l'utilisateur
- Disponibilité suffisante d'avis pour consultation
- Demande de filtrage des avis affichés
- Retour au menu principal ou nouvelle action

### Flux 2 : Personnalisation du profil étudiant

**Objectif** : Permettre à l'étudiant authentifié de configurer et sauvegarder ses préférences

**Séquences principales** :

1. **Accès personnalisation** : Navigation vers la section profil
2. **Modification préférences** : Configuration des paramètres personnels
3. **Tentative sauvegarde** : Enregistrement des modifications
4. **Vérification succès** : Confirmation ou échec de la sauvegarde
5. **Boucle correction** : Retour à la modification en cas d'échec
6. **Navigation post-sauvegarde** : Retour au menu ou poursuite des modifications

**Points de décision** :

- Succès ou échec de la sauvegarde
- Poursuite ou arrêt des modifications
- Retour au menu principal

### Flux 3 : Comparaison de cours

**Objectif** : Permettre la comparaison de plusieurs cours et l'analyse de leur charge de travail

**Séquences principales** :

1. **Initiation comparaison** : Accès à la fonctionnalité de comparaison
2. **Sélection multiple** : Choix d'au moins deux cours à comparer
3. **Affichage comparatif** : Présentation côte à côte des cours
4. **Calcul métriques** : Détermination de la charge de travail totale
5. **Navigation post-comparaison** : Retour au menu ou nouvelle comparaison

**Points de décision** :

- Retour au menu principal après comparaison
- Lancement d'une nouvelle comparaison
- Poursuite de l'analyse des cours comparés

### Flux 4 : Navigation et gestion des sessions

**Objectif** : Assurer une navigation fluide et cohérente entre les différentes fonctionnalités

**Séquences principales** :

1. **Accès menu** : Présentation des options principales
2. **Sélection fonctionnalité** : Choix d'une action spécifique
3. **Exécution tâche** : Réalisation de la fonctionnalité sélectionnée
4. **Évaluation continuation** : Décision de poursuite ou changement
5. **Boucle navigation** : Retour au menu ou nouvelle action similaire
6. **Gestion fin session** : Fermeture propre de l'application

**Points de décision** :

- Choix entre retour au menu et action similaire
- Décision de fermeture de l'application
- Persistance du contexte utilisateur
