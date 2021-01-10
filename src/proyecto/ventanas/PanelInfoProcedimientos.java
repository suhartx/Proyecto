package proyecto.ventanas;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import proyecto.contenido.Cita;
import proyecto.contenido.Prueba;
import proyecto.contenido.Tratamiento;
import proyecto.usuarios.Usuario;



public class PanelInfoProcedimientos extends JPanel{
	
	BoxLayout layoutPrincipal;
	
	JLabel codInfo;
	
	JLabel tituloInfo;
	
	JLabel fechaInfo;
	
	JLabel horaInfo;
	

	public PanelInfoProcedimientos() {

		
	}
	
	public void RellenarDeCitas(Usuario u) {
		
		layoutPrincipal = new BoxLayout(this, BoxLayout.Y_AXIS);
		
		setLayout(layoutPrincipal);

		Iterator<Cita> iterador = u.getCitasOrdenadas().iterator();

	       while (iterador.hasNext()) { 
	    	   
	    	   	Cita c = iterador.next();

				JPanel p = new JPanel();
				p.setLayout(new GridLayout(2, 2));
				
				Border border = BorderFactory.createTitledBorder("Informacion");
				p.setBorder(border);

				JPanel codPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
				JLabel codLabel = new JLabel("Codigo de cita: ");
				codInfo = new JLabel();
				
				codPanel.add(codLabel);
				codPanel.add(codInfo);
				
				p.add(codPanel);
				
				codInfo.setText(String.valueOf(c.getCodCita()));
				
				JPanel tituloPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
				JLabel tituloLabel = new JLabel("Titulo: ");
				tituloInfo = new JLabel();
				
				tituloPanel.add(tituloLabel);
				tituloPanel.add(tituloInfo);
				
				p.add(tituloPanel);
				
				tituloInfo.setText(c.getTitulo());
				
				
				JPanel fechaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
				JLabel fechaLabel = new JLabel("Fecha: ");
				fechaInfo = new JLabel();
				
				fechaPanel.add(fechaLabel);
				fechaPanel.add(fechaInfo);
				
				p.add(fechaPanel);
				
				String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(c.getFecha());
				
				fechaInfo.setText(modifiedDate);
				
				
				JPanel horaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
				JLabel horaLabel = new JLabel("Hora: ");
				horaInfo = new JLabel();
				
				horaPanel.add(horaLabel);
				horaPanel.add(horaInfo);
				
				p.add(horaPanel);

				horaInfo.setText(c.getHora().toString().substring(0, 5));
				
				add(p);
		}
	}
	
	
	
	public void limpiarInterior() {
		
		removeAll();
	}
	
	public void RellenarDeTReatamentos(Usuario u) {
		
		
		layoutPrincipal = new BoxLayout(this, BoxLayout.Y_AXIS);
		
		setLayout(layoutPrincipal);

		Iterator<Tratamiento> iterador = u.getTratamientosOrdenados().iterator();

	       while (iterador.hasNext()) { 
	    	   
	    	   	Tratamiento c = iterador.next();

				JPanel p = new JPanel();
				p.setLayout(new GridLayout(2, 2));
				
				Border border = BorderFactory.createTitledBorder("Informacion");
				p.setBorder(border);

				JPanel codPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
				JLabel codLabel = new JLabel("Codigo de cita: ");
				codInfo = new JLabel();
				
				codPanel.add(codLabel);
				codPanel.add(codInfo);
				
				p.add(codPanel);
				
				codInfo.setText(String.valueOf(c.getCodtratamiento()));
				
				JPanel tituloPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
				JLabel tituloLabel = new JLabel("Titulo: ");
				tituloInfo = new JLabel();
				
				tituloPanel.add(tituloLabel);
				tituloPanel.add(tituloInfo);
				
				p.add(tituloPanel);
				
				tituloInfo.setText(c.getTitulo());
				
				
				JPanel fechaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
				JLabel fechaLabel = new JLabel("Fecha: ");
				fechaInfo = new JLabel();
				
				fechaPanel.add(fechaLabel);
				fechaPanel.add(fechaInfo);
				
				p.add(fechaPanel);
				
				String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(c.getFecha());
				
				fechaInfo.setText(modifiedDate);
				
				
				JPanel horaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
				JLabel horaLabel = new JLabel("Hora: ");
				horaInfo = new JLabel();
				
				horaPanel.add(horaLabel);
				horaPanel.add(horaInfo);
				
				p.add(horaPanel);

				horaInfo.setText(c.getHora().toString().substring(0, 5));
				
				add(p);
		}
	}
	
	public void RellenarDePruebas(Usuario u) {
		
		layoutPrincipal = new BoxLayout(this, BoxLayout.Y_AXIS);
		
		setLayout(layoutPrincipal);

		Iterator<Prueba> iterador = u.getPruebasOrdenadas().iterator();

	       while (iterador.hasNext()) { 
	    	   
	    	   Prueba c = iterador.next();

				JPanel p = new JPanel();
				p.setLayout(new GridLayout(2, 2));
				
				Border border = BorderFactory.createTitledBorder("Informacion");
				p.setBorder(border);

				JPanel codPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
				JLabel codLabel = new JLabel("Codigo de cita: ");
				codInfo = new JLabel();
				
				codPanel.add(codLabel);
				codPanel.add(codInfo);
				
				p.add(codPanel);
				
				codInfo.setText(String.valueOf(c.getCodPrueba()));
				
				JPanel tituloPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
				JLabel tituloLabel = new JLabel("Titulo: ");
				tituloInfo = new JLabel();
				
				tituloPanel.add(tituloLabel);
				tituloPanel.add(tituloInfo);
				
				p.add(tituloPanel);
				
				tituloInfo.setText(c.getTitulo());
				
				
				JPanel fechaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
				JLabel fechaLabel = new JLabel("Fecha: ");
				fechaInfo = new JLabel();
				
				fechaPanel.add(fechaLabel);
				fechaPanel.add(fechaInfo);
				
				p.add(fechaPanel);
				
				String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(c.getFecha());
				
				fechaInfo.setText(modifiedDate);
				
				
				JPanel horaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
				JLabel horaLabel = new JLabel("Hora: ");
				horaInfo = new JLabel();
				
				horaPanel.add(horaLabel);
				horaPanel.add(horaInfo);
				
				p.add(horaPanel);

				horaInfo.setText(c.getHora().toString().substring(0, 5));
				
				add(p);
		}
	}	
	


}
