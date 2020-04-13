/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capadatos;

import capalogica.CLTipoContrato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos Aguilar
 */
public class CDTipoContrato {
    private final Connection cn;
    
    public CDTipoContrato() throws SQLException{
        this.cn = Conexion.conectar();
    }
    
    public void insertarTipoContrato(CLTipoContrato cltc) throws SQLException{
         String sql = "{CALL sp_insertarTipoContrato(?)}";
        
        try(PreparedStatement ps = cn.prepareCall(sql)){
            ps.setString(1, cltc.getTipoContrato());
            ps.execute();
        }
    }
    
     public void modificarTipoContrato(CLTipoContrato cltc) throws SQLException{
        String sql = "{CALL sp_modificarTipoContrato(?, ?)}";
        
        try(PreparedStatement ps = cn.prepareCall(sql)){
            ps.setInt(1, cltc.getIDTipoContrato());
            ps.setString(2, cltc.getTipoContrato());
            
            ps.execute();
        }
    }
    
    public void eliminarTipoContrato(CLTipoContrato cltc) throws SQLException{
        String sql = "{CALL sp_eliminarTipoContrato(?)}";
        
        try(PreparedStatement ps = cn.prepareCall(sql)){
            ps.setInt(1, cltc.getIDTipoContrato());
            
            ps.execute();
        }
    }
    
    public int autoIncrementableIDTipoContrato() throws SQLException{
        int idTipoContrato = 0;
        
        String sql = "{CALL sp_autoIncrementableTipoContrato()}";        
       
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rs.first();
        
        idTipoContrato = rs.getInt("idTipoContrato");
        
        
        if(idTipoContrato == 0){
            idTipoContrato = 1;
        }
        
        return idTipoContrato;
    }
    
    public List<CLTipoContrato> obtenerListaTipoContrato() throws SQLException{
        String sql;
        
        sql = "{call sp_mostrarTipoContrato()}";
        
        List<CLTipoContrato> miLista;
        
        try(PreparedStatement ps = cn.prepareStatement(sql)){
            
            ResultSet rs = ps.executeQuery();
            
            miLista = new ArrayList<>();
            
            while(rs.next()){
                CLTipoContrato cltc = new CLTipoContrato();
                
                cltc.setIDTipoContrato(rs.getInt("idTipoContrato"));
                cltc.setTipoContrato(rs.getString("tipoContrato"));
                
                miLista.add(cltc);
            }
        }        
        return miLista;
    }
    
    public List<CLTipoContrato> obtenerListaNombreTipoContrato(String nombreTipoContrato) throws SQLException{
        String sql;
        
        sql = "{call sp_mostrarTipoContratoPorNombre(?)}";
        
        List<CLTipoContrato> miLista;
        
        try(PreparedStatement ps = cn.prepareStatement(sql)){
            
            ps.setString(1, nombreTipoContrato);
            ResultSet rs = ps.executeQuery();            
            
            miLista = new ArrayList<>();
            
            while(rs.next()){
                CLTipoContrato cltc = new CLTipoContrato();
                
                cltc.setIDTipoContrato(rs.getInt("idTipoContrato"));
                cltc.setTipoContrato(rs.getString("tipoContrato"));
                
                miLista.add(cltc);
            }
        }        
        return miLista;
    }
}
