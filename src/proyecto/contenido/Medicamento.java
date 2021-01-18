package proyecto.contenido;

import java.sql.Time;
import java.util.Comparator;
import java.util.Date;

import proyecto.interfaces.ICSV;
/**
 * clase que contiene informacion acerca de los medicamentos
 * @author Suhar
 *
 */
public class Medicamento implements ICSV, Comparable<Medicamento>{
	
	private int codMedicamento;
	private String titulo;
	private String descripcion;
	private String ambito;
	private Date fechaLanzamiento;
	private int contador;
	

	
	
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


	public int getContador() {
		return contador;
	}


	public void setContador(int contador) {
		this.contador = contador;
	}


	@Override
	public String getCSV() {
		return codMedicamento + "," + titulo + "," + descripcion + "," + ambito + "," + fechaLanzamiento;
	}	

	@Override
	public String toString() {
		return "Medicamento [codMedicamento=" + codMedicamento + ", titulo=" + titulo + ", descripcion=" + descripcion
				+ ", ambito=" + ambito + ", fechaLanzamiento=" + fechaLanzamiento + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codMedicamento;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medicamento other = (Medicamento) obj;
		if (codMedicamento != other.codMedicamento)
			return false;
		other.contador++;
		return true;
	}


	@Override
	public int compareTo(Medicamento o) {
		int ret;
		ret = codMedicamento-o.codMedicamento;
				if(ret ==0) {
					o.contador++;
				}
			return ret;
	}	

}
