/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DataTier.PoloDAO;
import Model.Polo;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pedro
 */
public class PoloController extends CRUD implements Operation {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        return reflectMethod(PoloController.class, request, response);
    }

    @Override
    public String _new(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        return "views/polo/new.jsp";
    }
    
    @Override
    public String create(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        Polo polo = new Polo();
        polo.setName(req.getParameter("poloInput"));

        PoloDAO.add(polo);
        
        return "ctrl?op=PoloController&action=index";
        
    }

    @Override
    public String index(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        ArrayList<Polo> poloList = PoloDAO.getPoloList();
        
        req.setAttribute("poloList", poloList);
        
        return "views/polo/index.jsp";
        
    }

    @Override
    public String show(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        Polo polo = PoloDAO.get(Integer.parseInt(req.getParameter("id")));
        
        req.setAttribute("polo", polo);
        
        return "views/polo/show.jsp";
        
    }

    @Override
    public String edit(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        Polo p = PoloDAO.get(Integer.parseInt(req.getParameter("id")));
        
        req.setAttribute("polo", p);
        
        return "views/polo/edit.jsp";
        
    }

    @Override
    public String update(HttpServletRequest req, HttpServletResponse res) {
        
        return "ctrl?op=PoloController&action=index";
        
    }

    @Override
    public String delete(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        
        PoloDAO.delete(PoloDAO.get(Integer.parseInt(req.getParameter("id"))));
        
        return "ctrl?op=PoloController&action=index";
        
    }
    
}
