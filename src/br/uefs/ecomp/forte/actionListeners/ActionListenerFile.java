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
package br.uefs.ecomp.forte.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;

import javax.swing.JFileChooser;

import br.uefs.ecomp.forte.interfaces.FileListener;
import br.uefs.ecomp.forte.interfaces.Observable;

/**
 * ActionListenerFile
 * 
 * An observable listener to open file menu option. When a graph file is loaded, some class
 * can do operations. In this case, Graph Controller can build a graph
 * @author goodeath
 *
 */
public class ActionListenerFile implements ActionListener, Observable<FileListener>{
	
	private LinkedList<FileListener> listeners = new LinkedList<FileListener>();
	private BufferedReader buffer;
	
	public void addListener(FileListener listener) {
		this.listeners.add(listener);
	}
	
	public void removeListener(FileListener listener) {
		this.listeners.remove(listener);
	}
	
	public void update() {
		for(FileListener f : listeners) {
			f.notify(buffer);
		}
	}
	  
	
	public void actionPerformed(ActionEvent actionEvent) {
		if(actionEvent.getActionCommand().compareTo("Open File") == 0){
			System.out.println(actionEvent.getActionCommand().compareTo("Open File"));
			JFileChooser choose = new JFileChooser(".");
			int status = choose.showOpenDialog(null);
			if(status == JFileChooser.APPROVE_OPTION) {
				File file = choose.getSelectedFile();
				try {
					buffer = new BufferedReader(new FileReader(file));
					update();	
				} catch (FileNotFoundException ex) {
					
				}
			}
		}
	}
}