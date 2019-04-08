package model;
/** <h1>Clase Hija Model Cliente</h1> que extiende la clase Usuario, coniene los 3 atributos y el constructor definidos 
 * en la clase padre Usuario que permiten crear un objeto de tipo Cliente asignándole los valores correspondientes. 
 * @author Carlos
 * @since 07/04/2019
 * @version 1.0
 */

public class Cliente extends Usuario {
	
	/**<h2>método Cliente</h2>
	 * Constructor que recibe 2 parámetros para inicializar/asignar valor a los 3 atributos heredados.
	 * @param nombre de tipo String, contendrá el valor del nombre que se va a asignar al atributo <i>nombre</i>
	 * @param tipo de tipo String, inicializado y con el valor asignado de tipo Cliente
	 * @param iD de tipo int, contendrá el valor de número de identificaicón se va a asignar al atributo <i>ID</i>
	 */
	public Cliente(String nombre, int iD) {
		super(nombre, "cliente", iD);		
	}
	
	
	//traza
	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", tipo=" + tipo + ", ID=" + ID + "]";
	}
	
	

}
