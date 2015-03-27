/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DataTier.AttendanceDAO;
import DataTier.ClassDAO;
import DataTier.StudentClassDisciplineDAO;
import DataTier.StudentDAO;
import Model.Attendance;
import Model.StudentClassDiscipline;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.Class;
import Model.Student;
import java.sql.Date;

/**
 *
 * @author pedro
 */
public class AttendanceController extends CRUD implements Operation {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        return reflectMethod(AttendanceController.class, request, response);
    }
    
    @Override
    public String _new(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        Class aClass = ClassDAO.get(Integer.parseInt(req.getParameter("classInput")));
        
        ArrayList<Student> studentList = StudentDAO.getStudentListBySC(aClass.getStudentClassDiscipline().getStudentClass());
            
        req.setAttribute("studentList", studentList);
        req.setAttribute("aClass", aClass);
        req.setAttribute("action", "create");
    
        return "views/attendance/new.jsp";
                
    }

    @Override
    public String create(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        
        Class aClass = ClassDAO.get(Integer.parseInt(req.getParameter("id")));
        ArrayList<Student> studentList = StudentDAO.getStudentListBySC(aClass.getStudentClassDiscipline().getStudentClass());
        
        for(Student s : studentList) {
            
            Attendance att = new Attendance();
            
            att.setClass(aClass);
            att.setStudent(s);
            
            if( "on".equals(req.getParameter(String.valueOf(s.getId()))) )
                att.setPresence(true);
            else
                att.setPresence(false);
            
            AttendanceDAO.add(att);
            
        }
        
        return "ctrl?op=AttendanceController&action=presetSCD";
        
    }

    @Override
    public String index(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        return "home.jsp";
    }

    @Override
    public String show(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        return "home.jsp";
    }

    @Override
    public String edit(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        Class aClass = ClassDAO.get(Integer.parseInt(req.getParameter("id")));
        ArrayList<Student> studentList = StudentDAO.getStudentListBySC(aClass.getStudentClassDiscipline().getStudentClass());
        
        req.setAttribute("aClass", aClass);
        req.setAttribute("studentList", studentList);
        req.setAttribute("action", "update");
        
        return "views/attendance/edit.jsp";
    }

    @Override
    public String update(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        Class aClass = ClassDAO.get(Integer.parseInt(req.getParameter("id")));
        ArrayList<Student> studentList = StudentDAO.getStudentListBySC(aClass.getStudentClassDiscipline().getStudentClass());
        
        for(Student s : studentList) {
            
            Attendance att = new Attendance();
            
            att.setClass(aClass);
            att.setStudent(s);
            
            if( "on".equals(req.getParameter(String.valueOf(s.getId()))) )
                att.setPresence(true);
            else
                att.setPresence(false);
            
            AttendanceDAO.update(att);
            
        }
        
        return "ctrl?op=ClassController&action=show&id=" + aClass.getId();

    }

    @Override
    public String delete(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        return "home.jsp";
    }

    public String presetSCD(HttpServletRequest req, HttpServletResponse res) throws SQLException {

        ArrayList<StudentClassDiscipline> scdList = new ArrayList<>();
        
        String role = (String) req.getSession().getAttribute("currentUserRole");
        
        if( role.equals("teacher") )
            scdList = StudentClassDisciplineDAO.getSCDListByTeacher((String) req.getSession().getAttribute("currentUser"));
        else
            scdList = StudentClassDisciplineDAO.getStudentClassDisciplineList();

        req.setAttribute("scdList", scdList);

        return "views/attendance/presetSCD.jsp";

    }
    
    public String presetDate(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        StudentClassDiscipline scd = StudentClassDisciplineDAO.get(Integer.parseInt(req.getParameter("scdInput")));
        
        ArrayList<Class> classList = new ArrayList<>();
        
        for( Class c : ClassDAO.getClassesByStudentClassDiscipline(scd) )
            if( !c.hasAttendances() )
                classList.add(c);
        
        req.setAttribute("classList", classList );
        
        return "views/attendance/presetDate.jsp";
    }

}
