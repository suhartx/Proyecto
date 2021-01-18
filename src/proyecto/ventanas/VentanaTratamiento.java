package proyecto.ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import herramientas.DateLabelFormatter;
import proyecto.contenido.Medicamento;
import proyecto.usuarios.Usuario;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JSlider;
import java.awt.Dimension;
import java.awt.List;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.DefaultListModel;

public class VentanaTratamiento extends JFrame implements ListSelectionListener{


	private JPanel contenedor;
	private JScrollPane scroll;
	private JPanel panelDatos;
	private JPanel panelBotones;
	private JPanel panelTitulo;
	private JPanel panelAmbito;
	private JPanel panelFecha;
	private JPanel panelHora;
	private JPanel panelDesc;
	private JButton btnNewButton;
	private JButton btnAceptar;
	private JLabel labelTitulo;
	private JTextField textTitulo;
	private JLabel labelAmbito;
	private JTextField textAmbito;
	private JLabel labelFecha;
	//private JTextField textField_3;
	private JLabel labelHora;
	//private JTextField textField_4;
	private JLabel labelDesc;
	private JTextArea textArea;
	private JPanel panelMed;
	private DefaultListModel modelo;
	private JList list;
	private JButton btnAnyadir;
	private Component horizontalGlue;
	ArrayList<Medicamento> medicamentosSeleccionados;



	/**
	 * Create the frame.
	 * @param posSanitario 
	 * @param i 
	 */
	public VentanaTratamiento(VentanaPrincipal v, Usuario posPersona, int posSanitario) {
		medicamentosSeleccionados = new ArrayList<>();
		v.setEnabled(false);
		setTitle("Creador de Tratamientos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaTratamiento.class.getResource("/imagenes/osakidetza.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 457);
		contenedor = new JPanel();
		contenedor.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contenedor);
		contenedor.setLayout(new BorderLayout(0, 0));
		
		
		scroll = new JScrollPane();
		panelDatos = new JPanel();
		panelDatos.setBorder(new TitledBorder(null, "Cita para"+ posPersona.getNombre(), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contenedor.add(scroll, BorderLayout.CENTER);
		scroll.add(panelDatos);
		scroll.setViewportView(panelDatos);
		GridBagLayout gbl_panelDatos = new GridBagLayout();
		gbl_panelDatos.columnWeights = new double[]{1.0};
		gbl_panelDatos.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		gbl_panelDatos.rowHeights = new int[] {0, 0, 0, 0, 0, 0};
		panelDatos.setLayout(gbl_panelDatos);
		
		panelTitulo = new JPanel();
		FlowLayout fl_panelTitulo = (FlowLayout) panelTitulo.getLayout();
		fl_panelTitulo.setHgap(20);
		GridBagConstraints gbc_panelTitulo = new GridBagConstraints();
		gbc_panelTitulo.weighty = 1.0;
		gbc_panelTitulo.weightx = 1.0;
		gbc_panelTitulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelTitulo.insets = new Insets(0, 0, 5, 0);
		gbc_panelTitulo.gridx = 0;
		gbc_panelTitulo.gridy = 0;
		panelDatos.add(panelTitulo, gbc_panelTitulo);
		
		labelTitulo = new JLabel("Titulo: ");
		panelTitulo.add(labelTitulo);
		
		textTitulo = new JTextField();
		panelTitulo.add(textTitulo);
		textTitulo.setColumns(20);
		
		panelAmbito = new JPanel();
		FlowLayout fl_panelAmbito = (FlowLayout) panelAmbito.getLayout();
		fl_panelAmbito.setHgap(20);
		GridBagConstraints gbc_panelAmbito = new GridBagConstraints();
		gbc_panelAmbito.weighty = 1.0;
		gbc_panelAmbito.weightx = 1.0;
		gbc_panelAmbito.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelAmbito.insets = new Insets(0, 0, 5, 0);
		gbc_panelAmbito.gridx = 0;
		gbc_panelAmbito.gridy = 1;
		panelDatos.add(panelAmbito, gbc_panelAmbito);
		
		labelAmbito = new JLabel("Ambito");
		panelAmbito.add(labelAmbito);
		
		textAmbito = new JTextField();
		panelAmbito.add(textAmbito);
		textAmbito.setColumns(20);
		
		panelFecha = new JPanel();
		FlowLayout fl_panelFecha = (FlowLayout) panelFecha.getLayout();
		fl_panelFecha.setHgap(20);
		GridBagConstraints gbc_panelFecha = new GridBagConstraints();
		gbc_panelFecha.weighty = 1.0;
		gbc_panelFecha.weightx = 1.0;
		gbc_panelFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelFecha.insets = new Insets(0, 0, 5, 0);
		gbc_panelFecha.gridx = 0;
		gbc_panelFecha.gridy = 2;
		panelDatos.add(panelFecha, gbc_panelFecha);
		
		labelFecha = new JLabel("Fecha: ");
		panelFecha.add(labelFecha);
		
//		textField_3 = new JTextField();
//		panelFecha.add(textField_3);
//		textField_3.setColumns(20);
		UtilDateModel model = new UtilDateModel();
		//model.setDate(20,04,2014);
		// Need this...
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		//model.setDate(1990, 8, 24);
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		 
		panelFecha.add(datePicker);
		
		panelHora = new JPanel();
		FlowLayout fl_panelHora = (FlowLayout) panelHora.getLayout();
		fl_panelHora.setHgap(20);
		GridBagConstraints gbc_panelHora = new GridBagConstraints();
		gbc_panelHora.weighty = 1.0;
		gbc_panelHora.weightx = 1.0;
		gbc_panelHora.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelHora.insets = new Insets(0, 0, 5, 0);
		gbc_panelHora.gridx = 0;
		gbc_panelHora.gridy = 3;
		panelDatos.add(panelHora, gbc_panelHora);
		
		labelHora = new JLabel("Hora: ");
		panelHora.add(labelHora);
		
//		textField_4 = new JTextField();
//		panelHora.add(textField_4);
//		textField_4.setColumns(20);
		
		
	    Calendar calendar = new GregorianCalendar();
	    calendar.set(Calendar.HOUR_OF_DAY, 13); // 1pm

	    SpinnerDateModel dateModel = new SpinnerDateModel(calendar.getTime(), null,
	        null, Calendar.HOUR_OF_DAY);
	    JSpinner spinner = new JSpinner(dateModel);

	    JFormattedTextField tf = ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField();
	    DefaultFormatterFactory factory = (DefaultFormatterFactory) tf.getFormatterFactory();
	    DateFormatter formatter = (DateFormatter) factory.getDefaultFormatter();

	    // Change the date format to only show the hours
	    formatter.setFormat(new SimpleDateFormat("HH:mm:ss"));
	    
	    panelHora.add(spinner);
		
		
		
		
		panelDesc = new JPanel();
		FlowLayout fl_panelDesc = (FlowLayout) panelDesc.getLayout();
		fl_panelDesc.setHgap(20);
		GridBagConstraints gbc_panelDesc = new GridBagConstraints();
		gbc_panelDesc.weighty = 1.0;
		gbc_panelDesc.weightx = 1.0;
		gbc_panelDesc.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelDesc.insets = new Insets(0, 0, 5, 0);
		gbc_panelDesc.gridx = 0;
		gbc_panelDesc.gridy = 4;
		panelDatos.add(panelDesc, gbc_panelDesc);
		
		labelDesc = new JLabel("Descripción");
		panelDesc.add(labelDesc);
		
		textArea = new JTextArea();
		textArea.setRows(3);
		textArea.setColumns(20);
		textArea.setLineWrap(true);
		panelDesc.add(textArea);
		
		panelMed = new JPanel();
		panelMed.setBorder(new TitledBorder(null, "Medicamentos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FlowLayout flowLayout = (FlowLayout) panelMed.getLayout();
		GridBagConstraints gbc_panelMed = new GridBagConstraints();
		gbc_panelMed.fill = GridBagConstraints.BOTH;
		gbc_panelMed.gridx = 0;
		gbc_panelMed.gridy = 5;
		panelDatos.add(panelMed, gbc_panelMed);
		modelo =  new DefaultListModel();
		list = new JList(modelo);
		for (int i = 0; i < v.datos.getMed().size(); i++) {
			modelo.add(i, v.datos.getMed().get(i).getTitulo());
		}
		
		
		list.setPreferredSize(new Dimension(150, 90));

		list.addListSelectionListener(this);

		
		
		panelMed.add(list);
		
		
		horizontalGlue = Box.createHorizontalGlue();
		horizontalGlue.setPreferredSize(new Dimension(110, 0));
		panelMed.add(horizontalGlue);
		
		btnAnyadir = new JButton("Añadir");
		btnAnyadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Has añadido el medicamento " +modelo.get(list.getSelectedIndex()));
				int index = list.getSelectedIndex();
				medicamentosSeleccionados.add(v.datos.getMed().get(index));
				
			}
		});
		panelMed.add(btnAnyadir);
		
		panelBotones = new JPanel();
		contenedor.add(panelBotones, BorderLayout.SOUTH);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO
				if(textTitulo.getText().equals("")||textAmbito.getText().equals("")||datePicker.getJFormattedTextField().getText().equals("")||textArea.getText().equals("")) {
					JOptionPane.showMessageDialog(
							   panelBotones,
							   "Has dejado algun campo vacio");
					

				}else{
					//TODO
					Date fec = null;
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					fec = java.sql.Date.valueOf(datePicker.getJFormattedTextField().getText());
					
					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
					
					sdf.format(spinner.getValue());
					
					//System.out.println(sdf.format(spinner.getValue()));
					
					//System.out.println(((Date)spinner.getValue()).);
					
					
					
					v.anadirtratamientos(posPersona, textTitulo.getText(),textAmbito.getText(), fec, textArea.getText(),Time.valueOf(sdf.format(spinner.getValue())),medicamentosSeleccionados);
					v.setEnabled(true);
					setVisible(false);

				}
				
				
				
			}
		});
		panelBotones.add(btnAceptar);
		
		btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				v.setEnabled(true);
				setVisible(false);
				
				
			}
		});
		panelBotones.add(btnNewButton);
	}



	@Override
	public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
        	 
            if (list.getSelectedIndex() == -1) {
            //No selection, disable fire button.
                btnAnyadir.setEnabled(false);
 
            } else {
            //Selection, enable the fire button.
                btnAnyadir.setEnabled(true);
                System.out.println(list.getSelectedIndex());
            }
        }
	}





}
