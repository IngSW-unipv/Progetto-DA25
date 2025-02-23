USE TTicket;

INSERT INTO Utenti (username, email, password, nome, cognome) VALUES
('marioRossi', 'mario.rossi@email.com', 'password123', 'Mario', 'Rossi'),
('annaBianchi', 'anna.bianchi@email.com', 'securepass', 'Anna', 'Bianchi'),
('lucaVerdi', 'luca.verdi@email.com', 'mypassword', 'Luca', 'Verdi');

INSERT INTO Eventi (nome, descrizione, data_evento, luogo, organizzatore_id) VALUES
('Concerto Rock', 'Una serata di grande musica rock!', '2025-03-15 20:00:00', 'Milano Arena', 1),
('Festival del Cinema', 'Proiezioni di film indipendenti.', '2025-04-10 18:30:00', 'Roma Cineplex', 2);

INSERT INTO Gruppi (nome_gruppo, descrizione, creatore_id) VALUES
('Amanti del Rock', 'Un gruppo per chi ama la musica rock.', 1),
('Cinefili Anonimi', 'Appassionati di cinema e festival.', 2);

INSERT INTO Membri (gruppo_id, utente_id, ruolo) VALUES
(1, 1, 'organizzatore'),
(1, 2, 'membro'),
(2, 2, 'organizzatore'),
(2, 3, 'membro');

INSERT INTO Biglietti (utente_id, evento_id, tipo, stato) VALUES
(1, 1, 'VIP', 'acquistato'),
(2, 1, 'generale', 'acquistato'),
(3, 2, 'VIP', 'acquistato');
