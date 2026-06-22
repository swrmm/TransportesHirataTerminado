package com.mycompany.transporteshirata.GUI;

import com.mycompany.transporteshirata.Datos.EquipoOficinaDao;
import com.mycompany.transporteshirata.Logica.EquipoOficina;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Formulario de interfaz gráfica para el inventariado y control general de equipos de oficina.
 * Proporciona soporte para la inscripción, edición y eliminación de activos en el sistema.
 */
public class GuiEquipoOficina extends javax.swing.JInternalFrame {

    private EquipoOficinaDao dao = new EquipoOficinaDao();
    private DefaultTableModel modeloTabla;

    private JTextField txtNombre = new JTextField(20);
    private JComboBox<String> cmbTipo = new JComboBox<>(new String[]{
        "PC", "Impresora", "Monitor", "Laptop", "Teclado", "Mouse", "Otro"});
    private JTextField txtMarca = new JTextField(15);
    private JTextField txtModelo = new JTextField(15);
    private JTextField txtSerie = new JTextField(20);
    private JComboBox<String> cmbEstado = new JComboBox<>(new String[]{
        "Activo", "En reparación", "Dado de baja"});
    private JTextField txtBuscar = new JTextField(20);

    private JButton btnRegistrar = new JButton("Registrar");
    private JButton btnModificar = new JButton("Modificar"); // NUEVO BOTON
    private JButton btnEliminar = new JButton("Eliminar");
    private JButton btnLimpiar = new JButton("Limpiar");
    
    private JButton btnBuscar = new JButton("Buscar");
    private JButton btnActualizar = new JButton("Actualizar lista");

    private JTable tabla;

    /**
     * Constructor de la interfaz GuiEquipoOficina.
     */
    public GuiEquipoOficina() {
        setTitle("Equipos de Oficina");
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setSize(880, 580); 

        inicializarUI();
        cargarTabla(dao.listarEquipos());
        configurarEventos();
    }

    /**
     * Inicializa la interfaz y distribuye las secciones del formulario y la tabla de datos.
     */
    private void inicializarUI() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBorder(BorderFactory.createTitledBorder("Gestión de Equipo"));
        panelForm.setPreferredSize(new Dimension(320, 0)); // Un poco más ancho
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        agregarFila(panelForm, gbc, 0, "Nombre:", txtNombre);
        agregarFila(panelForm, gbc, 1, "Tipo:", cmbTipo);
        agregarFila(panelForm, gbc, 2, "Marca:", txtMarca);
        agregarFila(panelForm, gbc, 3, "Modelo:", txtModelo);
        agregarFila(panelForm, gbc, 4, "Nº Serie:", txtSerie);
        agregarFila(panelForm, gbc, 5, "Estado:", cmbEstado);

        
        gbc.gridy = 6; gbc.gridx = 0; gbc.gridwidth = 2;
        JPanel panelBotones = new JPanel(new GridLayout(2, 2, 5, 5));
        
        btnRegistrar.setBackground(new Color(0, 120, 215));
        btnRegistrar.setForeground(Color.WHITE);
        
        btnModificar.setBackground(new Color(220, 130, 0)); // Naranja
        btnModificar.setForeground(Color.WHITE);
        
        btnEliminar.setBackground(new Color(180, 50, 50)); // Rojo
        btnEliminar.setForeground(Color.WHITE);
        
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);
        panelForm.add(panelBotones, gbc);

        gbc.gridy = 7;
        gbc.weighty = 1.0;
        panelForm.add(Box.createVerticalGlue(), gbc);

        JPanel panelTabla = new JPanel(new BorderLayout(5, 5));
        panelTabla.setBorder(BorderFactory.createTitledBorder("Equipos Registrados"));

        JPanel panelBuscar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBuscar.add(new JLabel("Buscar:"));
        panelBuscar.add(txtBuscar);
        panelBuscar.add(btnBuscar);
        panelBuscar.add(btnActualizar);
        panelTabla.add(panelBuscar, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(
            new String[]{"ID", "Nombre", "Tipo", "Marca", "Modelo", "Nº Serie", "Estado"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tabla = new JTable(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
        panelTabla.add(new JScrollPane(tabla), BorderLayout.CENTER);

        add(panelForm, BorderLayout.WEST);
        add(panelTabla, BorderLayout.CENTER);
    }

    /**
     * Encaja visualmente las filas de elementos de control dentro del contenedor del layout.
     */
    private void agregarFila(JPanel panel, GridBagConstraints gbc, int fila, String label, JComponent campo) {
        gbc.gridy = fila; gbc.gridx = 0; gbc.gridwidth = 1; gbc.weightx = 0;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        panel.add(campo, gbc);
    }

    /**
     * Enlaza las rutinas correspondientes a las interacciones de la GUI.
     */
    private void configurarEventos() {
        btnRegistrar.addActionListener(e -> registrar());
        btnModificar.addActionListener(e -> modificar());
        btnEliminar.addActionListener(e -> eliminar());
        btnLimpiar.addActionListener(e -> limpiarFormulario());
        btnBuscar.addActionListener(e -> buscar());
        btnActualizar.addActionListener(e -> cargarTabla(dao.listarEquipos()));
        
        
        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int fila = tabla.getSelectedRow();
                if (fila >= 0) {
                    txtNombre.setText(modeloTabla.getValueAt(fila, 1).toString());
                    cmbTipo.setSelectedItem(modeloTabla.getValueAt(fila, 2).toString());
                    txtMarca.setText(modeloTabla.getValueAt(fila, 3).toString());
                    txtModelo.setText(modeloTabla.getValueAt(fila, 4).toString());
                    Object serie = modeloTabla.getValueAt(fila, 5);
                    txtSerie.setText(serie != null ? serie.toString() : "");
                    cmbEstado.setSelectedItem(modeloTabla.getValueAt(fila, 6).toString());
                }
            }
        });
    }

    /**
     * Registra un nuevo equipo.
     */
    private void registrar() {
        String nombre = txtNombre.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio para registrar.");
            return;
        }

        EquipoOficina eq = new EquipoOficina();
        eq.setNombre(nombre);
        eq.setTipo(cmbTipo.getSelectedItem().toString());
        eq.setMarca(txtMarca.getText().trim());
        eq.setModelo(txtModelo.getText().trim());
        eq.setNumeroSerie(txtSerie.getText().trim().isEmpty() ? null : txtSerie.getText().trim());
        eq.setEstado(cmbEstado.getSelectedItem().toString());

        if (dao.registrarEquipo(eq)) {
            JOptionPane.showMessageDialog(this, "Equipo registrado correctamente.");
            limpiarFormulario();
            cargarTabla(dao.listarEquipos());
        }
    }

    /**
     * Modifica los datos del equipo seleccionado en la tabla.
     */
    private void modificar() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un equipo de la tabla para modificar.");
            return;
        }

        String nombre = txtNombre.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre no puede quedar vacío.");
            return;
        }

        try {
            int id = Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());

            EquipoOficina eq = new EquipoOficina();
            eq.setIdEquipo(id);
            eq.setNombre(nombre);
            eq.setTipo(cmbTipo.getSelectedItem().toString());
            eq.setMarca(txtMarca.getText().trim());
            eq.setModelo(txtModelo.getText().trim());
            eq.setNumeroSerie(txtSerie.getText().trim().isEmpty() ? null : txtSerie.getText().trim());
            eq.setEstado(cmbEstado.getSelectedItem().toString());

            if (dao.modificarEquipo(eq)) {
                JOptionPane.showMessageDialog(this, "Equipo actualizado exitosamente.");
                limpiarFormulario();
                cargarTabla(dao.listarEquipos());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al leer el identificador.");
        }
    }

    /**
     * Remueve el registro del equipo seleccionado.
     */
    private void eliminar() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un equipo de la tabla para eliminar.");
            return;
        }
        
        try {
            int id = Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());
            
            int confirm = JOptionPane.showConfirmDialog(this, 
                "¿Está seguro de eliminar este equipo de forma permanente?", 
                "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                if (dao.eliminarEquipo(id)) {
                    JOptionPane.showMessageDialog(this, "Equipo eliminado exitosamente.");
                    limpiarFormulario();
                    cargarTabla(dao.listarEquipos());
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error de lectura en la tabla. Por favor, actualice la lista.");
        }
    }

    /**
     * Genera una búsqueda de elementos.
     */
    private void buscar() {
        String termino = txtBuscar.getText().trim();
        if (termino.isEmpty()) {
            cargarTabla(dao.listarEquipos());
        } else {
            cargarTabla(dao.buscarEquipos(termino));
        }
    }

    /**
     * Vuelca la colección de datos recopilados dentro de la tabla.
     */
    private void cargarTabla(List<EquipoOficina> lista) {
        modeloTabla.setRowCount(0);
        for (EquipoOficina eq : lista) {
            modeloTabla.addRow(new Object[]{
                eq.getIdEquipo(),
                eq.getNombre(),
                eq.getTipo(),
                eq.getMarca(),
                eq.getModelo(),
                eq.getNumeroSerie(),
                eq.getEstado()
            });
        }
    }

    /**
     * Vacía el contenido de los inputs y quita la selección de la tabla.
     */
    private void limpiarFormulario() {
        txtNombre.setText("");
        txtMarca.setText("");
        txtModelo.setText("");
        txtSerie.setText("");
        cmbTipo.setSelectedIndex(0);
        cmbEstado.setSelectedIndex(0);
        tabla.clearSelection();
    }
}