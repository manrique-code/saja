/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capadatos;

import capalogica.CLServicios;
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
public class CDServicios {
    private final Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;
    
    public CDServicios() throws SQLException{
        this.cn = Conexion.conectar();
    }
    
    public void insertarServicio(CLServicios cl) throws SQLException {

        String sql = "{call sp_insertarServicio(?,?)}";

        try {
            ps = cn.prepareCall(sql);
            ps.setString(1, cl.getNombreServicio());
            ps.setInt(2,cl.getEstadoServicio());
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    // Método para actualizar el color en la base de datos.
    public void editarServicio(CLServicios cl) throws SQLException{
        
        String sql = "{call sp_actualizarServicio(?,?,?)}";
        
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, cl.getIdServicio());
            ps.setString(2, cl.getNombreServicio());
            ps.setInt(3, cl.getEstadoServicio());
            ps.execute();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    // Método para eliminar un color de la base de datos.
    public void eliminarServicio(CLServicios cl) throws SQLException{
        
        String sql = "{call sp_eliminarServicio(?)}";

        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, cl.getIdServicio());
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

    }

    // Método para consultar el ID del color que se va a almacenar.
    public int autoIncrementServicioID() throws SQLException{
        
        int IdServicio = 0;
        String sql = "{call sp_autoIncrementableServicio()}";

        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            rs.first();

            IdServicio = rs.getInt("idServicio");

            if (IdServicio == 0) {
                IdServicio = 1;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return IdServicio;
    }
   
    // Método para llenar la tabla.
    public List<CLServicios> listaServicios() throws SQLException {
        String sql;

        sql = "{call sp_mostrarServicios()}";

        List<CLServicios> miLista = null;

        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            miLista = new ArrayList<>();

            while (rs.next()) {
                CLServicios cl = new CLServicios();

                cl.setIdServicio(rs.getInt("idServicio"));
                cl.setNombreServicio(rs.getString("nombreServicio"));
                cl.setEstadoServicio(rs.getInt("Estado"));
                miLista.add(cl);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
    
    
    public List<CLServicios> listaServicioID(String nombreServicio) throws SQLException {
        String sql;

        sql = "{call sp_mostrarServicioPorNombre(?)}";

        List<CLServicios> miLista = null;

        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, nombreServicio);
            rs = ps.executeQuery();

            miLista = new ArrayList<>();

            while (rs.next()) {
                CLServicios cl = new CLServicios();

                cl.setIdServicio(rs.getInt("idServicio"));
                cl.setNombreServicio(rs.getString("nombreServicio"));
                cl.setEstadoServicio(rs.getInt("Estado"));
                miLista.add(cl);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
    
}
