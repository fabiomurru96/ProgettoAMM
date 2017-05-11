/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nerdbook.classi;

import java.io.Serializable;

/**
 *
 * @author FabioM
 */
public class Post implements Serializable
{

    public static enum Type
    {
        NONE, LINK, IMAGE
    }

    private int id;
    private Utente autore;
    private Utente utente;
    private Gruppo gruppo;
    private String testo;
    private Type tipoAllegato;
    private String allegato;

    public Post()
    {
        this.id = 0;
        this.autore = null;
        this.utente = null;
        this.gruppo = null;
        this.testo = "";
        this.tipoAllegato = Type.NONE;
        this.allegato = "";
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * @return the utente
     */
    public Utente getUtente()
    {
        return utente;
    }

    /**
     * @param utente the utente to set
     */
    public void setUtente(Utente utente)
    {
        if (this.gruppo == null)
        {
            this.utente = utente;
        }
    }

    /**
     * @return the testo
     */
    public String getTesto()
    {
        return testo;
    }

    /**
     * @param testo the testo to set
     */
    public void setTesto(String testo)
    {
        this.testo = testo;
    }

    /**
     * @return the tipoAllegato
     */
    public Type getTipoAllegato()
    {
        return tipoAllegato;
    }

    /**
     * @param tipoAllegato the tipoAllegato to set
     */
    public void setTipoAllegato(Type tipoAllegato)
    {
        this.tipoAllegato = tipoAllegato;
    }

    /**
     * @return the allegato
     */
    public String getAllegato()
    {
        return allegato;
    }

    /**
     * @param allegato the allegato to set
     */
    public void setAllegato(String allegato)
    {
        this.allegato = allegato;
    }

    /**
     * @return the gruppo
     */
    public Gruppo getGruppo()
    {
        return gruppo;
    }

    /**
     * @param gruppo the gruppo to set
     */
    public void setGruppo(Gruppo gruppo)
    {
        if (this.utente == null)
        {
            this.gruppo = gruppo;
        }
    }

    /**
     * @return the autore
     */
    public Utente getAutore()
    {
        return autore;
    }

    /**
     * @param autore the autore to set
     */
    public void setAutore(Utente autore)
    {
        this.autore = autore;
    }

}
