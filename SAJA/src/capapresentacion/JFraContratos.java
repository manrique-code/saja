/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capapresentacion;

import AppPackage.AnimationClass;
import capadatos.CDAñadirContrato;
import capalogica.CLAbonado;
import capalogica.CLAñadirContrato;
import java.awt.Color;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Frame;

/**
 *
 * @author Manrique
 */
public class JFraContratos extends javax.swing.JFrame {

    /**
     * Creates new form jFraAñadirContrato
     */
    
    //;
    
    public JFraContratos() throws SQLException {
        initComponents();
        //new CLAñadirContrato();
        llenarTablaAbonados();
        llenarTablaContratos();
        cargarComboTipoContrato();
        cargarEstadoContrato();
        cargarComboBloque();
        cargarComboBloqueVerContratos();
        cargarEstadoContratoVerContratos();
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(null);
        this.jDialogServicios.setLocationRelativeTo(null);
        this.jDialogBusquedaContratos.setLocationRelativeTo(null);
        this.jSideBar1.getViewport().setBackground(Color.getColor("52, 74, 94"));
        this.jBtnSideBar1.setVisible(false);
        this.jCBEstadoContrato.setEnabled(false);
        this.jCboModificarEstado.setVisible(false);
        this.jBtnEditar.setVisible(false);
        EnabledBTN(true,false);
        this.jCBBloqueItem.setVisible(false);
        this.jCBEstadoItem.setVisible(false);
        this.jPnlBuscar6.setVisible(false);
        this.jCBTipoContrato.setSelectedIndex(1);
        this.jLblIdContrato.setVisible(false);
        this.jLblCodAbonado.setVisible(false);
        this.jTfBusquedaNumIdentidad.setVisible(false);
        this.jLblIdentidadBusqueda.setVisible(false);
    }
    AnimationClass sideBar = new AnimationClass();
    
    // Instancia del menú
    JFraMenu jfm = new JFraMenu();
    
    private void EnabledBTN(boolean Insertar,boolean Modificar ){
        this.jBtnGuardar.setEnabled(Insertar);
        this.jBtnEditar.setEnabled(Modificar);
                 
    }
    
    public void mostrarVentana(boolean mostrar, 
                               String nombreAbonado,
                               String numeroIdentidad,
                               String codContrato,
                               String bloque,
                               String casa,
                               String tipoContrato,
                               String estadoContrato,
                               String direccionPegue){
        if(mostrar){
            this.jTabContrato.setSelectedIndex(2);
            jLblNombreAbonado.setText(nombreAbonado);
            jLblNumeroIdentidad.setText(numeroIdentidad);
            jTfInfoIdContrato.setText(codContrato);
            jTfInfoBloque.setText(bloque);
            jTfInfoNumCasa.setText(casa);
            jTfInfoTipoContrato.setText(tipoContrato);
            jTfInfoEstadoContrato.setText(estadoContrato);
            jTextAreaInfoDireccion.setText(direccionPegue);
        }
    }
    
    // Colores del formulario
    Color celeste = new Color(52,152,219);
    Color azul = new Color(52,73,94);
    //LLENAR TABLA ABONADOS
    private void llenarTablaAbonados() throws SQLException  {
        limpiarTableAbonado();
        CDAñadirContrato cda = new CDAñadirContrato();
        List<CLAñadirContrato> miLista;
        miLista = cda.ListaAbonados();
        DefaultTableModel temp = (DefaultTableModel) this.jTblAbonados.getModel();

        miLista.stream().map((cla) -> {
            Object[] fila = new Object[2];
            fila[0] = cla.getCodAbonado();
            fila[1] = cla.getAbonado();
            return fila;
        }).forEachOrdered((fila) -> {
            temp.addRow(fila);
        });
    }
    private void busquedaListaAbonados(String abonado) throws SQLException{
        limpiarTableAbonado();
        CDAñadirContrato cda = new CDAñadirContrato();
        List<CLAñadirContrato> miLista;
        miLista = cda.busquedaListaAbonados(abonado);
        DefaultTableModel temp = (DefaultTableModel) this.jTblAbonados.getModel();

        miLista.stream().map((cla) -> {
            Object[] fila = new Object[2];
            fila[0] = cla.getCodAbonado();
            fila[1] = cla.getAbonado();
            return fila;
        }).forEachOrdered((fila) -> {
            temp.addRow(fila);
        });
    }
    
    private void limpiarTableAbonado(){
        DefaultTableModel dtm = (DefaultTableModel) this.jTblAbonados.getModel();
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
    }
    
    //LLENAR LA TABLA CON TODOS LOS REGISTROS //LISTO
    private void llenarTablaContratos() throws SQLException  {
        clearTable();

        CDAñadirContrato cda = new CDAñadirContrato();
        List<CLAñadirContrato> miLista;
        miLista = cda.listaContrato();
        DefaultTableModel temp = (DefaultTableModel) this.jTblContratos.getModel();

        miLista.stream().map((cla) -> {
            Object[] fila = new Object[8];
            fila[0] = cla.getIdContrato();
            fila[1] = cla.getAbonado();            
            fila[2] = cla.getBloque();
            fila[3] = cla.getNumCasa();
            fila[4] = cla.getTipoContrato();
            fila[5] = cla.getEstadoContrato();
            fila[6] = cla.getCodAbonado();
            fila[7] = cla.getDireccionPegue();


            return fila;
        }).forEachOrdered((fila) -> {
            temp.addRow(fila);
        });
    }
    
    //LLENAR LA TABLA CON BUSQUEDA DE CODIGO DE ABONADOS
    private void llenarTablaContratosCod(String cod) throws SQLException  {
        clearTable();

        CDAñadirContrato cda = new CDAñadirContrato();
        List<CLAñadirContrato> miLista;
        miLista = cda.listaContratoAbonado(cod);
        DefaultTableModel temp = (DefaultTableModel) this.jTblContratos.getModel();

        miLista.stream().map((cla) -> {
            Object[] fila = new Object[8];
            fila[0] = cla.getIdContrato();
            fila[1] = cla.getAbonado();            
            fila[2] = cla.getBloque();
            fila[3] = cla.getNumCasa();
            fila[4] = cla.getTipoContrato();
            fila[5] = cla.getEstadoContrato();
            fila[6] = cla.getCodAbonado();
            fila[7] = cla.getDireccionPegue();

            return fila;
        }).forEachOrdered((fila) -> {
            temp.addRow(fila);
        });
    }
    
    //LLENAR LA TABLA POR ESTADO ESPECIFICO DE UN CONTRATO
    private void llenarTablaContratosEstado(String estado) throws SQLException  {
        clearTable();

        CDAñadirContrato cda = new CDAñadirContrato();
        List<CLAñadirContrato> miLista;
        miLista = cda.listaContratoEstado(estado);
        DefaultTableModel temp = (DefaultTableModel) this.jTblContratos.getModel();

        miLista.stream().map((cla) -> {
            Object[] fila = new Object[8];
            fila[0] = cla.getIdContrato();
            fila[1] = cla.getAbonado();            
            fila[2] = cla.getBloque();
            fila[3] = cla.getNumCasa();
            fila[4] = cla.getTipoContrato();
            fila[5] = cla.getEstadoContrato();
            fila[6] = cla.getCodAbonado();
            fila[7] = cla.getDireccionPegue();

            return fila;
        }).forEachOrdered((fila) -> {
            temp.addRow(fila);
        });
    }
    //LLENAR LA TABLA POR BLOQUE ESPECIFICO DE UN CONTRATO
    private void llenarTablaContratosBloque(String bloque) throws SQLException  {
        clearTable();

        CDAñadirContrato cda = new CDAñadirContrato();
        List<CLAñadirContrato> miLista;
        miLista = cda.ListaContratoBloque(bloque);
        DefaultTableModel temp = (DefaultTableModel) this.jTblContratos.getModel();

        miLista.stream().map((cla) -> {
            Object[] fila = new Object[8];
            fila[0] = cla.getIdContrato();
            fila[1] = cla.getAbonado();            
            fila[2] = cla.getBloque();
            fila[3] = cla.getNumCasa();
            fila[4] = cla.getTipoContrato();
            fila[5] = cla.getEstadoContrato();
            fila[6] = cla.getCodAbonado();
            fila[7] = cla.getDireccionPegue();

            return fila;
        }).forEachOrdered((fila) -> {
            temp.addRow(fila);
        });
    }
    //LLENAR CBO TIPOCONTRATO
    private void cargarComboTipoContrato() throws SQLException {

        CDAñadirContrato cda = new CDAñadirContrato();

        String[] Tipo = new String[cda.loadTipoContrato().size()];
        Tipo = cda.loadTipoContrato().toArray(Tipo);

        DefaultComboBoxModel modeloContrato = new DefaultComboBoxModel(Tipo);
        this.jCBTipoContrato.setModel(modeloContrato);
    }
    //LLENAR CBO BLOQUE
    private void cargarComboBloque() throws SQLException {

        CDAñadirContrato cda = new CDAñadirContrato();

        String[] bloque = new String[cda.loadBloque().size()];
        bloque = cda.loadBloque().toArray(bloque);

        DefaultComboBoxModel modeloBloque = new DefaultComboBoxModel(bloque);
        this.jCBBloque.setModel(modeloBloque);
    }
    //LLENAR CBO ESTADO
    private void cargarEstadoContrato() throws SQLException {

        CDAñadirContrato cda = new CDAñadirContrato();

        String[] estado = new String[cda.loadBloque().size()];
        estado = cda.loadEstadoContrato().toArray(estado);

        DefaultComboBoxModel modeloEstado = new DefaultComboBoxModel(estado);
        this.jCBEstadoContrato.setModel(modeloEstado);
    }
    //LLENAR CBO ESTADO EN ESTADO DE EDICION
    private void cargarEstadoContratoEdicion() throws SQLException{
        CDAñadirContrato cda = new CDAñadirContrato();

        String[] estado = new String[cda.loadBloque().size()];
        estado = cda.loadEstadoContratoTablaCBO().toArray(estado);

        DefaultComboBoxModel modeloEstado = new DefaultComboBoxModel(estado);
        this.jCboModificarEstado.setModel(modeloEstado);
    }
    //
    //LLENAR CBO BLOQUE VER CONTRATOS
    private void cargarComboBloqueVerContratos() throws SQLException {

        CDAñadirContrato cda = new CDAñadirContrato();

        String[] bloque = new String[cda.loadBloque().size()];
        bloque = cda.loadBloque().toArray(bloque);

        DefaultComboBoxModel modeloBloque = new DefaultComboBoxModel(bloque);
        this.jCBBloqueItem.setModel(modeloBloque);
    }
    
    //LLENAR CBO ESTADO VER CONTRATOS
    private void cargarEstadoContratoVerContratos() throws SQLException {

        CDAñadirContrato cda = new CDAñadirContrato();

        String[] estado = new String[cda.loadBloque().size()];
        estado = cda.loadEstadoContratoTablaCBO().toArray(estado);

        DefaultComboBoxModel modeloEstado = new DefaultComboBoxModel(estado);
        this.jCBEstadoItem.setModel(modeloEstado);
    }
    private void limpiarEntradas(){
            this.jLblIdContrato.setText("");
            this.jCBBloque.setSelectedItem("--Seleccione--");
            this.jTFNumCasa.setText("");
            this.jCBTipoContrato.setSelectedItem("--Seleccione--");
            this.jCBEstadoContrato.setSelectedItem("--Seleccione--");
            this.jLblCodAbonado.setText("");
            this.jTADireccionPegue.setText("");
            this.jLblNombreAbonadoMostrar.setText("");
            this.jLblNumIdentidadMostrar.setText("");
            this.jTfBuscar.setText("");
    }
    //VALIDAR ENTRADAS
    private boolean validarEntradas(){
        boolean estado;
        
        if (this.jLblIdContrato.getText().isEmpty() || this.jTFNumCasa.getText().isEmpty()
            || this.jLblCodAbonado.getText().isEmpty() || this.jTADireccionPegue.getText().isEmpty() 
            || this.jCBBloque.getSelectedItem().toString() == "--Seleccione--" 
            || this.jCBTipoContrato.getSelectedItem().toString() == "--Seleccione--") {
            estado = false;
        }else{
            estado = true;
        }
        
        return estado;
    }
    //LIMPIAR TABLA CONTRATOS
    private void clearTable() {
        DefaultTableModel dtm = (DefaultTableModel) this.jTblContratos.getModel();
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
    }
    //LIMPIAR TABLA PRECIOS
    private void limpiarTablaPrecioServicio() {
        DefaultTableModel dtm = (DefaultTableModel) this.jTblPrecioServicio.getModel();
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
    }
    //CARGAR DATOS PARA MODIFICAR
    private void cargarDatos(){
        if(this.jTblContratos.getSelectedRow()!= -1){
            
            this.jLblIdContrato.setText(String.valueOf(this.jTblContratos.getValueAt(this.jTblContratos.getSelectedRow(), 0)));
            this.jCBBloque.setSelectedItem(String.valueOf(this.jTblContratos.getValueAt(this.jTblContratos.getSelectedRow(), 2)));
            this.jTFNumCasa.setText(String.valueOf(this.jTblContratos.getValueAt(this.jTblContratos.getSelectedRow(), 3)));
            this.jCBTipoContrato.setSelectedItem(String.valueOf(this.jTblContratos.getValueAt(this.jTblContratos.getSelectedRow(), 4)));
            this.jLblCodAbonado.setText((String.valueOf(this.jTblContratos.getValueAt(this.jTblContratos.getSelectedRow(), 6))));
            this.jTADireccionPegue.setText(String.valueOf(this.jTblContratos.getValueAt(this.jTblContratos.getSelectedRow(), 7)));
            this.jCboModificarEstado.setSelectedItem(String.valueOf(this.jTblContratos.getValueAt(this.jTblContratos.getSelectedRow(), 5)));
   
        }   
    }
    
    private void informacionUsuario(){
        if(this.jTblAbonados.getSelectedRow()!= -1){
            
            this.jLblNombreAbonadoMostrar.setText(String.valueOf(this.jTblAbonados.getValueAt(this.jTblAbonados.getSelectedRow(), 1)));
            this.jLblNumIdentidadMostrar.setText(String.valueOf(this.jTblAbonados.getValueAt(this.jTblAbonados.getSelectedRow(), 0)));
            
        }
    }
    private void informacionCompletaContrato() throws SQLException{
        if(this.jTblBusquedaContratos.getSelectedRow()!= -1){
            
            this.jTfInfoIdContrato.setText(String.valueOf(this.jTblBusquedaContratos.getValueAt(this.jTblBusquedaContratos.getSelectedRow(), 0)));
            this.jLblNumeroIdentidad.setText(String.valueOf(this.jTblBusquedaContratos.getValueAt(this.jTblBusquedaContratos.getSelectedRow(), 1)));
            this.jLblNombreAbonado.setText(String.valueOf(this.jTblBusquedaContratos.getValueAt(this.jTblBusquedaContratos.getSelectedRow(), 2)));
            this.jTfInfoBloque.setText(String.valueOf(this.jTblBusquedaContratos.getValueAt(this.jTblBusquedaContratos.getSelectedRow(), 3)));
            this.jTfInfoNumCasa.setText((String.valueOf(this.jTblBusquedaContratos.getValueAt(this.jTblBusquedaContratos.getSelectedRow(), 4))));
            this.jTfInfoEstadoContrato.setText(String.valueOf(this.jTblBusquedaContratos.getValueAt(this.jTblBusquedaContratos.getSelectedRow(), 5)));
            this.jTfInfoTipoContrato.setText(String.valueOf(this.jTblBusquedaContratos.getValueAt(this.jTblBusquedaContratos.getSelectedRow(), 6)));
            this.jTextAreaInfoDireccion.setText(String.valueOf(this.jTblBusquedaContratos.getValueAt(this.jTblBusquedaContratos.getSelectedRow(), 7)));
            String TipoContrato =(String.valueOf(this.jTblBusquedaContratos.getValueAt(this.jTblBusquedaContratos.getSelectedRow(), 6)));;
            
            cargarListaServicioVerContrato(TipoContrato);
        }  
    }
    private void mostrarInformacionModificar(){
        if(this.jTblContratos.getSelectedRow()!= -1){
            this.jLblNombreAbonadoMostrar.setText(String.valueOf(this.jTblContratos.getValueAt(this.jTblContratos.getSelectedRow(), 1)));
            this.jLblNumIdentidadMostrar.setText(String.valueOf(this.jTblContratos.getValueAt(this.jTblContratos.getSelectedRow(), 6))); 
        }
    }

    ////////////////////////////////////////==============GESTION DE DATOS ===============////////////////////////////////////////
    //METODO PARA INSERTAR UN CONTRATO
    private void insertarContrato(){
        try{ 
            
            CDAñadirContrato cda = new CDAñadirContrato();
            CLAñadirContrato cla = new CLAñadirContrato();
            //CLLogin login = new CLLogin();
            
            if (this.jCKBActivo.isSelected()) {
                cla.setEstadoContrato("Activo");
            } else if (!this.jCKBActivo.isSelected()) {  
                cla.setEstadoContrato(this.jCBEstadoContrato.getSelectedItem().toString());
            }
            if(this.jTblAbonados.getSelectedRow() != -1){
            cla.setCodAbonado(String.valueOf(this.jTblAbonados.getValueAt(this.jTblAbonados.getSelectedRow(),0)));   
            }
            cla.setNumCasa(this.jTFNumCasa.getText().trim());
            cla.setDireccionPegue(this.jTADireccionPegue.getText().trim());
            cla.setTipoContrato(this.jCBTipoContrato.getSelectedItem().toString());
            cla.setBloque(this.jCBBloque.getSelectedItem().toString());
            cla.setUsuario(2);
            //cla.setUsuario(login.getIdUsuario()); //HABILITAR AL PODER USAR EL MENU
            cda.insertarContrato(cla);
            
            JOptionPane.showMessageDialog(null, "El registro se ha creado con exito...", "SAJA", 
                    JOptionPane.INFORMATION_MESSAGE);
            //clearTextField();
            
            llenarTablaContratos();
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al crear el registro: " + ex);
        }
    }
    //METODO PARA INSERTAR EN TABLACONTRATOSERVICIO
    
    //METODO PARA MODIFICAR UN CONTRATO
    private void updateContrato() {
        try {
            
            CDAñadirContrato cda = new CDAñadirContrato();
            CLAñadirContrato cla = new CLAñadirContrato();
            //CLLogin login = new CLLogin();
            cla.setIdContrato(Integer.parseInt(this.jLblIdContrato.getText()));
            cla.setCodAbonado(this.jLblCodAbonado.getText());
            cla.setNumCasa(this.jTFNumCasa.getText().trim());
            cla.setDireccionPegue(this.jTADireccionPegue.getText().trim());
            cla.setTipoContrato(this.jCBTipoContrato.getSelectedItem().toString());
            cla.setEstadoContrato(this.jCboModificarEstado.getSelectedItem().toString());
            cla.setBloque(this.jCBBloque.getSelectedItem().toString());
            cla.setUsuario(2);
            //cla.setUsuario(login.getIdUsuario()); //HABILITAR AL PODER USAR EL MENU
            cda.modificarContrato(cla);
            insertarFilasUpdate();
            llenarTablaContratos();

            JOptionPane.showMessageDialog(null, "El registro se ha modificado con exito...", "SAJA",
                    JOptionPane.INFORMATION_MESSAGE);
            

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al editar el registro: " + ex);
        }
    }
    //METODO PARA ELIMINAR UN CONTRATO //RECORDATORIO, IMPLEMENTAR CAMBIO DE ESTADO()
    private void deleteContrato() {
        try {
            CDAñadirContrato cda = new CDAñadirContrato();
            CLAñadirContrato cla = new CLAñadirContrato();  
            
            if(this.jTblContratos.getSelectedRow() != -1){
            DefaultTableModel tm = (DefaultTableModel) jTblContratos.getModel();
            String dato = (String.valueOf(tm.getValueAt(this.jTblContratos.getSelectedRow(),0))); 
            int id = Integer.parseInt(dato);
            cla.setIdContrato(id);
            cda.eliminarContrato(cla);
            }

            JOptionPane.showMessageDialog(null, "El registro se ha eliminado con exito ...", "SAJA",
                    JOptionPane.INFORMATION_MESSAGE);
            //clearTextField();
            llenarTablaContratos();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el registro: " + ex);
        }
    }
        ////////////////////////////////////////==============GESTION DE DATOS ===============////////////////////////////////////////
    
    //////////////////////////////////////////================CONFIGURACION MODAL======================///////////////////////
    private void cargarDataModelServicios() throws SQLException{
        cargarListaPreciosServicio(this.jCBTipoContrato.getSelectedItem().toString());
        this.jLblTipoContrato.setText(this.jCBTipoContrato.getSelectedItem().toString());
        cargaNombresServiciosContrato();
    }
    private void cargarListaPreciosServicio(String TipoContrato) throws SQLException{
        
        limpiarTablaPrecioServicio();
        CDAñadirContrato cda = new CDAñadirContrato();
        List<CLAñadirContrato> miLista;
        miLista = cda.listaTipoContratoServicio(TipoContrato);
        DefaultTableModel temp = (DefaultTableModel) this.jTblPrecioServicio.getModel();

        miLista.stream().map((cla) -> {
            Object[] fila = new Object[2];
            fila[0] = cla.getNombreServicio();
            fila[1] = cla.getPrecioServicio();            
            
            return fila;
        }).forEachOrdered((fila) -> {
            
            temp.addRow(fila);
        });
    }
    private void cargaNombresServiciosContrato(){
        DefaultTableModel dtm = (DefaultTableModel) this.jTblPrecioServicio.getModel();
        
        String Servicios = "";
        for(int i = 0 ;i<dtm.getRowCount();i++) {
            Servicios  += ","+String.valueOf(this.jTblPrecioServicio.getValueAt(i, 0))+"\n";   
        }
        this.jTfNombresServicios.setText(Servicios);
    }//PENDIENTE
    private void eliminarFilaServicioPrecio(){
        DefaultTableModel dtm = (DefaultTableModel) this.jTblPrecioServicio.getModel();
        if(dtm.getRowCount() > 1) {
            dtm.removeRow(jTblPrecioServicio.getSelectedRow()); 
        }
    }
    
    private void cargarListaServicioVerContrato(String TipoContrato) throws SQLException{
        
        limpiarTablaPrecioServicio();
        CDAñadirContrato cda = new CDAñadirContrato();
        List<CLAñadirContrato> miLista;
        miLista = cda.listaTipoContratoServicio(TipoContrato);
        DefaultTableModel temp = (DefaultTableModel) this.jTblServiciosDeContrato.getModel();

        miLista.stream().map((cla) -> {
            Object[] fila = new Object[2];
            fila[0] = cla.getNombreServicio();
            fila[1] = cla.getPrecioServicio();            
            
            return fila;
        }).forEachOrdered((fila) -> {
            
            temp.addRow(fila);
        });
    }
    
    private void insertarFilas(){
        try{ 
            
            CDAñadirContrato cda = new CDAñadirContrato();
            CLAñadirContrato cla = new CLAñadirContrato();
            
            DefaultTableModel dtm = (DefaultTableModel) this.jTblPrecioServicio.getModel();
            
            for(int i= 0;i < dtm.getRowCount();i++){
                cla.setIdContratoServicio(cda.obtenerIdContrato());////USAR UN METODO PARA TRAER EL VALOR
                cla.setNombreServicio(String.valueOf(this.jTblPrecioServicio.getValueAt(i, 0)));
                cla.setPrecioServicio(Float.parseFloat(String.valueOf(this.jTblPrecioServicio.getValueAt(i, 1))));
                cda.insertarTipoContratoPrecio(cla);
            }

            JOptionPane.showMessageDialog(null, "Se han añadido los servicios de manera exitosa", "SAJA", 
                    JOptionPane.INFORMATION_MESSAGE);

            llenarTablaContratos();
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al crear el registro: " + ex);
        }
    }
    
    private void insertarFilasUpdate(){
        try{ 
            
            CDAñadirContrato cda = new CDAñadirContrato();
            CLAñadirContrato cla = new CLAñadirContrato();
            
            DefaultTableModel dtm = (DefaultTableModel) this.jTblPrecioServicio.getModel();
            
            for(int i= 0;i < dtm.getRowCount();i++){
                //cla.setIdContratoServicio(Integer.parseInt(this.jLblIdContrato.getText()));////USAR UN METODO PARA TRAER EL VALOR
                cla.setNombreServicio(String.valueOf(this.jTblPrecioServicio.getValueAt(i, 0)));
                cla.setPrecioServicio(Float.parseFloat(String.valueOf(this.jTblPrecioServicio.getValueAt(i, 1))));
                cda.insertarTipoContratoPrecio(cla);
            }

            JOptionPane.showMessageDialog(null, "Se han añadido los servicios de manera exitosa", "SAJA", 
                    JOptionPane.INFORMATION_MESSAGE);

            llenarTablaContratos();
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al crear el registro: " + ex);
        }
    }
    private void eliminarServiciosContratoUpdate(){
        try {
            CDAñadirContrato cda = new CDAñadirContrato();
            CLAñadirContrato cla = new CLAñadirContrato();  
            
            cda.eliminarServiciosContrato(cla);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,  ex);
        }
    } ////PENDIENTE
    
    private void limpiarTablaBusquedaContrato(){
        DefaultTableModel dtm = (DefaultTableModel) this.jTblBusquedaContratos.getModel();
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
    }
    
     //METODO PARA MODIFICAR UN CONTRATO
    private void cargardatosdesdeVerContrato() {
            this.jLblIdContrato.setText(this.jTfInfoIdContrato.getText());
            this.jCBBloque.setSelectedItem(this.jTfInfoBloque.getText());
            this.jTFNumCasa.setText(this.jTfInfoNumCasa.getText());
            this.jCBTipoContrato.setSelectedItem(this.jTfInfoTipoContrato.getText());
            this.jTADireccionPegue.setText(this.jTextAreaInfoDireccion.getText());
            this.jCboModificarEstado.setSelectedItem(this.jTfInfoEstadoContrato.getText());
            this.jLblNumIdentidadMostrar.setText(this.jLblNombreAbonado.getText());
            this.jLblNombreAbonadoMostrar.setText(this.jLblNumeroIdentidad.getText());
    }
    private void cargarModalBusquedaContrato(String bloque, String numCasa) throws SQLException{
        limpiarTablaBusquedaContrato();
        CDAñadirContrato cda = new CDAñadirContrato();
        List<CLAñadirContrato> miLista;
        miLista = cda.busquedaContratosBloqueNumCasa(bloque,numCasa);
        DefaultTableModel temp = (DefaultTableModel) this.jTblBusquedaContratos.getModel();

        miLista.stream().map((cla) -> {
            Object[] fila = new Object[8];
            fila[0] = cla.getIdContrato();
            fila[1] = cla.getCodAbonado();            
            fila[2] = cla.getAbonado(); 
            fila[3] = cla.getBloque(); 
            fila[4] = cla.getNumCasa(); 
            fila[5] = cla.getEstadoContrato(); 
            fila[6] = cla.getTipoContrato(); 
            fila[7] = cla.getDireccionPegue(); 
            
            return fila;
        }).forEachOrdered((fila) -> {
            
            temp.addRow(fila);
        });
    }
    
    private void cargarModalBusquedaContratoAbonado(String cod) throws SQLException  {
        limpiarTablaBusquedaContrato();
        CDAñadirContrato cda = new CDAñadirContrato();
        List<CLAñadirContrato> miLista;
        miLista = cda.listaContratoAbonado(cod);
        DefaultTableModel temp = (DefaultTableModel) this.jTblBusquedaContratos.getModel();

        miLista.stream().map((cla) -> {
            Object[] fila = new Object[8];
            fila[0] = cla.getIdContrato();
            fila[1] = cla.getCodAbonado();            
            fila[2] = cla.getAbonado(); 
            fila[3] = cla.getBloque(); 
            fila[4] = cla.getNumCasa(); 
            fila[5] = cla.getEstadoContrato(); 
            fila[6] = cla.getTipoContrato(); 
            fila[7] = cla.getDireccionPegue(); 

            return fila;
        }).forEachOrdered((fila) -> {
            temp.addRow(fila);
        });
    }
    //////////////////////////////////////////================CONFIGURACION MODAL======================///////////////////////
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jDialogServicios = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTblPrecioServicio = new javax.swing.JTable();
        jBtnAgregarAContrato = new javax.swing.JButton();
        jLblTitulo3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLblTipoContrato = new javax.swing.JLabel();
        jBtnDeshacer = new javax.swing.JButton();
        jBtnQuitarServicio1 = new javax.swing.JButton();
        jDialogBusquedaContratos = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jLblcasabusqueda = new javax.swing.JLabel();
        jLblIdentidadBusqueda = new javax.swing.JLabel();
        jRbBusquedaPegue = new javax.swing.JRadioButton();
        jRbBusquedaIdentida = new javax.swing.JRadioButton();
        jLblIdentificador64 = new javax.swing.JLabel();
        jBtnSeleccionar = new javax.swing.JButton();
        jBtnCancelarBusqueda = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTblBusquedaContratos = new javax.swing.JTable();
        jTfBusquedaBloque = new javax.swing.JTextField();
        jTfBusquedaCasa = new javax.swing.JTextField();
        jTfBusquedaNumIdentidad = new javax.swing.JTextField();
        jLblbloqueBusqueda = new javax.swing.JLabel();
        jBtnBusquedaCasaBloque = new javax.swing.JLabel();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jDfMenu = new javax.swing.JDialog();
        jPanel15 = new javax.swing.JPanel();
        jLblIdentificador60 = new javax.swing.JLabel();
        jLblIdentificador71 = new javax.swing.JLabel();
        jLblIdentificador73 = new javax.swing.JLabel();
        jBtnSi = new javax.swing.JButton();
        JBtnNo = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLblTitulo2 = new javax.swing.JLabel();
        jBtnSideBar1 = new javax.swing.JLabel();
        jBtnSideBar = new javax.swing.JLabel();
        jLblMinimizar2 = new javax.swing.JLabel();
        jLblSalir = new javax.swing.JLabel();
        jSideBar1 = new javax.swing.JScrollPane();
        jSideBar = new javax.swing.JPanel();
        jSBContrato = new javax.swing.JPanel();
        jLblIdentificador = new javax.swing.JLabel();
        jLblIdentificador1 = new javax.swing.JLabel();
        jSBListadoContrato = new javax.swing.JPanel();
        jLblIdentificador14 = new javax.swing.JLabel();
        jLblIdentificador2 = new javax.swing.JLabel();
        jSBMenu = new javax.swing.JPanel();
        jLblIdentificador9 = new javax.swing.JLabel();
        jLblIdentificador10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLblIdentificador5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jSBVerContrato = new javax.swing.JPanel();
        jLblIdentificador3 = new javax.swing.JLabel();
        jLblIdentificador13 = new javax.swing.JLabel();
        jTabContrato = new javax.swing.JTabbedPane();
        jPnlContrato = new javax.swing.JPanel();
        jPnlBuscar = new javax.swing.JPanel();
        jTfBuscar = new javax.swing.JTextField();
        jBtnBuscar = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTblAbonados = new javax.swing.JTable();
        jSeparatorY = new javax.swing.JPanel();
        jLblIdentificador11 = new javax.swing.JLabel();
        jLblIdentificador12 = new javax.swing.JLabel();
        jTFNumCasa = new javax.swing.JTextField();
        jLblIdentificador16 = new javax.swing.JLabel();
        jCBBloque = new javax.swing.JComboBox<>();
        jSeparatorX1 = new javax.swing.JPanel();
        jLblIdentificador18 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTADireccionPegue = new javax.swing.JTextArea();
        jCKBActivo = new javax.swing.JCheckBox();
        jCBEstadoContrato = new javax.swing.JComboBox<>();
        jBtnGuardar = new javax.swing.JButton();
        jBtnEditar = new javax.swing.JButton();
        jLblIdentificador15 = new javax.swing.JLabel();
        jLblIdentificador24 = new javax.swing.JLabel();
        jCBTipoContrato = new javax.swing.JComboBox<>();
        jTfNombresServicios = new javax.swing.JTextField();
        jBtnBuscar7 = new javax.swing.JLabel();
        jCboModificarEstado = new javax.swing.JComboBox<>();
        jLblCodAbonado = new javax.swing.JLabel();
        jLblIdContrato = new javax.swing.JLabel();
        jLblNombreAbonadoMostrar = new javax.swing.JLabel();
        jLblIdentificador23 = new javax.swing.JLabel();
        jLblIdentificador26 = new javax.swing.JLabel();
        jLblIdentificador27 = new javax.swing.JLabel();
        jLblNumIdentidadMostrar = new javax.swing.JLabel();
        jBtnCancelar = new javax.swing.JButton();
        jPnlListaContrato = new javax.swing.JPanel();
        jPnlBuscar6 = new javax.swing.JPanel();
        jBtnBuscar6 = new javax.swing.JLabel();
        jTfBuscar6 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTblContratos = new javax.swing.JTable();
        jLblIdentificador19 = new javax.swing.JLabel();
        jRBCodigo = new javax.swing.JRadioButton();
        jRBBloque = new javax.swing.JRadioButton();
        jRBEstado = new javax.swing.JRadioButton();
        jRBAlfabetico = new javax.swing.JRadioButton();
        jLblIdentificador21 = new javax.swing.JLabel();
        jLblIdentificador22 = new javax.swing.JLabel();
        jLblModificarContrato = new javax.swing.JLabel();
        jLblIdentificador25 = new javax.swing.JLabel();
        jCBEstadoItem = new javax.swing.JComboBox<>();
        jCBBloqueItem = new javax.swing.JComboBox<>();
        jPnlVerContrato = new javax.swing.JPanel();
        jLblNombreAbonado = new javax.swing.JLabel();
        jLblIdentificador47 = new javax.swing.JLabel();
        jLblNumeroIdentidad = new javax.swing.JLabel();
        jLblIdentificador43 = new javax.swing.JLabel();
        jLblIdentificador55 = new javax.swing.JLabel();
        jLblIdentificador45 = new javax.swing.JLabel();
        jPnlSeparatorY = new javax.swing.JPanel();
        jLblIdentificador49 = new javax.swing.JLabel();
        jPnlSeparatorX = new javax.swing.JPanel();
        jLblIdentificador50 = new javax.swing.JLabel();
        jLblIdentificador51 = new javax.swing.JLabel();
        jLblIdentificador52 = new javax.swing.JLabel();
        jTfInfoBloque = new javax.swing.JTextField();
        jTfInfoNumCasa = new javax.swing.JTextField();
        jLblModificarContrato2 = new javax.swing.JLabel();
        jLblIdentificador54 = new javax.swing.JLabel();
        jTfInfoIdContrato = new javax.swing.JTextField();
        jLblIdentificador56 = new javax.swing.JLabel();
        jTfInfoTipoContrato = new javax.swing.JTextField();
        jLblIdentificador57 = new javax.swing.JLabel();
        jTfInfoEstadoContrato = new javax.swing.JTextField();
        jLblIdentificador58 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaInfoDireccion = new javax.swing.JTextArea();
        jLblIdentificador59 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTblServiciosDeContrato = new javax.swing.JTable();
        jBtnBusquedaAvanzada = new javax.swing.JButton();

        jDialogServicios.setAlwaysOnTop(true);
        jDialogServicios.setModal(true);
        jDialogServicios.setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        jDialogServicios.setResizable(false);
        jDialogServicios.setSize(new java.awt.Dimension(588, 390));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(null);

        jTblPrecioServicio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Servicio", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTblPrecioServicio);

        jPanel5.add(jScrollPane3);
        jScrollPane3.setBounds(50, 110, 480, 130);

        jBtnAgregarAContrato.setBackground(new java.awt.Color(41, 128, 185));
        jBtnAgregarAContrato.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnAgregarAContrato.setForeground(new java.awt.Color(255, 255, 255));
        jBtnAgregarAContrato.setText("Agregar al contrato");
        jBtnAgregarAContrato.setBorder(null);
        jBtnAgregarAContrato.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnAgregarAContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAgregarAContratoActionPerformed(evt);
            }
        });
        jPanel5.add(jBtnAgregarAContrato);
        jBtnAgregarAContrato.setBounds(380, 270, 150, 50);

        jLblTitulo3.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 30)); // NOI18N
        jLblTitulo3.setText("Servicios de contrato");
        jPanel5.add(jLblTitulo3);
        jLblTitulo3.setBounds(50, 20, 290, 31);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Tipo Contrato:");
        jPanel5.add(jLabel1);
        jLabel1.setBounds(50, 70, 102, 30);

        jLblTipoContrato.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLblTipoContrato.setText("Selecciona un tipo");
        jPanel5.add(jLblTipoContrato);
        jLblTipoContrato.setBounds(160, 70, 130, 30);

        jBtnDeshacer.setBackground(new java.awt.Color(41, 128, 185));
        jBtnDeshacer.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnDeshacer.setForeground(new java.awt.Color(255, 255, 255));
        jBtnDeshacer.setText("Deshacer");
        jBtnDeshacer.setBorder(null);
        jBtnDeshacer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnDeshacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDeshacerActionPerformed(evt);
            }
        });
        jPanel5.add(jBtnDeshacer);
        jBtnDeshacer.setBounds(140, 270, 100, 50);

        jBtnQuitarServicio1.setBackground(new java.awt.Color(41, 128, 185));
        jBtnQuitarServicio1.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnQuitarServicio1.setForeground(new java.awt.Color(255, 255, 255));
        jBtnQuitarServicio1.setText("Quitar Servicio");
        jBtnQuitarServicio1.setBorder(null);
        jBtnQuitarServicio1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnQuitarServicio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnQuitarServicio1ActionPerformed(evt);
            }
        });
        jPanel5.add(jBtnQuitarServicio1);
        jBtnQuitarServicio1.setBounds(250, 270, 120, 50);

        javax.swing.GroupLayout jDialogServiciosLayout = new javax.swing.GroupLayout(jDialogServicios.getContentPane());
        jDialogServicios.getContentPane().setLayout(jDialogServiciosLayout);
        jDialogServiciosLayout.setHorizontalGroup(
            jDialogServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
        );
        jDialogServiciosLayout.setVerticalGroup(
            jDialogServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
        );

        jDialogBusquedaContratos.setModal(true);
        jDialogBusquedaContratos.setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        jDialogBusquedaContratos.setSize(new java.awt.Dimension(617, 450));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(null);

        jLblcasabusqueda.setBackground(new java.awt.Color(102, 102, 102));
        jLblcasabusqueda.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblcasabusqueda.setText("Casa");
        jPanel6.add(jLblcasabusqueda);
        jLblcasabusqueda.setBounds(420, 30, 50, 24);

        jLblIdentidadBusqueda.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentidadBusqueda.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentidadBusqueda.setText("Identidad");
        jPanel6.add(jLblIdentidadBusqueda);
        jLblIdentidadBusqueda.setBounds(330, 30, 110, 24);

        jRbBusquedaPegue.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(jRbBusquedaPegue);
        jRbBusquedaPegue.setFont(new java.awt.Font("HelveticaNowDisplay Light", 0, 14)); // NOI18N
        jRbBusquedaPegue.setSelected(true);
        jRbBusquedaPegue.setText("Pegue");
        jRbBusquedaPegue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRbBusquedaPegueActionPerformed(evt);
            }
        });
        jPanel6.add(jRbBusquedaPegue);
        jRbBusquedaPegue.setBounds(20, 60, 72, 28);

        jRbBusquedaIdentida.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(jRbBusquedaIdentida);
        jRbBusquedaIdentida.setFont(new java.awt.Font("HelveticaNowDisplay Light", 0, 14)); // NOI18N
        jRbBusquedaIdentida.setText("Identidad");
        jRbBusquedaIdentida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRbBusquedaIdentidaActionPerformed(evt);
            }
        });
        jPanel6.add(jRbBusquedaIdentida);
        jRbBusquedaIdentida.setBounds(100, 60, 87, 28);

        jLblIdentificador64.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador64.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador64.setText("Buscar contrato");
        jPanel6.add(jLblIdentificador64);
        jLblIdentificador64.setBounds(20, 20, 260, 24);

        jBtnSeleccionar.setBackground(new java.awt.Color(41, 128, 185));
        jBtnSeleccionar.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnSeleccionar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnSeleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cruz-blanca24.png"))); // NOI18N
        jBtnSeleccionar.setText("Seleccionar");
        jBtnSeleccionar.setBorder(null);
        jBtnSeleccionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSeleccionarActionPerformed(evt);
            }
        });
        jPanel6.add(jBtnSeleccionar);
        jBtnSeleccionar.setBounds(400, 330, 187, 50);

        jBtnCancelarBusqueda.setBackground(new java.awt.Color(41, 128, 185));
        jBtnCancelarBusqueda.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnCancelarBusqueda.setForeground(new java.awt.Color(255, 255, 255));
        jBtnCancelarBusqueda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/x-blanca24.png"))); // NOI18N
        jBtnCancelarBusqueda.setText("Cancelar");
        jBtnCancelarBusqueda.setBorder(null);
        jBtnCancelarBusqueda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnCancelarBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelarBusquedaActionPerformed(evt);
            }
        });
        jPanel6.add(jBtnCancelarBusqueda);
        jBtnCancelarBusqueda.setBounds(10, 330, 180, 50);

        jTblBusquedaContratos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "              #", "Numero identidad", "Nombre Completo", "Bloque", "Casa", "Estado Contrato", "Tipo Contrato", "Direccion Pegue"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(jTblBusquedaContratos);

        jPanel6.add(jScrollPane6);
        jScrollPane6.setBounds(10, 110, 580, 190);

        jTfBusquedaBloque.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfBusquedaBloque.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfBusquedaBloque.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPanel6.add(jTfBusquedaBloque);
        jTfBusquedaBloque.setBounds(330, 60, 80, 35);

        jTfBusquedaCasa.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfBusquedaCasa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfBusquedaCasa.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPanel6.add(jTfBusquedaCasa);
        jTfBusquedaCasa.setBounds(420, 60, 88, 35);

        jTfBusquedaNumIdentidad.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfBusquedaNumIdentidad.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfBusquedaNumIdentidad.setSelectionColor(new java.awt.Color(0, 153, 153));
        jTfBusquedaNumIdentidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTfBusquedaNumIdentidadKeyReleased(evt);
            }
        });
        jPanel6.add(jTfBusquedaNumIdentidad);
        jTfBusquedaNumIdentidad.setBounds(330, 60, 180, 35);

        jLblbloqueBusqueda.setBackground(new java.awt.Color(102, 102, 102));
        jLblbloqueBusqueda.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblbloqueBusqueda.setText("Bloque");
        jPanel6.add(jLblbloqueBusqueda);
        jLblbloqueBusqueda.setBounds(330, 30, 70, 24);

        jBtnBusquedaCasaBloque.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-black24.png"))); // NOI18N
        jBtnBusquedaCasaBloque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnBusquedaCasaBloqueMouseClicked(evt);
            }
        });
        jPanel6.add(jBtnBusquedaCasaBloque);
        jBtnBusquedaCasaBloque.setBounds(520, 50, 30, 50);

        javax.swing.GroupLayout jDialogBusquedaContratosLayout = new javax.swing.GroupLayout(jDialogBusquedaContratos.getContentPane());
        jDialogBusquedaContratos.getContentPane().setLayout(jDialogBusquedaContratosLayout);
        jDialogBusquedaContratosLayout.setHorizontalGroup(
            jDialogBusquedaContratosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
        );
        jDialogBusquedaContratosLayout.setVerticalGroup(
            jDialogBusquedaContratosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        jDfMenu.setTitle("Confirmar acción");
        jDfMenu.setAlwaysOnTop(true);
        jDfMenu.setModal(true);
        jDfMenu.setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        jDfMenu.setUndecorated(true);
        jDfMenu.setSize(new java.awt.Dimension(300, 200));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLblIdentificador60.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador60.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador60.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador60.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/menu-negro24.png"))); // NOI18N
        jLblIdentificador60.setText("¿Regresar al menú?");
        jLblIdentificador60.setIconTextGap(10);

        jLblIdentificador71.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador71.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jLblIdentificador71.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador71.setText("Si no guardó el registro actual, su");

        jLblIdentificador73.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador73.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jLblIdentificador73.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador73.setText("progreso se perderá.");

        jBtnSi.setBackground(new java.awt.Color(41, 128, 185));
        jBtnSi.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnSi.setForeground(new java.awt.Color(255, 255, 255));
        jBtnSi.setText("SI");
        jBtnSi.setBorder(null);
        jBtnSi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSiActionPerformed(evt);
            }
        });

        JBtnNo.setBackground(new java.awt.Color(41, 128, 185));
        JBtnNo.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        JBtnNo.setForeground(new java.awt.Color(255, 255, 255));
        JBtnNo.setText("NO");
        JBtnNo.setBorder(null);
        JBtnNo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JBtnNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBtnNoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIdentificador73)
                    .addComponent(jLblIdentificador60)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                            .addComponent(jBtnSi, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(JBtnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLblIdentificador71)))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLblIdentificador60)
                .addGap(18, 18, 18)
                .addComponent(jLblIdentificador71)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLblIdentificador73)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnSi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBtnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout jDfMenuLayout = new javax.swing.GroupLayout(jDfMenu.getContentPane());
        jDfMenu.getContentPane().setLayout(jDfMenuLayout);
        jDfMenuLayout.setHorizontalGroup(
            jDfMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDfMenuLayout.setVerticalGroup(
            jDfMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1000, 700));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(41, 128, 185));
        jPanel3.setPreferredSize(new java.awt.Dimension(800, 60));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLblTitulo2.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 30)); // NOI18N
        jLblTitulo2.setForeground(new java.awt.Color(255, 255, 255));
        jLblTitulo2.setText("Contratos");
        jPanel3.add(jLblTitulo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, 50));

        jBtnSideBar1.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 25)); // NOI18N
        jBtnSideBar1.setForeground(new java.awt.Color(255, 255, 255));
        jBtnSideBar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/x-blanca24.png"))); // NOI18N
        jBtnSideBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnSideBar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnSideBar1MouseClicked(evt);
            }
        });
        jPanel3.add(jBtnSideBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 60));

        jBtnSideBar.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 25)); // NOI18N
        jBtnSideBar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnSideBar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sidebar2-blanco24.png"))); // NOI18N
        jBtnSideBar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnSideBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnSideBarMouseClicked(evt);
            }
        });
        jPanel3.add(jBtnSideBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 60));

        jLblMinimizar2.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 30)); // NOI18N
        jLblMinimizar2.setForeground(new java.awt.Color(255, 255, 255));
        jLblMinimizar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minimizar-blanco24.png"))); // NOI18N
        jLblMinimizar2.setToolTipText("Minimizar la ventana");
        jLblMinimizar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLblMinimizar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblMinimizar2MouseClicked(evt);
            }
        });
        jPanel3.add(jLblMinimizar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 0, -1, 60));

        jLblSalir.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 30)); // NOI18N
        jLblSalir.setForeground(new java.awt.Color(255, 255, 255));
        jLblSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrar-blanco24.png"))); // NOI18N
        jLblSalir.setToolTipText("Cerrar la ventana");
        jLblSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLblSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblSalirMouseClicked(evt);
            }
        });
        jPanel3.add(jLblSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 0, -1, 60));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 60));

        jSideBar1.setBackground(new java.awt.Color(52, 73, 94));
        jSideBar1.setBorder(null);

        jSideBar.setBackground(new java.awt.Color(52, 73, 94));
        jSideBar.setPreferredSize(new java.awt.Dimension(260, 600));
        jSideBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSBContrato.setBackground(new java.awt.Color(52, 152, 219));
        jSBContrato.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSBContrato.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSBContratoMouseClicked(evt);
            }
        });

        jLblIdentificador.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/contrato-blanco32.png"))); // NOI18N
        jLblIdentificador.setToolTipText("Contratos");
        jLblIdentificador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblIdentificadorMouseClicked(evt);
            }
        });

        jLblIdentificador1.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador1.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador1.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador1.setText("Contratos");

        javax.swing.GroupLayout jSBContratoLayout = new javax.swing.GroupLayout(jSBContrato);
        jSBContrato.setLayout(jSBContratoLayout);
        jSBContratoLayout.setHorizontalGroup(
            jSBContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSBContratoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLblIdentificador1, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addGap(30, 30, 30)
                .addComponent(jLblIdentificador)
                .addContainerGap())
        );
        jSBContratoLayout.setVerticalGroup(
            jSBContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSBContratoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jSBContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIdentificador, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSideBar.add(jSBContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 225, 260, -1));

        jSBListadoContrato.setBackground(new java.awt.Color(52, 73, 94));
        jSBListadoContrato.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSBListadoContrato.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSBListadoContratoMouseClicked(evt);
            }
        });

        jLblIdentificador14.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador14.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador14.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador14.setText("Listado de contratos");

        jLblIdentificador2.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador2.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador2.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ojo-blanco32.png"))); // NOI18N
        jLblIdentificador2.setToolTipText("Imprimir contrato");
        jLblIdentificador2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblIdentificador2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jSBListadoContratoLayout = new javax.swing.GroupLayout(jSBListadoContrato);
        jSBListadoContrato.setLayout(jSBListadoContratoLayout);
        jSBListadoContratoLayout.setHorizontalGroup(
            jSBListadoContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSBListadoContratoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLblIdentificador14, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLblIdentificador2)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jSBListadoContratoLayout.setVerticalGroup(
            jSBListadoContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSBListadoContratoLayout.createSequentialGroup()
                .addGroup(jSBListadoContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jSBListadoContratoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLblIdentificador14, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
                    .addComponent(jLblIdentificador2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSideBar.add(jSBListadoContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 299, 260, 60));

        jSBMenu.setBackground(new java.awt.Color(52, 73, 94));
        jSBMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSBMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSBMenuMouseClicked(evt);
            }
        });

        jLblIdentificador9.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador9.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador9.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/menu-blanco32.png"))); // NOI18N
        jLblIdentificador9.setToolTipText("Menú");
        jLblIdentificador9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLblIdentificador10.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador10.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador10.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador10.setText("Menú");

        javax.swing.GroupLayout jSBMenuLayout = new javax.swing.GroupLayout(jSBMenu);
        jSBMenu.setLayout(jSBMenuLayout);
        jSBMenuLayout.setHorizontalGroup(
            jSBMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSBMenuLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLblIdentificador10, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLblIdentificador9)
                .addContainerGap())
        );
        jSBMenuLayout.setVerticalGroup(
            jSBMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSBMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jSBMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIdentificador9, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSideBar.add(jSBMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 132, 260, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(0, 1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 255, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jSideBar.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 113, 255, -1));

        jLblIdentificador5.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador5.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 12)); // NOI18N
        jLblIdentificador5.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador5.setText("SAJA");
        jLblIdentificador5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jSideBar.add(jLblIdentificador5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 78, 248, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(0, 1));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 255, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jSideBar.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 206, 255, -1));

        jSBVerContrato.setBackground(new java.awt.Color(52, 73, 94));
        jSBVerContrato.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSBVerContrato.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSBVerContratoMouseClicked(evt);
            }
        });

        jLblIdentificador3.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador3.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador3.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador3.setText("Ver contrato");

        jLblIdentificador13.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador13.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador13.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tabla-blanco32.png"))); // NOI18N
        jLblIdentificador13.setToolTipText("Listado de contratos");
        jLblIdentificador13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblIdentificador13MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jSBVerContratoLayout = new javax.swing.GroupLayout(jSBVerContrato);
        jSBVerContrato.setLayout(jSBVerContratoLayout);
        jSBVerContratoLayout.setHorizontalGroup(
            jSBVerContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSBVerContratoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLblIdentificador3, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jLblIdentificador13)
                .addGap(14, 14, 14))
        );
        jSBVerContratoLayout.setVerticalGroup(
            jSBVerContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSBVerContratoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jSBVerContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIdentificador13, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addGroup(jSBVerContratoLayout.createSequentialGroup()
                        .addComponent(jLblIdentificador3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jSideBar.add(jSBVerContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 373, 260, 70));

        jSideBar1.setViewportView(jSideBar);

        jPanel1.add(jSideBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-210, 0, 270, 600));

        jTabContrato.setBackground(new java.awt.Color(255, 255, 255));
        jTabContrato.setTabPlacement(javax.swing.JTabbedPane.RIGHT);

        jPnlContrato.setBackground(new java.awt.Color(255, 255, 255));
        jPnlContrato.setLayout(null);

        jPnlBuscar.setBackground(new java.awt.Color(255, 255, 255));
        jPnlBuscar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTfBuscar.setBackground(java.awt.Color.white);
        jTfBuscar.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 18)); // NOI18N
        jTfBuscar.setForeground(new java.awt.Color(0, 0, 0));
        jTfBuscar.setBorder(null);
        jTfBuscar.setSelectionColor(new java.awt.Color(0, 153, 153));
        jTfBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTfBuscarKeyReleased(evt);
            }
        });

        jBtnBuscar.setBackground(new java.awt.Color(9, 132, 227));
        jBtnBuscar.setForeground(new java.awt.Color(9, 132, 227));
        jBtnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-black24.png"))); // NOI18N
        jBtnBuscar.setToolTipText("Click para buscar");
        jBtnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPnlBuscarLayout = new javax.swing.GroupLayout(jPnlBuscar);
        jPnlBuscar.setLayout(jPnlBuscarLayout);
        jPnlBuscarLayout.setHorizontalGroup(
            jPnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlBuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTfBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jBtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPnlBuscarLayout.setVerticalGroup(
            jPnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTfBuscar)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlBuscarLayout.createSequentialGroup()
                .addComponent(jBtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPnlContrato.add(jPnlBuscar);
        jPnlBuscar.setBounds(35, 72, 303, 40);

        jTblAbonados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RTN", "Nombre Completo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTblAbonados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblAbonadosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTblAbonados);

        jPnlContrato.add(jScrollPane2);
        jScrollPane2.setBounds(35, 130, 303, 240);

        jSeparatorY.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jSeparatorYLayout = new javax.swing.GroupLayout(jSeparatorY);
        jSeparatorY.setLayout(jSeparatorYLayout);
        jSeparatorYLayout.setHorizontalGroup(
            jSeparatorYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jSeparatorYLayout.setVerticalGroup(
            jSeparatorYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 468, Short.MAX_VALUE)
        );

        jPnlContrato.add(jSeparatorY);
        jSeparatorY.setBounds(378, 30, 1, 468);

        jLblIdentificador11.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador11.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 18)); // NOI18N
        jLblIdentificador11.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador11.setText("Cód. Contrato");
        jPnlContrato.add(jLblIdentificador11);
        jLblIdentificador11.setBounds(419, 30, 200, 19);

        jLblIdentificador12.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador12.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 18)); // NOI18N
        jLblIdentificador12.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador12.setText("Bloque");
        jPnlContrato.add(jLblIdentificador12);
        jLblIdentificador12.setBounds(659, 164, 250, 19);

        jTFNumCasa.setBackground(java.awt.Color.white);
        jTFNumCasa.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTFNumCasa.setForeground(new java.awt.Color(0, 0, 0));
        jTFNumCasa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTFNumCasa.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPnlContrato.add(jTFNumCasa);
        jTFNumCasa.setBounds(419, 194, 200, 35);

        jLblIdentificador16.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador16.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 18)); // NOI18N
        jLblIdentificador16.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador16.setText("Número de casa");
        jPnlContrato.add(jLblIdentificador16);
        jLblIdentificador16.setBounds(419, 164, 200, 19);

        jCBBloque.setBackground(java.awt.Color.white);
        jCBBloque.setFont(new java.awt.Font("HelveticaNowDisplay Light", 1, 16)); // NOI18N
        jCBBloque.setForeground(new java.awt.Color(0, 0, 0));
        jPnlContrato.add(jCBBloque);
        jCBBloque.setBounds(659, 194, 250, 35);

        jSeparatorX1.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jSeparatorX1Layout = new javax.swing.GroupLayout(jSeparatorX1);
        jSeparatorX1.setLayout(jSeparatorX1Layout);
        jSeparatorX1Layout.setHorizontalGroup(
            jSeparatorX1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );
        jSeparatorX1Layout.setVerticalGroup(
            jSeparatorX1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jPnlContrato.add(jSeparatorX1);
        jSeparatorX1.setBounds(419, 152, 490, 1);

        jLblIdentificador18.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador18.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 18)); // NOI18N
        jLblIdentificador18.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador18.setText("Dirección del pegue del contrato");
        jPnlContrato.add(jLblIdentificador18);
        jLblIdentificador18.setBounds(419, 247, 490, 19);

        jTADireccionPegue.setBackground(java.awt.Color.white);
        jTADireccionPegue.setColumns(20);
        jTADireccionPegue.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTADireccionPegue.setForeground(new java.awt.Color(0, 0, 0));
        jTADireccionPegue.setRows(5);
        jTADireccionPegue.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane1.setViewportView(jTADireccionPegue);

        jPnlContrato.add(jScrollPane1);
        jScrollPane1.setBounds(419, 277, 490, 100);

        jCKBActivo.setBackground(new java.awt.Color(255, 255, 255));
        jCKBActivo.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 14)); // NOI18N
        jCKBActivo.setSelected(true);
        jCKBActivo.setText("Contrato en estado áctivo.");
        jCKBActivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCKBActivoActionPerformed(evt);
            }
        });
        jPnlContrato.add(jCKBActivo);
        jCKBActivo.setBounds(419, 399, 200, 24);

        jCBEstadoContrato.setBackground(java.awt.Color.white);
        jCBEstadoContrato.setFont(new java.awt.Font("HelveticaNowDisplay Light", 1, 15)); // NOI18N
        jCBEstadoContrato.setForeground(new java.awt.Color(0, 0, 0));
        jPnlContrato.add(jCBEstadoContrato);
        jCBEstadoContrato.setBounds(700, 390, 210, 40);

        jBtnGuardar.setBackground(new java.awt.Color(41, 128, 185));
        jBtnGuardar.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar-white24.png"))); // NOI18N
        jBtnGuardar.setText("GUARDAR CONTRATO");
        jBtnGuardar.setBorder(null);
        jBtnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGuardarActionPerformed(evt);
            }
        });
        jPnlContrato.add(jBtnGuardar);
        jBtnGuardar.setBounds(700, 450, 213, 50);

        jBtnEditar.setBackground(new java.awt.Color(41, 128, 185));
        jBtnEditar.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnEditar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar-blanco32.png"))); // NOI18N
        jBtnEditar.setText("EDITAR CONTRATO");
        jBtnEditar.setBorder(null);
        jBtnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEditarActionPerformed(evt);
            }
        });
        jPnlContrato.add(jBtnEditar);
        jBtnEditar.setBounds(700, 450, 213, 50);

        jLblIdentificador15.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador15.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador15.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador15.setText("Abonado a registrar contrato");
        jPnlContrato.add(jLblIdentificador15);
        jLblIdentificador15.setBounds(35, 30, 303, 24);

        jLblIdentificador24.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador24.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 18)); // NOI18N
        jLblIdentificador24.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador24.setText("Servicios");
        jPnlContrato.add(jLblIdentificador24);
        jLblIdentificador24.setBounds(656, 68, 201, 19);

        jCBTipoContrato.setBackground(java.awt.Color.white);
        jCBTipoContrato.setFont(new java.awt.Font("HelveticaNowDisplay Light", 1, 16)); // NOI18N
        jCBTipoContrato.setForeground(new java.awt.Color(0, 0, 0));
        jCBTipoContrato.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBTipoContratoItemStateChanged(evt);
            }
        });
        jPnlContrato.add(jCBTipoContrato);
        jCBTipoContrato.setBounds(419, 99, 200, 35);

        jTfNombresServicios.setEditable(false);
        jTfNombresServicios.setBackground(java.awt.Color.white);
        jTfNombresServicios.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfNombresServicios.setForeground(new java.awt.Color(0, 0, 0));
        jTfNombresServicios.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfNombresServicios.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPnlContrato.add(jTfNombresServicios);
        jTfNombresServicios.setBounds(656, 99, 209, 35);

        jBtnBuscar7.setBackground(new java.awt.Color(9, 132, 227));
        jBtnBuscar7.setForeground(new java.awt.Color(9, 132, 227));
        jBtnBuscar7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-black24.png"))); // NOI18N
        jBtnBuscar7.setToolTipText("Click para buscar");
        jBtnBuscar7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnBuscar7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnBuscar7MouseClicked(evt);
            }
        });
        jPnlContrato.add(jBtnBuscar7);
        jBtnBuscar7.setBounds(875, 98, 34, 36);

        jCboModificarEstado.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jPnlContrato.add(jCboModificarEstado);
        jCboModificarEstado.setBounds(700, 390, 210, 40);

        jLblCodAbonado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLblCodAbonado.setText("codAbonado");
        jPnlContrato.add(jLblCodAbonado);
        jLblCodAbonado.setBounds(70, 500, 40, 20);

        jLblIdContrato.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLblIdContrato.setText("idContrato");
        jPnlContrato.add(jLblIdContrato);
        jLblIdContrato.setBounds(30, 490, 30, 40);

        jLblNombreAbonadoMostrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPnlContrato.add(jLblNombreAbonadoMostrar);
        jLblNombreAbonadoMostrar.setBounds(140, 380, 210, 40);

        jLblIdentificador23.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador23.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 18)); // NOI18N
        jLblIdentificador23.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador23.setText("Tipo de contrato");
        jPnlContrato.add(jLblIdentificador23);
        jLblIdentificador23.setBounds(419, 68, 200, 19);

        jLblIdentificador26.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador26.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 18)); // NOI18N
        jLblIdentificador26.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador26.setText("Abonado: ");
        jPnlContrato.add(jLblIdentificador26);
        jLblIdentificador26.setBounds(40, 390, 100, 19);

        jLblIdentificador27.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador27.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 18)); // NOI18N
        jLblIdentificador27.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador27.setText("Identidad:");
        jPnlContrato.add(jLblIdentificador27);
        jLblIdentificador27.setBounds(40, 430, 110, 30);

        jLblNumIdentidadMostrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPnlContrato.add(jLblNumIdentidadMostrar);
        jLblNumIdentidadMostrar.setBounds(140, 430, 180, 30);

        jBtnCancelar.setBackground(new java.awt.Color(41, 128, 185));
        jBtnCancelar.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar-white24.png"))); // NOI18N
        jBtnCancelar.setText("Cancelar ");
        jBtnCancelar.setBorder(null);
        jBtnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelarActionPerformed(evt);
            }
        });
        jPnlContrato.add(jBtnCancelar);
        jBtnCancelar.setBounds(420, 450, 213, 50);

        jTabContrato.addTab("tabContrato", jPnlContrato);

        jPnlListaContrato.setBackground(new java.awt.Color(255, 255, 255));
        jPnlListaContrato.setLayout(null);

        jPnlBuscar6.setBackground(new java.awt.Color(255, 255, 255));
        jPnlBuscar6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jBtnBuscar6.setBackground(new java.awt.Color(9, 132, 227));
        jBtnBuscar6.setForeground(new java.awt.Color(9, 132, 227));
        jBtnBuscar6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-black24.png"))); // NOI18N
        jBtnBuscar6.setToolTipText("Click para buscar");
        jBtnBuscar6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnBuscar6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnBuscar6MouseClicked(evt);
            }
        });

        jTfBuscar6.setBackground(new java.awt.Color(255, 255, 255));
        jTfBuscar6.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 18)); // NOI18N
        jTfBuscar6.setForeground(new java.awt.Color(0, 0, 0));
        jTfBuscar6.setBorder(null);
        jTfBuscar6.setSelectionColor(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPnlBuscar6Layout = new javax.swing.GroupLayout(jPnlBuscar6);
        jPnlBuscar6.setLayout(jPnlBuscar6Layout);
        jPnlBuscar6Layout.setHorizontalGroup(
            jPnlBuscar6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlBuscar6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTfBuscar6, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnBuscar6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPnlBuscar6Layout.setVerticalGroup(
            jPnlBuscar6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlBuscar6Layout.createSequentialGroup()
                .addGroup(jPnlBuscar6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnBuscar6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTfBuscar6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPnlListaContrato.add(jPnlBuscar6);
        jPnlBuscar6.setBounds(500, 30, 280, 40);

        jTblContratos.setBackground(new java.awt.Color(255, 255, 255));
        jTblContratos.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jTblContratos.setForeground(new java.awt.Color(0, 0, 0));
        jTblContratos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Abonado", "Bloque", "Casa", "Tipo de Contrato", "Estado del Contrato", "Identidad del  Abonado", "Direccion del pegue"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTblContratos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblContratosMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTblContratos);

        jPnlListaContrato.add(jScrollPane4);
        jScrollPane4.setBounds(35, 167, 880, 306);

        jLblIdentificador19.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador19.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 18)); // NOI18N
        jLblIdentificador19.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador19.setText("Ver contratos por:");
        jPnlListaContrato.add(jLblIdentificador19);
        jLblIdentificador19.setBounds(35, 32, 147, 40);

        jRBCodigo.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRBCodigo);
        jRBCodigo.setFont(new java.awt.Font("HelveticaNowDisplay Light", 0, 14)); // NOI18N
        jRBCodigo.setForeground(new java.awt.Color(0, 0, 0));
        jRBCodigo.setSelected(true);
        jRBCodigo.setText("Código");
        jRBCodigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBCodigoMouseClicked(evt);
            }
        });
        jPnlListaContrato.add(jRBCodigo);
        jRBCodigo.setBounds(212, 30, 76, 40);

        jRBBloque.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRBBloque);
        jRBBloque.setFont(new java.awt.Font("HelveticaNowDisplay Light", 0, 14)); // NOI18N
        jRBBloque.setForeground(new java.awt.Color(0, 0, 0));
        jRBBloque.setText("Bloque");
        jRBBloque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBBloqueMouseClicked(evt);
            }
        });
        jPnlListaContrato.add(jRBBloque);
        jRBBloque.setBounds(429, 30, 74, 40);

        jRBEstado.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRBEstado);
        jRBEstado.setFont(new java.awt.Font("HelveticaNowDisplay Light", 0, 14)); // NOI18N
        jRBEstado.setForeground(new java.awt.Color(0, 0, 0));
        jRBEstado.setText("Estado");
        jRBEstado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBEstadoMouseClicked(evt);
            }
        });
        jPnlListaContrato.add(jRBEstado);
        jRBEstado.setBounds(358, 30, 74, 40);

        jRBAlfabetico.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRBAlfabetico);
        jRBAlfabetico.setFont(new java.awt.Font("HelveticaNowDisplay Light", 0, 14)); // NOI18N
        jRBAlfabetico.setForeground(new java.awt.Color(0, 0, 0));
        jRBAlfabetico.setText("A - Z");
        jRBAlfabetico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBAlfabeticoActionPerformed(evt);
            }
        });
        jPnlListaContrato.add(jRBAlfabetico);
        jRBAlfabetico.setBounds(293, 30, 60, 40);

        jLblIdentificador21.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador21.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 18)); // NOI18N
        jLblIdentificador21.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador21.setText("Bloque");
        jLblIdentificador21.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPnlListaContrato.add(jLblIdentificador21);
        jLblIdentificador21.setBounds(35, 132, 57, 19);

        jLblIdentificador22.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador22.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 18)); // NOI18N
        jLblIdentificador22.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador22.setText("Estado");
        jLblIdentificador22.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPnlListaContrato.add(jLblIdentificador22);
        jLblIdentificador22.setBounds(263, 132, 57, 19);

        jLblModificarContrato.setBackground(new java.awt.Color(102, 102, 102));
        jLblModificarContrato.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 18)); // NOI18N
        jLblModificarContrato.setForeground(new java.awt.Color(41, 128, 185));
        jLblModificarContrato.setText("Editar el contrato");
        jLblModificarContrato.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLblModificarContrato.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblModificarContratoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLblModificarContratoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLblModificarContratoMouseExited(evt);
            }
        });
        jPnlListaContrato.add(jLblModificarContrato);
        jLblModificarContrato.setBounds(760, 130, 140, 19);

        jLblIdentificador25.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador25.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 18)); // NOI18N
        jLblIdentificador25.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador25.setText("Casa");
        jLblIdentificador25.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPnlListaContrato.add(jLblIdentificador25);
        jLblIdentificador25.setBounds(154, 132, 42, 19);

        jCBEstadoItem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCBEstadoItem.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBEstadoItemItemStateChanged(evt);
            }
        });
        jPnlListaContrato.add(jCBEstadoItem);
        jCBEstadoItem.setBounds(530, 30, 210, 40);

        jCBBloqueItem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCBBloqueItem.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBBloqueItemItemStateChanged(evt);
            }
        });
        jCBBloqueItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBBloqueItemActionPerformed(evt);
            }
        });
        jPnlListaContrato.add(jCBBloqueItem);
        jCBBloqueItem.setBounds(530, 30, 210, 40);

        jTabContrato.addTab("tabLista", jPnlListaContrato);

        jPnlVerContrato.setBackground(new java.awt.Color(255, 255, 255));

        jLblNombreAbonado.setBackground(new java.awt.Color(255, 255, 255));
        jLblNombreAbonado.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 18)); // NOI18N
        jLblNombreAbonado.setForeground(new java.awt.Color(0, 0, 0));
        jLblNombreAbonado.setText("Nombre completo");

        jLblIdentificador47.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador47.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador47.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador47.setText("Nombre completo");

        jLblNumeroIdentidad.setBackground(new java.awt.Color(255, 255, 255));
        jLblNumeroIdentidad.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 18)); // NOI18N
        jLblNumeroIdentidad.setForeground(new java.awt.Color(0, 0, 0));
        jLblNumeroIdentidad.setText("Número de identidad");

        jLblIdentificador43.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador43.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador43.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador43.setText("Número de identidad");

        jLblIdentificador55.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador55.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/hombre-joven-64.png"))); // NOI18N
        jLblIdentificador55.setToolTipText("Masculino");

        jLblIdentificador45.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador45.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador45.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador45.setText("Información personal");

        jPnlSeparatorY.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPnlSeparatorYLayout = new javax.swing.GroupLayout(jPnlSeparatorY);
        jPnlSeparatorY.setLayout(jPnlSeparatorYLayout);
        jPnlSeparatorYLayout.setHorizontalGroup(
            jPnlSeparatorYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPnlSeparatorYLayout.setVerticalGroup(
            jPnlSeparatorYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 473, Short.MAX_VALUE)
        );

        jLblIdentificador49.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador49.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador49.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador49.setText("Buscar contrato");

        javax.swing.GroupLayout jPnlSeparatorXLayout = new javax.swing.GroupLayout(jPnlSeparatorX);
        jPnlSeparatorX.setLayout(jPnlSeparatorXLayout);
        jPnlSeparatorXLayout.setHorizontalGroup(
            jPnlSeparatorXLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPnlSeparatorXLayout.setVerticalGroup(
            jPnlSeparatorXLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jLblIdentificador50.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador50.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador50.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador50.setText("Información del contrato");

        jLblIdentificador51.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador51.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador51.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador51.setText("Bloque");

        jLblIdentificador52.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador52.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador52.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador52.setText("Número de casa");

        jTfInfoBloque.setEditable(false);
        jTfInfoBloque.setBackground(new java.awt.Color(255, 255, 255));
        jTfInfoBloque.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfInfoBloque.setForeground(new java.awt.Color(0, 0, 0));
        jTfInfoBloque.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfInfoBloque.setSelectionColor(new java.awt.Color(0, 153, 153));

        jTfInfoNumCasa.setEditable(false);
        jTfInfoNumCasa.setBackground(new java.awt.Color(255, 255, 255));
        jTfInfoNumCasa.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfInfoNumCasa.setForeground(new java.awt.Color(0, 0, 0));
        jTfInfoNumCasa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfInfoNumCasa.setSelectionColor(new java.awt.Color(0, 153, 153));

        jLblModificarContrato2.setBackground(new java.awt.Color(102, 102, 102));
        jLblModificarContrato2.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblModificarContrato2.setForeground(new java.awt.Color(41, 128, 185));
        jLblModificarContrato2.setText("Modificar contrato");
        jLblModificarContrato2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblModificarContrato2MouseClicked(evt);
            }
        });

        jLblIdentificador54.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador54.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador54.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador54.setText("Cód. del Contrato:");

        jTfInfoIdContrato.setEditable(false);
        jTfInfoIdContrato.setBackground(new java.awt.Color(255, 255, 255));
        jTfInfoIdContrato.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfInfoIdContrato.setForeground(new java.awt.Color(0, 0, 0));
        jTfInfoIdContrato.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfInfoIdContrato.setSelectionColor(new java.awt.Color(0, 153, 153));

        jLblIdentificador56.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador56.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador56.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador56.setText("Tipo de contrato");

        jTfInfoTipoContrato.setEditable(false);
        jTfInfoTipoContrato.setBackground(new java.awt.Color(255, 255, 255));
        jTfInfoTipoContrato.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfInfoTipoContrato.setForeground(new java.awt.Color(0, 0, 0));
        jTfInfoTipoContrato.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfInfoTipoContrato.setSelectionColor(new java.awt.Color(0, 153, 153));

        jLblIdentificador57.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador57.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador57.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador57.setText("Estado contrato");

        jTfInfoEstadoContrato.setEditable(false);
        jTfInfoEstadoContrato.setBackground(new java.awt.Color(255, 255, 255));
        jTfInfoEstadoContrato.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfInfoEstadoContrato.setForeground(new java.awt.Color(0, 0, 0));
        jTfInfoEstadoContrato.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfInfoEstadoContrato.setSelectionColor(new java.awt.Color(0, 153, 153));

        jLblIdentificador58.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador58.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador58.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador58.setText("Dirección pegue");

        jTextAreaInfoDireccion.setBackground(new java.awt.Color(255, 255, 255));
        jTextAreaInfoDireccion.setColumns(20);
        jTextAreaInfoDireccion.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jTextAreaInfoDireccion.setForeground(new java.awt.Color(0, 0, 0));
        jTextAreaInfoDireccion.setRows(5);
        jScrollPane5.setViewportView(jTextAreaInfoDireccion);

        jLblIdentificador59.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador59.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador59.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador59.setText("Servicios contratados");

        jTblServiciosDeContrato.setBackground(new java.awt.Color(255, 255, 255));
        jTblServiciosDeContrato.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jTblServiciosDeContrato.setForeground(new java.awt.Color(0, 0, 0));
        jTblServiciosDeContrato.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Servicios", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(jTblServiciosDeContrato);

        jBtnBusquedaAvanzada.setBackground(new java.awt.Color(41, 128, 185));
        jBtnBusquedaAvanzada.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnBusquedaAvanzada.setForeground(new java.awt.Color(255, 255, 255));
        jBtnBusquedaAvanzada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-blanco24.png"))); // NOI18N
        jBtnBusquedaAvanzada.setText("BUSCAR CONTRATO");
        jBtnBusquedaAvanzada.setBorder(null);
        jBtnBusquedaAvanzada.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnBusquedaAvanzada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBusquedaAvanzadaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPnlVerContratoLayout = new javax.swing.GroupLayout(jPnlVerContrato);
        jPnlVerContrato.setLayout(jPnlVerContratoLayout);
        jPnlVerContratoLayout.setHorizontalGroup(
            jPnlVerContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlVerContratoLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPnlVerContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIdentificador49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPnlVerContratoLayout.createSequentialGroup()
                        .addGroup(jPnlVerContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBtnBusquedaAvanzada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPnlSeparatorX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPnlVerContratoLayout.createSequentialGroup()
                                .addGroup(jPnlVerContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLblNombreAbonado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLblIdentificador47, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLblIdentificador45, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                                    .addComponent(jLblIdentificador43, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLblNumeroIdentidad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPnlVerContratoLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLblIdentificador55, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 54, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPnlSeparatorY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPnlVerContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPnlVerContratoLayout.createSequentialGroup()
                        .addGroup(jPnlVerContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLblIdentificador51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTfInfoBloque)
                            .addComponent(jLblIdentificador56, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .addComponent(jTfInfoTipoContrato, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLblIdentificador59, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(43, 43, 43)
                        .addGroup(jPnlVerContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTfInfoNumCasa)
                            .addComponent(jLblIdentificador52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLblIdentificador57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTfInfoEstadoContrato)
                            .addComponent(jLblIdentificador58, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                            .addComponent(jScrollPane5)))
                    .addGroup(jPnlVerContratoLayout.createSequentialGroup()
                        .addComponent(jLblIdentificador50)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLblModificarContrato2))
                    .addGroup(jPnlVerContratoLayout.createSequentialGroup()
                        .addComponent(jLblIdentificador54, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jTfInfoIdContrato)))
                .addGap(44, 44, 44))
        );
        jPnlVerContratoLayout.setVerticalGroup(
            jPnlVerContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlVerContratoLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPnlVerContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPnlVerContratoLayout.createSequentialGroup()
                        .addGroup(jPnlVerContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLblIdentificador50, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLblModificarContrato2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPnlVerContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLblIdentificador54, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTfInfoIdContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPnlVerContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLblIdentificador51, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLblIdentificador52, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPnlVerContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTfInfoBloque, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTfInfoNumCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPnlVerContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLblIdentificador56, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLblIdentificador57, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPnlVerContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTfInfoTipoContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTfInfoEstadoContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPnlVerContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLblIdentificador58, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLblIdentificador59, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPnlVerContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane5)))
                    .addGroup(jPnlVerContratoLayout.createSequentialGroup()
                        .addComponent(jLblIdentificador49, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnBusquedaAvanzada, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPnlSeparatorX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLblIdentificador45, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLblIdentificador55)
                        .addGap(18, 18, 18)
                        .addComponent(jLblIdentificador43, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLblNumeroIdentidad, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLblIdentificador47, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLblNombreAbonado, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPnlSeparatorY, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jTabContrato.addTab("tabPrint", jPnlVerContrato);

        jPanel1.add(jTabContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 1030, 540));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnSideBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnSideBarMouseClicked
        // TODO add your handling code here:
        this.sideBar.jTextAreaXRight(-210, 0 , 5, 5, jSideBar1);
        this.jBtnSideBar.setVisible(false);
        this.jBtnSideBar1.setVisible(true);
    }//GEN-LAST:event_jBtnSideBarMouseClicked

    private void jBtnSideBar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnSideBar1MouseClicked
        // TODO add your handling code here:
        this.sideBar.jTextAreaXLeft(0, -210 , 5, 5, jSideBar1);
        this.jBtnSideBar1.setVisible(false);
        this.jBtnSideBar.setVisible(true);
    }//GEN-LAST:event_jBtnSideBar1MouseClicked

    private void jLblModificarContratoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblModificarContratoMouseEntered
        // TODO add your handling code here:
        this.jLblModificarContrato.setForeground(Color.BLUE);
    }//GEN-LAST:event_jLblModificarContratoMouseEntered

    private void jLblModificarContratoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblModificarContratoMouseExited
        // TODO add your handling code here:
        this.jLblModificarContrato.setForeground(azul);
    }//GEN-LAST:event_jLblModificarContratoMouseExited

    private void jSBListadoContratoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSBListadoContratoMouseClicked
        // TODO add your handling code here:
        //Color celeste = new Color(52,152,219);
        this.jTabContrato.setSelectedIndex(1);
        this.jSBContrato.setBackground(azul);
        this.jSBListadoContrato.setBackground(celeste);
        this.jSBVerContrato.setBackground(azul);
        this.jBtnSideBar.setVisible(true);
        this.jBtnSideBar1.setVisible(false);
    }//GEN-LAST:event_jSBListadoContratoMouseClicked

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
        if(validarEntradas()){   
            insertarContrato();
            insertarFilas();
            limpiarEntradas();
            try {
                llenarTablaContratos();
            } catch (SQLException ex) {
                Logger.getLogger(JFraContratos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
    }//GEN-LAST:event_jBtnGuardarActionPerformed

    private void jCKBActivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCKBActivoActionPerformed
        if (this.jCKBActivo.isSelected()) {
                this.jCBEstadoContrato.setEnabled(false);
            } else if (!this.jCKBActivo.isSelected()) {
                this.jCBEstadoContrato.setEnabled(true); 
            }
    }//GEN-LAST:event_jCKBActivoActionPerformed

    private void jSBContratoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSBContratoMouseClicked
        //Color celeste = new Color(52,152,219);
        this.jTabContrato.setSelectedIndex(0);
        this.jSBContrato.setBackground(celeste);
        this.jSBListadoContrato.setBackground(azul);
        this.jSBVerContrato.setBackground(azul);
        this.jBtnSideBar.setVisible(true);
        this.jBtnSideBar1.setVisible(false);
    }//GEN-LAST:event_jSBContratoMouseClicked

    private void jTblContratosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblContratosMouseClicked
        EnabledBTN(true,true);

    }//GEN-LAST:event_jTblContratosMouseClicked

    private void jLblModificarContratoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblModificarContratoMouseClicked
        this.jBtnEditar.setVisible(true);
        this.jBtnGuardar.setVisible(false);
        try {
            cargarEstadoContratoEdicion();
            mostrarInformacionModificar();
            eliminarServiciosContratoUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(JFraContratos.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.jTabContrato.setSelectedIndex(0);
        EnabledBTN(false,true);
        this.jSBContrato.setBackground(celeste);
        this.jSBListadoContrato.setBackground(azul);
        this.jCboModificarEstado.setVisible(true);
        this.jCBEstadoContrato.setVisible(false);
        this.jCKBActivo.setVisible(false);
        cargarDatos();
    }//GEN-LAST:event_jLblModificarContratoMouseClicked

    private void jBtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEditarActionPerformed
        this.jBtnEditar.setVisible(false);
        this.jBtnGuardar.setVisible(true);
        if(validarEntradas()){
            
            updateContrato();
            limpiarEntradas();
        }
        this.jCboModificarEstado.setVisible(false);
        this.jCBEstadoContrato.setVisible(true);
        this.jCKBActivo.setVisible(true);
        this.jTabContrato.setSelectedIndex(1);
        
    }//GEN-LAST:event_jBtnEditarActionPerformed

    private void jBtnBuscar6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnBuscar6MouseClicked
        if(this.jRBCodigo.isSelected()){
            try {
                llenarTablaContratosCod(this.jTfBuscar6.getText());
            } catch (SQLException ex) {
                 Logger.getLogger(JFraContratos.class.getName()).log(Level.SEVERE, null, ex);      
            }
        }else if(this.jRBEstado.isSelected()){
            //
        }
    }//GEN-LAST:event_jBtnBuscar6MouseClicked

    private void jRBAlfabeticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBAlfabeticoActionPerformed
        
        this.jPnlBuscar6.setVisible(false);
        this.jCBBloqueItem.setVisible(false);
        this.jCBEstadoItem.setVisible(false);
        try {
                llenarTablaContratos();
            } catch (SQLException ex) {
                Logger.getLogger(JFraContratos.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_jRBAlfabeticoActionPerformed

    private void jLblIdentificadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblIdentificadorMouseClicked
        this.jTabContrato.setSelectedIndex(0);
        this.jSBContrato.setBackground(celeste);
        this.jSBListadoContrato.setBackground(azul);
        this.jSBVerContrato.setBackground(azul);
        
    }//GEN-LAST:event_jLblIdentificadorMouseClicked

    private void jLblIdentificador2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblIdentificador2MouseClicked
        this.jTabContrato.setSelectedIndex(1);
        this.jSBContrato.setBackground(azul);
        this.jSBListadoContrato.setBackground(celeste);
        this.jSBVerContrato.setBackground(azul);
    }//GEN-LAST:event_jLblIdentificador2MouseClicked

    private void jLblIdentificador13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblIdentificador13MouseClicked
        this.jTabContrato.setSelectedIndex(2);
        this.jSBContrato.setBackground(azul);
        this.jSBListadoContrato.setBackground(azul);
        this.jSBVerContrato.setBackground(celeste);
    }//GEN-LAST:event_jLblIdentificador13MouseClicked

    private void jSBVerContratoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSBVerContratoMouseClicked
        // TODO add your handling code here:
        this.jTabContrato.setSelectedIndex(2);
        this.jSBContrato.setBackground(azul);
        this.jSBListadoContrato.setBackground(azul);
        this.jSBVerContrato.setBackground(celeste);
        this.jBtnSideBar.setVisible(true);
        this.jBtnSideBar1.setVisible(false);
    }//GEN-LAST:event_jSBVerContratoMouseClicked

    private void jCBBloqueItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBBloqueItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBBloqueItemActionPerformed

    private void jRBEstadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBEstadoMouseClicked
        this.jPnlBuscar6.setVisible(false);
        this.jCBBloqueItem.setVisible(false);
        this.jCBEstadoItem.setVisible(true);
        
    }//GEN-LAST:event_jRBEstadoMouseClicked

    private void jRBBloqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBBloqueMouseClicked
        this.jPnlBuscar6.setVisible(false);
        this.jCBBloqueItem.setVisible(true);
        this.jCBEstadoItem.setVisible(false);
        
    }//GEN-LAST:event_jRBBloqueMouseClicked

    private void jRBCodigoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBCodigoMouseClicked
        this.jCBBloqueItem.setVisible(false);
        this.jCBEstadoItem.setVisible(false);
        this.jPnlBuscar6.setVisible(true);
        
    }//GEN-LAST:event_jRBCodigoMouseClicked

    private void jCBEstadoItemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBEstadoItemItemStateChanged
        try {
            llenarTablaContratosEstado(this.jCBEstadoItem.getSelectedItem().toString());
        } catch (SQLException ex) {
            Logger.getLogger(JFraContratos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jCBEstadoItemItemStateChanged

    private void jCBBloqueItemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBBloqueItemItemStateChanged
        try {
            llenarTablaContratosBloque(this.jCBBloqueItem.getSelectedItem().toString());
        } catch (SQLException ex) {
            Logger.getLogger(JFraContratos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jCBBloqueItemItemStateChanged

    private void jBtnBuscar7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnBuscar7MouseClicked
        this.jDialogServicios.setVisible(true);
        
    }//GEN-LAST:event_jBtnBuscar7MouseClicked

    private void jBtnAgregarAContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAgregarAContratoActionPerformed
        this.jDialogServicios.setVisible(false);
    }//GEN-LAST:event_jBtnAgregarAContratoActionPerformed

    private void jBtnDeshacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDeshacerActionPerformed
        try {
            cargarDataModelServicios();
        } catch (SQLException ex) {
            Logger.getLogger(JFraContratos.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.jBtnDeshacer.setEnabled(false);
    }//GEN-LAST:event_jBtnDeshacerActionPerformed

    private void jBtnQuitarServicio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnQuitarServicio1ActionPerformed
        eliminarFilaServicioPrecio();
        this.jBtnDeshacer.setEnabled(true);
    }//GEN-LAST:event_jBtnQuitarServicio1ActionPerformed

    private void jCBTipoContratoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBTipoContratoItemStateChanged
        try {
            cargarDataModelServicios();
        } catch (SQLException ex) {
            Logger.getLogger(JFraContratos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jCBTipoContratoItemStateChanged

    private void jTblAbonadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblAbonadosMouseClicked
        informacionUsuario();
    }//GEN-LAST:event_jTblAbonadosMouseClicked

    private void jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelarActionPerformed
        this.jBtnEditar.setVisible(false);
        this.jBtnGuardar.setVisible(true);
        this.jBtnGuardar.setEnabled(true);
        this.jCboModificarEstado.setVisible(false);
        this.jCBEstadoContrato.setVisible(true);
        this.jCKBActivo.setVisible(true);
        limpiarEntradas();
    }//GEN-LAST:event_jBtnCancelarActionPerformed

    private void jTfBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTfBuscarKeyReleased
        try {
            busquedaListaAbonados(this.jTfBuscar.getText());
        } catch (SQLException ex) {
            Logger.getLogger(JFraContratos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTfBuscarKeyReleased

    private void jRbBusquedaPegueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRbBusquedaPegueActionPerformed
        this.jTfBusquedaNumIdentidad.setVisible(false);
        this.jLblIdentidadBusqueda.setVisible(false);
        this.jTfBusquedaBloque.setVisible(true);
        this.jTfBusquedaCasa.setVisible(true);
        this.jLblbloqueBusqueda.setVisible(true);
        this.jLblcasabusqueda.setVisible(true);
        this.jBtnBusquedaCasaBloque.setVisible(true);
    }//GEN-LAST:event_jRbBusquedaPegueActionPerformed

    private void jRbBusquedaIdentidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRbBusquedaIdentidaActionPerformed
        this.jTfBusquedaNumIdentidad.setVisible(true);
        this.jLblIdentidadBusqueda.setVisible(true);
        this.jTfBusquedaBloque.setVisible(false);
        this.jTfBusquedaCasa.setVisible(false);
        this.jLblbloqueBusqueda.setVisible(false);
        this.jLblcasabusqueda.setVisible(false);
        this.jBtnBusquedaCasaBloque.setVisible(false);
    }//GEN-LAST:event_jRbBusquedaIdentidaActionPerformed

    private void jTfBusquedaNumIdentidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTfBusquedaNumIdentidadKeyReleased
        try {
            cargarModalBusquedaContratoAbonado(this.jTfBusquedaNumIdentidad.getText());
        } catch (SQLException ex) {
            Logger.getLogger(JFraContratos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTfBusquedaNumIdentidadKeyReleased

    private void jBtnBusquedaCasaBloqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnBusquedaCasaBloqueMouseClicked
        String bloque = this.jTfBusquedaBloque.getText();
        String numCasa = this.jTfBusquedaCasa.getText();
        try {
            cargarModalBusquedaContrato( bloque,  numCasa);
        } catch (SQLException ex) {
            Logger.getLogger(JFraContratos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnBusquedaCasaBloqueMouseClicked

    private void jBtnBusquedaAvanzadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBusquedaAvanzadaActionPerformed
        this.jDialogBusquedaContratos.setVisible(true);
    }//GEN-LAST:event_jBtnBusquedaAvanzadaActionPerformed

    private void jBtnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSeleccionarActionPerformed
        try {
            informacionCompletaContrato();
        } catch (SQLException ex) {
            Logger.getLogger(JFraContratos.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.jDialogBusquedaContratos.setVisible(false);
    }//GEN-LAST:event_jBtnSeleccionarActionPerformed

    private void jBtnCancelarBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelarBusquedaActionPerformed
        this.jDialogBusquedaContratos.setVisible(false);
    }//GEN-LAST:event_jBtnCancelarBusquedaActionPerformed

    private void jLblModificarContrato2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblModificarContrato2MouseClicked
        
        if(this.jLblNumeroIdentidad.getText()!=""){
            this.jTabContrato.setSelectedIndex(1);
            try {
                llenarTablaContratosCod(this.jLblNumeroIdentidad.getText());
            } catch (SQLException ex) {
                Logger.getLogger(JFraContratos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_jLblModificarContrato2MouseClicked

    private void jLblMinimizar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblMinimizar2MouseClicked
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_jLblMinimizar2MouseClicked

    private void jLblSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblSalirMouseClicked
        this.dispose();
    }//GEN-LAST:event_jLblSalirMouseClicked

    private void jBtnSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSiActionPerformed
        this.dispose();
        this.jDfMenu.dispose();
        jfm.setVisible(true);
    }//GEN-LAST:event_jBtnSiActionPerformed

    private void JBtnNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBtnNoActionPerformed
        this.jDfMenu.dispose();
    }//GEN-LAST:event_JBtnNoActionPerformed

    private void jSBMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSBMenuMouseClicked
        this.jDfMenu.setLocationRelativeTo(null);
        this.jDfMenu.setVisible(true);
    }//GEN-LAST:event_jSBMenuMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFraContratos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFraContratos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFraContratos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFraContratos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFraContratos().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(JFraContratos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBtnNo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jBtnAgregarAContrato;
    private javax.swing.JLabel jBtnBuscar;
    private javax.swing.JLabel jBtnBuscar6;
    private javax.swing.JLabel jBtnBuscar7;
    private javax.swing.JButton jBtnBusquedaAvanzada;
    private javax.swing.JLabel jBtnBusquedaCasaBloque;
    private javax.swing.JButton jBtnCancelar;
    private javax.swing.JButton jBtnCancelarBusqueda;
    private javax.swing.JButton jBtnDeshacer;
    private javax.swing.JButton jBtnEditar;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JButton jBtnQuitarServicio1;
    private javax.swing.JButton jBtnSeleccionar;
    private javax.swing.JButton jBtnSi;
    private javax.swing.JLabel jBtnSideBar;
    private javax.swing.JLabel jBtnSideBar1;
    private javax.swing.JComboBox<String> jCBBloque;
    private javax.swing.JComboBox<String> jCBBloqueItem;
    private javax.swing.JComboBox<String> jCBEstadoContrato;
    private javax.swing.JComboBox<String> jCBEstadoItem;
    private javax.swing.JComboBox<String> jCBTipoContrato;
    private javax.swing.JCheckBox jCKBActivo;
    private javax.swing.JComboBox<String> jCboModificarEstado;
    private javax.swing.JDialog jDfMenu;
    private javax.swing.JDialog jDialogBusquedaContratos;
    private javax.swing.JDialog jDialogServicios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLblCodAbonado;
    private javax.swing.JLabel jLblIdContrato;
    private javax.swing.JLabel jLblIdentidadBusqueda;
    private javax.swing.JLabel jLblIdentificador;
    private javax.swing.JLabel jLblIdentificador1;
    private javax.swing.JLabel jLblIdentificador10;
    private javax.swing.JLabel jLblIdentificador11;
    private javax.swing.JLabel jLblIdentificador12;
    private javax.swing.JLabel jLblIdentificador13;
    private javax.swing.JLabel jLblIdentificador14;
    private javax.swing.JLabel jLblIdentificador15;
    private javax.swing.JLabel jLblIdentificador16;
    private javax.swing.JLabel jLblIdentificador18;
    private javax.swing.JLabel jLblIdentificador19;
    private javax.swing.JLabel jLblIdentificador2;
    private javax.swing.JLabel jLblIdentificador21;
    private javax.swing.JLabel jLblIdentificador22;
    private javax.swing.JLabel jLblIdentificador23;
    private javax.swing.JLabel jLblIdentificador24;
    private javax.swing.JLabel jLblIdentificador25;
    private javax.swing.JLabel jLblIdentificador26;
    private javax.swing.JLabel jLblIdentificador27;
    private javax.swing.JLabel jLblIdentificador3;
    private javax.swing.JLabel jLblIdentificador43;
    private javax.swing.JLabel jLblIdentificador45;
    private javax.swing.JLabel jLblIdentificador47;
    private javax.swing.JLabel jLblIdentificador49;
    private javax.swing.JLabel jLblIdentificador5;
    private javax.swing.JLabel jLblIdentificador50;
    private javax.swing.JLabel jLblIdentificador51;
    private javax.swing.JLabel jLblIdentificador52;
    private javax.swing.JLabel jLblIdentificador54;
    private javax.swing.JLabel jLblIdentificador55;
    private javax.swing.JLabel jLblIdentificador56;
    private javax.swing.JLabel jLblIdentificador57;
    private javax.swing.JLabel jLblIdentificador58;
    private javax.swing.JLabel jLblIdentificador59;
    private javax.swing.JLabel jLblIdentificador60;
    private javax.swing.JLabel jLblIdentificador64;
    private javax.swing.JLabel jLblIdentificador71;
    private javax.swing.JLabel jLblIdentificador73;
    private javax.swing.JLabel jLblIdentificador9;
    private javax.swing.JLabel jLblMinimizar2;
    private javax.swing.JLabel jLblModificarContrato;
    private javax.swing.JLabel jLblModificarContrato2;
    private javax.swing.JLabel jLblNombreAbonado;
    private javax.swing.JLabel jLblNombreAbonadoMostrar;
    private javax.swing.JLabel jLblNumIdentidadMostrar;
    private javax.swing.JLabel jLblNumeroIdentidad;
    private javax.swing.JLabel jLblSalir;
    private javax.swing.JLabel jLblTipoContrato;
    private javax.swing.JLabel jLblTitulo2;
    private javax.swing.JLabel jLblTitulo3;
    private javax.swing.JLabel jLblbloqueBusqueda;
    private javax.swing.JLabel jLblcasabusqueda;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPnlBuscar;
    private javax.swing.JPanel jPnlBuscar6;
    private javax.swing.JPanel jPnlContrato;
    private javax.swing.JPanel jPnlListaContrato;
    private javax.swing.JPanel jPnlSeparatorX;
    private javax.swing.JPanel jPnlSeparatorY;
    private javax.swing.JPanel jPnlVerContrato;
    private javax.swing.JRadioButton jRBAlfabetico;
    private javax.swing.JRadioButton jRBBloque;
    private javax.swing.JRadioButton jRBCodigo;
    private javax.swing.JRadioButton jRBEstado;
    private javax.swing.JRadioButton jRbBusquedaIdentida;
    private javax.swing.JRadioButton jRbBusquedaPegue;
    private javax.swing.JPanel jSBContrato;
    private javax.swing.JPanel jSBListadoContrato;
    private javax.swing.JPanel jSBMenu;
    private javax.swing.JPanel jSBVerContrato;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JPanel jSeparatorX1;
    private javax.swing.JPanel jSeparatorY;
    private javax.swing.JPanel jSideBar;
    private javax.swing.JScrollPane jSideBar1;
    private javax.swing.JTextArea jTADireccionPegue;
    private javax.swing.JTextField jTFNumCasa;
    private javax.swing.JTabbedPane jTabContrato;
    private javax.swing.JTable jTblAbonados;
    private javax.swing.JTable jTblBusquedaContratos;
    private javax.swing.JTable jTblContratos;
    private javax.swing.JTable jTblPrecioServicio;
    private javax.swing.JTable jTblServiciosDeContrato;
    private javax.swing.JTextArea jTextAreaInfoDireccion;
    private javax.swing.JTextField jTfBuscar;
    private javax.swing.JTextField jTfBuscar6;
    private javax.swing.JTextField jTfBusquedaBloque;
    private javax.swing.JTextField jTfBusquedaCasa;
    private javax.swing.JTextField jTfBusquedaNumIdentidad;
    private javax.swing.JTextField jTfInfoBloque;
    private javax.swing.JTextField jTfInfoEstadoContrato;
    private javax.swing.JTextField jTfInfoIdContrato;
    private javax.swing.JTextField jTfInfoNumCasa;
    private javax.swing.JTextField jTfInfoTipoContrato;
    private javax.swing.JTextField jTfNombresServicios;
    // End of variables declaration//GEN-END:variables

    private Color Color(int i, int i0, int i1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
