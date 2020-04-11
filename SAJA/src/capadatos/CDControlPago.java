/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capadatos;

import capalogica.CLAbonado;
import capalogica.CLContrato;
import capalogica.CLControlPago;
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
public class CDControlPago {
    private final Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;
    
    public CDControlPago() throws SQLException{
        cn = Conexion.conectar();
    }
    
    // Método para llenar la tabla

    /**
     *
     * @return
     * @throws SQLException
     */
    
    // Recibe los parametros de etapa y la cantidad de meses que no estan permitidos deber.
    public List<CLControlPago> obtenerListadoCorte(int idEtapa, int mesesA) throws SQLException{
        String sql;
        
        sql = "{CALL sp_mostrarListadoCorte(?, ?)}";
        
        List<CLControlPago> listaCorte = null;
        
        try{
            ps = cn.prepareStatement(sql);
            ps.setInt(1, idEtapa);
            ps.setInt(2, mesesA);
            rs = ps.executeQuery();
            
            listaCorte = new ArrayList<>();
            
            while(rs.next()){
                CLControlPago clcp = new CLControlPago();
                
                clcp.setNumLista(rs.getInt("num"));
                clcp.setNumBloque(rs.getString("numBloque"));
                clcp.setNumCasa(rs.getString("numCasa"));
                clcp.setRTN(rs.getString("codAbonado"));
                clcp.setNombreCompleto(rs.getString("nombreCompleto"));
                clcp.setMesAdeudado(rs.getString("mesAdeudado"));
                
                listaCorte.add(clcp);                
            }            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        
        return listaCorte;
    }
    
    // Método para mostrar los datos de la tabla según la busqueda de bloque
    public List<CLControlPago> obtenerListadoCortePorBloque(int idEtapa, int mesesA, String idBloque) throws SQLException{
        String sql;
        
        sql = "{CALL sp_mostrarListadoCortePorBloque(?, ?, ?)}";
        
        List<CLControlPago> listaCorte = null;   

        try{            
            ps = cn.prepareStatement(sql);
            ps.setInt(1, idEtapa);
            ps.setInt(2, mesesA);
            ps.setString(3, idBloque);
            rs = ps.executeQuery();

            listaCorte = new ArrayList<>();

            while (rs.next()) {
                CLControlPago clcp = new CLControlPago();

                clcp.setNumLista(rs.getInt("num"));
                clcp.setNumBloque(rs.getString("numBloque"));
                clcp.setNumCasa(rs.getString("numCasa"));
                clcp.setRTN(rs.getString("codAbonado"));
                clcp.setNombreCompleto(rs.getString("nombreCompleto"));
                clcp.setMesAdeudado(rs.getString("mesAdeudado"));

                listaCorte.add(clcp);
            }             
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        
        return listaCorte;
    }
    
    // Método para mostrar los datos de las tablas según la busqueda de número de identidad
    public List<CLControlPago> obtenerListadoCortePorRTN(int idEtapa, int mesesA, String RTN) throws SQLException{
        String sql;
        
        sql = "{CALL sp_mostrarListadoCortePorAbonado(?, ?, ?)}";
        
        List<CLControlPago> listaCorte = null;   

        try{            
            ps = cn.prepareStatement(sql);
            ps.setInt(1, idEtapa);
            ps.setInt(2, mesesA);
            ps.setString(3, RTN);
            rs = ps.executeQuery();

            listaCorte = new ArrayList<>();

            while (rs.next()) {
                CLControlPago clcp = new CLControlPago();

                clcp.setNumLista(rs.getInt("num"));
                clcp.setNumBloque(rs.getString("numBloque"));
                clcp.setNumCasa(rs.getString("numCasa"));
                clcp.setRTN(rs.getString("codAbonado"));
                clcp.setNombreCompleto(rs.getString("nombreCompleto"));
                clcp.setMesAdeudado(rs.getString("mesAdeudado"));

                listaCorte.add(clcp);
            }             
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        
        return listaCorte;
    }
        
}
