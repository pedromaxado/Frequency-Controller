/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DataTier.StudentClassDisciplineDAO;
import Model.StudentClassDiscipline;
import Sheets.PartialSheet;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pedro
 */
public class PartialSheetController extends CRUD implements Operation {

    private static final int DEFAULT_BUFFER_SIZE = 8192;
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        return reflectMethod(PartialSheetController.class, request, response);
    }

    @Override
    public String _new(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        ArrayList<StudentClassDiscipline> scdList = StudentClassDisciplineDAO.getStudentClassDisciplineList();
        
        req.setAttribute("scdList", scdList);
        
        return "views/partial_sheet/new.jsp";
        
    }

    @Override
    public String create(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        StudentClassDiscipline scd = StudentClassDisciplineDAO.get(Integer.parseInt(req.getParameter("scd")));
//            
        Date startDate  = Date.valueOf(req.getParameter("startDate"));
        Date endDate    = Date.valueOf(req.getParameter("endDate"));
            
        PartialSheet ps = new PartialSheet(scd, startDate, endDate);

        try {
            
            ps.generate();
            ps.save();
            
        } catch (IOException ex) {
            Logger.getLogger(PartialSheetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "ctrl?op=PartialSheetController&action=show";
        
    }

    @Override
    public String index(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String show(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        return "views/partial_sheet/show.jsp";
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
