/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capadatos;

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
public class CDEtapa {
    private final Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;


    // constructor de la clase CDEtapa
    public CDEtapa() throws SQLException{
        cn = Conexion.conectar();
    }
    
    // Método para llenar el ComboBox de Etapas.
    public ArrayList<String> cargarEtapas() throws SQLException{
        String sql = "{CALL sp_mostrarEtapa()}";
        
        ArrayList<String> listaEtapas;
        
        ps = cn.prepareStatement(sql);
        rs = ps.executeQuery();
        
        listaEtapas = new ArrayList<>();
        
        while(rs.next()){
            listaEtapas.add(rs.getString("numEtapa"));
        }        
        
       return listaEtapas; 
    }
}
