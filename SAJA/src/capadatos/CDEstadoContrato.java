/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capadatos;

import capalogica.CLEstadoContrato;
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
 * @author Juan
 */
public class CDEstadoContrato {
    
    private final Connection cn;
    
    public CDEstadoContrato() throws SQLException{    
        this.cn = Conexion.conectar();
    }
    
    public void insertar(CLEstadoContrato clec) throws SQLException{
        String sql = "{CALL sp_insertarEstadoContrato(?)}";
        
        try(PreparedStatement ps = cn.prepareCall(sql)){
            ps.setString(1, clec.getEstadoContrato());
            ps.execute();
        }
    }
    
    public void actualizarEstadoContrato(CLEstadoContrato clec) throws SQLException{
        String sql = "{CALL sp_actualizarEstadoContrato(?, ?)}";
        
        try(PreparedStatement ps = cn.prepareCall(sql)){
            ps.setInt(1, clec.getIdEstadoContrato());
            ps.setString(2, clec.getEstadoContrato());
            
            ps.execute();
        }
    }
    
    public void eliminarEstadoContrato(CLEstadoContrato clec) throws SQLException{
        String sql = "{CALL sp_eliminarEstadoContrato(?)}";
        
        try(PreparedStatement ps = cn.prepareCall(sql)){
            ps.setInt(1, clec.getIdEstadoContrato());
            
            ps.execute();
        }
    }
    
    public int autoIncrementarEstadoContrato() throws SQLException{
        int idEstadoContrato = 0;
        
        String sql = "{CALL sp_autoIncrementableEstadoContrato()}";        
       
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rs.first();
        
        idEstadoContrato = rs.getInt("idEstadoContrato");
        
        
        if(idEstadoContrato == 0){
            idEstadoContrato = 1;
        }
        
        return idEstadoContrato;
    }  
    
    public List<CLEstadoContrato> ListaEstadoContrato() throws SQLException{
        String sql;
        
        sql = "{call sp_mostrarEstadoContrato()}";
        
        List<CLEstadoContrato> miLista;
        
        try(PreparedStatement ps = cn.prepareStatement(sql)){
            
            ResultSet rs = ps.executeQuery();
            
            miLista = new ArrayList<>();
            
            while(rs.next()){
                CLEstadoContrato clec = new CLEstadoContrato();
                
                clec.setIdEstadoContrato(rs.getInt("idEstadoContrato"));
                clec.setEstadoContrato(rs.getString("estadoContrato"));
                
                miLista.add(clec);
            }
        }        
        return miLista;
    }
    
    public List<CLEstadoContrato> mostrarEstadoContratoPorNombre(String nombre) throws SQLException {
        String sql;

        sql = "{call sp_mostrarEstadoContratoPorNombre(?)}";

        List<CLEstadoContrato> miLista = null;

        try (PreparedStatement ps = cn.prepareCall(sql)) {
            cn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.executeQuery();
            
            ResultSet rs = ps.executeQuery();
            miLista = new ArrayList<>();

            while (rs.next()) {
                CLEstadoContrato cl = new CLEstadoContrato();

                cl.setIdEstadoContrato(rs.getInt("idEstadoContrato"));
                cl.setEstadoContrato(rs.getString("estadoContrato"));
                miLista.add(cl);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
}
