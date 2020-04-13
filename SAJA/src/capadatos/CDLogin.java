/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capadatos;

import capalogica.CLLogin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Manrique
 */
public class CDLogin {
    private final Connection cn;
    
    public CDLogin() throws SQLException{
        this.cn = Conexion.conectar();
    }   
    
    public String iniciarSesion(CLLogin ll) throws SQLException{
        String sql = "{CALL sp_login(?, ?)}";
        int idUsuario = 0;
        String nombreUsuario = null;
        
        try(PreparedStatement ps = cn.prepareStatement(sql)){
            ps.setString(1, ll.getNombreUsuario());
            ps.setString(2, ll.getContra());
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                idUsuario = rs.getInt("idUsuario");
                nombreUsuario = rs.getString("nombreEmpleado");
            }
        }
        
        return (String.valueOf(idUsuario) + "-" + nombreUsuario);
    }
    
}
