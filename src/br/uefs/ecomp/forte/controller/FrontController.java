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
package br.uefs.ecomp.forte.controller;

import javax.swing.JComponent;
import javax.swing.JFrame;

import br.uefs.ecomp.forte.actionListeners.ActionListenerFile;
import br.uefs.ecomp.forte.interfaces.Controller;
import br.uefs.ecomp.forte.interfaces.FileListener;
import br.uefs.ecomp.forte.views.components.MenuBar;

/**
 * Front Controller
 * 
 * It's a singleton class that starts root frame and handle all future panel requests.
 * @author goodeath
 *
 */
public class FrontController {
	private static JFrame root;
	public static String SCENE_HOME = "HOME";
	public static String SCENE_GRAPH = "GRAPH";
	
	// Controllers
	private static Controller GraphController = new GraphController();
	private static Controller HomeController = new HomeController();
	
	/**
	 * Init.
	 * 
	 * Initialize Root Frame defaults.
	 */
	public static void init(){
		FrontController.root = new JFrame();
		JFrame root = FrontController.root;
		root.setSize(1366,768);
		root.setTitle("Forte - Single Source Shortest Path");
		
		ActionListenerFile file = new ActionListenerFile();
		file.addListener((FileListener)FrontController.GraphController);
		root.setJMenuBar(new MenuBar(file));
		root.setVisible(true);
		root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dispatch(SCENE_HOME);
		// subscribe on menubar
	}
	
	/**
	 * Dispatch.
	 * 
	 * Start a controller by its name.
	 * @param String route Controller route name.
	 */
	public static void dispatch(String route) {
		switch(route) {
			case "HOME": HomeController.init(); break;
			case "GRAPH": GraphController.init(); break;
		}
	}
	
	/**
	 * Dispatch.
	 * 
	 * After controller has been called, it can dispatch its own components to FC.
	 * @param JComponent panel
	 */
	public static void dispatch(JComponent panel) {
		JFrame root = FrontController.root;
		root.getContentPane().removeAll();
		root.add(panel);
		root.setVisible(true);
	}
}
