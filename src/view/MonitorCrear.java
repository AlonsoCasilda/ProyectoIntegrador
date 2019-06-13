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
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;

public class MonitorCrear extends JFrame {
	
	private JPanel contenedor;	
	private JButton btnCrearNuevo;
	private JLabel lblNombre;
	private JTextField textFNombreEj;
	private JRadioButton rdbtnAdd;
	private JTextArea textAInstrucc;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnLimpiar;
	private JRadioButton rdbtnEliminar;
	private ArrayList<String>listadMusculos;
	private JList <String>listMuscDispon;
	private DefaultListModel<String> dlmMDispon;
	private JList <String> listMuscAdd;
	private DefaultListModel<String> dlmMAdd;
	private JSpinner spinRep;


	public MonitorCrear() throws HeadlessException {
		super("Alta de ejercicio");	
		listadMusculos = new ArrayList<String>();
		inicializar();
	}

	private void inicializar() {
			
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(800, 300, 462, 528);

		contenedor = new JPanel();
		setContentPane(contenedor);
		contenedor.setLayout(null);
		
		btnCrearNuevo = new JButton("Crear");		
		btnCrearNuevo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		btnCrearNuevo.setBounds(40, 449, 124, 23);
		contenedor.add(btnCrearNuevo);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLimpiar.setBounds(274, 449, 124, 23);
		contenedor.add(btnLimpiar);
		
		lblNombre = new JLabel("Nombre del Ejercicio:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(10, 11, 121, 23);
		contenedor.add(lblNombre);
		
		textFNombreEj = new JTextField();
		textFNombreEj.setBounds(147, 12, 251, 20);
		contenedor.add(textFNombreEj);
		textFNombreEj.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 79, 391, 96);
		contenedor.add(scrollPane);
		
		textAInstrucc = new JTextArea();
		scrollPane.setViewportView(textAInstrucc);
		
		JLabel lblNewLabel = new JLabel("Instrucciones para hacer el ejercicio:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(20, 45, 233, 23);
		contenedor.add(lblNewLabel);
		
		JLabel lblMsculos = new JLabel("Músculos disponibles:");
		lblMsculos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMsculos.setBounds(20, 257, 138, 20);
		contenedor.add(lblMsculos);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 288, 132, 132);
		contenedor.add(scrollPane_1);
		
		listMuscDispon = new JList<String>();		
		listMuscDispon.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dlmMDispon = new DefaultListModel<String>();
		listMuscDispon.setModel(dlmMDispon);
		scrollPane_1.setViewportView(listMuscDispon);	
		
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(289, 288, 132, 132);
		contenedor.add(scrollPane_2);
		
		listMuscAdd = new JList<String>();
		listMuscAdd.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dlmMAdd = new DefaultListModel<String>();
		listMuscAdd.setModel(dlmMAdd);		
		scrollPane_2.setViewportView(listMuscAdd);
		
		
		rdbtnAdd = new JRadioButton("Añadir Músculo");
		rdbtnAdd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonGroup.add(rdbtnAdd);
		rdbtnAdd.setBounds(154, 299, 129, 23);
		contenedor.add(rdbtnAdd);
		
		rdbtnEliminar = new JRadioButton("Eliminar Músculo");
		rdbtnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonGroup.add(rdbtnEliminar);
		rdbtnEliminar.setBounds(158, 376, 121, 23);
		contenedor.add(rdbtnEliminar);
		
		JLabel lblNewLabel_1 = new JLabel("Músculos añadidos:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(300, 260, 136, 14);
		contenedor.add(lblNewLabel_1);		
		
		JLabel lblNumRep = new JLabel("Número de Repeticiones:");
		lblNumRep.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNumRep.setBounds(44, 203, 160, 14);
		contenedor.add(lblNumRep);
		
		spinRep = new JSpinner();
		spinRep.setModel(new SpinnerNumberModel(1, 0, 50, 1));
		spinRep.setBounds(245, 201, 54, 20);
		contenedor.add(spinRep);
	}
	
	public void cargarMusculos(ArrayList<String> listaMusculos) {
		listadMusculos = listaMusculos;
		for (String musculo : listadMusculos) {
			dlmMDispon.addElement(musculo);
		}
		
	}
	
	public String obtenerMuscSel() {
		String musculoSelec = null;
		
		if (listMuscDispon.isSelectionEmpty()) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar al menos un músculo", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			musculoSelec = listMuscDispon.getSelectedValue();
		}
		return musculoSelec;
	}
	
	public void cargarListaAdd(String muscSelec) {
		if (!dlmMAdd.contains(muscSelec)) {
			dlmMAdd.addElement(muscSelec);
		}		
	}
	
	public String obtenerMuscBorrar() {
		String muscBorrar = "";
		
		if (listMuscAdd.isSelectionEmpty()) {
			JOptionPane.showMessageDialog(this, "No hay ningún músculo para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			muscBorrar = listMuscAdd.getSelectedValue();
		}
		
		return muscBorrar;
	}

	public void actualizarListaAdd(String muscBorrar) {
		dlmMAdd.removeElement(muscBorrar);
		
	}
	
	public Ejercicio obtenerDatosCrear(){
		Ejercicio ejercicioN = null;
		String nombreE;
		String descrip;
		int rept;
		String musculos = "";
		if (textFNombreEj.getText().isEmpty() | textAInstrucc.getText().isEmpty() | dlmMAdd.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Debe rellenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			nombreE = textFNombreEj.getText();
			descrip = textAInstrucc.getText(); 
			rept = (int) spinRep.getValue();
			for(int i = 0; i < listMuscAdd.getModel().getSize(); i++){
				 musculos = musculos + " " +listMuscAdd.getModel().getElementAt(i);
				if (i < listMuscAdd.getModel().getSize() -1) {
					musculos += ",";
				}			    
			}
			ejercicioN = new Ejercicio(nombreE, descrip, rept, musculos);
			
		}
		return ejercicioN;
	}
	public void limpiarCampos() {
		textFNombreEj.setText("");
		textAInstrucc.setText("");
		spinRep.setValue(1);
		dlmMAdd.removeAllElements();
		
	}
	
	public JButton getBtnCrearNuevo() {
		return btnCrearNuevo;
	}

	public JRadioButton getRdbtnAdd() {
		return rdbtnAdd;
	}

	public JButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public JRadioButton getRdbtnEliminar() {
		return rdbtnEliminar;
	}	
	

	public JList<String> getListMuscDispon() {
		return listMuscDispon;
	}

	public JList<String> getListMuscAdd() {
		return listMuscAdd;
	}

	
	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}

	public void hacerVisible() {
	 	setVisible(true);
	
	}
	
	
	public void setControlador(ControladorPMusculation controlador) {			
		btnCrearNuevo.addActionListener(controlador);
		btnLimpiar.addActionListener(controlador);
		rdbtnAdd.addItemListener(controlador); 
		rdbtnEliminar.addItemListener(controlador);
		listMuscDispon.addMouseListener(controlador);
		listMuscAdd.addMouseListener(controlador);
	}

	

	
}
