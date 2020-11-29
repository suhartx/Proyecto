import java.util.ArrayList;

import proyecto.basededatos.DatosUsuariosBD;
import proyecto.usuarios.Usuario;
import proyecto.ventanas.VentanaLogin;
/**
 * Clase principal donde inicializaremos todos los programas
 * @author Suhar
 *
 */
public class Main {
	
	public static void main(String[] args) {
		
		//INICIALIZAREMOS LOS USUARIOS DESDE LA BASE DE DATOS
		
		ArrayList<Usuario> usuarios = DatosUsuariosBD.iniciaDatos();
		
		//INICIALIZAMOS LA VENTANA
		
        VentanaLogin frame = new VentanaLogin(usuarios);
        frame.setVisible(true);

	}
}
