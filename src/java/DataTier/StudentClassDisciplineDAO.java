/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTier;

import Model.StudentClass;
import Model.StudentClassDiscipline;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Pedro
 */
public class StudentClassDisciplineDAO extends DAO {

    public static void add(StudentClassDiscipline scd) throws SQLException {

        String sql = "INSERT INTO student_class_discipline (student_class,discipline,hours,startDate,endDate,teacher) VALUES(?,?,?,?,?,?)";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, scd.getStudentClass().getCode());
            stmt.setInt(2, scd.getDiscipline().getCode());
            stmt.setFloat(3, scd.getHours());
            stmt.setDate(4, scd.getStartDate());
            stmt.setDate(5, scd.getEndDate());
            stmt.setString(6, scd.getTeacher().getCpf());

            stmt.execute();
            
        } finally {
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
    }

    public static void delete(StudentClassDiscipline scd) throws SQLException {

        String sql = "DELETE FROM student_class_discipline WHERE student_class=? AND discipline=?";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, scd.getStudentClass().getCode());
            stmt.setInt(2, scd.getDiscipline().getCode());

            stmt.execute();
            
        } finally {
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
    }

    public static StudentClassDiscipline get(int id) throws SQLException {

        StudentClassDiscipline scd = null;

        String sql = "SELECT * FROM student_class_discipline WHERE id=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {

                scd = new StudentClassDiscipline();

                scd.setId(rs.getInt("id"));
                scd.setStudentClass(StudentClassDAO.get(rs.getInt("student_class")));
                scd.setDiscipline(DisciplineDAO.get(rs.getInt("discipline")));
                scd.setHours(rs.getFloat("hours"));
                scd.setStartDate(rs.getDate("startDate"));
                scd.setEndDate(rs.getDate("endDate"));
                scd.setTeacher(TeacherDAO.get(rs.getString("teacher")));

            }
            
        } finally {
            DatabaseUtils.close(rs);
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
        return scd;
        
    }
    
    public static void update(StudentClassDiscipline scd) throws SQLException {

        String sql = "UPDATE student_class_discipline SET student_class=? ,discipline=? ,hours=? ,startDate=?, endDate=?, teacher=? WHERE id=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, scd.getStudentClass().getCode());
            stmt.setInt(2, scd.getDiscipline().getCode());
            stmt.setFloat(3, scd.getHours());
            stmt.setDate(4, scd.getStartDate());
            stmt.setDate(5, scd.getEndDate());
            stmt.setString(6, scd.getTeacher().getCpf());
            stmt.setInt(7, scd.getId());

            stmt.execute();
            
        } finally {
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
    }

    public static ArrayList<StudentClassDiscipline> getStudentClassDisciplineList() throws SQLException {

        ArrayList<StudentClassDiscipline> list = new ArrayList<>();

        String sql = "SELECT * FROM student_class_discipline";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {

                StudentClassDiscipline scd = new StudentClassDiscipline();

                scd.setId(rs.getInt("id"));
                scd.setStudentClass(StudentClassDAO.get(rs.getInt("student_class")));
                scd.setDiscipline(DisciplineDAO.get(rs.getInt("discipline")));
                scd.setHours(rs.getFloat("hours"));
                scd.setStartDate(rs.getDate("startDate"));
                scd.setEndDate(rs.getDate("endDate"));
                scd.setTeacher(TeacherDAO.get(rs.getString("teacher")));

                list.add(scd);

            }
            
        } finally {
            DatabaseUtils.close(rs);
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
        return list;
        
    }

    public static ArrayList<StudentClassDiscipline> getStudentClassDisciplinesBySC(StudentClass sc) throws SQLException {

        ArrayList<StudentClassDiscipline> list = new ArrayList<>();

        String sql = "SELECT * FROM student_class_discipline WHERE student_class=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, sc.getCode());
            rs = stmt.executeQuery();

            while (rs.next()) {

                StudentClassDiscipline scd = new StudentClassDiscipline();

                scd.setId(rs.getInt("id"));
                scd.setStudentClass(StudentClassDAO.get(rs.getInt("student_class")));
                scd.setDiscipline(DisciplineDAO.get(rs.getInt("discipline")));
                scd.setHours(rs.getFloat("hours"));
                scd.setStartDate(rs.getDate("startDate"));
                scd.setEndDate(rs.getDate("endDate"));
                scd.setTeacher(TeacherDAO.get(rs.getString("teacher")));

                list.add(scd);

            }
            
        } finally {
            DatabaseUtils.close(rs);
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
        return list;
        
    }
    
    public static ArrayList<StudentClassDiscipline> getSCDListByTeacher(String cpf) throws SQLException {
        
        ArrayList<StudentClassDiscipline> list = new ArrayList<>();
        
        String sql = "SELECT * FROM student_class_discipline WHERE teacher=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, cpf);
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                StudentClassDiscipline scd = new StudentClassDiscipline();

                scd.setId(rs.getInt("id"));
                scd.setStudentClass(StudentClassDAO.get(rs.getInt("student_class")));
                scd.setDiscipline(DisciplineDAO.get(rs.getInt("discipline")));
                scd.setHours(rs.getFloat("hours"));
                scd.setStartDate(rs.getDate("startDate"));
                scd.setEndDate(rs.getDate("endDate"));
                scd.setTeacher(TeacherDAO.get(rs.getString("teacher")));

                list.add(scd);

            }
            
        } finally {
            DatabaseUtils.close(rs);
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
        return list;
        
    }
    
}
