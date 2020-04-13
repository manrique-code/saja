/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capadatos;

import capalogica.CLControlPago;
import capalogica.CLLogin;
import capalogica.CLPlanPago;
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
 * @author Manrique
 */
public class CDPlanPago {
    private final Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;
    
    public CDPlanPago() throws SQLException{
        cn = Conexion.conectar();
    } 
    
    // Método para obtener la información de un contrato según el bloque y casa que le pasemos
    public List<CLPlanPago> obtenerContratoPorBloqueCasa(String numBloque, String numCasa){
        String sql;
        
        sql = "{CALL sp_mostrarContratosPorBloqueCasa(?, ?)}";
        
        List<CLPlanPago> contrato = null;
        
        try{
            ps = cn.prepareStatement(sql);
            ps.setString(1, numBloque);
            ps.setString(2, numCasa);
            rs = ps.executeQuery();
            
            contrato = new ArrayList<>();
            
            while(rs.next()){
                CLPlanPago clpp = new CLPlanPago();
                
                clpp.setNum(rs.getInt("num"));
                clpp.setIdContrato(rs.getInt("idContrato"));
                clpp.setRtn(rs.getString("codAbonado"));
                clpp.setNombre(rs.getString("nombres"));
                clpp.setApellidos(rs.getString("apellidos"));
                clpp.setBloque(rs.getString("numBloque"));
                clpp.setCasa(rs.getString("numCasa"));
                
                contrato.add(clpp);
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return contrato;
    }
    
    public List<CLPlanPago> obtenerContratoPorRtn(String numIdentidad){
        String sql;
        
        sql = "{CALL sp_mostrarContratosPorRTN(?)}";
        
        List<CLPlanPago> contrato = null;
        
        try{
            ps = cn.prepareStatement(sql);
            ps.setString(1, numIdentidad);
            rs = ps.executeQuery();
            
            contrato = new ArrayList<>();
            
            while(rs.next()){
                CLPlanPago clpp = new CLPlanPago();
                
                clpp.setNum(rs.getInt("num"));
                clpp.setIdContrato(rs.getInt("idContrato"));
                clpp.setRtn(rs.getString("codAbonado"));
                clpp.setNombre(rs.getString("nombres"));
                clpp.setApellidos(rs.getString("apellidos"));
                clpp.setBloque(rs.getString("numBloque"));
                clpp.setCasa(rs.getString("numCasa"));
                
                contrato.add(clpp);
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return contrato;
    }
    
    public List<CLPlanPago> obtenerTodosContratos(){
        String sql;
        
        sql = "{CALL sp_mostrarContratosPlanPago()}";
        
        List<CLPlanPago> contrato = null;
        
        try{
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            contrato = new ArrayList<>();
            
            while(rs.next()){
                CLPlanPago clpp = new CLPlanPago();
                
                clpp.setNum(rs.getInt("num"));
                clpp.setIdContrato(rs.getInt("idContrato"));
                clpp.setRtn(rs.getString("codAbonado"));
                clpp.setNombre(rs.getString("nombres"));
                clpp.setApellidos(rs.getString("apellidos"));
                clpp.setBloque(rs.getString("numBloque"));
                clpp.setCasa(rs.getString("numCasa"));
                
                contrato.add(clpp);
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return contrato;
    }
    
    public int aiPlanPago() throws SQLException{
        int idPlanPago = 0;
        
        String sql = "CALL sp_autoIncrementablePlanPago()";
        
        st = cn.createStatement();
        rs = st.executeQuery(sql);
        rs.first();
        
        idPlanPago = rs.getInt("idPlanPago");
        
        return idPlanPago = (idPlanPago == 0) ? 1 : idPlanPago;              
    }
    
    // Método para insertar un plan de pago
    public void insertarPlanPago(CLPlanPago clpp, CLLogin cll) throws SQLException{
        String sql = "{CALL sp_insertarPlanPago(?, ?, ?, ?, ?)}";
        
        try(PreparedStatement ps = cn.prepareCall(sql)){
            ps.setInt(1, clpp.getPlazo());
            ps.setFloat(2, clpp.getValorPlazo());
            ps.setInt(3, cll.getIdUsuario());
            ps.setInt(4, clpp.getIdTipoPlanPago());
            ps.setInt(5, clpp.getIdContrato());
            ps.execute();
        }
    }
    
    // Método para insertar un control de pago
    public void insertarControlPlanPago(CLPlanPago clpp, CLLogin cll) throws SQLException{
        String sql = "CALL sp_insertarControlPago(?, ?, ?)";
        
        try(PreparedStatement ps = cn.prepareCall(sql)){
            ps.setFloat(1, clpp.getValorPagoPlazo());
            ps.setInt(2, clpp.getIdPlanPago());
            ps.setInt(3, cll.getIdUsuario());
        }
    }  
    
    // Método para mostrar todos los tipos de planes de pago existentes en la base de datos
    public List<CLPlanPago> obtenerTodoPlanes(){
        String sql;
        
        sql = "{CALL sp_mostrarTodosPlanesPago()}";
        
        List<CLPlanPago> planPago = null;
        
        try{
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            planPago = new ArrayList<>();
            
            while(rs.next()){
                CLPlanPago clpp = new CLPlanPago();
                
                clpp.setNum(rs.getInt("num"));
                clpp.setRtn(rs.getString("codAbonado"));
                clpp.setNombre(rs.getString("nombres"));
                clpp.setApellidos(rs.getString("apellidos"));
                clpp.setBloque(rs.getString("numBloque"));
                clpp.setCasa(rs.getString("numCasa"));
                clpp.setPlazo(rs.getInt("plazo"));
                clpp.setValorPlazo(rs.getFloat("valorPago"));
                clpp.setIdPlanPago(rs.getInt("idPlanPago"));
                
                planPago.add(clpp);
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return planPago;
    }
    
    // Método para mostrar todos los planes de pago por la busqueda un abonado
    public List<CLPlanPago> obtenerTodoPlanesPagoPorAbonado(String nombreAbonado){
        String sql;
        
        sql = "{CALL sp_mostrarTodosPlanesPagoPorAbonado(?)}";
        
        List<CLPlanPago> planPago = null;
        
        try{
            ps = cn.prepareStatement(sql);
            ps.setString(1, nombreAbonado);
            rs = ps.executeQuery();
            
            planPago = new ArrayList<>();
            
            while(rs.next()){
                CLPlanPago clpp = new CLPlanPago();
                
                clpp.setNum(rs.getInt("num"));
                clpp.setRtn(rs.getString("codAbonado"));
                clpp.setNombre(rs.getString("nombres"));
                clpp.setApellidos(rs.getString("apellidos"));
                clpp.setBloque(rs.getString("numBloque"));
                clpp.setCasa(rs.getString("numCasa"));
                clpp.setPlazo(rs.getInt("plazo"));
                clpp.setValorPlazo(rs.getFloat("valorPago"));
                clpp.setIdPlanPago(rs.getInt("idPlanPago"));
                
                planPago.add(clpp);
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return planPago;
    }
    
    
}
