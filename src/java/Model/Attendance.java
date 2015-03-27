/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Pedro
 */
public class Attendance {
    
    private Model.Class aClass;
    private Student student;
    private boolean presence;

    public Model.Class getClasss() {
        return aClass;
    }

    public void setClass(Class studentClass) {
        this.aClass = studentClass;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public boolean isPresent() {
        return presence;
    }
    
    public boolean getPresence() {
        return isPresent();
    }

    public void setPresence(boolean presence) {
        this.presence = presence;
    }
    
    public String getCharPresence() {
        
        if(this.presence)
            return ".";
        return "F";
        
    }
    
    public static Attendance findAttendanceByDate(ArrayList<Attendance> attendances, Date date) {
        
        for(Attendance attendace:attendances)
        {
              if(attendace.getClasss().getDate().equals(date))
              {
                return attendace;
              }
        }
        return null;
        
    }
    /**
     * Encontra preferencialmente a primeira presença daquele dia onde o studante estava presente.
     * Caso ele não esteja presente, retorna o ultimo da lista daquele dia em que ele não estava presente
     * Caso não exista presença registrada naquele dia, retorna null
     * @param attendances
     * @param date
     * @return 
     */
    public static Attendance findBestAttendanceByDate(ArrayList<Attendance> attendances, Date date) {
        
        Attendance best=null;
        for(Attendance attendace:attendances)
        {
              if(attendace.getClasss().getDate().equals(date)) //achou a presença naquele dia
              {
                best=attendace; //redefine o best
                if(attendace.isPresent()) //se o cara estava presente, essa é certamente a melhor presença, então o loop para
                {
                    break;
                }
              }
        }
        return best;
    }
    
    public static String getCharPresence(Attendance att) {
        
        if(att==null)
            return "*"; //asterisco significa que o aluno não tem presença, apesar da aula registrada 
        
        return att.getCharPresence();
        
    }
    public static String getCharPresence(Attendance att,ArrayList<Class>classes){
        if(classes.isEmpty())
            return "";
        return Attendance.getCharPresence(att);
    }
    
}
