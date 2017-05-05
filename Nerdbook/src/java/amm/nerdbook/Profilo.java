/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nerdbook;

import amm.nerdbook.classi.Utente;
import amm.nerdbook.classi.UtenteFactory;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "Profilo", urlPatterns = {"/profilo.html"})
public class Profilo extends HttpServlet {

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
        
        HttpSession session = request.getSession();
        
        if(session.getAttribute("loggedIn") == null || session.getAttribute("loggedUser") == null)
        {
            session.invalidate();
            request.setAttribute("error","403");
            request.getRequestDispatcher("profilo.jsp").forward(request, response);
            return;
        }
        
        Utente u = (Utente) session.getAttribute("loggedUser");
        
        String nome = (String) request.getParameter("nome");
        String cognome = (String) request.getParameter("cognome");
        String propic = (String) request.getParameter("propic");
        String status = (String) request.getParameter("status");
        String datanascita = (String) request.getParameter("datanascita");
        String psswd = (String) request.getParameter("psswd");
        String confirmpss = (String) request.getParameter("confirmpss");
        
        if(nome != null && !nome.equals(""))
        {
            UtenteFactory.getInstance().getById(u.getId()).setNome(nome);
            request.setAttribute("nome", nome);
            request.setAttribute("done","1");
        }
        if(cognome != null && !cognome.equals(""))
        {
            UtenteFactory.getInstance().getById(u.getId()).setCognome(cognome);
            request.setAttribute("cognome", cognome);
            request.setAttribute("done","1");
        }
        if(propic != null && !propic.equals(""))
        {
            UtenteFactory.getInstance().getById(u.getId()).setUrlFoto(propic);
            request.setAttribute("propic", propic);
            request.setAttribute("done","1");
        }
        if(status != null && !status.equals(""))
        {
            UtenteFactory.getInstance().getById(u.getId()).setFrase(status);
            request.setAttribute("status", status);
            request.setAttribute("done","1");
        }
        if(datanascita != null && !datanascita.equals(""))
        {
            UtenteFactory.getInstance().getById(u.getId()).setDataDiNascita(datanascita);
            request.setAttribute("datanascita", datanascita);
            request.setAttribute("done","1");
        }
        if(psswd != null && !psswd.equals(""))
        {
            if(confirmpss == null ||  (confirmpss != null && confirmpss.equals("")))
                request.setAttribute("errpass", "1");
            else if(!confirmpss.equals(psswd))
                request.setAttribute("errpass","2");
            else
            {
                UtenteFactory.getInstance().getById(u.getId()).setPassword(psswd);
                request.setAttribute("psswd", psswd);
                request.setAttribute("done","1");
            }
        }
            
        request.getRequestDispatcher("profilo.jsp").forward(request, response);
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
