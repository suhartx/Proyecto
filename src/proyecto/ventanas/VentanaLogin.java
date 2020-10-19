package proyecto.ventanas;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/** Ventana de logueo de la aplicación
 * @author Suhar Txabarri Aurrekoetxea
 * Facultad de Ingeniería - Universidad de Deusto
 */

public class VentanaLogin extends JFrame {
	
	// Varible de ventana de logueo de la clase
    private JPanel contenido;



    public VentanaLogin() {

    	// Creación de componentes/contenedores de swing
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle( "Ventana Login" );
        setSize( 800, 600 );
        setLocationRelativeTo( null );
        
        contenido = new JPanel();
        contenido.setBorder(new EmptyBorder(5, 5, 5, 5));
        contenido.setLayout(new BorderLayout(0, 0));
        setContentPane(contenido);
        
        contenido.setLayout(null);
        
        JLabel userLabel = new JLabel("User");
		userLabel.setBounds(50, 100, 80, 25);
		contenido.add(userLabel);
    }
    
    
    
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaLogin frame = new VentanaLogin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
