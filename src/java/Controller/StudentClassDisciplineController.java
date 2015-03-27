/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DataTier.DisciplineDAO;
import DataTier.StudentClassDAO;
import DataTier.StudentClassDisciplineDAO;
import DataTier.TeacherDAO;
import Model.Discipline;
import Model.StudentClass;
import Model.StudentClassDiscipline;
import Model.Teacher;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pedro
 */
public class StudentClassDisciplineController extends CRUD  implements Operation {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        return reflectMethod(StudentClassDisciplineController.class, request, response);
    }
    
    @Override
    public String _new(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        ArrayList<StudentClass> studentClassList    = StudentClassDAO.  getStudentClassList();
        ArrayList<Discipline>   disciplineList      = DisciplineDAO.    getDisciplineList();
        ArrayList<Teacher>      teacherList         = TeacherDAO.       getTeacherList();

        req.setAttribute("studentClassList" , studentClassList);
        req.setAttribute("disciplineList"   , disciplineList);
        req.setAttribute("teacherList"      , teacherList);
        
        return "views/student_class_discipline/new.jsp";
    }

    @Override
    public String create(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        StudentClassDiscipline scd = new StudentClassDiscipline();

        scd.setStudentClass (StudentClassDAO.get(Integer.parseInt(req.getParameter("studentClassSCDInput"))));
        scd.setDiscipline   (DisciplineDAO.get  (Integer.parseInt(req.getParameter("disciplineSCDInput"))));
        scd.setHours        (Float.parseFloat   (req.getParameter("hoursSCDInput")));
        scd.setStartDate    (Date.valueOf       (req.getParameter("startDateSCDInput")));
        scd.setEndDate      (Date.valueOf       (req.getParameter("endDateSCDInput")));
        scd.setTeacher      (TeacherDAO.get     (req.getParameter("teacherSCDInput")));

        StudentClassDisciplineDAO.add(scd);
        
        return "ctrl?op=StudentClassDisciplineController&action=index";
        
    }

    @Override
    public String index(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        ArrayList<StudentClassDiscipline> scdList = StudentClassDisciplineDAO.getStudentClassDisciplineList();
        
        req.setAttribute("scdList", scdList);
        
        return "views/student_class_discipline/index.jsp";
        
    }

    @Override
    public String show(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        StudentClassDiscipline scd = StudentClassDisciplineDAO.get(Integer.parseInt(req.getParameter("id")));
        
        req.setAttribute("scd", scd);
        
        return "views/student_class_discipline/show.jsp";
        
    }

    @Override
    public String edit(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        StudentClassDiscipline scd = StudentClassDisciplineDAO.get(Integer.parseInt(req.getParameter("id")));
        ArrayList<StudentClass> studentClassList    = StudentClassDAO.  getStudentClassList();
        ArrayList<Discipline>   disciplineList      = DisciplineDAO.    getDisciplineList();
        ArrayList<Teacher>      teacherList         = TeacherDAO.       getTeacherList();

        req.setAttribute("studentClassList" , studentClassList);
        req.setAttribute("disciplineList"   , disciplineList);
        req.setAttribute("teacherList"      , teacherList);
        req.setAttribute("scd", scd);
        
        return "views/student_class_discipline/edit.jsp";
        
    }

    @Override
    public String update(HttpServletRequest req, HttpServletResponse res) {
        
        return "ctrl?op=StudentClassDisciplineController&action=index";
        
    }

    @Override
    public String delete(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        StudentClassDisciplineDAO.delete(StudentClassDisciplineDAO.get(Integer.parseInt(req.getParameter("id"))));
        
        return "ctrl?op=StudentClassDisciplineController&action=index";
        
    }
    
}
