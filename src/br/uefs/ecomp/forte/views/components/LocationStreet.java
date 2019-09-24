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
package br.uefs.ecomp.forte.views.components;

import java.awt.Graphics;

import br.uefs.ecomp.forte.interfaces.Drawable;
import br.uefs.ecomp.forte.utils.Edge;

/**
 * Location Street.
 * 
 * Represents Edges in GUI interface.
 * @author goodeath
 *
 */
public class LocationStreet implements Drawable {

	private Location l;
	private Location origin;
	private Edge e;
	private int weight = 0;
	private int x, y;
	
	public LocationStreet(Location origin, Location l, Edge e) {
		this.l = l;
		this.e = e;
		this.weight = e.getWeight();
		this.origin = origin;
	}
	
	/**
	 * Draw
	 * 
	 * Draw a line from one vertex to other's center and write weight in mean point of rect's segment.
	 * 
	 *  @param Graphics Drawer of Graphics 2D
	 */
	public void draw(Graphics g) {
		x = this.getX();
		y = this.getY();
		int r = l.getRadius();
		int xd = Math.abs((x+l.getX())/2);
		int yd = Math.abs((y+l.getY())/2);
		g.drawString(Integer.toString(weight), xd, yd);
		g.drawLine(x, y, l.getX()+r, l.getY()+r);
	}
	
	public Location getLocation() {
		return l;
	}
	
	public void setLocation(Location l) {
		this.l = l;
	}

	public Edge getEdge() {
		return e;
	}
	
	public void setEdge(Edge e) {
		this.e = e;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	@Override
	public int getX() {
		return origin.getX() + origin.getRadius();
	}

	@Override
	public int getY() {
		return origin.getY() + origin.getRadius();
	}
}
