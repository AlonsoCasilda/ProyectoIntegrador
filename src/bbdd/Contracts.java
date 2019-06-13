package bbdd;

public class Contracts {
	
	public static class UsuariosContracts {
		
		public static final String NOM_TABLA = "USUARIOS";		
		public static final String COL_ID = "ID_U";
		public static final String COL_NOMBRE = "NOMBRE";
		public static final String COL_TIPO = "TIPO";	
		
	}
	
	public static class EjerciciosContracts {
		
		public static final String NOM_TABLA = "EJERCICIOS";		
		public static final String COL_ID = "ID_EJ";
		public static final String COL_NOMBRE = "NOMBRE";
		public static final String COL_DESC = "DESCRIPCION";
		public static final String COL_REP = "REPETICIONES";	
		public static final String COL_MUSCULOS = "MUSCULOS";
		
	}
	
	public static class MusculosContracts {
		
		public static final String NOM_TABLA = "MUSCULOS";		
		public static final String COL_ID = "ID_M";
		public static final String COL_NOMBRE = "NOMBRE_M";
		public static final String COL_REGION = "REGION";				
		
	}
	
	


}
