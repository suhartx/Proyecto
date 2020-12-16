package proyecto.usuarios;

import javax.swing.JTree;

import sun.security.util.Password;

public class Paciente extends Usuario {

	public Paciente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Paciente(int codusuario, String nombre, String apellido, String dni, char sexo, String contrasenya, float peso,
			int altura, String alergias, int colesterol, int tension, String enfermedades, String tipoSangre) {
		super(codusuario, nombre, apellido, dni, sexo, contrasenya, peso, altura, alergias, colesterol, tension, enfermedades, tipoSangre);
		// TODO Auto-generated constructor stub
	}

	@Override
	public JTree cargarJTree() {
		// TODO Auto-generated method stub
		return null;
	}

	



}
