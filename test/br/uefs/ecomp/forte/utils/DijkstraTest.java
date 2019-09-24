package br.uefs.ecomp.forte.utils;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import br.uefs.ecomp.forte.utils.Graph;
import br.uefs.ecomp.forte.utils.Edge;
import br.uefs.ecomp.forte.models.Dijkstra;

public class DijkstraTest {
	
	private Graph graph;
	private Edge e3,e4;
	
	@Before
	public void setUp() {
		this.graph = new Graph();
		Vertex v0 = this.graph.addVertex();
		Vertex v1 = this.graph.addVertex();
		Vertex v2 = this.graph.addVertex();
		Vertex v3 = this.graph.addVertex();
		 
		this.graph.addEdge(v0,v1,6);
		this.graph.addEdge(v1,v2,2);
		e3 = this.graph.addEdge(v2,v3,1);
		e4 = this.graph.addEdge(v0,v2,2);
	}
	
	@Test
	public void testShortestPath() {
		Vertex v0 = this.graph.getVertex(0);
		Vertex v3 = this.graph.getVertex(3);
		LinkedList<Edge> dijkstra = this.graph.findSSSP(new Dijkstra(), v0,v3);
		
		
		Iterator<Edge> it = dijkstra.iterator();
		assertTrue(it.hasNext());
		assertTrue(e4.equals(it.next()));
		assertTrue(it.hasNext());
		assertTrue(e3.equals(it.next()));
		assertFalse(it.hasNext());
		
		
	}
}
