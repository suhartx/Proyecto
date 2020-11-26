package proyecto.contenido;

import java.sql.Time;
import java.util.Date;

import proyecto.usuarios.Usuario;

public class Cita {

	private String titulo;
	private String descripcion;
	private String ambito;
	private Date fecha;
	private Time hora;
	private Usuario sanitarioAsociado;
	
	public Cita(String titulo, String descripcion, String ambito, Date fecha, Time hora) {
		super();
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.ambito = ambito;
		this.fecha = fecha;
		this.hora = hora;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getAmbito() {
		return ambito;
	}

	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public Usuario getSanitarioAsociado() {
		return sanitarioAsociado;
	}

	public void setSanitarioAsociado(Usuario sanitarioAsociado) {
		this.sanitarioAsociado = sanitarioAsociado;
	}

	@Override
	public String toString() {
		return "Cita [titulo=" + titulo + ", descripcion=" + descripcion + ", ambito=" + ambito + ", fecha=" + fecha
				+ ", hora=" + hora + ", sanitarioAsociado=" + sanitarioAsociado + "]";
	}






}
