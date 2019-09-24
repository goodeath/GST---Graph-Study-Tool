package br.uefs.ecomp.forte.utils;

import br.uefs.ecomp.forte.utils.LinkedList;
import br.uefs.ecomp.forte.utils.Edge;
import br.uefs.ecomp.forte.utils.Vertex;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

public class VertexTest {
	private LinkedList<Edge> edges;
	private Vertex v1,v2,v3;

	@Before
    public void setUp() {
    	this.edges = new LinkedList<Edge>();
    	this.v1 = new Vertex();
    	this.v2 = new Vertex();
    	this.v3 = new Vertex();
    	
    	this.edges.add(new Edge(v1,v2,10));
    	this.edges.add(new Edge(v2,v3,15));
    }
	
	@Test
	public void testInitialization() {
		Iterator<Edge> it = v1.getEdges();
		
		Edge e1 = this.edges.get(0);
		Edge e2 = this.edges.get(1);
		
		assertTrue(it.hasNext());
		assertTrue(it.next().equals(e1));
		assertFalse(it.hasNext());
		
		Iterator<Edge> it2 = v2.getEdges();
		assertTrue(it2.hasNext());
		assertTrue(it2.next().equals(e1));
		assertTrue(it2.hasNext());
		assertTrue(it2.next().equals(e2));
		assertFalse(it2.hasNext());
		
		Iterator<Edge> it3 = v3.getEdges();
		assertTrue(it3.hasNext());
		assertTrue(it3.next().equals(e2));
		assertFalse(it3.hasNext());
		
	}
}
