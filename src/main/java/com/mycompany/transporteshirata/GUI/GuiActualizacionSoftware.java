package com.mycompany.transporteshirata.GUI;

import com.mycompany.transporteshirata.Datos.ActualizacionSoftwareDao;
import com.mycompany.transporteshirata.Datos.EquipoDao;
import com.mycompany.transporteshirata.Datos.SoftwareDao;
import com.mycompany.transporteshirata.Logica.ActualizacionSoftware;
import com.mycompany.transporteshirata.Logica.Equipo;
import com.mycompany.transporteshirata.Logica.Software;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Ventana interna para la gestión de actualizaciones de software en equipos de
 * oficina. Permite administrar el catálogo de software (registrar, editar,
 * eliminar), y registrar actualizaciones de software en equipos específicos,
 * manteniendo un historial de versiones.
 */
public class GuiActualizacionSoftware extends javax.swing.JInternalFrame {

    private int idEquipoSeleccionado = 0;
    private int idSoftwareSeleccionado = 0;

    /**
     * Constructor. Inicializa la interfaz gráfica, centra la ventana, establece
     * la fecha actual, carga las tablas de equipos, software e historial, y
     * deja el formulario de software en modo "nuevo".
     */
    public GuiActualizacionSoftware() {
        initComponents();
        this.setTitle("     Actualizacion de Software     ");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (pantalla.width - this.getWidth());
        int y = (pantalla.height - this.getHeight());
        this.setLocation(x / 2 + 150, y / 2 - 40);
        txt_fecha.setText(LocalDate.now().toString());
        cargarTablaEquipos();
        cargarTablaSoftware();
        cargarTablaHistorial();
        cambiarSoftwareAModoNuevo();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_equipos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_software = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_id_software = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_version_actual = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_proveedor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cmb_tipo_software = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cmb_estado_software = new javax.swing.JComboBox<>();
        bt_guardar_software = new javax.swing.JButton();
        bt_editar_software = new javax.swing.JButton();
        bt_eliminar_software = new javax.swing.JButton();
        bt_cancelar_software = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txt_equipo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_software = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_fecha = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_version_anterior = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_version_nueva = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_tecnico = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cmb_estado_actualizacion = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        txt_observaciones = new javax.swing.JTextField();
        bt_registrar_actualizacion = new javax.swing.JButton();
        bt_limpiar_actualizacion = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_historial = new javax.swing.JTable();

        setClosable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Equipos de oficina", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14)));
        tbl_equipos.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, new String[]{"ID", "Codigo", "Tipo", "Marca", "Estado"}));
        tbl_equipos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_equiposMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_equipos);
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE).addContainerGap()));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap()));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Catalogo de software", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14)));
        tbl_software.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, new String[]{"ID", "Nombre", "Version", "Proveedor", "Estado"}));
        tbl_software.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_softwareMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_software);
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE).addContainerGap()));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap()));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro de software", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14)));
        jLabel1.setText("ID");
        txt_id_software.setEditable(false);
        jLabel2.setText("Nombre");
        jLabel3.setText("Version");
        jLabel4.setText("Proveedor");
        jLabel5.setText("Tipo");
        cmb_tipo_software.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Sistema operativo", "Ofimatica", "Seguridad", "Gestion", "Driver", "Otro"}));
        jLabel6.setText("Estado");
        cmb_estado_software.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"activo", "inactivo"}));
        bt_guardar_software.setText("Guardar");
        bt_guardar_software.addActionListener(evt -> bt_guardar_softwareActionPerformed(evt));
        bt_editar_software.setText("Editar");
        bt_editar_software.addActionListener(evt -> bt_editar_softwareActionPerformed(evt));
        bt_eliminar_software.setText("Eliminar");
        bt_eliminar_software.addActionListener(evt -> bt_eliminar_softwareActionPerformed(evt));
        bt_cancelar_software.setText("Cancelar");
        bt_cancelar_software.addActionListener(evt -> bt_cancelar_softwareActionPerformed(evt));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel1).addComponent(jLabel2).addComponent(jLabel3).addComponent(jLabel4).addComponent(jLabel5).addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_id_software)
                                        .addComponent(txt_nombre)
                                        .addComponent(txt_version_actual)
                                        .addComponent(txt_proveedor)
                                        .addComponent(cmb_tipo_software, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmb_estado_software, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(bt_guardar_software, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE).addComponent(bt_eliminar_software, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(bt_editar_software, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE).addComponent(bt_cancelar_software, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel1).addComponent(txt_id_software, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel2).addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel3).addComponent(txt_version_actual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel4).addComponent(txt_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel5).addComponent(cmb_tipo_software, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel6).addComponent(cmb_estado_software, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(bt_guardar_software).addComponent(bt_editar_software))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(bt_eliminar_software).addComponent(bt_cancelar_software))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Actualizar software en equipo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14)));
        jLabel7.setText("Equipo");
        txt_equipo.setEditable(false);
        jLabel8.setText("Software");
        txt_software.setEditable(false);
        jLabel9.setText("Fecha");
        txt_fecha.setEditable(false);
        jLabel10.setText("Version anterior");
        txt_version_anterior.setEditable(false);
        jLabel11.setText("Version nueva");
        jLabel12.setText("Tecnico");
        jLabel13.setText("Estado");
        cmb_estado_actualizacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"realizada", "fallida", "pendiente"}));
        jLabel14.setText("Observaciones");
        bt_registrar_actualizacion.setText("Registrar actualizacion");
        bt_registrar_actualizacion.addActionListener(evt -> bt_registrar_actualizacionActionPerformed(evt));
        bt_limpiar_actualizacion.setText("Limpiar");
        bt_limpiar_actualizacion.addActionListener(evt -> limpiarActualizacion());

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7).addComponent(jLabel8).addComponent(jLabel9).addComponent(jLabel10).addComponent(jLabel11).addComponent(jLabel12).addComponent(jLabel13).addComponent(jLabel14))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_equipo)
                                        .addComponent(txt_software)
                                        .addComponent(txt_fecha)
                                        .addComponent(txt_version_anterior)
                                        .addComponent(txt_version_nueva)
                                        .addComponent(txt_tecnico)
                                        .addComponent(cmb_estado_actualizacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_observaciones))
                                .addContainerGap())
                        .addGroup(jPanel4Layout.createSequentialGroup().addGap(92, 92, 92).addComponent(bt_registrar_actualizacion).addGap(18, 18, 18).addComponent(bt_limpiar_actualizacion).addContainerGap(77, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel7).addComponent(txt_equipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel8).addComponent(txt_software, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel9).addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel10).addComponent(txt_version_anterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel11).addComponent(txt_version_nueva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel12).addComponent(txt_tecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel13).addComponent(cmb_estado_actualizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel14).addComponent(txt_observaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(bt_registrar_actualizacion).addComponent(bt_limpiar_actualizacion))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Historial de actualizaciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14)));
        tbl_historial.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, new String[]{"Fecha", "Equipo", "Software", "Anterior", "Nueva", "Tecnico", "Estado"}));
        jScrollPane3.setViewportView(tbl_historial);
        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel5Layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane3).addContainerGap()));
        jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel5Layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap()));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(layout.createSequentialGroup().addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))).addContainerGap()));
        pack();
    }

    /**
     * Carga la tabla con todos los equipos registrados.
     */
    private void cargarTablaEquipos() {
        String col[] = {"ID", "Codigo", "Tipo", "Marca", "Estado"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
        for (Equipo e : equipoDao.listarEquipos()) {
            tableModel.addRow(new Object[]{e.getIdEquipo(), e.getCodigo(), e.getTipo(), e.getMarca(), e.getEstado()});
        }
        tbl_equipos.setModel(tableModel);
    }

    /**
     * Carga la tabla con todo el catálogo de software registrado.
     */
    private void cargarTablaSoftware() {
        String col[] = {"ID", "Nombre", "Version", "Proveedor", "Estado"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
        for (Software s : softwareDao.listarSoftware()) {
            tableModel.addRow(new Object[]{s.getIdSoftware(), s.getNombre(), s.getVersionActual(), s.getProveedor(), s.getEstado()});
        }
        tbl_software.setModel(tableModel);
    }

    /**
     * Carga la tabla con el historial de todas las actualizaciones registradas.
     */
    private void cargarTablaHistorial() {
        String col[] = {"Fecha", "Equipo", "Software", "Anterior", "Nueva", "Tecnico", "Estado"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
        for (ActualizacionSoftware a : actualizacionDao.listarActualizaciones()) {
            tableModel.addRow(new Object[]{a.getFechaActualizacion(), a.getEquipo().getCodigo(), a.getSoftware().getNombre(), a.getVersionAnterior(), a.getVersionNueva(), a.getTecnicoResponsable(), a.getEstado()});
        }
        tbl_historial.setModel(tableModel);
    }

    /**
     * Cambia el panel de software a modo "nuevo": habilita Guardar, deshabilita
     * Editar/Eliminar y limpia el formulario de software.
     */
    private void cambiarSoftwareAModoNuevo() {
        bt_guardar_software.setEnabled(true);
        bt_editar_software.setEnabled(false);
        bt_eliminar_software.setEnabled(false);
        limpiarSoftware();
    }

    /**
     * Cambia el panel de software a modo "edición": deshabilita Guardar,
     * habilita Editar y Eliminar.
     */
    private void cambiarSoftwareAModoEdicion() {
        bt_guardar_software.setEnabled(false);
        bt_editar_software.setEnabled(true);
        bt_eliminar_software.setEnabled(true);
    }

    /**
     * Limpia todos los campos del formulario de software.
     */
    private void limpiarSoftware() {
        idSoftwareSeleccionado = 0;
        txt_id_software.setText("");
        txt_nombre.setText("");
        txt_version_actual.setText("");
        txt_proveedor.setText("");
        cmb_tipo_software.setSelectedIndex(0);
        cmb_estado_software.setSelectedIndex(0);
        tbl_software.clearSelection();
    }

    /**
     * Limpia todos los campos del formulario de actualización y deselecciona
     * equipo y software.
     */
    private void limpiarActualizacion() {
        idEquipoSeleccionado = 0;
        idSoftwareSeleccionado = 0;
        txt_equipo.setText("");
        txt_software.setText("");
        txt_fecha.setText(LocalDate.now().toString());
        txt_version_anterior.setText("");
        txt_version_nueva.setText("");
        txt_tecnico.setText("");
        txt_observaciones.setText("");
        cmb_estado_actualizacion.setSelectedIndex(0);
        tbl_equipos.clearSelection();
        tbl_software.clearSelection();
    }

    private boolean validarSoftware() {
        if (txt_nombre.getText().trim().isEmpty() || txt_version_actual.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete nombre y version del software.");
            return false;
        }
        return true;
    }

    private Software obtenerSoftwareDesdeFormulario() {
        Software s = new Software();
        if (!txt_id_software.getText().trim().isEmpty()) {
            s.setIdSoftware(Integer.parseInt(txt_id_software.getText()));
        }
        s.setNombre(txt_nombre.getText().trim());
        s.setVersionActual(txt_version_actual.getText().trim());
        s.setProveedor(txt_proveedor.getText().trim());
        s.setTipo(cmb_tipo_software.getSelectedItem().toString());
        s.setEstado(cmb_estado_software.getSelectedItem().toString());
        return s;
    }

    private void bt_guardar_softwareActionPerformed(java.awt.event.ActionEvent evt) {
        if (!validarSoftware()) {
            return;
        }
        Software s = obtenerSoftwareDesdeFormulario();
        if (softwareDao.existeSoftware(s.getNombre(), 0)) {
            JOptionPane.showMessageDialog(this, "Ya existe software registrado con ese nombre.");
            return;
        }
        if (softwareDao.registrarSoftware(s)) {
            JOptionPane.showMessageDialog(this, "Software registrado correctamente.");
            cargarTablaSoftware();
            cambiarSoftwareAModoNuevo();
        }
    }

    private void bt_editar_softwareActionPerformed(java.awt.event.ActionEvent evt) {
        if (txt_id_software.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione software de la tabla.");
            return;
        }
        if (!validarSoftware()) {
            return;
        }
        Software s = obtenerSoftwareDesdeFormulario();
        if (softwareDao.existeSoftware(s.getNombre(), s.getIdSoftware())) {
            JOptionPane.showMessageDialog(this, "Ya existe otro software con ese nombre.");
            return;
        }
        if (softwareDao.modificarSoftware(s)) {
            JOptionPane.showMessageDialog(this, "Software actualizado correctamente.");
            cargarTablaSoftware();
            cambiarSoftwareAModoNuevo();
        }
    }

    private void bt_eliminar_softwareActionPerformed(java.awt.event.ActionEvent evt) {
        if (txt_id_software.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione software de la tabla.");
            return;
        }
        int confirmacion = JOptionPane.showConfirmDialog(this, "Esta seguro de eliminar este software?", "Confirmar eliminacion", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION && softwareDao.eliminarSoftware(Integer.parseInt(txt_id_software.getText()))) {
            JOptionPane.showMessageDialog(this, "Software eliminado correctamente.");
            cargarTablaSoftware();
            cambiarSoftwareAModoNuevo();
        }
    }

    private void bt_cancelar_softwareActionPerformed(java.awt.event.ActionEvent evt) {
        cambiarSoftwareAModoNuevo();
    }

    /**
     * Evento de clic en la tabla de equipos. Carga el equipo seleccionado y
     * actualiza la versión instalada del software en ese equipo.
     */
    private void tbl_equiposMouseClicked(java.awt.event.MouseEvent evt) {
        int fila = tbl_equipos.getSelectedRow();
        if (fila != -1) {
            idEquipoSeleccionado = (int) tbl_equipos.getValueAt(fila, 0);
            txt_equipo.setText(tbl_equipos.getValueAt(fila, 1) + " - " + tbl_equipos.getValueAt(fila, 2));
            cargarVersionInstalada();
        }
    }

    /**
     * Evento de clic en la tabla de software. Carga los datos del software
     * seleccionado en el formulario y cambia a modo edición.
     */
    private void tbl_softwareMouseClicked(java.awt.event.MouseEvent evt) {
        int fila = tbl_software.getSelectedRow();
        if (fila != -1) {
            idSoftwareSeleccionado = (int) tbl_software.getValueAt(fila, 0);
            for (Software s : softwareDao.listarSoftware()) {
                if (s.getIdSoftware() == idSoftwareSeleccionado) {
                    txt_id_software.setText(String.valueOf(s.getIdSoftware()));
                    txt_nombre.setText(s.getNombre());
                    txt_version_actual.setText(s.getVersionActual());
                    txt_proveedor.setText(s.getProveedor());
                    cmb_tipo_software.setSelectedItem(s.getTipo());
                    cmb_estado_software.setSelectedItem(s.getEstado());
                    txt_software.setText(s.getNombre());
                    if (txt_version_nueva.getText().trim().isEmpty()) {
                        txt_version_nueva.setText(s.getVersionActual());
                    }
                    cambiarSoftwareAModoEdicion();
                    cargarVersionInstalada();
                    break;
                }
            }
        }
    }

    /**
     * Carga la versión actual del software instalada en el equipo seleccionado.
     */
    private void cargarVersionInstalada() {
        if (idEquipoSeleccionado > 0 && idSoftwareSeleccionado > 0) {
            String versionInstalada = actualizacionDao.obtenerVersionInstalada(idEquipoSeleccionado, idSoftwareSeleccionado);
            if (versionInstalada == null || versionInstalada.isEmpty()) {
                versionInstalada = txt_version_actual.getText();
            }
            txt_version_anterior.setText(versionInstalada);
        }
    }

    /**
     * Evento del botón "Registrar actualización". Registra una nueva
     * actualización de software para el equipo y software seleccionados.
     */
    private void bt_registrar_actualizacionActionPerformed(java.awt.event.ActionEvent evt) {
        if (idEquipoSeleccionado == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un equipo.");
            return;
        }
        if (idSoftwareSeleccionado == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione software del catalogo. Si no existe, debe registrarlo primero.");
            return;
        }
        if (txt_version_nueva.getText().trim().isEmpty() || txt_tecnico.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete version nueva y tecnico responsable.");
            return;
        }

        Equipo equipo = new Equipo();
        equipo.setIdEquipo(idEquipoSeleccionado);

        Software software = new Software();
        software.setIdSoftware(idSoftwareSeleccionado);

        ActualizacionSoftware a = new ActualizacionSoftware();
        a.setEquipo(equipo);
        a.setSoftware(software);
        a.setFechaActualizacion(LocalDate.parse(txt_fecha.getText()));
        a.setVersionAnterior(txt_version_anterior.getText());
        a.setVersionNueva(txt_version_nueva.getText());
        a.setTecnicoResponsable(txt_tecnico.getText());
        a.setEstado(cmb_estado_actualizacion.getSelectedItem().toString());
        a.setObservaciones(txt_observaciones.getText());

        if (actualizacionDao.registrarActualizacion(a)) {
            JOptionPane.showMessageDialog(this, "Actualizacion de software registrada correctamente.");
            cargarTablaSoftware();
            cargarTablaHistorial();
            limpiarActualizacion();
        }
    }

    ActualizacionSoftwareDao actualizacionDao = new ActualizacionSoftwareDao();
    EquipoDao equipoDao = new EquipoDao();
    SoftwareDao softwareDao = new SoftwareDao();
    private javax.swing.JButton bt_cancelar_software;
    private javax.swing.JButton bt_editar_software;
    private javax.swing.JButton bt_eliminar_software;
    private javax.swing.JButton bt_guardar_software;
    private javax.swing.JButton bt_limpiar_actualizacion;
    private javax.swing.JButton bt_registrar_actualizacion;
    private javax.swing.JComboBox<String> cmb_estado_actualizacion;
    private javax.swing.JComboBox<String> cmb_estado_software;
    private javax.swing.JComboBox<String> cmb_tipo_software;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tbl_equipos;
    private javax.swing.JTable tbl_historial;
    private javax.swing.JTable tbl_software;
    private javax.swing.JTextField txt_equipo;
    private javax.swing.JTextField txt_fecha;
    private javax.swing.JTextField txt_id_software;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_observaciones;
    private javax.swing.JTextField txt_proveedor;
    private javax.swing.JTextField txt_software;
    private javax.swing.JTextField txt_tecnico;
    private javax.swing.JTextField txt_version_actual;
    private javax.swing.JTextField txt_version_anterior;
    private javax.swing.JTextField txt_version_nueva;
}
