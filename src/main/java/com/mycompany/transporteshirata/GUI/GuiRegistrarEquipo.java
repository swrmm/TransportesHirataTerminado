/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.mycompany.transporteshirata.GUI;

import com.mycompany.transporteshirata.Datos.EquipoDao;
import com.mycompany.transporteshirata.Logica.Equipo;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author danie
 */
public class GuiRegistrarEquipo extends javax.swing.JInternalFrame {

    /**
     * Creates new form GuiRegistrarEquipo
     */
    public GuiRegistrarEquipo() {
        initComponents();
        jLabel1.setVisible(false);
        txt_id.setVisible(false);
        cargarTabla();
        cambiarAModoNuevo();
        this.setTitle("     Registrar Equipo     ");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (pantalla.width - this.getWidth());
        int y = (pantalla.height - this.getHeight());
        this.setLocation(x / 2 + 150, y / 2 - 40);
        this.setSize(1080, 650);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cmb_tipo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_marca = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_ubicacion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cmb_estado = new javax.swing.JComboBox<>();
        bt_guardar = new javax.swing.JButton();
        bt_editar = new javax.swing.JButton();
        bt_eliminar = new javax.swing.JButton();
        bt_cancelar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_equipo = new javax.swing.JTable();

        setClosable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Equipo de oficina", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("ID");
        txt_id.setEditable(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Tipo");

        cmb_tipo.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        cmb_tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Computador", "Impresora", "Telefono"}));
        cmb_tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_tipoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Codigo");
        txt_codigo.setEditable(false);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Marca");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Ubicacion");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Estado");

        cmb_estado.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        cmb_estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"operativo", "en mantenimiento", "fuera de servicio"}));

        bt_guardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bt_guardar.setText("Guardar");
        bt_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_guardarActionPerformed(evt);
            }
        });

        bt_editar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bt_editar.setText("Editar");
        bt_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_editarActionPerformed(evt);
            }
        });

        bt_eliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bt_eliminar.setText("Eliminar");
        bt_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_eliminarActionPerformed(evt);
            }
        });

        bt_cancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bt_cancelar.setText("Cancelar");
        bt_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cancelarActionPerformed(evt);
            }
        });

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
                                        .addComponent(jLabel6))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_id)
                                        .addComponent(cmb_tipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_codigo)
                                        .addComponent(txt_marca)
                                        .addComponent(txt_ubicacion)
                                        .addComponent(cmb_estado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(bt_guardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bt_eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(bt_editar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bt_cancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(112, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(cmb_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(txt_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(txt_ubicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(cmb_estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(bt_guardar)
                                        .addComponent(bt_editar))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(bt_eliminar)
                                        .addComponent(bt_cancelar))
                                .addContainerGap(133, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Listado de equipos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tbl_equipo.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null, null}
                },
                new String[]{
                    "ID", "Tipo", "Codigo", "Marca", "Ubicacion", "Estado"
                }
        ));
        tbl_equipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_equipoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_equipo);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );

        pack();
    }

    public void cargarTabla() {
        String col[] = {"ID", "Tipo", "Codigo", "Marca", "Ubicacion", "Estado"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);

        for (Equipo e : equipoDao.listarEquipos()) {
            Object[] objs = {
                e.getIdEquipo(),
                e.getTipo(),
                e.getCodigo(),
                e.getMarca(),
                e.getUbicacion(),
                e.getEstado()
            };
            tableModel.addRow(objs);
        }
        tbl_equipo.setModel(tableModel);
    }

    public void cambiarAModoEdicion() {
        this.bt_cancelar.setEnabled(true);
        this.bt_eliminar.setEnabled(true);
        this.bt_editar.setEnabled(true);
        this.bt_guardar.setEnabled(false);
    }

    public void cambiarAModoNuevo() {
        this.bt_cancelar.setEnabled(false);
        this.bt_eliminar.setEnabled(false);
        this.bt_editar.setEnabled(false);
        this.bt_guardar.setEnabled(true);
        limpiarFormulario();
    }

    public void limpiarFormulario() {
        this.txt_id.setText("");
        this.cmb_tipo.setSelectedIndex(0);
        actualizarCodigoAutomatico();
        this.txt_marca.setText("");
        this.txt_ubicacion.setText("");
        this.cmb_estado.setSelectedIndex(0);
        this.tbl_equipo.clearSelection();
    }

    private boolean validarFormulario() {
        if (txt_codigo.getText().trim().isEmpty()
                || txt_marca.getText().trim().isEmpty() || txt_ubicacion.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, llene todos los campos del equipo.");
            return false;
        }

        if (txt_codigo.getText().trim().length() > 12) {
            JOptionPane.showMessageDialog(this, "El codigo no puede tener mas de 12 caracteres.");
            return false;
        }

        return true;
    }

    private Equipo obtenerEquipoDesdeFormulario() {
        Equipo e = new Equipo();
        if (!txt_id.getText().trim().isEmpty()) {
            e.setIdEquipo(Integer.parseInt(txt_id.getText()));
        }
        e.setTipo(cmb_tipo.getSelectedItem().toString());
        e.setCodigo(txt_codigo.getText().trim());
        e.setMarca(txt_marca.getText().trim());
        e.setUbicacion(txt_ubicacion.getText().trim());
        e.setEstado(cmb_estado.getSelectedItem().toString());
        return e;
    }

    private String obtenerPrefijoTipoSeleccionado() {
        String tipo = cmb_tipo.getSelectedItem().toString();
        if ("Computador".equals(tipo)) {
            return "PC";
        }
        if ("Impresora".equals(tipo)) {
            return "IMP";
        }
        return "TEL";
    }

    private void actualizarCodigoAutomatico() {
        if (!txt_id.getText().trim().isEmpty()) {
            return;
        }

        String prefijo = obtenerPrefijoTipoSeleccionado();
        int siguienteNumero = equipoDao.obtenerSiguienteNumeroCodigo(prefijo);
        txt_codigo.setText(String.format("%s-%03d", prefijo, siguienteNumero));
    }

    private void generarCodigoPorCambioDeTipo() {
        String prefijo = obtenerPrefijoTipoSeleccionado();
        int siguienteNumero = equipoDao.obtenerSiguienteNumeroCodigo(prefijo);
        txt_codigo.setText(String.format("%s-%03d", prefijo, siguienteNumero));
    }

    private void seleccionarTipo(String tipo) {
        if ("Computador".equalsIgnoreCase(tipo) || (tipo != null && tipo.toUpperCase().startsWith("PC"))) {
            cmb_tipo.setSelectedItem("Computador");
        } else if ("Impresora".equalsIgnoreCase(tipo) || (tipo != null && tipo.toUpperCase().startsWith("IMP"))) {
            cmb_tipo.setSelectedItem("Impresora");
        } else if ("Telefono".equalsIgnoreCase(tipo) || "Teléfono".equalsIgnoreCase(tipo) || (tipo != null && tipo.toUpperCase().startsWith("TEL"))) {
            cmb_tipo.setSelectedItem("Telefono");
        } else {
            cmb_tipo.setSelectedItem("Computador");
        }
    }

    private void bt_guardarActionPerformed(java.awt.event.ActionEvent evt) {
        actualizarCodigoAutomatico();
        if (!validarFormulario()) {
            return;
        }

        Equipo e = obtenerEquipoDesdeFormulario();
        if (equipoDao.existeCodigo(e.getCodigo(), 0)) {
            JOptionPane.showMessageDialog(this, "Ya existe un equipo registrado con ese codigo.");
            return;
        }

        if (equipoDao.registrarEquipo(e)) {
            JOptionPane.showMessageDialog(this, "Equipo registrado correctamente.");
            cargarTabla();
            cambiarAModoNuevo();
        }
    }

    private void tbl_equipoMouseClicked(java.awt.event.MouseEvent evt) {
        int selectedRow = this.tbl_equipo.getSelectedRow();
        if (selectedRow != -1) {
            this.txt_id.setText(tbl_equipo.getValueAt(selectedRow, 0).toString());
            seleccionarTipo(tbl_equipo.getValueAt(selectedRow, 1).toString());
            this.txt_codigo.setText(tbl_equipo.getValueAt(selectedRow, 2).toString());
            this.txt_marca.setText(tbl_equipo.getValueAt(selectedRow, 3).toString());
            this.txt_ubicacion.setText(tbl_equipo.getValueAt(selectedRow, 4).toString());
            this.cmb_estado.setSelectedItem(tbl_equipo.getValueAt(selectedRow, 5).toString());
            cambiarAModoEdicion();
        }
    }

    private void bt_editarActionPerformed(java.awt.event.ActionEvent evt) {
        if (txt_id.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un equipo de la tabla.");
            return;
        }
        if (!validarFormulario()) {
            return;
        }

        Equipo e = obtenerEquipoDesdeFormulario();
        if (equipoDao.existeCodigo(e.getCodigo(), e.getIdEquipo())) {
            JOptionPane.showMessageDialog(this, "Ya existe otro equipo registrado con ese codigo.");
            return;
        }

        if (equipoDao.modificarEquipo(e)) {
            JOptionPane.showMessageDialog(this, "Equipo actualizado correctamente.");
            cargarTabla();
            cambiarAModoNuevo();
        }
    }

    private void bt_eliminarActionPerformed(java.awt.event.ActionEvent evt) {
        if (txt_id.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un equipo de la tabla.");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this, "Esta seguro de eliminar este equipo?", "Confirmar eliminacion", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            int idEquipo = Integer.parseInt(txt_id.getText());
            if (equipoDao.eliminarEquipo(idEquipo)) {
                JOptionPane.showMessageDialog(this, "Equipo eliminado correctamente.");
                cargarTabla();
                cambiarAModoNuevo();
            }
        }
    }

    private void bt_cancelarActionPerformed(java.awt.event.ActionEvent evt) {
        cambiarAModoNuevo();
    }

    private void cmb_tipoActionPerformed(java.awt.event.ActionEvent evt) {
        if (txt_id != null && txt_codigo != null) {
            if (txt_id.getText().trim().isEmpty()) {
                actualizarCodigoAutomatico();
            } else {
                generarCodigoPorCambioDeTipo();
            }
        }
    }

    EquipoDao equipoDao = new EquipoDao();
    private javax.swing.JButton bt_cancelar;
    private javax.swing.JButton bt_editar;
    private javax.swing.JButton bt_eliminar;
    private javax.swing.JButton bt_guardar;
    private javax.swing.JComboBox<String> cmb_estado;
    private javax.swing.JComboBox<String> cmb_tipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_equipo;
    private javax.swing.JTextField txt_codigo;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_marca;
    private javax.swing.JTextField txt_ubicacion;
}
