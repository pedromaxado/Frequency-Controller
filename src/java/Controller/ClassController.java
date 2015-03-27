/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DataTier.AttendanceDAO;
import DataTier.ClassDAO;
import DataTier.PoloDAO;
import DataTier.StudentClassDisciplineDAO;
import Model.Attendance;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.Class;
import Model.Polo;
import Model.StudentClassDiscipline;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class ClassController extends CRUD implements Operation {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        return reflectMethod(ClassController.class, request, response);
    }

    @Override
    public String _new(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        ArrayList<Polo> poloList = PoloDAO.getPoloList();
        ArrayList<StudentClassDiscipline> scdList;
        
        if( "admin".equals((String)req.getSession().getAttribute("currentUserRole"))) {
            scdList = StudentClassDisciplineDAO.getStudentClassDisciplineList();
        }
        else {
            scdList = StudentClassDisciplineDAO.getSCDListByTeacher((String) req.getSession().getAttribute("currentUser"));
        }
        
        req.setAttribute("poloList", poloList);
        req.setAttribute("scdList", scdList);
        
        return "views/class/new.jsp";
    }
    
    @Override
    public String create(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        Model.Class c = new Model.Class();

        c.setStudentClassDiscipline(StudentClassDisciplineDAO.get(Integer.parseInt(req.getParameter("scdInput"))));
        c.setDate(Date.valueOf(req.getParameter("classDate")));
        c.setHoursPerDay(Integer.parseInt(req.getParameter("hoursPerDay")));

        ClassDAO.add(c);
        
        return "ctrl?op=ClassController&action=index";
        
    }
    
    @Override
    public String index(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        ArrayList<Class> clssList = ClassDAO.getClassList();
        
        req.setAttribute("clssList", clssList);
        
        return "views/class/index.jsp";
        
    }

    @Override
    public String show(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        Class clss = ClassDAO.get(Integer.parseInt(req.getParameter("id")));
        ArrayList<Attendance> attList = AttendanceDAO.getAttendancesByClass(clss);
        
        req.setAttribute("clss", clss);
        req.setAttribute("attList", attList);
        
        return "views/class/show.jsp";
        
    }

    @Override
    public String edit(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        Class clss = ClassDAO.get(Integer.parseInt(req.getParameter("id")));
        ArrayList<StudentClassDiscipline> scdList = StudentClassDisciplineDAO.getStudentClassDisciplineList();
        
        req.setAttribute("scdList", scdList);
        req.setAttribute("clss", clss);
        
        return "views/class/edit.jsp";
        
    }

    @Override
    public String update(HttpServletRequest req, HttpServletResponse res) {
        
        return "ctrl?op=ClassController&action=index";
        
    }

    @Override
    public String delete(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        ClassDAO.delete(ClassDAO.get(Integer.parseInt(req.getParameter("id"))));
        
        return "ctrl?op=ClassController&action=index";
        
    }
    
}
