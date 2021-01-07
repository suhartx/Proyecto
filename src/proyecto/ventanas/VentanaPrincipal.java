package proyecto.ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;

import proyecto.basededatos.DatosUsuariosBD;
import proyecto.usuarios.Paciente;
import proyecto.usuarios.Usuario;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JSplitPane;
import java.awt.Dimension;
import java.awt.ComponentOrientation;
import java.awt.Rectangle;

public class VentanaPrincipal extends JFrame implements TreeSelectionListener {
	private JTextField panelBusqueda;
    static ArrayList<Usuario> usuarios;
    static int posPersona;
    
	HashMap<Integer, Usuario> usuariosMapID =  new HashMap<>();
	
	HashMap<String, Usuario> usuariosMapNombre =  new HashMap<>();

	JTree tree;
	private JPanel contentPane;
	private JPanel panelCentral;
	private JScrollPane panelDatos;
	//private JPanel panelDatos;




	/**
	 * Create the frame.
	 */
	public VentanaPrincipal(DatosUsuariosBD datos, int posPersona) {
		
		
		this.usuariosMapNombre = datos.devuelveUsuariosMapNombre();
		this.usuariosMapID = datos.devuelveUsuariosMapID();
		this.usuarios=datos.devuelveUsuarios();
		this.posPersona = posPersona;
		setTitle("Ventana principal "+usuarios.get(posPersona).getClass().getSimpleName()+" "+usuarios.get(posPersona).getNombre());
		setIconImage(Toolkit.getDefaultToolkit().getImage("src\\imagenes\\osakidetza.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.2), (int) (Toolkit.getDefaultToolkit().getScreenSize().height*0.15),//establezco el tama�o de la ventana adapado para
				(int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.6), (int) (Toolkit.getDefaultToolkit().getScreenSize().height*0.6));//la pantalla de diferentes PCs
		contentPane = new JPanel();
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
		
		JScrollPane panel = new JScrollPane();
		panel.setPreferredSize(new Dimension(150, 4));
		panel.setMinimumSize(new Dimension(40, 23));
		panelIzquierdo.add(panel, BorderLayout.CENTER);
		

		
		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Pacientes");
		
		DefaultMutableTreeNode paciente = new DefaultMutableTreeNode("Paciente");
		
		raiz.add(paciente);
		
		DefaultMutableTreeNode cita = new DefaultMutableTreeNode("citas");
		
		paciente.add(cita);
		
		DefaultMutableTreeNode cita1 = new DefaultMutableTreeNode("Revision");
		
		cita.add(cita1);
		
		DefaultMutableTreeNode prueba = new DefaultMutableTreeNode("pruebas");
		
		paciente.add(prueba);
		
		DefaultMutableTreeNode prueba1 = new DefaultMutableTreeNode("prueba Asma");
		
		prueba.add(prueba1);
		
		DefaultMutableTreeNode paciente2 = new DefaultMutableTreeNode("Paciente 2");
		
		raiz.add(paciente2);
		

		//JTree tree = new JTree(raiz);
		tree = usuarios.get(posPersona).cargarJTree(usuarios);
		
		tree.getSelectionModel().addTreeSelectionListener(this);

		panel.add(tree);
		panel.setViewportView(tree);
		
		panelCentral = new JPanel();
		splitPane.setRightComponent(panelCentral);
		panelCentral.setLayout(new BorderLayout(0, 0));
		
		panelBusqueda = new JTextField();
		panelCentral.add(panelBusqueda, BorderLayout.NORTH);
		panelBusqueda.setColumns(10);
		
//		panelDatos = new JPanel(new BorderLayout());
		

		panelDatos = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
	            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		
		////////////////panelDatos.getViewport().setLayout(new BorderLayout());
		

		//panelDatos.getViewport().setBackground(Color.BLACK);
		panelCentral.add(panelDatos, BorderLayout.CENTER);
		
		//panelDatos.setBounds(panelDatos.getBounds());
		//scrollPane.set
		
		JScrollPane scrollPaneProc =  new JScrollPane();
		
		
		
		
		JPanel panelBotonera = new JPanel();
		contentPane.add(panelBotonera, BorderLayout.SOUTH);
		
		JButton btnCrear = new JButton("Crear");
		panelBotonera.add(btnCrear);
		
		JButton btnModificar = new JButton("Modificar");
		panelBotonera.add(btnModificar);

		
		JButton btnEliminar = new JButton("Eliminar");
		panelBotonera.add(btnEliminar);

		
	}
	/**
	 * Clase que genera un panel con la informacion medica de cada usuario
	 * falta perfeccionar el voxlayout
	 * @author Suhar
	 *
	 */
	class InfoUsuario extends JPanel{
		
		
		public InfoUsuario(Usuario u) {
			
			//setLayout(new FlowLayout());
			setBorder(new TitledBorder(null, "Datos usuario", TitledBorder.LEADING, TitledBorder.TOP, null, null));


			setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

			
			JLabel rotuloNombre = new JLabel("Nombre: ");
			
			JLabel contenidoNombre =  new JLabel(u.getNombre());
			
			JLabel rotuloApellido = new JLabel("Apellido: ");
			
			JLabel contenidoApellido =  new JLabel(u.getApellido());
			
			JLabel rotuloDNI = new JLabel("DNI: ");
			
			JLabel contenidoDNI =  new JLabel(u.getDni());

			
			Box cajaH1 = Box.createHorizontalBox();
			
			Box cajaH11 =  Box.createHorizontalBox();
			
			Box cajaH12 =  Box.createHorizontalBox();
			
			Box cajaV1 =  Box.createVerticalBox();
			
			Box cajaH2 = Box.createHorizontalBox();
			
			
			
//
//			cajaV1.add(rotuloNombre);
//			cajaV1.add(Box.createHorizontalStrut(20));
//			cajaV1.add(contenidoNombre);
//			
//			cajaV2.add(rotuloApellido);
//			cajaV2.add(Box.createHorizontalStrut(20));
//			cajaV2.add(contenidoApellido);
//			
//			cajaH1.add(Box.createHorizontalGlue());
//			cajaH1.add(cajaV1);			
//			cajaH1.add(Box.createHorizontalGlue());
//			cajaH1.add(cajaV2);	
//			cajaH1.add(Box.createHorizontalGlue());
//			
//			cajaH2.add(rotuloDNI);			
//			cajaH2.add(Box.createHorizontalGlue());
//			cajaH2.add(contenidoDNI);	
//			
//			
//			
//
//			setBackground(Color.WHITE);
//			cajaV3.add(cajaH1);
//			cajaV3.add(cajaH2);
////			add(cajaV3, BorderLayout.CENTER);
//			add(cajaV3);


			setVisible(true);
			
			
			
		}
	}





	@Override
	public void valueChanged(TreeSelectionEvent e) {
		
		 DefaultMutableTreeNode nodoSeleccionado = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
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
//			panelDatos.removeAll();
//			panelDatos.invalidate();
//			JPanel j = usuariosMapNombre.get(nodo.getUserObject()).devuelvePanelInformación();
//			panelDatos.add(j);
//
//			panelDatos.revalidate();
//			//panelDatos.getViewport().setBackground(Color.BLACK);

			panelDatos.getViewport().removeAll();
			panelDatos.getViewport().invalidate();
			JPanel j = usuariosMapNombre.get(nodo.getUserObject()).devuelvePanelInformación();
			panelDatos.add(j);
			panelDatos.setViewportView(j);
			panelDatos.getViewport().getView().setBounds(panelDatos.getBounds());

			panelDatos.getViewport().revalidate();
			panelDatos.getViewport().setBackground(Color.BLACK);
		  System.out.println("Selecciona : "+nodo.toString());
		}
	}


}
