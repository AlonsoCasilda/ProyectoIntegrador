package view;

import java.awt.HeadlessException;
import java.awt.Image;
import javax.swing.JPanel;
import javax.swing.UIManager;
import control.ControladorPMusculation;
import model.Usuario;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class LoginVentana extends JDialog {
	
	private JPanel contenedor;	
	private JButton btnAcceder;
	private JTextField textFNombre;
	private JTextField textFIdentificador;
	private JComboBox<String> comboBox;


	public LoginVentana() throws HeadlessException {		
		setTitle("Login");
		inicializar();
	}

	private void inicializar() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(850, 300, 343, 459);

		contenedor = new JPanel();
		setContentPane(contenedor);
		contenedor.setLayout(null);
		
		btnAcceder = new JButton("Acceder");
		btnAcceder.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAcceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		btnAcceder.setBounds(24, 185, 264, 23);
		contenedor.add(btnAcceder);
		
		JLabel lblIntroduceTuNombre = new JLabel("Introduce tu Nombre e Identificador");
		lblIntroduceTuNombre.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIntroduceTuNombre.setBounds(24, 11, 264, 30);
		contenedor.add(lblIntroduceTuNombre);
		
		textFNombre = new JTextField();
		textFNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFNombre.setBounds(120, 52, 155, 20);
		contenedor.add(textFNombre);
		textFNombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombre.setBounds(24, 52, 68, 20);
		contenedor.add(lblNombre);
		
		textFIdentificador = new JTextField();
		textFIdentificador.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFIdentificador.setBounds(120, 99, 155, 20);
		contenedor.add(textFIdentificador);
		textFIdentificador.setColumns(10);
		
		JLabel lblIdentificador = new JLabel("Identificador:");
		lblIdentificador.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblIdentificador.setBounds(24, 99, 86, 20);
		contenedor.add(lblIdentificador);
		
		comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Monitor", "Cliente"}));
		comboBox.setBounds(162, 141, 126, 20);
		contenedor.add(comboBox);
		
		JLabel lblTipoDeUsusario = new JLabel("Tipo de usuario:");
		lblTipoDeUsusario.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTipoDeUsusario.setBounds(24, 143, 105, 17);
		contenedor.add(lblTipoDeUsusario);
		
		JLabel lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/portada1.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(10, 219, 307, 200);
		contenedor.add(lblNewLabel);
		
	}
	public Usuario obtenerDatos() {
		Usuario user = null;
		String nombre;
		int id;
		String tipo;
		
		if (textFIdentificador.getText().isEmpty() | textFNombre.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Debe rellenar todos los datos", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				nombre = textFNombre.getText();
				id = Integer.parseInt(textFIdentificador.getText());
				tipo = (String) comboBox.getSelectedItem();
				user = new Usuario(nombre, tipo, id);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "El identificador debe completarse con un dato numérico", "Error", JOptionPane.ERROR_MESSAGE);
			}			
		}
		
		return user;
	}
	
	
	public JButton getBtnAcceder() {
		return btnAcceder;
	}

	public void hacerVisible() {
	 	setVisible(true);	
	}	
	
	public void setControlador(ControladorPMusculation controlador) {		
		btnAcceder.addActionListener(controlador);
	}
}
