package bbdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MusculosPersistencia {
	
	private AccesoBBDD acceso;
	
	
	public MusculosPersistencia() {
		acceso = new AccesoBBDD();
	}

	public ArrayList<String> consultarMusculos(){
		ArrayList<String> listaMusculos = new ArrayList<String>();
		
		String query = "SELECT " + Contracts.MusculosContracts.COL_NOMBRE + " FROM " + Contracts.MusculosContracts.NOM_TABLA;
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rslst = null;
		
		try {
			con = acceso.getConexion();
			stmt = con.createStatement();
			rslst = stmt.executeQuery(query);			
			while (rslst.next()) {
				listaMusculos.add(rslst.getString(Contracts.MusculosContracts.COL_NOMBRE));
			}			
		} catch (ClassNotFoundException | SQLException e) {			
			e.printStackTrace();
		} finally {
			try {
				if (rslst != null)
					rslst.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}
		
		return listaMusculos;
	}
}
