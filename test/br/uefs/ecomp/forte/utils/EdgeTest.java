package br.uefs.ecomp.forte.utils;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import br.uefs.ecomp.forte.utils.Vertex;

public class EdgeTest {
	
	private LinkedList<Vertex> vertex;

	@Before
    public void setUp() {
    	this.vertex = new LinkedList<Vertex>();
    	this.vertex.add(new Vertex());
    	this.vertex.add(new Vertex());
    	this.vertex.add(new Vertex());
    	this.vertex.add(new Vertex());
    	this.vertex.add(new Vertex());
    	this.vertex.add(new Vertex());
    	this.vertex.add(new Vertex());
    }
	
	@Test
	public void testInitialization() {
		Edge e1 = new Edge(this.vertex.get(0),this.vertex.get(1),10);
		Edge e2 = new Edge(this.vertex.get(1),this.vertex.get(0),10);
		
		Vertex v1 = e1.getFirstVertex();
		assertTrue(v1.equals(this.vertex.get(0)));
	
		Vertex v2 = e1.getLastVertex();
		assertTrue(v2.equals(this.vertex.get(1)));
		
		assertEquals(10,e1.getWeight());
		
		assertTrue(v1.getEdge(0).equals(e1));
	}
}
