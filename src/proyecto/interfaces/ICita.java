package proyecto.interfaces;

import proyecto.usuarios.Usuario;

public interface ICita {

	public void crearCita(Usuario u);
	
	public void mofificarCita(Usuario u, int num);
	
	public void eliminarCita(Usuario u, int num);
}
