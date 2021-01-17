package proyecto.ventanas;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.Border;

import com.sun.java.swing.plaf.motif.MotifBorders.BevelBorder;

import proyecto.contenido.Cita;
import proyecto.contenido.Medicamento;
import proyecto.contenido.Prueba;
import proyecto.contenido.Tratamiento;
import proyecto.usuarios.Usuario;

public class PanelInformeProcedimiento extends JPanel{

	LayoutManager layoutPrincipal;
	
	JLabel codInfo;
	
	JLabel tituloInfo;
	
	JLabel fechaInfo;
	
	JLabel horaInfo;
	
	JLabel ambitoInfo;
	
	JLabel descInfo;
	
	JLabel sanitInfo;


	
	

	

	public PanelInformeProcedimiento() {
		
		JScrollBar b = new JScrollBar();

		
	}
	public void informeCitas(Usuario u, Cita c) {
		
		layoutPrincipal = new BoxLayout(this, BoxLayout.Y_AXIS);
		
		setLayout(layoutPrincipal);
		
		Border border = BorderFactory.createTitledBorder("Informe de cita " + u.getNombre()+" " + u.getApellido());
		setBorder(border);
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3, 2));

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
		
		JPanel ambitoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel ambitoLabel = new JLabel("Ambito: ");
		ambitoInfo= new JLabel();
		
		ambitoPanel.add(ambitoLabel);
		ambitoPanel.add(ambitoInfo);
		
		p.add(ambitoPanel);

		ambitoInfo.setText(c.getAmbito());
		
		
		JPanel medPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel medLabel = new JLabel("Sanitario asociado: ");
		sanitInfo= new JLabel();
		
		medPanel.add(medLabel);
		medPanel.add(sanitInfo);
		
		p.add(medPanel);
		
		add(p);

		sanitInfo.setText(c.getSanitarioAsociado().getNombre() +" " + c.getSanitarioAsociado().getApellido());
		
		JPanel desc = new JPanel();
		desc.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel descLabel = new JLabel("Sanitario asociado: ");
		
		desc.add(descLabel);
		
		descLabel.setText("Descripcion: \n" + c.getDescripcion());

		add(desc);
	
	}
	public void informePruebas(Usuario u, Prueba c) {
		
		layoutPrincipal = new BoxLayout(this, BoxLayout.Y_AXIS);
		
		setLayout(layoutPrincipal);
		
		Border border = BorderFactory.createTitledBorder("Informe de prueba " + u.getNombre()+" " + u.getApellido());
		setBorder(border);
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3, 2));
		
		JPanel codPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel codLabel = new JLabel("Codigo de prueba: ");
		JLabel codInfo = new JLabel();
		
		codPanel.add(codLabel);
		codPanel.add(codInfo);
		
		p.add(codPanel);
		
		
		
		codInfo.setText(String.valueOf(c.getCodPrueba()));
		
		JPanel tituloPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel tituloLabel = new JLabel("Titulo: ");
		JLabel tituloInfo = new JLabel();
		
		tituloPanel.add(tituloLabel);
		tituloPanel.add(tituloInfo);
		
		p.add(tituloPanel);
		
		tituloInfo.setText(c.getTitulo());
		
		
		JPanel fechaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel fechaLabel = new JLabel("Fecha: ");
		JLabel fechaInfo = new JLabel();
		
		fechaPanel.add(fechaLabel);
		fechaPanel.add(fechaInfo);
		
		p.add(fechaPanel);
		
		String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(c.getFecha());
		
		fechaInfo.setText(modifiedDate);
		
		
		JPanel horaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel horaLabel = new JLabel("Hora: ");
		JLabel horaInfo = new JLabel();
		
		horaPanel.add(horaLabel);
		horaPanel.add(horaInfo);
		
		p.add(horaPanel);

		horaInfo.setText(c.getHora().toString().substring(0, 5));
		
		JPanel ambitoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel ambitoLabel = new JLabel("Ambito: ");
		JLabel ambitoInfo= new JLabel();
		
		ambitoPanel.add(ambitoLabel);
		ambitoPanel.add(ambitoInfo);
		
		p.add(ambitoPanel);

		ambitoInfo.setText(c.getAmbito());
		
		
		JPanel medPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel medLabel = new JLabel("Sanitario asociado: ");
		JLabel sanitInfo= new JLabel();
		
		medPanel.add(medLabel);
		medPanel.add(sanitInfo);
		
		p.add(medPanel);
		
		add(p);

		sanitInfo.setText(c.getSanitarioAsociado().getNombre() +" " + c.getSanitarioAsociado().getApellido());
		
		JPanel desc = new JPanel();
		desc.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel descLabel = new JLabel("Sanitario asociado: ");
		
		desc.add(descLabel);
		
		descLabel.setText("Descripcion: \n" + c.getDescripcion());

		add(desc);
	
	}
	public void informeTratamientos(Usuario u, Tratamiento c) {
		
		layoutPrincipal = new BoxLayout(this, BoxLayout.Y_AXIS);
		
		setLayout(layoutPrincipal);
		
		
		
		Border border = BorderFactory.createTitledBorder("Informe de cita " + u.getNombre()+" " + u.getApellido());
		setBorder(border);
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3, 2));

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
		
		JPanel ambitoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel ambitoLabel = new JLabel("Ambito: ");
		ambitoInfo= new JLabel();
		
		ambitoPanel.add(ambitoLabel);
		ambitoPanel.add(ambitoInfo);
		
		p.add(ambitoPanel);

		ambitoInfo.setText(c.getAmbito());
		
		
		JPanel medPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel medLabel = new JLabel("Sanitario asociado: ");
		sanitInfo= new JLabel();
		
		medPanel.add(medLabel);
		medPanel.add(sanitInfo);
		
		p.add(medPanel);
		
		add(p);

		sanitInfo.setText(c.getMedicoAsociado().getNombre() +" " + c.getMedicoAsociado().getApellido());
		
		JPanel desc = new JPanel();
		desc.setLayout(new FlowLayout(FlowLayout.LEFT));
		JTextArea descLabel = new JTextArea(2, 50);
		
		desc.add(descLabel);
		descLabel.setWrapStyleWord(true);
		descLabel.setLineWrap(true);
		descLabel.setOpaque(false);
		descLabel.setEditable(false);
		descLabel.setFocusable(false);
		descLabel.setBackground(UIManager.getColor("Label.background"));
		descLabel.setFont(UIManager.getFont("Label.font"));
		descLabel.setBorder(UIManager.getBorder("Label.border"));
		
		descLabel.setText("Descripcion: " + c.getDescripcion());
		
		

		//String.format("<html><div WIDTH=%d>%s</div></html>", "Descripcion:<p>" + c.getDescripcion()+"<p>");
		
		add(desc);
		
		JPanel med =  new JPanel();
		FlowLayout experimentLayout = new FlowLayout(FlowLayout.LEFT);
		//experimentLayout.setAlignment(FlowLayout.TRAILING);
		med.setLayout(experimentLayout);
		
		
		
		p.setPreferredSize(new Dimension(300, 150));
		
		Border bordeM = BorderFactory.createTitledBorder("Medicamentos");
		med.setBorder(bordeM);
		
		
		
		Iterator<Medicamento> iterador = c.getMedicamentosSet().iterator();
		while (iterador.hasNext()){
			Medicamento m = iterador.next();
			
			JPanel pm = new JPanel();
			pm.setLayout(new GridLayout(3, 2));
			Border borde = BorderFactory.createTitledBorder("cantidad: " + m.getContador());
			pm.setBorder(borde);
			
			
			//////////////////////////////////////////
			
			JPanel codPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JLabel codLabel1 = new JLabel("Cod. med.: ");
			JLabel codInfo = new JLabel();
			
			codPanel1.add(codLabel1);
			codPanel1.add(codInfo);
			
			pm.add(codPanel1);
			
			codInfo.setText(String.valueOf(m.getCodMedicamento()));
			
			JPanel tituloPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JLabel tituloLabel1 = new JLabel("Tit.: ");
			JLabel tituloInfo = new JLabel();
			
			tituloPanel1.add(tituloLabel1);
			tituloPanel1.add(tituloInfo);
			
			pm.add(tituloPanel1);
			
			tituloInfo.setText(m.getTitulo());
			
			JPanel ambitoPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JLabel ambitoLabel1 = new JLabel("Amb.: ");
			JLabel ambitoInfo= new JLabel();
			
			ambitoPanel1.add(ambitoLabel1);
			ambitoPanel1.add(ambitoInfo);

			pm.add(ambitoPanel1);
			ambitoInfo.setText(m.getAmbito());	
			
			
			JPanel fechaPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JLabel fechaLabel1 = new JLabel("Fec. lanz.: ");
			JLabel fechaInfo = new JLabel();
			
			fechaPanel1.add(fechaLabel1);
			fechaPanel1.add(fechaInfo);
			
			pm.add(fechaPanel1);
			
			String modifiedDate1= new SimpleDateFormat("yyyy-MM-dd").format(m.getFechaLanzamiento());
			
			fechaInfo.setText(modifiedDate1);
			
			
			JPanel descPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JTextArea descInfor = new JTextArea(3, 20);



			descInfor.setWrapStyleWord(true);
			descInfor.setLineWrap(true);
			descInfor.setOpaque(false);
			descInfor.setEditable(false);
			descInfor.setFocusable(false);
			descInfor.setBackground(UIManager.getColor("Label.background"));
			descInfor.setFont(UIManager.getFont("Label.font"));
			descInfor.setBorder(UIManager.getBorder("Label.border"));
			
			descInfor.setText("Desc.:  "+m.getDescripcion());
			
			descPanel1.add(descInfor);
			
			
			pm.add(descPanel1);



			med.add(pm);
			
			
		}
		med.setPreferredSize(new Dimension(400,1000));
		//med.setMinimumSize(new Dimension(400,400));
		//med.setMaximumSize(new Dimension(400,2000 ));
		
		add(med);

	}
	


	
	
	public void limpiarInterior() {
		
		removeAll();
	}
	

}
