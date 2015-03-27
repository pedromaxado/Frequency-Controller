/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sheets;

import Model.CustomDate;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author pedro
 */
public abstract class AttendanceSheet {
    
    protected final String          fileInputName;
    protected final String          fileOutputName;
    protected FileInputStream       inputFile;
    protected FileOutputStream      outputFile;
    protected Workbook              workbook;
    protected Sheet                 sheet;
    protected Date                  startDate;
    protected Date                  endDate;
    protected ArrayList<CustomDate> dates; //datas entre o início e o fim do período, descartando os domingos
    
    protected final static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    protected AttendanceSheet(String fileInputName,String fileOutputName,Date startDate,Date endDate)
    {
        this.fileInputName  = fileInputName;
        this.fileOutputName = fileOutputName;
        this.startDate      = startDate;
        this.endDate        = endDate;
        this.dates          = CustomDate.getDatesByRange(startDate, endDate, true);
    }
    
    private void loadInputSheet() throws IOException
    {
        this.inputFile      =   new FileInputStream(fileInputName);
        this.workbook       =   new XSSFWorkbook(this.inputFile);
        this.sheet          =   this.workbook.getSheetAt(0);
        this.inputFile.close();
    }
    
    public void generate() throws SQLException, IOException
    {
        this.loadInputSheet();
        this.initMembers();
        this.createDateRows();
        this.createAttendanceRows();
        this.createInfoRows();
    }
    
    protected abstract void initMembers() throws SQLException;

    protected abstract void createDateRows() throws SQLException;
    
    protected abstract void createAttendanceRows() throws SQLException;

    protected abstract void createInfoRows();  
    
    public void save() throws IOException 
    {      
        if (this.workbook != null) 
        {
            this.outputFile = new FileOutputStream(fileOutputName);
            this.workbook.write(this.outputFile);
            this.outputFile.close();
        }       
    }

}
