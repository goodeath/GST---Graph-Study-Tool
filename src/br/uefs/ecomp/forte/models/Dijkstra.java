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
package br.uefs.ecomp.forte.models;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import br.uefs.ecomp.forte.interfaces.SSSP;
import br.uefs.ecomp.forte.utils.Edge;
import br.uefs.ecomp.forte.utils.Graph;
import br.uefs.ecomp.forte.utils.Vertex;

/**
 * Dijkstra
 * 
 * Implements Dijkstra Algorithm.
 * @author goodeath
 *
 */
public class Dijkstra implements SSSP {
	
	/**
	 * TrackPoint.
	 * 
	 * Encapsulates vertex to make edge relaxation.
	 * @author goodeath
	 *
	 */
	class TrackPoint {
		/** @var TrackPoint Predecessor of the shortest path */
		private TrackPoint before = null;
		private Edge beforeEdge = null;
		private Vertex vertex;
		private boolean visited = false;
		private int distance = 10000000;	
		
		public TrackPoint(Vertex vertex) {
			this.vertex = vertex;
		}
		
		public Vertex getVertex() {
			return this.vertex;
		}
		
		public void setDistance(int distance) {
			this.distance = distance;
		}
		
		public int getDistance() {
			return this.distance;
		}
		
		public void setBefore(TrackPoint point) {
			this.before = point;
		}
		
		public TrackPoint getBefore() {
			return this.before;
		}
		
		public void setBeforeEdge(Edge e) {
			this.beforeEdge = e;
		}
		
		public Edge getEdge() {
			return this.beforeEdge;
		}
		
		/**
		 * Equals.
		 * 
		 * @param Trackpoint Two TrackPoints are equals if their vertex are equals.
		 * @return boolean
		 */
		public boolean equals(TrackPoint point) {
			return this.vertex.equals(point.getVertex());
		}
	}
	
	/**
	 * Init Vertex.
	 * 
	 * @param Graph Map All Vertex to TrackPoints
	 * @return Map<Vertex,TrackPoint>
	 */
	private Map<Vertex,TrackPoint> initVertex(Graph graph){
		LinkedList<Vertex> vertex = graph.getVertexList();
		Map<Vertex,TrackPoint> mapped = new HashMap<Vertex,TrackPoint>();
		for(Vertex v : vertex) {
			TrackPoint point = new TrackPoint(v);
			mapped.put(v, point);
		}
		return mapped;
	}
	
	/**
	 * Reach.
	 * 
	 * Reach v1 from v0 in such Graph G.
	 * 
	 * @return LinkedList<Edge> Edges collection of shortest path.
	 */

	@Override
	public LinkedList<Edge> reach(Graph graph, Vertex v0, Vertex v1) {
		
		Map<Vertex,TrackPoint> mapped = this.initVertex(graph);
		TrackPoint initial = mapped.get(v0);
		initial.setDistance(0);
		
		Deque<TrackPoint> queue = new LinkedList<TrackPoint>();
		queue.add(initial);
		
		while(queue.size() > 0) {
			TrackPoint currentPoint = queue.remove();
			Vertex currentVertex = currentPoint.getVertex();
			
			for(Edge e: currentVertex.getAdjList()) {
				TrackPoint adjacentPoint = mapped.get(e.getLastVertex());
				if(currentPoint.distance + e.getWeight() < adjacentPoint.distance) {
					adjacentPoint.setDistance(currentPoint.distance + e.getWeight());
					adjacentPoint.setBefore(currentPoint);
					adjacentPoint.setBeforeEdge(e);
				}
				if(adjacentPoint.visited == false) queue.add(adjacentPoint);
			}
			currentPoint.visited = true;
			
		}
		
		Deque<Edge> trace = new LinkedList<Edge>();
		TrackPoint destination = mapped.get(v1);
		while(destination != null && destination.getEdge() != null ) {
			trace.addFirst(destination.getEdge());
			destination = destination.getBefore();
		}
		
		return (LinkedList<Edge>)trace;
	}
	
}
