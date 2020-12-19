package proyecto.ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.ArrayList;

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

import proyecto.usuarios.Usuario;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField panelBusqueda;
    static ArrayList<Usuario> usuarios;
    static int posPersona;



	/**
	 * Create the frame.
	 */
	public VentanaPrincipal(ArrayList<Usuario> usuarios, int posPersona) {
		this.usuarios=usuarios;
		setTitle("Ventana principal "+usuarios.get(posPersona).getClass().getSimpleName()+" "+usuarios.get(posPersona).getNombre());
		setIconImage(Toolkit.getDefaultToolkit().getImage("src\\imagenes\\osakidetza.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.2), (int) (Toolkit.getDefaultToolkit().getScreenSize().height*0.15),//establezco el tama�o de la ventana adapado para
				(int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.6), (int) (Toolkit.getDefaultToolkit().getScreenSize().height*0.6));//la pantalla de diferentes PCs
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu Test");
		menuBar.add(menu);
		setJMenuBar(menuBar);
		
		JPanel panelIzquierdo = new JPanel();
		panelIzquierdo.setBorder(new TitledBorder(null, "Menu usuarios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panelIzquierdo, BorderLayout.WEST);
		panelIzquierdo.setLayout(new BorderLayout(0, 0));
		
		JScrollPane panel = new JScrollPane();
		panelIzquierdo.add(panel, BorderLayout.CENTER);
		
		JTree tree = new JTree();
		panel.add(tree);
		panel.setViewportView(tree);
		
		
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));
		
		panelBusqueda = new JTextField();
		panelCentral.add(panelBusqueda, BorderLayout.NORTH);
		panelBusqueda.setColumns(10);
		
		JTextPane textPane = new JTextPane();
		panelCentral.add(textPane, BorderLayout.CENTER);
		
		JPanel panelBotonera = new JPanel();
		contentPane.add(panelBotonera, BorderLayout.SOUTH);
		
		JButton btnCrear = new JButton("Crear");
		panelBotonera.add(btnCrear);
		
		JButton btnModificar = new JButton("Modificar");
		panelBotonera.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		panelBotonera.add(btnEliminar);
		
		
	}

}
