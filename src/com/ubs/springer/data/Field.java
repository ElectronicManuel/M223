package com.ubs.springer.data;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Field extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5719381714738952897L;
	private boolean visited = false;
	private boolean current = false;
	private boolean start = false;
	
	private JFrame gui;
	
	public Field(JFrame gui) {
		setBorder(null);
		setOpaque(true);
		this.gui = gui;
		repaint();
		gui.repaint();
	}
	
	public void repaint() {
		Color c = Color.white;
		if(isVisited()) {
			c = Color.gray;
		}
		if(isCurrent()) {
			c = Color.red;
		}
		if(start) {
			c = Color.green;
		}
		
		setBackground(c);
		
		super.repaint();
	}
	
	public boolean isVisited() { return visited; }
	
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public boolean isCurrent() {
		return current;
	}

	public void setCurrent(boolean current) {
		this.current = current;
	}
	
	public void setStart(boolean start) {
		this.start = start;
	}

	public boolean isStart() {
		return start;
	}

}