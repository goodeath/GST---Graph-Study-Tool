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

public class Edge {
	
	private Vertex v1;
	private Vertex v2;
	private int weight;
	private boolean directed = false;
	
	public Edge(Vertex v1, Vertex v2, int w){
		this.v1 = v1;
		this.v2 = v2;
		this.v1.addEdge(this);
		this.weight = w;
	}
	
	public Vertex getFirstVertex() {
		return this.v1;
	}
	
	public Vertex getLastVertex() {
		return this.v2;
	}
	
	public int getWeight() {
		return this.weight;
	}
	
	public boolean equals(Edge e) {
		boolean isEqual = false;
		if(!directed && (
				(e.v1.equals(this.v2) && e.v2.equals(this.v1)) || 
				(e.v1.equals(this.v1) && e.v2.equals(this.v2))
			)
		) {
			isEqual = true;
		} else if(e.v1.equals(this.v1)&& e.v2.equals(this.v2)) {
			isEqual = true;
		}
		return isEqual;
		
	}
}
