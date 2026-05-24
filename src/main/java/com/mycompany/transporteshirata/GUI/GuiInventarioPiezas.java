package com.mycompany.transporteshirata.GUI;

import com.mycompany.transporteshirata.Datos.MovimientoInventarioDao;
import com.mycompany.transporteshirata.Datos.PiezaDao;
import com.mycompany.transporteshirata.Logica.MovimientoInventario;
import com.mycompany.transporteshirata.Logica.Pieza;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class GuiInventarioPiezas extends javax.swing.JInternalFrame {

    private int idPiezaSeleccionada = 0;

    public GuiInventarioPiezas() {
        initComponents();
        this.setTitle("     Inventario de Piezas     ");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (pantalla.width - this.getWidth());
        int y = (pantalla.height - this.getHeight());
        this.setLocation(x / 2 + 150, y / 2 - 40);
        cargarTablaPiezas();
        cargarTablaMovimientos();
        cambiarAModoNuevo();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmb_categoria = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txt_marca = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_stock = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_stock_minimo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_ubicacion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cmb_estado = new javax.swing.JComboBox<>();
        bt_guardar = new javax.swing.JButton();
        bt_editar = new javax.swing.JButton();
        bt_eliminar = new javax.swing.JButton();
        bt_cancelar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_piezas = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cmb_movimiento = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txt_cantidad = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_tecnico = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_motivo = new javax.swing.JTextField();
        bt_registrar_movimiento = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_movimientos = new javax.swing.JTable();

        setClosable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pieza de repuesto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14)));

        jLabel1.setText("ID");
        txt_id.setEditable(false);

        jLabel2.setText("Codigo");
        jLabel3.setText("Nombre");
        jLabel4.setText("Categoria");
        cmb_categoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Hardware", "Almacenamiento", "Memoria", "Impresion", "Telefonia", "Otro"}));
        jLabel5.setText("Marca");
        jLabel6.setText("Stock");
        jLabel7.setText("Stock minimo");
        jLabel8.setText("Ubicacion");
        jLabel9.setText("Estado");
        cmb_estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"disponible", "stock bajo", "sin stock"}));

        bt_guardar.setText("Guardar");
        bt_guardar.addActionListener(evt -> bt_guardarActionPerformed(evt));
        bt_editar.setText("Editar");
        bt_editar.addActionListener(evt -> bt_editarActionPerformed(evt));
        bt_eliminar.setText("Eliminar");
        bt_eliminar.addActionListener(evt -> bt_eliminarActionPerformed(evt));
        bt_cancelar.setText("Cancelar");
        bt_cancelar.addActionListener(evt -> bt_cancelarActionPerformed(evt));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_id)
                                        .addComponent(txt_codigo)
                                        .addComponent(txt_nombre)
                                        .addComponent(cmb_categoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_marca)
                                        .addComponent(txt_stock)
                                        .addComponent(txt_stock_minimo)
                                        .addComponent(txt_ubicacion)
                                        .addComponent(cmb_estado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(bt_guardar, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                        .addComponent(bt_eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(bt_editar, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                        .addComponent(bt_cancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel1).addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel2).addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel3).addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel4).addComponent(cmb_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel5).addComponent(txt_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel6).addComponent(txt_stock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel7).addComponent(txt_stock_minimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel8).addComponent(txt_ubicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel9).addComponent(cmb_estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(bt_guardar).addComponent(bt_editar))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(bt_eliminar).addComponent(bt_cancelar))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Listado de piezas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14)));
        tbl_piezas.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, new String[]{"ID", "Codigo", "Nombre", "Categoria", "Stock", "Minimo", "Estado"}));
        tbl_piezas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_piezasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_piezas);
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE).addContainerGap()));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap()));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Movimiento de stock", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14)));
        jLabel10.setText("Tipo");
        cmb_movimiento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"entrada", "salida", "ajuste"}));
        jLabel11.setText("Cantidad");
        jLabel12.setText("Responsable");
        jLabel13.setText("Motivo");
        bt_registrar_movimiento.setText("Registrar movimiento");
        bt_registrar_movimiento.addActionListener(evt -> bt_registrar_movimientoActionPerformed(evt));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmb_movimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_tecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_motivo)
                                .addGap(18, 18, 18)
                                .addComponent(bt_registrar_movimiento)
                                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel10).addComponent(cmb_movimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel11).addComponent(txt_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel12).addComponent(txt_tecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel13).addComponent(txt_motivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bt_registrar_movimiento))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Movimientos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14)));
        tbl_movimientos.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, new String[]{"Fecha", "Tipo", "Pieza", "Cantidad", "Responsable", "Motivo"}));
        jScrollPane2.setViewportView(tbl_movimientos);
        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane2).addContainerGap()));
        jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap()));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(layout.createSequentialGroup().addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))).addContainerGap()));
        pack();
    }

    private void cargarTablaPiezas() {
        String col[] = {"ID", "Codigo", "Nombre", "Categoria", "Stock", "Minimo", "Estado"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
        for (Pieza p : piezaDao.listarPiezas()) {
            tableModel.addRow(new Object[]{p.getIdPieza(), p.getCodigo(), p.getNombre(), p.getCategoria(), p.getStockActual(), p.getStockMinimo(), p.getEstado()});
        }
        tbl_piezas.setModel(tableModel);
    }

    private void cargarTablaMovimientos() {
        String col[] = {"Fecha", "Tipo", "Pieza", "Cantidad", "Responsable", "Motivo"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
        for (MovimientoInventario m : movimientoDao.listarMovimientos()) {
            tableModel.addRow(new Object[]{m.getFecha(), m.getTipoMovimiento(), m.getPieza().getCodigo(), m.getCantidad(), m.getTecnicoResponsable(), m.getMotivo()});
        }
        tbl_movimientos.setModel(tableModel);
    }

    private void cambiarAModoNuevo() {
        bt_guardar.setEnabled(true);
        bt_editar.setEnabled(false);
        bt_eliminar.setEnabled(false);
        limpiarFormulario();
    }

    private void cambiarAModoEdicion() {
        bt_guardar.setEnabled(false);
        bt_editar.setEnabled(true);
        bt_eliminar.setEnabled(true);
    }

    private void limpiarFormulario() {
        idPiezaSeleccionada = 0;
        txt_id.setText("");
        txt_codigo.setText("");
        txt_nombre.setText("");
        txt_marca.setText("");
        txt_stock.setText("");
        txt_stock_minimo.setText("");
        txt_ubicacion.setText("");
        txt_cantidad.setText("");
        txt_tecnico.setText("");
        txt_motivo.setText("");
        cmb_categoria.setSelectedIndex(0);
        cmb_estado.setSelectedIndex(0);
        cmb_movimiento.setSelectedIndex(0);
        tbl_piezas.clearSelection();
    }

    private boolean validarFormulario() {
        if (txt_codigo.getText().trim().isEmpty() || txt_nombre.getText().trim().isEmpty()
                || txt_stock.getText().trim().isEmpty() || txt_stock_minimo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete codigo, nombre, stock y stock minimo.");
            return false;
        }
        try {
            int stock = Integer.parseInt(txt_stock.getText());
            int minimo = Integer.parseInt(txt_stock_minimo.getText());
            if (stock < 0 || minimo < 0) {
                JOptionPane.showMessageDialog(this, "El stock no puede ser negativo.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Stock y stock minimo deben ser numeros.");
            return false;
        }
        return true;
    }

    private Pieza obtenerPiezaDesdeFormulario() {
        Pieza p = new Pieza();
        if (!txt_id.getText().trim().isEmpty()) {
            p.setIdPieza(Integer.parseInt(txt_id.getText()));
        }
        p.setCodigo(txt_codigo.getText().trim());
        p.setNombre(txt_nombre.getText().trim());
        p.setCategoria(cmb_categoria.getSelectedItem().toString());
        p.setMarca(txt_marca.getText().trim());
        p.setStockActual(Integer.parseInt(txt_stock.getText()));
        p.setStockMinimo(Integer.parseInt(txt_stock_minimo.getText()));
        p.setUbicacion(txt_ubicacion.getText().trim());
        p.setEstado(cmb_estado.getSelectedItem().toString());
        return p;
    }

    private void bt_guardarActionPerformed(java.awt.event.ActionEvent evt) {
        if (!validarFormulario()) {
            return;
        }
        Pieza p = obtenerPiezaDesdeFormulario();
        if (piezaDao.existeCodigo(p.getCodigo(), 0)) {
            JOptionPane.showMessageDialog(this, "Ya existe una pieza registrada con ese codigo.");
            return;
        }
        if (piezaDao.registrarPieza(p)) {
            JOptionPane.showMessageDialog(this, "Pieza registrada correctamente.");
            cargarTablaPiezas();
            cambiarAModoNuevo();
        }
    }

    private void bt_editarActionPerformed(java.awt.event.ActionEvent evt) {
        if (txt_id.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione una pieza de la tabla.");
            return;
        }
        if (!validarFormulario()) {
            return;
        }
        Pieza p = obtenerPiezaDesdeFormulario();
        if (piezaDao.existeCodigo(p.getCodigo(), p.getIdPieza())) {
            JOptionPane.showMessageDialog(this, "Ya existe otra pieza registrada con ese codigo.");
            return;
        }
        if (piezaDao.modificarPieza(p)) {
            JOptionPane.showMessageDialog(this, "Pieza actualizada correctamente.");
            cargarTablaPiezas();
            cambiarAModoNuevo();
        }
    }

    private void bt_eliminarActionPerformed(java.awt.event.ActionEvent evt) {
        if (txt_id.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione una pieza de la tabla.");
            return;
        }
        int confirmacion = JOptionPane.showConfirmDialog(this, "Esta seguro de eliminar esta pieza?", "Confirmar eliminacion", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION && piezaDao.eliminarPieza(Integer.parseInt(txt_id.getText()))) {
            JOptionPane.showMessageDialog(this, "Pieza eliminada correctamente.");
            cargarTablaPiezas();
            cambiarAModoNuevo();
        }
    }

    private void bt_cancelarActionPerformed(java.awt.event.ActionEvent evt) {
        cambiarAModoNuevo();
    }

    private void tbl_piezasMouseClicked(java.awt.event.MouseEvent evt) {
        int fila = tbl_piezas.getSelectedRow();
        if (fila != -1) {
            idPiezaSeleccionada = (int) tbl_piezas.getValueAt(fila, 0);
            Pieza p = piezaDao.buscarPorId(idPiezaSeleccionada);
            if (p != null) {
                txt_id.setText(String.valueOf(p.getIdPieza()));
                txt_codigo.setText(p.getCodigo());
                txt_nombre.setText(p.getNombre());
                cmb_categoria.setSelectedItem(p.getCategoria());
                txt_marca.setText(p.getMarca());
                txt_stock.setText(String.valueOf(p.getStockActual()));
                txt_stock_minimo.setText(String.valueOf(p.getStockMinimo()));
                txt_ubicacion.setText(p.getUbicacion());
                cmb_estado.setSelectedItem(p.getEstado());
                cambiarAModoEdicion();
            }
        }
    }

    private void bt_registrar_movimientoActionPerformed(java.awt.event.ActionEvent evt) {
        if (idPiezaSeleccionada == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una pieza de la tabla.");
            return;
        }
        if (txt_cantidad.getText().trim().isEmpty() || txt_tecnico.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese cantidad y responsable.");
            return;
        }
        try {
            int cantidad = Integer.parseInt(txt_cantidad.getText());
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a cero.");
                return;
            }
            MovimientoInventario m = new MovimientoInventario();
            Pieza p = new Pieza();
            p.setIdPieza(idPiezaSeleccionada);
            m.setPieza(p);
            m.setFecha(LocalDate.now());
            m.setTipoMovimiento(cmb_movimiento.getSelectedItem().toString());
            m.setCantidad(cantidad);
            m.setTecnicoResponsable(txt_tecnico.getText());
            m.setMotivo(txt_motivo.getText());

            if (movimientoDao.registrarMovimiento(m)) {
                JOptionPane.showMessageDialog(this, "Movimiento registrado correctamente.");
                cargarTablaPiezas();
                cargarTablaMovimientos();
                cambiarAModoNuevo();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser numerica.");
        }
    }

    PiezaDao piezaDao = new PiezaDao();
    MovimientoInventarioDao movimientoDao = new MovimientoInventarioDao();
    private javax.swing.JButton bt_cancelar;
    private javax.swing.JButton bt_editar;
    private javax.swing.JButton bt_eliminar;
    private javax.swing.JButton bt_guardar;
    private javax.swing.JButton bt_registrar_movimiento;
    private javax.swing.JComboBox<String> cmb_categoria;
    private javax.swing.JComboBox<String> cmb_estado;
    private javax.swing.JComboBox<String> cmb_movimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbl_movimientos;
    private javax.swing.JTable tbl_piezas;
    private javax.swing.JTextField txt_cantidad;
    private javax.swing.JTextField txt_codigo;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_marca;
    private javax.swing.JTextField txt_motivo;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_stock;
    private javax.swing.JTextField txt_stock_minimo;
    private javax.swing.JTextField txt_tecnico;
    private javax.swing.JTextField txt_ubicacion;
}
