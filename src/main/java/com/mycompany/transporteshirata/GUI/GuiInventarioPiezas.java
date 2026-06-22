package com.mycompany.transporteshirata.GUI;

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
}
