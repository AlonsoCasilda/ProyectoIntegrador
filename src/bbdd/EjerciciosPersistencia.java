package bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Ejercicio;

public class EjerciciosPersistencia {
	
private AccesoBBDD acceso;	
	
	public EjerciciosPersistencia() {
		acceso = new AccesoBBDD();
	}
	
	public ArrayList<Ejercicio> consultarEjercicio(String musculo){
		 ArrayList<Ejercicio>listaejercicios = new ArrayList<Ejercicio>();
		 
		 String query = "SELECT " + Contracts.EjerciciosContracts.COL_NOMBRE + ", " + Contracts.EjerciciosContracts.COL_DESC + ", " + 
		 Contracts.EjerciciosContracts.COL_REP + " FROM " + Contracts.EjerciciosContracts.NOM_TABLA + " WHERE " + Contracts.EjerciciosContracts.COL_MUSCULOS + 
		 " LIKE '%" + musculo + "%'";
		 
		 Connection con = null;
		 Statement stmt = null;
		 ResultSet rslst = null;
		 
		 try {
			con = acceso.getConexion();
			stmt = con.createStatement();
			rslst = stmt.executeQuery(query);
			
			String nombreEj;
			String desc;
			int repet;
			
			while (rslst.next()) {
				nombreEj = rslst.getString(Contracts.EjerciciosContracts.COL_NOMBRE);
				desc = rslst.getString(Contracts.EjerciciosContracts.COL_DESC);
				repet = rslst.getInt(Contracts.EjerciciosContracts.COL_REP);
				
				listaejercicios.add(new Ejercicio(nombreEj, desc, repet));				
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("No se ha establecido la coneción");
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
		 
		 return listaejercicios;
	}

	public int registrarEjercicio(Ejercicio ejercicioNuevo) {
		int resul = 0;
		String query = "INSERT INTO " + Contracts.EjerciciosContracts.NOM_TABLA + " (" + Contracts.EjerciciosContracts.COL_NOMBRE + ", " + Contracts.EjerciciosContracts.COL_DESC
				+ ", " + Contracts.EjerciciosContracts.COL_REP + ", " + Contracts.EjerciciosContracts.COL_MUSCULOS + ") VALUES ( ?, ?, ?, ?)";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = acceso.getConexion();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, ejercicioNuevo.getNombreEj());
			pstmt.setString(2, ejercicioNuevo.getDescripcion());
			pstmt.setInt(3, ejercicioNuevo.getRepeticiones());
			pstmt.setString(4, ejercicioNuevo.getMusculos());
			
			resul = pstmt.executeUpdate();
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			try {
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
		return resul;
	}

	public ArrayList<Ejercicio> consultarEjerciciosCompleto() {
		ArrayList<Ejercicio>listaDejercicios = new ArrayList<Ejercicio>();
		 
		 String query = "SELECT " + Contracts.EjerciciosContracts.COL_NOMBRE + ", " + Contracts.EjerciciosContracts.COL_DESC + ", " + 
		 Contracts.EjerciciosContracts.COL_REP + ", " + Contracts.EjerciciosContracts.COL_MUSCULOS + " FROM " + Contracts.EjerciciosContracts.NOM_TABLA;
		 
		 Connection con = null;
		 Statement stmt = null;
		 ResultSet rslst = null;
		 
		 try {
			con = acceso.getConexion();
			stmt = con.createStatement();
			rslst = stmt.executeQuery(query);
			
			String nombreEj;
			String desc;
			int repet;
			String musculos;
			
			while (rslst.next()) {
				nombreEj = rslst.getString(Contracts.EjerciciosContracts.COL_NOMBRE);
				desc = rslst.getString(Contracts.EjerciciosContracts.COL_DESC);
				repet = rslst.getInt(Contracts.EjerciciosContracts.COL_REP);
				musculos = rslst.getString(Contracts.EjerciciosContracts.COL_MUSCULOS);
				
				listaDejercicios.add(new Ejercicio(nombreEj, desc, repet, musculos));				
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("No se ha establecido la coneción");
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
		 
		 return listaDejercicios;
	}
	
	public int modificarEjercicio (Ejercicio ejercicio) {		
		
		String query= "UPDATE " + Contracts.EjerciciosContracts.NOM_TABLA + " SET " + Contracts.EjerciciosContracts.COL_DESC + " = ?, " + Contracts.EjerciciosContracts.COL_REP 
				+ " = ?, " + Contracts.EjerciciosContracts.COL_MUSCULOS + " = ? " + " WHERE " + Contracts.EjerciciosContracts.COL_NOMBRE + " = ?";	
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int res = 0;
		try {
			con = acceso.getConexion();
			pstmt = con.prepareStatement(query);			
			pstmt.setString(1, ejercicio.getDescripcion());
			pstmt.setInt(2, ejercicio.getRepeticiones());
			pstmt.setString(3, ejercicio.getMusculos());
			pstmt.setString(4, ejercicio.getNombreEj());
			
			res = pstmt.executeUpdate();
		} catch (Exception e) {
			System.err.println("No se ha podido establecer la conexión");
			e.printStackTrace();
		} finally {
			try {
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
		return res;
		
	}

	public int eliminarEjercicio(String nombreEj) {
		String query= "DELETE FROM " + Contracts.EjerciciosContracts.NOM_TABLA + " WHERE " + Contracts.EjerciciosContracts.COL_NOMBRE + " = ?";	
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int res = 0;
		try {
			con = acceso.getConexion();
			pstmt = con.prepareStatement(query);				
			pstmt.setString(1, nombreEj);
			res = pstmt.executeUpdate();
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			try {
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
		return res;
	}
}
