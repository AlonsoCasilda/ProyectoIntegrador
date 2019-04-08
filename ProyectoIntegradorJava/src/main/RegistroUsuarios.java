package main;
/** <h1>Clase Main RegistroUsuarios</h1> que implementa los métodos para solicitar los datos y la creación de objetos de dos tipos:
 * Monitor, y Cliente. 
 * @author Carlos
 * @since 07/04/2019
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Scanner;

import model.Cliente;
import model.Monitor;
import model.Usuario;

public class RegistroUsuarios {
	static Scanner scan;
	static ArrayList<Usuario> usuarios;
	static int Id_m;
	static int Id_c;

	public static void main(String[] args) {
		scan = new Scanner(System.in);
		Id_m = 1; Id_c = 1;
		usuarios = new ArrayList<Usuario>();
		
		solicitarDatos();
		
		solicitarDatos();
		
		mostrarDatos();
		
		
		
		scan.close();

	}
	

	


	private static void solicitarDatos() {
		boolean datoOk = false;
		String tipo = null;
		
		while (!datoOk) {
			System.out.println("Introduzca el tipo de usuario: -Monitor- o -Cliente- o -Salir- para salir");
			tipo= scan.nextLine();
			if (tipo.trim().equalsIgnoreCase("monitor")) {
				registroMonitor();
				datoOk = true;
			} else if (tipo.trim().equalsIgnoreCase("cliente")) {
				registroCliente();
				datoOk = true;
			} else {
				System.err.println("El dato introducido debe ser -Monitor- o -Cliente-");
				
			}
		}		
		
	}

	private static void registroMonitor() {			
		System.out.println("Introduzca el nombre del Monitor");
		String nombre= scan.nextLine().trim();		
		usuarios.add(new Monitor(nombre, Id_m));
		Id_m++;
		
	}

	private static void registroCliente() {		
		System.out.println("Introduzca el nombre del Cliente");
		String nombre= scan.nextLine().trim();		
		usuarios.add(new Cliente(nombre, Id_c));
		Id_c++;
		
	}
	private static void mostrarDatos() {
		for (Usuario usuario : usuarios) {
			System.out.println(usuario);
		}
		
	}

}
