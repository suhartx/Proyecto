package proyecto.contenido;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import proyecto.usuarios.Medico;

public class Tratamiento {

	private int codtratamiento;
	private String titulo;
	private String descripcion;
	private String ambito;
	private Date fecha;
	private Time hora;
	private Medico medicoAsociado;
	
	
	private ArrayList<Medicamento> medicamentos= new ArrayList<Medicamento>();
	

	
	public Tratamiento(int codtratamiento, String titulo, String descripcion, String ambito, Date fecha, Time hora) {

		this.codtratamiento = codtratamiento;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.ambito = ambito;
		this.fecha = fecha;
		this.hora = hora;

	}


	public int getCodtratamiento() {
		return codtratamiento;
	}


	public void setCodtratamiento(int codtratamiento) {
		this.codtratamiento = codtratamiento;
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


	public Medico getMedicoAsociado() {
		return medicoAsociado;
	}


	public void setMedicoAsociado(Medico medicoAsociado) {
		this.medicoAsociado = medicoAsociado;
	}


	public ArrayList<Medicamento> getMedicamentos() {
		return medicamentos;
	}


	public void setMedicamentos(ArrayList<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}


	@Override
	public String toString() {
		return "Tratamiento [codtratamiento=" + codtratamiento + ", titulo=" + titulo + ", descripcion=" + descripcion
				+ ", ambito=" + ambito + ", fecha=" + fecha + ", hora=" + hora + "]";
	}

}
