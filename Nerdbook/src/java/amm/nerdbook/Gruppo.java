/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nerdbook;

import java.util.ArrayList;

/**
 *
 * @author FabioM
 */
public class Gruppo {
    
    private int id;
    private String nome;
    private String urlFoto;
    private ArrayList<Utente> utenti;

    public Gruppo()
    {
        this.id = 0;
        this.nome = "";
        this.urlFoto = "";
        this.utenti = null;
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
     * @return the utenti
     */
    public ArrayList<Utente> getUtenti() {
        return utenti;
    }

    /**
     * @param utenti the utenti to set
     */
    public void setUtenti(ArrayList<Utente> utenti) {
        this.utenti = utenti;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome.substring(0, 1).toUpperCase() + nome.substring(1).toLowerCase();
    }

    /**
     * @return the urlFoto
     */
    public String getUrlFoto() {
        return urlFoto;
    }

    /**
     * @param urlFoto the urlFoto to set
     */
    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

}
