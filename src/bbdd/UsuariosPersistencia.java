package bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Usuario;

public class UsuariosPersistencia {
	private AccesoBBDD acceso;
	
	
	
	public UsuariosPersistencia() {
		acceso = new AccesoBBDD();
	}

	public boolean comprobarUsuario(Usuario user) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		boolean userOK = false;
		
		try {
			con = acceso.getConexion();
			
			String query = "SELECT " + Contracts.UsuariosContracts.COL_NOMBRE + ", " + Contracts.UsuariosContracts.COL_TIPO + " FROM " + 
			Contracts.UsuariosContracts.NOM_TABLA + " WHERE " + Contracts.UsuariosContracts.COL_ID + " = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, user.getIdU());
			
			
			rslt = pstmt.executeQuery();
			
			if (rslt.next()) {
				String numbreU = rslt.getString(Contracts.UsuariosContracts.COL_NOMBRE);
				String tipo = rslt.getString(Contracts.UsuariosContracts.COL_TIPO);
				
				if (numbreU.equalsIgnoreCase(user.getNombre()) & tipo.equalsIgnoreCase(user.getTipo())) {
					userOK = true;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rslt != null) {
					rslt.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return userOK;
	}

}
