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

import br.uefs.ecomp.forte.interfaces.SSSP;

public class Graph {
	
	private LinkedList<Vertex> vertex;
	private static int serial = 0;
	public Graph() {
		this.vertex = new LinkedList<Vertex>();
	}
	
	public Vertex addVertex() {
		Vertex v = new Vertex(serial++);
		this.vertex.add(v);
		return v;
	}
	
	public void removeVertex(Vertex v) {
		for(Vertex vv : this.vertex) {
			LinkedList<Edge> edgeList = new LinkedList<Edge>();
			for(Edge e: vv.getAdjList()) {
				if(v.equals(e.getLastVertex())) {
					edgeList.add(e);
//					vv.removeEdge(e);
				}
			}
			Iterator<Edge> it = edgeList.iterator();
			while(it.hasNext()) {
				vv.removeEdge(it.next());
			}
		}

		this.vertex.remove(v);
	}
	
	public int size() {
		return this.vertex.size();
	}
	
	public Vertex getVertex(int i) {
		return this.vertex.get(i);
	}
	
	public LinkedList<Vertex> getVertexList(){
		return this.vertex;
	}
	
	public Edge addEdge(int v1, int v2, int w) {
		Edge e = new Edge(this.vertex.get(v1),this.vertex.get(v2),w);
		new Edge(this.vertex.get(v2),this.vertex.get(v1),w);
		return e;
	}
	
	public Edge addEdge(Vertex v1, Vertex v2, int w) {
		Edge e = new Edge(v1,v2,w);
		new Edge(v2,v1,w);
		return e;
	}
	
	public LinkedList<Edge> findSSSP(SSSP algorithm, Vertex v0, Vertex v1) {
		return algorithm.reach(this, v0,v1);
	}
}
