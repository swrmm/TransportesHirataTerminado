package com.mycompany.transporteshirata.GUI;

import com.mycompany.transporteshirata.Datos.ActualizacionSoftwareDao;
import com.mycompany.transporteshirata.Datos.EquipoOficinaDao;
import com.mycompany.transporteshirata.Logica.ActualizacionSoftware;
import com.mycompany.transporteshirata.Logica.EquipoOficina;
import java.time.LocalDate;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Formulario de interfaz gráfica para el registro y consulta de actualizaciones de software.
 * Permite mantener la trazabilidad de las versiones instaladas en los equipos informáticos.
 * Cumple con el requerimiento funcional.
 */
public class GuiActualizacionSoftware extends javax.swing.JInternalFrame {
    
    private ActualizacionSoftwareDao dao = new ActualizacionSoftwareDao();
    private EquipoOficinaDao daoEq = new EquipoOficinaDao();
    private DefaultTableModel modeloTabla;

    private JComboBox<EquipoOficina> cmbEquipo = new JComboBox<>();
    private JTextField txtSoftware = new JTextField(20);
    private JTextField txtVersionAnt = new JTextField(10);
    private JTextField txtVersionNueva = new JTextField(10);
    private JTextField txtFecha = new JTextField(LocalDate.now().toString());
    private JTextField txtResponsable = new JTextField(20);
    private JTextField txtBuscar = new JTextField(15);

    private JButton btnRegistrar = new JButton("Registrar");
    private JButton btnLimpiar = new JButton("Limpiar");
    private JButton btnBuscar = new JButton("Buscar");
    private JButton btnActualizar = new JButton("Actualizar lista");

    private JTable tabla;

    /**
     * Constructor de la interfaz GuiActualizacionSoftware.
     * Inicializa los componentes visuales, combos de selección y carga los registros iniciales.
     */
    public GuiActualizacionSoftware() {
        setTitle("Actualización de Software en Equipos");
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setSize(900, 560);

        cargarEquiposEnCombo();
        inicializarUI();
        cargarTabla(dao.listarActualizaciones());
        configurarEventos();
        
        txtResponsable.setText(GuiLoginGeneral.rut_global);
        txtResponsable.setEditable(false);
    }

    /**
     * Carga el listado de equipos de oficina disponibles en el selector del formulario.
     */
    private void cargarEquiposEnCombo() {
        List<EquipoOficina> equipos = daoEq.listarEquipos();
        cmbEquipo.removeAllItems();
        for (EquipoOficina eq : equipos) {
            cmbEquipo.addItem(eq);
        }
    }

    /**
     * Construye y distribuye los paneles del formulario y de las tablas del sistema.
     */
    private void inicializarUI() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBorder(BorderFactory.createTitledBorder("Registrar Actualización de Software"));
        panelForm.setPreferredSize(new Dimension(310, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        agregarFila(panelForm, gbc, 0, "Equipo:", cmbEquipo);
        agregarFila(panelForm, gbc, 1, "Software:", txtSoftware);
        agregarFila(panelForm, gbc, 2, "Versión anterior:", txtVersionAnt);
        agregarFila(panelForm, gbc, 3, "Versión nueva:", txtVersionNueva);
        agregarFila(panelForm, gbc, 4, "Fecha (YYYY-MM-DD):", txtFecha);
        agregarFila(panelForm, gbc, 5, "Responsable:", txtResponsable);

        gbc.gridy = 6; gbc.gridx = 0; gbc.gridwidth = 2;
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        btnRegistrar.setBackground(new Color(0, 100, 200));
        btnRegistrar.setForeground(Color.WHITE);
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnLimpiar);
        panelForm.add(panelBotones, gbc);

        JPanel panelTabla = new JPanel(new BorderLayout(5, 5));
        panelTabla.setBorder(BorderFactory.createTitledBorder("Actualizaciones Registradas"));

        JPanel panelBuscar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBuscar.add(new JLabel("Buscar software:"));
        panelBuscar.add(txtBuscar);
        panelBuscar.add(btnBuscar);
        panelBuscar.add(btnActualizar);
        panelTabla.add(panelBuscar, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(
            new String[]{"ID", "Equipo", "Software", "V. Anterior", "V. Nueva", "Fecha", "Responsable"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tabla = new JTable(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panelTabla.add(new JScrollPane(tabla), BorderLayout.CENTER);

        add(panelForm, BorderLayout.WEST);
        add(panelTabla, BorderLayout.CENTER);
    }

    /**
     * Distribuye de forma secuencial las etiquetas y sus campos dentro del formulario.
     */
    private void agregarFila(JPanel panel, GridBagConstraints gbc, int fila, String label, JComponent campo) {
        gbc.gridy = fila; gbc.gridx = 0; gbc.gridwidth = 1; gbc.weightx = 0;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        panel.add(campo, gbc);
    }

    /**
     * Vincula los oyentes de eventos a los botones correspondientes.
     */
    private void configurarEventos() {
        btnRegistrar.addActionListener(e -> registrar());
        btnLimpiar.addActionListener(e -> limpiarFormulario());
        btnBuscar.addActionListener(e -> buscar());
        btnActualizar.addActionListener(e -> cargarTabla(dao.listarActualizaciones()));
    }

    /**
     * Valida los datos ingresados en el formulario y efectúa el registro de la actualización.
     */
    private void registrar() {
        EquipoOficina eq = (EquipoOficina) cmbEquipo.getSelectedItem();
        if (eq == null) {
            JOptionPane.showMessageDialog(this, "No hay equipos disponibles. Registre un equipo primero.");
            return;
        }
        if (txtSoftware.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre del software es obligatorio.");
            return;
        }
        if (txtVersionNueva.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "La versión nueva es obligatoria.");
            return;
        }

        LocalDate fecha;
        try {
            fecha = LocalDate.parse(txtFecha.getText().trim());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use YYYY-MM-DD.");
            return;
        }

        ActualizacionSoftware a = new ActualizacionSoftware();
        a.setEquipo(eq);
        a.setNombreSoftware(txtSoftware.getText().trim());
        a.setVersionAnterior(txtVersionAnt.getText().trim());
        a.setVersionNueva(txtVersionNueva.getText().trim());
        a.setFechaActualizacion(fecha);
        a.setResponsable(txtResponsable.getText().trim());

        if (dao.registrarActualizacion(a)) {
            JOptionPane.showMessageDialog(this, "Actualización registrada correctamente.");
            limpiarFormulario();
            cargarTabla(dao.listarActualizaciones());
        }
    }

    /**
     * Filtra los datos de la tabla basándose en el criterio ingresado en el buscador.
     */
    private void buscar() {
        String termino = txtBuscar.getText().trim();
        if (termino.isEmpty()) {
            cargarTabla(dao.listarActualizaciones());
        } else {
            cargarTabla(dao.buscarPorSoftware(termino));
        }
    }

    /**
     * Refresca los datos del modelo de la tabla visual de actualizaciones.
     * @param lista Lista de actualizaciones que poblarán la tabla.
     */
    private void cargarTabla(List<ActualizacionSoftware> lista) {
        modeloTabla.setRowCount(0);
        for (ActualizacionSoftware a : lista) {
            modeloTabla.addRow(new Object[]{
                a.getIdActualizacion(),
                a.getEquipo() != null ? a.getEquipo().getNombre() : "-",
                a.getNombreSoftware(),
                a.getVersionAnterior(),
                a.getVersionNueva(),
                a.getFechaActualizacion(),
                a.getResponsable()
            });
        }
    }

    /**
     * Restablece los campos de entrada del formulario a sus valores por defecto.
     */
    private void limpiarFormulario() {
        txtResponsable.setText(GuiLoginGeneral.rut_global);
        txtResponsable.setEditable(false);
        txtSoftware.setText("");
        txtVersionAnt.setText("");
        txtVersionNueva.setText("");
        txtFecha.setText(LocalDate.now().toString());
        txtBuscar.setText("");
    }
}