# Template de site web pour IFT2255

Ce projet est un template pour le rapport à produire pour le cours IFT2255 (Génie logiciel), construit avec [MkDocs](https://www.mkdocs.org/) et le thème [Material for MkDocs](https://squidfunk.github.io/mkdocs-material/).

## Prérequis

Assurez-vous d’avoir les outils suivants installés :

- Python **3.11** ou plus récent
- `pip` (gestionnaire de paquets Python)
- `pipenv` ou équivalent (gestion d’environnement virtuel) 
  - Évite de polluer votre système et les conflits de version.
  - Installez-le avec `pip install pipenv`.

## Pour commencer

### Option 1: Le répertoire n'est pas encore créé

1. Cliquez sur le bouton `Use this template` sur GitHub.
2. Choisissez l'option `Create a new repository`.
3. À la fin des étapes, vous devriez avoir un nouveau répertoire avec le contenu du template.

### Option 2: Le répertoire existe déjà

1. Clonez ce dépôt (optionnel) pour obtenir le template localement 
```bash
git clone git@github.com:udem-diro/template-rapport-ift2255.git
```
2. Copiez les fichiers du template (en local) dans votre répertoire de projet.

> Note : Cette option est utile si vous souhaitez récupérer le contenu du template sans créer un nouveau dépôt (répertoire).

## Installation

> Vous avez maintenant le contenu du template sur votre poste. Il ne reste qu’à installer les dépendances pour commencer à l’utiliser.

1. Activez l'environnement virtuel avec 
```bash
pipenv shell
```
2. Installez les dépendances listées dans `requirements.txt` (à exécuter dans le répertoire du projet) :

```bash
pip install -r requirements.txt
```

## Utilisation

> Avant toute utilisation, assurez-vous que l’environnement virtuel est activé (`pipenv shell`).

### Développement local

Pour lancer un serveur de développement local et visualiser les modifications en temps réel, utilisez :

```bash
mkdocs serve
```

Le site sera accessible à l'adresse [http://127.0.0.1:8000](http://127.0.0.1:8000)

### Construction du site (optionnel)

> Cette étape n’est pas nécessaire pour la publication sur GitHub Pages

Pour construire le site :

```bash
mkdocs build
```

Les fichiers générés seront dans le dossier `site/`.

### Déploiement

Pour déployer automatiquement le site sur GitHub Pages (branche `gh-pages`)

```bash
mkdocs gh-deploy
```

> Cette commande pousse automatiquement le contenu du site sur la branche `gh-pages`. Si la branche n'existe pas, elle est crée automatiquement.

## Structure du projet

- `docs/` : Contient tous les fichiers Markdown du site
- `mkdocs.yml` : Configuration de MkDocs
- `requirements.txt` : Dépendances Python
- `site/` : Site généré (créé lors de la construction) -- *optionnel*

## Personnalisation

1. Modifiez `mkdocs.yml` pour changer la configuration du site
2. Ajoutez/modifiez les fichiers Markdown (`.md`) dans `docs/`
3. Personnalisez le thème en modifiant les paramètres dans `mkdocs.yml`

## Licence

Ce projet est sous licence MIT. Voir le fichier `LICENSE` pour plus de détails.

## Ressources utiles

- Documentation officielle MkDocs
- Thème Material for MkDocs

- --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------






# PlanifiumHelper – Phase 2

Projet du cours **IFT2255 – Génie logiciel** (DIRO, UdeM).

L’objectif du système est d’aider les étudiants à faire des choix de cours éclairés en
s’appuyant sur les données officielles de l’API **Planifium** (cours, prérequis, horaires).
Cette phase se concentre sur le backend (API REST) et couvre :

- la **recherche de cours** ;
- l’**affichage des détails d’un cours** ;
- la **comparaison de plusieurs cours**.

---

## 1. Prérequis

- Java **JDK 19**
- Maven 3.x
- IntelliJ IDEA (recommandé)

---

## 2. Lancer l’application

### Depuis IntelliJ

1. Ouvrir le projet Maven (`pom.xml`).
2. Ouvrir `src/main/java/com/diro/ift2255/Main.java`.
3. Clic droit → **Run 'Main.main()'**.
4. Le serveur démarre sur : `http://localhost:7000`.

---

## 3. Endpoints

Base URL : `http://localhost:7000`

- `GET /ping`  
  → Test simple, retourne `"pong"`.

- `GET /courses-fake`  
  → Liste de cours codée en dur (pour test).

- `GET /courses?sigles=IFT2255,IFT2015`  
  → Recherche par sigles. Utilise l’API Planifium `/api/v1/courses?courses_sigle=...&response_level=min`.

- `GET /courses?name=logiciel`  
  → Recherche par mot-clé dans le nom du cours.

- `GET /courses/IFT2255`  
  → Détails complets du cours IFT2255 (description, prérequis, disponibilités, etc.).

- `GET /compare?sigles=IFT2255,IFT2015`  
  → Comparaison de plusieurs cours. Retourne une liste de `CourseDetails`.

Ces endpoints couvrent les cas d’utilisation de la phase 2 :
**recherche de cours**, **voir les détails**, **comparer des cours**.

---

## 4. Lancer les tests

```bash
mvn test





