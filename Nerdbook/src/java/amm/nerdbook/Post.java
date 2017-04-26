/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nerdbook;

/**
 *
 * @author FabioM
 */
public class Post {
    
    public enum Type
    {
        NONE, LINK, IMAGE
    }
    
    private int id;
    private Utente utente;
    private String testo;
    private Type tipoAllegato;

    public Post()
    {
        this.id = 0;
        this.utente = null;
        this.testo = "";
        this.tipoAllegato = Type.NONE;
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the utente
     */
    public Utente getUtente() {
        return utente;
    }

    /**
     * @param utente the utente to set
     */
    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    /**
     * @return the testo
     */
    public String getTesto() {
        return testo;
    }

    /**
     * @param testo the testo to set
     */
    public void setTesto(String testo) {
        this.testo = testo;
    }

    /**
     * @return the tipoAllegato
     */
    public Type getTipoAllegato() {
        return tipoAllegato;
    }

    /**
     * @param tipoAllegato the tipoAllegato to set
     */
    public void setTipoAllegato(Type tipoAllegato) {
        this.tipoAllegato = tipoAllegato;
    }
    
}
