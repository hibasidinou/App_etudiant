#  App Étudiant — Gestion des étudiants (Android + SQLite)

Application Android minimaliste de gestion des étudiants utilisant une base de données SQLite embarquée. Réalisée dans le cadre d'un TP en développement mobile.

---

## 📸 Screenshots

<img width="373" height="786" alt="image" src="https://github.com/user-attachments/assets/fdedbdfb-62ee-45e1-a958-dd3ce4c3ea34" />


| Écran principal | Ajout d'un étudiant |
|:-:|:-:|

<img width="384" height="786" alt="image" src="https://github.com/user-attachments/assets/6a5abbfb-5e49-4374-bd61-7bbe3aee996f" />


<img width="390" height="806" alt="image" src="https://github.com/user-attachments/assets/bbd245e4-dd61-4c47-ae51-132d65ba76c5" />


| Recherche par ID | Suppression |
|:-:|:-:|
<img width="401" height="793" alt="image" src="https://github.com/user-attachments/assets/3401896a-eb44-451e-b1b6-d7edeea778ae" />

<img width="372" height="772" alt="image" src="https://github.com/user-attachments/assets/442673bf-b85d-4d65-84ae-d3688674bf04" />

<img width="382" height="770" alt="image" src="https://github.com/user-attachments/assets/a182f759-73e0-461a-bc92-551b3b981943" />


---

## 🗂️ Architecture du projet

```
app/
└── src/main/java/projet/fst/ma/app/
    ├── classes/
    │   └── Etudiant.java          # Modèle métier
    ├── service/
    │   └── EtudiantService.java   # Opérations CRUD
    ├── util/
    │   └── DatabaseHelper.java    # SQLiteOpenHelper
    └── MainActivity.java          # Interface utilisateur
```

---

## ⚙️ Fonctionnalités

- **Ajouter** un étudiant (nom + prénom)
- **Chercher** un étudiant par son ID
- **Supprimer** un étudiant par son ID
- Persistance locale via **SQLite** (base `school.db`)
- Validation des champs et messages **Toast** pour chaque action

---

## 🛠️ Stack technique

| Technologie | Détail |
|---|---|
| Langage | Java |
| Base de données | SQLite (embarquée) |
| API minimale | Android API 21 (Lollipop) |
| IDE | Android Studio |
| Architecture | 3 couches : modèle / accès données / présentation |

---

##  Lancer le projet

1. Cloner le dépôt
```bash
git clone https://github.com/ton-username/app-etudiant.git
```
2. Ouvrir dans **Android Studio**
3. Connecter un appareil Android (USB ou Wi-Fi) avec le **mode développeur** activé
4. Lancer avec ▶️ **Run**

---

##  Structure de la base de données

Table : `etudiants`

| Colonne | Type | Contrainte |
|---|---|---|
| `id` | INTEGER | PRIMARY KEY AUTOINCREMENT |
| `nom` | TEXT | NOT NULL |
| `prenom` | TEXT | NOT NULL |

