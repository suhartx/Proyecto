package proyecto.ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import proyecto.usuarios.Usuario;

import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.JMenu;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class VentanaEnfermero extends JFrame {

	private JPanel contentPane;
	private JTextField panelBusqueda;
    static ArrayList<Usuario> usuarios;
    static int posPersona;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEnfermero frame = new VentanaEnfermero(usuarios, posPersona);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaEnfermero(ArrayList<Usuario> usuarios, int posPersona) {
		this.usuarios=usuarios;
		setTitle("Ventana principal enfermero "+usuarios.get(posPersona).getNombre());
		setIconImage(Toolkit.getDefaultToolkit().getImage("src\\imagenes\\osakidetza.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.2), (int) (Toolkit.getDefaultToolkit().getScreenSize().height*0.15),//establezco el tama�o de la ventana adapado para
				(int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.6), (int) (Toolkit.getDefaultToolkit().getScreenSize().height*0.6));//la pantalla de diferentes PCs
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelIzquierdo = new JPanel();
		contentPane.add(panelIzquierdo, BorderLayout.WEST);
		panelIzquierdo.setLayout(new BorderLayout(0, 0));
		
		JTree tree = new JTree();
		panelIzquierdo.add(tree, BorderLayout.CENTER);
		
		JMenu mnMenu = new JMenu("Menu");
		panelIzquierdo.add(mnMenu, BorderLayout.NORTH);
		
		
		
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
