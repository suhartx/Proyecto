package proyecto.ventanas;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelInfoUsuarios {
	
	String[] keys = {"Nombre: ", "Apellidos: ", "DNI: ", "sexo: ",
	  "Peso: ", "Altura: ", "Alergias: ",
	  "Colesterol: ", "Tensión: ", "Enfermedades: ", "Tipo de sangre: ", 
	  "Medico de cabecera: "};	

	public PanelInfoUsuarios() {
		// TODO Auto-generated constructor stub

			

			  String[] values = {nombre , apellido, dni, Character.toString(sexo).toUpperCase(),
	                    Float.toString(peso)+ " kg", Integer.toString(altura)+" cm", Alergias.replace("-", ", "), Integer.toString(colesterol),
	                    Integer.toString(tension), enfermedades.replace("-", ", "), tipoSangre, medicoCabecera.getNombre()+" "+ medicoCabecera.getApellido()};

			JPanel panel = new JPanel(new FlowLayout());
			GridBagConstraints gbc;
			JLabel valor = null;
			int maxWidth = 0;
			
			JLabel[] labels = new JLabel[keys.length];
			
		    for (int i = 0; i < keys.length; i++)
		    {
		      labels[i] = new JLabel(keys[i]);
		      maxWidth = Math.max(labels[i].getPreferredSize().width, maxWidth);
		    }

		    JPanel[] panels = new JPanel[keys.length];

		    for (int i = 0; i < keys.length; i++)
		    {
		      panels[i] = new JPanel(new GridBagLayout());

		      gbc = new GridBagConstraints();
		      gbc.gridx = 0;
		      gbc.gridy = 0;
		      gbc.anchor = GridBagConstraints.LINE_START;
		      gbc.insets = new Insets(1,1,1,1);
		      panels[i].add(Box.createHorizontalStrut(maxWidth), gbc);

		      gbc.gridy = 1;
		      panels[i].add(labels[i], gbc);

		      valor = new JLabel(values[i]);

		      gbc.gridx = 1;
		      panels[i].add(valor, gbc);

		      panel.add(panels[i]);
		    }
		    panel.setPreferredSize(new Dimension(300, 300));
			return panel;
			
		
	}

}
