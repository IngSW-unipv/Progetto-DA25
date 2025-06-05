CREATE DATABASE TT;
USE TT;

CREATE TABLE Utenti (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ruolo VARCHAR(50)
);


CREATE TABLE Eventi (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descrizione TEXT,
    data_evento DATETIME NOT NULL,
    luogo VARCHAR(100),
    organizzatore_id INT,
    prezzo DECIMAL(10, 2),
    FOREIGN KEY (organizzatore_id) REFERENCES Utenti(id)
);


CREATE TABLE Gruppi (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_gruppo VARCHAR(100) NOT NULL,
    descrizione TEXT,
    creatore_id INT,
    evento_id INT, 
    FOREIGN KEY (creatore_id) REFERENCES Utenti(id),
    FOREIGN KEY (evento_id) REFERENCES Eventi(id)  -- Collegamento con Eventi
);


CREATE TABLE Membri (
    id INT AUTO_INCREMENT PRIMARY KEY,
    gruppo_id INT,
    utente_id INT,
    ruolo VARCHAR(50),  -- Ad esempio "organizzatore", "membro"
    FOREIGN KEY (gruppo_id) REFERENCES Gruppi(id),
    FOREIGN KEY (utente_id) REFERENCES Utenti(id)
);

CREATE TABLE Bacheca (
    id INT AUTO_INCREMENT PRIMARY KEY,
    gruppo_id INT,
    utente_id INT,
    messaggio TEXT NOT NULL,
    data_pubblicazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (gruppo_id) REFERENCES Gruppi(id),
    FOREIGN KEY (utente_id) REFERENCES Utenti(id)
);

