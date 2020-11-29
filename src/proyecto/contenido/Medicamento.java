package proyecto.contenido;

import java.sql.Time;
import java.util.Date;

public class Medicamento {
	
	private int codMedicamento;
	private String titulo;
	private String descripcion;
	private String ambito;
	private Date fechaLanzamiento;

	
	
	public Medicamento(int codMedicamento, String titulo, String descripcion, String ambito, Date fechaLanzamiento) {
		super();
		this.codMedicamento = codMedicamento;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.ambito = ambito;
		this.fechaLanzamiento = fechaLanzamiento;

	}


	public int getCodMedicamento() {
		return codMedicamento;
	}


	public void setCodMedicamento(int codMedicamento) {
		this.codMedicamento = codMedicamento;
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


	public Date getFechaLanzamiento() {
		return fechaLanzamiento;
	}


	public void setFechaLanzamiento(Date fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
	}


	@Override
	public String toString() {
		return "Medicamento [codMedicamento=" + codMedicamento + ", titulo=" + titulo + ", descripcion=" + descripcion
				+ ", ambito=" + ambito + ", fechaLanzamiento=" + fechaLanzamiento + "]";
	}	

}
