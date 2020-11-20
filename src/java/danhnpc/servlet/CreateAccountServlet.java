/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhnpc.servlet;

import danhnpc.tblusers.TblUsersCreateError;
import danhnpc.tblusers.TblUsersDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author visug
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {

    private final String CREATE_ERROR = "signUp.jsp";
    private final String LOGIN_PAGE = "login.html";

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
        PrintWriter out = response.getWriter();
        String userID = request.getParameter("txtUserID");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");

        String url = CREATE_ERROR;
        boolean errFound = false;
        TblUsersCreateError errors = new TblUsersCreateError();

        try {
            
            if (userID.trim().length() < 6 || userID.trim().length() > 20) {
                errFound = true;
                errors.setUsernameLengthErr("Username length has 6 - 20 chars");
            }

            if (password.trim().length() < 6 || password.trim().length() > 30) {
                errFound = true;
                errors.setPasswordLengthErr("Password length has 6 - 30 chars");
            } else if (!confirm.trim().equals(password.trim())) {
                errFound = true;
                errors.setConfirmErr("Confirm does not match password");
            }

            if (fullname.trim().length() < 2 || fullname.trim().length() > 50) {
                errFound = true;
                errors.setFullnameLengthErr("Full name length has 2 - 50 chars");
            }
            if (errFound) {
                request.setAttribute("CREATEERROR", errors);
            } else {
                TblUsersDAO dao = new TblUsersDAO();
                boolean result = dao.createAcoount(userID, password, fullname, false);
                if (result) {
                    url = LOGIN_PAGE;
                }
            }

        } catch (SQLException ex) {
            String err = ex.getMessage();
            if(err.contains("duplicate")){
                errors.setUsernameIsExisted(userID + " is existed");
                request.setAttribute("CREATEERROR", errors);
            }
            log("CreateAccountServlet_SQLException: " + err);
        } catch (NamingException ex) {
            log("CreateAccountServlet_NamingException: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
