/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nerdbook.classi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabio
 */
public class GruppoFactory
{

    private static GruppoFactory singleton;

    public static GruppoFactory getInstance()
    {
        if (singleton == null)
        {
            singleton = new GruppoFactory();
        }
        return singleton;
    }

 //   private ArrayList<Gruppo> listaGruppi = new ArrayList<>();
    private String connectionString;
    
    
    private GruppoFactory()
    {
    }

    public Gruppo getById(int id)
    {
        try
        {
            Connection conn = DriverManager.getConnection(connectionString, "amm", "admin");            
            String sql = "SELECT * FROM gruppi WHERE id = ?";
            
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setInt(1,id);
            
            ResultSet set = stat.executeQuery();     
            
            if(set.next())
            {
                Gruppo gruppo = new Gruppo();
            
                gruppo.setId(id);
                gruppo.setNome(set.getString("nome"));
                gruppo.setUrlFoto(set.getString("urlFoto"));
                stat.close();
                conn.close();
                return gruppo;
            }
            stat.close();
            conn.close();
            
        }catch(SQLException e)
        {
            Logger.getLogger(UtenteFactory.class.getName()).
            log(Level.SEVERE, null, e);
        }
        
        return null;
        
        /*for (Gruppo g : listaGruppi)
        {
            if (g.getId() == id)
            {
                return g;
            }
        }
        return null;*/
    }

    public ArrayList<Gruppo> getGroupsList(int id)
    {
        ArrayList<Gruppo> listaGruppi = new ArrayList<>();
        try
        {   
            Connection conn = DriverManager.getConnection(connectionString, "amm", "admin");            

            String sql = "SELECT g.* FROM utentigruppi JOIN utenti u ON id_utente=u.id JOIN gruppi g ON id_gruppo=g.id WHERE id_utente = ?";
            
            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setInt(1, id);
            
            ResultSet set = stat.executeQuery();     
            
            while(set.next())
            {
                Gruppo gruppo = new Gruppo();
            
                gruppo.setId(set.getInt("id"));
                gruppo.setNome(set.getString("nome"));
                gruppo.setUrlFoto(set.getString("urlFoto"));
                
                listaGruppi.add(gruppo);
            }
            stat.close();
            conn.close();      
        }catch(SQLException e)
        {
            Logger.getLogger(UtenteFactory.class.getName()).
            log(Level.SEVERE, null, e);
        }
        return listaGruppi;
    }

    /**
     * @return the connectionString
     */
    public String getConnectionString()
    {
        return connectionString;
    }

    /**
     * @param connectionString the connectionString to set
     */
    public void setConnectionString(String connectionString)
    {
        this.connectionString = connectionString;
    }
}
