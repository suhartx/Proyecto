import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import proyecto.basededatos.DatosUsuariosBD;
import proyecto.basededatos.UsuariosBD;
import proyecto.ficheros.Ficheros;
import proyecto.usuarios.Usuario;



public class TestUsuariosBD {

	ArrayList<Usuario> usuariosBD;
	
	ArrayList<Usuario> usuariosFichero;


		@Before
		 public void setUp() throws Exception {

			Ficheros.processCSV(new File("Usuarios.csv"));
			Ficheros.processCSV(new File("Procedimientos.csv"));
			Ficheros.processCSV(new File("Medicamentos.csv"));
			
			usuariosFichero=Ficheros.devuelvelistaUsuarios();
			
			usuariosBD= DatosUsuariosBD.iniciaDatos();
			
			
	}
		
		
		@After
		 public void tearDown() {
			
			

		 }
		
		/**
		 * En este test comprobamos si es los dos sistemas de ficheros devuelven el mismo objeto
		 */
		@Test
		public void objetoBDFichero() {
			for (int i = 0; i < usuariosBD.size(); i++) {
				assertEquals("Usuarios", usuariosBD.get(i).getCSV(), usuariosFichero.get(i).getCSV());
				for (int j = 0; j < usuariosBD.get(i).getCitas().size(); j++) {
					assertEquals("Citas", usuariosBD.get(i).getCitas().get(j).getCSV(), usuariosFichero.get(i).getCitas().get(j).getCSV());
				}
				for (int j = 0; j < usuariosBD.get(i).getPruebas().size(); j++) {
					assertEquals("Pruebas", usuariosBD.get(i).getPruebas().get(j).getCSV(), usuariosFichero.get(i).getPruebas().get(j).getCSV());
				}
				for (int j = 0; j < usuariosBD.get(i).getTratamientos().size(); j++) {
					assertEquals("Tratamiento", usuariosBD.get(i).getTratamientos().get(j).getCSV(), usuariosFichero.get(i).getTratamientos().get(j).getCSV());
					for (int j2 = 0; j2 < usuariosBD.get(i).getTratamientos().get(j).getMedicamentos().size(); j2++) {
						assertEquals("Medicamentos", usuariosBD.get(i).getTratamientos().get(j).getMedicamentos().get(j2).getCSV(), usuariosFichero.get(i).getTratamientos().get(j).getMedicamentos().get(j2).getCSV());						
					}
				}
			}

			
			//System.out.println(tabla.getCabecera(7));

		}
		



}
