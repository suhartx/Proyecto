package proyecto.contenido;

import java.sql.Time;
import java.util.Date;

import proyecto.basededatos.UsuariosBD;
import proyecto.interfaces.ICSV;
import proyecto.usuarios.Medico;
import proyecto.usuarios.Usuario;
/**
 * case que contiene informacion a cerca de las pruebas
 * @author Suhar
 *
 */
public class Prueba implements ICSV, Comparable<Prueba>{

	private int codPrueba;
	private String titulo;
	private String descripcion;
	private String ambito;
	private Date fecha;
	private Time hora;
	private Usuario sanitarioAsociado;
	
	
	public Prueba(int codPrueba, String titulo, String descripcion, String ambito, Date fecha, Time hora,
			Usuario sanitarioAsociado) {
		super();
		this.codPrueba = codPrueba;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.ambito = ambito;
		this.fecha = fecha;
		this.hora = hora;
		this.sanitarioAsociado = sanitarioAsociado;
	}


	public Prueba() {
		super();
	}


	public Prueba(int codPrueba, String titulo, String descripcion, String ambito, Date fecha, Time hora) {
		super();
		this.codPrueba =  codPrueba;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.ambito = ambito;
		this.fecha = fecha;
		this.hora = hora;
	}

	
	public int getCodPrueba() {
		return codPrueba;
	}


	public void setCodPrueba(int codPrueba) {
		this.codPrueba = codPrueba;
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
	public String getCSV() {
		return codPrueba + ","+this.getClass().getSimpleName()+"," + titulo + "," + descripcion + "," + ambito + "," + fecha + "," + hora + ","
				+ sanitarioAsociado.getCodUsuario();
	}


	@Override
	public String toString() {
		return "Prueba [codPrueba=" + codPrueba + ", titulo=" + titulo + ", descripcion=" + descripcion + ", ambito="
				+ ambito + ", fecha=" + fecha + ", hora=" + hora + ", sanitarioAsociado=" + sanitarioAsociado + "]";
	}


	@Override
	public int compareTo(Prueba c) {
		int ret;
		ret = c.fecha.compareTo(getFecha());
				if(ret ==0) 
			ret= c.hora.compareTo(hora);
				if(ret ==0) 
			ret= c.titulo.compareToIgnoreCase(titulo);
			return ret;
	}

}
