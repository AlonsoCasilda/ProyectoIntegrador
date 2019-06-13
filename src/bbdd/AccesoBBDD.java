package bbdd;
/**
 * <h1>Clase AccesoBBDD</h1> donde creamos la conexión a la base de datos.
 * Contiene los atributos con los que crearemos la conexión a la bases de datos
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccesoBBDD {
	
	private String driver; 
	private String url;
	/** Atributos definidos como privados: 	 
	 *<ul>
	 *  <li><i>driver</i> de tipo String, representa el driver utilizado para la conexión</li>
	 *  <li><i>url</i> de tipo String, contiene la dirección de acceso para la conexión a la BBDD</li>	 *    	
	 *</ul> 
	 *
	 *
	 * Tambien se describe el constructor AccesoBBDD */
	
	/**<h2>método AccdesoBBDD</h2>
	 * Constructor que recibe 2 parámetros para inicializar/asignar valor a los 2 atributos 
	 * @param driver de tipo String, contendrá el valor de número de identificación se va a asignar al atributo <i>driver</i>
	 * @param url de tipo String, contendrá el valor de la dirección del atributo<i>url</i>	 
	 */	
	public AccesoBBDD() {
		this.driver = "org.sqlite.JDBC";
		this.url = "jdbc:sqlite:dbSQLITE/POJOMUSCULATION.db"; 
	}
	
	/**
	 * Método getConexion para establecer la conexión con la BBDD.*
	 * @return el atributo "con" con la conexión
	 * @throws ClassNotFoundException excepción que es propagada al método  que lo invoque
	 * @throws SQLException excepción que es propagada al método  que lo invoque
	 */
	public Connection getConexion() throws ClassNotFoundException, SQLException { 
		Class.forName(driver); 
		Connection con = DriverManager.getConnection(url); 
		return con; 
	}
	

}
