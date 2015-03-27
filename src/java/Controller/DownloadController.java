/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pedro
 */
@WebServlet("/download")
public class DownloadController extends HttpServlet {
    
    private static final int DEFAULT_BUFFER_SIZE = 8192;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) {
        
        String sheet = req.getParameter("sheet");
        String mode = req.getParameter("mode");
        
        File file = new File("/opt/SheetFiles/Planilha" + sheet + ".xlsx");
        
        res.reset();
        res.setBufferSize(DEFAULT_BUFFER_SIZE);
        res.setHeader("Content-Type", req.getServletContext().getMimeType(file.getName()));
        res.setHeader("Content-Length", String.valueOf(file.length()));
        res.setHeader("Content-Disposition", mode + "; filename=\"" + file.getName() + "\"");
        
        BufferedInputStream     input   = null;
        BufferedOutputStream    output  = null;
        
        try {
            
            input = new BufferedInputStream(new FileInputStream(file), DEFAULT_BUFFER_SIZE);
            output = new BufferedOutputStream(res.getOutputStream(), DEFAULT_BUFFER_SIZE);
            
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            
            for ( int lenght; (lenght = input.read(buffer)) > -1; ) {
                output.write(buffer, 0, lenght);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(PartialSheetController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (output != null) try { output.close(); } catch (IOException ignore) {}
            if (input  != null) try { input.close();  } catch (IOException ignore) {}
        }
        
    }
    
}
