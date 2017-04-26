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
public class UtenteFactory {
    
    private static UtenteFactory singleton;
    
    public static UtenteFactory getInstance()
    {
        if(singleton == null)
        {
            singleton = new UtenteFactory();
        }        
        return singleton;
    }
    
    private ArrayList<Utente> listaUtenti = new ArrayList<>();
    
    private UtenteFactory()
    {
        //Mario rossi
        Utente user1 = new Utente();
        user1.setId(0);
        user1.setNome("Mario");
        user1.setCognome("Rossi");
        user1.setUsername("m.rossi1980");
        user1.setPassword("Hello123");
        user1.setUrlFoto("img/propic.png");
        user1.setFrase("It's-a me Mario");
        user1.setDataDiNascita("1/1/1980");
        
        //Pinco pallino
        Utente user2 = new Utente();
        user2.setId(1);
        user2.setNome("Pinco");
        user2.setCognome("Pallino");
        user2.setUsername("p.pallino1992");
        user2.setPassword("Punto111");
        user2.setUrlFoto("img/pincopallino.png");
        user2.setFrase("Non so chi sono");
        user2.setDataDiNascita("1/1/1992");
        
        listaUtenti.add(user1);
        listaUtenti.add(user2);
    }
    
    public Utente getUtendeById(int id)
    {
        for(Utente u : listaUtenti)
            if(u.getId() == id)
                return u;
        
        return null;
    }
    
}
