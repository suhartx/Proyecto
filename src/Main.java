import java.util.ArrayList;

import javax.swing.SwingUtilities;

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

		SwingUtilities.invokeLater( () -> {

			inicia();

		} );

	}
	public static void inicia() {
		
		//INICIALIZAREMOS LOS USUARIOS DESDE LA BASE DE DATOS

		DatosUsuariosBD datos = new DatosUsuariosBD();
		


		//INICIALIZAMOS LA VENTANA
		
        VentanaLogin frame = new VentanaLogin(datos);
        frame.setVisible(true);

	}
}
