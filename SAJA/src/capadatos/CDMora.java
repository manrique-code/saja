/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capadatos;

import capalogica.CLMora;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Juan
 */
public class CDMora {
    private final Connection cn;
    
    public CDMora() throws SQLException{    
        this.cn = Conexion.conectar();
    }
    
     // Metodo para llenar la tabla lista Mora
    public List<CLMora> ListaMora() throws SQLException{
        String sql;
        
        sql = "{call sp_monstrarMora()}";
        
        List<CLMora> miLista;
        
        try(PreparedStatement ps = cn.prepareStatement(sql)){
            
            ResultSet rs = ps.executeQuery();
            
            miLista = new ArrayList<>();
            
            while(rs.next()){
                CLMora clm = new CLMora();
                
                clm.setIdContrato(rs.getInt("idContrato"));
                clm.setCodAbonado(rs.getString("codAbonado"));
                clm.setNombre(rs.getString("nombreCompleto"));
                clm.setEtapa(rs.getInt("idEtapa"));
                clm.setBloque(rs.getInt("numBloque"));
                clm.setCasa(rs.getInt("numCasa"));
                clm.setMes(rs.getString("mesAdeudado"));
                clm.setValor(rs.getFloat("cantidadQueDebe"));
                
                miLista.add(clm);
            }
        }        
        return miLista;
    }
    
    // Metodo para llenar las tabla de los meses pagados
    public List<CLMora> ListaMesesPagados(int idContrato) throws SQLException {
        String sql;

        sql = "{call sp_monstrarMoraMesesPagados(?)}";

        List<CLMora> miLista = null;

        try (PreparedStatement ps = cn.prepareCall(sql)) {
            cn.prepareStatement(sql);
            ps.setInt(1, idContrato);
            ps.executeQuery();
            
            ResultSet rs = ps.executeQuery();
            miLista = new ArrayList<>();

            while (rs.next()) {
                CLMora clm = new CLMora();

                clm.setIdMes2(rs.getInt("idMes"));
                clm.setMes(rs.getString("nombreMes"));

                miLista.add(clm);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
    
    // Metodo para llenar la tabla de los meses morosos
    public List<CLMora> ListaMesesMoroso(int idContrato) throws SQLException {
        String sql;

        sql = "{call sp_monstrarMesesMorosos(?)}";

        List<CLMora> miLista = null;

        try (PreparedStatement ps = cn.prepareCall(sql)) {
            cn.prepareStatement(sql);
            ps.setInt(1, idContrato);
            ps.executeQuery();
            
            ResultSet rs = ps.executeQuery();
            miLista = new ArrayList<>();

            while (rs.next()) {
                CLMora clm = new CLMora();

                clm.setIdMes2(rs.getInt("idMes"));
                clm.setMes(rs.getString("nombreMes"));

                miLista.add(clm);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
    
    // Metodo para llenar la tabla de valor de los meses morosos
    public List<CLMora> ListaValorMesesMorosos(int idContrato) throws SQLException {
        String sql;

        sql = "{call sp_monstrarMoraTotal(?)}";

        List<CLMora> miLista = null;

        try (PreparedStatement ps = cn.prepareCall(sql)) {
            cn.prepareStatement(sql);
            ps.setInt(1, idContrato);
            ps.executeQuery();
            
            ResultSet rs = ps.executeQuery();
            miLista = new ArrayList<>();

            while (rs.next()) {
                CLMora clm = new CLMora();

                clm.setIdContrato(rs.getInt("idContrato"));
                clm.setMes(rs.getString("nombreMes"));
                clm.setValor(rs.getDouble("valor"));

                miLista.add(clm);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
    
    // Metodo para mostrar abonados por bloque
    public List<CLMora> mostrarMoraPorBloque(int bloque) throws SQLException {
        String sql;

        sql = "{call sp_monstrarMoraPorBloque(?)}";

        List<CLMora> miLista = null;

        try (PreparedStatement ps = cn.prepareCall(sql)) {
            cn.prepareStatement(sql);
            ps.setInt(1, bloque);
            ps.executeQuery();
            
            ResultSet rs = ps.executeQuery();
            miLista = new ArrayList<>();

            while (rs.next()) {
                CLMora clm = new CLMora();

                clm.setIdContrato(rs.getInt("idContrato"));
                clm.setCodAbonado(rs.getString("codAbonado"));
                clm.setNombre(rs.getString("nombreCompleto"));
                clm.setBloque(rs.getInt("numBloque"));
                clm.setEtapa(rs.getInt("idEtapa"));
                clm.setCasa(rs.getInt("numCasa"));
                clm.setMes(rs.getString("mesAdeudado"));
                clm.setValor(rs.getFloat("cantidadQueDebe"));
                
                miLista.add(clm);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
    
    // Metodo para mostrar aponador por etapa
    public List<CLMora> mostrarMoraPorEtapa(int etapa) throws SQLException {
        String sql;

        sql = "{call sp_monstrarMoraPorEtapa(?)}";

        List<CLMora> miLista = null;

        try (PreparedStatement ps = cn.prepareCall(sql)) {
            cn.prepareStatement(sql);
            ps.setInt(1, etapa);
            ps.executeQuery();
            
            ResultSet rs = ps.executeQuery();
            miLista = new ArrayList<>();

            while (rs.next()) {
                CLMora clm = new CLMora();

                clm.setIdContrato(rs.getInt("idContrato"));
                clm.setCodAbonado(rs.getString("codAbonado"));
                clm.setNombre(rs.getString("nombreCompleto"));
                clm.setBloque(rs.getInt("numBloque"));
                clm.setEtapa(rs.getInt("idEtapa"));
                clm.setCasa(rs.getInt("numCasa"));
                clm.setMes(rs.getString("mesAdeudado"));
                clm.setValor(rs.getFloat("cantidadQueDebe"));
                
                miLista.add(clm);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
    
    // Metodo para el total a pagar por los meses morosos
    public double mostrarMoraTotalPagar(int idContrato) throws SQLException {
        String sql;

        sql = "{call sp_monstrarMoraTotalPagar(?)}";
        double total = 0;

        try (PreparedStatement ps = cn.prepareCall(sql)) {
            cn.prepareStatement(sql);
            ps.setInt(1, idContrato);
            ps.executeQuery();  
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                total = rs.getDouble("valor");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return total;
    }  
}
