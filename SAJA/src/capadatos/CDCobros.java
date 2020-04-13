/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capadatos;

import capalogica.CLCobros;
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
 * @author Carlos Aguilar
 */
public class CDCobros {
    private final Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;
    
    
    public CDCobros() throws SQLException{
        this.cn = Conexion.conectar();
    }
    
    public List<CLCobros> mostrarCobrosPorBloqueYCasa(String bloque, String casa) throws SQLException {
        String sql;

        sql = "{call sp_mostrarPagosPorBloqueYCasa(?,?)}";

        List<CLCobros> miLista = null;

        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, bloque);
            ps.setString(2, casa);
            rs = ps.executeQuery();

            miLista = new ArrayList<>();

            while (rs.next()) {
                CLCobros clc = new CLCobros();
                clc.setNumIdentidad(rs.getString("codAbonado"));
                clc.setNombreCompleto(rs.getString("nombreCompleto"));
                clc.setBloque(rs.getString("numBloque"));
                clc.setCasa(rs.getString("numCasa"));
                miLista.add(clc);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
    
    public List<CLCobros> mostrarCobrosPorRTN(String RTN) throws SQLException {
        String sql;

        sql = "{call sp_mostrarPagosPorRTN(?)}";

        List<CLCobros> miLista = null;

        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, RTN);
            rs = ps.executeQuery();

            miLista = new ArrayList<>();

            while (rs.next()) {
                CLCobros clc = new CLCobros();
                clc.setNumIdentidad(rs.getString("codAbonado"));
                clc.setNombreCompleto(rs.getString("nombreCompleto"));
                clc.setBloque(rs.getString("numBloque"));
                clc.setCasa(rs.getString("numCasa"));
                miLista.add(clc);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
    
    public List<CLCobros> mostrarMesesPagados(String RTN) throws SQLException {
        String sql;

        sql = "{call sp_mostrarMesesPagados(?)}";

        List<CLCobros> miLista = null;

        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, RTN);
            rs = ps.executeQuery();

            miLista = new ArrayList<>();

            while (rs.next()) {
                CLCobros clc = new CLCobros();
                clc.setMesPagado(rs.getString("nombreMes"));
                clc.setPrecio(rs.getFloat("precio"));
                miLista.add(clc);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
    
    public List<CLCobros> mostrarPagosMes(String mes) throws SQLException {
        String sql;

        sql = "{call sp_mostrarPagosMes(?)}";

        List<CLCobros> miLista = null;

        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, mes);
            rs = ps.executeQuery();

            miLista = new ArrayList<>();

            while (rs.next()) {
                CLCobros cle = new CLCobros();
                cle.setNumIdentidad(rs.getString("codAbonado"));
                cle.setNombreCompleto(rs.getString("nombres") + " " + rs.getString("apellidos"));
                cle.setBloque(rs.getString("numBloque"));
                cle.setCasa(rs.getString("numCasa"));
                cle.setTipoContrato(rs.getString("tipoContrato"));
                cle.setMes(rs.getString("nombreMes"));
                cle.setValor(rs.getFloat("valorPago"));
                miLista.add(cle);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
    
    public List<CLCobros> mostrarPagosPorRangoFecha(String fecha1, String fecha2) throws SQLException {
        String sql;

        sql = "{call sp_mostrarPagosPorRangoFecha(?, ?)}";

        List<CLCobros> miLista = null;

        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, fecha1);
            ps.setString(2, fecha2);
            rs = ps.executeQuery();

            miLista = new ArrayList<>();

            while (rs.next()) {
                CLCobros cle = new CLCobros();
                cle.setNumIdentidad(rs.getString("codAbonado"));
                cle.setNombreCompleto(rs.getString("nombres") + " " + rs.getString("apellidos"));
                cle.setBloque(rs.getString("numBloque"));
                cle.setCasa(rs.getString("numCasa"));
                cle.setTipoContrato(rs.getString("tipoContrato"));
                cle.setMes(rs.getString("nombreMes"));
                cle.setValor(rs.getFloat("valorPago"));
                miLista.add(cle);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
    
    public int numMesesPagados(String RTN) throws SQLException {
        String sql = "{call sp_numMesesPagados(?)}";
        int numMeses = 0;
           
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, RTN);
            rs = ps.executeQuery();

            while (rs.next()) {
                numMeses = rs.getInt("numPagado");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return numMeses;
    }
    
}
