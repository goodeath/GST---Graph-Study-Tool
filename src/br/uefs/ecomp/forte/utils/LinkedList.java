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

/**
 * Implements a chained list.
 * 
 * @author Alex Anderson
 * 
 * @see br.uefs.ecomp.oc.model.ItemPedido
 * @see 
 */
public class LinkedList<T> {

	private int size = 0;
	private Node<T> head;
	
	
	/**
	 * Iterator.
	 * 
	 * Get a iterator to list.
	 * 
	 * @return Iterator
	 */
	public Iterator<T> iterator() {
		Iterator<T> it = new Iterator<T>() {
			private int curIndex = 0;
			
			public boolean hasNext() {
				Node<T> tmp = LinkedList.this.head;
				if(!LinkedList.this.isEmpty()) {			
					for(int i = 0; i < this.curIndex; i++)
						tmp = tmp.getNext();
					return tmp != null;
				} else {
					return false;
				}
			}
			
			public T next() {
				T data = LinkedList.this.get(this.curIndex);
				this.curIndex += 1;
				return data;
			}	
		};
		return it;
	}

	/**
	 * Size.
	 * 
	 * Get list size.
	 * 
	 * @return int List size.
	 */
	
	public int size() {
		return this.size;
	}

	/**
	 * Add.
	 * 
	 * Add item at final of the list.
	 * 
	 * @return
	 */
	
	public void add(T obj) {
		Node<T> tmp = this.head;
		if(this.head == null) {
			this.head = new Node<T>(obj);
		} else {
			while(tmp.getNext() != null)
				tmp = tmp.getNext();
			tmp.setNext(new Node<T>(obj));
		}
		++this.size;
	}

	/**
	 * Add.
	 * 
	 * Add item in a position of the list.
	 * 
	 * @param int pos Position that'll be inserted.
	 * @param Object Data
	 */
	public boolean add(int pos, T obj) {
		
		if(pos > this.size())
			return false;
		
		Node<T> node = new Node<T>(obj),
				cur = this.head,
				prev = new Node<T>();
		
		if(!this.isEmpty()) {
			if(pos == 0 ) {
				node.setNext(cur);
				this.head = node;
			} else {
				int i = 0;
				for( ; i < pos ; i++) {
					prev = cur;
					cur = cur.getNext();
				}
				prev.setNext(node);
				node.setNext(cur);
			}
			++this.size;
			return true;
		} else {
			this.add(obj);
			return true;
		}
	}

	/**
	 * Remove.
	 * 
	 * Remove item from a position of the list.
	 * 
	 * @return Object.
	 */
	
	public T remove(int pos) {
		Node<T> tmp = this.head,
				prev = this.head;
		for(int i = 0; i < pos && tmp != null; i++) {
			prev = tmp;
			tmp = tmp.getNext();	
		}
		if(tmp != null) {
			if(pos == 0)
				this.head = this.head.getNext();
			else
				prev.setNext(tmp.getNext());
			--this.size;
			return tmp.getData();
		} 
		
		return null; 
	}
	
	/**
	 * Get Head.
	 * 
	 * Get head of the list.
	 * @return
	 */
	public Node<T> getHead() {
		return this.head;
	}

	/**
	 * Get.
	 * 
	 * Return data from a node list index.
	 * 
	 * @param int index Node Index.
	 * @return Object 
	 */
	public T get(int index) {
		Node<T> tmp = this.head;
		int i;
		if(!this.isEmpty()) {
			for(i = 0; i < index; i++)
				tmp = tmp.getNext();
			return tmp.getData();
		} else if(this.size() == 0) {
			return null;
		}
		return tmp.getData();
	}

	/**
	 * Is Empty.
	 * 
	 * Check if list is empty.
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
}
