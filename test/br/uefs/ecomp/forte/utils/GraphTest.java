package br.uefs.ecomp.forte.utils;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import br.uefs.ecomp.forte.utils.Graph;

import java.util.Iterator;

public class GraphTest {

	private Graph graph;

	@Before
    public void setUp() {
    	this.graph = new Graph();
    }
	
	@Test
	public void testAddVertex() {
		assertEquals(0,this.graph.size());
		this.graph.addVertex();
		assertEquals(1,this.graph.size());
		this.graph.addVertex();
		assertEquals(2,this.graph.size());
	}
	
	@Test
	public void testRemoveVertex() {
		assertEquals(0,this.graph.size());
		Vertex v = this.graph.addVertex();
		assertEquals(1,this.graph.size());
		this.graph.removeVertex(v);
		assertEquals(0,this.graph.size());
	}
	
	@Test
	public void testAddEdge() {
		this.graph.addVertex();
		this.graph.addVertex();
		Edge e = this.graph.addEdge(0,1,5);
		
		Vertex v1 = this.graph.getVertex(0);
		Iterator <Edge> it = v1.getEdges();
		
		assertTrue(it.hasNext());
		Edge e1 = it.next();
		assertTrue(e1.equals(e));
		
		Vertex v2 = this.graph.getVertex(1);
		it = v2.getEdges();
		
		assertTrue(it.hasNext());
		Edge e2 = it.next();
		assertTrue(e2.equals(e));
	}
	
	
}
