/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DataTier.StudentClassesDAO;
import DataTier.StudentDAO;
import Model.Student;
import Model.StudentClasses;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pedro
 */
public class StudentController extends CRUD implements Operation {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        return reflectMethod(StudentController.class, request, response);
    }
    
    @Override
    public String _new(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        return "views/student/new.jsp";
    }

    @Override
    public String create(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        Student student = new Student();

        student.setName(req.getParameter("studentNameInput"));

        StudentDAO.add(student);
        
        return "ctrl?op=StudentController&action=index";
        
    }

    @Override
    public String index(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        ArrayList<Student> studentList = StudentDAO.getStudentList();
        
        req.setAttribute("studentList", studentList);
        
        return "views/student/index.jsp";
        
    }

    @Override
    public String show(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        Student student = StudentDAO.get(Integer.parseInt(req.getParameter("id")));
        ArrayList<StudentClasses> scsList = StudentClassesDAO.getStudentClassesByStudent(student);

        req.setAttribute("scsList", scsList);
        req.setAttribute("student", student);
        
        return "views/student/show.jsp";
        
    }

    @Override
    public String edit(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        Student s = StudentDAO.get(Integer.parseInt(req.getParameter("id")));
        
        req.setAttribute("student", s);
        
        return "views/student/edit.jsp";
        
    }

    @Override
    public String update(HttpServletRequest req, HttpServletResponse res) {
        
        return "ctrl?op=StudentController&action=index";
        
    }

    @Override
    public String delete(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        StudentDAO.delete(StudentDAO.get(Integer.parseInt(req.getParameter("id"))));
        
        return "ctrl?op=StudentController&action=index";
        
    }
    
}
