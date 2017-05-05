/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nerdbook.classi;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabio
 */
public class PostFactory {
    
    private static PostFactory singleton;
   
    public static PostFactory getInstance()
    {
        if(singleton == null)
            singleton = new PostFactory();
        return singleton;
    }
    
    private ArrayList<Post> listaPost = new ArrayList<>();
   
    private int lastId = -1;
    
    private PostFactory()
    {
        UtenteFactory utenti = UtenteFactory.getInstance();
        GruppoFactory gruppi = GruppoFactory.getInstance();
        
        Post post1 = new Post();
        post1.setId(0);
        post1.setTesto("Ciao a tutti!");
        post1.setTipoAllegato(Post.Type.NONE);
        post1.setUtente(utenti.getById(0));
        post1.setAutore(utenti.getById(0));
        lastId++;
        
        Post post2 = new Post();
        post2.setId(1);
        post2.setTesto("Wow, che bella foto!");
        post2.setTipoAllegato(Post.Type.IMAGE);
        post2.setAllegato("img/Lorem-ipsum.jpg");
        post2.setUtente(utenti.getById(1));
        post2.setAutore(utenti.getById(1));
        lastId++;
        
        Post post3 = new Post();
        post3.setId(2);
        post3.setTesto("Apri Mario!");
        post3.setTipoAllegato(Post.Type.LINK);
        post3.setAllegato("http://www.google.it");
        post3.setUtente(utenti.getById(0));
        post3.setAutore(utenti.getById(1));
        lastId++;
        
        Post post4 = new Post();
        post4.setId(3);
        post4.setTesto("Ciao mongolfieristi.");
        post4.setGruppo(gruppi.getById(0));
        post4.setAutore(utenti.getById(0));
        lastId++;
        
        listaPost.add(post1);
        listaPost.add(post2);
        listaPost.add(post3);
        listaPost.add(post4);
    }
    
    public Post getById(int id)
    {
        for(Post p : listaPost)
            if(p.getId() == id)
                return p;
        return null;
    }
    
    public void addPost(Post p)
    {
        listaPost.add(p);
        lastId++;
    }
    
    public List<Post> getList(Utente u)
    {
        ArrayList<Post> temp = new ArrayList<>();
        
        for(Post p : listaPost)
            if(p.getUtente() != null && p.getUtente().equals(u))
                temp.add(p);
        if(temp.isEmpty())
            return null;
        return temp;
    }
    
    public List<Post> getList(Gruppo g)
    {
        ArrayList<Post> temp = new ArrayList<>();
        
        for(Post p : listaPost)
            if(p.getGruppo() != null && p.getGruppo().equals(g))
                temp.add(p);
        if(temp.isEmpty())
            return null;
        return temp;
    }

    /**
     * @return the lastId
     */
    public int getLastId() {
        return lastId;
    }

}
