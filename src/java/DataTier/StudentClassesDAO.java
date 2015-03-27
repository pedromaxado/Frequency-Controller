/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTier;

import Model.Student;
import Model.StudentClasses;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class StudentClassesDAO extends DAO {

    public static void add(StudentClasses sc) throws SQLException {

        String sql = "INSERT INTO student_classes VALUES(?,?)";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, sc.getStudent().getId());
            stmt.setInt(2, sc.getStudentClass().getCode());

            stmt.execute();
            
        } finally {
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
    }

    public static void delete(StudentClasses sc) throws SQLException {

        String sql = "DELETE FROM student_classes WHERE student=? AND student_class=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, sc.getStudent().getId());
            stmt.setInt(2, sc.getStudentClass().getCode());

            stmt.execute();
            
        } finally {
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
    }
    
    public static ArrayList<StudentClasses> getStudentClassesByStudent(Student s) throws SQLException {
        
        ArrayList<StudentClasses> list = new ArrayList<>();
        
        String sql = "SELECT * FROM student_classes WHERE student=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, s.getId());
            
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                
                StudentClasses sc = new StudentClasses();
                
                sc.setStudent(s);
                sc.setStudentClass(StudentClassDAO.get(rs.getInt("student_class")));
                
                list.add(sc);
                
            }
            
        } finally {
            DatabaseUtils.close(rs);
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
        return list;
        
    }

}
