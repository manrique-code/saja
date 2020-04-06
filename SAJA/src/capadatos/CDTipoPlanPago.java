/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capadatos;
import capalogica.CLTipoPlanPago;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author Manrique
 */
public class CDTipoPlanPago {
    
    private final Connection cn;
    
    public CDTipoPlanPago() throws SQLException {
        this.cn = Conexion.conectar();
    }
    
    public void insertarTipoPlanPago(CLTipoPlanPago cltpp) throws SQLException {
        String sql = "{call sp_insertarTipoPlanPago(?)}";
        
        try(PreparedStatement ps = cn.prepareCall(sql)) {
            ps.setString(1, cltpp.getNombreTipoPlanPago());
            ps.execute();
        }
    }
    
    public void actualizarTipoPlanPago(CLTipoPlanPago cltpp) throws SQLException {
        String sql = "{call sp_actualizarTipoPlanPago}";
        
        try(PreparedStatement ps = cn.prepareCall(sql)) {
            ps.setInt(1, cltpp.getIdTipoPlanPago());
            ps.setString(2, cltpp.getNombreTipoPlanPago());
            
            ps.execute();
        }    
    }
    
    public void eliminarTipoPlanPago(CLTipoPlanPago cltpp) throws SQLException {
        String sql = "{call sp_eliminarTipoPlanPago(?)}";
        
        try(PreparedStatement ps = cn.prepareCall(sql)) {
            ps.setInt(1, cltpp.getIdTipoPlanPago());
            
            ps.execute();
        }
    }
    
    public int aiTipoPlanPago() throws SQLException {
        int idTipoPlanPago = 0;
        
        String sql = "{call sp_autoIncrementableTipoPlanPago()}";
        
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rs.first();
        
        idTipoPlanPago = rs.getInt("idTipoPlanPago");
        
        if(idTipoPlanPago == 0) {
            idTipoPlanPago = 1;
        }
        
        return idTipoPlanPago;
       
    }
    
    public List<CLTipoPlanPago> mostrarTodoTipoPlanPago() throws SQLException {
        String sql;
        
        sql = "{call sp_mostrarTipoPlanPago}";
        
        List<CLTipoPlanPago> miLista;
        
        try(PreparedStatement ps = cn.prepareStatement(sql)){
            
            ResultSet rs = ps.executeQuery();
            
            miLista = new ArrayList<>();
            
            while(rs.next()){
                CLTipoPlanPago cltpp = new CLTipoPlanPago();
                
                cltpp.setIdTipoPlanPago(rs.getInt("idTipoPlanPago"));
                cltpp.setNombreTipoPlanPago(rs.getString("nombreTipoPlanPago"));
                
                miLista.add(cltpp);
            }
        }   
        
        return miLista;       
    }
    
    
    public List<CLTipoPlanPago> mostrarTipoPlanPagoPorID(String nombreTPP) throws SQLException{
        String sql;
        
        sql = "{call sp_mostrarTipoPlanPagoPorNombre(?)}";
        
        List<CLTipoPlanPago> miLista;
        
        try(PreparedStatement ps = cn.prepareStatement(sql)){
            ps.setString(1, nombreTPP);
            ResultSet rs = ps.executeQuery();            
            
            miLista = new ArrayList<>();
            
            while(rs.next()){
                CLTipoPlanPago cltpp = new CLTipoPlanPago();
                
                cltpp.setIdTipoPlanPago(rs.getInt("idTipoPlanPago"));
                cltpp.setNombreTipoPlanPago(rs.getString("nombreTipoPlanPago"));
                
                miLista.add(cltpp);
            }
        }        
        return miLista;
    }
    
}
