package proyecto.usuarios;

import proyecto.interfaces.ICita;
import sun.security.util.Password;

public class Medico extends Usuario implements ICita {

	public Medico(String nombre, String apellido, String dni, char sexo, String contraseņa, float peso, int altura,
			String alergias, int colesterol, int tension, String enfermedades, String tipoSangre) {
		super(nombre, apellido, dni, sexo, contraseņa, peso, altura, alergias, colesterol, tension, enfermedades, tipoSangre);
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
