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

import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.uefs.ecomp.forte.interfaces.GraphListener;
import br.uefs.ecomp.forte.utils.Graph;

public class GraphToolPanel extends JPanel  implements GraphListener {
	
	private static final long serialVersionUID = 8634678970689514841L;
	public JButton addVertex;
	public JButton resetGraph;
	public JButton rmVertex;
	public JButton addAdj;
	public JButton parkBtn;
	public JButton bankBtn;
	public JButton collectBtn;
	public JButton shortest;
	public JTextField weight;
	public DefaultComboBoxModel<String>  model = new DefaultComboBoxModel<String>();
	public DefaultComboBoxModel<String>  model2 = new DefaultComboBoxModel<String>();
	public JComboBox<String> vcombo, v2combo;

	public GraphToolPanel() {
		addVertex = new JButton("Adicionar Vértice");
		rmVertex = new JButton("Remover Vértice");
		parkBtn = new JButton("Marcar Estacionamento");
		collectBtn = new JButton("Marcar Coleta");
		bankBtn = new JButton("Marcar Banco");
		resetGraph = new JButton("Limpar Grafo");
		addAdj = new JButton("Adicionar adjacência");
		shortest = new JButton("Executar Dijkstra");
		weight = new JTextField();
		weight.setToolTipText("Insira o peso da aresta");
		vcombo = new JComboBox<String>(model);
		v2combo = new JComboBox<String>(model2);
		
		this.setLayout(new GridLayout(0, 1));
		this.add(vcombo);
		this.add(v2combo);
		this.add(weight);
		this.add(addAdj);
		this.add(addVertex);
		this.add(rmVertex);
		this.add(parkBtn);
		this.add(collectBtn);
		this.add(bankBtn);
		this.add(shortest);
		this.add(resetGraph);
	}

	@Override
	public void notify(Graph graph) {
		int size = graph.size();
		vcombo.removeAllItems();
		v2combo.removeAllItems();
		for(int x = 0; x < size; x++) {
			vcombo.addItem("V" + graph.getVertex(x).getId());
			v2combo.addItem("V" +  graph.getVertex(x).getId());
		}
		repaint();
	}
}
