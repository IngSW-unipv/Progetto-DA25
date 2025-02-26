package com.togetherticket.servlet;

//Classe che rappresenta un gruppo all'interno del sistema TogetherTicket.

public class Gruppo {
    // Identificativo univoco del gruppo nel database
    private int id;  
    
    // Nome del gruppo creato dagli utenti
    private String nomeGruppo;  

    //Costruttore della classe Gruppo.
    public Gruppo(int id, String nomeGruppo) {
        this.id = id;
        this.nomeGruppo = nomeGruppo;
    }

    // Metodi Getter e Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeGruppo() {
        return nomeGruppo;
    }

    public void setNomeGruppo(String nomeGruppo) {
        this.nomeGruppo = nomeGruppo;
    }
}




