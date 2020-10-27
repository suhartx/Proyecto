package proyecto.usuarios;

public class Usuario {

	private String nombre;
	private String comunismo;
	private String dni;
	private char sexo;
	
	public Usuario() {
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getComunismo() {
		return comunismo;
	}

	public void setComunismo(String comunismo) {
		this.comunismo = comunismo;
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
	
}
