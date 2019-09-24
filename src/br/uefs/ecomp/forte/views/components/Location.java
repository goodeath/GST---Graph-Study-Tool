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

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import br.uefs.ecomp.forte.interfaces.Drawable;
import br.uefs.ecomp.forte.utils.Edge;
import br.uefs.ecomp.forte.utils.Vertex;

/**
 * Location.
 * 
 * Represents vertex in GUI interface.
 * @author goodeath
 *
 */
public class Location implements Drawable {
	
	public static int TYPE_NEUTRAL = 0;
	public static int TYPE_PARK = 1;
	public static int TYPE_COLLECT = 2;
	public static int TYPE_BANK = 3;
	
	private int x = 0;
	private int y = 0;

	
	private String label;
	private int size = 25;
	private int radius = size/2;
	private LinkedList<LocationStreet> adj;
	private Vertex vertex;
	private int type = 0;
	
	public Location() {
		this.adj = new LinkedList<LocationStreet>();
	};
	
	public Location(String label, int x, int y) {
		this(x,y);
		this.label = label;
		
	}
	
	public Location(int x, int y) {
		this();
		this.x = x;
		this.y = y;
	}
	
	public LocationStreet addAdjacent(Location l, Edge e) {
		LocationStreet s = new LocationStreet(this,l,e);
		LocationStreet s2 = new LocationStreet(l, this,e);
		this.adj.add(s);
		l.adj.add(s2);
		return s;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawString(label, x, y-10);
		g.drawOval(x, y, size, size);
		for(LocationStreet l : this.adj) l.draw(g);
	}
	
	public void draw(Graphics g, Color color) {
		g.drawString(label, x, y-10);
		g.setColor(color);
		g.fillOval(x, y, size, size);
		g.setColor(Color.BLACK);
		for(LocationStreet l : this.adj) l.draw(g);
	}
	
	/*public void draw(Graphics g, Color color, boolean keep, Map<Edge, LocationStreet> sssp) {
//		boolean isPart = false;
//		for(LocationStreet l : this.adj) {
//			LocationStreet s = sssp.get(l.getEdge());
//			if(s!=null) isPart = true;
//		}
//		if(isPart) color = Color.BLUE;
		g.drawString(label, x, y-10);
		g.setColor(color);
		g.fillOval(x, y, size, size);
//		if(!keep)
//			g.setColor(Color.BLACK);
		for(LocationStreet l : this.adj) {
			LocationStreet s = sssp.get(l.getEdge());
			if(s != null && keep) {
				g.setColor(Color.BLUE);				
			} else {
				g.setColor(Color.BLACK);	
			}
				
			l.draw(g);
		}
	}*/
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public Vertex getVertex() {
		return vertex;
	}

	public void setVertex(Vertex vertex) {
		this.vertex = vertex;
	}
	
	public LinkedList<LocationStreet> getAdjacencyList(){
		return adj;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public int getType() {
		return this.type;
	}
	
	public boolean equals(Location v) {
		return v.getVertex().equals(this.getVertex());
	}
}
