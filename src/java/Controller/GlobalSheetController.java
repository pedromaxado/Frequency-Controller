/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DataTier.StudentClassDAO;
import Model.StudentClass;
import Sheets.GlobalSheet;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pedro
 */
public class GlobalSheetController extends CRUD implements Operation {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        return reflectMethod(GlobalSheetController.class, request, response);
    }
    
    @Override
    public String _new(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        ArrayList<StudentClass> scList = StudentClassDAO.getStudentClassList();
        
        req.setAttribute("scList", scList);
        
        return "views/global_sheet/new.jsp";
        
    }

    @Override
    public String create(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        StudentClass sc = StudentClassDAO.get(Integer.parseInt(req.getParameter("studentClass")));
        Date startDate = Date.valueOf(req.getParameter("startDate"));
        Date endDate = Date.valueOf(req.getParameter("endDate"));
        
        GlobalSheet gs = new GlobalSheet(sc, startDate, endDate);
        
        try {
            
            gs.generate();
            gs.save();
            
        } catch (IOException ex) {
            Logger.getLogger(GlobalSheetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "ctrl?op=GlobalSheetController&action=show";
        
    }

    @Override
    public String index(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String show(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        return "views/global_sheet/show.jsp";
        
    }

    @Override
    public String edit(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String update(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String delete(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
