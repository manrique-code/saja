/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capalogica;
import java.util.Date;

/**
 *
 * @author Manrique
 */
public class CLControlPago {
    
    int idControlPago, idContrato, idMes, idUsuario, numLista;
    Date fechaPago;
    float valor;
    String mesAdeudado, numBloque, numCasa, nombreCompleto, RTN;

    public String getRTN() {
        return RTN;
    }

    public void setRTN(String RTN) {
        this.RTN = RTN;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNumBloque() {
        return numBloque;
    }

    public void setNumBloque(String numBloque) {
        this.numBloque = numBloque;
    }

    public String getNumCasa() {
        return numCasa;
    }

    public void setNumCasa(String numCasa) {
        this.numCasa = numCasa;
    }
    
    public String getMesAdeudado() {
        return mesAdeudado;
    }

    public void setMesAdeudado(String mesAdeudado) {
        this.mesAdeudado = mesAdeudado;
    }    
    
    
    public int getNumLista() {
        return numLista;
    }

    public void setNumLista(int numLista) {
        this.numLista = numLista;
    }
    

    public int getIdControlPago() {
        return idControlPago;
    }

    public void setIdControlPago(int idControlPago) {
        this.idControlPago = idControlPago;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public int getIdMes() {
        return idMes;
    }

    public void setIdMes(int idMes) {
        this.idMes = idMes;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

        
    
}
