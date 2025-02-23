package com.togetherticket.servlet;

public class Bacheca {
    private int gruppoId;
    private int utenteId;
    private String username; // Aggiunto il nome utente
    private String messaggio;
    private String dataPubblicazione;

    // Costruttore aggiornato
    public Bacheca(int gruppoId, int utenteId, String username, String messaggio, String dataPubblicazione) {
        this.gruppoId = gruppoId;
        this.utenteId = utenteId;
        this.username = username;
        this.messaggio = messaggio;
        this.dataPubblicazione = dataPubblicazione;
    }

    // Getters e Setters
    public int getGruppoId() {
        return gruppoId;
    }

    public void setGruppoId(int gruppoId) {
        this.gruppoId = gruppoId;
    }

    public int getUtenteId() {
        return utenteId;
    }

    public void setUtenteId(int utenteId) {
        this.utenteId = utenteId;
    }

    public String getUsername() { // Getter per username
        return username;
    }

    public void setUsername(String username) { // Setter per username
        this.username = username;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public String getDataPubblicazione() {
        return dataPubblicazione;
    }

    public void setDataPubblicazione(String dataPubblicazione) {
        this.dataPubblicazione = dataPubblicazione;
    }
}


