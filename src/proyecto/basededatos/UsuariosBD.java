package proyecto.basededatos;
import java.sql.*;
import java.util.ArrayList;

import javax.sound.midi.MidiDeviceReceiver;

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
	public static void main(String[] args) {
		UsuariosBD bd = new UsuariosBD();
	}
	ArrayList<Usuario> listaUsuariosBD = new ArrayList<Usuario>();
	
	ArrayList<Integer> medicosC = new ArrayList<Integer>();
	

	
	//TODO meter lista de citas pruebas y tratamientos en cada usuario buscar cual es el metodo mas corto
	
	


	public UsuariosBD() {
		try {
			
			//1. CREAMOS LA CONEXION
			
			Connection miConexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/osabide","root","");
			
			//2. CREAMOS EL OBJETO STATEMENT
			
			Statement usuariosBD = miConexion.createStatement();
			
			//3. EJECUTAMOS LA INSTRUCCION SQL
			
			ResultSet  mirResultSet= usuariosBD.executeQuery("SELECT * FROM USUARIOS");
			
			//4. RECORREMOS EL RESUTSET
			
			while (mirResultSet.next()){


				//System.out.println("vaa  ");


					if(mirResultSet.getString("tipo").equals("medico")) {//SI EL USUARIO ES DE TIPO MEDICO
						System.out.println("hay medico");
						
					Usuario m = new Medico(mirResultSet.getInt("cod_usuario"),mirResultSet.getString("nombre"), mirResultSet.getString("apellidos"),  mirResultSet.getString("dni"),
							mirResultSet.getString("sexo").charAt(0),mirResultSet.getString("contrasenya"), mirResultSet.getFloat("peso"),
							(int)mirResultSet.getInt("altura"), mirResultSet.getString("alergias"), mirResultSet.getInt("colesterol"),
							(int)mirResultSet.getInt("tension"), mirResultSet.getString("enfermedades"), mirResultSet.getString("tipo_sangre"));
						listaUsuariosBD.add(m);
						medicosC.add(mirResultSet.getInt("medico_cabecera"));
						System.out.println(m.toString());
					


					}else if(mirResultSet.getString("tipo").equals("enfermero")) {//SI EL USUARIO ES DE TIPO ENFERMERO
						System.out.println("hay enfermero");
						
						Usuario m = new Enfermero(mirResultSet.getInt("cod_usuario"), mirResultSet.getString("nombre"), mirResultSet.getString("apellidos"),  mirResultSet.getString("dni"),
							mirResultSet.getString("sexo").charAt(0),mirResultSet.getString("contrasenya"), mirResultSet.getFloat("peso"),
							(int)mirResultSet.getInt("altura"), mirResultSet.getString("alergias"), mirResultSet.getInt("colesterol"),
							(int)mirResultSet.getInt("tension"), mirResultSet.getString("enfermedades"), mirResultSet.getString("tipo_sangre"));
						listaUsuariosBD.add(m);
						medicosC.add(mirResultSet.getInt("medico_cabecera"));
						System.out.println(m.toString());
					
							
					}else if(mirResultSet.getString("tipo").equals("paciente")) {//SI EL USUARIO ES DE TIPO PACIENTE
						System.out.println("hay paciente");
						
						Usuario m = new Paciente(mirResultSet.getInt("cod_usuario"), mirResultSet.getString("nombre"), mirResultSet.getString("apellidos"),  mirResultSet.getString("dni"),
								mirResultSet.getString("sexo").charAt(0),mirResultSet.getString("contrasenya"), mirResultSet.getFloat("peso"),
								(int)mirResultSet.getInt("altura"), mirResultSet.getString("alergias"), mirResultSet.getInt("colesterol"),
								(int)mirResultSet.getInt("tension"), mirResultSet.getString("enfermedades"), mirResultSet.getString("tipo_sangre"));
							listaUsuariosBD.add(m);
							medicosC.add(mirResultSet.getInt("medico_cabecera"));
							System.out.println(m.toString());
					}else{
						System.out.println("ALGUN DATO ESTA MAL EN LA BASE DE DATOS");
					}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("NO CONECTAAA!!");
			
			e.printStackTrace();
			
		}
		//FUNCION QUE ASIGNA EL MÉDICO DE CABECERA A CADA USUARIO
	    for (int i=0;i<listaUsuariosBD.size();i++) {
	        
	    	listaUsuariosBD.get(i).setMedicoCabecera(asignaMedicoC(listaUsuariosBD, medicosC.get(i)));
			System.out.println("Añadiendo medico " +listaUsuariosBD.get(i).getMedicoCabecera().getNombre() +" al usuario " + listaUsuariosBD.get(i).getNombre());
	    	

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
	
	
}
