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
    private ArrayList<Utente> utenti;

    public Gruppo()
    {
        this.id = 0;
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

}
