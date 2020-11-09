package basededatos;
import java.sql.*;

/**
 * Clase para crar la conexión con la base de datos y crear un objeto statement
 * @author Suhar
 *
 */
public class ConexionBBDD {
	
	public static void main(String[] args) {
		try {
			
			//1. CREAMOS LA CONEXION
			
			Connection miConexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/osabide","root","");
			
			//2. CREAMOS EL OBJETO STATEMENT
			
			Statement miStatement = miConexion.createStatement();
			
			//3. EJECUTAMOS LA INSTRUCCION SQL
			
			ResultSet  mirResultSet= miStatement.executeQuery("SELECT * FROM USUARIOS");
			
			//4. RECORREMOS EL RESUTSET
			
			while (mirResultSet.next()){
					for (int i = 1; i < mirResultSet.getMetaData().getColumnCount(); i++) {
						
					
					System.out.print(mirResultSet.getString(i)+" ");
				}
					System.out.println();
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("NO CONECTAAA!!");
			
			e.printStackTrace();
			
		}	
	}

}
