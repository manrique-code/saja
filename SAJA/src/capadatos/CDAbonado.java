/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capadatos;

import capalogica.CLAbonado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Juan
 */
public class CDAbonado {
    private final Connection cn;
    

            
    
    public CDAbonado() throws SQLException{    
        this.cn = Conexion.conectar();
    }
    
    public void insertar(CLAbonado cla) throws SQLException{
        String sql = "{CALL sp_insertarAbonado(?,?,?,?,?,?,?,?)}";
        
        try(PreparedStatement ps = cn.prepareCall(sql)){
            ps.setString(1, cla.getCodAbonado());
            ps.setString(2, cla.getNombres());
            ps.setString(3, cla.getApellidos());
            ps.setString(4, cla.getFechaNacimiento());
            ps.setString(5, cla.getTelefono());
            ps.setString(6, cla.getCorreoElectronico());
            ps.setString(7, cla.getIdSexo());
            ps.setString(8, cla.getDireccion());      
            
            ps.execute();
        }
    }
    
    public void actualizarAbonado(CLAbonado cla) throws SQLException{
        String sql = "{CALL sp_modificarAbonado(?,?,?,?,?,?,?,?,?)}";
        
        try(PreparedStatement ps = cn.prepareCall(sql)){
            ps.setString(1, cla.getCodAbonado());
            ps.setString(2, cla.getCodAbonadoModificable());
            ps.setString(3, cla.getNombres());
            ps.setString(4, cla.getApellidos());
            ps.setString(5, cla.getFechaNacimiento());
            ps.setString(6, cla.getTelefono());
            ps.setString(7, cla.getCorreoElectronico());
            ps.setString(8, cla.getIdSexo());
            ps.setString(9, cla.getDireccion());    
            
            ps.execute();
        }
    }
    
    public List<CLAbonado> ListaAbonados() throws SQLException{
        String sql;
        
        sql = "{call sp_mostrarAbonados()}";
        
        List<CLAbonado> miLista;
        
        try(PreparedStatement ps = cn.prepareStatement(sql)){
            
            ResultSet rs = ps.executeQuery();
            
            miLista = new ArrayList<>();
            
            while(rs.next()){
                CLAbonado cla = new CLAbonado();
                
                cla.setCodAbonado(rs.getString("codAbonado"));
                cla.setNombres(rs.getString("nombres"));
                cla.setApellidos(rs.getString("apellidos"));
                cla.setFechaNacimiento(rs.getString("fechaNacimiento"));
                cla.setTelefono(rs.getString("telefono"));
                cla.setCorreoElectronico(rs.getString("correoElectronico"));
                cla.setSexo(rs.getString("nombreSexo"));
                cla.setDireccion(rs.getString("direccion"));
                cla.setNombreCompleto(rs.getString("nombreCompleto"));               
                
                miLista.add(cla);
            }
        }        
        return miLista;
    }
    
    public List<CLAbonado> listaContratoAbonado(String codAbonado) throws SQLException {
        String sql;

        sql = "{call sp_mostrarContratoAbonados(?)}";

        List<CLAbonado> miLista = null;

        try (PreparedStatement ps = cn.prepareCall(sql)) {
            cn.prepareStatement(sql);
            ps.setString(1, codAbonado);
            ps.executeQuery();
            
            ResultSet rs = ps.executeQuery();
            miLista = new ArrayList<>();

            while (rs.next()) {
                CLAbonado cla = new CLAbonado();

                cla.setIdContrato(rs.getInt("idContrato"));
                cla.setNumCasa(rs.getInt("numCasa"));
                cla.setBloque(rs.getInt("numBloque"));
                cla.setTipoContrato(rs.getString("tipoContrato"));
                cla.setEstadoContrato(rs.getString("estadoContrato"));
                miLista.add(cla);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
    
    public List<CLAbonado> mostrarAbonadoPorNombre(String nombres) throws SQLException {
        String sql;

        sql = "{call sp_mostrarAbonadoPorNombre(?)}";

        List<CLAbonado> miLista = null;

        try (PreparedStatement ps = cn.prepareCall(sql)) {
            cn.prepareStatement(sql);
            ps.setString(1, nombres);
            ps.executeQuery();
            
            ResultSet rs = ps.executeQuery();
            miLista = new ArrayList<>();

            while (rs.next()) {
                CLAbonado cl = new CLAbonado();

                cl.setCodAbonado(rs.getString("codAbonado"));
                cl.setNombres(rs.getString("nombres"));
                cl.setApellidos(rs.getString("apellidos"));
                cl.setFechaNacimiento(rs.getString("fechaNacimiento"));
                cl.setTelefono(rs.getString("telefono"));
                cl.setCorreoElectronico(rs.getString("correoElectronico"));
                cl.setSexo(rs.getString("nombreSexo"));
                cl.setDireccion(rs.getString("direccion"));
                
                miLista.add(cl);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
    
    public List<CLAbonado> mostrarAbonadoPorCod(String codAbonado) throws SQLException {
        String sql;

        sql = "{call sp_mostrarAbonadoPorID(?)}";

        List<CLAbonado> miLista = null;

        try (PreparedStatement ps = cn.prepareCall(sql)) {
            cn.prepareStatement(sql);
            ps.setString(1, codAbonado);
            ps.executeQuery();
            
            ResultSet rs = ps.executeQuery();
            miLista = new ArrayList<>();

            while (rs.next()) {
                CLAbonado cla = new CLAbonado();

                cla.setCodAbonado(rs.getString("codAbonado"));
                cla.setNombres(rs.getString("nombres"));
                cla.setApellidos(rs.getString("apellidos"));
                cla.setFechaNacimiento(rs.getString("fechaNacimiento"));
                cla.setTelefono(rs.getString("telefono"));
                cla.setCorreoElectronico(rs.getString("correoElectronico"));
                cla.setSexo(rs.getString("nombreSexo"));
                cla.setDireccion(rs.getString("direccion"));
                
                miLista.add(cla);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
}
