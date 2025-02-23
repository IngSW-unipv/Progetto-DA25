package com.togetherticket.servlet;

public class Evento {
    private String nome;
    private String descrizione;
    private String dataEvento;
    private String luogo;
    private double prezzo;  

    // Costruttore
    public Evento(String nome, String descrizione, String dataEvento, String luogo, double prezzo) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.dataEvento = dataEvento;
        this.luogo = luogo;
        this.prezzo = prezzo;
    }

    // Getter e setter
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
