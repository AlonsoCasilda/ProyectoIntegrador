package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import bbdd.EjerciciosPersistencia;
import bbdd.MusculosPersistencia;
import bbdd.UsuariosPersistencia;
import model.Ejercicio;
import model.Usuario;
import view.BienvenidaMonitor;
import view.ClienteConsultaEjercicio;
import view.LoginVentana;
import view.MonitorConsulta;
import view.MonitorCrear;
import view.MonitorModificar;


public class ControladorPMusculation extends MouseAdapter implements ActionListener, ItemListener {
	//crear atributos de las clases ventana
	private UsuariosPersistencia usuarioDatos;	
	private ClienteConsultaEjercicio clienteConsulta;
	private BienvenidaMonitor bienvMon;
	private LoginVentana hv;
	private MusculosPersistencia musculosDatos;
	private EjerciciosPersistencia ejercicioDatos;
	private MonitorCrear crearEj;	
	private MonitorConsulta monitorConsulta;
	private MonitorModificar monitorModif;
	private ArrayList<String>listadeMusculos;

	public ControladorPMusculation(LoginVentana hv, MonitorConsulta monitorConsulta, ClienteConsultaEjercicio clienteConsulta, BienvenidaMonitor bienvMon, MonitorCrear crearEj,
			MonitorModificar monitorModif) {		
		this.hv = hv;
		this.monitorConsulta = monitorConsulta;
		this.clienteConsulta = clienteConsulta;
		this.bienvMon = bienvMon;
		this.crearEj = crearEj;	
		this.monitorModif = monitorModif; //
		usuarioDatos = new UsuariosPersistencia();
		musculosDatos = new MusculosPersistencia();
		ejercicioDatos = new EjerciciosPersistencia();
		listadeMusculos = musculosDatos.consultarMusculos();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == hv.getBtnAcceder()) {
			Usuario user = hv.obtenerDatos();					
			if (user != null) {
				boolean userOk = usuarioDatos.comprobarUsuario(user);
				if (userOk) {
					if (user.getTipo().equalsIgnoreCase("Cliente")) {						
						clienteConsulta.cargarMusculos(listadeMusculos);
						hv.dispose();
						clienteConsulta.hacerVisible();					
					} else {
						hv.dispose();
						bienvMon.getLblMonitorName().setText(user.getNombre());
						bienvMon.hacerVisible();						
					}
				} else {
					JOptionPane.showMessageDialog(hv, "Los datos del usuario no son correctos", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (e.getSource() == clienteConsulta.getBtnConsultaEjercicio()) {			
			String musculoSect = clienteConsulta.obtenerMuscSelecc();			
			if (musculoSect != null) {
				ArrayList<Ejercicio> listaEjercicios = ejercicioDatos.consultarEjercicio(musculoSect);
				clienteConsulta.cargarEjercicios(listaEjercicios);
			}
		} else if (e.getSource() == clienteConsulta.getBtnVerEjercicio()) {
			Ejercicio ejercMostrar = clienteConsulta.obtenerEjercSelec();
			if (ejercMostrar != null) {
				clienteConsulta.mostrarDescr(ejercMostrar);
			} 
		} else if (e.getSource() == clienteConsulta.getBtnLimpiar()) {
			clienteConsulta.limpiarCampos();
		} else if (e.getSource() == bienvMon.getBtnCrear()) {
			//bienvMon.dispose();
			crearEj.hacerVisible();			
			crearEj.cargarMusculos(listadeMusculos);
		} else if (e.getSource() == crearEj.getBtnLimpiar()) {
			crearEj.limpiarCampos();
		} else if (e.getSource() == crearEj.getBtnCrearNuevo()) {
			Ejercicio ejercicioNuevo = crearEj.obtenerDatosCrear();
			if (ejercicioNuevo != null) {
				int resul = ejercicioDatos.registrarEjercicio(ejercicioNuevo);
				if (resul != 0) {
					JOptionPane.showMessageDialog(crearEj, "El ejercicio se ha registrado correctamente", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
					crearEj.limpiarCampos();
				}				
			}
		} else if (e.getSource() == bienvMon.getBtnConsultar()) {
			monitorConsulta.cargarMusculos(listadeMusculos);
			monitorConsulta.hacerVisible();			
		} else if (e.getSource() == monitorConsulta.getBtnConsultaEjercicio()) {
			String musculoSelect = monitorConsulta.obtenerMuscSelecc();			
			if (musculoSelect != null) {				
				ArrayList<Ejercicio> listadEjercicios = ejercicioDatos.consultarEjercicio(musculoSelect);				
				monitorConsulta.cargarEjercicios(listadEjercicios);
			} 
		} else if (e.getSource() == monitorConsulta.getBtnVerEjercicio()) {
			Ejercicio ejercMostrar = monitorConsulta.obtenerEjercSelec();
			if (ejercMostrar != null) {
				monitorConsulta.mostrarDescr(ejercMostrar);
			} 
		} else if (e.getSource() == monitorConsulta.getBtnLimpiar()) {
			monitorConsulta.limpiarCampos();
		} else if (e.getSource() == monitorConsulta.getBtnModificar()) {
			monitorConsulta.dispose();
			monitorModif.hacerVisible();
			monitorModif.cargarMusculos(listadeMusculos);
			ArrayList<Ejercicio> listaEjercicios = ejercicioDatos.consultarEjerciciosCompleto();
			monitorModif.cargarlistEjercicios(listaEjercicios);			
		} else if (e.getSource() == monitorModif.getBtnCargar()) {
			monitorModif.limpiarCampos(false);
			String ejercicioCargar = monitorModif.obtenerEjercModif();
			monitorModif.cargarDatosEjercicio(ejercicioCargar);
		} else if (e.getSource() == monitorModif.getBtnLimpiar()) {
			monitorModif.limpiarCampos(true);
		} else if (e.getSource() == monitorModif.getBtnModificar()) {
			Ejercicio ejercModif = monitorModif.obtenerDatosModificar();
			if (ejercModif != null) {
				int result = ejercicioDatos.modificarEjercicio(ejercModif);
				if (result != 0) {
					JOptionPane.showMessageDialog(monitorModif, "El ejercicio se ha modificado correctamente", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
					monitorModif.limpiarCampos(true);
					ArrayList<Ejercicio> listaEjercicios = ejercicioDatos.consultarEjerciciosCompleto();
					monitorModif.cargarlistEjercicios(listaEjercicios);		
				} else {
					JOptionPane.showMessageDialog(monitorModif, "No se ha podido efectuar la modificación", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (e.getSource() == monitorModif.getBtnEliminar()) {
			String ejercDelente = monitorModif.obtenerEjercModif();
			int opcion = JOptionPane.showConfirmDialog(monitorModif, "¿Desea eliminar el ejercicio '" + ejercDelente + "' ?", "Confirmar borrado", JOptionPane.YES_NO_OPTION);
			if (opcion == 0) {					
				int resul = ejercicioDatos.eliminarEjercicio(ejercDelente);
				if (resul != 0) {
					JOptionPane.showMessageDialog(monitorModif, "El ejercicio se ha eliminado correctamente", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
					monitorModif.limpiarCampos(true);
					ArrayList<Ejercicio> listaEjercicios = ejercicioDatos.consultarEjerciciosCompleto();
					monitorModif.cargarlistEjercicios(listaEjercicios);		
				} else {
					JOptionPane.showMessageDialog(monitorModif, "No se ha podido eliminar el ejercicio", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		}

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			if (crearEj.getRdbtnAdd().isSelected()) {
				String muscSelec = crearEj.obtenerMuscSel();
				crearEj.cargarListaAdd(muscSelec);
				crearEj.getListMuscDispon().clearSelection();	
				crearEj.getListMuscAdd().clearSelection(); //
				crearEj.getButtonGroup().clearSelection();//
			} else if (crearEj.getRdbtnEliminar().isSelected()) {
				String muscBorrar = crearEj.obtenerMuscBorrar();
				crearEj.actualizarListaAdd(muscBorrar);
				crearEj.getListMuscAdd().clearSelection();
				crearEj.getListMuscDispon().clearSelection(); //
				crearEj.getButtonGroup().clearSelection();
			} else if(monitorModif.getRdbtnAdd().isSelected()) {
				String muscSelec = monitorModif.obtenerMuscSel();
				monitorModif.cargarListaAdd(muscSelec);
				monitorModif.getListMuscDispon().clearSelection();
				monitorModif.getListMuscAdd().clearSelection(); //
				monitorModif.getButtonGroup().clearSelection();//
			} else if (monitorModif.getRdbtnEliminar().isSelected()) {
				String muscBorrar = monitorModif.obtenerMuscBorrar();
				monitorModif.actualizarListaAdd(muscBorrar);
				monitorModif.getListMuscAdd().clearSelection();
				monitorModif.getListMuscDispon().clearSelection(); //
				monitorModif.getButtonGroup().clearSelection();
			}	
		}		
	}
	
	@Override
	public void mouseClicked(MouseEvent ev) {
		if (!crearEj.getListMuscDispon().isSelectionEmpty() ) {
			crearEj.getButtonGroup().clearSelection();				
		} else if (!monitorModif.getListMuscDispon().isSelectionEmpty()) {
			monitorModif.getButtonGroup().clearSelection();
		}
	}

	

}
