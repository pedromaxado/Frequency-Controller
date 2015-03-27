/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DataTier.AttendanceDAO;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author Pedro
 */
public class Class {

    private int id;
    private StudentClassDiscipline studentClassDiscipline;
    private Date date;
    private float hoursPerDay;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StudentClassDiscipline getStudentClassDiscipline() {
        return studentClassDiscipline;
    }

    public void setStudentClassDiscipline(StudentClassDiscipline studentClassDiscipline) {
        this.studentClassDiscipline = studentClassDiscipline;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getHoursPerDay() {
        return hoursPerDay;
    }

    public void setHoursPerDay(float hoursPerDay) {
        this.hoursPerDay = hoursPerDay;
    }
    
    public boolean hasAttendances() throws SQLException {
        return AttendanceDAO.getAttendancesByClass(this).size() > 0;
    }
    
}
