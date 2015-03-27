/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pedro
 */
public class Home implements Operation {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        return "views/home/index.jsp";
    }
    
}
