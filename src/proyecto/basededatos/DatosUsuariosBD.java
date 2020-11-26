package proyecto.basededatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jdk.internal.dynalink.beans.StaticClass;
import proyecto.contenido.Cita;
import proyecto.contenido.Prueba;
import proyecto.usuarios.Enfermero;
import proyecto.usuarios.Medico;
import proyecto.usuarios.Paciente;
import proyecto.usuarios.Usuario;

/**
 * metodo que añade las listas de citas pruebas y tratamientos
 * @author Suhar
 *
 */
public class DatosUsuariosBD {
	
	public static void main(String[] args) {
		
		DatosUsuariosBD bd = new DatosUsuariosBD();

	}
	static ArrayList<Usuario> usuarios = UsuariosBD.getUsuarios();	
	
	public DatosUsuariosBD() {
		
		añadirCitas();
		añadirPruebas();

	}
	
	public static void añadirCitas(){
		

		try {
		
			//1. CREAMOS LA CONEXION
			
			Connection miConexionCita=DriverManager.getConnection("jdbc:mysql://localhost:3306/osabide","root","");
			
			//2. CREAMOS EL OBJETO STATEMENT
			
			Statement datosBD = miConexionCita.createStatement();
			
			//3. EJECUTAMOS LA INSTRUCCION SQL PARA RECOJER LOS DATOS DE LA TABLA CITA
			
			ResultSet  mirResultSetDatos = datosBD.executeQuery("SELECT * FROM CITA");
			
			//ResultSet  mirResultSetTratamientos= usuariosBD.executeQuery("SELECT * FROM TRATAMIENTOS");
			
			//ResultSet  mirResultSetPruebas= usuariosBD.executeQuery("SELECT * FROM PRUEBA");

			//4. RECORREMOS LOS RESUTSET
		
			while (mirResultSetDatos.next()){

				Cita C = new Cita(mirResultSetDatos.getString("titulo"), mirResultSetDatos.getString("descripción"),
						mirResultSetDatos.getString("ámbito") ,  mirResultSetDatos.getDate("fecha"),mirResultSetDatos.getTime("hora"));
				
				usuarios.get(mirResultSetDatos.getInt("paciente_asociado")-1).getCitas().add(C);
				C.setSanitarioAsociado(usuarios.get(mirResultSetDatos.getInt("sanitario_asociado")-1));

				System.out.println(usuarios.get(mirResultSetDatos.getInt("paciente_asociado")-1).getNombre());
				System.out.println(C.toString());				
				System.out.println("Sanitario asociado: "+C.getSanitarioAsociado().getNombre());
				
			}

			//5. CERRAMOS LA CONEXION
			
			miConexionCita.close();

		} catch (SQLException e) {

		    System.out.println("Error en las operaciones a base de datos.");
		    
		    e.printStackTrace(System.out);
			
		}
	}
			
	public static void añadirPruebas(){
		
		try {

			
			// REALIZAMOS LA MISMA OPERACION PARA LAS PRUEBAS
			
			//1. CREAMOS LA CONEXION
			
			Connection miConexionPrueba=DriverManager.getConnection("jdbc:mysql://localhost:3306/osabide","root","");
			
			//2. REDEFINIMOS EL OBJETO STATEMENT
			
			Statement datosBD = miConexionPrueba.createStatement();
			
			//3. EJECUTAMOS LA INSTRUCCION SQL
			
			ResultSet mirResultSetDatos= datosBD.executeQuery("SELECT * FROM PRUEBA");

			//4. RECORREMOS LOS RESUTSET
		
			while (mirResultSetDatos.next()){

				Prueba p = new Prueba(mirResultSetDatos.getString("título"), mirResultSetDatos.getString("descripción"),
						mirResultSetDatos.getString("ambito") ,  mirResultSetDatos.getDate("fecha"),mirResultSetDatos.getTime("hora"));
				
				usuarios.get(mirResultSetDatos.getInt("paciente_asociado")-1).getPruebas().add(p);
				p.setSanitarioAsociado(usuarios.get(mirResultSetDatos.getInt("sanitario_asociado")-1));

				System.out.println(usuarios.get(mirResultSetDatos.getInt("paciente_asociado")-1).getNombre());
				System.out.println(p.toString());				
				System.out.println("Sanitario asociado: "+p.getSanitarioAsociado().getNombre());
				
			}

			//5. CERRAMOS LA CONEXION
			miConexionPrueba.close();

		} catch (SQLException e) {

		    System.out.println("Error en las operaciones a base de datos.");
		    
		    e.printStackTrace(System.out);
			
		}
	}
		
		
	public static void añadirTratamientos(){

//		try {
//
//
//		
//			//Y TAMBIEN CON LOS TRATAMIENTOS
//		
//			//1. CREAMOS LA CONEXION
//			
//			Connection miConexionCita=DriverManager.getConnection("jdbc:mysql://localhost:3306/osabide","root","");
//			
//			//2. CREAMOS EL OBJETO STATEMENT
//			
//			Statement usuariosBD = miConexionCita.createStatement();
//			
//			//3. EJECUTAMOS LA INSTRUCCION SQL
//			
//			ResultSet  mirResultSetCitas= usuariosBD.executeQuery("SELECT * FROM CITA");
//			
//			//ResultSet  mirResultSetTratamientos= usuariosBD.executeQuery("SELECT * FROM TRATAMIENTOS");
//			
//			//ResultSet  mirResultSetPruebas= usuariosBD.executeQuery("SELECT * FROM PRUEBA");
//
//			//4. RECORREMOS LOS RESUTSET
//		
//			while (mirResultSetCitas.next()){
//
//				Cita C = new Cita(mirResultSetCitas.getString("titulo"), mirResultSetCitas.getString("descripción"),
//						mirResultSetCitas.getString("ámbito") ,  mirResultSetCitas.getDate("fecha"),mirResultSetCitas.getTime("hora"));
//				
//				usuarios.get(mirResultSetCitas.getInt("paciente_asociado")-1).getCitas().add(C);
//				C.setSanitarioAsociado(usuarios.get(mirResultSetCitas.getInt("sanitario_asociado")-1));
//				
//				
//				System.out.println(usuarios.get(mirResultSetCitas.getInt("paciente_asociado")-1).getNombre());
//				System.out.println(C.toString());				
//				System.out.println("Sanitario asociado: "+C.getSanitarioAsociado().getNombre());
//				
//			}
//
//			//5. CERRAMOS LA CONEXION
//			miConexionCita.close();
//			
//		
//
//	}catch (SQLException e) {
//
//	    System.out.println("Error en las operaciones a base de datos.");
//	    
//	    e.printStackTrace(System.out);
//		
//	}


	//TODO meter lista de citas pruebas y tratamientos en cada usuario buscar cual es el metodo mas corto
	
		
	}		
}
