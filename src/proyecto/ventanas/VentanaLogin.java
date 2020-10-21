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
    
    private JTextField userText;
    private JPasswordField passwordText;
    
    

    public VentanaLogin() {

    	// Creación de componentes/contenedores de swing
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
		
		JLabel passwordLabel = new JLabel("Contraseña:");
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


				loginButtonActionPerformed(evt);
				
			}
		});
		
    }
    
    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String usuario = userText.getText();
        String paswd = passwordText.getText();
        
        if(usuario.isEmpty() || paswd.isEmpty()){
            JOptionPane.showMessageDialog(null, "AlgÃºn campo esta vacio");
            
        }else{
             if(usuario.equals("usuario1") && paswd.equals("1234")){
                 JOptionPane.showMessageDialog(null,"Bienvenido");
                 VentanaMedico pc = new VentanaMedico();
                 pc.setVisible(true);
                 this.dispose();
                 
             }else{
                 JOptionPane.showConfirmDialog(null,"Su usuario o contraseÃ±a es incorrecto");
             }
        }
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


