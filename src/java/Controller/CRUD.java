/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pedro
 */
public abstract class CRUD {

    final String reflectMethod(Class sonClass,HttpServletRequest req, HttpServletResponse res) {
        
        String page = "home.jsp";
        
        try {
            
            String action = req.getParameter("action");
            
            Method m = sonClass.getMethod(action, new Class[]{HttpServletRequest.class,HttpServletResponse.class});
            page = (String) m.invoke(this, req, res);
            
        }
        catch ( NoSuchMethodException 
              | SecurityException 
              | IllegalAccessException 
              | IllegalArgumentException 
              | InvocationTargetException ex ) {
            Logger.getLogger(ClassController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return page;
        
    }
    
    public abstract String _new     (HttpServletRequest req, HttpServletResponse res) throws SQLException;
    public abstract String create   (HttpServletRequest req, HttpServletResponse res) throws SQLException;
    public abstract String index    (HttpServletRequest req, HttpServletResponse res) throws SQLException;
    public abstract String show     (HttpServletRequest req, HttpServletResponse res) throws SQLException;
    public abstract String edit     (HttpServletRequest req, HttpServletResponse res) throws SQLException;
    public abstract String update   (HttpServletRequest req, HttpServletResponse res) throws SQLException;
    public abstract String delete   (HttpServletRequest req, HttpServletResponse res) throws SQLException;
    
}
