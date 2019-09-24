/*******************************************************************************
Autor: Alex Anderson Ferreira Boa Morte
Componente Curricular: Algoritmos II
Concluido em: 19/08/2019
Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************************************************************************/
package br.uefs.ecomp.forte.utils;

import br.uefs.ecomp.forte.utils.Node;

/**
 * Implements a node to store data inside a chained list.
 * 
 * @author Alex Anderson
 * 
 * @see br.uefs.ecomp.oc.util.MyLinkedList
 */
public class Node<T> {
	private Node<T> next;
	private T data;

	public Node() {
		this.data = null;
	}
	
	public Node(T data) {
		this.data = data;
	}
	
	public Node<T> getNext() {
		return this.next;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public void setNext(Node<T> node) {
		this.next = node;
	}

	public T getData() {
		return this.data;
	}
}

