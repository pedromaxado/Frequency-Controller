/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTier;

import Model.Teacher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Pedro
 */
public class TeacherDAO extends DAO {

    public static void add(Teacher t) throws SQLException {

        String sql = "INSERT INTO teacher VALUES(?,?,?,?,?,?)";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, t.getCpf());
            stmt.setString(2, t.getName());
            stmt.setString(3, t.getBank());
            stmt.setString(4, t.getAgency());
            stmt.setString(5, t.getAccount());
            stmt.setString(6, t.getContact());

            stmt.execute();
            
        } finally {
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
    }

    public static void delete(Teacher t) throws SQLException {

        String sql = "DELETE FROM teacher WHERE cpf=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, t.getCpf());

            stmt.execute();
            
        } finally {
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
    }

    public static Teacher get(String cpf) throws SQLException {

        Teacher t = null;

        String sql = "SELECT * FROM teacher WHERE cpf=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, cpf);

            rs = stmt.executeQuery();

            if (rs.next()) {

                t = new Teacher();

                t.setCpf(rs.getString("cpf"));
                t.setName(rs.getString("name"));
                t.setBank(rs.getString("bank"));
                t.setAccount(rs.getString("agency"));
                t.setAgency(rs.getString("account"));
                t.setContact(rs.getString("contact"));
            }
            
        } finally {
            DatabaseUtils.close(rs);
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
        return t;
        
    }
    
    public static void update(Teacher t) throws SQLException {
        
        String sql = "UPDATE teacher SET name=?, bank=?, agency=?, account=?, contact=? WHERE cpf=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, t.getName());
            stmt.setString(2, t.getBank());
            stmt.setString(3, t.getAgency());
            stmt.setString(4, t.getAccount());
            stmt.setString(5, t.getContact());
            stmt.setString(6, t.getCpf());

            stmt.execute();
            
        } finally {
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
    }

    public static ArrayList<Teacher> getTeacherList() throws SQLException {

        ArrayList<Teacher> list = new ArrayList<>();

        String sql = "SELECT * FROM teacher";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {

                Teacher t = new Teacher();

                t.setCpf(rs.getString("cpf"));
                t.setName(rs.getString("name"));
                t.setBank(rs.getString("bank"));
                t.setAccount(rs.getString("agency"));
                t.setAgency(rs.getString("account"));
                t.setContact(rs.getString("contact"));

                list.add(t);
            }
            
        } finally {
            DatabaseUtils.close(rs);
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
        return list;
        
    }
    
}
