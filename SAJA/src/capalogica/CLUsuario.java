/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capalogica;

/**
 *
 * @author CLIENTE
 */
public class CLUsuario {
    //DATOS EMPLEADO
    String codEmpleado,nombres,apellidos,telefono,direccion;
    
    //DATOS USUARIO
    int idUsuario;
    String nombreUsuario,contraseña;
    String fechaRegristro;
    String EstadoUsuario;
    String TipoUsuario;
    String NombreCompleto;
    
    //DATOS DE USUARIO ADMINISTRADOR
    String Administrador;
    String ContraseñaAdmin;
    int idAdministrador;

    public String getAdministrador() {
        return Administrador;
    }

    public void setAdministrador(String Administrador) {
        this.Administrador = Administrador;
    }

    public String getContraseñaAdmin() {
        return ContraseñaAdmin;
    }

    public void setContraseñaAdmin(String ContraseñaAdmin) {
        this.ContraseñaAdmin = ContraseñaAdmin;
    }

    public int getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }
    
    
    
    
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    
    
    public String getNombreCompleto() {
        return NombreCompleto;
    }

    public void setNombreCompleto(String NombreCompleto) {
        this.NombreCompleto = NombreCompleto;
    }
    
    

    public String getEstadoUsuario() {
        return EstadoUsuario;
    }

    public void setEstadoUsuario(String EstadoUsuario) {
        this.EstadoUsuario = EstadoUsuario;
    }

    public String getCodEmpleado() {
        return codEmpleado;
    }

    public void setCodEmpleado(String codEmpleado) {
        this.codEmpleado = codEmpleado;
    }
    


    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getFechaRegristro() {
        return fechaRegristro;
    }

    public void setFechaRegristro(String fechaRegristro) {
        this.fechaRegristro = fechaRegristro;
    }

    public String getTipoUsuario() {
        return TipoUsuario;
    }

    public void setTipoUsuario(String TipoUsuario) {
        this.TipoUsuario = TipoUsuario;
    }
    
    
}
