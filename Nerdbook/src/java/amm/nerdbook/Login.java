/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nerdbook;

import amm.nerdbook.classi.GruppoFactory;
import amm.nerdbook.classi.PostFactory;
import amm.nerdbook.classi.Utente;
import amm.nerdbook.classi.UtenteFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "Login", urlPatterns = {"/login.html"}, loadOnStartup = 0)
public class Login extends HttpServlet {

    private static final String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DB_CLEAN_PATH = "../../web/WEB-INF/db/ammdb";
    private static final String DB_BUILD_PATH = "WEB-INF/db/ammdb";
    
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
      
            if (request.getParameter("user") != null &&
                request.getParameter("password") != null) 
            {
                String username = new String(request.getParameter("user").getBytes("ISO-8859-1"));
                String password = new String(request.getParameter("password").getBytes("ISO-8859-1"));
                
                int loggedUserID = UtenteFactory.getInstance().getIdByUserAndPassword(username, password);
                Utente loggedUser = UtenteFactory.getInstance().getById(loggedUserID);
               
                if(loggedUser!=null)
                {
                    session.setMaxInactiveInterval(-1);
                    session.setAttribute("loggedIn", true);
                    session.setAttribute("loggedUser", loggedUser);
                    
                    if( (loggedUser.getNome()!=null&&loggedUser.getNome().equals(""))   ||
                        (loggedUser.getCognome()!=null&&loggedUser.getCognome().equals(""))||
                        (loggedUser.getUrlFoto()!=null&&loggedUser.getUrlFoto().equals(""))||
                        (loggedUser.getFrase()!=null&&loggedUser.getFrase().equals("")))
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

    @Override
   public void init(){
       String dbConnection = "jdbc:derby:" + this.getServletContext().getRealPath("/") + DB_BUILD_PATH;
       try {
           Class.forName(JDBC_DRIVER);
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
       }
       PostFactory.getInstance().setConnectionString(dbConnection);
       UtenteFactory.getInstance().setConnectionString(dbConnection);
       GruppoFactory.getInstance().setConnectionString(dbConnection);
       
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
