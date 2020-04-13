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
public class CLServicios {
    String NombreServicio;
    int IdServicio;
    int estadoServicio;

    public int getEstadoServicio() {
        return estadoServicio;
    }

    public void setEstadoServicio(int estadoServicio) {
        this.estadoServicio = estadoServicio;
    }

    public String getNombreServicio() {
        return NombreServicio;
    }

    public void setNombreServicio(String NombreServicio) {
        this.NombreServicio = NombreServicio;
    }

    public int getIdServicio() {
        return IdServicio;
    }

    public void setIdServicio(int IdServicio) {
        this.IdServicio = IdServicio;
    }
}
