/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capadatos;

import capalogica.CLEgresos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos Aguilar
 */
public class CDEgresos {
    private final Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;
    
    public CDEgresos() throws SQLException{
        this.cn = Conexion.conectar();
    }
    
    // Método para insertar un egreso
    public void insertarControlEgreso(CLEgresos cle) throws SQLException {

        String sql = "{call sp_insertarControlEgresoFra(?,?,?,?,?)}";

        try {
            ps = cn.prepareCall(sql);
            ps.setString(1, cle.getFechaEgreso());
            ps.setFloat(2, cle.getValor());
            ps.setString(3, cle.getObservacion());
            ps.setString(4, cle.getMotivo());
            ps.setInt(5, cle.getIdUsuario());
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    // Método para modificar un egreso
    public void modificarControlEgreso(CLEgresos cle) throws SQLException{
        
        String sql = "{call sp_actualizarControlEgresoFra(?,?,?,?,?,?)}";
        
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, cle.getIdControlEgreso());
            ps.setString(2, cle.getFechaEgreso());
            ps.setFloat(3, cle.getValor());
            ps.setString(4, cle.getObservacion());
            ps.setString(5, cle.getMotivo());
            ps.setInt(6, cle.getIdUsuario());
            ps.execute();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    // Método para deshabilitar un egreso, pendiente...
    public void eliminarControlEgreso(CLEgresos cle) throws SQLException{
        
        String sql = "{call sp_eliminarControlEgreso(?)}";

        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, cle.getIdControlEgreso());
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    // Método para llenar la tabla de egresos por día
    public List<CLEgresos> mostrarEgresosHoy() throws SQLException {
        String sql;

        sql = "{call sp_mostrarEgresosHoy()}";

        List<CLEgresos> miLista = null;

        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            miLista = new ArrayList<>();

            while (rs.next()) {
                CLEgresos cle = new CLEgresos();
                cle.setIdControlEgreso(rs.getInt("idControlEgreso"));
                cle.setFechaEgreso(rs.getString("fechaEgreso"));
                cle.setValor(rs.getFloat("valor"));
                cle.setObservacion(rs.getString("observacion"));
                cle.setMotivo(rs.getString("motivo"));
                miLista.add(cle);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
    
    // Método para cargar el Combo Box  TipoEgresos
    public ArrayList<String> cargarTipoEgreso() throws SQLException{
        String sql = "{call sp_mostrarMotivo()}";

        ArrayList<String> miLista = null;

        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            miLista = new ArrayList<>();
            miLista.add("--Select--");

            while (rs.next()) {
                miLista.add(rs.getString("motivo"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

        return miLista;
    }
    
    // Método para llenar la tabla de egresos por mes
    public List<CLEgresos> mostrarEgresosMes(String mes) throws SQLException {
        String sql;

        sql = "{call sp_mostrarEgresosMes(?)}";

        List<CLEgresos> miLista = null;

        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, mes);
            rs = ps.executeQuery();

            miLista = new ArrayList<>();

            while (rs.next()) {
                CLEgresos cle = new CLEgresos();
                cle.setIdControlEgreso(rs.getInt("idControlEgreso"));
                cle.setFechaEgreso(rs.getString("fechaEgreso"));
                cle.setValor(rs.getFloat("valor"));
                cle.setObservacion(rs.getString("observacion"));
                cle.setMotivo(rs.getString("motivo"));
                miLista.add(cle);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
    
    // Método para llenar la tabla de egresos por año
    public List<CLEgresos> mostrarEgresosAño(String año) throws SQLException {
        String sql;

        sql = "{call sp_mostrarEgresosAño(?)}";

        List<CLEgresos> miLista = null;

        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, año);
            rs = ps.executeQuery();

            miLista = new ArrayList<>();

            while (rs.next()) {
                CLEgresos cle = new CLEgresos();
                cle.setIdControlEgreso(rs.getInt("idControlEgreso"));
                cle.setFechaEgreso(rs.getString("fechaEgreso"));
                cle.setValor(rs.getFloat("valor"));
                cle.setObservacion(rs.getString("observacion"));
                cle.setMotivo(rs.getString("motivo"));
                miLista.add(cle);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
    
    // Método para llenar la tabla de egresos por un rango de fecha
    public List<CLEgresos> mostrarEgresosPorRangoFecha(String fecha1, String fecha2) throws SQLException {
        String sql;

        sql = "{call sp_mostrarEgresosPorRangoFecha(?, ?)}";

        List<CLEgresos> miLista = null;

        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, fecha1);
            ps.setString(2, fecha2);
            rs = ps.executeQuery();

            miLista = new ArrayList<>();

            while (rs.next()) {
                CLEgresos cle = new CLEgresos();
                cle.setIdControlEgreso(rs.getInt("idControlEgreso"));
                cle.setFechaEgreso(rs.getString("fechaEgreso"));
                cle.setValor(rs.getFloat("valor"));
                cle.setObservacion(rs.getString("observacion"));
                cle.setMotivo(rs.getString("motivo"));
                miLista.add(cle);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
    
}
