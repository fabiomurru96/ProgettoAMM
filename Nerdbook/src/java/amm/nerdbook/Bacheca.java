/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nerdbook;

import amm.nerdbook.classi.Gruppo;
import amm.nerdbook.classi.GruppoFactory;
import amm.nerdbook.classi.Post;
import amm.nerdbook.classi.PostFactory;
import amm.nerdbook.classi.Utente;
import amm.nerdbook.classi.UtenteFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fabio
 */
@WebServlet(name = "Bacheca", urlPatterns = {"/bacheca.html"})
public class Bacheca extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session=request.getSession();
        
        Utente utente = null;
        Gruppo gruppo = null;
        Post p;
        if(request.getParameter("postC") != null)
        {
            try
            {
                final String base64String = request.getParameter("postC");
                final byte[] objToBytes = Base64.getDecoder().decode(base64String);
                ByteArrayInputStream bais = new ByteArrayInputStream(objToBytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                p = (Post) ois.readObject();
            }catch(ClassNotFoundException e)
            {
                p = null;
            }
        }
        else 
            p = null;
        
        
        if(request.getParameter("confirm") != null && p != null)
        {
            PostFactory abc = PostFactory.getInstance();
            abc.addPost(p);
            /*String redirect = request.getContextPath() + "/bacheca.html";
            if(request.getParameter("user") != null)
                redirect=redirect.concat("?user=" + request.getParameter("user"));
            else if(request.getParameter("group") != null)
                redirect=redirect.concat("?group=" + request.getParameter("group"));
            response.sendRedirect(redirect);*/
            request.setAttribute("scritto", "1");
        }
         
        
        if(session.getAttribute("loggedIn") == null || session.getAttribute("loggedUser") == null)
        {
            session.invalidate();
            request.setAttribute("error","403");
            request.getRequestDispatcher("bacheca.jsp").forward(request, response);
            return;
        }
        
        if(request.getParameter("group")!=null)
        {
            int group = Integer.parseInt(request.getParameter("group"));
            request.setAttribute("group", group);
            gruppo = GruppoFactory.getInstance().getById(group);
            request.setAttribute("posts", PostFactory.getInstance().getList(gruppo));
        }
        else 
        {
            int user;
            
            if(request.getParameter("user")!=null)
                user = Integer.parseInt(request.getParameter("user"));
            else
                user = ((Utente)session.getAttribute("loggedUser")).getId();
            
            utente = UtenteFactory.getInstance().getById(user);
            request.setAttribute("user", user);
            request.setAttribute("userU", utente);
            request.setAttribute("posts", PostFactory.getInstance().getList(utente));
        }
        
        if(request.getParameter("invia") != null)
        {
            String testo, allegato, tipo;
            
            p = new Post();
            PostFactory posts = PostFactory.getInstance();
            
            p.setId(posts.getLastId()+1);
            if(request.getParameter("user") != null)
                utente = UtenteFactory.getInstance().getById(Integer.parseInt(request.getParameter("user")));
            else if(request.getParameter("group") != null)
                gruppo = GruppoFactory.getInstance().getById(Integer.parseInt(request.getParameter("group")));
            p.setUtente(utente);
            p.setGruppo(gruppo);
            p.setAutore((Utente)session.getAttribute("loggedUser"));
            
            if(request.getParameter("testo") != null)
            {
                testo = request.getParameter("testo");
                if(testo.equals(""))
                {
                    request.setAttribute("err", "1");
                    request.getRequestDispatcher("bacheca.jsp").forward(request, response);
                    return;
                }
                p.setTesto(testo);
            }
            else
            {
                request.setAttribute("err", "1");
                request.getRequestDispatcher("bacheca.jsp").forward(request, response);
                return;
            }
            
            if(request.getParameter("tipo") == null)
                tipo = "NONE";
            else
                tipo = request.getParameter("tipo");
              
            p.setTipoAllegato(Post.Type.valueOf(tipo.toUpperCase()));
            
            if(request.getParameter("allegato") != null)
            {
                allegato = request.getParameter("allegato");
                if(!tipo.equals("NONE") && allegato.equals(""))
                {
                    request.setAttribute("err", "2");
                    request.getRequestDispatcher("bacheca.jsp").forward(request, response);
                    return;
                }
                p.setAllegato(allegato);
            }
            else if(!tipo.equals("NONE"))
            {
                request.setAttribute("err", "2");
                request.getRequestDispatcher("bacheca.jsp").forward(request, response);
                return;
            }
            
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(p);
            oos.flush();
            final String result = new String(Base64.getEncoder().encode(baos.toByteArray()));
            request.setAttribute("postCode", result);
            request.setAttribute("postNew", p);
            request.setAttribute("submit", "1");
        }
        
        request.getRequestDispatcher("bacheca.jsp").forward(request, response);
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
