---
title: Analyse des besoins - Exigences
---

# Exigences

## Exigences fonctionnelles

### Gestion des utilisateurs

- [ ] EF1 : L'utilisateur peut créer un compte personnel
- [ ] EF2 : L'utilisateur peut s'authentifier au système
- [ ] EF3 : L'utilisateur peut réinitialiser son mot de passe
- [ ] EF4 : L'administrateur peut gérer les comptes utilisateurs
- [ ] EF5 : L'administrateur peut attribuer des droits spécifiques

### Gestion des ressources

- [ ] EF6 : Le système permet de consulter les ressources disponibles
- [ ] EF7 : L'administrateur peut ajouter une nouvelle ressource
- [ ] EF8 : L'administrateur peut modifier les caractéristiques d'une ressource
- [ ] EF9 : L'administrateur peut supprimer une ressource
- [ ] EF10 : Le système affiche les détails d'une ressource (description, localisation, capacité)

### Réservations

- [ ] EF11 : L'utilisateur authentifié peut réserver une ressource disponible
- [ ] EF12 : Le système vérifie les conflits de réservation automatiquement
- [ ] EF13 : L'utilisateur peut consulter ses réservations futures et passées
- [ ] EF14 : L'utilisateur peut annuler une réservation future
- [ ] EF15 : Le système envoie un courriel de confirmation de réservation
- [ ] EF16 : Le système envoie un rappel avant le début de la réservation

### Recherche et filtrage

- [ ] EF17 : L'utilisateur peut rechercher des ressources par nom
- [ ] EF18 : L'utilisateur peut filtrer les ressources par type
- [ ] EF19 : L'utilisateur peut filtrer les ressources par localisation
- [ ] EF20 : Le système affiche les disponibilités par créneaux horaires

### Administration

- [ ] EF21 : L'administrateur peut consulter toutes les réservations
- [ ] EF22 : L'administrateur peut annuler n'importe quelle réservation
- [ ] EF23 : L'administrateur peut générer des rapports d'utilisation
- [ ] EF24 : Le système envoie des notifications pour les réservations problématiques

## Exigences non fonctionnelles

### Performance

- [ ] ENF1 : Le système doit répondre en moins de 2 secondes pour 95% des requêtes
- [ ] ENF2 : Le système doit supporter jusqu'à 500 utilisateurs simultanés
- [ ] ENF3 : Les recherches doivent retourner des résultats en moins de 1 seconde

### Sécurité

- [ ] ENF4 : Les mots de passe doivent être hashés avec un algorithme sécurisé (bcrypt)
- [ ] ENF5 : Les sessions utilisateur doivent expirer après 30 minutes d'inactivité
- [ ] ENF6 : Protection contre les attaques CSRF et XSS
- [ ] ENF7 : Audit des actions administratives

### Disponibilité et fiabilité

- [ ] ENF8 : Le système doit être accessible 99% du temps pendant les heures d'ouverture (8h-18h)
- [ ] ENF9 : Les sauvegardes automatiques doivent être effectuées quotidiennement
- [ ] ENF10 : Temps de récupération maximal de 4 heures en cas de panne

### Utilisabilité

- [ ] ENF11 : Interface responsive compatible desktop et mobile
- [ ] ENF12 : Conformité WCAG 2.1 niveau AA pour l'accessibilité
- [ ] ENF13 : Documentation utilisateur complète et accessible

### Compatibilité

- [ ] ENF14 : Compatible avec Chrome 90+, Firefox 88+, Safari 14+
- [ ] ENF15 : Support des résolutions d'écran 1280x720 et supérieures

## Priorisation

### Critique (Phase 1 - MVP)

- EF1, EF2, EF6, EF11, EF12, EF13, EF7, EF21
- ENF1, ENF4, ENF8, ENF11

### Haute priorité (Phase 2)

- EF3, EF5, EF8, EF14, EF15, EF17, EF18
- ENF2, ENF5, ENF9, ENF14

### Moyenne priorité (Phase 3)

- EF4, EF9, EF10, EF16, EF19, EF20, EF22
- ENF3, ENF6, ENF12, ENF15

### Faible priorité (Phase 4)

- EF23, EF24
- ENF7, ENF10, ENF13

## Types d'utilisateurs

| Type d'utilisateur          | Description                                  | Exemples de fonctionnalités accessibles                                                                                                |
| --------------------------- | -------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------- |
| Utilisateur invité          | Accès limité, pas d'authentification         | Consultation des ressources disponibles, recherche et filtrage, visualisation des créneaux                                             |
| Utilisateur authentifié     | Compte personnel, fonctions principales      | Réservation de ressources, gestion de ses réservations, historique personnel, profil utilisateur                                       |
| Gestionnaire de département | Droits étendus sur un ensemble de ressources | Gestion des ressources de son département, consultation des réservations de son département, rapports d'utilisation                    |
| Administrateur système      | Droits complets, gestion globale             | Gestion de tous les utilisateurs, gestion de toutes les ressources, supervision du système, génération de rapports, paramètres système |

## Infrastructures

### Environnement de production

- Système d'exploitation : Ubuntu 22.04 LTS
- Base de données : PostgreSQL version 15 avec réplication
- Serveur Web : Nginx + Gunicorn
- Langage de programmation : Python 3.10+
- Framework web : Django 4.2+
- Cache : Redis 6.0+
- File d'attente : Celery avec Redis comme broker

### Environnement de développement

- Conteneurisation : Docker et Docker Compose
- Versioning : Git avec GitHub
- CI/CD : GitHub Actions
- Testing : Pytest avec couverture de code > 80%

### Sécurité

- SSL/TLS : Certificats Let's Encrypt
- Firewall : UFW avec règles restrictives
- Monitoring : Fail2ban pour protection contre les attaques
- Sauvegarde : Sauvegardes automatiques quotidiennes avec rétention 30 jours

### Stockage

- Base de données : PostgreSQL avec sauvegarde continue
- Fichiers statiques : AWS S3 ou équivalent
- Logs : Centralisation avec ELK Stack ou équivalent

### Intégration

- Authentification : Système interne avec possibilité d'intégration LDAP/Active Directory
- Email : Service SMTP externe (SendGrid, Mailgun, ou serveur interne)
- API : REST API avec documentation Swagger/OpenAPI
- Monitoring : Prometheus + Grafana pour les métriques système

## Priorisation

TODO: Identifier les exigences critiques.

## Types d'utilisateurs

> Identifier les différents profils qui interagiront avec le système.

| Type d’utilisateur      | Description                             | Exemples de fonctionnalités accessibles                      |
| ----------------------- | --------------------------------------- | ------------------------------------------------------------ |
| Utilisateur invité      | Accès limité, pas d’authentification    | Consultation des ressources                                  |
| Utilisateur authentifié | Compte personnel, fonctions principales | Réservation, historique                                      |
| Administrateur          | Droits étendus, gestion des ressources  | Création/suppression de ressources, gestion des utilisateurs |

## Infrastructures

> Informations sur l’environnement d’exécution cible, les outils ou plateformes nécessaires.

- Le système sera hébergé sur un serveur Ubuntu 22.04.
- Base de données : PostgreSQL version 15.
- Serveur Web : Nginx + Gunicorn (pour une app Python, par exemple).
- Framework principal : [Django].
