/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import DataTier.AttendanceDAO;
import DataTier.ClassDAO;
import DataTier.StudentClassDAO;
import DataTier.StudentClassDisciplineDAO;
import DataTier.StudentDAO;
import Model.Attendance;
import Model.Student;
import Model.StudentClass;
import Model.StudentClassDiscipline;
import Model.Class;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Pedro
 */
public class ExcelSheet {
    
    public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
        
        StudentClass sc=StudentClassDAO.get(1); //pega a primeira turma
        
        Date startDate=Date.valueOf("2015-01-31"); //inicio do período referência
        Date endDate=Date.valueOf("2015-02-02");   //   fim do período referência
        
        System.out.println("Turma:" +sc.getCode()+" "+sc.getCourse()+" "+sc.getShift()+" "+sc.getStartTime()+" "+sc.getEndTime());
        System.out.println("\nAlunos da turma:");
        
        ArrayList<Student> students=StudentDAO.getStudentListBySC(sc);
        
        for(Student student: students)
        {
            System.out.println(student.getName());
        }
        
        System.out.println("\nDisciplinas da turma:");
        
        ArrayList<StudentClassDiscipline> scdList=StudentClassDisciplineDAO.getStudentClassDisciplinesBySC(sc);
        
        for(StudentClassDiscipline scd:scdList)
        {
            getAttendanceByDiscipline(scd,students, startDate, endDate); //printa as presenças dessa disciplina nessa sala entre startDate e endDate
        }
        
        System.out.println("-----------------------------------------------------------");
        System.out.println("\n\nPresenças consolidadas:");
        
        for(Student student:students)
        {
            getAttendanceByStudentAndStudentClass(student, sc, startDate, endDate);
        }
        
        System.out.println("-----------------------------------------------------------");
    }
    private static void getAttendanceByDiscipline(StudentClassDiscipline scd,ArrayList<Student> students,Date startDate,Date endDate) throws SQLException
    {
        System.out.println("-----------------------------------------------------------");
        System.out.println(scd.getDiscipline().getName()+" Professor:"+scd.getTeacher().getName());
        System.out.println("\tPresenças:");
            
        Date actualDay =(Date) startDate.clone();       
        Calendar actualDayAux = new GregorianCalendar(); //variável auxiliar
        actualDayAux.setTime(startDate);                 //seta o dia
        
        while(actualDay.compareTo(endDate)<=0)
        {
            System.out.println("\t"+actualDay);
            
            ArrayList<Class> classes;
            classes=ClassDAO.getClassesByStudentClassDisciplineAndDate(scd,actualDay); //procura pela aula
            
            if(classes.isEmpty()) //se a aula não foi registrada
            {
                for(Student student:students)
                {
                     System.out.println("\t\t"+student.getName()+": *"); //asterisco significa que a aula não ocorreu ou não foi registrada
                }
            }
            else
            {
                Class classs=classes.get(0);//pega somente a primeira aula (não deveria existir mais de uma aula de uma displiplina em um mesmo dia)
                ArrayList<Attendance> attendancess=AttendanceDAO.getAttendancesByClass(classs);//pega as presenças daquela aula
                
                for(Attendance att:attendancess)
                {
                    System.out.println("\t\t"+att.getStudent().getName()+": "+att.getCharPresence());
                }
            }
            
            actualDayAux.add(Calendar.DATE, 1);//soma uma dia
            actualDay=new Date(actualDayAux.getTime().getTime());
        }
    }
    private static void getAttendanceByStudentAndStudentClass(Student student, StudentClass sc,Date startDate,Date endDate ) throws SQLException
    {
         System.out.println("-----------------------------------------------------------");
         System.out.println(student.getName()+":");         
        
         Date actualDay =(Date) startDate.clone();       
         Calendar actualDayAux = new GregorianCalendar(); //variável auxiliar
         actualDayAux.setTime(startDate);                 //seta o dia
         
         ArrayList<Attendance> globalAttendances=new ArrayList<>(); //presenças consolidadas
         
         while(actualDay.compareTo(endDate)<=0)
         {
            ArrayList<Attendance> attendances= AttendanceDAO.getAttendancesByStudentAndStudentClassAndDate(student, sc, actualDay);
            Attendance globalAttendance=new Attendance();
            Class classs= new Class();
            
            classs.setDate(actualDay);
            globalAttendance.setClass(classs);
            globalAttendance.setStudent(student);
            
            boolean present=false;
            
            for(Attendance att:attendances)
            {
                if(att.isPresent())
                {
                    present=true; 
                    break;
                }
            }
            
            globalAttendance.setPresence(present);
            
            if(attendances.isEmpty()) //se o cara não tem nenhuma presença naquele dia lançada, a aula não ocorreu
            {
                System.out.println(actualDay+": *"); //asterisco significa que a aula não ocorreu ou não foi registrada
            }
            else
            {
                System.out.println(actualDay+": "+ globalAttendance.getCharPresence());
            }
            
            actualDayAux.add(Calendar.DATE, 1);//soma uma dia
            actualDay=new Date(actualDayAux.getTime().getTime());
         }
    }
}
