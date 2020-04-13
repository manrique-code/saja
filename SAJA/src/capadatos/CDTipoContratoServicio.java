/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capadatos;

import capalogica.CLTipoContratoServicio;
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
 * @author CLIENTE
 */
public class CDTipoContratoServicio {
        private final Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;
    
    public CDTipoContratoServicio() throws SQLException{
        this.cn = Conexion.conectar();
    }
    
    // Método para insertar el color en la BD's
    public void insertarTipoContratoPrecio(CLTipoContratoServicio cl) throws SQLException {

        String sql = "{call sp_insertarPrecioTipoContrato(?,?,?)}";

        try {
            ps = cn.prepareCall(sql);
            ps.setString(1, cl.getTipoContrato());
            ps.setString(2, cl.getNombreServicio());
            ps.setDouble(3, cl.getPrecio());

            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    // Método para actualizar el color en la base de datos.
    public void editarTipoContratoPrecio(CLTipoContratoServicio cl) throws SQLException{
        
        String sql = "{call sp_modificarPrecioTipoContrato(?,?,?)}";
        
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, cl.getTipoContrato());
            ps.setString(2, cl.getNombreServicio());
            ps.setFloat(3, cl.getPrecio());
            ps.execute();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    // Método para eliminar un color de la base de datos.
    

    
    
    // Método para llenar la tabla.
    public List<CLTipoContratoServicio> listaTipoContratoServicio() throws SQLException {
        String sql;

        sql = "{call sp_mostrarTipoContratoServicioPrecio()}";

        List<CLTipoContratoServicio> miLista = null;

        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            miLista = new ArrayList<>();

            while (rs.next()) {
                CLTipoContratoServicio cl = new CLTipoContratoServicio();

                cl.setTipoContrato(rs.getString("TipoContrato"));
                cl.setNombreServicio(rs.getString("NombreServicio"));
                cl.setPrecio(rs.getFloat("Precio"));
                cl.setFechaRegistro(rs.getString("fecharegistroPrecio"));

                miLista.add(cl);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
   
    // Método para cargar de datos el ComboBox TipoContrato.
    public ArrayList<String> loadTipoContrato() throws SQLException{
        String sql = "{call sp_mostrarTipoContrato()}";

        ArrayList<String> miLista = null;

        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            miLista = new ArrayList<>();
            miLista.add("--Seleccione--");

            while (rs.next()) {
                miLista.add(rs.getString("TipoContrato"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

        return miLista;
    }
     // Método para cargar de datos el ComboBox Servicio.
    public ArrayList<String> loadServicio() throws SQLException{
        String sql = "{call sp_mostrarServicios()}";

        ArrayList<String> miLista = null;

        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            miLista = new ArrayList<>();
            miLista.add("--Seleccione--");

            while (rs.next()) {
                miLista.add(rs.getString("NombreServicio"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

        return miLista;
    }
    
    
    public List<CLTipoContratoServicio> listaTipoContratoServicioBusqueda(String TipoContrato) throws SQLException {
        String sql;

        sql = "{call sp_mostrarTipoContratoServicioPrecioPorTipoContrato(?)}";

        List<CLTipoContratoServicio> miLista = null;

        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, TipoContrato);
            rs = ps.executeQuery();

            miLista = new ArrayList<>();

            while (rs.next()) {
                CLTipoContratoServicio cl = new CLTipoContratoServicio();
                
                cl.setTipoContrato(rs.getString("TipoContrato"));
                cl.setNombreServicio(rs.getString("NombreServicio"));
                cl.setPrecio(rs.getFloat("Precio"));
                cl.setFechaRegistro(rs.getString("FechaRegistroPrecio"));
                miLista.add(cl);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
}
