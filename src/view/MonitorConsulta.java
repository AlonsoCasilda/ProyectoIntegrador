package view;

import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import control.ControladorPMusculation;
import model.Ejercicio;
import javax.swing.JButton;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import java.awt.Font;

public class MonitorConsulta extends JFrame {
	
	private JPanel contenedor;
	private JLabel lblNombreMúsculo;
	private JTextPane textPaneDescripcion;
	private JList<String> listMusculos;
	private DefaultListModel<String> dlmM;
	private JList<String> listEjercicios;
	private DefaultListModel<String> dlmE;
	private JButton btnConsultaEjercicio;
	private JButton btnVerEjercicio;
	private ArrayList<Ejercicio>ejercicios;
	private JButton btnLimpiar;
	private JButton btnModificar;


	public MonitorConsulta() throws HeadlessException {
		super("Consulta de ejercicio");	
		ejercicios = new ArrayList<>();
		inicializar();
	}

	private void inicializar() {
			
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setBounds(810, 300, 386, 601);

			contenedor = new JPanel();
			setContentPane(contenedor);
			contenedor.setLayout(null);
			
			btnLimpiar = new JButton("Limpiar");
			btnLimpiar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnLimpiar.setBounds(10, 533, 157, 23);
			contenedor.add(btnLimpiar);
			
			lblNombreMúsculo = new JLabel("Músculo a ejercitar:");
			lblNombreMúsculo.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblNombreMúsculo.setBounds(10, 11, 141, 23);
			contenedor.add(lblNombreMúsculo);
			
			JScrollPane scrollPaneEjercicios = new JScrollPane();
			scrollPaneEjercicios.setBounds(10, 208, 166, 105);
			contenedor.add(scrollPaneEjercicios);
			
			listEjercicios = new JList<String>();
			listEjercicios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			dlmE = new DefaultListModel<String>();
			listEjercicios.setModel(dlmE);
			scrollPaneEjercicios.setViewportView(listEjercicios);
			
			
			JLabel lblListaEjercicios = new JLabel("Lista de ejercicios:");
			lblListaEjercicios.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblListaEjercicios.setBounds(10, 174, 181, 23);
			contenedor.add(lblListaEjercicios);
			
			JLabel lblInstrucciones = new JLabel("Instrucciones:");
			lblInstrucciones.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblInstrucciones.setBounds(10, 324, 108, 20);
			contenedor.add(lblInstrucciones);
			
			JScrollPane scrollPaneMusculos = new JScrollPane();
			scrollPaneMusculos.setBounds(10, 45, 166, 118);
			contenedor.add(scrollPaneMusculos);
			
			listMusculos = new JList<String>();
			listMusculos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			dlmM = new DefaultListModel<String>();
			listMusculos.setModel(dlmM);			
			scrollPaneMusculos.setViewportView(listMusculos);
			
			btnConsultaEjercicio = new JButton("Consultar Ejercicios");
			btnConsultaEjercicio.setFont(new Font("Tahoma", Font.PLAIN, 13));			
			btnConsultaEjercicio.setBounds(195, 45, 167, 118);
			contenedor.add(btnConsultaEjercicio);
			
			btnVerEjercicio = new JButton("Ver Ejercicio");
			btnVerEjercicio.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnVerEjercicio.setBounds(196, 206, 166, 107);
			contenedor.add(btnVerEjercicio);
			
			JScrollPane scrollDescripcion = new JScrollPane();
			scrollDescripcion.setBounds(10, 355, 352, 167);
			contenedor.add(scrollDescripcion);
			
			textPaneDescripcion = new JTextPane();
			textPaneDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
			scrollDescripcion.setViewportView(textPaneDescripcion);
			
			btnModificar = new JButton("Modificar Ejercicios");
			btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnModificar.setBounds(195, 533, 167, 23);
			contenedor.add(btnModificar);
		
	}
	
	public void cargarMusculos(ArrayList<String> listaMusculos) {
		for (String musculo : listaMusculos) {
			dlmM.addElement(musculo);
		}
		
		
	}
	public String obtenerMuscSelecc() {
		String musculoSelec = "";		
		
		if (listMusculos.isSelectionEmpty()) { 
			JOptionPane.showMessageDialog(this, "Seleccione el músculo a buscar", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			musculoSelec = listMusculos.getSelectedValue();			
		}
		return musculoSelec;		
	}
	
	public void cargarEjercicios(ArrayList<Ejercicio> listaEjercicios) {
		dlmE.clear();
		ejercicios = listaEjercicios;
		for (Ejercicio ejercicio : listaEjercicios) {
			dlmE.addElement(ejercicio.getNombreEj());
		}		
	}
	
	public Ejercicio obtenerEjercSelec() {
		String nombreEj;
		String descr = "";
		int rep = 0;
		Ejercicio ejercMostrar = null;
		
		if (listEjercicios.isSelectionEmpty()) {
			JOptionPane.showMessageDialog(this, "Seleccione el ejercicio a consultar", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			nombreEj = listEjercicios.getSelectedValue();
			for (Ejercicio ejercicio : ejercicios) {
				if (ejercicio.getNombreEj().equalsIgnoreCase(nombreEj)) {
					descr = ejercicio.getDescripcion();
					rep = ejercicio.getRepeticiones();
					ejercMostrar = new Ejercicio(nombreEj, descr, rep);
				}
			}
		}
		return ejercMostrar;		
	}
	
	public void mostrarDescr(Ejercicio ejercMos) {
		textPaneDescripcion.setText(ejercMos.getDescripcion() + ".\nRepeticiones: " + ejercMos.getRepeticiones());
	}
	
	public void limpiarCampos() {
		dlmE.clear();
		listMusculos.clearSelection();
		textPaneDescripcion.setText("");
	}
	
	public void hacerVisible() {
	 	setVisible(true);
	
	}
	
	public JButton getBtnConsultaEjercicio() {
		return btnConsultaEjercicio;
	}

	public JButton getBtnVerEjercicio() {
		return btnVerEjercicio;
	}
		
	public JButton getBtnLimpiar() {
		return btnLimpiar;
	}	

	public JButton getBtnModificar() {
		return btnModificar;
	}

	public void setControlador(ControladorPMusculation controlador) {
		btnConsultaEjercicio.addActionListener(controlador);
		btnVerEjercicio.addActionListener(controlador);
		btnLimpiar.addActionListener(controlador);
		btnModificar.addActionListener(controlador);
	}
}
