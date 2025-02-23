package com.togetherticket.servlet;

public class Gruppo {
    private int id;
    private String nomeGruppo;

    // Costruttore
    public Gruppo(int id, String nomeGruppo) {
        this.id = id;
        this.nomeGruppo = nomeGruppo;
    }

    // Getter e Setter
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




