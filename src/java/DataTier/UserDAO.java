/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTier;

import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Pedro
 */
public class UserDAO extends DAO {

    public static void add(User u) throws SQLException {

        String sql = "INSERT INTO users (username, password, name, role) VALUES(?,MD5(?),?,?)";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, u.getUsername());
            stmt.setString(2, u.getPassword());
            stmt.setString(3, u.getName());
            stmt.setString(4, u.getRole());
            
            stmt.execute();
            
        } finally {
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }

    }

    public static boolean login(User u) throws SQLException {

        String sql = "SELECT * FROM users WHERE username=?";
        String md5Pwd = "SELECT MD5(?) AS pwd";
        
        Connection conn1 = null;
        Connection conn2 = null;
        
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        
        boolean isValid=false;
        
        try {
            
            conn1 = DAO.getConnection();
            conn2 = DAO.getConnection();
            stmt1 = conn1.prepareStatement(sql);
            stmt2 = conn1.prepareStatement(md5Pwd);

            stmt1.setString(1, u.getUsername());
            stmt2.setString(1, u.getPassword());

            rs1 = stmt1.executeQuery();
            rs2 = stmt2.executeQuery();
            
            isValid = rs1.next() && rs2.next() && rs1.getString("password").equals(rs2.getString("pwd"));

        } finally {
            DatabaseUtils.close(rs1);
            DatabaseUtils.close(rs2);
            DatabaseUtils.close(stmt1);
            DatabaseUtils.close(stmt2);
            DatabaseUtils.close(conn1);
            DatabaseUtils.close(conn2);
        }
        
        return isValid;

    }
    
    public static void delete(User s) throws SQLException {
        
        String sql = "DELETE FROM users WHERE username=?";
        
        Connection con = null;
        PreparedStatement stmt = null;
        
        try {
            
            con = getConnection();
            stmt = con.prepareStatement(sql);
            
            stmt.setString(1, s.getUsername());
            
            stmt.execute();
            
        } finally {
            
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(con);
            
        }
        
    }

    public static User get(String username) throws SQLException {

        User user = null;

        String sql = "SELECT * FROM users WHERE username=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, username);

            rs = stmt.executeQuery();

            if (rs.next()) {

                user = new User();

                user.setUsername(rs.getString("username"));
                user.setName(rs.getString("name"));
                user.setRole(rs.getString("role"));

            }
            
        } finally {
            DatabaseUtils.close(rs);
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
        return user;
        
    }
    
    public static void updatePassword(User u) throws SQLException {

        String sql = "UPDATE users SET password=MD5(?) WHERE username=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, u.getPassword());
            stmt.setString(2, u.getUsername());
            
            stmt.execute();
            
        } finally {
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }

    }

}
