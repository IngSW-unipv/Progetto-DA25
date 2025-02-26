package com.togetherticket.servlet;

public class Bacheca {
    private int gruppoId; // Identificativo del gruppo a cui il messaggio appartiene
    private int utenteId; // Identificativo dell'utente che ha scritto il messaggio
    private String username; // Nome utente di chi ha scritto il messaggio
    private String messaggio; // Contenuto del messaggio
    private String dataPubblicazione; // Data e ora di pubblicazione del messaggio

    // Costruttore della classe bacheca
    /*gruppoId Identificativo del gruppo a cui il messaggio è associato.
      utenteId Identificativo dell'utente che ha scritto il messaggio.
      username Nome utente dell'autore del messaggio.
      messaggio Testo del messaggio pubblicato.
      dataPubblicazione Data e ora della pubblicazione del messaggio.*/
    public Bacheca(int gruppoId, int utenteId, String username, String messaggio, String dataPubblicazione) {
        this.gruppoId = gruppoId;
        this.utenteId = utenteId;
        this.username = username;
        this.messaggio = messaggio;
        this.dataPubblicazione = dataPubblicazione;
    }

    // Getters e Setters
   //Restituisce l'ID del gruppo a cui il messaggio appartiene. @return ID del gruppo.
    public int getGruppoId() {
        return gruppoId;
    }

    //Imposta l'ID del gruppo a cui il messaggio appartiene. @param gruppoId Nuovo ID del gruppo.
     
    public void setGruppoId(int gruppoId) {
        this.gruppoId = gruppoId;
    }

    //Restituisce l'ID dell'utente che ha scritto il messaggio. @return ID dell'utente.
    public int getUtenteId() {
        return utenteId;
    }

    //Imposta l'ID dell'utente che ha scritto il messaggio. @param utenteId Nuovo ID dell'utente.
    public void setUtenteId(int utenteId) {
        this.utenteId = utenteId;
    }

    //Restituisce il nome utente dell'autore del messaggio. @return Nome utente.
    public String getUsername() {
        return username;
    }

    //Imposta il nome utente dell'autore del messaggio. @param username Nuovo nome utente.
    public void setUsername(String username) {
        this.username = username;
    }

    //Restituisce il contenuto del messaggio. @return Testo del messaggio.
    public String getMessaggio() {
        return messaggio;
    }

    //Imposta il contenuto del messaggio. @param messaggio Nuovo contenuto del messaggio.
    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    //Restituisce la data e l'ora di pubblicazione del messaggio. @return Data e ora di pubblicazione.
    public String getDataPubblicazione() {
        return dataPubblicazione;
    }

    //Imposta la data e l'ora di pubblicazione del messaggio. @param dataPubblicazione Nuova data e ora di pubblicazione.
    public void setDataPubblicazione(String dataPubblicazione) {
        this.dataPubblicazione = dataPubblicazione;
    }
}


