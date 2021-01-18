package proyecto.basededatos;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import javax.sound.midi.MidiDeviceReceiver;

import proyecto.ficheros.Ficheros;
import proyecto.usuarios.Enfermero;
import proyecto.usuarios.Medico;
import proyecto.usuarios.Paciente;
import proyecto.usuarios.Usuario;
import sun.security.util.Password;

/**
 * Clase para crar la conexion con la base de datos y crear un objeto statement
 * @author Suhar
 *
 */
public class UsuariosBD {

	ArrayList<Usuario> listaUsuariosBD = new ArrayList<Usuario>();
	
	ArrayList<Integer> medicosC = new ArrayList<Integer>();
	
	private static boolean LOGGING = true;  // Log a consola de lo que se va leyendo en el CSV



	/**
	 * genera una lista de información extrayendo informacion acerca de los usuarios
	 */
	public UsuariosBD() {
		
		Connection miConexion = null;
		
		try {
			
			//1. CREAMOS LA CONEXION
			
			miConexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/osabide","root","");
			log( Level.INFO, "Conectada base de datos " + "osabide", null );

		} catch (SQLException e) {
			
			log( Level.SEVERE, "Error en conexión de base de datos " + "osabide", null);
			System.out.println("conexión establecida con los ficheros de la cache");
			

		}
		if (miConexion!= null) {
			//2. CREAMOS EL OBJETO STATEMENT
			try {	
				Statement usuariosBD = miConexion.createStatement();
				log( Level.INFO, "Declaracion en la base de datos", null );
	
				
				//3. EJECUTAMOS LA INSTRUCCION SQL
				
				try {
				ResultSet  mirResultSet= usuariosBD.executeQuery("SELECT * FROM USUARIOS");
				log( Level.INFO, "BD tipo buscado\t " + "SELECT * FROM USUARIOS", null );
		
				//4. RECORREMOS EL RESUTSET
				
					while (mirResultSet.next()){
		
						//System.out.println("vaa  ");
		
							if(mirResultSet.getString("tipo").equals("medico")) {//SI EL USUARIO ES DE TIPO MEDICO
								//System.out.println("hay medico");
								
							Usuario m = new Medico(mirResultSet.getInt("cod_usuario"),mirResultSet.getString("nombre"), mirResultSet.getString("apellidos"),  mirResultSet.getString("dni"),
									mirResultSet.getString("sexo").charAt(0),mirResultSet.getString("contrasenya"), mirResultSet.getFloat("peso"),
									(int)mirResultSet.getInt("altura"), mirResultSet.getString("alergias"), mirResultSet.getInt("colesterol"),
									(int)mirResultSet.getInt("tension"), mirResultSet.getString("enfermedades"), mirResultSet.getString("tipo_sangre"));
								listaUsuariosBD.add(m);
								medicosC.add(mirResultSet.getInt("medico_cabecera"));
								//System.out.println(m.toString());
		
							}else if(mirResultSet.getString("tipo").equals("enfermero")) {//SI EL USUARIO ES DE TIPO ENFERMERO
								//System.out.println("hay enfermero");
								
								Usuario m = new Enfermero(mirResultSet.getInt("cod_usuario"), mirResultSet.getString("nombre"), mirResultSet.getString("apellidos"),  mirResultSet.getString("dni"),
									mirResultSet.getString("sexo").charAt(0),mirResultSet.getString("contrasenya"), mirResultSet.getFloat("peso"),
									(int)mirResultSet.getInt("altura"), mirResultSet.getString("alergias"), mirResultSet.getInt("colesterol"),
									(int)mirResultSet.getInt("tension"), mirResultSet.getString("enfermedades"), mirResultSet.getString("tipo_sangre"));
								listaUsuariosBD.add(m);
								medicosC.add(mirResultSet.getInt("medico_cabecera"));
								//System.out.println(m.toString());
		
							}else if(mirResultSet.getString("tipo").equals("paciente")) {//SI EL USUARIO ES DE TIPO PACIENTE
								//System.out.println("hay paciente");
								
								Usuario m = new Paciente(mirResultSet.getInt("cod_usuario"), mirResultSet.getString("nombre"), mirResultSet.getString("apellidos"),  mirResultSet.getString("dni"),
										mirResultSet.getString("sexo").charAt(0),mirResultSet.getString("contrasenya"), mirResultSet.getFloat("peso"),
										(int)mirResultSet.getInt("altura"), mirResultSet.getString("alergias"), mirResultSet.getInt("colesterol"),
										(int)mirResultSet.getInt("tension"), mirResultSet.getString("enfermedades"), mirResultSet.getString("tipo_sangre"));
									listaUsuariosBD.add(m);
									medicosC.add(mirResultSet.getInt("medico_cabecera"));
									//System.out.println(m.toString());
							}else{
								System.out.println("ALGUN DATO ESTA MAL EN LA BASE DE DATOS");
							}
					}
				} catch (SQLException e) {
					log( Level.SEVERE, "Error en BD\t " + "SELECT * FROM USUARIOS", e );
				}
	
				//5. CERRAMOS LA CONEXION
	
				try {
					if (usuariosBD!=null) usuariosBD.close();
					if (miConexion!=null) miConexion.close();
					log( Level.INFO, "Cierre de base de datos", null );
				} catch (SQLException e) {
					log( Level.SEVERE, "Error en cierre de base de datos", e );
				}			
	
			
			} catch (SQLException e) {
				
				log( Level.SEVERE, "Error en la declaracion de la base de datos", e );
	
	
			    System.out.println("Error en las operaciones a base de datos.");
			    
			    //e.printStackTrace(System.out);
			    
			    System.out.println("Accediendo a los ficheros guardados en la cache...");	
	
			}
			
			//FUNCION QUE ASIGNA EL MÉDICO DE CABECERA A CADA USUARIO
		    for (int i=0;i<listaUsuariosBD.size();i++) {
		        
		    	listaUsuariosBD.get(i).setMedicoCabecera(asignaMedicoC(listaUsuariosBD, medicosC.get(i)));
				//System.out.println("Añadiendo medico " +listaUsuariosBD.get(i).getMedicoCabecera().getNombre() +" al usuario " + listaUsuariosBD.get(i).getNombre());
	
		    }
	    }
	}
	/**
	 * El llamamiento a la función recursiva simplificada
	 * @param array
	 * @param valor
	 * @return Medico
	 */
	private static Medico asignaMedicoC( ArrayList<Usuario> array, int valor) {

		Medico m = asignaMedicoCRecursivo(array, valor, 0, array.size()-1);
		
	return m;
		
	}

	/**
	 * Metodo que utilizamos para hacer la busqueda del medico de cabecera de cada paciente a partir del codigo de medico
	 * @param array lista en la que hacemos la busqueda
	 * @param valor valor del codigo de medico que queremos buscar
	 * @param ini posición inicial del array
	 * @param fin posición final del array
	 * @return Medico
	 */
	private static Medico asignaMedicoCRecursivo( ArrayList<Usuario> array, int valor, int ini, int fin) {

		/*
		 * if (ini==fin) {
		 * 
		 * if (array.get(ini).getCodUsuario()==valor) {
		 * 
		 * return (Medico) array.get(ini); } else { return null; } } else { int mitad =
		 * (ini + fin) / 2;
		 * 
		 * if (array.get(mitad).getCodUsuario()>=valor) { return asignaMedicoCRecursivo(
		 * array, valor, ini, mitad); // Donde va el == va la mitad }else { // < return
		 * asignaMedicoCRecursivo( array, valor, mitad+1, fin); // Donde no, el de la
		 * mitad se desprecia (2 partes) } }
		 */
		
		//LOS DOS METODOS SIRVEN POR IGUAL PERO ESTE ES MAS EFICIENTE
		if (ini>fin) return null;
		int mitad = (ini + fin) / 2;

		if (array.get(mitad).getCodUsuario()>valor) {
			return asignaMedicoCRecursivo( array, valor, ini, mitad-1 );
		} else {

			if (array.get(mitad).getCodUsuario()==valor) {
				return (Medico) array.get(mitad);
			} else {
				return asignaMedicoCRecursivo( array, valor, mitad+1, fin );
			}
		}	
	}	
	/**
	 * metodo que devuelve la lista deusuarios creada
	 * @return
	 */
	public static ArrayList<Usuario> getUsuarios() {
		UsuariosBD usuarios = new UsuariosBD();
		return usuarios.listaUsuariosBD;
	}
	/////////////////////////////////////////////////////////////////////
	//                      Logging                                    //
	/////////////////////////////////////////////////////////////////////
	
	private static Logger logger = null;
	
	// Método local para loggear
	private static void log( Level level, String msg, Throwable excepcion ) {
		if (!LOGGING) return;
		if (logger==null) {  // Logger por defecto local:
			logger = Logger.getLogger( UsuariosBD.class.getName() );  // Nombre del logger - el de la clase
			logger.setLevel( Level.ALL );  // Loguea todos los niveles
			logger.setUseParentHandlers(false);	//Quitamos la consola por defecto
			try {
				Handler h = new StreamHandler( System.out, new SimpleFormatter() );	
				h.setLevel( Level.ALL );
				logger.addHandler( h );  // Saca todos los errores a out
				Handler h2 =  new FileHandler( "LoggerBDUsuarios.log.xml", false);//ESTO SE TIENE QUE ARREGLAR POQUE NO VA
				h2.setLevel(Level.INFO);
				logger.addHandler(h2);
			} catch (SecurityException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if (excepcion==null)
			logger.log( level, msg );
		else
			logger.log( level, msg, excepcion );
	}

}

	

