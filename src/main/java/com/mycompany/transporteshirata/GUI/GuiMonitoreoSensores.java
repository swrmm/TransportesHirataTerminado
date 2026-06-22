package com.mycompany.transporteshirata.GUI;

import com.mycompany.transporteshirata.Datos.CamionDao;
import com.mycompany.transporteshirata.Datos.SensorDao;
import com.mycompany.transporteshirata.Logica.Camion;
import com.mycompany.transporteshirata.Logica.ReporteSensor;
import com.mycompany.transporteshirata.Logica.SensorGps;
import com.mycompany.transporteshirata.Logica.SensorTemperatura;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class GuiMonitoreoSensores extends JInternalFrame {

    private final CamionDao camionDao = new CamionDao();
    private final SensorDao sensorDao = new SensorDao();
    private final Random random = new Random();

    private final JComboBox<Camion> cmbCamionGps = new JComboBox<>();
    private final JComboBox<Camion> cmbCamionTemperatura = new JComboBox<>();
    private final JComboBox<String> cmbEstadoGps = new JComboBox<>(new String[]{"Con senal", "Sin senal"});
    private final JTextField txtLatitud = new JTextField(12);
    private final JTextField txtLongitud = new JTextField(12);
    private final JTextField txtVelocidad = new JTextField(12);
    private final JTextField txtRuta = new JTextField(18);
    private final JTextField txtTiempo = new JTextField(12);
    private final JTextField txtTemperatura = new JTextField(12);
    private final JTextField txtLimite = new JTextField("8.0", 12);
    private final JTextArea txtObservacion = new JTextArea(3, 18);
    private final JTable tblGps = new JTable();
    private final JTable tblTemperatura = new JTable();
    private final JTable tblReporte = new JTable();
    private final JLabel lblGps = UiTheme.subtitle("0 lecturas GPS");
    private final JLabel lblTemperatura = UiTheme.subtitle("0 lecturas de temperatura");
    private final JLabel lblAlertas = UiTheme.subtitle("0 alertas criticas");
    private final JLabel lblEstado = UiTheme.subtitle("Listo para capturar datos de sensores.");

    public GuiMonitoreoSensores() {
        super("Monitoreo de Sensores IoT", true, true, true, true);
        setSize(1120, 720);
        inicializarUI();
        cargarCamiones();
        cargarDatos();
    }

    private void inicializarUI() {
        getContentPane().setBackground(UiTheme.BACKGROUND);
        setLayout(new BorderLayout(12, 12));

        JPanel header = new JPanel(new BorderLayout(12, 8));
        header.setBackground(UiTheme.BACKGROUND);
        header.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 0, 16));

        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setBackground(UiTheme.BACKGROUND);
        titlePanel.add(UiTheme.title("Centro de Monitoreo IoT Hirata"));
        titlePanel.add(UiTheme.subtitle("GPS, rutas, rendimiento y temperatura de carga en tiempo real simulado."));
        header.add(titlePanel, BorderLayout.WEST);

        JPanel kpis = new JPanel(new GridLayout(1, 3, 12, 0));
        kpis.setBackground(UiTheme.BACKGROUND);
        kpis.add(kpi("RF-10 GPS", lblGps));
        kpis.add(kpi("RF-13 Temperatura", lblTemperatura));
        kpis.add(kpi("Alertas", lblAlertas));
        header.add(kpis, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);

        JTabbedPane tabs = new JTabbedPane();
        tabs.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 16, 0, 16));
        tabs.addTab("GPS y rutas", crearPanelGps());
        tabs.addTab("Temperatura de carga", crearPanelTemperatura());
        tabs.addTab("Reportes", crearPanelReportes());
        add(tabs, BorderLayout.CENTER);

        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(UiTheme.BACKGROUND);
        footer.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 16, 16, 16));
        footer.add(lblEstado, BorderLayout.WEST);
        add(footer, BorderLayout.SOUTH);
    }

    private JPanel kpi(String titulo, JLabel valor) {
        JPanel panel = UiTheme.surface();
        panel.setLayout(new GridLayout(2, 1));
        JLabel label = UiTheme.label(titulo);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(label);
        panel.add(valor);
        return panel;
    }

    private JPanel crearPanelGps() {
        JPanel panel = new JPanel(new BorderLayout(12, 12));
        panel.setBackground(UiTheme.BACKGROUND);
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 0, 12, 0));

        JPanel form = UiTheme.surface();
        form.setLayout(new GridBagLayout());
        GridBagConstraints gbc = baseConstraints();
        agregarCampo(form, gbc, 0, "Camion", cmbCamionGps);
        agregarCampo(form, gbc, 1, "Ruta", txtRuta);
        agregarCampo(form, gbc, 2, "Latitud", txtLatitud);
        agregarCampo(form, gbc, 3, "Longitud", txtLongitud);
        agregarCampo(form, gbc, 4, "Velocidad km/h", txtVelocidad);
        agregarCampo(form, gbc, 5, "Tiempo min.", txtTiempo);
        agregarCampo(form, gbc, 6, "Estado senal", cmbEstadoGps);

        JButton btnSimular = new JButton("Simular lectura GPS");
        JButton btnGuardar = new JButton("Guardar GPS");
        JButton btnActualizar = new JButton("Actualizar tabla");
        UiTheme.secondaryButton(btnSimular);
        UiTheme.primaryButton(btnGuardar);
        UiTheme.secondaryButton(btnActualizar);
        btnSimular.addActionListener(e -> simularGps());
        btnGuardar.addActionListener(e -> registrarGps());
        btnActualizar.addActionListener(e -> cargarDatos());

        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        acciones.setBackground(UiTheme.SURFACE);
        acciones.add(btnSimular);
        acciones.add(btnGuardar);
        acciones.add(btnActualizar);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        form.add(acciones, gbc);

        UiTheme.table(tblGps);
        panel.add(form, BorderLayout.WEST);
        panel.add(new JScrollPane(tblGps), BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelTemperatura() {
        JPanel panel = new JPanel(new BorderLayout(12, 12));
        panel.setBackground(UiTheme.BACKGROUND);
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 0, 12, 0));

        JPanel form = UiTheme.surface();
        form.setLayout(new GridBagLayout());
        GridBagConstraints gbc = baseConstraints();
        agregarCampo(form, gbc, 0, "Camion", cmbCamionTemperatura);
        agregarCampo(form, gbc, 1, "Temperatura C", txtTemperatura);
        agregarCampo(form, gbc, 2, "Limite critico C", txtLimite);
        agregarCampo(form, gbc, 3, "Observacion", new JScrollPane(txtObservacion));

        JButton btnSimular = new JButton("Simular temperatura");
        JButton btnGuardar = new JButton("Guardar temperatura");
        JButton btnActualizar = new JButton("Actualizar tabla");
        UiTheme.secondaryButton(btnSimular);
        UiTheme.primaryButton(btnGuardar);
        UiTheme.secondaryButton(btnActualizar);
        btnSimular.addActionListener(e -> simularTemperatura());
        btnGuardar.addActionListener(e -> registrarTemperatura());
        btnActualizar.addActionListener(e -> cargarDatos());

        JPanel acciones = new JPanel(new GridLayout(3, 1, 0, 8));
        acciones.setBackground(UiTheme.SURFACE);
        acciones.add(btnSimular);
        acciones.add(btnGuardar);
        acciones.add(btnActualizar);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        form.add(acciones, gbc);

        UiTheme.table(tblTemperatura);
        panel.add(form, BorderLayout.WEST);
        panel.add(new JScrollPane(tblTemperatura), BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelReportes() {
        JPanel panel = new JPanel(new BorderLayout(12, 12));
        panel.setBackground(UiTheme.BACKGROUND);
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 0, 12, 0));

        JPanel top = UiTheme.surface();
        top.setLayout(new BorderLayout(12, 0));
        top.add(UiTheme.subtitle("Analisis de rendimiento, rutas frecuentes y alertas de carga."), BorderLayout.WEST);

        JButton btnGenerar = new JButton("Generar reporte");
        JButton btnExportar = new JButton("Exportar TXT");
        UiTheme.primaryButton(btnGenerar);
        UiTheme.secondaryButton(btnExportar);
        btnGenerar.addActionListener(e -> cargarReportes());
        btnExportar.addActionListener(e -> exportarReporte());
        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        acciones.setBackground(UiTheme.SURFACE);
        acciones.add(btnGenerar);
        acciones.add(btnExportar);
        top.add(acciones, BorderLayout.EAST);

        UiTheme.table(tblReporte);
        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(tblReporte), BorderLayout.CENTER);
        return panel;
    }

    private GridBagConstraints baseConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new java.awt.Insets(6, 0, 6, 0);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        return gbc;
    }

    private void agregarCampo(JPanel panel, GridBagConstraints gbc, int fila, String label, java.awt.Component campo) {
        gbc.gridx = 0;
        gbc.gridy = fila;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        panel.add(UiTheme.label(label), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        panel.add(campo, gbc);
    }

    private void cargarCamiones() {
        List<Camion> camiones;
        try {
            camiones = camionDao.listarCamiones();
        } catch (RuntimeException ex) {
            lblEstado.setText("No pudimos cargar camiones. Revise la conexion a la base de datos.");
            return;
        }
        DefaultComboBoxModel<Camion> modeloGps = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<Camion> modeloTemp = new DefaultComboBoxModel<>();
        for (Camion camion : camiones) {
            modeloGps.addElement(camion);
            modeloTemp.addElement(camion);
        }
        cmbCamionGps.setModel(modeloGps);
        cmbCamionTemperatura.setModel(modeloTemp);
        if (camiones.isEmpty()) {
            lblEstado.setText("No hay camiones registrados. Registre flota antes de capturar sensores.");
        }
    }

    private void cargarDatos() {
        cargarGps();
        cargarTemperaturas();
        cargarReportes();
        actualizarIndicadores();
    }

    private void cargarGps() {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID", "Fecha", "Patente", "Ruta", "Latitud", "Longitud", "Km/h", "Min.", "Senal"}, 0);
        for (SensorGps lectura : sensorDao.listarLecturasGps()) {
            model.addRow(new Object[]{
                lectura.getIdLecturaGps(),
                lectura.getFechaHora(),
                lectura.getCamion().getPatente(),
                lectura.getRuta(),
                lectura.getLatitud(),
                lectura.getLongitud(),
                lectura.getVelocidadKmh(),
                lectura.getTiempoRecorridoMinutos(),
                lectura.getEstadoSenal()
            });
        }
        tblGps.setModel(model);
    }

    private void cargarTemperaturas() {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID", "Fecha", "Patente", "Temperatura C", "Limite C", "Estado", "Observacion"}, 0);
        for (SensorTemperatura lectura : sensorDao.listarLecturasTemperatura()) {
            model.addRow(new Object[]{
                lectura.getIdLecturaTemperatura(),
                lectura.getFechaHora(),
                lectura.getCamion().getPatente(),
                lectura.getTemperaturaCelsius(),
                lectura.getLimiteCritico(),
                lectura.getEstado(),
                lectura.getObservacion()
            });
        }
        tblTemperatura.setModel(model);
    }

    private void cargarReportes() {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Req.", "Indicador", "Valor", "Detalle"}, 0);
        for (ReporteSensor item : sensorDao.generarReporteSensores()) {
            model.addRow(new Object[]{
                item.getRequerimiento(),
                item.getIndicador(),
                item.getValor(),
                item.getDetalle()
            });
        }
        tblReporte.setModel(model);
    }

    private void actualizarIndicadores() {
        lblGps.setText(sensorDao.contarLecturasGps() + " lecturas GPS");
        lblTemperatura.setText(sensorDao.contarLecturasTemperatura() + " lecturas de temperatura");
        lblAlertas.setText(sensorDao.contarAlertasTemperatura() + " alertas criticas");
    }

    private void simularGps() {
        String[] rutas = {"Santiago - Valparaiso", "Santiago - Concepcion", "Rancagua - Talca", "Antofagasta - Calama"};
        txtRuta.setText(rutas[random.nextInt(rutas.length)]);
        txtLatitud.setText(String.format("%.6f", -33.45 + random.nextDouble() * 4).replace(',', '.'));
        txtLongitud.setText(String.format("%.6f", -70.66 + random.nextDouble() * 4).replace(',', '.'));
        txtVelocidad.setText(String.valueOf(55 + random.nextInt(45)));
        txtTiempo.setText(String.valueOf(45 + random.nextInt(360)));
        cmbEstadoGps.setSelectedIndex(random.nextInt(10) == 0 ? 1 : 0);
        lblEstado.setText("Lectura GPS simulada. Revise los datos y guarde.");
    }

    private void registrarGps() {
        if (cmbCamionGps.getSelectedItem() == null) {
            mostrarError("Seleccione un camion antes de guardar la lectura GPS.");
            return;
        }
        if ("Sin senal".equals(cmbEstadoGps.getSelectedItem())) {
            mostrarError("No hay senal GPS. No es posible registrar ubicacion confiable.");
            return;
        }
        try {
            SensorGps lectura = new SensorGps();
            lectura.setCamion((Camion) cmbCamionGps.getSelectedItem());
            lectura.setFechaHora(LocalDateTime.now());
            lectura.setRuta(validarTexto(txtRuta.getText(), "ruta"));
            lectura.setLatitud(Double.parseDouble(txtLatitud.getText().trim()));
            lectura.setLongitud(Double.parseDouble(txtLongitud.getText().trim()));
            lectura.setVelocidadKmh(Double.parseDouble(txtVelocidad.getText().trim()));
            lectura.setTiempoRecorridoMinutos(Integer.parseInt(txtTiempo.getText().trim()));
            lectura.setEstadoSenal(cmbEstadoGps.getSelectedItem().toString());
            if (sensorDao.registrarLecturaGps(lectura)) {
                lblEstado.setText("Lectura GPS guardada correctamente.");
                cargarDatos();
            }
        } catch (IllegalArgumentException ex) {
            mostrarError(ex.getMessage());
        }
    }

    private void simularTemperatura() {
        double valor = 2 + random.nextDouble() * 12;
        txtTemperatura.setText(String.format("%.1f", valor).replace(',', '.'));
        txtObservacion.setText(valor > 8 ? "Carga sobre el umbral recomendado." : "Condicion de carga normal.");
        lblEstado.setText("Lectura de temperatura simulada. Revise los datos y guarde.");
    }

    private void registrarTemperatura() {
        if (cmbCamionTemperatura.getSelectedItem() == null) {
            mostrarError("Seleccione un camion antes de guardar temperatura.");
            return;
        }
        try {
            double temperatura = Double.parseDouble(txtTemperatura.getText().trim());
            double limite = Double.parseDouble(txtLimite.getText().trim());
            String estado = temperatura > limite ? "Critico" : "Normal";

            SensorTemperatura lectura = new SensorTemperatura();
            lectura.setCamion((Camion) cmbCamionTemperatura.getSelectedItem());
            lectura.setFechaHora(LocalDateTime.now());
            lectura.setTemperaturaCelsius(temperatura);
            lectura.setLimiteCritico(limite);
            lectura.setEstado(estado);
            lectura.setObservacion(txtObservacion.getText().trim());

            if (sensorDao.registrarLecturaTemperatura(lectura)) {
                cargarDatos();
                if ("Critico".equals(estado)) {
                    JOptionPane.showMessageDialog(this,
                            "Temperatura critica detectada. Revise la carga y active protocolo de control.",
                            "Alerta de temperatura", JOptionPane.WARNING_MESSAGE);
                } else {
                    lblEstado.setText("Lectura de temperatura guardada correctamente.");
                }
            }
        } catch (NumberFormatException ex) {
            mostrarError("Ingrese valores numericos validos para temperatura y limite.");
        }
    }

    private String validarTexto(String valor, String campo) {
        String limpio = valor == null ? "" : valor.trim();
        if (limpio.isEmpty()) {
            throw new IllegalArgumentException("Complete el campo " + campo + ".");
        }
        return limpio;
    }

    private void exportarReporte() {
        if (tblReporte.getRowCount() == 0) {
            mostrarError("No hay datos de reporte para exportar.");
            return;
        }
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File("reporte_sensores_hirata.txt"));
        if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File file = chooser.getSelectedFile();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("Reporte de Sensores Hirata\n");
            writer.write("Generado: " + LocalDateTime.now() + "\n\n");
            for (int row = 0; row < tblReporte.getRowCount(); row++) {
                writer.write(tblReporte.getValueAt(row, 0) + " | "
                        + tblReporte.getValueAt(row, 1) + " | "
                        + tblReporte.getValueAt(row, 2) + " | "
                        + tblReporte.getValueAt(row, 3) + "\n");
            }
            lblEstado.setText("Reporte exportado: " + file.getAbsolutePath());
        } catch (IOException ex) {
            mostrarError("No pudimos exportar el reporte. Intente nuevamente.");
        }
    }

    private void mostrarError(String mensaje) {
        lblEstado.setText(mensaje);
        JOptionPane.showMessageDialog(this, mensaje, "Validacion", JOptionPane.WARNING_MESSAGE);
    }
}
