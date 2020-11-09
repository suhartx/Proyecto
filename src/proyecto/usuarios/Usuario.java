package proyecto.usuarios;

import java.util.ArrayList;

import proyecto.contenido.Cita;
import proyecto.contenido.Prueba;
import proyecto.contenido.Tratamiento;
import sun.security.util.Password;

public abstract class Usuario {

	private String nombre;
	private String apellido;
	private String dni;
	private char sexo;
	private Password contraseña;
	private float peso;
	private float altura;
	private String Alergias;
	private float colesterol;
	private float tension;
	private String enfermedades;
	private String tipoSangre;
	private Medico medicoCabecera;


	
	ArrayList<Cita> citas = new ArrayList<Cita>();
	ArrayList<Tratamiento> tratamientos = new ArrayList<Tratamiento>();
	ArrayList<Prueba> pruebas = new ArrayList<Prueba>();
	
	public Usuario() {
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public Password getContraseña() {
		return contraseña;
	}

	public void setContraseña(Password contraseña) {
		this.contraseña = contraseña;
	}
	
}
