package ec.edu.pucem.bocaurna.gui;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import ec.edu.pucem.bocaurna.models.Candidato;
import ec.edu.pucem.bocaurna.models.Voto;

import javax.swing.JMenuBar;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDesktopPane;

import java.awt.BorderLayout;
import java.awt.CardLayout;

public class FrmMenuPrincipal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contenedor;
	private JDesktopPane desktopPane;
	private JMenuItem mntmSalir;
	private JMenuItem mntmCrearPrefectos;
	private JMenuItem mntmListaPrefectos;
	private JMenuItem mntmBocaDeUrna;
	
	private FrmCrearCandidato frmCrearCandidato;
    private FrmListaCandidatos frmListaCandidatos;
    private FrmBocaUrna frmBocaUrna;
    private FrmReporteCanton frmResultadosCanton;
    private FrmReporteProvincia frmResultadosProvincia;

	public List<Candidato> prefectos = new ArrayList<>();
	public List<Voto> votos = new ArrayList<>();
	private JMenuItem mntmResultadosBarras;
	private JMenuItem mntmResultadosPastel;
	private JMenuItem mntmResultadosPorProvincia;

	public FrmMenuPrincipal() {
		setTitle("Sistema Boca de Urna - Prefectos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 255, 255));
		setJMenuBar(menuBar);

		JMenu mnSistema = new JMenu("Sistema");
		mnSistema.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(mnSistema);

		mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mntmSalir.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mnSistema.add(mntmSalir);

		JMenu mnAdministracion = new JMenu("Administración");
		mnAdministracion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(mnAdministracion);

		mntmCrearPrefectos = new JMenuItem("Crear Prefectos");
		mntmCrearPrefectos.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mntmCrearPrefectos.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (frmCrearCandidato == null || frmCrearCandidato.isClosed()) {
                    frmCrearCandidato = new FrmCrearCandidato(prefectos);
                    desktopPane.add(frmCrearCandidato);
                    frmCrearCandidato.setVisible(true);

                    Dimension desktopSize = desktopPane.getSize();
                    Dimension frameSize = frmCrearCandidato.getSize();
                    frmCrearCandidato.setLocation((desktopSize.width - frameSize.width) / 2,
                            (desktopSize.height - frameSize.height) / 2);
                }
            }			
		});
		mnAdministracion.add(mntmCrearPrefectos);
		
		mntmListaPrefectos = new JMenuItem("Lista de prefectos");
		mntmListaPrefectos.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mntmListaPrefectos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (frmListaCandidatos == null || frmListaCandidatos.isClosed()) {
					frmListaCandidatos = new FrmListaCandidatos(prefectos);
                    desktopPane.add(frmListaCandidatos);
                    frmListaCandidatos.setVisible(true);

                    Dimension desktopSize = desktopPane.getSize();
                    Dimension frameSize = frmListaCandidatos.getSize();
                    frmListaCandidatos.setLocation((desktopSize.width - frameSize.width) / 2,
                            (desktopSize.height - frameSize.height) / 2);
                }
            }			
		});
		mnAdministracion.add(mntmListaPrefectos);
		
		
		JMenu mnProceso = new JMenu("Proceso");
		mnProceso.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(mnProceso);

		mntmBocaDeUrna = new JMenuItem("Boca de Urna");
		mntmBocaDeUrna.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mntmBocaDeUrna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (frmBocaUrna == null || frmBocaUrna.isClosed()) {
					frmBocaUrna = new FrmBocaUrna(prefectos);
                    desktopPane.add(frmBocaUrna);
                    frmBocaUrna.setVisible(true);

                    Dimension desktopSize = desktopPane.getSize();
                    Dimension frameSize = frmBocaUrna.getSize();
                    frmBocaUrna.setLocation((desktopSize.width - frameSize.width) / 2,
                            (desktopSize.height - frameSize.height) / 2);
                }
			}
		});
		mnProceso.add(mntmBocaDeUrna);

		JMenu mnReportes = new JMenu("Reportes");
		mnReportes.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(mnReportes);

		mntmResultadosPorProvincia = new JMenuItem("Resultados por provincia ");
		mntmResultadosPorProvincia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmReporteProvincia rg = new FrmReporteProvincia(prefectos);
				desktopPane.add(rg);
				rg.setVisible(true);
			}
		});
		mntmResultadosPorProvincia.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mnReportes.add(mntmResultadosPorProvincia);

		JMenuItem mntmResultadosPorCantn = new JMenuItem("Resultados por cantón ");
		mntmResultadosPorCantn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (frmResultadosCanton == null || frmResultadosCanton.isClosed()) {
					frmResultadosCanton = new FrmReporteCanton(prefectos, votos);
                    desktopPane.add(frmResultadosCanton);
                    frmResultadosCanton.setVisible(true);

                    Dimension desktopSize = desktopPane.getSize();
                    Dimension frameSize = frmResultadosCanton.getSize();
                    frmResultadosCanton.setLocation((desktopSize.width - frameSize.width) / 2,
                            (desktopSize.height - frameSize.height) / 2);
                }
			}	
		});
		mntmResultadosPorCantn.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mnReportes.add(mntmResultadosPorCantn);

		JMenu mnGrficos = new JMenu("Gráficos");
		mnGrficos.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(mnGrficos);

		mntmResultadosBarras = new JMenuItem("Resultados Barras");
		mnGrficos.add(mntmResultadosBarras);
		mntmResultadosBarras.addActionListener(this);
		mntmResultadosBarras.setFont(new Font("Segoe UI", Font.BOLD, 16));

		mntmResultadosPastel = new JMenuItem("Resultados Pastel");
		mnGrficos.add(mntmResultadosPastel);
		mntmResultadosPastel.addActionListener(this);
		mntmResultadosPastel.setFont(new Font("Segoe UI", Font.BOLD, 16));
		contenedor = new JPanel();
		contenedor.setBackground(new Color(255, 255, 255));
		contenedor.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contenedor);
		contenedor.setLayout(new CardLayout(0, 0));

		desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(192, 192, 192));
		contenedor.add(desktopPane, "name_35522358088801");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmSalir) {
			dispose();
		} else if (e.getSource() == mntmListaPrefectos) {
			FrmCrearCandidato crearPrefecto = new FrmCrearCandidato(prefectos);
			desktopPane.add(crearPrefecto);
			crearPrefecto.setVisible(true);

		} else if (e.getSource() == mntmBocaDeUrna) {
			FrmBocaUrna bocaUrna = new FrmBocaUrna(prefectos);
			desktopPane.add(bocaUrna);
			bocaUrna.setVisible(true);
		} else if (e.getSource() == mntmResultadosBarras) {
			crearResultadosEnBarras();
		} else if (e.getSource() == mntmResultadosPastel) {
			crearResultadosEnPastel();
		} 

	}

	private void crearResultadosEnBarras() {
	    final JInternalFrame frame = new JInternalFrame("Resultado en Barras", true);
	    final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

	    Map<Candidato, Integer> votosPorCandidato = contarVotosPorCandidato();
	    for (Map.Entry<Candidato, Integer> entry : votosPorCandidato.entrySet()) {
	        Candidato candidato = entry.getKey();
	        int votos = entry.getValue();
	        dataset.addValue(votos, "Votos", candidato.getNombre() + " " + candidato.getApellido());
	    }

	    final JFreeChart chart = ChartFactory.createBarChart("Resultados de Votación", "Candidatos", "Número de Votos", dataset,
	            PlotOrientation.VERTICAL, true, true, false);
	    final ChartPanel chartPanel = new ChartPanel(chart);
	    chartPanel.setPreferredSize(new Dimension(600, 400));

	    final JPanel panel = new JPanel();
	    panel.setBounds(0, 0, 600, 400);
	    panel.setLayout(new BorderLayout());
	    panel.add(chartPanel);

	    JButton btnCancelar = new JButton("Cancelar");
	    btnCancelar.setPreferredSize(new Dimension(100, 30)); // Tamaño más pequeño
	    btnCancelar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	            frame.dispose();
	        }
	    });

	    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Panel para centrar el botón
	    buttonPanel.add(btnCancelar);
	    panel.add(buttonPanel, BorderLayout.SOUTH);

	    frame.getContentPane().add(panel);
	    desktopPane.add(frame);
	    frame.pack();
	    frame.setVisible(true);
	}

	private void crearResultadosEnPastel() {
		final JInternalFrame frame = new JInternalFrame("Resultado en Pastel", true);

		DefaultPieDataset datos = new DefaultPieDataset();
		for (Candidato prefecto : prefectos) {
			//datos.setValue(prefecto.getNombre(), prefecto.getVotos());
		}

		JFreeChart grafico = ChartFactory.createPieChart("Prefectos", datos);
		ChartPanel chartPanel = new ChartPanel(grafico);
		chartPanel.setBounds(10, 50, 450, 350);

		final JPanel panel = new JPanel();
		panel.setBounds(0, 0, 600, 400);
		panel.setLayout(new BorderLayout());
		panel.add(chartPanel);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});

		panel.add(btnCancelar, BorderLayout.SOUTH);
		frame.getContentPane().add(panel);
		desktopPane.add(frame);
		frame.pack();
		frame.setVisible(true);
	}
	
	private Map<Candidato, Integer> contarVotosPorCandidato() {
	    Map<Candidato, Integer> votosPorCandidato = new HashMap<>();
	    for (Candidato candidato : prefectos) {
	        votosPorCandidato.put(candidato, 0);
	    }
	    for (Voto voto : votos) {
	        Candidato candidato = voto.getCandidato();
	        if (votosPorCandidato.containsKey(candidato)) {
	            votosPorCandidato.put(candidato, votosPorCandidato.get(candidato) + 1);
	        }
	    }
	    return votosPorCandidato;
	}

}
