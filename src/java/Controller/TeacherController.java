/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DataTier.TeacherDAO;
import Model.Teacher;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pedro
 */
public class TeacherController extends CRUD implements Operation {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        return reflectMethod(TeacherController.class, request, response);
    }
    
    @Override
    public String _new(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        return "views/teacher/new.jsp";
    }

    @Override
    public String create(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        Teacher teacher = new Teacher();

        teacher.setCpf(req.getParameter("cpfInput"));
        teacher.setName(req.getParameter("teacherNameInput"));
        teacher.setBank(req.getParameter("bankInput"));
        teacher.setAgency(req.getParameter("agencyInput"));
        teacher.setAccount(req.getParameter("accountInput"));
        teacher.setContact(req.getParameter("contactInput"));

        TeacherDAO.add(teacher);
        
        return "ctrl?op=TeacherController&action=index";
        
    }

    @Override
    public String index(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        ArrayList<Teacher> teacherList = TeacherDAO.getTeacherList();
        
        req.setAttribute("teacherList", teacherList);
        
        return "views/teacher/index.jsp";
        
    }

    @Override
    public String show(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        Teacher teacher = TeacherDAO.get(req.getParameter("id"));
        
        req.setAttribute("teacher", teacher);
        
        return "views/teacher/show.jsp";
        
    }

    @Override
    public String edit(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        Teacher t = TeacherDAO.get(req.getParameter("id"));
        
        req.setAttribute("teacher", t);
        
        return "views/teacher/edit.jsp";
        
    }

    @Override
    public String update(HttpServletRequest req, HttpServletResponse res) {
        
        return "ctrl?op=TeacherController&action=index";
        
    }

    @Override
    public String delete(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        TeacherDAO.delete(TeacherDAO.get(req.getParameter("id")));
        
        return "ctrl?op=TeacherController&action=index";
        
    }
    
}
