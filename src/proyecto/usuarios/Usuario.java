package proyecto.usuarios;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import proyecto.contenido.Cita;
import proyecto.contenido.Prueba;
import proyecto.contenido.Tratamiento;
import proyecto.interfaces.ICSV;
import sun.security.util.Password;

public abstract class Usuario implements ICSV{



	private int codUsuario;
	private String nombre;
	private String apellido;
	private String dni;
	private char sexo;
	private String contrasenya;
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
	HashMap<String, Cita> mapaCitas;
	HashMap<String, Prueba> mapaPruebas;
	HashMap<String, Tratamiento> mapaTratamientos;
	
	
	/**
	 * Objeto usuario vacio para inicializarlo
	 */
	public Usuario() {

	}


	/**
	 * Clase principal donde vamos a partir todos los usuarios
	 * @param nombre
	 * @param apellido
	 * @param dni
	 * @param sexo
	 * @param contrase�a
	 * @param peso
	 * @param altura
	 * @param alergias
	 * @param colesterol
	 * @param tension
	 * @param enfermedades
	 * @param tipoSangre
	 */
	
	public Usuario(int codusuario, String nombre, String apellido, String dni, char sexo, String contrasenya, float peso, int altura,
			String alergias, int colesterol, int tension, String enfermedades, String tipoSangre) {

		this.codUsuario=codusuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.sexo = sexo;
		this.contrasenya = contrasenya;
		this.peso = peso;
		this.altura = altura;
		Alergias = alergias;
		this.colesterol = colesterol;
		this.tension = tension;
		this.enfermedades = enfermedades;
		this.tipoSangre = tipoSangre;
		


	}


	public int getCodUsuario() {
		return codUsuario;
	}


	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
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


	public String getContrasenya() {
		return contrasenya;
	}


	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
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
	/**
	 * devuelve un treeset de citas ordenadas
	 * @return
	 */
	public TreeSet<Cita> getCitasOrdenadas(){
		TreeSet<Cita> citasOrdenadas =  new TreeSet<Cita>();
		
		for (Cita cita : getCitas()) {
			citasOrdenadas.add(cita);
		}
		return citasOrdenadas;
	}
	/**
	 * genera una lista de Strings de citas
	 * @return
	 */
	public ArrayList<String> getListaCitas() {
		ArrayList<String> CitasLista = new ArrayList<String>();
		for (Cita p : citas) {
			CitasLista.add(p.getTitulo());
		}
		return CitasLista;
	}

	/**
	 * devuelve la cita con el nombre y codigo que hayas generado
	 * @param cita cod de cita + nombre
	 * @return
	 */
	public Cita getCitaSeleccionada(String cita){
		
		mapaCitas =  new HashMap<String, Cita>();
		for (Cita c : citas) {
			mapaCitas.put(c.getCodCita() + " " + c.getTitulo(), c);
		}
		
		if (mapaCitas.containsKey(cita)) {
			return mapaCitas.get(cita);
		}else {
			System.out.println(mapaCitas.toString());
			System.out.println("no encuentra la cita");
			return null;
		}
		
	}
	/**
	 * devuelve un hashmap de citas
	 * @return
	 */
	public HashMap<String, Cita> getMapacitas(){
		mapaCitas =  new HashMap<String, Cita>();
		for (Cita c : citas) {
			mapaCitas.put(c.getCodCita() + " " + c.getTitulo(), c);
		}
		return mapaCitas;
	}
	

	public void setCitas(ArrayList<Cita> citas) {
		this.citas = citas;

	}


	public ArrayList<Tratamiento> getTratamientos() {
		return tratamientos;
	}
	/**
	 * genera un treeset ordenado de los tratamientos
	 * @return treeset de tratamientos
	 */
	public TreeSet<Tratamiento> getTratamientosOrdenados(){
		TreeSet<Tratamiento> tratamientosOrdenados =  new TreeSet<Tratamiento>();
		
		for (Tratamiento tratamiento : getTratamientos()) {
			tratamientosOrdenados.add(tratamiento);
		}
		return tratamientosOrdenados;
	}
	/**
	 * devuelve un arraylist de Strings con los nombres de tratamientos
	 */
	public ArrayList<String> getListaTratamientos() {
		ArrayList<String> TratamientosLista = new ArrayList<String>();
		for (Tratamiento p : tratamientos) {
			TratamientosLista.add(p.getTitulo());
		}
		return TratamientosLista;
	}
	/**
	 * te devuelve el tratamiento seleccionado
	 * @param tratamiento cod + nombre del tratamiento que quieras seleccionar
	 * @return
	 */
	public Tratamiento getTratamientoSeleccionado(String tratamiento) {
		mapaTratamientos = new HashMap<String, Tratamiento>();
		for (Tratamiento t : tratamientos) {
			mapaTratamientos.put(t.getCodtratamiento()+ " "+ t.getTitulo(), t);
		}
		if (mapaTratamientos.containsKey(tratamiento)) {
			return mapaTratamientos.get(tratamiento);
		}else {
			System.out.println("no encuentra el tratamiento");
			return null;
		}
	}
	/**
	 * devuelve un hashmap de los tratamientos
	 * @return
	 */
	public HashMap<String, Tratamiento> getMapaTratamientos(){
		mapaTratamientos =  new HashMap<String, Tratamiento>();
		for (Tratamiento t : tratamientos) {
			mapaTratamientos.put(t.getCodtratamiento() + " " + t.getTitulo(), t);
		}
		return mapaTratamientos;
	}

	public void setTratamientos(ArrayList<Tratamiento> tratamientos) {
		this.tratamientos = tratamientos;
	}


	public ArrayList<Prueba> getPruebas() {
		return pruebas;
	}
	/**
	 * devuelve unna lista de Strings de las pruebas
	 * @return
	 */
	public ArrayList<String> getListaPruebas() {
		ArrayList<String> pruebasLista = new ArrayList<String>();
		for (Prueba p : pruebas) {
			pruebasLista.add(p.getTitulo());
		}
		return pruebasLista;
	}

	/**
	 * devuelve un treeset de las pruebas ordenadas
	 * @return
	 */
	public TreeSet<Prueba> getPruebasOrdenadas(){
		TreeSet<Prueba> pruebasOrdenadas =  new TreeSet<Prueba>();
		
		for (Prueba prueba : getPruebas()) {
			pruebasOrdenadas.add(prueba);
		}
		return pruebasOrdenadas;
	}
	/**
	 * devuelve la prueva que hayas seleccionado
	 * @param prueba cod + nombre de prueba separados por un espacio
	 * @return
	 */
	public Prueba getPruebaSeleccionada(String prueba) {
		mapaPruebas =  new HashMap<String, Prueba>();
		for (Prueba p : pruebas) {
			mapaPruebas.put(p.getCodPrueba()+ " "+ p.getTitulo(), p);
		}
		if (mapaPruebas.containsKey(prueba)) {
			return mapaPruebas.get(prueba);
		}else {
			System.out.println("no encuentra la prueba");
			return null;
		}
	}
	/**
	 * devcuelve un hashmap de las pruebas
	 * @return
	 */
	public HashMap<String, Prueba> getMapaPruebas(){
		mapaPruebas =  new HashMap<String, Prueba>();
		for (Prueba p : pruebas) {
			mapaPruebas.put(p.getCodPrueba() + " " + p.getTitulo(), p);
		}
		return mapaPruebas;
	}
	

	public void setPruebas(ArrayList<Prueba> pruebas) {
		this.pruebas = pruebas;
	}
	/**
	 * genera un string en el formato adecuado para subirlo a un csv
	 */
	@Override
	public String getCSV() {
		return codUsuario + "," + nombre + "," + apellido + "," + dni + "," + sexo + "," + contrasenya + "," + peso
				+ "," + altura + "," + Alergias + "," + colesterol + "," + tension + "," + enfermedades + ","
				+ tipoSangre + "," + medicoCabecera.getCodUsuario() + "," + this.getClass().getSimpleName();
	}
	
	
	
	
	@Override
	public String toString() {
		return "Usuario [codUsuario=" + codUsuario + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni
				+ ", sexo=" + sexo + ", contrasenya=" + contrasenya + ", peso=" + peso + ", altura=" + altura
				+ ", Alergias=" + Alergias + ", colesterol=" + colesterol + ", tension=" + tension + ", enfermedades="
				+ enfermedades + ", tipoSangre=" + tipoSangre + ", medicoCabecera=" + this.medicoCabecera.getNombre() + ", citas="
				+ this.getListaCitas() + ", tratamientos=" + this.getListaTratamientos() + ", pruebas=" + this.getListaPruebas() + "]";
	}

	/**
	 * Este metodo devuelve un arraylist de los datos Importantes del usuario, 
	 * frecuentemente usado para mostrar datos en la ventana de informacion del usuario
	 * @return arraylist de datos
	 */
	public ArrayList<String> devuelveDatosPanel(){
		return null;
		
		
	}

	/**
	 * carga el jtree de los usuarios en cuestion
	 * @param user
	 * @return
	 */
	public JTree cargarJTree(ArrayList<Usuario> user) {
		
		JTree arbol = new JTree();

		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("nodo raiz, no deberia ser visible");		
		
		DefaultTreeModel defaultTreeModel = new DefaultTreeModel( rootNode );		
		
		arbol.setModel( defaultTreeModel );		

		
		for (Usuario u : user) {
			
			DefaultMutableTreeNode t = new DefaultMutableTreeNode(u.getNombre()+" "+ u.getApellido());
			DefaultMutableTreeNode cita = new DefaultMutableTreeNode("citas");
			DefaultMutableTreeNode prueba = new DefaultMutableTreeNode("pruebas");
			DefaultMutableTreeNode tratamiento = new DefaultMutableTreeNode("tratamientos");

			
			
			defaultTreeModel.insertNodeInto(t, (MutableTreeNode) defaultTreeModel.getRoot(), ((DefaultMutableTreeNode) defaultTreeModel.getRoot()).getChildCount());
			
			defaultTreeModel.insertNodeInto(cita, t, t.getChildCount());
			
			defaultTreeModel.insertNodeInto(prueba, t, t.getChildCount());
			
			defaultTreeModel.insertNodeInto(tratamiento, t, t.getChildCount());
			
			
			for (Cita c : u.getCitas()) {
				defaultTreeModel.insertNodeInto(new DefaultMutableTreeNode(c.getCodCita() +" " +c.getTitulo()), cita, cita.getChildCount());
			}


			for (Prueba p : u.getPruebas()) {
				defaultTreeModel.insertNodeInto(new DefaultMutableTreeNode(p.getCodPrueba() +" " +p.getTitulo()), prueba, prueba.getChildCount());
			}			
			for (Tratamiento tr : u.getTratamientos()) {
				defaultTreeModel.insertNodeInto(new DefaultMutableTreeNode(tr.getCodtratamiento() +" " +tr.getTitulo()), tratamiento, tratamiento.getChildCount());
			}			
			
			
			
		}
		
		arbol.expandRow(0);
		
		arbol.setRootVisible( false );
		
		//arbol.setShowsRootHandles(true);
		
		return arbol;
	}
	/**
	 * Estr metodo devuelve una tabla con todos los los datos del usuario
	 * @return
	 */
	public static JTable devuelveTablaUsuario() {
		JTable tablaU = new JTable();
		DefaultTableModel modelo = (DefaultTableModel)tablaU.getModel();
		
		
		return null;
		
	}
}
