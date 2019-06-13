package main;

import java.awt.EventQueue;
import control.ControladorPMusculation;
import view.BienvenidaMonitor;
import view.ClienteConsultaEjercicio;
import view.LoginVentana;
import view.MonitorConsulta;
import view.MonitorCrear;
import view.MonitorModificar;

public class PMusculationMain {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {				
				LoginVentana hv = new LoginVentana();				
				ClienteConsultaEjercicio clienteConsulta = new ClienteConsultaEjercicio();
				BienvenidaMonitor bienvMon = new BienvenidaMonitor();
				MonitorCrear crearEj = new MonitorCrear();
				MonitorConsulta monitorConsulta = new MonitorConsulta();//
				MonitorModificar monitorModif = new MonitorModificar();
				
				ControladorPMusculation control = new ControladorPMusculation(hv, monitorConsulta, clienteConsulta, bienvMon, crearEj, monitorModif);
				
				hv.setControlador(control);
				monitorConsulta.setControlador(control);//
				clienteConsulta.setControlador(control);
				bienvMon.setControlador(control);
				crearEj.setControlador(control);
				monitorModif.setControlador(control);
				
				
				hv.hacerVisible();
			}
		});

	}

}