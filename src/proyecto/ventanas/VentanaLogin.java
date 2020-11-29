package proyecto.ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import proyecto.basededatos.DatosUsuariosBD;
import proyecto.usuarios.Enfermero;
import proyecto.usuarios.Medico;
import proyecto.usuarios.Usuario;

/** Ventana de logueo de la aplicaci�n
 * @author Suhar Txabarri Aurrekoetxea
 * Facultad de Ingenier�a - Universidad de Deusto
 */



public class VentanaLogin extends JFrame {
	
	// Varible de ventana de logueo de la clase
    private JPanel contenido;
    static Thread hiloActual;
    
    private JTextField userText;
    private JPasswordField passwordText;
    ArrayList<Usuario> usuarios;

    public VentanaLogin(ArrayList<Usuario> usuarios) {
    	
    	this.usuarios=usuarios;
    	// Creaci�n de componentes/contenedores de swing
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle( "Ventana Login" );
        setSize( 400, 350 );
        setLocationRelativeTo( null );
        ImageIcon icon = new ImageIcon("src\\imagenes\\osakidetza.png");
        setIconImage(icon.getImage());
			
        
        contenido = new JPanel();
        contenido.setBorder(new EmptyBorder(5, 5, 5, 5));
        contenido.setLayout(new BorderLayout(0, 0));
        contenido.setBackground(new java.awt.Color(1, 116, 255));
        contenido.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acceder", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
        		javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Narrow", 0, 25), new java.awt.Color(255, 255, 255)));
        setContentPane(contenido);
        
        contenido.setLayout(null);
        
        JLabel userLabel = new JLabel("Usuario:");
		userLabel.setBounds(50, 100, 80, 25);
		//userLabel.setBounds((int)(getBounds().width*0.2),(int)(getBounds().height*0.3),80, 25);//mirar como crear contenido dinamico
		contenido.add(userLabel);
		
		userText = new JTextField(20);
		userText.setBounds(140, 100, 160, 25);
		contenido.add(userText);
		
		JLabel passwordLabel = new JLabel("Contrasenya:");
		passwordLabel.setBounds(50, 150, 80, 25);
		contenido.add(passwordLabel);

		passwordText = new JPasswordField(20);
		passwordText.setBounds(140, 150, 160, 25);
		contenido.add(passwordText);
		
		JButton loginButton = new JButton("Acceder");
		loginButton.setBounds(50, 190, 90, 25);
		contenido.add(loginButton);
		
		JButton cerrarButton = new JButton("Cerrar");
		cerrarButton.setBounds(160, 190, 90, 25);
		contenido.add(cerrarButton);
		
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evt) {

				loguearse();
				
			}
		});
		cerrarButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evt) {
				System.exit(0);
				
			}
		});
		
		passwordText.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {

				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					loguearse();
				} 
				
			}
		});


		
    }
    
    private void loguearse() {
        String usuario = userText.getText();
        String paswd = passwordText.getText();
		boolean encontrado = false;
		System.out.println(usuarios.size());
        
        if(usuario.isEmpty() || paswd.isEmpty()){
            JOptionPane.showMessageDialog(null, "Algún campo esta vacio");

        }else{

        	for(int i = 0; i<usuarios.size(); i++){
        		System.out.println(usuarios.get(i));
        		if((usuario.equals((usuarios.get(i).getNombre()))|| usuario.equals((usuarios.get(i).getNombre()).toLowerCase() )&& paswd.equals(usuarios.get(i).getContrasenya()))){
        		
            		encontrado = true;

	                if(usuarios.get(i) instanceof Medico) {
	                	
		                JOptionPane.showMessageDialog(null,"Bienvenido Doctor/a " + usuarios.get(i).getApellido());
		                VentanaMedico pc = new VentanaMedico(usuarios, i);
		                pc.setVisible(true);
		                this.dispose();

		                
	                }else if(usuarios.get(i) instanceof Enfermero) {
	                	
		                JOptionPane.showMessageDialog(null,"Bienvenido Enfermero/a " + usuarios.get(i).getApellido());
		                VentanaEnfermero pc = new VentanaEnfermero(usuarios, i);
		                pc.setVisible(true);
		                this.dispose();

		                
	                }else {
	                	
		                JOptionPane.showMessageDialog(null,"Bienvenido Señor/a " + usuarios.get(i).getApellido());
		                VentanaPaciente pc = new VentanaPaciente(usuarios, i);
		                pc.setVisible(true);
		                this.dispose();

	                }
        		}
        	}

			if(encontrado == false){
				JOptionPane.showMessageDialog(null,"Su usuario o contraseña es incorrecto");
			}
        }
    }
}


