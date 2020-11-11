package proyecto.basededatos;
import java.sql.*;
import java.util.ArrayList;

import javax.sound.midi.MidiDeviceReceiver;

import proyecto.usuarios.Medico;
import proyecto.usuarios.Usuario;
import sun.security.util.Password;

/**
 * Clase para crar la conexión con la base de datos y crear un objeto statement
 * @author Suhar
 *
 */
public class UsuariosBD {
	public static void main(String[] args) {
		UsuariosBD bd = new UsuariosBD();
	}
	ArrayList<Usuario> listaUsuariosBD = new ArrayList<Usuario>();
	
	
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
//					for (int i = 1; i < mirResultSet.getMetaData().getColumnCount(); i++) {
//						
//					
//					System.out.print(mirResultSet.getString(i)+" ");
//				}
//					System.out.println();
				System.out.println("vaa  ");


					if(mirResultSet.getString("tipo").equals("medico")) {//SI EL USUARIO ES DE TIPO MEDICO
						System.out.println("hay medico");
						
					Medico m = new Medico(mirResultSet.getString("nombre"), mirResultSet.getString("apellidos"),  mirResultSet.getString("dni"),
							mirResultSet.getString("sexo").charAt(0),mirResultSet.getString("contraseña"), mirResultSet.getFloat("peso"),
							(int)mirResultSet.getInt("altura"), mirResultSet.getString("alergias"), mirResultSet.getInt("colesterol"),
							(int)mirResultSet.getInt("tension"), mirResultSet.getString("enfermedades"), mirResultSet.getString("tipo_sangre"));
						listaUsuariosBD.add(m);
						System.out.println(listaUsuariosBD.toString());
					


			}
				}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("NO CONECTAAA!!");
			
			e.printStackTrace();
			
		}	
	}

}
