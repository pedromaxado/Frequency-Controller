/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DataTier.PoloDAO;
import DataTier.StudentClassDAO;
import DataTier.StudentDAO;
import Model.Polo;
import Model.Student;
import Model.StudentClass;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pedro
 */
public class StudentClassController extends CRUD implements Operation {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        return reflectMethod(StudentClassController.class, request, response);
    }
    
    @Override
    public String _new(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        ArrayList<Polo> poloList = PoloDAO.getPoloList();
        
        req.setAttribute("poloList", poloList);        
        
        return "views/student_class/new.jsp";
    }

    @Override
    public String create(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        StudentClass studentClass = new StudentClass();

        studentClass.setCode(Integer.parseInt(req.getParameter("studentClassCodeInput")));
        studentClass.setCourse(req.getParameter("courseInput"));
        studentClass.setStartTime(Time.valueOf(req.getParameter("starTimeInput")+":00"));
        studentClass.setEndTime(Time.valueOf(req.getParameter("endTimeInput")+":00"));
        studentClass.setShift(req.getParameter("shiftInput"));
        studentClass.setPolo(PoloDAO.get(Integer.parseInt(req.getParameter("studentClassPoloInput"))));

        StudentClassDAO.add(studentClass);
        
        return "ctrl?op=StudentClassController&action=index";
    }

    @Override
    public String index(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        ArrayList<StudentClass> scList = StudentClassDAO.getStudentClassList();
        
        req.setAttribute("scList", scList);
        
        return "views/student_class/index.jsp";
        
    }

    @Override
    public String show(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        StudentClass sc = StudentClassDAO.get(Integer.parseInt(req.getParameter("id")));
        ArrayList<Student> studentList = StudentDAO.getStudentListBySC(sc);
        
        req.setAttribute("studentList", studentList);
        req.setAttribute("sc", sc);
        
        return "views/student_class/show.jsp";
        
    }

    @Override
    public String edit(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        StudentClass sc = StudentClassDAO.get(Integer.parseInt(req.getParameter("id")));
        
        req.setAttribute("sc", sc);
        
        return "views/student_class/edit.jsp";
        
    }

    @Override
    public String update(HttpServletRequest req, HttpServletResponse res) {
        
        return "ctrl?op=StudentClassController&action=index";
        
    }

    @Override
    public String delete(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        StudentClassDAO.delete(StudentClassDAO.get(Integer.parseInt(req.getParameter("id"))));
        
        return "ctrl?op=StudentClassController&action=index";
        
    }
    
}
