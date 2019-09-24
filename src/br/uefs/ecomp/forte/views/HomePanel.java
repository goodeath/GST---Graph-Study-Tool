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
package br.uefs.ecomp.forte.views;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Home Panel.
 * 
 * It's create the home application.
 * @author goodeath
 *
 */
public class HomePanel extends JPanel {

	private static final long serialVersionUID = 8048114653693768918L;
	public JButton next;
	
	public HomePanel() {
		final JLabel label = new JLabel("Build a graph and check shortest path");
		JLabel picLabel = new JLabel(new ImageIcon("/home/goodeath/eclipse-workspace/pbl4/src/images/marker.png"));
		next = new JButton();
		next.setText("Iniciar");
		this.add(label);
		this.add(picLabel);
		this.add(next);
	}
}
