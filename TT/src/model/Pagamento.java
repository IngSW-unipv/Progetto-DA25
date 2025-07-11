package model;

public class Pagamento {
    private int utenteId;
    private int eventoId;
    private double importo; // se serve
    private String stato;

    public Pagamento(int utenteId, int eventoId, double importo) {
        this.utenteId = utenteId;
        this.eventoId = eventoId;
        this.importo = importo;
        this.stato = "NON_EFFETTUATO";  // imposta lo stato del pagamento iniziale a "NON EFFETUATO"
    }

    // getter e setter
    public int getUtenteId() { 
        return utenteId; 
    }
    public int getEventoId() { 
        return eventoId; 
    }
    
    public double getImporto() { 
        return importo; 
    }
    
    public String getStato() { 
        return stato; 
    }
    
    public void setStato(String stato) { 
        this.stato = stato; // modifica lo stato ad esempio dopo un pagamento riuscito
    }
}
