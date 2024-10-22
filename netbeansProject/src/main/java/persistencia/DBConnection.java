
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author g01
 */
public class DBConnection {
    
    // class level (singleton)
    private static DBConnection theDBConnection;
    
    public static DBConnection getInstance() {
        if (theDBConnection == null) {
            Properties prop = new Properties();
            InputStream read; 
            String url;
            String user;
            String password;
            try {
                // config.db file should be copied into classpath together with DBConnection.class
                read = DBConnection.class.getResourceAsStream("config.db");
                prop.load(read);
                url = prop.getProperty("url");
                user = prop.getProperty("user");
                password = prop.getProperty("password");
                read.close(); 
                theDBConnection = new DBConnection(url, user, password);
            } catch (FileNotFoundException e) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, "DB configuration file not found.");       
            } catch (IOException e) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, "Couln't read DB configuration file.");        
            }      
        }
        return theDBConnection;
    }

    // instance level
    private Connection conn;
    private String url;
    private String user;
    private String password;

    private DBConnection(String url, String user, String password) {        
        this.url = url;
        this.user = user;
        this.password = password;
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver"); 
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, "Derby driver not found.");
        }
    }
    
    public void openConnection() {
        try {
            conn = DriverManager.getConnection(url, user, password);
        }
        catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PreparedStatement getStatement(String s) {
        PreparedStatement stmt = null;
        try {
                stmt = conn.prepareStatement(s);
        } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stmt;
    }
    
}
