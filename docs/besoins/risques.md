# Analyse des risques

## Identification des risques

### Risque 1 – Indisponibilité des données officielles

**Probabilité** : Moyenne  
**Impact** : Élevé  
**Plan de mitigation** :

- Mise en cache des données Planifium avec rafraîchissement quotidien
- Système de fallback avec données historiques
- Monitoring proactif des APIs externes
- Notifications automatiques en cas d'indisponibilité

### Risque 2 – Biais de sélection dans les avis Discord

**Probabilité** : Élevée  
**Impact** : Élevé  
**Justification** : Seuls les étudiants très satisfaits ou très insatisfaits prennent la peine de donner leur avis sur Discord, créant un biais de polarisation.

**Type de biais identifié** :

- Biais de sélection (sur-représentation des opinions extrêmes)
- Biais d'auto-sélection (volontariat non représentatif)
- Biais de popularité (cours connus sur-représentés)

**Impact concret** :

- Fausse perception de la difficulté réelle des cours
- Décisions d'inscription basées sur des données non représentatives
- Cours peu populaires mais de qualité manquant de visibilité

**Plan de mitigation** :

- Seuil minimal de 5 avis avant affichage (exigence du projet)
- Indicateur de confiance basé sur le nombre d'avis et la variance
- Pondération temporelle (avis récents plus importants)
- Collecte proactive ciblant des profils variés via le bot Discord
- Alertes lorsque l'écart-type dépasse un seuil critique

### Risque 3 – Absence prolongée d'un membre clé

**Probabilité** : Moyenne  
**Impact** : Élevé  
**Plan de mitigation** :

- Documentation technique exhaustive et régulièrement mise à jour
- Répartition claire des responsabilités avec chevauchement
- Révisions de code systématiques et pair programming
- Formation croisée des membres sur les composants critiques

### Risque 4 – Problèmes de performance en période d'inscription

**Probabilité** : Élevée  
**Impact** : Élevé  
**Plan de mitigation** :

- Architecture scalable avec mise à l'échelle automatique
- Cache Redis pour les données fréquemment consultées
- Optimisation des requêtes de base de données
- Tests de charge préalables sur l'environnement de production

### Risque 5 – Problèmes de confidentialité des données

**Probabilité** : Moyenne  
**Impact** : Très élevé  
**Plan de mitigation** :

- Anonymisation stricte des données académiques
- Conformité RGPD/PIPEDA pour la protection des données
- Chiffrement des données sensibles en transit et au repos
- Audit de sécurité régulier et contrôles d'accès stricts

### Risque 6 – Faible adoption de la plateforme par les étudiants

**Probabilité** : Élevée _(augmenté)_  
**Impact** : Moyen  
**Justification** : Les étudiants ont des habitudes établies et peuvent être réticents à adopter un nouvel outil, surtout si la valeur ajoutée n'est pas immédiatement visible.

**Plan de mitigation** :

- Interface "time-to-value" rapide (résultats visibles en < 3 clics)
- Intégration avec les périodes clés (campagne lors des inscriptions)
- Promotion via les associations étudiantes (ADEPS, etc.)
- Fonctionnalité "wow" : estimation charge de travail combinée
- Mesure continue du taux d'adoption et feedback utilisateur

## Modification du processus opérationnel

### Processus actuels modifiés

#### Consultation des informations de cours

**Processus actuel** :

- Consultation fragmentée sur Planifium, forums Discord, bouche-à-oreille
- Difficulté à comparer objectivement plusieurs cours
- Absence de vue consolidée et personnalisée

**Nouveau processus** :

- Plateforme centralisée agrégant toutes les sources
- Tableaux de bord comparatifs avec métriques normalisées
- Recommandations personnalisées selon le profil étudiant

#### Prise de décision pour le choix de cours

**Processus actuel** :

- Décisions basées sur des informations incomplètes
- Forte dépendance aux expériences individuelles
- Difficulté à anticiper la charge de travail réelle

**Nouveau processus** :

- Décisions éclairées par des données agrégées et analysées
- Visualisation des tendances historiques et patterns
- Estimation quantitative de la charge de travail

### Impacts organisationnels

#### Pour les étudiants

- **Avantages** : Meilleure planification académique, réduction de l'incertitude, optimisation du parcours
- **Défis** : Adaptation à un nouvel outil, confiance dans les données fournies

#### Pour l'administration universitaire

- **Avantages** : Réduction des demandes de réorientation, meilleure satisfaction étudiante
- **Défis** : Gestion des attentes, intégration avec les systèmes existants

### Mesures d'accompagnement

1. **Phase de transition** :

   - Période de fonctionnement parallèle avec les méthodes traditionnelles
   - Support technique dédié pendant les premières semaines

2. **Formation et documentation** :

   - Tutoriels vidéo et guides utilisateur
   - Sessions de démonstration en début de session

3. **Collecte de feedback** :

   - Mécanismes intégrés de suggestion et rapport de bug
   - Enquêtes de satisfaction régulières

4. **Amélioration continue** :
   - Mise à jour régulière basée sur les retours utilisateurs
   - Adaptation aux changements des programmes académiques
