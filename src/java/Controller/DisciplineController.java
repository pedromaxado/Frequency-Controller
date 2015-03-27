/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DataTier.DisciplineDAO;
import Model.Discipline;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pedro
 */
public class DisciplineController  extends CRUD implements Operation {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        return reflectMethod(DisciplineController.class, request, response);
    }

    @Override
    public String _new(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        return "views/discipline/new.jsp";
    }
    
    @Override
    public String create(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        Discipline discipline = new Discipline();

        discipline.setCode(Integer.parseInt(req.getParameter("disciplineIdInput")));
        discipline.setName(req.getParameter("disciplineNameInput"));

        DisciplineDAO.add(discipline);
        
        return "ctrl?op=DisciplineController&action=index";
        
    }

    @Override
    public String index(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        ArrayList<Discipline> disciplineList = DisciplineDAO.getDisciplineList();
        
        req.setAttribute("disciplineList", disciplineList);
        
        return "views/discipline/index.jsp";
        
    }

    @Override
    public String show(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        Discipline discipline = DisciplineDAO.get(Integer.parseInt(req.getParameter("id")));
        
        req.setAttribute("discipline", discipline);
        
        return "views/discipline/show.jsp";
        
    }

    @Override
    public String edit(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        Discipline d = DisciplineDAO.get(Integer.parseInt(req.getParameter("id")));
        
        req.setAttribute("discipline", d);
        
        return "views/discipline/edit.jsp";
        
    }

    @Override
    public String update(HttpServletRequest req, HttpServletResponse res) {
        
        return "ctrl?op=DisciplineController&action=index";
        
    }

    @Override
    public String delete(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        DisciplineDAO.delete(DisciplineDAO.get(Integer.parseInt(req.getParameter("id"))));
        
        return "ctrl?op=DisciplineController&action=index";
        
    }
    
}
