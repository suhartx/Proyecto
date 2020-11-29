package proyecto.basededatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jdk.internal.dynalink.beans.StaticClass;
import proyecto.contenido.Cita;
import proyecto.contenido.Medicamento;
import proyecto.contenido.Prueba;
import proyecto.contenido.Tratamiento;
import proyecto.usuarios.Enfermero;
import proyecto.usuarios.Medico;
import proyecto.usuarios.Paciente;
import proyecto.usuarios.Usuario;

/**
 * metodo que añade las listas de citas pruebas y tratamientos
 * @author Suhar
 */
public class DatosUsuariosBD {

	ArrayList<Usuario> usuarios = UsuariosBD.getUsuarios();	
	ArrayList<Medicamento> med = new ArrayList<>();	
	
	
	public DatosUsuariosBD() {
		//CON ESTOS MÉTODOS INTRODUCIMOS LOS DATOS EN LAS LISTAS
		añadirCitas();
		añadirPruebas();
		añadirTratamientos();
		añadirMedicamentos();
		añadirMedicamentosAsociados();

	}
	
	public void añadirCitas(){

		try {
		
			//1. CREAMOS LA CONEXION
			
			Connection miConexionCita=DriverManager.getConnection("jdbc:mysql://localhost:3306/osabide","root","");
			
			//2. CREAMOS EL OBJETO STATEMENT
			
			Statement datosBD = miConexionCita.createStatement();
			
			//3. EJECUTAMOS LA INSTRUCCION SQL PARA RECOJER LOS DATOS DE LA TABLA CITA
			
			ResultSet  mirResultSetDatos = datosBD.executeQuery("SELECT * FROM CITA");

			//4. RECORREMOS LOS RESUTSET
		
			while (mirResultSetDatos.next()){

				Cita C = new Cita(mirResultSetDatos.getString("titulo"), mirResultSetDatos.getString("descripción"),
						mirResultSetDatos.getString("ámbito") ,  mirResultSetDatos.getDate("fecha"),mirResultSetDatos.getTime("hora"));
				
				usuarios.get(mirResultSetDatos.getInt("paciente_asociado")-1).getCitas().add(C);
				C.setSanitarioAsociado(usuarios.get(mirResultSetDatos.getInt("sanitario_asociado")-1));

				//System.out.println(usuarios.get(mirResultSetDatos.getInt("paciente_asociado")-1).getNombre());
				//System.out.println(C.toString());				
				//System.out.println("Sanitario asociado: "+C.getSanitarioAsociado().getNombre());
				
			}

			//5. CERRAMOS LA CONEXION
			
			miConexionCita.close();

		} catch (SQLException e) {

		    System.out.println("Error en las operaciones a base de datos.");
		    
		    e.printStackTrace(System.out);
			
		}
	}
			
	public void añadirPruebas(){
		
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


				//System.out.println(usuarios.get(mirResultSetDatos.getInt("paciente_asociado")-1).getNombre());
				//System.out.println(p.toString());				
				//System.out.println("Sanitario asociado: "+p.getSanitarioAsociado().getNombre());
				
			}

			//5. CERRAMOS LA CONEXION
			miConexionPrueba.close();

		} catch (SQLException e) {

		    System.out.println("Error en las operaciones a base de datos.");
		    
		    e.printStackTrace(System.out);
			
		}
	}
		
		
	public void añadirTratamientos(){

		try {

			//Y TAMBIEN CON LOS TRATAMIENTOS
		
			//1. CREAMOS LA CONEXION
			
			Connection miConexionTratamiento=DriverManager.getConnection("jdbc:mysql://localhost:3306/osabide","root","");
			
			//2. CREAMOS EL OBJETO STATEMENT
			
			Statement usuariosBD = miConexionTratamiento.createStatement();
			
			//3. EJECUTAMOS LA INSTRUCCION SQL
			
			ResultSet  mirResultSetTratamientos= usuariosBD.executeQuery("SELECT * FROM TRATAMIENTOS");

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

			//5. CERRAMOS LA CONEXION
			miConexionTratamiento.close();
			
		

		}catch (SQLException e) {
	
		    System.out.println("Error en las operaciones a base de datos.");
		    
		    e.printStackTrace(System.out);
			
		}
	}		
	
	public void añadirMedicamentos(){
		
		try {

			//POR ULTÍMO AÑADIMOS LOS MEDICAMENTOS 
		
			//1. CREAMOS LA CONEXION
			
			Connection miConexionMedicamento=DriverManager.getConnection("jdbc:mysql://localhost:3306/osabide","root","");
			
			//2. CREAMOS EL OBJETO STATEMENT
			
			Statement usuariosBD = miConexionMedicamento.createStatement();
			
			//3. EJECUTAMOS LA INSTRUCCION SQL
			
			ResultSet  mirResultSetMedicamentos= usuariosBD.executeQuery("SELECT * FROM MEDICAMENTO");


			//4. RECORREMOS LOS RESUTSET
		
			while (mirResultSetMedicamentos.next()){

				Medicamento m = new Medicamento(mirResultSetMedicamentos.getInt("cod_medicamento"), mirResultSetMedicamentos.getString("titulo"),mirResultSetMedicamentos.getString("descripcion"),
						mirResultSetMedicamentos.getString("ambito") ,  mirResultSetMedicamentos.getDate("fecha_lanzamiento"));
				
				med.add(m);
				//System.out.println("medicamento añadido");
				
			//5. CERRAMOS LA CONEXION
				
			}
			miConexionMedicamento.close();
			
		}catch (SQLException e) {

		    System.out.println("Error en las operaciones a base de datos.");
		    
		    e.printStackTrace(System.out);
			
		}
	}
				

	public void añadirMedicamentosAsociados(){
			
		try {

			//POR ULTÍMO AÑADIMOS LOS MEDICAMENTOS ASOCIADOS
		
			//1. CREAMOS LA CONEXION
			
			Connection miConexionMedicamentoAsociado=DriverManager.getConnection("jdbc:mysql://localhost:3306/osabide","root","");
			
			//2. CREAMOS EL OBJETO STATEMENT
			
			Statement usuariosBD = miConexionMedicamentoAsociado.createStatement();
			
			//3. EJECUTAMOS LA INSTRUCCION SQL

			ResultSet  mirResultSetMedicamentosAsociados= usuariosBD.executeQuery("SELECT * FROM medicamento_tratamiento");

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

				
			//5. CERRAMOS LA CONEXION
				miConexionMedicamentoAsociado.close();

		}catch (SQLException e) {
	
		    System.out.println("Error en las operaciones a base de datos.");
		    
		    e.printStackTrace(System.out);

		}
	}
	public static ArrayList<Usuario> iniciaDatos(){
		DatosUsuariosBD inicia = new DatosUsuariosBD();
		return inicia.usuarios;
	}
}