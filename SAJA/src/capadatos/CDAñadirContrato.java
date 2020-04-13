/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capadatos;

import capalogica.CLAñadirContrato;

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
public class CDAñadirContrato {
    private final Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;
    
    public CDAñadirContrato()throws SQLException{
        cn = Conexion.conectar();   
    }
    
    //METODO PARA AGREGAR UN CONTRATO //listo
    public void insertarContrato(CLAñadirContrato cla) throws SQLException{
        
        String sql ="{call sp_insertarContrato(?,?,?,?,?,?,?)}";
        
        try{
            ps = cn.prepareStatement(sql);
            ps.setString(1,cla.getNumCasa());            
            ps.setString(2,cla.getDireccionPegue());
            ps.setString(3,cla.getCodAbonado());
            ps.setString(4,cla.getTipoContrato());
            ps.setString(5,cla.getEstadoContrato());            
            ps.setString(6,cla.getBloque());
            ps.setInt(7,cla.getUsuario()); //PROBABLE VALOR GLOBAL
            ps.execute();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }  
    //METODO PARA MODIFICAR UN CONTRATO
    public void modificarContrato(CLAñadirContrato cla) throws SQLException{
        
        String sql = "{call sp_actualizarContrato(?,?,?,?,?,?,?,?)}";
        
        try{
            ps = cn.prepareStatement(sql);
            ps.setInt(1,cla.getIdContrato());
            ps.setString(2,cla.getNumCasa());            
            ps.setString(3,cla.getDireccionPegue());
            ps.setString(4,cla.getCodAbonado());
            ps.setString(5,cla.getTipoContrato());
            ps.setString(6,cla.getEstadoContrato());            
            ps.setString(7,cla.getBloque());
            ps.setInt(8,cla.getUsuario()); //PROBABLE VALOR GLOBAL
            
            ps.execute();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    //METODO PARA ELIMINAR CONTRATO
    public void eliminarContrato(CLAñadirContrato cla) throws SQLException{
        
        String sql ="{call sp_eliminarContrato(?)}";
        
        try{
            ps = cn.prepareStatement(sql);
            ps.setInt(1,cla.getIdContrato());
            ps.execute();
 
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    //ELIMINAR SERVICIOS
    public void eliminarServiciosContrato(CLAñadirContrato cla) throws SQLException{
        
        String sql ="{call sp_EliminarContratoServicio(?)}";
        
        try{
            ps = cn.prepareStatement(sql);
            ps.setInt(1,cla.getIdContratoServicio());
            ps.execute();
 
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    //lLENAR TODO //Listo
    public List<CLAñadirContrato> listaContrato() throws SQLException {
        String sql;

        sql = "{call sp_mostrarContratos()}";

        List<CLAñadirContrato> miLista = null;

        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            miLista = new ArrayList<>();

            while (rs.next()) {
                CLAñadirContrato clc = new CLAñadirContrato();

                clc.setIdContrato(rs.getInt("IdContrato"));
                clc.setAbonado(rs.getString("Abonado"));
                clc.setBloque(rs.getString("bloque"));
                clc.setNumCasa(rs.getString("NumCasa"));
                clc.setTipoContrato(rs.getString("TipoContrato"));
                clc.setEstadoContrato(rs.getString("EstadoContrato"));
                clc.setCodAbonado(rs.getString("codAbonado"));
                clc.setDireccionPegue(rs.getString("direccionPegue"));

                miLista.add(clc);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
    
    //LLENAR TABLA POR ABONADO//LISTO
    public List<CLAñadirContrato> listaContratoAbonado(String CodAbonado) throws SQLException {
        String sql;

        sql = "{call sp_mostrarContratoPorCodAbonado(?)}";

        List<CLAñadirContrato> miLista = null;

        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, CodAbonado);
            rs = ps.executeQuery();

            miLista = new ArrayList<>();

            while (rs.next()) {
                CLAñadirContrato clc = new CLAñadirContrato();

                clc.setIdContrato(rs.getInt("IdContrato"));
                clc.setAbonado(rs.getString("Abonado"));
                clc.setBloque(rs.getString("bloque"));
                clc.setNumCasa(rs.getString("NumCasa"));
                clc.setTipoContrato(rs.getString("TipoContrato"));
                clc.setEstadoContrato(rs.getString("EstadoContrato"));
                clc.setDireccionPegue(rs.getString("direccionpegue"));
                clc.setCodAbonado(rs.getString("codAbonado"));
                miLista.add(clc);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
    
    //MOSTRAR DATOS POR ESTADO DE CONTRATO
    public List<CLAñadirContrato> listaContratoEstado(String Estado) throws SQLException {
        String sql;

        sql = "{call sp_mostrarContratosPorEstado(?)}";

        List<CLAñadirContrato> miLista = null;

        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, Estado);
            rs = ps.executeQuery();

            miLista = new ArrayList<>();

            while (rs.next()) {
                CLAñadirContrato clc = new CLAñadirContrato();

                clc.setIdContrato(rs.getInt("IdContrato"));
                clc.setAbonado(rs.getString("Abonado"));
                clc.setBloque(rs.getString("bloque"));
                clc.setNumCasa(rs.getString("NumCasa"));
                clc.setTipoContrato(rs.getString("TipoContrato"));
                clc.setEstadoContrato(rs.getString("EstadoContrato"));
                clc.setDireccionPegue(rs.getString("direccionpegue"));
                clc.setCodAbonado(rs.getString("codAbonado"));
                miLista.add(clc);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
   
    //MOSTRATAR DATOS POR BLOQUE DE CONTRATO
    public List<CLAñadirContrato> ListaContratoBloque(String bloque) throws SQLException {
        String sql;

        sql = "{call sp_mostrarContratosPorBloque(?)}";

        List<CLAñadirContrato> miLista = null;

        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1,bloque);
            rs = ps.executeQuery();

            miLista = new ArrayList<>();

            while (rs.next()) {
                CLAñadirContrato clc = new CLAñadirContrato();

                clc.setIdContrato(rs.getInt("IdContrato"));
                clc.setAbonado(rs.getString("Abonado"));
                clc.setBloque(rs.getString("bloque"));
                clc.setNumCasa(rs.getString("NumCasa"));
                clc.setTipoContrato(rs.getString("TipoContrato"));
                clc.setEstadoContrato(rs.getString("EstadoContrato"));
                clc.setDireccionPegue(rs.getString("direccionpegue"));
                clc.setCodAbonado(rs.getString("codAbonado"));
                miLista.add(clc);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
    
    
    //LLENAR TABLA DE ABONADOS A CREAR CONTRATO //LISTO
    public List<CLAñadirContrato> ListaAbonados() throws SQLException {
        String sql;

        sql = "{call sp_mostrarAbonadosCyN()}";

        List<CLAñadirContrato> miLista = null;

        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            miLista = new ArrayList<>();

            while (rs.next()) {
                CLAñadirContrato cla = new CLAñadirContrato();

                cla.setCodAbonado(rs.getString("codabonado"));
                cla.setAbonado(rs.getString("NombreCompleto"));
                miLista.add(cla);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
    
    //METODO PARA BUSCAR ABONADOS EN AUTOCOMPLETADO
    public List<CLAñadirContrato> busquedaListaAbonados(String nombre) throws SQLException {
        String sql;

        sql = "{call sp_mostrarAbonadosPorNombre(?)}";

        List<CLAñadirContrato> miLista = null;

        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1,nombre);
            rs = ps.executeQuery();

            miLista = new ArrayList<>();

            while (rs.next()) {
                CLAñadirContrato cla = new CLAñadirContrato();

                cla.setCodAbonado(rs.getString("codabonado"));
                cla.setAbonado(rs.getString("NombreCompleto"));
                miLista.add(cla);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
    
    
    //METODO PARA LLENAR EL COMBOBOX TIPOCONTRATO  //LISTO
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
    
    //METODO PARA LLENAR EL COMBOBOX BLOQUE //LISTO
    public ArrayList<String> loadBloque() throws SQLException{
        String sql = "{call sp_mostrarBloque()}";

        ArrayList<String> miLista = null;

        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            miLista = new ArrayList<>();
            miLista.add("--Seleccione--");

            while (rs.next()) {
                miLista.add(rs.getString("numbloque"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

        return miLista;
    }
    
    //METODO PARA LLENAR EL COMBOBOX EstadoContrato //LISTO
    public ArrayList<String> loadEstadoContrato() throws SQLException{
        String sql = "{call sp_mostrarEstadoContrato()}";

        ArrayList<String> miLista = null;

        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            miLista = new ArrayList<>();
            miLista.add("--Seleccione--");

            while (rs.next()) {
                miLista.add(rs.getString("estadoContrato"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

        return miLista;
    }
    
    public ArrayList<String> loadEstadoContratoTablaCBO() throws SQLException{
        String sql = "{call sp_mostrarEstadoContrato()}";

        ArrayList<String> miLista = null;

        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            miLista = new ArrayList<>();
            miLista.add("--Seleccione--");
            miLista.add("Activo");

            while (rs.next()) {
                miLista.add(rs.getString("estadoContrato"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

        return miLista;
    }
    
    
    ////////////////===============================CONFIGURACION MODALS ============================//////////////////////////////////////////////
    
    // Método para llenar la tabla.
    public List<CLAñadirContrato> listaTipoContratoServicio(String TipoContrato) throws SQLException {
        String sql;

        sql = "{call sp_mostrarTipoContratoServicioPrecioPorTipoContrato(?)}";

        List<CLAñadirContrato> miLista = null;

        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, TipoContrato);
            rs = ps.executeQuery();

            miLista = new ArrayList<>();

            while (rs.next()) {
                CLAñadirContrato cl = new CLAñadirContrato();

                cl.setNombreServicio(rs.getString("NombreServicio"));
                cl.setPrecioServicio(rs.getFloat("Precio"));
                
                miLista.add(cl);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
    
    public void insertarTipoContratoPrecio(CLAñadirContrato cl) throws SQLException {

        String sql = "{call sp_insertarContratoServicio(?,?)}";

        try {
            ps = cn.prepareCall(sql);
            ps.setInt(1, cl.getIdContratoServicio());
            ps.setString(2, cl.getNombreServicio());
            

            ps.execute();
        } catch (SQLException e) {
            
        }
    }
    
    public void eliminarTipoContratoPrecio(CLAñadirContrato cl){
        String sql = "{call sp_EliminarContratoServicio(?,?)}";

        try {
            ps = cn.prepareCall(sql);
            ps.setInt(1, cl.getIdContratoServicio());
            ps.setString(2, cl.getNombreServicio());
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public int obtenerIdContrato(){
        int idContrato = 0;
        String sql = "{call sp_mostrarUltimoContrato()}";

        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                idContrato = rs.getInt("IdContrato");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

        return idContrato;
    }
   
    public List<CLAñadirContrato> busquedaContratosBloqueNumCasa(String Bloque,String numCasa) throws SQLException {
        String sql;

        sql = "{call sp_mostrarContratosPorBloqueYCasa(?,?)}";

        List<CLAñadirContrato> miLista = null;

        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1,Bloque);
            ps.setString(2,numCasa);
            rs = ps.executeQuery();

            miLista = new ArrayList<>();

            while (rs.next()) {
                CLAñadirContrato cla = new CLAñadirContrato();

                cla.setIdContrato(rs.getInt("idcontrato"));
                cla.setCodAbonado(rs.getString("codAbonado"));
                cla.setAbonado(rs.getString("nombreCompleto"));
                cla.setBloque(rs.getString("numbloque"));
                cla.setNumCasa(rs.getString("numcasa"));
                cla.setTipoContrato(rs.getString("TipoContrato"));
                cla.setEstadoContrato(rs.getString("EstadoContrato"));
                cla.setDireccionPegue(rs.getString("direccionPegue"));

                miLista.add(cla);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
    
}

