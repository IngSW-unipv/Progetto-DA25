package com.togetherticket.servlet;
//Classe che rappresenta un evento nel sistema TogetherTicket. Contiene informazioni come nome, descrizione, data, luogo e prezzo.

public class Evento {
    
    // Attributi dell'evento
    private String nome;        // Nome dell'evento
    private String descrizione; // Descrizione dell'evento
    private String dataEvento;  // Data dell'evento in formato String
    private String luogo;       // Luogo dell'evento
    private double prezzo;      // Prezzo dell'evento

    // Costruttore della classe evento
    public Evento(String nome, String descrizione, String dataEvento, String luogo, double prezzo) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.dataEvento = dataEvento;
        this.luogo = luogo;
        this.prezzo = prezzo;
    }

    // Metodi getter e setter per accedere e modificare gli attributi della classe
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescrizione() { return descrizione; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }

    public String getDataEvento() { return dataEvento; }
    public void setDataEvento(String dataEvento) { this.dataEvento = dataEvento; }

    public String getLuogo() { return luogo; }
    public void setLuogo(String luogo) { this.luogo = luogo; }

    public double getPrezzo() { return prezzo; }
    public void setPrezzo(double prezzo) { this.prezzo = prezzo; }
}
