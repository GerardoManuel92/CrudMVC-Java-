package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
    
    private final String db = "buffete";
    private final String user = "root";
    private final String pass = "P4tr1c10";
    private final String url = "jdbc:mysql://localhost:3306/"+db;
    private Connection conn = null;
    
    public Connection getConexion() throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(this.url, this.user, this.pass);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        } 
        
        return conn;
    }
    
}
