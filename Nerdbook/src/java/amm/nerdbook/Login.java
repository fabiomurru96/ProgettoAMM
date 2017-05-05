/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nerdbook;

import amm.nerdbook.classi.GruppoFactory;
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
@WebServlet(name = "Login", urlPatterns = {"/login.html"})
public class Login extends HttpServlet {

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
        
        if(request.getParameter("logout")!=null)
        {
            session.invalidate();
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        
        if (session.getAttribute("loggedUser") != null &&
            session.getAttribute("loggedIn") != null   &&
            session.getAttribute("loggedIn").equals(true)) 
        {

            response.sendRedirect(request.getContextPath() + "/bacheca.html");
            return;
        
        }
        else 
        {
            String username = request.getParameter("user");
            String password = request.getParameter("password");
        
      
            if (username != null &&
                password != null) 
            {
                int loggedUserID = UtenteFactory.getInstance().getIdByUserAndPassword(username, password);
                Utente loggedUser = UtenteFactory.getInstance().getById(loggedUserID);
               
                if(loggedUser!=null)
                {
                    session.setMaxInactiveInterval(-1);
                    session.setAttribute("loggedIn", true);
                    session.setAttribute("loggedUser", loggedUser);
                    
                    getServletContext().setAttribute("utenti", UtenteFactory.getInstance().getUsersList());
                    getServletContext().setAttribute("gruppi", GruppoFactory.getInstance().getGroupsList());
                    
                    if( loggedUser.getNome().equals("")         ||
                        loggedUser.getCognome().equals("")      ||
                        loggedUser.getDataDiNascita().equals("")||
                        loggedUser.getFrase().equals(""))
                    {
                        response.sendRedirect(request.getContextPath() + "/profilo.html");
                    }
                    else
                        response.sendRedirect(request.getContextPath() + "/bacheca.html");
                    return;
                } 
                else 
                { 
                    request.setAttribute("invalidData", true);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    return;
                }
                
                
            }
        }
        
        request.getRequestDispatcher("login.jsp").forward(request, response);
    
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
