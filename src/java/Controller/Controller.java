/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pedro
 */
@WebServlet("/ctrl")
public class Controller extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {

        String param = request.getParameter("op");
        String className = "Controller." + param;

        try {

            Class newClass = Class.forName(className);
            Operation op = (Operation)newClass.newInstance();

            String page = op.execute(request, response);

            request.getRequestDispatcher(page).forward(request, response);

        }
        catch ( ClassNotFoundException
              | InstantiationException
              | IllegalAccessException
              | SQLException
              | ServletException
              | IOException ex ) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
