package view;

import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import control.ControladorPMusculation;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class BienvenidaMonitor extends JFrame {
	
	private JPanel contenedor;
	private JButton btnCrear;
	private JLabel lblMonitorName;
	private JButton btnConsultar;


	public BienvenidaMonitor() throws HeadlessException {
		super("Crear Consultar");		
		inicializar();
	}

	private void inicializar() {
			
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setBounds(850, 400, 341, 205);

			contenedor = new JPanel();
			setContentPane(contenedor);
			contenedor.setLayout(null);
			
			btnCrear = new JButton("Crear Nuevo Ejercicio");
			btnCrear.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnCrear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btnCrear.setBounds(31, 70, 264, 30);
			contenedor.add(btnCrear);
			
			JLabel lblBienvenida = new JLabel("Bienvenido");
			lblBienvenida.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblBienvenida.setBounds(20, 10, 89, 30);
			contenedor.add(lblBienvenida);
			
			lblMonitorName = new JLabel("");
			lblMonitorName.setFont(new Font("Verdana", Font.PLAIN, 13));
			lblMonitorName.setBounds(146, 12, 169, 28);
			contenedor.add(lblMonitorName);
			
			btnConsultar = new JButton("Consultar Ejercicio");
			btnConsultar.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnConsultar.setBounds(31, 112, 264, 35);
			contenedor.add(btnConsultar);
		
	}
	
	
	public JButton getBtnCrear() {
		return btnCrear;
	}

	public JLabel getLblMonitorName() {
		return lblMonitorName;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public void hacerVisible() {
	 	setVisible(true);
	
	}	
	
	public void setControlador(ControladorPMusculation controlador) {			
		btnCrear.addActionListener(controlador);
		btnConsultar.addActionListener(controlador);
	}
}
