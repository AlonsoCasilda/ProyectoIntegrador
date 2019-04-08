package model;
/**
 * <h1>Clase Padre Model Usuario</h1> definida como abstracta que coniene los atributos con los que formaremos objetos de tipo 
 * Usuario que serán definidos en las clases hijas. Es abstracta ya que no va a tener implementación ni
 * instanciación.
 * @author Carlos
 * @since 07/04/2019
 * @version 1.0
 */

public abstract class Usuario {	
	/** Atributos definidos como protected ya que serán heredados en las clases hijas: 	 
	 *  <ul>
	 *  	<li><i>nombre</i> de tipo String, contiene el nombre del usuario</li>
	 *  	<li><i>tipo</i> de tipo String, diferencia si el usuario es monitor o cliente</li>
	 *  	<li><i>ID</i> de tipo int, representa el número de identificación del usuario</li>
	 *  </ul> 
	 * Tambien se describe el constructor Usuario */
	
	protected String nombre;
	protected String tipo;
	protected int ID;
	
	/**<h2>método Usuario</h2>
	 * Constructor que recibe 3 parámetros para inicializar/asignar valor a los 3 atributos 
	 * @param nombre de tipo String, contendrá el valor del nombre que se va a asignar al atributo <i>nombre</i>
	 * @param tipo de tipo String, contendrá el valor del tipo de usuario que se va a asignar al atributo <i>tipo</i>
	 * @param iD de tipo int, contendrá el valor de número de identificaicón se va a asignar al atributo <i>ID</i>
	 */
	public Usuario(String nombre, String tipo, int iD) {
		this.nombre = nombre;
		this.tipo = tipo;
		ID = iD;
	}
	
	
	
	

}
