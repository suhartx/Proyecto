package proyecto.usuarios;

import java.util.ArrayList;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

import proyecto.contenido.Cita;
import proyecto.contenido.Prueba;
import proyecto.contenido.Tratamiento;
import sun.security.util.Password;

public class Paciente extends Usuario {

	public Paciente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Paciente(int codusuario, String nombre, String apellido, String dni, char sexo, String contrasenya, float peso,
			int altura, String alergias, int colesterol, int tension, String enfermedades, String tipoSangre) {
		super(codusuario, nombre, apellido, dni, sexo, contrasenya, peso, altura, alergias, colesterol, tension, enfermedades, tipoSangre);
		// TODO Auto-generated constructor stub
	}

	@Override
	public JTree cargarJTree(ArrayList<Usuario> user) {
		
		JTree arbol = new JTree();

		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("nodo raiz, no deberia ser visible");		
		
		DefaultTreeModel defaultTreeModel = new DefaultTreeModel( rootNode );		
		
		arbol.setModel( defaultTreeModel );		

		
		DefaultMutableTreeNode t = new DefaultMutableTreeNode(this.getNombre()+" "+ this.getApellido());
		DefaultMutableTreeNode cita = new DefaultMutableTreeNode("citas");
		DefaultMutableTreeNode prueba = new DefaultMutableTreeNode("pruebas");
		DefaultMutableTreeNode tratamiento = new DefaultMutableTreeNode("tratamientos");

		
		
		defaultTreeModel.insertNodeInto(t, (MutableTreeNode) defaultTreeModel.getRoot(), ((DefaultMutableTreeNode) defaultTreeModel.getRoot()).getChildCount());
		
		defaultTreeModel.insertNodeInto(cita, t, t.getChildCount());
		
		defaultTreeModel.insertNodeInto(prueba, t, t.getChildCount());
		
		defaultTreeModel.insertNodeInto(tratamiento, t, t.getChildCount());
		
		
		for (Cita c : this.getCitas()) {
			defaultTreeModel.insertNodeInto(new DefaultMutableTreeNode(c.getTitulo()), cita, cita.getChildCount());
		}
		for (Prueba p : this.getPruebas()) {
			defaultTreeModel.insertNodeInto(new DefaultMutableTreeNode(p.getTitulo()), prueba, prueba.getChildCount());
		}			
		for (Tratamiento tr : this.getTratamientos()) {
			defaultTreeModel.insertNodeInto(new DefaultMutableTreeNode(tr.getTitulo()), tratamiento, tratamiento.getChildCount());
		}			
		
		
		arbol.expandRow(0);
		
		arbol.setRootVisible( false );
		
		//arbol.setShowsRootHandles(true);
		
		return arbol;
		
	}


	



}
