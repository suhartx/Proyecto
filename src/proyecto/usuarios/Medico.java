package proyecto.usuarios;

import javax.swing.JTree;

import proyecto.interfaces.ICita;
import sun.security.util.Password;

public class Medico extends Usuario implements ICita {

	public Medico(int codusuario, String nombre, String apellido, String dni, char sexo, String contrasenya, float peso, int altura,
			String alergias, int colesterol, int tension, String enfermedades, String tipoSangre) {
		super(codusuario, nombre, apellido, dni, sexo, contrasenya, peso, altura, alergias, colesterol, tension, enfermedades, tipoSangre);
		// TODO Auto-generated constructor stub
	}

	public Medico() {
		super();
		// TODO Auto-generated constructor stub
	}



	@Override
	public void crearCita(Usuario u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mofificarCita(Usuario u, int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarCita(Usuario u, int num) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public JTree cargarJTree() {
		// TODO Auto-generated method stub
		return null;
	}
}
