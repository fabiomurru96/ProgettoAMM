/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nerdbook;

import java.util.ArrayList;

/**
 *
 * @author fabio
 */
public class GruppoFactory {
 
    private static GruppoFactory singleton;
    
    public GruppoFactory getInstance()
    {
        if(singleton == null)
            singleton = new GruppoFactory();
        return singleton;
    }
    
    private ArrayList<Gruppo> listaGruppi = new ArrayList<>();
    
    private GruppoFactory()
    {
        UtenteFactory utenti = UtenteFactory.getInstance();
        
        Gruppo gruppo1 = new Gruppo();
        ArrayList<Utente> users1 = new ArrayList<>();
        users1.add(utenti.getById(0));
        users1.add(utenti.getById(1));
        gruppo1.setId(0);
        gruppo1.setNome("Mongolfieristi");
        gruppo1.setUtenti(users1);
        
        Gruppo gruppo2 = new Gruppo();
        ArrayList<Utente> users2 = new ArrayList<>();
        users2.add(utenti.getById(0));
        gruppo2.setId(0);
        gruppo2.setNome("Ritardatari");
        gruppo2.setUtenti(users2);
        
        listaGruppi.add(gruppo1);
        listaGruppi.add(gruppo2);
    }
    
    public Gruppo getById(int id)
    {
        for(Gruppo g : listaGruppi)
            if(g.getId() == id)
                return g;
        return null;
    }
}
