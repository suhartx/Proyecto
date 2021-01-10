package proyecto.ventanas;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;


import proyecto.usuarios.Usuario;

/**
 * Clase que devuelve unpanel de informacion de usuarios
 * @author Suhar
 *
 */
public class PanelInfoUsuarios extends JPanel{
	
//	String[] keys = {"Nombre: ", "Apellidos: ", "DNI: ", "sexo: ",
//	  "Peso: ", "Altura: ", "Alergias: ",
//	  "Colesterol: ", "Tensión: ", "Enfermedades: ", "Tipo de sangre: ", 
//	  "Medico de cabecera: "};	
	
	private JLabel nombreInfo;
	
	private JLabel apeInfo;
	
	private JLabel dniInfo;
	
	private JLabel sexoInfo;
	
	private JLabel pesoInfo;
	
	private JLabel alturaInfo;
	
	private JLabel alergiasInfo;
	
	private JLabel colesterolInfo;
	
	private JLabel tensionInfo;
	
	private JLabel enfermedadesInfo;
	
	private JLabel tipoSInfo;
	
	private JLabel medicoInfo;

	public PanelInfoUsuarios() {

		super(new GridLayout(6,2));
		
		Border border = BorderFactory.createTitledBorder("Informacion");
		setBorder(border);
		
		JPanel nombrePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel nombreLabel = new JLabel("Nombre: ");
		nombreInfo = new JLabel();
		
		nombrePanel.add(nombreLabel);
		nombrePanel.add(nombreInfo);
		
		add(nombrePanel);
		
		JPanel apePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel apeLabel = new JLabel("Apellidos: ");
		apeInfo = new JLabel();
		
		apePanel.add(apeLabel);
		apePanel.add(apeInfo);
		
		add(apePanel);
		
		
		JPanel dniPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel dniLabel = new JLabel("DNI: ");
		dniInfo = new JLabel();
		
		dniPanel.add(dniLabel);
		dniPanel.add(dniInfo);
		
		add(dniPanel);
		
		
		JPanel sexoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel sexoLabel = new JLabel("Sexo: ");
		sexoInfo = new JLabel();
		
		sexoPanel.add(sexoLabel);
		sexoPanel.add(sexoInfo);
		
		add(sexoPanel);
		
		JPanel pesoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pesoLabel = new JLabel("Peso: ");
		pesoInfo = new JLabel();
		
		pesoPanel.add(pesoLabel);
		pesoPanel.add(pesoInfo);
		
		add(pesoPanel);		
		
		JPanel alturaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel alturaLabel = new JLabel("Altura: ");
		alturaInfo = new JLabel();
		
		alturaPanel.add(alturaLabel);
		alturaPanel.add(alturaInfo);
		
		add(alturaPanel);		
		
		
		JPanel colesterolPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel colesterolLabel = new JLabel("Colesterol: ");
		colesterolInfo = new JLabel();
		
		colesterolPanel.add(colesterolLabel);
		colesterolPanel.add(colesterolInfo);
		
		add(colesterolPanel);		
		
		JPanel tensionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel tensionLabel = new JLabel("Tensión: ");
		tensionInfo = new JLabel();
		
		tensionPanel.add(tensionLabel);
		tensionPanel.add(tensionInfo);
		
		add(tensionPanel);		
		
		
		JPanel tipoSPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel tipoSLabel = new JLabel("Tipo de sangre: ");
		tipoSInfo = new JLabel();
		
		tipoSPanel.add(tipoSLabel);
		tipoSPanel.add(tipoSInfo);
		
		add(tipoSPanel);		
		
		JPanel medicoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel medicoLabel = new JLabel("Medico de cabecera: ");
		medicoInfo = new JLabel();
		
		medicoPanel.add(medicoLabel);
		medicoPanel.add(medicoInfo);
		
		add(medicoPanel);		
		
		
		JPanel enfermedadesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel enfermedadesLabel = new JLabel("Enfermedades: ");
		enfermedadesInfo = new JLabel();
		
		enfermedadesPanel.add(enfermedadesLabel);
		enfermedadesPanel.add(enfermedadesInfo);
		
		add(enfermedadesPanel);		
		
		
		JPanel alergiasPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel alergiasLabel = new JLabel("Alergias: ");
		alergiasInfo = new JLabel();
		
		alergiasPanel.add(alergiasLabel);
		alergiasPanel.add(alergiasInfo);
		
		add(alergiasPanel);			
		
		
//////////////////////
		
//			
//
//			  String[] values = {nombre , apellido, dni, Character.toString(sexo).toUpperCase(),
//	                    Float.toString(peso)+ " kg", Integer.toString(altura)+" cm", Alergias.replace("-", ", "), Integer.toString(colesterol),
//	                    Integer.toString(tension), enfermedades.replace("-", ", "), tipoSangre, medicoCabecera.getNombre()+" "+ medicoCabecera.getApellido()};
//
//			JPanel panel = new JPanel(new FlowLayout());
//			GridBagConstraints gbc;
//			JLabel valor = null;
//			int maxWidth = 0;
//			
//			JLabel[] labels = new JLabel[keys.length];
//			
//		    for (int i = 0; i < keys.length; i++)
//		    {
//		      labels[i] = new JLabel(keys[i]);
//		      maxWidth = Math.max(labels[i].getPreferredSize().width, maxWidth);
//		    }
//
//		    JPanel[] panels = new JPanel[keys.length];
//
//		    for (int i = 0; i < keys.length; i++)
//		    {
//		      panels[i] = new JPanel(new GridBagLayout());
//
//		      gbc = new GridBagConstraints();
//		      gbc.gridx = 0;
//		      gbc.gridy = 0;
//		      gbc.anchor = GridBagConstraints.LINE_START;
//		      gbc.insets = new Insets(1,1,1,1);
//		      panels[i].add(Box.createHorizontalStrut(maxWidth), gbc);
//
//		      gbc.gridy = 1;
//		      panels[i].add(labels[i], gbc);
//
//		      valor = new JLabel(values[i]);
//
//		      gbc.gridx = 1;
//		      panels[i].add(valor, gbc);
//
//		      panel.add(panels[i]);
//		    }
//		    panel.setPreferredSize(new Dimension(300, 300));
//			return panel;
//			
//		
	}
	
	public void setInfoUsuario(Usuario u) {
		
		//System.out.println("esto funciona");
		nombreInfo.setText(u.getNombre());
		apeInfo.setText(u.getApellido());
		dniInfo.setText(u.getDni());
		sexoInfo.setText(String.valueOf(u.getSexo()));
		pesoInfo.setText(String.valueOf(u.getPeso()));
		alturaInfo.setText(String.valueOf(u.getAltura()));
		alergiasInfo.setText(String.valueOf(u.getAlergias()).replace("-", ", "));
		colesterolInfo.setText(String.valueOf(u.getColesterol()));
		tensionInfo.setText(String.valueOf(u.getTension()));
		enfermedadesInfo.setText(String.valueOf(u.getEnfermedades()).replace("-", ", "));
		tipoSInfo.setText(String.valueOf(u.getTipoSangre()));
		medicoInfo.setText(String.valueOf(u.getMedicoCabecera().getNombre()+" " + u.getMedicoCabecera().getApellido()));
	}

	public void clear() {
		nombreInfo.setText("");
		apeInfo.setText("");
		dniInfo.setText("");
		sexoInfo.setText("");
		pesoInfo.setText("");
		alturaInfo.setText("");
		alergiasInfo.setText("");
		colesterolInfo.setText("");
		tensionInfo.setText("");
		enfermedadesInfo.setText("");
		tipoSInfo.setText(String.valueOf(""));
		medicoInfo.setText(String.valueOf(""));
	}
}
