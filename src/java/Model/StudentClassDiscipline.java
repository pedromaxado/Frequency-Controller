/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DataTier.ClassDAO;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Pedro
 */
public class StudentClassDiscipline {
    
    private int id;
    private StudentClass studentClass;
    private Discipline discipline;
    private float hours;
    private Date startDate;
    private Date endDate;
    private Teacher teacher;
    private SCDWeekDays weekDays=new SCDWeekDays(); // Aulas da semana que a disciplina acontece na turma.
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StudentClass getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(StudentClass studentClass) {
        this.studentClass = studentClass;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public float getHours() {
        return hours;
    }

    public void setHours(float hours) {
        this.hours = hours;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public SCDWeekDays getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(SCDWeekDays weekDays) {
        this.weekDays = weekDays;
    }
    
    public ArrayList<Class> getClassList() throws SQLException {
        return ClassDAO.getClassesByStudentClassDiscipline(this);
    }
}
