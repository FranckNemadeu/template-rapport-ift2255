---
title: Analyse des besoins - Présentation générale
---

# Présentation du projet

Concevoir une plateforme web qui aide les étudiants à mieux choisir et comparer leurs cours en agrégeant (1) les données académiques officielles (Plans de cours, groupes, horaires) depuis Planifium, et (2) des indicateurs issus de Discord (avis/retours) fournis par export/flux. L’administrateur peut déclencher et surveiller les synchronisations.

La plateforme ne modère pas Discord et n’extrait pas activement des messages : elle se contente de consommer des données mises à disposition (export, webhook, connecteur).

Bénéfices clés.

Étudiant : recherche/filtrage rapides, comparaison de cours, visibilité sur des tendances issues de retours étudiants.

Admin : pilotage des synchronisations, gestion des correspondances cours ↔ canaux Discord, suivi de la santé des intégrations.

## Méthodologie pour la cueillette des données

Notre plateforme récupère d’abord les données officielles (cours, groupes, horaires) depuis Planifium. Concrètement, on interroge régulièrement son API en lecture seule pour prendre les nouveautés ou mises à jour, puis on enregistre ces informations dans notre base. Ce sont les données “de référence” que l’on affiche aux étudiants.

Pour Discord, on ne collecte pas nous-mêmes et on ne modère pas. On reçoit périodiquement un export/flux autorisé (par exemple un fichier ou un webhook) contenant des indicateurs utiles (ex. volume de messages lié à un cours). On relie ensuite ces informations au bon cours à l’aide d’une table de correspondance (cours ↔ canal), sans toucher au serveur Discord lui-même.

À la réception, on fait un petit nettoyage : vérifier que le format est bon, enlever ce qui est inutile, harmoniser les dates, puis stocker le tout. On en garde le strict nécessaire pour l’usage prévu (ex. identifiant du cours, titre, horaire, et seulement des indicateurs côté Discord). Enfin, l’interface est mise à jour automatiquement : la recherche et la comparaison de cours se basent toujours sur les dernières données disponibles.

Côté confidentialité (Loi 25), on applique des règles simples : minimiser les renseignements personnels, sécuriser les échanges (connexion chiffrée) et supprimer ce qui n’a plus d’utilité. L’idée est de rester sobre : des données fiables, reçues de sources autorisées, utilisées uniquement pour aider les étudiants à choisir leurs cours.

## Description du domaine

Sources de vérité

Planifium (officiel) : cours, groupes, horaires, programmes → lecture seule.

Discord (externe) : avis/retours étudiants via flux/export/webhook fournis → consommation, aucune modération côté plateforme.

Ingestion & mise à jour

Jobs d’ingestion (planifiés ou manuels) récupèrent les données des sources.

Contrôles d’intégrité (schémas, ID stables) et journalisation (traçabilité).

Traitements

Normalisation (formats/encodage), mapping cours ↔ canaux Discord,

Indexation pour la recherche/filtrage,

Agrégations (ex. compte de messages, tendances par cours).

Exposition

Tableau de bord étudiant : recherche, comparaison de cours.

Vue admin : suivi des synchronisations, gestion des correspondances.

Sécurité & accès

SSO UdeM (si applicable) pour l’authentification,

RBAC (Étudiant / Admin), chiffrement en transit et au repos.

### Fonctionnement

Ingestion Planifium (pull API) : récupération des entités académiques (cours, groupes, horaires). Contrôles d’intégrité (schémas/ID), horodatage, logs.

Consommation Discord (push via export/webhook/connector) : réception périodique d’un jeu de données fourni par Discord (ou un connecteur autorisé). Aucune modération côté plateforme.

Normalisation & stockage : harmonisation formats/encodage (UTF-8), persistance (BD) et indexation (moteur de recherche) ; calcul d’agrégats (ex. nb de messages par cours, tendances simples).

Exposition :

Front Étudiant : recherche, filtres (programme, prof, plage horaire), comparateur de cours, fiches de cours (données Planifium + synthèses Discord).

Front Admin : déclenchement manuel/planifié des synchronisations, surveillance (journaux, taux d’erreurs), mapping cours↔canal, alertes.

Sécurité : authentification (SSO si applicable), RBAC (Étudiant/Admin), chiffrement TLS, contrôle d’accès aux vues/exports.

Observabilité & résilience : métriques (latence/fraîcheur/erreurs), retries avec backoff, dégradation gracieuse (affichage des données Planifium si Discord indisponible).

### Acteurs

| Acteur             | Rôle                                                | Type                     |
| ------------------ | --------------------------------------------------- | ------------------------ |
| **Étudiant**       | Recherche/compare des cours, consulte des synthèses | **Principal**            |
| **Administrateur** | Lance/suit les synchronisations, gère le mapping    | **Principal**            |
| **API Planifium**  | Fournit les données académiques officielles         | **Secondaire (externe)** |
| **Discord**        | Fournit les messages/avis (via export/flux)         | **Secondaire (externe)** |

### Dépendances

Externes : API Planifium (versioning, quotas), Discord (exports/webhooks, rate limits), SSO UdeM.

Internes : base de données (transactions, rétention), moteur d’index (recherche), jobs/planificateur, observabilité (logs, métriques, alertes), stockage d’objets (si fichiers d’export).

## Hypothèses et contraintes

Vérité des données : Planifium est la source officielle pour tout ce qui est académique.

Accès Discord : l’équipe-projet dispose d’un moyen autorisé d’obtenir les données (export, webhook, connecteur) — pas de scraping.

Périmètre : la plateforme ne modère pas Discord ; elle affiche des synthèses/indicateurs et permet le filtrage.

Identité : authentification via SSO UdeM ; pas d’auto-inscription locale.

PII minimales : les identifiants étudiants sont pseudonymisés si non indispensables.

Mise à jour : « quasi-temps réel » non requis ; une latence Δ ≤ 5–15 min est acceptable pour les synchronisations.

Volumes (à confirmer) :

Étudiants actifs : ~N (ex. 1–5k),

Cours par session : ~X, groupes : ~Y,

Messages importés par session : ~Z k.

Contraintes techniques : JSON/UTF-8, horodatage UTC, déploiement conteneurisé.

Conformité : respect Loi 25 (minimisation, sécurité, droits d’accès/effacement), conservation N sessions avec purge planifiée.
