/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DataTier.UserDAO;
import Model.User;
import java.io.IOException;
import java.sql.SQLException;
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
 * @author pedro
 */
@WebServlet("/Login")
public class Login extends HttpServlet {

    private final int MAX_INACTIVE_TIME = 3600;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User u = new User();

        u.setUsername(request.getParameter("user"));
        u.setPassword(request.getParameter("pwd"));

        try {
            
            if (UserDAO.login(u)) {
                
                HttpSession session = request.getSession();
                
                User currentUser = UserDAO.get(u.getUsername());
                
                session.setAttribute("userObj", currentUser);
                session.setAttribute("currentUser",     currentUser.getUsername());
                session.setAttribute("currentUserRole", currentUser.getRole());
                session.setAttribute("currentUserName", currentUser.getName());
                session.setMaxInactiveInterval(MAX_INACTIVE_TIME);
                
//                request.getRequestDispatcher("views/home/index.jsp").forward(request, response);
                response.sendRedirect("ctrl?op=Home");
                
            } else {
                response.sendRedirect(request.getServletContext().getContextPath());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
