-- Insertion de données dans la table Auteurs
INSERT INTO Auteurs (nom, prenom, biographie)
VALUES
    ('Doe', 'John', 'Auteur renommé avec de nombreuses œuvres publiées.'),
    ('Smith', 'Jane', 'Écrivaine primée et célèbre pour ses romans captivants.'),
    ('Alpha-Oumar', 'Sow', 'Enseignant et auteur');

-- Insertion de données dans la table Genres
INSERT INTO Genres (nom)
VALUES
    ('Roman'),
    ('Science-fiction'),
    ('Thriller');

-- Insertion de données dans la table Livres
INSERT INTO Livres (titre, auteur_id, genre_id, edition, disponibilite)
VALUES
    ('Étranger', 1, 1, 'Édition XYZ', true),
    ('Le Guide du voyageur galactique', 2, 2, 'Édition ABC', true),
    ('Gone Girl', 1, 3, 'Édition 123', false);

-- Insertion de données dans la table Exemplaires
INSERT INTO Exemplaires (livre_id, statut)
VALUES
    (1, 'Disponible'),
    (1, 'Emprunté'),
    (2, 'Disponible'),
    (2, 'Reservé'),
    (3, 'Disponible');

-- Insertion de données dans la table Utilisateurs
INSERT INTO Utilisateurs (nom, prenom, adresse_email, mot_de_passe_hashed, role, active)
VALUES
    ('Dupont', 'Alice', 'alice@example.com', 'hashed_password', 'Utilisateur', true),
    ('Martin', 'Bob', 'bob@example.com', 'hashed_password', 'Bibliothecaire', true);

-- Insertion de données dans la table Emprunts
INSERT INTO Emprunts (utilisateur_id, exemplaire_id, date_emprunt, date_retour_prevue, statut)
VALUES
    (1, 1, '2024-02-01', '2024-02-15', 'En cours'),
    (1, 2, '2024-02-10', '2024-02-25', 'En cours'),
    (2, 3, '2024-02-05', '2024-02-20', 'Terminé');

-- Insertion de données dans la table Reservations
INSERT INTO Reservations (utilisateur_id, livre_id, date_reservation, statut)
VALUES
    (1, 3, '2024-02-15', 'En attente'),
    (2, 1, '2024-02-12', 'Prêt à être récupéré');

-- Insertion de données dans la table Commentaires
INSERT INTO Commentaires (utilisateur_id, livre_id, contenu, date_commentaire, note)
VALUES
    (1, 1, 'Un très bon livre!', '2024-02-10', 5),
    (2, 2, 'Une lecture fascinante.', '2024-02-18', 4);
