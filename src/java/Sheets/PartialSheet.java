/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sheets;

import DataTier.AttendanceDAO;
import DataTier.ClassDAO;
import DataTier.StudentClassDisciplineDAO;
import DataTier.StudentDAO;
import Model.Attendance;
import Model.CustomDate;
import Model.Student;
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
public class PartialSheet extends AttendanceSheet {

    /*

     ASCI Art da planilha parcial...
     _________________________________________________________________________
     |                                   |                   |                 |
     | ---TABULAÇÃO DE FREQUENCIA------- | --LOGO PRONATEC-- | --LOGO COLTEC-- |
     |___________________________________|___________________|_________________|
     |                                                                         |
     |                          Técnico subsequente em                         |
     |_________________________________________________________________________|
     |                                                                         |
     | ---------------------------------CURSO----------------------------------|
     |_________________________________________________________________________|
     |                                                                         |
     |-------DISCIPLINA------|--------PROFESSOR--------|--PERIODO REFERENCIA---|
     |-----POLO/MUNICIPIO----|--------SUPERVISOR-------|--------CODIGO---------|
     |---CARGA HORARIO/DIA---|------TURNO/HORARIO------|--------TURMA----------|
     |--CARGA HORARIO TOTAL--|------INICIO-TERMINO-----|------DIAS LETIVOS-----|
     |_________________________________________________________________________|
     | |                                                           |           |
     | |-MARQUE COM UM X OS DIAS LETIVOS-|X| |X| |X| |X| |X| |X| |X|           |
     |_|___________________________________________________________|___________|
     |                                                                         |
     |                         AQUI COMEÇA O INFERNO.                          |
     |_________________________________________________________________________|
     |                                                                         |
     | - Este documento não deve possuir rasuras.                              |
     | - Utilize um ponto(.) para marcar a presença e a letra F para faltas.   |
     | - Utilize as letras (AT) para sinalizar algum atestado apresentado.     |
     | - Marque com um asterisco(*) os dias LETIVOS quer não houveram aula.    |
     |_________________________________________________________________________|
     |                                                                         |
     | - OBSERVAÇÕES                                                           |
     | -                                                                       |
     | -                                                                       |
     | -                                                                       |
     | -                                                                       |
     |_________________________________________________________________________|

     */
    
    public static final String fileInputName    = "C:\\Users\\Usuario\\Dropbox\\Controle de Frequencia\\Frequency Controller\\SheetFiles\\blankParcial.xlsx";
    public static final String fileOutputName   = "C:\\Users\\Usuario\\Dropbox\\Controle de Frequencia\\Frequency Controller\\SheetFiles\\PlanilhaParcial.xlsx";
    //public static final String fileInputName   = "/opt/SheetFiles/blankParcial.xlsx";
    //public static final String fileOutputName  = "/opt/SheetFiles/PlanilhaParcial.xlsx";
    private StudentClassDiscipline studentClassDiscipline;
    private int nDays;
    private int nClassDays;
    private float totalHours;
    private ArrayList<Student> students;
    
    public PartialSheet(StudentClassDiscipline studentClassDiscipline, Date startDate, Date endDate){
        super(fileInputName, fileOutputName,startDate, endDate);
        this.studentClassDiscipline=studentClassDiscipline;
    }
    @Override
    protected void initMembers() throws SQLException {
        this.students     = StudentDAO.getStudentListBySC(this.studentClassDiscipline.getStudentClass());
        Student.sort(students); //ordena por ordem alfabética
        this.nDays        =   this.dates.size();
        this.nClassDays   =   0;
        this.totalHours   =   0;
    }
    /**
     * Cria as linhas das datas
     * @throws java.sql.SQLException
     */
    @Override
    protected void createDateRows() throws SQLException
    {
        int currentColN=11;
        Row row1=sheet.getRow(10); //row com os "X" dos dias letivos
        Row row2=sheet.getRow(12); //row com os dias da semana
        Row row3=sheet.getRow(13); //row com as datas
        XSSFCellStyle style;
        
        for(CustomDate cDate: dates)
        {
            ArrayList<Class> classes=ClassDAO.getClassesByStudentClassDisciplineAndDate(studentClassDiscipline,cDate.getDate());
            XSSFCell cell1=(XSSFCell) row1.getCell(currentColN);
            XSSFCell cell2=(XSSFCell) row2.getCell(currentColN);
            XSSFCell cell3=(XSSFCell) row3.getCell(currentColN);
            
            if(!classes.isEmpty())
            {
                Class classs=classes.get(0);//pega somente a primeira aula (não deveria existir mais de uma aula de uma displiplina em um mesmo dia)
                this.totalHours+=classs.getHoursPerDay();
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
    protected void createAttendanceRows() throws SQLException
    {
        int currentRowN = 14;
        for (Student student : students) {
            
            ArrayList<Attendance> attendances=AttendanceDAO.getAttendancesByStudentAndStudentClassDisciplineAndDate(student, studentClassDiscipline,startDate, endDate); 
            int currentColN=1;
            Row currentRow = sheet.getRow(currentRowN);
            currentRow.getCell(currentColN).setCellValue(student.getName());
            currentColN=11;
            for(CustomDate cDate: dates)
            {
                ArrayList<Class> classes=ClassDAO.getClassesByStudentClassDisciplineAndDate(studentClassDiscipline,cDate.getDate()); //pega as aulas que ocorreram naquele dia
                Attendance attendance=Attendance.findAttendanceByDate(attendances, cDate.getDate()); //encontra a presença daquele dia (ou nao, caso nao exista)
                currentRow.getCell(currentColN++).setCellValue(Attendance.getCharPresence(attendance,classes)); //imprime o char de presença ('.' ou 'F' ou '*' ou '') 
            }
            currentRowN++;
            currentRow.getCell(38).setCellFormula("COUNTIF(L"+currentRowN+":AL"+currentRowN+",\".\")"); //presenças
            currentRow.getCell(40).setCellFormula("COUNTIF(L"+currentRowN+":AL"+currentRowN+",\"F\")"); //faltas
            currentRow.getCell(42).setCellFormula("COUNTIF(L$11:AL$11,\"X\")");                             //dias letivos
            
        }
        
    }
    
    @Override
    protected void createInfoRows()
    {
        // Rows setup
        Row courseRow       =   sheet.getRow(3);
        Row info1Row        =   sheet.getRow(5);
        Row info2Row        =   sheet.getRow(6);
        Row info3Row        =   sheet.getRow(7);
        Row info4Row        =   sheet.getRow(8);   
        
        // Info cells setups
        XSSFCell CourseCell         = (XSSFCell) courseRow.getCell(0);

        XSSFCell DisciplineCell     = (XSSFCell) info1Row.getCell(5);
        XSSFCell TeacherCell        = (XSSFCell) info1Row.getCell(20);
        XSSFCell ReferenceTimeCell  = (XSSFCell) info1Row.getCell(36);
        
        XSSFCell PoloCell           = (XSSFCell) info2Row.getCell(5);
        XSSFCell SupervisorCell     = (XSSFCell) info2Row.getCell(20);
        XSSFCell CodeCell           = (XSSFCell) info2Row.getCell(36);

        XSSFCell HoursPerDayCell    = (XSSFCell) info3Row.getCell(8);
        XSSFCell ShiftCell          = (XSSFCell) info3Row.getCell(20);
        XSSFCell StudentClassCell   = (XSSFCell) info3Row.getCell(36);

        XSSFCell TotalHoursCell     = (XSSFCell) info4Row.getCell(8);
        XSSFCell BeginEndCell       = (XSSFCell) info4Row.getCell(20);
        XSSFCell NDaysCell          = (XSSFCell) info4Row.getCell(39);        
   
        // Info labels
        CourseCell          .setCellValue   (this.studentClassDiscipline.getStudentClass().getCourse().toUpperCase());          
        DisciplineCell      .setCellValue   (this.studentClassDiscipline.getDiscipline().getName().toUpperCase()); 
        TeacherCell         .setCellValue   (this.studentClassDiscipline.getTeacher().getName().toUpperCase());    
        ReferenceTimeCell   .setCellValue   (dateFormat.format(startDate) + "-" + dateFormat.format(endDate));
        PoloCell            .setCellValue   (this.studentClassDiscipline.getStudentClass().getPolo().getName().toUpperCase());
        SupervisorCell      .setCellValue   ("LEANDRO MAIA SILVA");
        CodeCell            .setCellValue   (this.studentClassDiscipline.getStudentClass().getCode());
        HoursPerDayCell     .setCellValue   (this.totalHours/this.nClassDays +"  horas / dia");
        ShiftCell           .setCellValue   (this.studentClassDiscipline.getStudentClass().getShift().toUpperCase());
        StudentClassCell    .setCellValue   (this.studentClassDiscipline.getStudentClass().getPolo().getName().toUpperCase());
        TotalHoursCell      .setCellValue   (this.totalHours+" horas");
        BeginEndCell        .setCellValue   (dateFormat.format(this.studentClassDiscipline.getStartDate())+" - "+dateFormat.format(this.studentClassDiscipline.getEndDate().getTime()));
        NDaysCell           .setCellValue   (this.nDays);
        
    }

    public static void main(String[] args) throws SQLException, IOException {

        StudentClassDiscipline scd = StudentClassDisciplineDAO.get(1);
        PartialSheet ps = new PartialSheet(scd, Date.valueOf("2015-01-28"), Date.valueOf("2015-02-24"));
        ps.generate();
        ps.save();
        
    }
    
}
