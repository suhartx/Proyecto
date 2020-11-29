import java.util.ArrayList;

import proyecto.basededatos.DatosUsuariosBD;
import proyecto.usuarios.Usuario;
import proyecto.ventanas.VentanaLogin;

public class Main {
	
	public static void main(String[] args) {
		
		ArrayList<Usuario> usuarios = DatosUsuariosBD.iniciaDatos();
        VentanaLogin frame = new VentanaLogin(usuarios);
        frame.setVisible(true);
	

	}
}
