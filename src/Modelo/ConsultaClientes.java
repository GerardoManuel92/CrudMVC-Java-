package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaClientes extends ConexionMySQL {

    public boolean registrar(Cliente cl) throws SQLException {
        PreparedStatement ps = null;
        Connection conn = getConexion();       

        String insertar = "INSERT INTO cliente (paterno, materno, nombre, CURP, RFC)"
                + "VALUES(?,?,?,?,?)";

        try {
            ps = conn.prepareStatement(insertar);
            ps.setString(1, cl.getPaterno());
            ps.setString(2, cl.getMaterno());
            ps.setString(3, cl.getNombre());
            ps.setString(4, cl.getCurp());
            ps.setString(5, cl.getRfc());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean modificar(Cliente cl) throws SQLException {
        PreparedStatement ps = null;
        Connection conn = getConexion();        

        String modificar = "UPDATE cliente SET paterno=?, materno=?, nombre=?, CURP=?, RFC=?"
                + "WHERE id_cliente=?";                

        try {
            ps = conn.prepareStatement(modificar);
            ps.setString(1, cl.getPaterno());
            ps.setString(2, cl.getMaterno());
            ps.setString(3, cl.getNombre());
            ps.setString(4, cl.getCurp());
            ps.setString(5, cl.getRfc());
            ps.setInt(6, cl.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean eliminar(Cliente cl) throws SQLException {
        PreparedStatement ps = null;
        Connection conn = getConexion();        
        String eliminar = "DELETE FROM cliente WHERE id_cliente=?";                               

        try {
            ps = conn.prepareStatement(eliminar);            
            ps.setInt(1, cl.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean buscar(Cliente cl) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = getConexion();      

        String buscar = "SELECT paterno, materno, nombre, CURP, RFC FROM cliente WHERE id_cliente=?";                

        try {
            
            ps = conn.prepareStatement(buscar);            
            ps.setInt(1, cl.getId());
            rs = ps.executeQuery();
            if(rs.next()){                
                cl.setPaterno(rs.getString("paterno"));
                cl.setMaterno(rs.getString("materno"));
                cl.setNombre(rs.getString("nombre"));
                cl.setCurp(rs.getString("CURP"));
                cl.setRfc(rs.getString("RFC")); 
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
}
