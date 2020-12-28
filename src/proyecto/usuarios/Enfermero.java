package proyecto.usuarios;

import javax.swing.JTree;

import proyecto.interfaces.ICita;
import sun.security.util.Password;

public class Enfermero extends Usuario implements ICita {

	public Enfermero() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Enfermero(int codusuario, String nombre, String apellido, String dni, char sexo, String contrasenya, float peso,
			int altura, String alergias, int colesterol, int tension, String enfermedades, String tipoSangre) {
		super(codusuario, nombre, apellido, dni, sexo, contrasenya, peso, altura, alergias, colesterol, tension, enfermedades, tipoSangre);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void crearCita(Usuario u) {
		// TODO Auto-generated method stub
		//PUEDO HACER QUE LOS ENFERMEROS ESTEN LIMITADOS A EN LA HORA QUE PUEDAN PONER LAS CITAS O EL AMBITO O ALGO ASI
		
	}

	@Override
	public void mofificarCita(Usuario u, int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarCita(Usuario u, int num) {
		// TODO Auto-generated method stub
		
	}







}
