package ec.edu.pucem.bocaurna.gui;

import javax.swing.JInternalFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import ec.edu.pucem.bocaurna.models.Candidato;
import ec.edu.pucem.bocaurna.models.DatosVotacion;
import ec.edu.pucem.bocaurna.models.Provincia;
import ec.edu.pucem.bocaurna.models.ProvinciaManager;
import ec.edu.pucem.bocaurna.models.Voto;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FrmBocaUrna extends JInternalFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
	private List<Candidato> prefectos;
    private JComboBox<Provincia> comboBoxProvincia;
    private JComboBox<String> comboBoxCiudad;
    
    private JTable table;
    private DefaultTableModel model;
    private JPanel panel;
    private JButton btnCancelar;

    public FrmBocaUrna(List<Candidato> prefectos) {
        this.prefectos = prefectos;
        
        setTitle("Proceso de Boca de Urna");
        setBounds(100, 100, 600, 427);
        getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 172, 566, 167);
        getContentPane().add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Nombre", "Apellidos", "Partido", "Votos"}));
        scrollPane.setViewportView(table);

        panel = new JPanel();
        panel.setBounds(12, 76, 566, 84);
        getContentPane().add(panel);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
        });
        btnCancelar.setBounds(226, 349, 117, 25);
        getContentPane().add(btnCancelar);

        JLabel lblProvincia = new JLabel("Provincia");
        lblProvincia.setBounds(12, 21, 70, 15);
        getContentPane().add(lblProvincia);

        comboBoxProvincia = new JComboBox<>(ProvinciaManager.getProvincias());
        comboBoxProvincia.insertItemAt(new Provincia("Seleccione una provincia"), 0);
        comboBoxProvincia.setSelectedIndex(0);
        comboBoxProvincia.setBounds(79, 12, 231, 24);
        comboBoxProvincia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarCandidatosYCiudades();
            }
        });
        getContentPane().add(comboBoxProvincia);

        JLabel lblCiudad = new JLabel("Ciudad");
        lblCiudad.setBounds(12, 48, 70, 15);
        getContentPane().add(lblCiudad);

        comboBoxCiudad = new JComboBox<>();
        comboBoxCiudad.setBounds(79, 43, 231, 24);
        getContentPane().add(comboBoxCiudad);

        model = (DefaultTableModel) table.getModel();
        llenarTabla();
    }

    private void cargarCandidatosYCiudades() {
        Provincia provinciaSeleccionada = (Provincia) comboBoxProvincia.getSelectedItem();
        if (provinciaSeleccionada.getNombre().equals("Seleccione una provincia")) {
            comboBoxCiudad.removeAllItems();
            panel.removeAll();
            panel.revalidate();
            panel.repaint();
            return;
        }

        comboBoxCiudad.removeAllItems();
        for (String ciudad : ProvinciaManager.getCiudades(provinciaSeleccionada.getNombre())) {
            comboBoxCiudad.addItem(ciudad);
        }

        panel.removeAll();
        panel.revalidate();
        panel.repaint();
        int x = 0;
        for (Candidato candidato : prefectos) {
            if (candidato.getProvincia().getNombre().equals(provinciaSeleccionada.getNombre())) {
                JButton btnPrefecto = new JButton(candidato.getNombre());
                btnPrefecto.setBounds(x * 155, 0, 150, 80);
                btnPrefecto.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        registrarVoto(candidato, provinciaSeleccionada, (String) comboBoxCiudad.getSelectedItem());
                    }
                });
                panel.setLayout(null);
                panel.add(btnPrefecto);
                x++;
            }
        }
    }

    private void registrarVoto(Candidato candidato, Provincia provincia, String ciudad) {
        Voto voto = new Voto(candidato, provincia, ciudad);
        DatosVotacion.agregarVoto(voto); // Agregar voto a la lista compartida
        llenarTabla();
    }

    private void llenarTabla() {
        model.setRowCount(0);
        for (Candidato candidato : prefectos) {
            int votosCount = contarVotosPorCandidato(candidato);
            Object[] fila = new Object[4];
            fila[0] = candidato.getNombre();
            fila[1] = candidato.getApellido();
            fila[2] = candidato.getPartido();
            fila[3] = votosCount;
            model.addRow(fila);
        }
    }

    private int contarVotosPorCandidato(Candidato candidato) {
        int count = 0;
        List<Voto> votos = DatosVotacion.getVotos(); // Obtener la lista de votos actualizada
        for (Voto voto : votos) {
            if (voto.getCandidato().equals(candidato)) {
                count++;
            }
        }
        return count;
    }

    public List<Candidato> getPrefectos() {
        return prefectos;
    }

    public void setPrefectos(List<Candidato> prefectos) {
        this.prefectos = prefectos;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}

