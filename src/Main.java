import java.util.ArrayList;

import proyecto.basededatos.DatosUsuariosBD;
import proyecto.contenido.Cita;
import proyecto.usuarios.Usuario;
import proyecto.ventanas.VentanaLogin;
/**
 * Clase principal donde inicializaremos todos los programas
 * @author Suhar
 *
 */
public class Main {
	
	public static void main(String[] args) {
		
		//TODO invoke later
		
		//INICIALIZAREMOS LOS USUARIOS DESDE LA BASE DE DATOS
		
		ArrayList<Usuario> usuarios = DatosUsuariosBD.iniciaDatos();
		
		for (Cita c: usuarios.get(0).getCitas()) {
			System.out.println(c.getCSV());
		}
		
		//INICIALIZAMOS LA VENTANA
		
        VentanaLogin frame = new VentanaLogin(usuarios);
        frame.setVisible(true);
        
//        for (Usuario u : usuarios) {
//			System.out.println(u.toString());
//		}
        
//        System.out.println(usuarios.toString());

	}
}
