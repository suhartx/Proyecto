package proyecto.usuarios;

import interfaces.ICita;
import sun.security.util.Password;

public class Enfermero extends Usuario implements ICita {

	public Enfermero(String nombre, String apellido, String dni, char sexo, String contraseña, float peso,
			int altura, String alergias, int colesterol, int tension, String enfermedades, String tipoSangre) {
		super(nombre, apellido, dni, sexo, contraseña, peso, altura, alergias, colesterol, tension, enfermedades, tipoSangre);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void crearCita() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mofificarCita() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarCita() {
		// TODO Auto-generated method stub
		
	}




}
