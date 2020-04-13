/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capadatos;


import capalogica.CLUsuario;
import capapresentacion.JFraUsuario;
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
public class CDUsuario {
    private final Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;
    
    public CDUsuario()throws SQLException{
        cn = Conexion.conectar();   
    }
    
    //METODO PARA INSERTAR UN EMPLEADO
    public void insertarEmpleado(CLUsuario cla) throws SQLException{
        
        String sql ="{call sp_insertarEmpleado(?,?,?,?,?)}";
        
        try{
            ps = cn.prepareStatement(sql);
            ps.setString(1,cla.getCodEmpleado());            
            ps.setString(2,cla.getNombres());
            ps.setString(3,cla.getApellidos());
            ps.setString(4,cla.getDireccion());
            ps.setString(5,cla.getTelefono());            
            ps.execute();
            
        }catch(SQLException e){
            //JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    } 
    
    //METODO PARA INSERTAR UN EMPLEADO
    public void insertarUsuario(CLUsuario cla) throws SQLException{
        
        String sql ="{call sp_insertarUsuario(?,?,?,?,?)}";
        
        try{
            ps = cn.prepareStatement(sql);
            ps.setString(1,cla.getCodEmpleado());            
            ps.setString(2,cla.getNombreUsuario());
            ps.setString(3,cla.getContraseña());
            ps.setString(4,cla.getEstadoUsuario());
            ps.setString(5,cla.getTipoUsuario());
            ps.execute();
            
        }catch(SQLException e){
            //JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    //METODO PARA MODIFICAR UN USUARIO
    public void modificarUsuario(CLUsuario cla) throws SQLException{
        
        String sql = "{call sp_actualizarUsuario(?,?,?,?,?)}";//CREAR O BUSCAR EL SP
        
        try{
            ps = cn.prepareStatement(sql);
            ps.setInt(1,cla.getIdUsuario());            
            ps.setString(2,cla.getNombreUsuario());
            ps.setString(3,cla.getContraseña());
            ps.setString(4,cla.getEstadoUsuario());
            ps.setString(5,cla.getTipoUsuario());
            
            ps.execute();
            
        }catch(SQLException e){
            //JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    //METODO PARA MODIFICAR UN EMPLEADO
    public void modificarEmpleado(CLUsuario cla) throws SQLException{
        
        String sql ="{call sp_modificarEmpleado(?,?,?,?,?)}"; ///cambiar el sp
        
        try{
            ps = cn.prepareStatement(sql);
            ps.setString(1,cla.getCodEmpleado());            
            ps.setString(2,cla.getNombres());
            ps.setString(3,cla.getApellidos());
            ps.setString(4,cla.getDireccion());
            ps.setString(5,cla.getTelefono());            
            ps.execute();
            
        }catch(SQLException e){
            //JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    } 
    
    //METODO PARA LLENAR TABLA USUARIOS
     public List<CLUsuario> ListaUsuarios() throws SQLException {
        String sql;

        sql = "{call sp_mostrarUsuario()}";

        List<CLUsuario> miLista = null;

        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            miLista = new ArrayList<>();

            while (rs.next()) {
                CLUsuario cla = new CLUsuario();
                
                cla.setIdUsuario(rs.getInt("idUsuario"));
                cla.setCodEmpleado(rs.getString("codEmpleado"));
                cla.setNombres(rs.getString("nombres"));
                cla.setApellidos(rs.getString("Apellidos"));
                cla.setNombreUsuario(rs.getString("nombreUsuario"));
                cla.setEstadoUsuario(rs.getString("estadoUsuario"));
                cla.setTipoUsuario(rs.getString("TipoUsuario"));
                cla.setDireccion(rs.getString("Direccion"));
                cla.setTelefono(rs.getString("telefono"));
                
                miLista.add(cla);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
     
     //METODO PARA BUSQUEDA EN TABLA
     public List<CLUsuario> ListaUsuariosPorCodEmpleado(String codEmpleado) throws SQLException {
        String sql;

        sql = "{call sp_mostrarUsuarioPorCodEmpleado(?)}";

        List<CLUsuario> miLista = null;

        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1,codEmpleado);
            rs = ps.executeQuery();

            miLista = new ArrayList<>();

            while (rs.next()) {
                CLUsuario cla = new CLUsuario();
                
                cla.setIdUsuario(rs.getInt("idUsuario"));
                cla.setCodEmpleado(rs.getString("codEmpleado"));
                cla.setNombres(rs.getString("nombres"));
                cla.setApellidos(rs.getString("Apellidos"));
                cla.setNombreUsuario(rs.getString("nombreUsuario"));
                cla.setEstadoUsuario(rs.getString("estadoUsuario"));
                cla.setTipoUsuario(rs.getString("TipoUsuario"));
                cla.setDireccion(rs.getString("Direccion"));
                cla.setTelefono(rs.getString("telefono"));
                
                miLista.add(cla);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
    
     //METODO PARA LLENAR EL COMBOBOX TipoUsuario //LISTO
     public ArrayList<String> cargarTipoUsuario() throws SQLException{
        String sql = "{call sp_mostrartipousuario()}";

        ArrayList<String> miLista = null;

        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            miLista = new ArrayList<>();
            miLista.add("--Seleccione--");

            while (rs.next()) {
                miLista.add(rs.getString("tipoUsuario"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

        return miLista;
    }
     
    public String verificarUsuario(CLUsuario clu) throws SQLException{
        String sql = "{CALL sp_login(?, ?)}";
        int idUsuario = 0;
        String nombreUsuario = null;
        
        try(PreparedStatement ps = cn.prepareStatement(sql)){
            ps.setString(1, clu.getAdministrador());
            ps.setString(2, clu.getContraseñaAdmin());
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                idUsuario = rs.getInt("idUsuario");
                nombreUsuario = rs.getString("nombreUsuario");
            }
        }
        if(idUsuario == 0 || nombreUsuario == ""){
            return "NoFunciona";
        }
        else{
            return "acceso";
        }
        
    }
}
