/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTier;

import Model.Attendance;
import Model.Class;
import Model.Student;
import Model.StudentClass;
import Model.StudentClassDiscipline;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Pedro
 */
public class AttendanceDAO extends DAO {

    public static void add(Attendance a) throws SQLException {
        
        String sql = "INSERT INTO attendance VALUES(?,?,?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, a.getClasss().getId());
            stmt.setInt(2, a.getStudent().getId());
            stmt.setBoolean(3, a.isPresent());
            
            stmt.execute();

        } finally {
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
    }

    public static void delete(Attendance a) throws SQLException {

        String sql = "DELETE FROM attendance WHERE class=? AND student=?";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, a.getClasss().getId());
            stmt.setInt(2, a.getStudent().getId());
            stmt.execute();
            
        } finally {
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
    }
    
    public static void update(Attendance att) throws SQLException {
        
        String sql = "UPDATE attendance SET presence=? WHERE class=? AND student=?";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setBoolean(1, att.isPresent());
            stmt.setInt(2, att.getClasss().getId());
            stmt.setInt(3, att.getStudent().getId());
            
            stmt.execute();

        } finally {
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
    }

    public static ArrayList<Attendance> getAttendancesByClass(Class c) throws SQLException {

        ArrayList<Attendance> list = new ArrayList<>();

        String sql = "SELECT * FROM attendance WHERE class=?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, c.getId());
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {

                Attendance att = new Attendance();
                att.setClass(ClassDAO.get(rs.getInt("class")));
                att.setStudent(StudentDAO.get(rs.getInt("student")));
                att.setPresence(rs.getBoolean("presence"));
                
                list.add(att);
                
            }
            
        } finally {
            DatabaseUtils.close(rs);
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
        return list;

    }

    public static ArrayList<Attendance> getAttendancesByStudentAndStudentClassDisciplineAndDate(Student student, StudentClassDiscipline scd, Date startDate, Date endDate) throws SQLException {

        ArrayList<Attendance> list = new ArrayList<>();
        String sql  = "SELECT * FROM "
                    + "(attendance "
                    + "INNER JOIN classes on attendance.class=classes.id)"
                
                    + "where attendance.student=? AND classes.student_class_discipline=? AND classes.date>=? AND classes.date<=?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, student.getId());
            stmt.setInt(2, scd.getId());
            stmt.setDate(3, startDate);
            stmt.setDate(4, endDate);

            rs = stmt.executeQuery();

            while (rs.next()) {

                Attendance att = new Attendance();
                att.setClass(ClassDAO.get(rs.getInt("class")));
                att.setStudent(student);
                att.setPresence(rs.getBoolean("presence"));
                list.add(att);

            }
            
        } finally {
            DatabaseUtils.close(rs);
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }

        return list;

    }

    public static ArrayList<Attendance> getAttendancesByStudentAndStudentClassDisciplineAndDate(Student student, StudentClassDiscipline scd, Date date) throws SQLException {
        return getAttendancesByStudentAndStudentClassDisciplineAndDate(student, scd, date, date); //chama o método acima, usando a data como data de início e fim
    }

    public static ArrayList<Attendance> getAttendancesByStudentAndStudentClassAndDate(Student student, StudentClass sc, Date startDate, Date endDate) throws SQLException {

        ArrayList<Attendance> list = new ArrayList<>();

        String sql = "SELECT * FROM "
                + "(((attendance "
                + "INNER JOIN classes on attendance.class=classes.id)"
                + "INNER JOIN student_class_discipline on classes.student_class_discipline=student_class_discipline.id)"
                + "INNER JOIN student_class on student_class_discipline.student_class=student_class.code)"
                + "where attendance.student=? AND student_class.code=? AND classes.date>=? AND classes.date<=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, student.getId());
            stmt.setInt(2, sc.getCode());
            stmt.setDate(3, startDate);
            stmt.setDate(4, endDate);

            rs = stmt.executeQuery();

            while (rs.next()) {

                Attendance att = new Attendance();
                att.setClass(ClassDAO.get(rs.getInt("class")));
                att.setStudent(student);
                att.setPresence(rs.getBoolean("presence"));
                list.add(att);

            }
            
        } finally {
            DatabaseUtils.close(rs);
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
        return list;

    }

    public static ArrayList<Attendance> getAttendancesByStudentAndStudentClassAndDate(Student student, StudentClass sc, Date date) throws SQLException {
        return getAttendancesByStudentAndStudentClassAndDate(student, sc, date, date);//chama o método acima, usando a data como data de início e fim
    }

}
