/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Usuario
 */
public class DatabaseUtils {
    
    public static void close(Statement st) throws SQLException {
        
        if (st != null)
            st.close();
        
    }
    
    public static void close(Connection con) throws SQLException {
        
        if (con != null)
            con.close();
        
    }
    
    public static void close(ResultSet rs) throws SQLException {
        
        if (rs != null)
            rs.close();
        
    }   
    
}
