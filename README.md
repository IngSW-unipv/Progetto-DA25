# TogetherTicket

**TogetherTicket** è un'applicazione web che consente agli utenti di acquistare biglietti per eventi, creare o unirsi a gruppi e comunicare tramite una bacheca condivisa.

## Tecnologie Utilizzate

![Frontend](https://img.shields.io/badge/Frontend-HTML-E34F26?style=for-the-badge&logoColor=white)  ![Java](https://img.shields.io/badge/Backend-Java-007396?style=for-the-badge&logo=java&logoColor=white)   ![MySQL](https://img.shields.io/badge/Database-MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)  ![Eclipse](https://img.shields.io/badge/IDE-Eclipse-2C2255?style=for-the-badge&logo=eclipseide&logoColor=white)  



- **HTML/CSS**: Frontend per l'interfaccia utente.
- **Java**: Backend per la gestione delle operazioni, come l'autenticazione, la gestione degli eventi e dei gruppi.
- **MySQL**: Database per la memorizzazione di utenti, eventi, gruppi e chat.
- **Eclipse**: IDE utilizzato per lo sviluppo del progetto.

## Prerequisiti

Per eseguire questo progetto sul proprio dispositivo, è necessario avere:

- **Git**: Per usare il comando git nel prompt dei comandi. Puoi scaricarlo [qui](https://git-scm.com/downloads/win)
- **Java Development Kit (JDK) 11 o superiore**: Assicurati di avere Java installato. Puoi scaricarlo da [qui](https://www.oracle.com/java/technologies/downloads/#jdk23-windows).
- **MySQL**: Per la gestione del database. Puoi scaricarlo da [qui](https://dev.mysql.com/downloads/installer/).
- **Eclipse IDE**: Un IDE per lo sviluppo Java. Puoi scaricarlo da [qui](https://www.eclipse.org/downloads/packages/)

## Configurazione del progetto

1. **Clona il repository**: Nel prompt dei comandi digita:

   ```bash
   git clone https://github.com/IngSW-unipv/Progetto-DA25.git

3. **Configura il database**:
   - Si eseguano in ordine i due script sql presenti nella cartella Database, prima `CreaDatabase.sql` e poi `InsertData.sql`. Questi file contengono le tabelle necessarie per la gestione degli utenti, eventi, gruppi e bacheca.

4. **Importa il progetto in Eclipse**:
   - Apri Eclipse e seleziona `File > Import > Existing Maven Projects`.
   - Seleziona la cartella del progetto `TogetherTicket` che hai clonato.
   - Eclipse importerà automaticamente il progetto e le dipendenze tramite Maven.
   - Nel caso avesse un nome differente, rinominare il progetto su eclipse come **TogetherTicket**

5. **Configurazione del server Apache Tomcat**:
   - Se non hai già Apache Tomcat configurato in Eclipse, vai su `Window > Preferences > Server > Runtime Environments` e aggiungi un server Tomcat.
   - Se non e' presenta scaricalo [qui](https://tomcat.apache.org/download-11.cgi).
  
6. **Associa il runtime al tuo progetto**:
   -Clicca con il tasto destro sul progetto e seleziona Properties.
   -Vai su Target Runtime.
   -Seleziona il runtime di Apache Tomcat appena aggiunto.
   -Clicca su Apply and Close.

6. **Avvio dell'applicazione**:
   - Avvia il server Tomcat direttamente da Eclipse cliccando su `Run as > Run on Server`
   - Accedi all'applicazione nel browser visitando `http://localhost:8080/TogetherTicket`.

---

### Dettagli principali delle cartelle:

- **controller**: Contiene le servlet Java che gestiscono le richieste HTTP e le risposte.
- **model**: Contiene le classi modello, come `Evento`, `User` e `Group`, che rappresentano le entità nel database.
- **util**: Include classi utili per la gestione della connessione al database e altre operazioni generali.
- **webapp**: Contiene tutti i file web, come le pagine HTML, i fogli di stile CSS e gli script JavaScript.

---

## Come utilizzare TogetherTicket

### Registrazione e login

- Vai alla pagina di **login** per accedere al sistema se hai già un account.
- Se sei un nuovo utente, vai alla pagina di **registrazione** per creare un nuovo account. Una volta registrato, sarai automaticamente loggato.

### Visualizzazione eventi

- Dopo il login, la homepage mostrerà una lista di eventi disponibili, ciascuno con i dettagli (data, luogo, descrizione e prezzo) e, se desideri, puoi acquistare un biglietto.

### Creazione e gestione gruppi

- Ogni evento ha la possibilità di creare un gruppo dopo l'acquisto del relativo biglietto
- Dopo aver creato un gruppo, dovrai unirti allo stesso o eventualmente unirti a un gruppo esistente

### Bacheca del gruppo

- Ogni gruppo creato ha una **bacheca** in cui i membri possono fissare dei messaggi particolarmente importanti.



