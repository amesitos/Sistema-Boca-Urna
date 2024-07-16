package ec.edu.pucem.bocaurna.gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import ec.edu.pucem.bocaurna.models.Candidato;
import ec.edu.pucem.bocaurna.models.Provincia;
import ec.edu.pucem.bocaurna.models.ProvinciaManager;
import ec.edu.pucem.bocaurna.models.Voto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class FrmReporteCanton extends JInternalFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private List<Candidato> prefectos;
    private List<Voto> votos;

    private JComboBox<Provincia> comboBoxProvincia;
    private JComboBox<String> comboBoxCiudad;

    private JTable table;
    private DefaultTableModel model;
    private JButton btnCancelar;

    public FrmReporteCanton(List<Candidato> prefectos, List<Voto> votos) {
        this.prefectos = prefectos;
        this.votos = votos;

        setTitle("Reporte por Ciudad");
        setBounds(100, 100, 600, 427);
        getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 172, 566, 167);
        getContentPane().add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Nombre", "Apellidos", "Partido", "Votos"}));
        scrollPane.setViewportView(table);

        JLabel lblProvincia = new JLabel("Provincia");
        lblProvincia.setBounds(12, 21, 70, 15);
        getContentPane().add(lblProvincia);

        comboBoxProvincia = new JComboBox<>(ProvinciaManager.getProvincias());
        comboBoxProvincia.insertItemAt(new Provincia("Seleccione una provincia"), 0);
        comboBoxProvincia.setSelectedIndex(0);
        comboBoxProvincia.setBounds(79, 12, 231, 24);
        comboBoxProvincia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarCiudades();
            }
        });
        getContentPane().add(comboBoxProvincia);

        JLabel lblCiudad = new JLabel("Ciudad");
        lblCiudad.setBounds(12, 48, 70, 15);
        getContentPane().add(lblCiudad);

        comboBoxCiudad = new JComboBox<>();
        comboBoxCiudad.setBounds(79, 43, 231, 24);
        comboBoxCiudad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                llenarTabla();
            }
        });
        getContentPane().add(comboBoxCiudad);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnCancelar.setBounds(157, 351, 117, 25);
        getContentPane().add(btnCancelar);

        model = (DefaultTableModel) table.getModel();
    }

    private void cargarCiudades() {
        Provincia provinciaSeleccionada = (Provincia) comboBoxProvincia.getSelectedItem();
        if (provinciaSeleccionada == null || provinciaSeleccionada.getNombre().equals("Seleccione una provincia")) {
            comboBoxCiudad.removeAllItems();
            System.out.println("Ninguna provincia seleccionada o 'Seleccione una provincia' seleccionado.");
            return;
        }

        System.out.println("Provincia seleccionada: " + provinciaSeleccionada.getNombre());
        comboBoxCiudad.removeAllItems();
        for (String ciudad : ProvinciaManager.getCiudades(provinciaSeleccionada.getNombre())) {
            comboBoxCiudad.addItem(ciudad);
        }
    }

    private void llenarTabla() {
        model.setRowCount(0); // Limpia la tabla antes de llenarla
        String ciudadSeleccionada = (String) comboBoxCiudad.getSelectedItem();
        Provincia provinciaSeleccionada = (Provincia) comboBoxProvincia.getSelectedItem();

        if (ciudadSeleccionada == null || provinciaSeleccionada == null || provinciaSeleccionada.getNombre().equals("Seleccione una provincia")) {
            System.out.println("Provincia o ciudad no seleccionada.");
            return;
        }

        System.out.println("Llenando la tabla para la ciudad: " + ciudadSeleccionada + " en la provincia: " + provinciaSeleccionada.getNombre());

        for (Candidato candidato : prefectos) {
            System.out.println("Revisando candidato: " + candidato.getNombre() + " " + candidato.getApellido() + " en la provincia: " + candidato.getProvincia().getNombre());
            if (candidato.getProvincia().getNombre().equals(provinciaSeleccionada.getNombre())) {
                System.out.println("Candidato coincide con la provincia.");
                int votosCount = contarVotosPorCandidatoYCiudad(candidato, ciudadSeleccionada);
                System.out.println("Votos para " + candidato.getNombre() + " en " + ciudadSeleccionada + ": " + votosCount);
                if (votosCount > 0) {
                    Object[] fila = new Object[4];
                    fila[0] = candidato.getNombre();
                    fila[1] = candidato.getApellido();
                    fila[2] = candidato.getPartido();
                    fila[3] = votosCount;
                    model.addRow(fila);
                }
            }
        }
    }

    private int contarVotosPorCandidatoYCiudad(Candidato candidato, String ciudad) {
        int count = 0;
        for (Voto voto : votos) {
            if (voto.getCandidato().equals(candidato) && voto.getCiudad().equals(ciudad)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}




