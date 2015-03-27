/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sheets;

import DataTier.AttendanceDAO;
import DataTier.ClassDAO;
import DataTier.StudentClassDAO;
import DataTier.StudentClassDisciplineDAO;
import DataTier.StudentDAO;
import Model.Attendance;
import Model.CustomDate;
import Model.Student;
import Model.StudentClass;
import Model.StudentClassDiscipline;
import Model.Class;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;

/**
 *
 * @author pedro
 */
public class GlobalSheet extends AttendanceSheet {
    
//    public static final String fileInputName    = "C:\\Users\\Usuario\\Dropbox\\Controle de Frequencia\\Frequency Controller\\SheetFiles/blankGlobal.xlsx";
//    public static final String fileOutputName   = "C:\\Users\\Usuario\\Dropbox\\Controle de Frequencia\\Frequency Controller\\SheetFiles/PlanilhaConsolidada.xlsx";
    public static final String fileInputName   = "/opt/SheetFiles/blankGlobal.xlsx";
    public static final String fileOutputName  = "/opt/SheetFiles/PlanilhaConsolidada.xlsx";

    private StudentClass studentClass;
    private int nDays;
    private int nClassDays;
    private float totalHours;
    private ArrayList<Student> students;
    private ArrayList<StudentClassDiscipline> studentClassDisciplines;

    public GlobalSheet(StudentClass studentClass,Date startDate, Date endDate) {
        super(fileInputName, fileOutputName, startDate, endDate);
        this.studentClass=studentClass;
    }

    @Override
    protected void initMembers() throws SQLException {
        this.students     = StudentDAO.getStudentListBySC(this.studentClass);
        Student.sort(students); //ordena por ordem alfabética
        this.studentClassDisciplines=StudentClassDisciplineDAO.getStudentClassDisciplinesBySC(studentClass);
        this.nDays        =   this.dates.size();
        this.nClassDays   =   0;
        this.totalHours   =   0;
    }

    @Override
    protected void createDateRows() throws SQLException {
        int currentColN=11;
        Row row1=sheet.getRow(21); //row com os "X" dos dias letivos
        Row row2=sheet.getRow(23); //row com os dias da semana
        Row row3=sheet.getRow(24); //row com as datas
        XSSFCellStyle style;
        
        for(CustomDate cDate: dates)
        {
            XSSFCell cell1=(XSSFCell) row1.getCell(currentColN);
            XSSFCell cell2=(XSSFCell) row2.getCell(currentColN);
            XSSFCell cell3=(XSSFCell) row3.getCell(currentColN);
            boolean classRegistered=false;
            for(StudentClassDiscipline studentClassDiscipline:studentClassDisciplines)
            {
                ArrayList<Class> classes=ClassDAO.getClassesByStudentClassDisciplineAndDate(studentClassDiscipline,cDate.getDate());     
                if(!classes.isEmpty()) //se pelo menos uma aula ocorreu
                {
                    classRegistered=true;
                    studentClassDiscipline.getWeekDays().addDate(cDate); //essa turma/disciplina tem aula no dia da semana de cDate
                    for(Class classs:classes)
                    {
                        this.totalHours+=classs.getHoursPerDay();                        
                    }
                }
                    
            }
            if(classRegistered)
            {
                 this.nClassDays++;
                 cell1.setCellValue("X");
            }
            
            cell2.setCellValue(" "+cDate.getWeekDay());
            cell3.setCellValue(new java.util.Date(cDate.getDate().getTime()));
            
            style=(XSSFCellStyle) cell1.getCellStyle().clone();
            XSSFColor fillBackgroundColorColor = style.getFillBackgroundColorColor();
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style.setFillForegroundColor(fillBackgroundColorColor);
            cell1.setCellStyle(style);
            
            style=(XSSFCellStyle) cell2.getCellStyle().clone();
            style.setFillPattern(FillPatternType.NO_FILL);
            cell2.setCellStyle(style);
            
            style=(XSSFCellStyle) cell3.getCellStyle().clone();
            style.setFillPattern(FillPatternType.NO_FILL);
            cell3.setCellStyle(style);
            
            currentColN++;//vai pra próxima coluna
        }
    }

    @Override
    protected void createAttendanceRows() throws SQLException {
        int currentRowN = 25;
        for (Student student : students) {
            
            ArrayList<Attendance> attendances=AttendanceDAO.getAttendancesByStudentAndStudentClassAndDate(student, studentClass, startDate,endDate);
            int currentColN=1;
            Row currentRow = sheet.getRow(currentRowN);
            currentRow.getCell(currentColN).setCellValue(student.getName());
            currentColN=11;
            for(CustomDate cDate: dates)
            {
                ArrayList<Class> classes=ClassDAO.getClassesByStudentClassAndDate(studentClass,cDate.getDate()); //pega as aulas que ocorreram naquele dia
                Attendance attendance=Attendance.findBestAttendanceByDate(attendances, cDate.getDate()); //encontra a melhor presença daquele dia (ou nao, caso nao exista)
                currentRow.getCell(currentColN++).setCellValue(Attendance.getCharPresence(attendance,classes)); //imprime o char de presença ('.' ou 'F' ou '*' ou '') 
            }
            currentRowN++;
            currentRow.getCell(38).setCellFormula("COUNTIF(L"+currentRowN+":AL"+currentRowN+",\".\")"); //presenças
            currentRow.getCell(40).setCellFormula("COUNTIF(L"+currentRowN+":AL"+currentRowN+",\"F\")"); //faltas
            currentRow.getCell(42).setCellFormula("COUNTIF(L$22:AL$22,\"X\")");                         //dias letivos
            
        }
    }

    @Override
    protected void createInfoRows() {
         createStudentClassHeader();
         createTeachersHeader();
    }
    private void createStudentClassHeader()
    {
        // Rows setup
        Row courseRow       =   sheet.getRow(3);
        Row info1Row        =   sheet.getRow(5);
        Row info2Row        =   sheet.getRow(6);
        Row info3Row        =   sheet.getRow(7);
        
        // Info cells setups
        XSSFCell CourseCell         = (XSSFCell) courseRow.getCell(0);

        XSSFCell PoloCell           = (XSSFCell) info1Row.getCell(5);
        XSSFCell SupervisorCell     = (XSSFCell) info1Row.getCell(20);
        XSSFCell ReferenceTimeCell  = (XSSFCell) info1Row.getCell(36);        

        XSSFCell HoursPerDayCell    = (XSSFCell) info2Row.getCell(8);
        XSSFCell ShiftCell          = (XSSFCell) info2Row.getCell(20);
        XSSFCell CodeCell           = (XSSFCell) info2Row.getCell(36);

        XSSFCell BeginEndCell       = (XSSFCell) info3Row.getCell(20);
        XSSFCell NDaysCell          = (XSSFCell) info3Row.getCell(39); 
        
        // Info labels
        CourseCell.setCellValue        (this.studentClass.getCourse().toUpperCase());
        PoloCell.setCellValue          (this.studentClass.getPolo().getName().toUpperCase());
        SupervisorCell.setCellValue    ("LEANDRO MAIA SILVA");
        ReferenceTimeCell.setCellValue (dateFormat.format(startDate) + " - " + dateFormat.format(endDate));
        HoursPerDayCell.setCellValue   (this.totalHours/this.nClassDays +"  horas / dia");
        ShiftCell.setCellValue         (this.studentClass.getShift().toUpperCase()+" / "+this.studentClass.getStartTime().toLocalTime().toString()+" - "+this.studentClass.getEndTime().toLocalTime().toString());
        CodeCell.setCellValue          (this.studentClass.getCode());
        BeginEndCell.setCellValue      (dateFormat.format(studentClassDisciplines.get(0).getStartDate())+" - "+dateFormat.format(studentClassDisciplines.get(0).getEndDate().getTime()));
        NDaysCell.setCellValue         (this.nDays);
    }
    private void createTeachersHeader()
    {
        int currentRowN = 10;
        for(StudentClassDiscipline studentClassDiscipline:studentClassDisciplines)
        {
            Row currentRow = sheet.getRow(currentRowN);
            currentRow.getCell(01).setCellValue(studentClassDiscipline.getTeacher().getName().toUpperCase());
            currentRow.getCell(11).setCellValue(studentClassDiscipline.getDiscipline().getName().toUpperCase());
            currentRow.getCell(26).setCellValue(studentClassDiscipline.getWeekDays().getFormatedWeekDays());
            currentRow.getCell(35).setCellValue(" "+studentClassDiscipline.getTeacher().getContact()); //É colocado um espaço antes do e-mail para evitar hiperlink
            currentRowN++;
        }

    }
    public static void main(String[] args) throws SQLException, IOException {
        StudentClass sc = StudentClassDAO.get(1);
        GlobalSheet gs = new GlobalSheet(sc, Date.valueOf("2015-01-28"), Date.valueOf("2015-02-24"));
        gs.generate();
        gs.save();
    }
    
}
