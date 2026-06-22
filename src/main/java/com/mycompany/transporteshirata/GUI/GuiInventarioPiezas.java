package com.mycompany.transporteshirata.GUI;

<<<<<<< HEAD
import com.mycompany.transporteshirata.Datos.PiezaRepuestoDao;
import com.mycompany.transporteshirata.Logica.PiezaRepuesto;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Formulario: Inventario de Piezas de Repuesto (RF-09)
 * Ruta: src/main/java/com/mycompany/transporteshirata/GUI/GuiInventarioPiezas.java
 */
public class GuiInventarioPiezas extends javax.swing.JInternalFrame {

    private PiezaRepuestoDao  dao = new PiezaRepuestoDao();
    private DefaultTableModel modeloTabla;

    // Campos formulario
    private JTextField txtNombre      = new JTextField(20);
    private JTextArea  txtDescripcion = new JTextArea(3, 20);
    private JTextField txtStock       = new JTextField(6);
    private JComboBox<String> cmbEstado = new JComboBox<>(new String[]{
        "Disponible", "Sin stock", "Descontinuado"});
    private JTextField txtBuscar      = new JTextField(15);

    // Campos para descontar stock
    private JTextField txtCantDescontar = new JTextField(5);

    // Botones
    private JButton btnRegistrar  = new JButton("Registrar pieza");
    private JButton btnActualizar = new JButton("Guardar cambios");
    private JButton btnDescontar  = new JButton("Descontar stock");
    private JButton btnBuscar     = new JButton("Buscar");
    private JButton btnRefresh    = new JButton("Actualizar lista");
    private JButton btnLimpiar    = new JButton("Limpiar");

    private JTable tabla;

    public GuiInventarioPiezas() {
        setTitle("Inventario de Piezas de Repuesto");
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setSize(900, 580);

        inicializarUI();
        cargarTabla(dao.listarPiezas());
        configurarEventos();
    }

    private void inicializarUI() {
        setLayout(new BorderLayout(10, 10));


        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBorder(BorderFactory.createTitledBorder("Registrar / Actualizar Pieza"));
        panelForm.setPreferredSize(new Dimension(360, 0)); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill   = GridBagConstraints.HORIZONTAL;

        agregarFila(panelForm, gbc, 0, "Nombre:",      txtNombre);
        

        gbc.gridy = 1; gbc.gridx = 0; gbc.gridwidth = 1; gbc.weightx = 0;
        panelForm.add(new JLabel("Descripción:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH; 
        gbc.weighty = 0.3; 
        JScrollPane scrollDesc = new JScrollPane(txtDescripcion);
        panelForm.add(scrollDesc, gbc);


        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;

        agregarFila(panelForm, gbc, 2, "Stock:",       txtStock);
        agregarFila(panelForm, gbc, 3, "Estado:",      cmbEstado);

        // Botones registrar/actualizar
        gbc.gridy = 4; gbc.gridx = 0; gbc.gridwidth = 2;
        JPanel panelBtn1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
        btnRegistrar.setBackground(new Color(0, 140, 70));
        btnRegistrar.setForeground(Color.WHITE);
        btnActualizar.setBackground(new Color(200, 120, 0));
        btnActualizar.setForeground(Color.WHITE);
        panelBtn1.add(btnRegistrar);
        panelBtn1.add(btnActualizar);
        panelBtn1.add(btnLimpiar);
        panelForm.add(panelBtn1, gbc);


        gbc.gridy = 5; gbc.gridx = 0; gbc.gridwidth = 2;
        JPanel panelDescontar = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 2));
        panelDescontar.setBorder(BorderFactory.createTitledBorder("Descontar stock de fila seleccionada"));
        panelDescontar.add(new JLabel("Cantidad:"));
        txtCantDescontar.setText("1");
        panelDescontar.add(txtCantDescontar);
        btnDescontar.setBackground(new Color(180, 30, 30));
        btnDescontar.setForeground(Color.WHITE);
        panelDescontar.add(btnDescontar);
        panelForm.add(panelDescontar, gbc);


        gbc.gridy = 6; 
        gbc.weighty = 1.0; 
        panelForm.add(Box.createVerticalGlue(), gbc);

  
        JPanel panelTabla = new JPanel(new BorderLayout(5, 5));
        panelTabla.setBorder(BorderFactory.createTitledBorder("Inventario de Piezas"));

        JPanel panelBuscar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBuscar.add(new JLabel("Buscar:"));
        panelBuscar.add(txtBuscar);
        panelBuscar.add(btnBuscar);
        panelBuscar.add(btnRefresh);
        panelTabla.add(panelBuscar, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(
            new String[]{"ID", "Nombre", "Descripción", "Stock", "Estado"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tabla = new JTable(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(60);


        tabla.getSelectionModel().addListSelectionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                txtNombre.setText(modeloTabla.getValueAt(fila, 1).toString());
                txtDescripcion.setText(modeloTabla.getValueAt(fila, 2) != null
                        ? modeloTabla.getValueAt(fila, 2).toString() : "");
                txtStock.setText(modeloTabla.getValueAt(fila, 3).toString());
                cmbEstado.setSelectedItem(modeloTabla.getValueAt(fila, 4).toString());
            }
        });

        panelTabla.add(new JScrollPane(tabla), BorderLayout.CENTER);

        add(panelForm,  BorderLayout.WEST);
        add(panelTabla, BorderLayout.CENTER);
    }

    private void agregarFila(JPanel panel, GridBagConstraints gbc, int fila,
                              String label, JComponent campo) {
        gbc.gridy = fila; gbc.gridx = 0; gbc.gridwidth = 1; gbc.weightx = 0;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        panel.add(campo, gbc);
    }

    private void configurarEventos() {
        btnRegistrar.addActionListener(e -> registrar());
        btnLimpiar.addActionListener(e -> limpiarFormulario());
        btnBuscar.addActionListener(e -> buscar());
        btnRefresh.addActionListener(e -> cargarTabla(dao.listarPiezas()));
        btnActualizar.addActionListener(e -> actualizarPieza());
        btnDescontar.addActionListener(e -> descontarStock());
    }

    private void registrar() {
        String nombre = txtNombre.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio.");
            return;
        }
        int stock;
        try { stock = Integer.parseInt(txtStock.getText().trim()); }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El stock debe ser un número entero.");
            return;
        }

        PiezaRepuesto p = new PiezaRepuesto();
        p.setNombre(nombre);
        p.setDescripcion(txtDescripcion.getText().trim());
        p.setStock(stock);
        p.setEstado(cmbEstado.getSelectedItem().toString());

        if (dao.registrarPieza(p)) {
            JOptionPane.showMessageDialog(this, "Pieza registrada correctamente.");
            limpiarFormulario();
            cargarTabla(dao.listarPiezas());
        }
    }

    private void actualizarPieza() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una pieza de la tabla para actualizar.");
            return;
        }
        int idPieza = (int) modeloTabla.getValueAt(fila, 0);
        int stock;
        try { stock = Integer.parseInt(txtStock.getText().trim()); }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El stock debe ser un número entero.");
            return;
        }

        PiezaRepuesto p = new PiezaRepuesto();
        p.setIdPieza(idPieza);
        p.setNombre(txtNombre.getText().trim());
        p.setDescripcion(txtDescripcion.getText().trim());
        p.setStock(stock);
        p.setEstado(cmbEstado.getSelectedItem().toString());

        if (dao.actualizarPieza(p)) {
            JOptionPane.showMessageDialog(this, "Pieza actualizada correctamente.");
            limpiarFormulario();
            cargarTabla(dao.listarPiezas());
        }
    }

    private void descontarStock() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una pieza de la tabla.");
            return;
        }
        int idPieza = (int) modeloTabla.getValueAt(fila, 0);
        int cantidad;
        try { cantidad = Integer.parseInt(txtCantDescontar.getText().trim()); }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero.");
            return;
        }
        if (cantidad <= 0) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a 0.");
            return;
        }

        if (dao.descontarStock(idPieza, cantidad)) {
            JOptionPane.showMessageDialog(this, "Stock descontado correctamente.");
            cargarTabla(dao.listarPiezas());
        }
    }

    private void buscar() {
        String termino = txtBuscar.getText().trim();
        if (termino.isEmpty()) {
            cargarTabla(dao.listarPiezas());
        } else {
            cargarTabla(dao.buscarPiezas(termino));
        }
    }

    private void cargarTabla(List<PiezaRepuesto> lista) {
        modeloTabla.setRowCount(0);
        for (PiezaRepuesto p : lista) {
            modeloTabla.addRow(new Object[]{
                p.getIdPieza(),
                p.getNombre(),
                p.getDescripcion(),
                p.getStock(),
                p.getEstado()
            });
        }
    }

    private void limpiarFormulario() {
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtStock.setText("");
        txtCantDescontar.setText("1");
        cmbEstado.setSelectedIndex(0);
        tabla.clearSelection();
    }
=======
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

/**
 * Ventana interna para la gestión del inventario de piezas de repuesto. Permite
 * registrar, editar y eliminar piezas, así como registrar movimientos de stock
 * (entradas, salidas, ajustes) y visualizar el historial de movimientos.
 */
public class GuiInventarioPiezas extends javax.swing.JInternalFrame {

    private int idPiezaSeleccionada = 0;

    /**
     * Constructor. Inicializa la interfaz gráfica, centra la ventana, carga la
     * tabla de piezas y movimientos, y deja el formulario en modo "nuevo".
     */
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

    /**
     * Carga la tabla con todas las piezas registradas.
     */
    private void cargarTablaPiezas() {
        String col[] = {"ID", "Codigo", "Nombre", "Categoria", "Stock", "Minimo", "Estado"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
        for (Pieza p : piezaDao.listarPiezas()) {
            tableModel.addRow(new Object[]{p.getIdPieza(), p.getCodigo(), p.getNombre(), p.getCategoria(), p.getStockActual(), p.getStockMinimo(), p.getEstado()});
        }
        tbl_piezas.setModel(tableModel);
    }

    /**
     * Carga la tabla con todos los movimientos de inventario registrados.
     */
    private void cargarTablaMovimientos() {
        String col[] = {"Fecha", "Tipo", "Pieza", "Cantidad", "Responsable", "Motivo"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
        for (MovimientoInventario m : movimientoDao.listarMovimientos()) {
            tableModel.addRow(new Object[]{m.getFecha(), m.getTipoMovimiento(), m.getPieza().getCodigo(), m.getCantidad(), m.getTecnicoResponsable(), m.getMotivo()});
        }
        tbl_movimientos.setModel(tableModel);
    }

    /**
     * Cambia la interfaz a modo "nuevo": habilita el botón Guardar, deshabilita
     * Editar/Eliminar y limpia el formulario.
     */
    private void cambiarAModoNuevo() {
        bt_guardar.setEnabled(true);
        bt_editar.setEnabled(false);
        bt_eliminar.setEnabled(false);
        limpiarFormulario();
    }

    /**
     * Cambia la interfaz a modo "edición": deshabilita Guardar, habilita Editar
     * y Eliminar.
     */
    private void cambiarAModoEdicion() {
        bt_guardar.setEnabled(false);
        bt_editar.setEnabled(true);
        bt_eliminar.setEnabled(true);
    }

    /**
     * Limpia todos los campos del formulario de piezas y movimientos.
     */
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

    /**
     * Valida que los campos obligatorios del formulario estén completos y que
     * los valores numéricos sean correctos.
     *
     * @return true si los datos son válidos, false en caso contrario
     */
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

    /**
     * Evento del botón "Guardar". Registra una nueva pieza en el inventario.
     */
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

    /**
     * Evento del botón "Editar". Actualiza los datos de la pieza seleccionada.
     */
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

    /**
     * Evento del botón "Eliminar". Elimina la pieza seleccionada previa
     * confirmación.
     */
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

    /**
     * Evento del botón "Cancelar". Limpia el formulario y vuelve al modo
     * "nuevo".
     */
    private void bt_cancelarActionPerformed(java.awt.event.ActionEvent evt) {
        cambiarAModoNuevo();
    }

    /**
     * Evento de clic en la tabla de piezas. Carga los datos de la pieza
     * seleccionada en el formulario y cambia a modo "edición".
     */
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

    /**
     * Evento del botón "Registrar movimiento". Registra una entrada, salida o
     * ajuste de stock para la pieza seleccionada.
     */
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
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
}
