/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sheets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author Usuario
 */
public class TestSheet {
    
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    
    public TestSheet() throws FileNotFoundException, IOException {
        
        FileInputStream file = new FileInputStream(new File("blank.xlsx"));
        workbook = new XSSFWorkbook(file);
        sheet = workbook.getSheetAt(0);
        
    }
    
    public void generate(){
        
        Row courseRow       =   sheet.getRow(3);
        courseRow.getCell(0).setCellValue("testando13125435");

    }
    
    public void save() {

        try {

            if (this.workbook != null) {
                FileOutputStream out = new FileOutputStream("output.xlsx");
                this.workbook.write(out);
                out.close();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PartialSheet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PartialSheet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void main(String[] args) throws IOException {
        
        TestSheet t=new TestSheet();
        t.generate();
        t.save();
        
    }
    
}
