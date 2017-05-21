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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    //private ArrayList<Utente> listaUtenti = new ArrayList<>();
    private String connectionString;
    
    
    private UtenteFactory()
    {/*
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
       
        user2.setDataDiNascita("1/1/1992");
        
        
        listaUtenti.add(user1);
        listaUtenti.add(user2);*/
    }
    
    public Utente getById(int id)
    {
        try
        {
            Connection conn = DriverManager.getConnection(connectionString, "amm", "admin");            
            String sql = "SELECT * FROM utenti WHERE id = ?";
            
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setInt(1,id);
            
            ResultSet set = stat.executeQuery();     
            
            if(set.next())
            {
                Utente utente = new Utente();
            
                utente.setId(id);
                utente.setNome(set.getString("nome"));
                utente.setCognome(set.getString("cognome"));
                utente.setFrase(set.getString("frase"));
                
                Date data = set.getDate("dataDiNascita");
                utente.setDataDiNascita(new SimpleDateFormat("dd/mm/yyyy").format(data));
                
                utente.setPassword(set.getString("password"));
                utente.setUrlFoto(set.getString("urlFoto"));
                utente.setUsername(set.getString("username"));
                stat.close();
                conn.close();
                return utente;
            }
            stat.close();
            conn.close();
            
        }catch(SQLException e)
        {
            Logger.getLogger(UtenteFactory.class.getName()).
            log(Level.SEVERE, null, e);
        }
        
        return null;
        
       /* for(Utente u : listaUtenti)
            if(u.getId() == id)
              return u;
        
        return null;*/
    }
    
    public int getIdByUserAndPassword(String usn, String psw)
    {
        try
        {
            Connection conn = DriverManager.getConnection(connectionString, "amm", "admin");            

            String sql = "SELECT id FROM utenti WHERE username = ? AND password = ?";
            
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1,usn);
            stat.setString(2,psw);
            
            ResultSet set = stat.executeQuery();     
            
            if(set.next())
            {
                int id = set.getInt("id");
                stat.close();
                conn.close();
                return id;
            }
            stat.close();
            conn.close();      
        }catch(SQLException e)
        {
            Logger.getLogger(UtenteFactory.class.getName()).
            log(Level.SEVERE, null, e);
        }
        
        return -1;
        
        /*for(Utente u : listaUtenti)
            if(u.getUsername().equals(usn) && u.getPassword().equals(psw))
                return u.getId();
        return -1;*/
    }
    
    public ArrayList<Utente> getUsersList()
    {
        ArrayList<Utente> listaUtenti = new ArrayList<>();
        try
        {   
            Connection conn = DriverManager.getConnection(connectionString, "amm", "admin");            

            String sql = "SELECT * FROM utenti";
            
            PreparedStatement stat = conn.prepareStatement(sql);

            ResultSet set = stat.executeQuery();     
            
            while(set.next())
            {
                Utente utente = new Utente();
            
                utente.setId(set.getInt("id"));
                utente.setNome(set.getString("nome"));
                utente.setCognome(set.getString("cognome"));
                utente.setFrase(set.getString("frase"));
                
                Date data = set.getDate("dataDiNascita");
                utente.setDataDiNascita(new SimpleDateFormat("dd/mm/yyyy").format(data));
                
                utente.setPassword(set.getString("password"));
                utente.setUrlFoto(set.getString("urlFoto"));
                utente.setUsername(set.getString("username"));
                listaUtenti.add(utente);
            }
            stat.close();
            conn.close();      
        }catch(SQLException e)
        {
            Logger.getLogger(UtenteFactory.class.getName()).
            log(Level.SEVERE, null, e);
        }
        return listaUtenti;
    }

    public void updateUser(Utente u)
    {
        try
        {
            Connection conn = DriverManager.getConnection(connectionString, "amm", "admin");            
            String sql = "UPDATE utenti SET nome = ?, cognome = ?, urlFoto = ?, frase = ?, dataDiNascita= ?, password = ?  WHERE id = ?";
            
            PreparedStatement stat = conn.prepareStatement(sql);
            
            
            stat.setString(1, u.getNome());
            stat.setString(2, u.getCognome());
            stat.setString(3, u.getUrlFoto());
            stat.setString(4, u.getFrase());
            try
            {
                Date date = new SimpleDateFormat("dd/mm/yyyy").parse(u.getDataDiNascita());
                stat.setDate(5, new java.sql.Date(date.getTime()));
            }catch(ParseException e)
            {
                Logger.getLogger(UtenteFactory.class.getName()).
                log(Level.SEVERE, null, e);
            }
            stat.setString(6, u.getPassword());

            stat.setInt(7, u.getId());
            
            stat.executeUpdate();
            
            stat.close();
            conn.close();
            
        }catch(SQLException e)
        {
            Logger.getLogger(UtenteFactory.class.getName()).
            log(Level.SEVERE, null, e);
        }
    }
    
    public boolean removeUser(Utente u)
    {
        Connection conn = null;
        PreparedStatement removePost = null;
        PreparedStatement removeUser = null;
        try
        {
            conn = DriverManager.getConnection(connectionString, "amm", "admin");  
            conn.setAutoCommit(false);
            String post = "DELETE FROM posts WHERE bacheca_id = ? AND gruppo = false";
            String user = "DELETE FROM utenti WHERE id = ?";
            
            removePost = conn.prepareStatement(post);
            removeUser = conn.prepareStatement(user);
            
            removePost.setInt(1, u.getId());
            removeUser.setInt(1, u.getId());
            
            removePost.executeUpdate();
            removeUser.executeUpdate();
            
            conn.commit();
        }catch(SQLException e)
        {
            Logger.getLogger(UtenteFactory.class.getName()).
            log(Level.SEVERE, null, e);
            
            if(conn!=null)
            {    
                try
                {
                    conn.rollback();
                }catch(SQLException ex)
                {
                    Logger.getLogger(UtenteFactory.class.getName()).
                    log(Level.SEVERE, null, ex);
                }
            }
            return false;
        }
        finally
        {
            try
            {    
                if(removePost != null)
                    removePost.close();
                if(removeUser != null)
                    removeUser.close();
                if(conn != null)
                    conn.close();
            }catch(SQLException e)
            {
                Logger.getLogger(UtenteFactory.class.getName()).
                log(Level.SEVERE, null, e);
            }
        }
        return true;
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
