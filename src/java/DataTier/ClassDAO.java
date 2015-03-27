/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTier;

import Model.Class;
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
public class ClassDAO extends DAO {

    public static int getLastId() throws SQLException {
        
        int last_id = 0;
        String sql = "SELECT MAX(id) AS bigone FROM classes";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            if (rs.next()) {
                last_id = rs.getInt("bigone");
            }
            
        } finally {
            DatabaseUtils.close(rs);
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
        return last_id;
        
    }

    public static void add(Class c) throws SQLException {

        String sql = "INSERT INTO classes VALUES(?,?,?,?)";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, getLastId() + 1);
            stmt.setInt(2, c.getStudentClassDiscipline().getId());
            stmt.setDate(3, c.getDate());
            stmt.setFloat(4, c.getHoursPerDay());

            stmt.execute();
            
        } finally {
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }

    }

    public static void delete(Class c) throws SQLException {

        String sql = "DELETE FROM classes WHERE id=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, c.getId());
            stmt.execute();
            
        } finally {
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
    }

    public static Class get(int id) throws SQLException {

        Class c = null;

        String sql = "SELECT * FROM classes WHERE id=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {

                c = new Class();

                c.setId(rs.getInt("id"));
                c.setStudentClassDiscipline(StudentClassDisciplineDAO.get(rs.getInt("student_class_discipline")));
                c.setDate(rs.getDate("date"));
                c.setHoursPerDay(rs.getFloat("hours_per_day"));

            }
            
        } finally {
            DatabaseUtils.close(rs);
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }

        return c;

    }
    
    public static void update(Class aClass) throws SQLException {
        
        String sql = "UPDATE classes SET student_class_discipline=?, date=?, hours_per_day=? WHERE id=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, aClass.getStudentClassDiscipline().getId());
            stmt.setDate(2, aClass.getDate());
            stmt.setFloat(3, aClass.getHoursPerDay());
            stmt.setInt(4, aClass.getId());

            stmt.execute();
            
        } finally {
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        
    }

    public static ArrayList<Class> getClassList() throws SQLException {

        ArrayList<Class> list = new ArrayList<>();

        String sql = "SELECT * FROM classes";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {

                Class c = new Class();

                c.setId(rs.getInt("id"));
                c.setStudentClassDiscipline(StudentClassDisciplineDAO.get(rs.getInt("student_class_discipline")));
                c.setDate(rs.getDate("date"));
                c.setHoursPerDay(rs.getFloat("hours_per_day"));

                list.add(c);

            }

        } finally {
            DatabaseUtils.close(rs);
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }

        return list;

    }

    public static ArrayList<Class> getClassesByStudentClassDiscipline(StudentClassDiscipline scd) throws SQLException {

        ArrayList<Class> list = new ArrayList<>();

        String sql = "SELECT * FROM classes WHERE student_class_discipline=? ORDER BY date";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, scd.getId());

            rs = stmt.executeQuery();

            while (rs.next()) {

                Class c = new Class();
                c.setId(rs.getInt("id"));
                c.setStudentClassDiscipline(StudentClassDisciplineDAO.get(rs.getInt("student_class_discipline")));
                c.setDate(rs.getDate("date"));
                c.setHoursPerDay(rs.getFloat("hours_per_day"));
                list.add(c);
            }
            
        } finally {
            DatabaseUtils.close(rs);
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }

        return list;

    }

    public static ArrayList<Class> getClassesByStudentClassDisciplineAndDate(StudentClassDiscipline scd, Date startDate, Date endDate) throws SQLException {

        ArrayList<Class> list = new ArrayList<>();

        String sql = "SELECT * FROM classes WHERE student_class_discipline=? AND date>=? AND date<=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, scd.getId());
            stmt.setDate(2, startDate);
            stmt.setDate(3, endDate);

            rs = stmt.executeQuery();

            while (rs.next()) {

                Class c = new Class();
                c.setId(rs.getInt("id"));
                c.setStudentClassDiscipline(StudentClassDisciplineDAO.get(rs.getInt("student_class_discipline")));
                c.setDate(rs.getDate("date"));
                c.setHoursPerDay(rs.getFloat("hours_per_day"));
                list.add(c);

            }
            
        } finally {
            DatabaseUtils.close(rs);
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        return list;
    }

    /**
     * Overloaded method
     * @param scd
     * @param date
     * @return 
     * @throws java.sql.SQLException
     */
    public static ArrayList<Class> getClassesByStudentClassDisciplineAndDate(StudentClassDiscipline scd, Date date) throws SQLException {
        return getClassesByStudentClassDisciplineAndDate(scd, date, date); //
    }
    
    public static ArrayList<Class> getClassesByStudentClassAndDate(StudentClass sc, Date startDate, Date endDate) throws SQLException {

        ArrayList<Class> list = new ArrayList<>();

        String sql = " SELECT * FROM "
                   + "(classes "
                   + "INNER JOIN student_class_discipline on classes.student_class_discipline=student_class_discipline.id )"
                   + "WHERE student_class_discipline.student_class=? AND classes.date>=? AND classes.date<=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            
            conn = DAO.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, sc.getCode());
            stmt.setDate(2, startDate);
            stmt.setDate(3, endDate);

            rs = stmt.executeQuery();

            while (rs.next()) {

                Class c = new Class();
                c.setId(rs.getInt("id"));
                c.setStudentClassDiscipline(StudentClassDisciplineDAO.get(rs.getInt("student_class_discipline")));
                c.setDate(rs.getDate("date"));
                c.setHoursPerDay(rs.getFloat("hours_per_day"));
                list.add(c);

            }
            
        } finally {
            DatabaseUtils.close(rs);
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
        return list;
    }
    
    public static ArrayList<Class> getClassesByStudentClassAndDate(StudentClass sc, Date date) throws SQLException {
        return getClassesByStudentClassAndDate(sc, date,date);
    }


}
