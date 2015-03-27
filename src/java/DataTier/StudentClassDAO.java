/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTier;

import Model.Polo;
import Model.StudentClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Pedro
 */
public class StudentClassDAO extends DAO {

    public static void add(StudentClass sc) throws SQLException {

        String sql = "INSERT INTO student_class VALUES(?,?,?,?,?,?)";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, sc.getCode());
            stmt.setString(2, sc.getCourse());
            stmt.setTime(3, sc.getStartTime());
            stmt.setTime(4, sc.getEndTime());
            stmt.setString(5, sc.getShift());
            stmt.setInt(6, sc.getPolo().getId());

            stmt.execute();
            
        } finally {
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
    }

    public static void delete(StudentClass sc) throws SQLException {

        String sql = "DELETE FROM student_class WHERE code=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, sc.getCode());

            stmt.execute();
            
        } finally {
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
    }

    public static StudentClass get(int code) throws SQLException {

        StudentClass sc = null;

        String sql = "SELECT * FROM student_class WHERE code=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, code);

            rs = stmt.executeQuery();

            if (rs.next()) {

                sc = new StudentClass();

                sc.setCode(rs.getInt("code"));
                sc.setCourse(rs.getString("course"));
                sc.setStartTime(rs.getTime("startTime"));
                sc.setEndTime(rs.getTime("endTime"));
                sc.setShift(rs.getString("shift"));
                sc.setPolo(PoloDAO.get(rs.getInt("polo")));

            }
            
        } finally {
            DatabaseUtils.close(rs);
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
        return sc;
        
    }
    
    public static void update(StudentClass sc) throws SQLException {

        String sql = "UPDATE student_class SET course=?, startTime=?, endTime=?, shift=?, polo=? WHERE code=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, sc.getCourse());
            stmt.setTime(2, sc.getStartTime());
            stmt.setTime(3, sc.getEndTime());
            stmt.setString(4, sc.getShift());
            stmt.setInt(5, sc.getPolo().getId());
            stmt.setInt(6, sc.getCode());

            stmt.execute();
            
        } finally {
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
    }

    public static ArrayList<StudentClass> getStudentClassList() throws SQLException {

        ArrayList<StudentClass> list = new ArrayList<>();

        String sql = "SELECT * FROM student_class";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {

                StudentClass sc = new StudentClass();

                sc.setCode(rs.getInt("code"));
                sc.setCourse(rs.getString("course"));
                sc.setStartTime(rs.getTime("startTime"));
                sc.setEndTime(rs.getTime("endTime"));
                sc.setShift(rs.getString("shift"));
                sc.setPolo(PoloDAO.get(rs.getInt("polo")));

                list.add(sc);

            }
            
        } finally {
            DatabaseUtils.close(rs);
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
        return list;
        
    }
    
    public static ArrayList<StudentClass> getPoloStudentClassList(Polo p) throws SQLException {
        
        ArrayList<StudentClass> list = new ArrayList<>();
        
        String sql = "SELECT * FROM student_class WHERE polo=?";
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            
            con = getConnection();
            stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, p.getId());
            
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                
                StudentClass sc = new StudentClass();
                
                sc.setCode(rs.getInt("code"));
                sc.setCourse(rs.getString("course"));
                sc.setStartTime(rs.getTime("startTime"));
                sc.setEndTime(rs.getTime("endTime"));
                sc.setShift(rs.getString("shift"));
                sc.setPolo(p);
                
                list.add(sc);
                
            }
            
        } finally {
            DatabaseUtils.close(rs);
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(con);
        }
        
        return list;
        
    }
    
}
