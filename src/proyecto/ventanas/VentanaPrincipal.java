package proyecto.ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;

import proyecto.basededatos.DatosUsuariosBD;
import proyecto.contenido.Cita;
import proyecto.contenido.Medicamento;
import proyecto.contenido.Prueba;
import proyecto.contenido.Tratamiento;
import proyecto.ficheros.Ficheros;
import proyecto.usuarios.Medico;
import proyecto.usuarios.Paciente;
import proyecto.usuarios.Usuario;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import jdk.internal.dynalink.beans.StaticClass;

import javax.swing.JSplitPane;
import java.awt.Dimension;
import java.awt.ComponentOrientation;
import java.awt.Rectangle;
/**
 * metodo que genera la ventana principal del programa
 * @author Suhar
 *
 */
@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame implements TreeSelectionListener {


	DatosUsuariosBD datos;
	private static ArrayList<Usuario> usuarios;
    private static int posPersona;
    
	HashMap<Integer, Usuario> usuariosMapID =  new HashMap<>();
	
	HashMap<String, Usuario> usuariosMapNombre =  new HashMap<>();
	
	private JTree tree;

	private PanelInfoUsuarios panelInfoUsuarios;
	private PanelInfoProcedimientos panelInfoProcedimientos;
	private  PanelInformeProcedimiento panelInformeProcedimiento;
	private JScrollPane panelDatos;

	private JButton btnCrear;

	private JButton btnEliminar;

	private JTextField panelBusqueda;
	private JScrollPane panelArbol;



	/**
	 * crea el frame
	 * @param datos de los usuarios que hemos generado
	 * @param posPersona posicion de la personal logueada dentro del arraylist de usuarios
	 */
	public VentanaPrincipal(DatosUsuariosBD datos, int posPersona) {
		
		this.datos=datos;
		this.usuariosMapNombre = datos.devuelveUsuariosMapNombre();
		this.usuariosMapID = datos.devuelveUsuariosMapID();
		VentanaPrincipal.usuarios=datos.devuelveUsuarios();
		VentanaPrincipal.posPersona = posPersona;
		setTitle("Ventana principal "+usuarios.get(posPersona).getClass().getSimpleName()+" "+usuarios.get(posPersona).getNombre());
		setIconImage(Toolkit.getDefaultToolkit().getImage("src\\imagenes\\osakidetza.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.2), (int) (Toolkit.getDefaultToolkit().getScreenSize().height*0.15),//establezco el tama�o de la ventana adapado para
				(int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.6), (int) (Toolkit.getDefaultToolkit().getScreenSize().height*0.6));//la pantalla de diferentes PCs

		
		inicializarArbol();		
		inicializarPaneles();
		iniciaPanelDatos();
		
		
		
		DefaultMutableTreeNode nodoSeleccionado = (((DefaultMutableTreeNode) (tree.getLastSelectedPathComponent())));
		
		btnCrear.addActionListener(new ActionListener() {
			

			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (((((DefaultMutableTreeNode) (tree.getLastSelectedPathComponent())).getUserObject()).equals("citas"))||((DefaultMutableTreeNode)((DefaultMutableTreeNode) (tree.getLastSelectedPathComponent())).getParent()).getUserObject().equals("citas")) {
					llamarVentanaCita();
					
					
					
				}
				else if (((((DefaultMutableTreeNode) (tree.getLastSelectedPathComponent())).getUserObject()).equals("pruebas"))||((DefaultMutableTreeNode)((DefaultMutableTreeNode) (tree.getLastSelectedPathComponent())).getParent()).getUserObject().equals("pruebas")) {
					
					llamarVentanaPrueba();
					
				}
				else if (((((DefaultMutableTreeNode) (tree.getLastSelectedPathComponent())).getUserObject()).equals("tratamientos"))||((DefaultMutableTreeNode)((DefaultMutableTreeNode) (tree.getLastSelectedPathComponent())).getParent()).getUserObject().equals("tratamientos")) {
					
					llamarVentanaTratamiento();
					
				}else {
					System.out.println(((DefaultMutableTreeNode)((DefaultMutableTreeNode) (tree.getLastSelectedPathComponent())).getParent()).getUserObject());
					JOptionPane.showMessageDialog(
							   panelDatos,
							   "No puedes hacer eso");
				}
				
			}
		});

		btnEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (((DefaultMutableTreeNode)((DefaultMutableTreeNode) (tree.getLastSelectedPathComponent())).getParent()).getUserObject().equals("tratamientos")||((DefaultMutableTreeNode)((DefaultMutableTreeNode) (tree.getLastSelectedPathComponent())).getParent()).getUserObject().equals("pruebas")||((DefaultMutableTreeNode)((DefaultMutableTreeNode) (tree.getLastSelectedPathComponent())).getParent()).getUserObject().equals("citas")) {
					
					eliminarContenido();
					
				}else {
					System.out.println(((DefaultMutableTreeNode)((DefaultMutableTreeNode) (tree.getLastSelectedPathComponent())).getParent()).getUserObject());
					JOptionPane.showMessageDialog(
							   panelDatos,
							   "No puedes hacer eso");
				}
			}
		});
		
	}
	
	
	/**
	 * metodo que inicializa los paneles internos
	 */
	public void inicializarPaneles() {
		
		
		JPanel contentPane = new JPanel();
		contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);

		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu Test");
		menuBar.add(menu);
		setJMenuBar(menuBar);
		
		JPanel panelIzquierdo = new JPanel();
		panelIzquierdo.setBorder(new TitledBorder(null, "Menu usuarios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setLeftComponent(panelIzquierdo);
		panelIzquierdo.setLayout(new BorderLayout(0, 0));
		
		panelArbol = new JScrollPane();
		panelArbol.setPreferredSize(new Dimension(150, 4));
		panelArbol.setMinimumSize(new Dimension(40, 23));
		panelIzquierdo.add(panelArbol, BorderLayout.CENTER);
		
		panelArbol.add(tree);
		panelArbol.setViewportView(tree);

		JPanel panelCentral = new JPanel();
		splitPane.setRightComponent(panelCentral);
		panelCentral.setLayout(new BorderLayout(0, 0));
		
		panelBusqueda = new JTextField();
		panelBusqueda.setColumns(10);
		panelCentral.add(panelBusqueda, BorderLayout.NORTH);
		
		panelDatos = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
	            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelCentral.add(panelDatos, BorderLayout.CENTER);

		JPanel panelBotonera = new JPanel();
		contentPane.add(panelBotonera, BorderLayout.SOUTH);
		
		btnCrear = new JButton("Crear");
		panelBotonera.add(btnCrear);
		


		
		btnEliminar = new JButton("Eliminar");
		panelBotonera.add(btnEliminar);
		if (usuarios.get(posPersona).getClass().getSimpleName().equals("Paciente")) {
			
			btnCrear.setEnabled(false);
			btnEliminar.setEnabled(false);

		}
		
		
	}
	/**
	 * metodo que inicializa el arbol 
	 */
	public void inicializarArbol() {
		


		tree = usuarios.get(posPersona).cargarJTree(usuarios);
		tree.getSelectionModel().addTreeSelectionListener(this);



	}
	
	public void iniciaPanelDatos() {
		

		
		panelInfoUsuarios =  new PanelInfoUsuarios();
		
		panelInfoProcedimientos =  new PanelInfoProcedimientos();
		
		panelInformeProcedimiento =  new PanelInformeProcedimiento();
		


	}

	
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		
		 DefaultMutableTreeNode nodoSeleccionado = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		 System.out.println(nodoSeleccionado.getUserObject());
		 eventosNodoSeleccionado(nodoSeleccionado);

		
	}
	/**
	 * Permite validar cual fue la hoja seleccionada para mostrar el
	 * panel correspondiente
	 * @param nodo
	 */
	private void eventosNodoSeleccionado(DefaultMutableTreeNode nodo) {
		
		if (usuariosMapNombre.containsKey(nodo.getUserObject()))
		{

			panelDatos.getViewport().removeAll();
			panelDatos.getViewport().invalidate();

			panelInfoUsuarios.setInfoUsuario(usuariosMapNombre.get(nodo.getUserObject()));
			
			
			panelDatos.add(panelInfoUsuarios);
			panelDatos.setViewportView(panelInfoUsuarios);


			panelDatos.getViewport().revalidate();
			//panelDatos.getViewport().setBackground(Color.BLACK);
		  System.out.println("Selecciona : "+nodo.toString());
		}
		
		if (nodo.getUserObject().equals("citas")) {
			
			
			if (!usuarios.get(posPersona).getClass().getSimpleName().equals("Paciente")) {
				
				btnCrear.setEnabled(true);
				btnEliminar.setEnabled(true);
			}

			panelDatos.getViewport().removeAll();
			panelDatos.getViewport().invalidate();

			panelInfoProcedimientos.limpiarInterior();
			panelInfoProcedimientos.RellenarDeCitas(usuariosMapNombre.get(((DefaultMutableTreeNode)nodo.getParent()).getUserObject()));
			
			
			panelDatos.add(panelInfoProcedimientos);
			panelDatos.setViewportView(panelInfoProcedimientos);

			panelDatos.getViewport().revalidate();
			//panelDatos.getViewport().setBackground(Color.BLACK);
			
		}
		if (nodo.getUserObject().equals("pruebas")) {
			

			if (!usuarios.get(posPersona).getClass().getSimpleName().equals("Paciente")) {
				
				btnCrear.setEnabled(true);
				btnEliminar.setEnabled(true);
			}
			
			panelDatos.getViewport().removeAll();
			panelDatos.getViewport().invalidate();

			panelInfoProcedimientos.limpiarInterior();
			panelInfoProcedimientos.RellenarDePruebas(usuariosMapNombre.get(((DefaultMutableTreeNode)nodo.getParent()).getUserObject()));
			
			
			panelDatos.add(panelInfoProcedimientos);
			panelDatos.setViewportView(panelInfoProcedimientos);

			panelDatos.getViewport().revalidate();
			//panelDatos.getViewport().setBackground(Color.BLACK);
			
		}
		if (nodo.getUserObject().equals("tratamientos")) {
			if (rootPaneCheckingEnabled) {
				if (usuarios.get(posPersona).getClass().getSimpleName().equals("Enfermero")) {
					
					btnCrear.setEnabled(false);
					btnEliminar.setEnabled(false);
				}
			}

			panelDatos.getViewport().removeAll();
			panelDatos.getViewport().invalidate();

			panelInfoProcedimientos.limpiarInterior();
			panelInfoProcedimientos.RellenarDeTReatamentos(usuariosMapNombre.get(((DefaultMutableTreeNode)nodo.getParent()).getUserObject()));
			
			
			panelDatos.add(panelInfoProcedimientos);
			panelDatos.setViewportView(panelInfoProcedimientos);

			panelDatos.getViewport().revalidate();
			//panelDatos.getViewport().setBackground(Color.BLACK);
			
		}
		
		if (((DefaultMutableTreeNode)nodo.getParent()).getUserObject().equals("citas")) {
			
			if (!usuarios.get(posPersona).getClass().getSimpleName().equals("Paciente")) {
				
				btnCrear.setEnabled(true);
				btnEliminar.setEnabled(true);
			}
			
			panelDatos.getViewport().removeAll();
			panelDatos.getViewport().invalidate();
			
			Usuario u =  usuariosMapNombre.get(((DefaultMutableTreeNode)nodo.getParent().getParent()).getUserObject());
			
			System.out.println(u.getNombre());
			
			System.out.println(u.getCitaSeleccionada((String) nodo.getUserObject()).getTitulo());
			
			panelInformeProcedimiento.limpiarInterior();
			panelInformeProcedimiento.informeCitas(u,
					u.getCitaSeleccionada((String) nodo.getUserObject()));

			panelDatos.add(panelInformeProcedimiento);
			panelDatos.setViewportView(panelInformeProcedimiento);

			panelDatos.getViewport().revalidate();
			//panelDatos.getViewport().setBackground(Color.BLACK);
			
			
			
		}
		if (((DefaultMutableTreeNode)nodo.getParent()).getUserObject().equals("pruebas")) {
			
			if (!usuarios.get(posPersona).getClass().getSimpleName().equals("Paciente")) {
				
				btnCrear.setEnabled(true);
				btnEliminar.setEnabled(true);
			}
			
			panelDatos.getViewport().removeAll();
			panelDatos.getViewport().invalidate();
			
			Usuario u =  usuariosMapNombre.get(((DefaultMutableTreeNode)nodo.getParent().getParent()).getUserObject());
//			
//			System.out.println(u.getNombre());
//			
//			System.out.println(u.getCitaSeleccionada((String) nodo.getUserObject()).getTitulo());
			
			panelInformeProcedimiento.limpiarInterior();
			panelInformeProcedimiento.informePruebas(u,
					u.getPruebaSeleccionada((String) nodo.getUserObject()));

			panelDatos.add(panelInformeProcedimiento);
			panelDatos.setViewportView(panelInformeProcedimiento);

			panelDatos.getViewport().revalidate();
			//panelDatos.getViewport().setBackground(Color.BLACK);
			
			
			
		}
		if (((DefaultMutableTreeNode)nodo.getParent()).getUserObject().equals("tratamientos")) {
			
			if (rootPaneCheckingEnabled) {
				if (usuarios.get(posPersona).getClass().getSimpleName().equals("Enfermero")) {
					
					btnCrear.setEnabled(false);
					btnEliminar.setEnabled(false);
				}
			}

			
			panelDatos.getViewport().removeAll();
			panelDatos.getViewport().invalidate();
			
			Usuario u =  usuariosMapNombre.get(((DefaultMutableTreeNode)nodo.getParent().getParent()).getUserObject());
//			
//			System.out.println(u.getNombre());
//			
//			System.out.println(u.getTratamientoSeleccionado((String) nodo.getUserObject()).getTitulo());
//			
			panelInformeProcedimiento.limpiarInterior();
			panelInformeProcedimiento.informeTratamientos(u,
					u.getTratamientoSeleccionado((String) nodo.getUserObject()));

			panelDatos.add(panelInformeProcedimiento);
			panelDatos.setViewportView(panelInformeProcedimiento);

			panelDatos.getViewport().revalidate();
			//panelDatos.getViewport().setBackground(Color.BLACK);
			
			
			
		}
	}
	
	/**
	 * devuelve el usuario de la rama del arbol que hayas seleccionado
	 * @return
	 */
	public Usuario getPersonaSeleccionada() {
		
		if (usuariosMapNombre.containsKey(((DefaultMutableTreeNode)((DefaultMutableTreeNode) (tree.getLastSelectedPathComponent())).getParent()).getUserObject())) {
			return usuariosMapNombre.get(((DefaultMutableTreeNode)((DefaultMutableTreeNode) (tree.getLastSelectedPathComponent())).getParent()).getUserObject());
		}else {
			//System.out.println(((DefaultMutableTreeNode)((DefaultMutableTreeNode)((DefaultMutableTreeNode) (tree.getLastSelectedPathComponent())).getParent()).getParent()).getUserObject());
			return usuariosMapNombre.get(((DefaultMutableTreeNode)((DefaultMutableTreeNode)((DefaultMutableTreeNode) (tree.getLastSelectedPathComponent())).getParent()).getParent()).getUserObject());			
		}
	}
	/**
	 * llama a la ventana cita
	 */
	public void llamarVentanaCita() {

		VentanaCita v = new VentanaCita(this, usuarios.get(getPersonaSeleccionada().getCodUsuario()-1), posPersona);
		v.setVisible(true);
	}
	/**
	 * llama a la ventana prueba
	 */
	public void llamarVentanaPrueba() {

		VentanaPrueba p = new VentanaPrueba(this, usuarios.get(getPersonaSeleccionada().getCodUsuario()-1), posPersona);
		p.setVisible(true);
	}
	/**
	 * llama a la ventana tratamiento
	 */
	public void llamarVentanaTratamiento() {

		VentanaTratamiento t = new VentanaTratamiento(this, usuarios.get(getPersonaSeleccionada().getCodUsuario()-1), posPersona);
		t.setVisible(true);
	}
	/**
	 * añade la cita al arbol y a la base de datos y los ficheros
	 */
	public void anadirCitas(Usuario pac, String titulo, String ambito, Date fec, String descripcion, Time hora) {
		tree.getSelectionModel().removeTreeSelectionListener(this);
			usuarios.get(pac.getCodUsuario()-1).getCitas().add(new Cita(datos.getContCitas()+1, titulo, descripcion, ambito, fec, hora, usuarios.get(posPersona)));


			
			inicializarArbol();
			panelArbol.getViewport().removeAll();
			panelArbol.getViewport().invalidate();
			panelArbol.setViewportView(tree);
			panelArbol.revalidate();
			panelArbol.repaint();
			String sentencia = "('"+(datos.getContCitas()+1)+"',"+ "'" + titulo+"',"+"'" + descripcion+"',"+"'" + ambito+"',"+
					"'" + (posPersona+1)+"',"+"'" + pac.getCodUsuario()+"',"+"'" + fec+"',"+"'" + hora+"')";
			
			datos.anadirCita(sentencia);
			
			Ficheros.Escribeficheros(usuarios);


		
	}
	/**
	 * Añade la prueba al arbo a las bases de datos y al fichero
	 */
	public void anadirPruebas(Usuario pac, String titulo, String ambito, Date fec, String descripcion, Time hora) {
		
		tree.getSelectionModel().removeTreeSelectionListener(this);
		usuarios.get(pac.getCodUsuario()-1).getPruebas().add(new Prueba(datos.getContPruebas()+1, titulo, descripcion, ambito, fec, hora, usuarios.get(posPersona)));


		
		inicializarArbol();
		panelArbol.getViewport().removeAll();
		panelArbol.getViewport().invalidate();
		panelArbol.setViewportView(tree);
		panelArbol.revalidate();
		panelArbol.repaint();
		String sentencia = "('"+(datos.getContPruebas()+1)+"',"+ "'" + titulo+"',"+"'" + descripcion+"',"+"'" + ambito+"',"+
				"'" + (posPersona+1)+"',"+"'" + pac.getCodUsuario()+"',"+"'" + fec+"',"+"'" + hora+"')";
		
		datos.anadirPrueba(sentencia);
		
		Ficheros.Escribeficheros(usuarios);
	}
	/*
	 * añade el tratamiento a la ventana al fichero y a las bases de datos
	 */
	public void anadirtratamientos(Usuario pac, String titulo, String ambito, Date fec, String descripcion, Time hora, ArrayList<Medicamento> medicamentos) {
		
		tree.getSelectionModel().removeTreeSelectionListener(this);
		usuarios.get(pac.getCodUsuario()-1).getTratamientos().add(new Tratamiento(datos.getContTratamientos()+1, titulo, descripcion, ambito, fec, hora, (Medico)usuarios.get(posPersona), medicamentos));
		
		
		
		inicializarArbol();
		panelArbol.getViewport().removeAll();
		panelArbol.getViewport().invalidate();
		panelArbol.setViewportView(tree);
		panelArbol.revalidate();
		panelArbol.repaint();
		int contTratamiento = datos.getContTratamientos()+1;
		String sentencia = "('"+(contTratamiento)+"',"+ "'" + titulo+"',"+"'" + descripcion+"',"+"'" + ambito+"',"+
				"'" + fec+"',"+"'" + hora+"', '" + (posPersona+1)+"',"+"'" + pac.getCodUsuario()+"')";

		
		datos.anadirTratamiento(sentencia);
		HashSet<Medicamento> medConcretos= new HashSet<Medicamento>();
		
		
		for (Medicamento medicamento : medicamentos) {
			medicamento.setContador(1);
			
		}
		for (Medicamento medicamento : medicamentos) {
			medConcretos.add(medicamento);
		}
		Iterator<Medicamento> iterador = medConcretos.iterator();
		while(iterador.hasNext()) {
			Medicamento m = iterador.next();
			String SentenciaMedicamentos = "('"+(contTratamiento)+"',"+ "'" +(m.getCodMedicamento())+"',"+m.getContador()+")";
			datos.anadirTratamientoAsociado(SentenciaMedicamentos);
		}



		
		Ficheros.Escribeficheros(usuarios);
	}
	/**
	 * elimina el contenido que hayas seleccionado
	 */
	public void eliminarContenido(){
		
		int respuesta = JOptionPane.showConfirmDialog(this, "seguro que quieres elimiar esto?");
		if (respuesta==0) {
			if (((DefaultMutableTreeNode)((DefaultMutableTreeNode) (tree.getLastSelectedPathComponent())).getParent()).getUserObject().equals("citas")) {
				
				//getPersonaSeleccionada().getCitaSeleccionada((String)((DefaultMutableTreeNode) tree.getLastSelectedPathComponent()).getUserObject()).get;
				HashMap<String, Cita> mapaCitas =  usuarios.get(getPersonaSeleccionada().getCodUsuario()-1).getMapacitas();
				
				Cita citaEnCuestion = mapaCitas.get((String)((DefaultMutableTreeNode) tree.getLastSelectedPathComponent()).getUserObject());
				mapaCitas.remove((String)((DefaultMutableTreeNode) tree.getLastSelectedPathComponent()).getUserObject());

				usuarios.get(getPersonaSeleccionada().getCodUsuario()-1).setCitas(new ArrayList<Cita>(mapaCitas.values()));
				datos.eliminarCitas(String.valueOf(citaEnCuestion.getCodCita()));

				
				
			}else if(((DefaultMutableTreeNode)((DefaultMutableTreeNode) (tree.getLastSelectedPathComponent())).getParent()).getUserObject().equals("pruebas")) {
				//getPersonaSeleccionada().getCitaSeleccionada((String)((DefaultMutableTreeNode) tree.getLastSelectedPathComponent()).getUserObject()).get;
				HashMap<String, Prueba> mapaPruebas =  usuarios.get(getPersonaSeleccionada().getCodUsuario()-1).getMapaPruebas();
				
				Prueba pruebaEnCuestion = mapaPruebas.get((String)((DefaultMutableTreeNode) tree.getLastSelectedPathComponent()).getUserObject());
				mapaPruebas.remove((String)((DefaultMutableTreeNode) tree.getLastSelectedPathComponent()).getUserObject());

				usuarios.get(getPersonaSeleccionada().getCodUsuario()-1).setPruebas(new ArrayList<Prueba>(mapaPruebas.values()));
				datos.eliminarPruebas(String.valueOf(pruebaEnCuestion.getCodPrueba()));
			}else if(((DefaultMutableTreeNode)((DefaultMutableTreeNode) (tree.getLastSelectedPathComponent())).getParent()).getUserObject().equals("tratamientos")) {
				HashMap<String, Tratamiento> mapaTratamientos =  usuarios.get(getPersonaSeleccionada().getCodUsuario()-1).getMapaTratamientos();
				
				Tratamiento tratamientoEnCuestion = mapaTratamientos.get((String)((DefaultMutableTreeNode) tree.getLastSelectedPathComponent()).getUserObject());
				mapaTratamientos.remove((String)((DefaultMutableTreeNode) tree.getLastSelectedPathComponent()).getUserObject());

				usuarios.get(getPersonaSeleccionada().getCodUsuario()-1).setTratamientos(new ArrayList<Tratamiento>(mapaTratamientos.values()));
				datos.eliminarCitas(String.valueOf(tratamientoEnCuestion.getCodtratamiento()));
			}
			Ficheros.Escribeficheros(usuarios);
			inicializarArbol();
			panelArbol.getViewport().removeAll();
			panelArbol.getViewport().invalidate();
			panelArbol.setViewportView(tree);
			panelArbol.revalidate();
			panelArbol.repaint();
		}
	}

}
