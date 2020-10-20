package proyecto.ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/** Ventana de logueo de la aplicación
 * @author Suhar Txabarri Aurrekoetxea
 * Facultad de Ingeniería - Universidad de Deusto
 */

public class VentanaLogin extends JFrame {
	
	// Varible de ventana de logueo de la clase
    private JPanel contenido;
    static Thread hiloActual;

    public VentanaLogin() {

    	// Creación de componentes/contenedores de swing
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle( "Ventana Login" );
        setSize( 800, 600 );
        setLocationRelativeTo( null );
        ImageIcon icon = new ImageIcon("src\\imagenes\\osakidetza.png");
        setIconImage(icon.getImage());
			
        
        contenido = new JPanel();
        contenido.setBorder(new EmptyBorder(5, 5, 5, 5));
        contenido.setLayout(new BorderLayout(0, 0));
        setContentPane(contenido);
        
        contenido.setLayout(null);
        
        JLabel userLabel = new JLabel("Usuario:");
		userLabel.setBounds(50, 100, 80, 25);
		contenido.add(userLabel);
		
		JTextField userText = new JTextField(20);
		userText.setBounds(140, 100, 160, 25);
		contenido.add(userText);
		
		JLabel passwordLabel = new JLabel("Contraseña:");
		passwordLabel.setBounds(50, 150, 80, 25);
		contenido.add(passwordLabel);

		JPasswordField passwordText = new JPasswordField(20);
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
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
		//		JOptionPane.showMessageDialog(null, "login button has been pressed");
				//hiloActual.interrupt();
			}
		});
		
    }
    
    
    
    
    public static void main(String[] args) {
    	
        VentanaLogin frame = new VentanaLogin();
        frame.setVisible(true);
//    	hiloActual = new Thread() {
//            public void run() {
//                try {
//                    VentanaLogin frame = new VentanaLogin();
//                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//    	};

//    	hiloActual.start();
    	}
    
}


