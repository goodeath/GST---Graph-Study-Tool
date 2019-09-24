/*******************************************************************************
Autor: Alex Anderson Ferreira Boa Morte
Componente Curricular: Algoritmos II
Concluido em: 21/09/2019
Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************************************************************************/
package br.uefs.ecomp.forte.views.components;
 
 
import java.awt.event.ActionListener;
 
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = -177188742945400658L;

	public MenuBar(ActionListener menuListener) {
		JMenu file = this.addMenu("File");
		KeyStroke key = KeyStroke.getKeyStroke("control O");
		JMenuItem openFile = this.addMenuItem(file, "Open File", key);
		openFile.addActionListener(menuListener);
		file.addSeparator();
		// Add Exit Option Menu
		this.addMenuItem(file, "Exit");
		JMenu about = this.addMenu("About");
		this.addMenuItem(about, "Help");
		about.addSeparator();
		this.addMenuItem(about, "Version");		
	}
	 
	public JMenu addMenu(String name) {
		JMenu newMenu = new JMenu(name);
		this.add(newMenu);
		return newMenu;
	}
	
	public JMenuItem addMenuItem(JMenu menu, String name) {
		JMenuItem item = new JMenuItem(name);
		menu.add(item);
		return item;
	}
	
	public JMenuItem addMenuItem(JMenu menu, String name, int shortcut) {
		JMenuItem item = new JMenuItem(name, shortcut);
		menu.add(item);
		return item;
 
	}
	
	public JMenuItem addMenuItem(JMenu menu, String name, KeyStroke shortcut) {
		JMenuItem item = new JMenuItem(name);
		menu.add(item);
		item.setAccelerator(shortcut);
		return item;
		
	}
}