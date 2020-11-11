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
	private String contraseña;
	private float peso;
	private int altura;
	private String Alergias;
	private int colesterol;
	private int tension;
	private String enfermedades;
	private String tipoSangre;
	private Medico medicoCabecera;


	
	ArrayList<Cita> citas = new ArrayList<Cita>();
	ArrayList<Tratamiento> tratamientos = new ArrayList<Tratamiento>();
	ArrayList<Prueba> pruebas = new ArrayList<Prueba>();
	
	/**
	 * Clase principal donde vamos a partir todos los usuarios
	 * @param nombre
	 * @param apellido
	 * @param dni
	 * @param sexo
	 * @param contraseña
	 * @param peso
	 * @param altura
	 * @param alergias
	 * @param colesterol
	 * @param tension
	 * @param enfermedades
	 * @param tipoSangre
	 */
	public Usuario(String nombre, String apellido, String dni, char sexo, String contraseña, float peso, int altura,
			String alergias, int colesterol, int tension, String enfermedades, String tipoSangre) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.sexo = sexo;
		this.contraseña = contraseña;
		this.peso = peso;
		this.altura = altura;
		Alergias = alergias;
		this.colesterol = colesterol;
		this.tension = tension;
		this.enfermedades = enfermedades;
		this.tipoSangre = tipoSangre;
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


	public String getContraseña() {
		return contraseña;
	}


	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}


	public float getPeso() {
		return peso;
	}


	public void setPeso(float peso) {
		this.peso = peso;
	}


	public float getAltura() {
		return altura;
	}


	public void setAltura(int altura) {
		this.altura = altura;
	}


	public String getAlergias() {
		return Alergias;
	}


	public void setAlergias(String alergias) {
		Alergias = alergias;
	}


	public float getColesterol() {
		return colesterol;
	}


	public void setColesterol(int colesterol) {
		this.colesterol = colesterol;
	}


	public float getTension() {
		return tension;
	}


	public void setTension(int tension) {
		this.tension = tension;
	}


	public String getEnfermedades() {
		return enfermedades;
	}


	public void setEnfermedades(String enfermedades) {
		this.enfermedades = enfermedades;
	}


	public String getTipoSangre() {
		return tipoSangre;
	}


	public void setTipoSangre(String tipoSangre) {
		this.tipoSangre = tipoSangre;
	}


	public Medico getMedicoCabecera() {
		return medicoCabecera;
	}


	public void setMedicoCabecera(Medico medicoCabecera) {
		this.medicoCabecera = medicoCabecera;
	}


	public ArrayList<Cita> getCitas() {
		return citas;
	}


	public void setCitas(ArrayList<Cita> citas) {
		this.citas = citas;
	}


	public ArrayList<Tratamiento> getTratamientos() {
		return tratamientos;
	}


	public void setTratamientos(ArrayList<Tratamiento> tratamientos) {
		this.tratamientos = tratamientos;
	}


	public ArrayList<Prueba> getPruebas() {
		return pruebas;
	}


	public void setPruebas(ArrayList<Prueba> pruebas) {
		this.pruebas = pruebas;
	}
	

}
