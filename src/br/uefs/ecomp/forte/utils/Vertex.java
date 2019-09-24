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
package br.uefs.ecomp.forte.utils;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Vertex
 * 
 * Vertex of Graph
 * @author goodeath
 *
 */
public class Vertex {
	
	private int id;
	
	private LinkedList<Edge> edges;
	
	public Vertex() {
		edges = new LinkedList<Edge>();
	}
	
	public Vertex(int id) {
		this();
		this.id = id;
	}

	public void addEdge(Edge e) {
		this.edges.add(e);
	}
	
	public void removeEdge(Edge e) {
		this.edges.remove(e);
	}
	
	/**
	 * Get Adj List.
	 * 
	 * Return adjacency list of vertex.
	 * @return LinkedList<Edge>
	 */
	public LinkedList<Edge> getAdjList(){
		return edges;
	}
	
	public Iterator<Edge> getEdges(){
		return this.edges.iterator();
	}
	
	public Edge getEdge(int i) {
		return this.edges.get(i);
	}
	
	public int getId() {
		return this.id;
	}
	
}
