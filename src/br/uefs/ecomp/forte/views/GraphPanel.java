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
package br.uefs.ecomp.forte.views;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import br.uefs.ecomp.forte.interfaces.GraphListener;
import br.uefs.ecomp.forte.utils.Edge;
import br.uefs.ecomp.forte.utils.Graph;
import br.uefs.ecomp.forte.utils.Vertex;
import br.uefs.ecomp.forte.views.components.Location;
import br.uefs.ecomp.forte.views.components.LocationStreet;

public class GraphPanel extends JPanel implements GraphListener {
	
	public Map<Vertex, Location> locations;
	public Map<Edge, LocationStreet> roads = new HashMap<Edge, LocationStreet>();
	public Map<Edge, LocationStreet> ShortPath = new HashMap<Edge,LocationStreet>();
	private Location active = null;
	public Location activePark = null;
	public Location activeBank = null;
	public Location activeCollect = null;
	private int size = 0;
	
	public Location detectColision(int x, int y) {
		Location find = null;
		Iterator<Map.Entry<Vertex,Location>> it = locations.entrySet().iterator();
		while(find == null && it.hasNext()) {
			Location l = it.next().getValue();
			int xf = l.getX();
			int yf = l.getY();
			int r = l.getRadius();
			System.out.println("Cliquei em: " + x + " , " + y +
					" O ponto está em: " + xf + " , " + yf + " e o raio é: " + r);
			if(x >= xf && x <= xf+(r*2) &&
					y >= yf && y <= yf+(r*2)) {
				find = l;
				System.out.println("COLIDIU");
			}			
		}
		return find;
	}
	
	public void setActive(Location l) {
		this.active = l;
	}
	
	public Location getActive() {
		return this.active;
	}
	
	public int genRan(int lower, int upper) {
		int a = (int) (Math.random() * (upper - lower)) + lower;
		System.out.println(a);
		return a;
	}
	
	private void addLocation(Vertex vertex, int i) {
		int lowerx =0, lowery = 0, upperx = 1066, uppery = 608;
		Location e = new Location("V" + vertex.getId() , genRan(lowerx,upperx),genRan(lowery,uppery));
		e.setVertex(vertex);
		locations.put(vertex,e);
	}
	
	public void buildGraph(Graph g) {
		this.locations = new HashMap<Vertex,Location>();
		int size = g.size();
		for(int x = 0; x < size; x++) {
			this.addLocation(g.getVertex(x),x);
		}
		
		for(int x = 0; x < size; x++) {
			Vertex l = g.getVertex(x);
			LinkedList<Edge> it = l .getAdjList();
			Location c = locations.get(l);
			for(Edge e: it) {
				Vertex v = e.getLastVertex();
				LocationStreet road = c.addAdjacent(locations.get(v), e);
				this.roads.put(e, road);
			}
		 
		}
		repaint();
	}
	
	public GraphPanel(Graph g) {
		this();
		buildGraph(g);
  
		
	}
	
	public GraphPanel() {
		this.locations = new HashMap<Vertex,Location>();
		MouseListener mouseListener = new MouseAdapter() {
			public void mousePressed(MouseEvent mEvent) {
				int btn = mEvent.getModifiers();
				if((btn & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
					Point p = mEvent.getLocationOnScreen();
					int x = mEvent.getX();
					int y = mEvent.getY();
					Location l = detectColision(x,y);
					setActive(l);
					repaint();
					System.out.println(mEvent.getX() + " - " + mEvent.getY());
				}
			}
		};
		this.addMouseListener(mouseListener);
		
		MouseMotionListener mouserMotion = new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Location active = GraphPanel.this.getActive();
				if(active != null) {
					active.setX(e.getX());
					active.setY(e.getY());
					repaint();
				}
				
			}
		};
		this.addMouseMotionListener(mouserMotion);
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(Location l : this.locations.values()) {
			//LocationStreet sssp = ShortPath.get(l.getVertex());
			if(this.active != null && this.active.equals(l) == true) {
				l.draw(g, Color.BLACK);
			} else if(l.getType() == Location.TYPE_COLLECT){
				l.draw(g,Color.RED);
			} else if(l.getType() == Location.TYPE_PARK){
				l.draw(g,Color.GREEN);
			} else if(l.getType() == Location.TYPE_BANK){
				l.draw(g,Color.ORANGE);
			} else /*if (sssp != null)*/ {
				l.draw(g);
			}/* else {
			 	l.draw(g);
			}*/
			
		}
		for(LocationStreet l : this.ShortPath.values()) {
			g.setColor(Color.BLUE);
			l.draw(g);
		}
		
	}
	
	public void clearType(int type) {
		active = null;
		for(Location l : locations.values()) {
			if(l.getType() == type) {
				l.setType(Location.TYPE_NEUTRAL);
			}	
		}
	}
	
	public void setPark() {
		Location active = this.active;
		clearType(Location.TYPE_PARK);
		if(active != null) {
			this.activePark = active;
			active.setType(Location.TYPE_PARK);
		}
		repaint();
	}
	
	public void setCollect() {
		Location active = this.active;
		clearType(Location.TYPE_COLLECT);
		
		if(active != null) {
			this.activeCollect = active;
			active.setType(Location.TYPE_COLLECT);
		}
		repaint();
	}
	
	public void setBank() {
		Location active = this.active;
		clearType(Location.TYPE_BANK);
		
		if(active != null) {
			this.activeBank = active;
			active.setType(Location.TYPE_BANK);
		}
		repaint();
	}
	
	public void notify(Graph g) {
		
//		buildGraph(g);
		repaint();
	}
	
	public void createLocation(Vertex e) {
		this.addLocation(e, this.locations.size());
		repaint();
	}	
	
	public Location findByVertex(Vertex v) {
		Location loc = null;
		for(Location l: locations.values()) {
			if(v.equals(l.getVertex())) {
				loc = l;
			}
		}
		return loc;
	}
	
	public void addAdjacency(Vertex v1, Vertex v2, Edge e) {
		Location location = findByVertex(v1); 
		LocationStreet road = location.addAdjacent(findByVertex(v2), e);
		this.roads.put(e, road);
		repaint();
	}
	
	
	public void removeLocation(Vertex e) {
		Location loc = locations.remove(e);

		for(Location l: locations.values()) {
			LinkedList<LocationStreet> adj = l.getAdjacencyList(); 
			LinkedList<LocationStreet> road = new LinkedList<LocationStreet>();
		
			for(LocationStreet ls: adj) {
				if(loc.equals(ls.getLocation())) road.add(ls);
					
			}
			Iterator<LocationStreet> it = road.iterator();
			while(it.hasNext()) {
				LocationStreet ls = it.next();
				adj.remove(ls);
				this.roads.remove(ls);
				this.ShortPath.remove(ls);
			}
			
		}

	}
}
