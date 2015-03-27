/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTier;

import Model.Polo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Pedro
 */
public class PoloDAO extends DAO {

    public static void add(Polo p) throws SQLException {

        String sql = "INSERT INTO polo (name) VALUES (?)";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, p.getName());
            stmt.execute();
            
        } finally {
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
    }

    public static void delete(Polo p) throws SQLException {

        String sql = "DELETE FROM polo WHERE id=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, p.getId());
            stmt.execute();
            
        } finally {
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
    }

    public static Polo get(int id) throws SQLException {

        Polo p = null;

        String sql = "SELECT * FROM polo WHERE id=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {

                p = new Polo();

                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));

            }
            
        } finally {
            DatabaseUtils.close(rs);
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
        return p;
        
    }
    
    public static void update(Polo p) throws SQLException {
        
        String sql = "UPDATE polo SET name=? WHERE id=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, p.getName());
            stmt.setInt(2, p.getId());
            
            stmt.execute();
            
        } finally {
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
    }

    public static ArrayList<Polo> getPoloList() throws SQLException {

        ArrayList<Polo> list = new ArrayList<>();

        String sql = "SELECT * FROM polo";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {

                Polo p = new Polo();

                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));

                list.add(p);

            }
            
        } finally {
            DatabaseUtils.close(rs);
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
        return list;
        
    }

}
