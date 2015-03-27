/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DataTier.StudentClassDAO;
import DataTier.StudentClassesDAO;
import DataTier.StudentDAO;
import Model.Student;
import Model.StudentClass;
import Model.StudentClasses;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pedro
 */
public class StudentAssociationController extends CRUD implements Operation {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        return reflectMethod(StudentAssociationController.class, request, response);
    }
    
    @Override
    public String _new(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        ArrayList<Student> studentList = StudentDAO.getStudentList();
        ArrayList<StudentClass> studentClassList = StudentClassDAO.getStudentClassList();
        
        req.setAttribute("studentList", studentList);
        req.setAttribute("scList", studentClassList);
        
        return "views/student_association/new.jsp";
    }

    @Override
    public String create(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        StudentClasses sc = new StudentClasses();

        sc.setStudent(StudentDAO.get(Integer.parseInt(req.getParameter("studentIdAssocInput"))));
        sc.setStudentClass(StudentClassDAO.get(Integer.parseInt(req.getParameter("studentStudentClassAssocInput"))));

        StudentClassesDAO.add(sc);
        
        return "ctrl?op=StudentClassController&action=show&id=" + sc.getStudentClass().getCode();
        
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
        return "home.jsp";
    }

    @Override
    public String update(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        return "home.jsp";
    }

    @Override
    public String delete(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        StudentClasses sc = new StudentClasses();
        
        sc.setStudent(StudentDAO.get(Integer.parseInt(req.getParameter("student"))));
        sc.setStudentClass(StudentClassDAO.get(Integer.parseInt(req.getParameter("studentClass"))));
        
        StudentClassesDAO.delete(sc);
        
        return "home.jsp";
        
    }
    
}
