/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTier;

import Model.Discipline;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Pedro
 */
public class DisciplineDAO extends DAO {

    public static void add(Discipline d) throws SQLException {

        String sql = "INSERT INTO discipline VALUES(?,?)";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, d.getCode());
            stmt.setString(2, d.getName());

            stmt.execute();
            
        } finally {
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
    }

    public static void delete(Discipline d) throws SQLException {

        String sql = "DELETE FROM discipline WHERE code=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, d.getCode());

            stmt.execute();
            
        } finally {
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
    }

    public static Discipline get(int code) throws SQLException {

        Discipline d = null;

        String sql = "SELECT * FROM discipline WHERE code=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, code);

            rs = stmt.executeQuery();

            if (rs.next()) {

                d = new Discipline();

                d.setCode(rs.getInt("code"));
                d.setName(rs.getString("name"));

            }
            
        } finally {
            DatabaseUtils.close(rs);
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
        return d;
        
    }
    
    public static void update(Discipline d) throws SQLException {
        
        String sql = "UPDATE discipline SET name=? WHERE code=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, d.getCode());
            stmt.setString(2, d.getName());

            stmt.execute();
            
        } finally {
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
    }

    public static ArrayList<Discipline> getDisciplineList() throws SQLException {

        ArrayList<Discipline> list = new ArrayList<>();

        String sql = "SELECT * FROM discipline";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {

                Discipline d = new Discipline();

                d.setCode(rs.getInt("code"));
                d.setName(rs.getString("name"));

                list.add(d);

            }
            
        } finally {
            DatabaseUtils.close(rs);
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
        return list;
        
    }

}
