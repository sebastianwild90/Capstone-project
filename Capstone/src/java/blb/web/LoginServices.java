/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blb.web;

import blb.database.DBOperations;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sebastian Wild
 */
@WebServlet(name = "LoginServices", urlPatterns = {"/LoginServices"})
public class LoginServices extends HttpServlet {

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
        
        String action = request.getParameter("action");
        String username = request.getParameter("email");
        String password = request.getParameter("password");
        DBOperations dbops = new DBOperations();
        String logout = request.getParameter("logout");
        
        int authUserResult = dbops.authUser(username, password);
        System.out.println("HELLO?\n\n\n\n\n\n\n");
        if (logout!=null) {
            request.setAttribute("message", "Logged out");
            request.getRequestDispatcher("/WEB-INF/LoginScreen.jsp").forward(request, response);
        } else if (username == null || password == null) {
            request.setAttribute("message", "Both username and password are required");
            request.getRequestDispatcher("/WEB-INF/LoginScreen.jsp").forward(request, response);
        }
        else if(username.trim().equals("") || password.trim().equals("")){// nothing supplied
            request.setAttribute("message", "Both username and password are required");
            request.getRequestDispatcher("/WEB-INF/LoginScreen.jsp").forward(request, response);
        } else if(authUserResult != -1){
            switch (authUserResult) {
                case 0://Regular User
                    //We have no screens for regular clients at this time
                    request.getRequestDispatcher("/WEB-INF/LoginScreen.jsp").forward(request, response);
                    break;
                case 1://Manager
                    
                    String date = new SimpleDateFormat("EEEE MMMM d, y").format(new Date());
                    request.setAttribute("reportDate", date);
                    request.setAttribute("dailyReportProductionList", dbops.getDailyReportProductionList(date));
                    request.getRequestDispatcher("/WEB-INF/reportDailyScreen.jsp").forward(request, response);
                    break;
                case 2://Admin
                    //We have no screens for admins at this time
                    request.getRequestDispatcher("/WEB-INF/LoginScreen.jsp").forward(request, response);
                    break;
            }
            
            
        } else { //invalid username or password
            request.setAttribute("message", "Invalid username or password");
            request.getRequestDispatcher("/WEB-INF/LoginScreen.jsp").forward(request, response);
        }
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
