/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capalogica;

import java.sql.Date;

/**
 *
 * @author Manrique
 */
public class CLPlanPago {
    
    String rtn, 
           nombre, 
           apellidos,
           bloque, 
           casa, 
           tipoPlanPago, 
           motivoCancelacion, 
           nombreEstadoPlanPago;
    
    int idPlanPago, 
        plazo, 
        idTipoPlanPago,
        idEstadoPlanPago,
        idContrato, 
        pagado,
        num;
    
    float montoDeuda, 
          valorPlazo, 
          totalPlanPago, 
          valorPagoPlazo;
    
    Date fechaPlanPago, 
         fechaCancelacion;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }    
    

    public String getRtn() {
        return rtn;
    }

    public void setRtn(String rtn) {
        this.rtn = rtn;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getBloque() {
        return bloque;
    }

    public void setBloque(String bloque) {
        this.bloque = bloque;
    }

    public String getCasa() {
        return casa;
    }

    public void setCasa(String casa) {
        this.casa = casa;
    }

    public String getTipoPlanPago() {
        return tipoPlanPago;
    }

    public void setTipoPlanPago(String tipoPlanPago) {
        this.tipoPlanPago = tipoPlanPago;
    }

    public String getMotivoCancelacion() {
        return motivoCancelacion;
    }

    public void setMotivoCancelacion(String motivoCancelacion) {
        this.motivoCancelacion = motivoCancelacion;
    }

    public String getNombreEstadoPlanPago() {
        return nombreEstadoPlanPago;
    }

    public void setNombreEstadoPlanPago(String nombreEstadoPlanPago) {
        this.nombreEstadoPlanPago = nombreEstadoPlanPago;
    }

    public int getIdPlanPago() {
        return idPlanPago;
    }

    public void setIdPlanPago(int idPlanPago) {
        this.idPlanPago = idPlanPago;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    public int getIdTipoPlanPago() {
        return idTipoPlanPago;
    }

    public void setIdTipoPlanPago(int idTipoPlanPago) {
        this.idTipoPlanPago = idTipoPlanPago;
    }

    public int getIdEstadoPlanPago() {
        return idEstadoPlanPago;
    }

    public void setIdEstadoPlanPago(int idEstadoPlanPago) {
        this.idEstadoPlanPago = idEstadoPlanPago;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public int getPagado() {
        return pagado;
    }

    public void setPagado(int pagado) {
        this.pagado = pagado;
    }

    public float getMontoDeuda() {
        return montoDeuda;
    }

    public void setMontoDeuda(float montoDeuda) {
        this.montoDeuda = montoDeuda;
    }

    public float getValorPlazo() {
        return valorPlazo;
    }

    public void setValorPlazo(float valorPlazo) {
        this.valorPlazo = valorPlazo;
    }

    public float getTotalPlanPago() {
        return totalPlanPago;
    }

    public void setTotalPlanPago(float totalPlanPago) {
        this.totalPlanPago = totalPlanPago;
    }

    public float getValorPagoPlazo() {
        return valorPagoPlazo;
    }

    public void setValorPagoPlazo(float valorPagoPlazo) {
        this.valorPagoPlazo = valorPagoPlazo;
    }

    public Date getFechaPlanPago() {
        return fechaPlanPago;
    }

    public void setFechaPlanPago(Date fechaPlanPago) {
        this.fechaPlanPago = fechaPlanPago;
    }

    public Date getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(Date fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }
    
    
}
