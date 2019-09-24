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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.swing.JSplitPane;

import br.uefs.ecomp.forte.interfaces.Controller;
import br.uefs.ecomp.forte.interfaces.FileListener;
import br.uefs.ecomp.forte.interfaces.GraphListener;
import br.uefs.ecomp.forte.interfaces.Observable;
import br.uefs.ecomp.forte.interfaces.Observer;
import br.uefs.ecomp.forte.models.Dijkstra;
import br.uefs.ecomp.forte.utils.Edge;
import br.uefs.ecomp.forte.utils.Graph;
import br.uefs.ecomp.forte.utils.Vertex;
import br.uefs.ecomp.forte.views.GraphPanel;
import br.uefs.ecomp.forte.views.GraphToolPanel;
import br.uefs.ecomp.forte.views.components.Location;
import br.uefs.ecomp.forte.views.components.LocationStreet;

public class GraphController implements FileListener, Controller, Observable<GraphListener> {
	
	private Graph graph;
	private GraphPanel graphPanel;
	private GraphToolPanel tool;
	
	private LinkedList<GraphListener> listeners = new LinkedList<GraphListener>();
	
	public void addListener(GraphListener listener) {
		this.listeners.add(listener);
	}
	
	public void removeListener(GraphListener listener) {
		this.listeners.remove(listener);
	}
	
	public void update() {
		for(GraphListener f : listeners) {
			f.notify(graph);
		}
	}
	
	public GraphController() {
		this.graph = new Graph();
	}
	
	public void init() {
		this.show();
	}
	
	public void notify(BufferedReader br) {
		try {
			this.graph = new Graph();
			int numberOfVertex = Integer.parseInt(br.readLine());
			for(int x = 0; x < numberOfVertex; x++) {
				this.graph.addVertex();
			}
			
			String line;
			while((line = br.readLine()) != null) {
				StringTokenizer token = new StringTokenizer(line);
				int v1 = Integer.parseInt(token.nextToken());
				int v2 = Integer.parseInt(token.nextToken());
				int w = Integer.parseInt(token.nextToken());
				this.graph.addEdge(v1, v2, w);
			}
			graphPanel.buildGraph(graph);
			update();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void show() {
		GraphPanel main = new GraphPanel(graph);
		this.graphPanel = main;
		 tool = new GraphToolPanel();
		this.addEvents(main,tool);
		this.addListener((GraphListener) main);
		this.addListener((GraphListener) tool);
		JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tool, main );
		panel.setDividerLocation(200);
		FrontController.dispatch(panel);
	}
	
	public Graph getGraph() {
		return this.graph;
	}
	
	public void setGraph(Graph g) {
		this.graph = g;
		graphPanel.buildGraph(g);
		update();
	}
	
	public void addVertex() {
		Graph g = this.getGraph();
		Vertex e = g.addVertex();
		update();
		graphPanel.createLocation(e);
	}
	
	public void removeVertex(Vertex v) {
		Graph g = this.getGraph();
		g.removeVertex(v);
		graphPanel.removeLocation(v);
		update();
	}
	
	public void addAdjacency(int v1, int v2, int w) {
		Edge e = this.graph.addEdge(v1, v2, w);
		graphPanel.addAdjacency(this.graph.getVertex(v1), this.graph.getVertex(v2),e);
		update();
	}
	
	public void setPark( ) {
		this.graphPanel.setPark();
	}
	
	public void setBank( ) {
		this.graphPanel.setBank();
	}
	
	public void setCollect( ) {
		this.graphPanel.setCollect();
	}
	
	public void findShortestPath() {
		this.graphPanel.ShortPath = new HashMap<Edge,LocationStreet>();
		Vertex v1 = this.graphPanel.activePark.getVertex();
		Vertex v2 = this.graphPanel.activeCollect.getVertex();
		Vertex v3 = this.graphPanel.activeBank.getVertex();

		LinkedList<Edge> dijkstra = this.graph.findSSSP(new Dijkstra(), v1,v2);
		System.out.println("Menor Caminho do Estacionamento para a Coleta");
		for(Edge v : dijkstra) {

			LocationStreet l = this.graphPanel.roads.get(v);
			System.out.println("V"+v.getFirstVertex().getId() + " - " + "V"+v.getLastVertex().getId());
			this.graphPanel.ShortPath.put(v,l);
		}
		
		dijkstra = this.graph.findSSSP(new Dijkstra(), v2,v3);
		System.out.println("Menor Caminho da Coleta para o Banco");
		for(Edge v : dijkstra) {
			LocationStreet l = this.graphPanel.roads.get(v);
			System.out.println("V"+v.getFirstVertex().getId() + " - " + "V"+v.getLastVertex().getId());
			this.graphPanel.ShortPath.put(v,l);
		}
		update();
	}
	
	
	
	public void addEvents(GraphPanel main, GraphToolPanel tool) {
		tool.addVertex.addActionListener(new ActionListenerAddVertex());
		tool.resetGraph.addActionListener(new ActionListenerResetGraph());
		tool.rmVertex.addActionListener(new ActionListenerRemoveVertex());
		tool.addAdj.addActionListener(new ActionListenerAddAdj());
		tool.parkBtn.addActionListener(new ActionListenerSetPark());
		tool.bankBtn.addActionListener(new ActionListenerSetBank());
		tool.collectBtn.addActionListener(new ActionListenerSetCollect());
		tool.shortest.addActionListener(new ActionListenerSSSP());
	}
	
	/**
	 * Listeners
	 */
	
	private class ActionListenerAddVertex implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ev) {
			int event = ev.getModifiers();
			int action = InputEvent.BUTTON1_MASK;
			if((event & action) == action) GraphController.this.addVertex();			
		}
	}
	
	private class ActionListenerResetGraph implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ev) {
			int event = ev.getModifiers();
			int action = InputEvent.BUTTON1_MASK;
			if((event & action) == action) GraphController.this.setGraph(new Graph());			
		}
	}
	
	private class ActionListenerRemoveVertex implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ev) {
			int event = ev.getModifiers();
			int action = InputEvent.BUTTON1_MASK;
			Vertex active = GraphController.this.graphPanel.getActive() != null ?  GraphController.this.graphPanel.getActive().getVertex() : null ;
			if((event & action) == action && active != null) GraphController.this.removeVertex(active);			
		}
	}
	
	private class ActionListenerAddAdj implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ev) {
			int event = ev.getModifiers();
			int action = InputEvent.BUTTON1_MASK;
			GraphToolPanel tool = GraphController.this.tool;
			int v1 = tool.vcombo.getSelectedIndex();
			int v2 = tool.v2combo.getSelectedIndex();
			int w = tool.weight.getText() != "" ? Integer.parseInt(tool.weight.getText()) : 5;
			tool.weight.setText("");
			GraphController.this.addAdjacency(v1, v2, w);
						
		}
	}
	
	private class ActionListenerSetPark implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ev) {
			int event = ev.getModifiers();
			int action = InputEvent.BUTTON1_MASK;
			if((event & action) == action) GraphController.this.setPark();			
		}
	}
	
	private class ActionListenerSetBank implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ev) {
			int event = ev.getModifiers();
			int action = InputEvent.BUTTON1_MASK;
			if((event & action) == action) GraphController.this.setBank();			
		}
	}
	
	private class ActionListenerSetCollect implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ev) {
			int event = ev.getModifiers();
			int action = InputEvent.BUTTON1_MASK;
			if((event & action) == action) GraphController.this.setCollect();			
		}
	}
	
	private class ActionListenerSSSP implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ev) {
			int event = ev.getModifiers();
			int action = InputEvent.BUTTON1_MASK;
			if((event & action) == action) GraphController.this.findShortestPath();			
		}
	}	

}
