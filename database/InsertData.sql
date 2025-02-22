USE TT;

INSERT INTO Utenti (id, username, email, password, nome, cognome, ruolo) VALUES
(1, 'marioRossi', 'mario.rossi@email.com', 'password123', 'Mario', 'Rossi', 'utente'),
(2, 'annaBianchi', 'anna.bianchi@email.com', 'securepass', 'Anna', 'Bianchi', 'utente'),
(3, 'lucaVerdi', 'luca.verdi@email.com', 'mypassword', 'Luca', 'Verdi', 'utente'),
(4, 'saraNeri', 'sara.neri@email.com', 'sara123', 'Sara', 'Neri', 'utente'),
(5, 'giovanniBlu', 'giovanni.blu@email.com', 'bluPass', 'Giovanni', 'Blu', 'utente'),
(6, 'elenaV', 'elena.v@email.com', 'elenapass', 'Elena', 'Verdi', 'utente'),
(7, 'alessioG', 'alessio.g@email.com', 'giordano45', 'Alessio', 'Giordano', 'utente'),
(8, 'francescaP', 'francesca.p@email.com', 'franci1987', 'Francesca', 'Palmieri', 'utente'),
(9, 'matteoT', 'matteo.t@email.com', 'tommypass', 'Matteo', 'Tosti', 'utente'),
(10, 'giuliaS', 'giulia.s@email.com', 'giuliaPass', 'Giulia', 'Santini', 'utente'),
(11, 'riccardoM', 'riccardo.m@email.com', 'rickPass', 'Riccardo', 'Mariani', 'utente'),
(12, 'valentinaD', 'valentina.d@email.com', 'vale123', 'Valentina', 'De Luca', 'utente'),
(13, 'emanueleF', 'emanuele.f@email.com', 'ema2025', 'Emanuele', 'Ferrari', 'utente'),
(14, 'fede', 'fede.degio@email.com', 'fede', 'Federico', 'De Giovanni', 'admin'),
(15, 'gloria', 'gloria.go@email.com', 'gloria', 'Gloria', 'Gorelli', 'admin'),
(16, 'diane', 'diane@email.com', 'diane', 'Diane', 'Ange', 'admin');


-- Inserimento eventi (gli organizzatori sono utenti e non amministratori)
INSERT INTO Eventi (nome, descrizione, data_evento, luogo, organizzatore_id, prezzo) VALUES
('Concerto Rock', 'Una serata di grande musica rock!', '2025-03-15 20:00:00', 'Milano Arena', 1, 100),
('Festival del Cinema', 'Proiezioni di film indipendenti.', '2025-04-10 18:30:00', 'Roma Cineplex', 2, 12),
('Concerto Jazz', 'Serata di musica jazz con artisti internazionali.', '2025-03-20 21:00:00', 'Blue Note, Milano', 3, 85),
('Spettacolo Teatrale', 'Dramma classico rivisitato in chiave moderna.', '2025-04-05 19:30:00', 'Teatro Verdi, Torino', 4, 45),
('Mostra d\'Arte Moderna', 'Esposizione di opere contemporanee e installazioni.', '2025-05-01 10:00:00', 'Galleria d\'Arte, Firenze', 5, 30),
('Serata di Cabaret', 'Performance di cabarettisti e comici emergenti.', '2025-03-28 22:00:00', 'Teatro Splendido, Napoli', 2, 60),
('Evento Sportivo', 'Partita di calcio amichevole tra squadre locali.', '2025-06-15 16:00:00', 'Stadio Olimpico, Roma', 6, 20),
('Concerto Pop', 'Serata pop con i migliori artisti del momento.', '2025-07-20 20:30:00', 'Stadio San Siro, Milano', 7, 90),
('Fiera del Libro', 'Esposizione e vendita di libri di ogni genere.', '2025-08-10 09:00:00', 'Fiera di Bologna, Bologna', 8, 15),
('Festival della Musica Classica', 'Concerti e masterclass di musica classica.', '2025-09-01 19:00:00', 'Auditorium Parco della Musica, Roma', 9, 70),
('Mostra di Fotografia', 'Esposizione fotografica di talenti emergenti.', '2025-05-20 10:00:00', 'Palazzo Reale, Torino', 10, 25),
('Evento di Cucina', 'Dimostrazioni di cucina con chef rinomati.', '2025-06-05 12:00:00', 'Hotel Excelsior, Napoli', 11, 50),
('Seminario Tecnologico', 'Conferenze e workshop su nuove tecnologie.', '2025-04-20 09:30:00', 'Centro Congressi, Firenze', 12, 40),
('Car Show', 'Esposizione di auto d’epoca e moderne.', '2025-07-15 14:00:00', 'Parco Espositivo, Verona', 13, 30);