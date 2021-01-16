package proyecto.basededatos;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import jdk.internal.dynalink.beans.StaticClass;
import proyecto.contenido.Cita;
import proyecto.contenido.Medicamento;
import proyecto.contenido.Prueba;
import proyecto.contenido.Tratamiento;
import proyecto.ficheros.Ficheros;
import proyecto.usuarios.Enfermero;
import proyecto.usuarios.Medico;
import proyecto.usuarios.Paciente;
import proyecto.usuarios.Usuario;

/**
 * metodo que añade las listas de citas pruebas y tratamientos
 * @author Suhar
 */
public class DatosUsuariosBD {

	static ArrayList<Usuario> usuarios = UsuariosBD.getUsuarios();	

	ArrayList<Medicamento> med = new ArrayList<>();	
	
	HashMap<Integer, Usuario> usuariosMapID =  new HashMap<>();
	HashMap<String, Usuario> usuariosMapNombre =  new HashMap<>();
	


	private static boolean LOGGING = true;  // Log a consola de lo que se va leyendo en el CSV

	
	
	
	public DatosUsuariosBD() {
		//CON ESTOS MÉTODOS INTRODUCIMOS LOS DATOS EN LAS LISTAS


		if (usuarios.isEmpty()) {
			
			
			try {
				System.out.println("añadiendo usuarios...");
				Ficheros.processCSV(new File("Usuarios.csv"));
				System.out.println("añadiendo procedimientos...");
				Ficheros.processCSV(new File("Procedimientos.csv"));
				System.out.println("añadiendo medicamentos...");
				Ficheros.processCSV(new File("Medicamentos.csv"));				
				usuarios=Ficheros.devuelvelistaUsuarios();
				System.out.println("Usuarios añadidos con exito");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			
			}else {
		
		añadirCitas();
		añadirPruebas();
		añadirTratamientos();
		añadirMedicamentos();
		añadirMedicamentosAsociados();
		//cargaUsuariosMapa();
		
			
		
		Ficheros.Escribeficheros(usuarios);
		}
		

	}
	
	public void añadirCitas(){
		
		Connection miConexionCita = null;

		try {
		
			//1. CREAMOS LA CONEXION
			
			miConexionCita=DriverManager.getConnection("jdbc:mysql://localhost:3306/osabide","root","");
			
		} catch (SQLException e) {
			log( Level.SEVERE, "Error en conexión de base de datos " + "osabide", e );

		}
	
			//2. CREAMOS EL OBJETO STATEMENT
		try {	
	
			Statement datosBD = miConexionCita.createStatement();
			
			//3. EJECUTAMOS LA INSTRUCCION SQL PARA RECOJER LOS DATOS DE LA TABLA CITA
			try {
				ResultSet  mirResultSetDatos = datosBD.executeQuery("SELECT * FROM CITA");
				log( Level.INFO, "BD tipo buscado\t " + "SELECT * FROM CITA", null );

			//4. RECORREMOS LOS RESUTSET
		
				while (mirResultSetDatos.next()){
	
					Cita C = new Cita(mirResultSetDatos.getInt("cod_cita") ,mirResultSetDatos.getString("titulo"), mirResultSetDatos.getString("descripción"),
							mirResultSetDatos.getString("ámbito") ,  mirResultSetDatos.getDate("fecha"),mirResultSetDatos.getTime("hora"));
					
					usuarios.get(mirResultSetDatos.getInt("paciente_asociado")-1).getCitas().add(C);
					C.setSanitarioAsociado(usuarios.get(mirResultSetDatos.getInt("sanitario_asociado")-1));
	
					//System.out.println(usuarios.get(mirResultSetDatos.getInt("paciente_asociado")-1).getNombre());
					//System.out.println(C.toString());				
					//System.out.println("Sanitario asociado: "+C.getSanitarioAsociado().getNombre());
					
				}
				
			} catch (SQLException e) {
				log( Level.SEVERE, "Error en BD\t " + "SELECT * FROM CITA", e );
			}
			//5. CERRAMOS LA CONEXION

			try {
				if (datosBD!=null) datosBD.close();
				if (miConexionCita!=null) miConexionCita.close();
				log( Level.INFO, "Cierre de base de datos", null );
			} catch (SQLException e) {
				log( Level.SEVERE, "Error en cierre de base de datos", e );
			}			
		


		} catch (SQLException e) {
			
			log( Level.SEVERE, "Error en la declaracion de la base de datos", e );


		    System.out.println("Error en las operaciones a base de datos.");
		    
		    e.printStackTrace(System.out);
			
		}
	}
			
	public void añadirPruebas(){
		
		Connection miConexionPrueba = null;

		try {
		
			//1. CREAMOS LA CONEXION
			
			miConexionPrueba=DriverManager.getConnection("jdbc:mysql://localhost:3306/osabide","root","");
			
		} catch (SQLException e) {
			log( Level.SEVERE, "Error en conexión de base de datos " + "osabide", e );

		}
			//2. REDEFINIMOS EL OBJETO STATEMENT
		try {	
			
			Statement datosBD = miConexionPrueba.createStatement();
			log( Level.INFO, "Declaracion en la base de datos", null );

			//3. EJECUTAMOS LA INSTRUCCION SQL
			try {
				ResultSet mirResultSetDatos= datosBD.executeQuery("SELECT * FROM PRUEBA");
				log( Level.INFO, "BD tipo buscado\t " + "SELECT * FROM PRUEBA", null );

			//4. RECORREMOS LOS RESUTSET
		
				while (mirResultSetDatos.next()){
	
					Prueba p = new Prueba(mirResultSetDatos.getInt("cod_prueba") ,mirResultSetDatos.getString("título"), mirResultSetDatos.getString("descripción"),
							mirResultSetDatos.getString("ambito") ,  mirResultSetDatos.getDate("fecha"),mirResultSetDatos.getTime("hora"));
					
					usuarios.get(mirResultSetDatos.getInt("paciente_asociado")-1).getPruebas().add(p);
					p.setSanitarioAsociado(usuarios.get(mirResultSetDatos.getInt("sanitario_asociado")-1));
	
	
					//System.out.println(usuarios.get(mirResultSetDatos.getInt("paciente_asociado")-1).getNombre());
					//System.out.println(p.toString());				
					//System.out.println("Sanitario asociado: "+p.getSanitarioAsociado().getNombre());
					
				}
			} catch (SQLException e) {
				log( Level.SEVERE, "Error en BD\t " + "SELECT * FROM PRUEBA", e );
			}

			//5. CERRAMOS LA CONEXION

			try {
				if (datosBD!=null) datosBD.close();
				if (miConexionPrueba!=null) miConexionPrueba.close();
				log( Level.INFO, "Cierre de base de datos", null );
			} catch (SQLException e) {
				log( Level.SEVERE, "Error en cierre de base de datos", e );
			}			
		


		} catch (SQLException e) {
			
			log( Level.SEVERE, "Error en la declaracion de la base de datos", e );

			System.out.println("Error en las operaciones a base de datos.");
		    
		    e.printStackTrace(System.out);
			
		}
	}
		
		
	public void añadirTratamientos(){

		

			//Y TAMBIEN CON LOS TRATAMIENTOS
			
			Connection miConexionTratamiento = null;
			
			//1. CREAMOS LA CONEXION
			
		try {
			miConexionTratamiento=DriverManager.getConnection("jdbc:mysql://localhost:3306/osabide","root","");
			log( Level.INFO, "Conectada base de datos " + "osabide", null );

		} catch (SQLException e) {
			log( Level.SEVERE, "Error en conexión de base de datos " + "osabide", e );

		}
		
			//2. CREAMOS EL OBJETO STATEMENT
		try {	
			Statement datosBD = miConexionTratamiento.createStatement();
			log( Level.INFO, "Declaracion en la base de datos", null );

			//3. EJECUTAMOS LA INSTRUCCION SQL
			try {
				ResultSet  mirResultSetTratamientos= datosBD.executeQuery("SELECT * FROM TRATAMIENTOS");
				log( Level.INFO, "BD tipo buscado\t " + "SELECT * FROM TRATAMIENTOS", null );


			//4. RECORREMOS LOS RESUTSET
		
				while (mirResultSetTratamientos.next()){
	
					Tratamiento t = new Tratamiento(mirResultSetTratamientos.getInt("cod_tratamiento"), mirResultSetTratamientos.getString("titulo"), mirResultSetTratamientos.getString("descripción"),
							mirResultSetTratamientos.getString("ambito") ,  mirResultSetTratamientos.getDate("fecha"),mirResultSetTratamientos.getTime("hora"));
					
					usuarios.get(mirResultSetTratamientos.getInt("paciente_asociado")-1).getTratamientos().add(t);
					t.setMedicoAsociado((Medico)usuarios.get(mirResultSetTratamientos.getInt("medico_asociado")-1));
					
					
	//				System.out.println(usuarios.get(mirResultSetTratamientos.getInt("paciente_asociado")-1).getNombre());
	//				System.out.println(t.toString());				
	//				System.out.println("Sanitario asociado: "+t.getMedicoAsociado().getNombre());
					
				}
			} catch (SQLException e) {
				log( Level.SEVERE, "Error en BD\t " + "SELECT * FROM USUARIOS", e );
			}

			//5. CERRAMOS LA CONEXION

			try {
				if (datosBD!=null) datosBD.close();
				if (miConexionTratamiento!=null) miConexionTratamiento.close();
				log( Level.INFO, "Cierre de base de datos", null );
			} catch (SQLException e) {
				log( Level.SEVERE, "Error en cierre de base de datos", e );
			}			
		

		}catch (SQLException e) {
			
			log( Level.SEVERE, "Error en la declaracion de la base de datos", e );
	
		    System.out.println("Error en las operaciones a base de datos.");
		    
		    e.printStackTrace(System.out);
			
		}
	}		
	
	public void añadirMedicamentos(){
		
		Connection miConexionMedicamento=null;
		
		try {

			//POR ULTÍMO AÑADIMOS LOS MEDICAMENTOS 
		
			//1. CREAMOS LA CONEXION
			
			miConexionMedicamento=DriverManager.getConnection("jdbc:mysql://localhost:3306/osabide","root","");
			log( Level.INFO, "Conectada base de datos " + "osabide", null );

		} catch (SQLException e) {
			log( Level.SEVERE, "Error en conexión de base de datos " + "osabide", e );

		}

			//2. CREAMOS EL OBJETO STATEMENT
		try {		
			Statement usuariosBD = miConexionMedicamento.createStatement();
			log( Level.INFO, "Declaracion en la base de datos", null );

			//3. EJECUTAMOS LA INSTRUCCION SQL
			try {
				ResultSet  mirResultSetMedicamentos= usuariosBD.executeQuery("SELECT * FROM MEDICAMENTO");
				log( Level.INFO, "BD tipo buscado\t " + "SELECT * FROM MEDICAMENTO", null );

			//4. RECORREMOS LOS RESUTSET
		
				while (mirResultSetMedicamentos.next()){
	
					Medicamento m = new Medicamento(mirResultSetMedicamentos.getInt("cod_medicamento"), mirResultSetMedicamentos.getString("titulo"),mirResultSetMedicamentos.getString("descripcion"),
							mirResultSetMedicamentos.getString("ambito") ,  mirResultSetMedicamentos.getDate("fecha_lanzamiento"));
					
					med.add(m);
					//System.out.println("medicamento añadido");
					
				//5. CERRAMOS LA CONEXION
					
				}
			} catch (SQLException e) {
				log( Level.SEVERE, "Error en BD\t " + "SELECT * FROM USUARIOS", e );
			}
			miConexionMedicamento.close();
			
		}catch (SQLException e) {
			
			log( Level.SEVERE, "Error en la declaracion de la base de datos", e );


		    System.out.println("Error en las operaciones a base de datos.");
		    
		    e.printStackTrace(System.out);
			
		}
	}
				

	public void añadirMedicamentosAsociados(){
		
		Connection miConexionMedicamentoAsociado =null;
			
		try {

			//POR ULTÍMO AÑADIMOS LOS MEDICAMENTOS ASOCIADOS
		
			//1. CREAMOS LA CONEXION
			
			miConexionMedicamentoAsociado=DriverManager.getConnection("jdbc:mysql://localhost:3306/osabide","root","");
			log( Level.INFO, "Conectada base de datos " + "osabide", null );
		} catch (SQLException e) {
			log( Level.SEVERE, "Error en conexión de base de datos " + "osabide", e );

		}
			//2. CREAMOS EL OBJETO STATEMENT
		try {	
			Statement usuariosBD = miConexionMedicamentoAsociado.createStatement();
			log( Level.INFO, "Declaracion en la base de datos", null );

			//3. EJECUTAMOS LA INSTRUCCION SQL
			try {
				ResultSet  mirResultSetMedicamentosAsociados= usuariosBD.executeQuery("SELECT * FROM medicamento_tratamiento");
				log( Level.INFO, "BD tipo buscado\t " + "SELECT * FROM medicamento_tratamiento", null );
	
			//4. RECORREMOS LOS RESUTSET
			
				while (mirResultSetMedicamentosAsociados.next()){
	
				    for (int i=0;i<usuarios.size();i++) {
	
				    	//System.out.println(usuarios.get(i).getNombre());
				        
				    	for(int t=0;t<usuarios.get(i).getTratamientos().size();t++){
	
				    		if(usuarios.get(i).getTratamientos().get(t).getCodtratamiento()==mirResultSetMedicamentosAsociados.getInt("cod_tratamiento")) { 
							    for (int c=0;c<mirResultSetMedicamentosAsociados.getInt("cantidad");c++) {
	
	    					    	Medicamento m = med.get(mirResultSetMedicamentosAsociados.getInt("cod_medicamento")-1);
	
							    	usuarios.get(i).getTratamientos().get(t).getMedicamentos().add(m);
	
							    }
				    		}
				    	//System.out.println("Paciente: "+usuarios.get(i).getNombre()+" Tratamiento: "+usuarios.get(i).getTratamientos().get(t).getTitulo()+" Medicamentos: "+ usuarios.get(i).getTratamientos().get(t).getMedicamentos().toString());			    		
				    	}
				    }					
				}
			} catch (SQLException e) {
				log( Level.SEVERE, "Error en BD\t " + "SELECT * FROM medicamento_tratamiento", e );
			}
				
			//5. CERRAMOS LA CONEXION
			miConexionMedicamentoAsociado.close();
			try {
				if (usuariosBD!=null) usuariosBD.close();
				if (miConexionMedicamentoAsociado!=null) miConexionMedicamentoAsociado.close();
				log( Level.INFO, "Cierre de base de datos", null );
			} catch (SQLException e) {
				log( Level.SEVERE, "Error en cierre de base de datos", e );
			}		

		}catch (SQLException e) {
			
			log( Level.SEVERE, "Error en la declaracion de la base de datos", e );
	
		    System.out.println("Error en las operaciones a base de datos.");
		    
		    e.printStackTrace(System.out);

		}
	}
	public static ArrayList<Usuario> devuelveUsuarios(){
		return usuarios;
	}
	/**
	 * decuelve la lista de usuarios de la base de datos con clave nombre + apellido y valor usuario
	 * @return
	 */
	public HashMap<String, Usuario> devuelveUsuariosMapNombre() {
		for (Usuario u : usuarios) {
			usuariosMapNombre.put(u.getNombre()+" "+u.getApellido(), u);
		}
		return usuariosMapNombre;
	}
	/**
	 * devuelve la lista de la base de datos en forma de hashmap con clave id y valor usuario
	 * @return
	 */
	public HashMap<Integer, Usuario> devuelveUsuariosMapID() {
		for (Usuario u : usuarios) {
			usuariosMapID.put(u.getCodUsuario(), u);
		}
		return usuariosMapID;
	}	
	
	
	/////////////////////////////////////////////////////////////////////
	//                      Logging                                    //
	/////////////////////////////////////////////////////////////////////
	
	private static Logger logger = null;
	
	// Método local para loggear
	private static void log( Level level, String msg, Throwable excepcion ) {
		if (!LOGGING) return;
		if (logger==null) {  // Logger por defecto local:
			logger = Logger.getLogger( DatosUsuariosBD.class.getName() );  // Nombre del logger - el de la clase
			logger.setLevel( Level.ALL );  // Loguea todos los niveles
			try {
				logger.addHandler(new FileHandler("LoggerBDDatos.log.xml",false));//ESTO SE TIENE QUE ARREGLAR PORQUE NO VA
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