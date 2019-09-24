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

import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import br.uefs.ecomp.forte.interfaces.Controller;
import br.uefs.ecomp.forte.views.HomePanel;

/**
 * Home Controller.
 * 
 * Handle home panel interactions.
 * @author goodeath
 *
 */
public class HomeController implements Controller{
	
	private HomePanel view = new HomePanel();
	
	/**
	 * Init.
	 * 
	 * Initialize default behavior for view.
	 */
	public void init() {
		view.next.addMouseListener(new NextListener());
		this.show();
	}
	
	/**
	 * Show.
	 * 
	 * Make this view visible.
	 */
	public void show() {
		FrontController.dispatch(view);
	}
	
	/**
	 * Listeners to handle events	
	 */
	private class NextListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent ev) {			
			int event = ev.getModifiersEx();
			int action = InputEvent.BUTTON1_DOWN_MASK;
			if((event & action) == action) FrontController.dispatch(FrontController.SCENE_GRAPH);
		}
	}
}
