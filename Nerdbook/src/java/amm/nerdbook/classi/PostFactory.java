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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabio
 */
public class PostFactory
{

    private static PostFactory singleton;

    public static PostFactory getInstance()
    {
        if (singleton == null)
        {
            singleton = new PostFactory();
        }
        return singleton;
    }

    private ArrayList<Post> listaPost = new ArrayList<>();
    private String connectionString;
    private int lastId = -1;

    private PostFactory()
    {
        UtenteFactory utenti = UtenteFactory.getInstance();
        GruppoFactory gruppi = GruppoFactory.getInstance();

        Post post1 = new Post();
        post1.setId(0);
        post1.setTesto("Ciao a tutti!");
        post1.setTipoAllegato(Post.Type.NONE);
        post1.setUtente(utenti.getById(2));
        post1.setAutore(utenti.getById(2));
        lastId++;

        Post post2 = new Post();
        post2.setId(1);
        post2.setTesto("Wow, guardate quanto è bella questa foto!");
        post2.setTipoAllegato(Post.Type.IMAGE);
        post2.setAllegato("img/Lorem-ipsum.jpg");
        post2.setUtente(utenti.getById(4));
        post2.setAutore(utenti.getById(4));
        lastId++;

        Post post3 = new Post();
        post3.setId(2);
        post3.setTesto("Apri Mario!");
        post3.setTipoAllegato(Post.Type.LINK);
        post3.setAllegato("http://www.google.it");
        post3.setUtente(utenti.getById(2));
        post3.setAutore(utenti.getById(4));
        lastId++;

        Post post4 = new Post();
        post4.setId(3);
        post4.setTesto("Ciao mongolfieristi.");
        post4.setGruppo(gruppi.getById(0));
        post4.setAutore(utenti.getById(2));
        lastId++;

        Post post5 = new Post();
        post5.setId(4);
        post5.setTesto("Yeeee");
        post5.setGruppo(gruppi.getById(0));
        post5.setAutore(utenti.getById(4));
        lastId++;
        
        listaPost.add(post1);
        listaPost.add(post2);
        listaPost.add(post3);
        listaPost.add(post4);
        listaPost.add(post5);
    }

    public Post getById(int id)
    {
        try
        {
            Connection conn = DriverManager.getConnection(connectionString, "amm", "admin");            
            String sql = "SELECT * FROM posts JOIN postType ON tipoAllegato = postType.id WHERE posts.id = ?";
            
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setInt(1,id);
            
            ResultSet set = stat.executeQuery();     
            
            if(set.next())
            {
                Post post = new Post();
                
                post.setId(id);
                post.setTesto(set.getString("testo"));
                post.setAutore(UtenteFactory.getInstance().getById(set.getInt("autore")));
                if(set.getBoolean("gruppo"))
                    post.setGruppo(GruppoFactory.getInstance().getById(set.getInt("bacheca_id")));
                else
                    post.setUtente(UtenteFactory.getInstance().getById(set.getInt("bacheca_id")));
                post.setAutore(UtenteFactory.getInstance().getById(set.getInt("autore")));
                post.setTesto(set.getString("testo"));
                post.setTipoAllegato(Post.Type.valueOf(set.getString("name")));
                if(post.getTipoAllegato() != Post.Type.NONE)
                    post.setAllegato(set.getString("allegato"));
                
                
                stat.close();
                conn.close();
                return post;
            }
            stat.close();
            conn.close();
            
        }catch(SQLException e)
        {
            Logger.getLogger(UtenteFactory.class.getName()).
            log(Level.SEVERE, null, e);
        }
        
        return null;
        
        
        /*for (Post p : listaPost)
        {
            if (p.getId() == id)
            {
                return p;
            }
        }
        return null;*/
    }

    public void addPost(Post p)
    {
        
        try
        {
            Connection conn = DriverManager.getConnection(connectionString, "amm", "admin");            
            String sql;
            if(p.getTipoAllegato() == Post.Type.NONE) 
                sql = "INSERT INTO posts (id,autore,bacheca_id,gruppo,testo,tipoAllegato) VALUES (default,?,?,?,?,?)";
            else
                sql = "INSERT INTO posts (id,autore,bacheca_id,gruppo,testo,tipoAllegato,allegato) VALUES (default,?,?,?,?,?,?)";
            
            
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setInt(1,p.getAutore().getId());
            if(p.getGruppo()==null)
            {
                stat.setInt(2,p.getUtente().getId());
                stat.setBoolean(3, false);
            }
            else if(p.getUtente()==null)
            {
                stat.setInt(2,p.getGruppo().getId());
                stat.setBoolean(3, true);
            }
            stat.setString(4,p.getTesto());
            stat.setInt(5,p.getTipoAllegato().getValue());
            if(p.getTipoAllegato() != Post.Type.NONE)
                stat.setString(6,p.getAllegato());
            
            
            stat.executeUpdate();
            
            stat.close();
            conn.close();
        }catch(SQLException e)
        {
            Logger.getLogger(UtenteFactory.class.getName()).
            log(Level.SEVERE, null, e);
        }
        
        /*listaPost.add(p);
        lastId++;*/
    }

    public List<Post> getList(Utente u)
    {
        ArrayList<Post> temp = new ArrayList<>();

        if(u != null)
            try
            {
                Connection conn = DriverManager.getConnection(connectionString, "amm", "admin");            
                String sql = "SELECT * FROM posts JOIN postType ON tipoAllegato = postType.id WHERE posts.bacheca_id = ? AND posts.gruppo = false";

                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setInt(1,u.getId());

                ResultSet set = stat.executeQuery();     

                while(set.next())
                {
                    Post post = new Post();

                    post.setId(u.getId());
                    post.setTesto(set.getString("testo"));
                    post.setAutore(UtenteFactory.getInstance().getById(set.getInt("autore")));
                    if(set.getBoolean("gruppo"))
                        post.setGruppo(GruppoFactory.getInstance().getById(set.getInt("bacheca_id")));
                    else
                        post.setUtente(UtenteFactory.getInstance().getById(set.getInt("bacheca_id")));
                    post.setAutore(UtenteFactory.getInstance().getById(set.getInt("autore")));
                    post.setTesto(set.getString("testo"));
                    post.setTipoAllegato(Post.Type.valueOf(set.getString("name")));
                    if(post.getTipoAllegato() != Post.Type.NONE)
                        post.setAllegato(set.getString("allegato"));

                    temp.add(post);
                }
                stat.close();
                conn.close();

            }catch(SQLException e)
            {
                Logger.getLogger(UtenteFactory.class.getName()).
                log(Level.SEVERE, null, e);
            }
        
        return temp;
        
        /*for (Post p : listaPost)
        {
            if (p.getUtente() != null && p.getUtente().equals(u))
            {
                temp.add(p);
            }
        }
        if (temp.isEmpty())
        {
            return null;
        }
        return temp;*/
    }

    public void removePostById(int id)
    {
        try
        {
            Connection conn = DriverManager.getConnection(connectionString, "amm", "admin");
            String sql = "DELETE FROM posts WHERE id = ?";
            
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setInt(1,id);
            
            stat.executeUpdate();
            
        }catch(SQLException e)
        {
            Logger.getLogger(UtenteFactory.class.getName()).
            log(Level.SEVERE, null, e);
        }
    }
    
    /*public void removePostByBacheca(Utente u) throws SQLException
    {
            Connection conn = DriverManager.getConnection(connectionString, "amm", "admin");
            String sql = "DELETE FROM posts WHERE id = ? AND gruppo = false";
            
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setInt(1, u.getId());
            
            stat.executeUpdate();
            
    }
    
    public void removePostByBacheca(Gruppo g) throws SQLException
    {
            Connection conn = DriverManager.getConnection(connectionString, "amm", "admin");
            String sql = "DELETE FROM posts WHERE id = ? AND gruppo = true";
            
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setInt(1, g.getId());
            
            stat.executeUpdate();
            
    }*/
    
    public List<Post> getList(Gruppo g)
    {
        ArrayList<Post> temp = new ArrayList<>();

        if(g!=null)
            try
            {
                Connection conn = DriverManager.getConnection(connectionString, "amm", "admin");            
                String sql = "SELECT * FROM posts JOIN postType ON tipoAllegato = postType.id WHERE posts.bacheca_id = ? AND posts.gruppo = true";

                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setInt(1,g.getId());

                ResultSet set = stat.executeQuery();     

                while(set.next())
                {
                    Post post = new Post();

                    post.setId(g.getId());
                    post.setTesto(set.getString("testo"));
                    post.setAutore(UtenteFactory.getInstance().getById(set.getInt("autore")));
                    if(set.getBoolean("gruppo"))
                        post.setGruppo(GruppoFactory.getInstance().getById(set.getInt("bacheca_id")));
                    else
                        post.setUtente(UtenteFactory.getInstance().getById(set.getInt("bacheca_id")));
                    post.setAutore(UtenteFactory.getInstance().getById(set.getInt("autore")));
                    post.setTesto(set.getString("testo"));
                    post.setTipoAllegato(Post.Type.valueOf(set.getString("name")));
                    if(post.getTipoAllegato() != Post.Type.NONE)
                        post.setAllegato(set.getString("allegato"));

                    temp.add(post);
                }
                stat.close();
                conn.close();

            }catch(SQLException e)
            {
                Logger.getLogger(UtenteFactory.class.getName()).
                log(Level.SEVERE, null, e);
            }
        
        return temp;
        
        /*ArrayList<Post> temp = new ArrayList<>();

        for (Post p : listaPost)
        {
            if (p.getGruppo() != null && p.getGruppo().equals(g))
            {
                temp.add(p);
            }
        }
        if (temp.isEmpty())
        {
            return null;
        }
        return temp;*/
    }

    /**
     * @return the lastId
     */
    public int getLastId()
    {
        return lastId;
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
