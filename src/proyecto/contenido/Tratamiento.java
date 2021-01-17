package proyecto.contenido;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;

import proyecto.interfaces.ICSV;
import proyecto.usuarios.Medico;

public class Tratamiento implements ICSV, Comparable<Tratamiento>{

	private int codTratamiento;
	private String titulo;
	private String descripcion;
	private String ambito;
	private Date fecha;
	private Time hora;
	private Medico medicoAsociado;
	
	private TreeSet<Medicamento> medicamentosSet;
	private ArrayList<Medicamento> medicamentos= new ArrayList<Medicamento>();
	
	
	
	public Tratamiento() {
		super();
	}


	public Tratamiento(int codTratamiento, String titulo, String descripcion, String ambito, Date fecha, Time hora) {

		this.codTratamiento = codTratamiento;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.ambito = ambito;
		this.fecha = fecha;
		this.hora = hora;

	}


	public Tratamiento(int codTratamiento, String titulo, String descripcion, String ambito, Date fecha, Time hora,
			Medico medicoAsociado) {
		super();
		this.codTratamiento = codTratamiento;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.ambito = ambito;
		this.fecha = fecha;
		this.hora = hora;
		this.medicoAsociado = medicoAsociado;
	}


	public int getCodtratamiento() {
		return codTratamiento;
	}


	public void setCodtratamiento(int codtratamiento) {
		this.codTratamiento = codtratamiento;
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

	public TreeSet<Medicamento> getMedicamentosSet(){
		if (medicamentosSet==null) {
			
		
		medicamentosSet= new TreeSet<>();
		Iterator<Medicamento> iterador = getMedicamentos().iterator();
		while (iterador.hasNext()){
			medicamentosSet.add(iterador.next());
			
		}
		}
		return medicamentosSet;
	}

	public void setMedicamentos(ArrayList<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}

	@Override
	public String getCSV() {
		return codTratamiento + ","+this.getClass().getSimpleName()+"," + titulo + "," + descripcion + "," + ambito + "," + fecha + "," + hora + ","
				+ medicoAsociado.getCodUsuario();
	}

	@Override
	public String toString() {
		return "Tratamiento [codtratamiento=" + codTratamiento + ", titulo=" + titulo + ", descripcion=" + descripcion
				+ ", ambito=" + ambito + ", fecha=" + fecha + ", hora=" + hora + "]";
	}
	@Override
	public int compareTo(Tratamiento c) {
		int ret;
		ret = c.fecha.compareTo(getFecha());
				if(ret ==0) 
			ret= c.hora.compareTo(hora);
				if(ret ==0) 
			ret= c.titulo.compareToIgnoreCase(titulo);
			return ret;
	}
}
