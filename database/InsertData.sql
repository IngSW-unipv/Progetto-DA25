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

INSERT INTO Gruppi (nome_gruppo, descrizione, creatore_id, evento_id) VALUES 
('Rock Fans', 'Gruppo di appassionati di musica rock.', 1, 1),
('Cinema Lovers', 'Discutiamo dei film del Festival del Cinema.', 2, 2),
('Jazz Club', 'Amanti del jazz, unitevi!', 3, 3),
('Teatro Moderno', 'Gruppo dedicato al teatro classico e moderno.', 4, 4),
('Arte Contemporanea', 'Per chi ama l\'arte moderna e le installazioni.', 5, 5),
('Cabaret Night', 'Ridiamo insieme con il miglior cabaret!', 2, 6),
('Sport Maniacs', 'Seguiamo e discutiamo le partite di calcio!', 6, 7),
('Pop Lovers', 'Fan della musica pop, uniamoci!', 7, 8),
('Book Addicts', 'Club del libro per la Fiera di Bologna.', 8, 9),
('Classic Music Enthusiasts', 'Per gli amanti della musica classica.', 9, 10);


INSERT INTO Membri (gruppo_id, utente_id, ruolo) VALUES 
(1, 1, 'organizzatore'),
(1, 3, 'membro'),
(1, 5, 'membro'),

(2, 2, 'organizzatore'),
(2, 4, 'membro'),
(2, 6, 'membro'),

(3, 3, 'organizzatore'),
(3, 7, 'membro'),
(3, 8, 'membro'),

(4, 4, 'organizzatore'),
(4, 9, 'membro'),
(4, 10, 'membro'),

(5, 5, 'organizzatore'),
(5, 11, 'membro'),
(5, 12, 'membro'),

(6, 2, 'organizzatore'),
(6, 13, 'membro'),
(6, 1, 'membro'),

(7, 6, 'organizzatore'),
(7, 14, 'membro'),
(7, 15, 'membro'),

(8, 7, 'organizzatore'),
(8, 16, 'membro'),
(8, 3, 'membro'),

(9, 8, 'organizzatore'),
(9, 5, 'membro'),
(9, 10, 'membro'),

(10, 9, 'organizzatore'),
(10, 2, 'membro'),
(10, 4, 'membro');

INSERT INTO Bacheca (gruppo_id, utente_id, messaggio) VALUES
(1, 1, 'Ragazzi, chi viene al concerto rock? Ci troviamo prima per un aperitivo?'),
(1, 3, 'Ottima idea! Dove ci troviamo?'),
(1, 5, 'Io porto qualche amico, ci vediamo lì!'),

(2, 2, 'Il Festival del Cinema sta per iniziare! Quali film volete vedere?'),
(2, 4, 'Io punto ai documentari, sembrano interessanti.'),
(2, 6, 'Qualcuno sa dove comprare i biglietti scontati?'),

(3, 3, 'Serata jazz in arrivo! Chi vuole condividere il taxi?'),
(3, 7, 'Io ci sto! Qualcuno ha già i biglietti?'),
(3, 8, 'Io li ho presi ieri, vi consiglio di affrettarvi!'),

(4, 4, 'Appuntamento alle 19:00 davanti al teatro! Non fate tardi.'),
(4, 9, 'Perfetto, ci sarò!'),
(4, 10, 'Non vedo l’ora, adoro il teatro!'),

(5, 5, 'La mostra d’arte è incredibile, vi consiglio di visitarla con calma.'),
(5, 11, 'C’è qualche opera che ti ha colpito particolarmente?'),
(5, 12, 'Sì, le installazioni luminose sono spettacolari!'),

(6, 2, 'Domani sera cabaret! Chi ha voglia di farsi due risate?'),
(6, 13, 'Io! Mi servono un paio d’ore di svago.'),
(6, 1, 'Ci vediamo direttamente lì?'),

(7, 6, 'Il match di calcio si avvicina! Qualcuno vuole fare una scommessa amichevole?'),
(7, 14, 'Io dico che vinciamo 3-1!'),
(7, 15, 'Vedremo! Comunque sarà una bella partita.'),

(8, 7, 'Concerto pop in arrivo, dobbiamo organizzarci per il viaggio.'),
(8, 16, 'Io posso offrire un passaggio a chi serve.'),
(8, 3, 'Grazie! Ci sentiamo in privato per i dettagli.'),

(9, 8, 'Qualcuno ha già dato un’occhiata ai libri in fiera?'),
(9, 5, 'Sì, la sezione fantasy è stupenda!'),
(9, 10, 'Hanno anche un angolo dedicato ai fumetti, da non perdere!'),

(10, 9, 'Domani sera musica classica, portate i fazzoletti!'),
(10, 2, 'Ahaha, prepariamoci per un’esperienza emozionante.'),
(10, 4, 'Io porto il programma dei concerti, così scegliamo in anticipo.');

