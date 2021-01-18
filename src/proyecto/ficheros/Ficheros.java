package proyecto.ficheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files; 
import java.nio.file.Paths;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JTree;

import proyecto.basededatos.DatosUsuariosBD;
import proyecto.basededatos.UsuariosBD;
import proyecto.contenido.Cita;
import proyecto.contenido.Medicamento;
import proyecto.contenido.Prueba;
import proyecto.contenido.Tratamiento;
import proyecto.usuarios.Enfermero;
import proyecto.usuarios.Medico;
import proyecto.usuarios.Paciente;
import proyecto.usuarios.Usuario;






/**
 * clase que sirve para escribir un fichero csv formado con datos
 * @author Suhar
 *
 */
public class Ficheros { 
	
	
	private static int contCitas= 0;
	
	private static int contPruebas=0;
	
	private static int contTratamientos=0;
	
	 static ArrayList<Usuario> usuariosFicheros =  new ArrayList<Usuario>();
	static ArrayList<Integer> MedicosCabecera = new ArrayList<>();
	
	private static boolean LOG_CONSOLE_CSV = false;  // Log a consola de lo que se va leyendo en el CSV

	public static void Escribeficheros(ArrayList<?> listaDatos) {


		try {
			FileWriter fw = new FileWriter("Usuarios.csv", false);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			FileWriter fw2 = new FileWriter("Procedimientos.csv", false);
			//BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Procedimientos.csv", false), StandardCharsets.UTF_8));
			BufferedWriter bw2 = new BufferedWriter(fw2);
			PrintWriter pw2 = new PrintWriter(bw2);
			FileWriter fw3 = new FileWriter("Medicamentos.csv", false);
			BufferedWriter bw3 = new BufferedWriter(fw3);
			PrintWriter pw3 = new PrintWriter(bw3);


				for (Object o : listaDatos) {

					Usuario u = (Usuario) o;
					fw.append(u.getCSV()+"\\\n");
					for (Cita c: u.getCitas()) {
						//System.out.println(c.getCSV());
						fw2.append(c.getCSV()+","+u.getCodUsuario()+"\\\n");
						pw2.flush();
					}
					for (Prueba c: u.getPruebas()){
						fw2.append(c.getCSV()+","+u.getCodUsuario()+"\\\n");
						pw2.flush();
					}
					for (Tratamiento c: u.getTratamientos()) {
						fw2.append(c.getCSV()+","+u.getCodUsuario()+"\\\n");
						pw2.flush();
						for (Medicamento m: c.getMedicamentos()) {
							fw3.append(m.getCSV()+","+c.getCodtratamiento()+","+u.getCodUsuario()+"\\\n");
							pw3.flush();
						}
					}
					
				pw.flush();	
				}
			pw3.close();
			pw2.close();	
			pw.close();
			
			JOptionPane.showMessageDialog(null, "Ficheros CSV sobreescritos");
			
		}catch(Exception e){
			
		}
	}
	/**
	 * procesa un fichero csv
	 * @param file
	 * @throws IOException
	 */
	public static void processCSV( File file ) 
	throws IOException // Error de I/O
	{
		processCSV( file.toURI().toURL() );
	}
	
	/** Procesa un fichero csv
	 * @param urlCompleta	URL del csv
	 * @throws IOException
	 * @throws UnknownHostException
	 * @throws FileNotFoundException
	 * @throws ConnectException
	 */
	public static void processCSV( URL url ) 
	throws MalformedURLException,  // URL incorrecta 
	 IOException, // Error al abrir conexión
	 UnknownHostException, // servidor web no existente
	 FileNotFoundException, // En algunos servidores, acceso a p�gina inexistente
	 ConnectException // Error de timeout
	{
		BufferedReader input = null;
		InputStream inStream = null;
		try {
		    URLConnection connection = url.openConnection();
		    connection.setDoInput(true);
		    inStream = connection.getInputStream();
		    input = new BufferedReader(new InputStreamReader( inStream, "UTF-8" ));  // Supone utf-8 en la codificación de texto
		    String line = "";
		    int numLine = 0;
		    while ((line = input.readLine()) != null) {
		    	numLine++;
		    	if (LOG_CONSOLE_CSV) System.out.println( numLine + "\t" + line );
		    	try {
			    	ArrayList<Object> l = processCSVLine( input, line, numLine );
			    	if (LOG_CONSOLE_CSV) System.out.println( "\t" + l.size() + "\t" + l );
			    		if (!l.isEmpty()) {
			    			procesaLineaDatos( l );
			    		}
		    	} catch (StringIndexOutOfBoundsException e) {
		    		/* if (LOG_CONSOLE_CSV) */ System.err.println( "\tError: " + e.getMessage() );
		    	}
		    }
		    for(int i = 0; i < usuariosFicheros.size(); i++) {
		    	usuariosFicheros.get(i).setMedicoCabecera((Medico) usuariosFicheros.get(MedicosCabecera.get(i)-1));
			}

		} finally {
			try {
				inStream.close();
				input.close();
			} catch (Exception e2) {
			}
		}
	}
	
		/** Procesa una línea de entrada de csv	
		 * @param input	Stream de entrada ya abierto
		 * @param line	La línea YA LEÍDA desde input
		 * @param numLine	Número de línea ya leída
		 * @return	Lista de objetos procesados en el csv. Si hay algún string sin acabar en la línea actual, lee más líneas del input hasta que se acaben los strings o el input
		 * @throws StringIndexOutOfBoundsException
		 */
		public static ArrayList<Object> processCSVLine( BufferedReader input, String line, int numLine ) throws StringIndexOutOfBoundsException {
			ArrayList<Object> ret = new ArrayList<>();
			ArrayList<Object> lista = null; // Para posibles listas internas
			int posCar = 0;
			boolean inString = false; // Marca de cuando se está leyendo un string
			boolean lastString = false;  // Marca que el último leído era un string
			boolean inList = false; // Marca de cuando se está leyendo una lista (entre corchetes, separada por comas)
			boolean finString = false;
			String stringActual = "";
			char separador = 0;
			while (line!=null && (posCar<line.length() || line.isEmpty() && posCar==0)) {
				if (line.isEmpty() && posCar==0) {
					if (!inString) return ret;  // Línea vacía
				} else {
					char car = line.charAt( posCar );
					if (car=='"') {
						if (inString) {
							if (nextCar(line,posCar)=='"') {  // Doble "" es un "
								posCar++;
								stringActual += "\"";
							} else {  // " de cierre
								inString = false;
								finString = true;
								lastString = true;
							}
						} else {  // !inString
							if (stringActual.isEmpty()) {  // " de apertura
								inString = true;
							} else {  // " después de valor - error
								throw new StringIndexOutOfBoundsException( "\" after data in char " + posCar + " of line [" + line + "]" );
							}
						}
					} else if (!inString && (car == '\\'||car=='\t')) {  // separador fuera de string
						//System.out.println("golpe de remo");
					//} else if (!inString && (car=='\t')) {  // separador fuera de string
						//stringActual += car;
						// Nada que hacer
					} else if (car==',' || car==';') {
						if (inString) {  // separador dentro de string
							stringActual += car;
						} else {  // separador que separa valores
							if (separador==0) { // Si no se había encontrado separador hasta ahora
								separador = car;
								if (inList)
									lista.add( getDato( stringActual, lastString ) );
								else if (lista!=null) {
									ret.add( lista );
									lista = null;
								} else 
									ret.add( getDato( stringActual, lastString ) );
								stringActual = "";
								lastString = false;
								finString = false;
							} else { // Si se había encontrado, solo vale el mismo (, o ;)
								if (separador==car) {  // Es un separador
									if (inList)
										lista.add( getDato( stringActual, lastString ) );
									else if (lista!=null) {
										ret.add( lista );
										lista = null;
									} else 
										ret.add( getDato( stringActual, lastString ) );
									stringActual = "";
									lastString = false;
									finString = false;
								} else {  // Es un carácter normal
									if (finString) throw new StringIndexOutOfBoundsException( "Data after string in char " + posCar + " of line [" + line + "]");  // valor después de string - error
									stringActual += car;
								}
							}
						}
					} else if (!inString && car=='[') {  // Inicio de lista
						if (inList) throw new StringIndexOutOfBoundsException( "Nested lists not allowed in this process in line " + numLine + ": [" + line + "]");
						inList = true;
						lista = new ArrayList<>();
					} else if (!inString && car==']') {  // Posible fin de lista
						if (!inList) throw new StringIndexOutOfBoundsException( "Closing list not opened in line " + numLine + ": [" + line + "]");
						if (!stringActual.isEmpty()) lista.add( getDato( stringActual, lastString ) );
						stringActual = "";
						inList = false;
					} else {  // Carácter dentro de valor
						if (finString) throw new StringIndexOutOfBoundsException( "Data after string in char " + posCar + " of line [" + line + "]");  // valor después de string - error
						stringActual += car;
					}
					posCar++;
				}
				if (posCar>=line.length() && inString) {  // Se ha acabado la línea sin acabarse el string. Eso es porque algún string incluye salto de línea. Se sigue con la siguiente línea
					line = null;
				    try {
						line = input.readLine();
				    	if (LOG_CONSOLE_CSV) System.out.println( "  " + numLine + " (add)\t" + line );
						posCar = 0;
						stringActual += "\n";
					} catch (IOException e) {}  // Si la línea es null es que el fichero se ha acabado ya o hay un error de I/O
				}
			}
			if (inString) throw new StringIndexOutOfBoundsException( "String not closed in line " + numLine + ": [" + line + "]");
			if (lista!=null)
				ret.add( lista );
			else if (!stringActual.isEmpty())
				ret.add( getDato( stringActual, lastString ) );
			return ret;
		}

			// Devuelve el siguiente carácter (car 0 si no existe el siguiente carácter)
			private static char nextCar( String line, int posCar ) {
				if (posCar+1<line.length()) return line.charAt( posCar + 1 );
				else return Character.MIN_VALUE;
			}
			
			// Devuelve el objeto que corresponde a un dato (por defecto String. Si es entero o doble válido, se devuelve ese tipo)
			private static Object getDato( String valor, boolean esString ) {
				if (esString) return valor;
				try {
					Integer entero = Integer.parseInt( valor );
					return entero ;
				} catch (Exception e) {}
				try {
					double doble = Double.parseDouble( valor );
					return new Double( doble );
				} catch (Exception e) {}
				return valor;
			}


	//private static int numLin = 0;
	private static void procesaLineaDatos( ArrayList<Object> datos ) {
		// TODO Cambiar este proceso si se quiere hacer algo con las cabeceras
		//numLin++;
		
		if (datos.size()==15) {
		Usuario u= null;
		if (datos.get(14).equals("Medico")) {
			u =  new Medico();
		}else if(datos.get(14).equals("Enfermero")) {
			u = new Enfermero();
		}else if(datos.get(14).equals("Paciente")){
			u =  new Paciente();
		}
			u.setCodUsuario((Integer)datos.get(0));
			
			String s =  (String) datos.get(4);
			
			char c = s.charAt(0);
			
			Double  d =  (Double)datos.get(6);
			float f = d.floatValue();
			
			u.setNombre((String)datos.get(1));u.setApellido((String)datos.get(2));u.setDni((String)datos.get(3));
			u.setSexo(c);u.setContrasenya((String)datos.get(5));
			u.setPeso(f);
			
			u.setAltura((Integer)datos.get(7));
			u.setAlergias((String)datos.get(8));u.setColesterol((Integer)datos.get(9));u.setTension((Integer)datos.get(10));u.setEnfermedades((String)datos.get(11));
			u.setTipoSangre((String)datos.get(12));
			
			usuariosFicheros.add(u);
			MedicosCabecera.add((Integer) datos.get(13));
		}else if(datos.get(1).equals("Cita")||datos.get(1).equals("Prueba")||datos.get(1).equals("Tratamiento")) {
			int cod =  (Integer)datos.get(0);
			String tit = (String) datos.get(2);
			String desc = (String) datos.get(3);
			String amb = (String) datos.get(4);
			String f =(String) datos.get(5);
			//System.out.println(f);
			Date fec = null;
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			fec = java.sql.Date.valueOf(f);
			String t = (String)datos.get(6);

		    Time ti = Time.valueOf(t);
		    



			if (datos.get(1).equals("Cita")) {
				contCitas++;
				usuariosFicheros.get((int) datos.get(8)-1).getCitas().add(new Cita(cod, tit, desc, amb, fec, ti,usuariosFicheros.get((int) datos.get(7)-1)));

			}else if(datos.get(1).equals("Prueba")) {
				usuariosFicheros.get((int) datos.get(8)-1).getPruebas().add(new Prueba(cod, tit, desc, amb, fec, ti,usuariosFicheros.get((int) datos.get(7)-1)));
				contPruebas++;
			}else if(datos.get(1).equals("Tratamiento")){
				usuariosFicheros.get((int) datos.get(8)-1).getTratamientos().add(new Tratamiento(cod, tit, desc, amb, fec, ti,(Medico) usuariosFicheros.get((int) datos.get(7)-1)));
				contTratamientos++;
			}
		}else {

			for (Tratamiento t : usuariosFicheros.get((int) datos.get(6)-1).getTratamientos()) {
				if (t.getCodtratamiento()==(int)datos.get(5)) {
					usuariosFicheros.get((int) datos.get(6)-1).getTratamientos().get((int)datos.get(5)-1).getMedicamentos().add(new Medicamento((int) datos.get(0),
							(String)datos.get(1), (String)datos.get(2), (String)datos.get(3), java.sql.Date.valueOf((String) datos.get(4))));
				}
			}

		}

		//System.out.println(nuevoUser.toString());
		//System.out.println(Usuarios);

	}
	/**
	 * devuelve la lista de usuarios que haya genrado
	 * @return
	 */
	public static ArrayList<Usuario>devuelvelistaUsuarios(){
		return Ficheros.usuariosFicheros;
		
	}
	public static int getContCitas() {
		return contCitas;
	}
	public static void setContCitas(int contCitas) {
		Ficheros.contCitas = contCitas;
	}
	public static int getContPruebas() {
		return contPruebas;
	}
	public static void setContPruebas(int contPruebas) {
		Ficheros.contPruebas = contPruebas;
	}
	public static int getContTratamientos() {
		return contTratamientos;
	}
	public static void setContTratamientos(int contTratamientos) {
		Ficheros.contTratamientos = contTratamientos;
	}
} 