package com.ubs.main.springer.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.UIManager;

import com.ubs.main.springer.controller.SpringerControllerRecursive;
import com.ubs.main.springer.data.Field;
import com.ubs.main.springer.data.Step;

public class SpringerGUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int sizeY;
	private int sizeX;
	private SpringerControllerRecursive controller;
	
	public SpringerGUI(SpringerControllerRecursive controller, int sizeX, int sizeY) {
		super();
		
		this.controller = controller;
		
		this.sizeY = sizeY;
		this.sizeX = sizeX;
		
		initComponents();
	}
	
	private void initComponents() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		setLayout(new GridLayout(sizeY, sizeX, 2, 2));
		setSize(800, 800);
		
		for(int x = 0; x < sizeX; x++) {
			for(int y = 0; y < sizeY; y++) {
				getFields()[x][y] = new Field();
				add(getFields()[x][y]);
			}
		}
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Springer");
		setVisible(true);
	}
	
	public Field[][] getFields() {
		return controller.getFields();
	}
	
	public void update() {
		List<Step> steps = controller.getSteps();
		if(steps.size() > 0) {
			Field first = controller.getField(steps.get(0).getField());
			first.setStart(true);
		}
		if(steps.size() > 1) {
			for(int i = 0; i < getFields().length; i++) {
				for(int y = 0; y < getFields()[i].length; y++) {
					controller.getFields()[i][y].setCurrent(false);
				}
			}
			Field newest = controller.getField(steps.get(steps.size()-1).getField());
			newest.setCurrent(true);
		}
		for(int i = 0; i < getFields().length; i++) {
			for(int y = 0; y < getFields()[i].length; y++) {
				controller.getFields()[i][y].repaint();
			}
		}
		
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		List<Step> steps = controller.getSteps();
		for(int i = 0; i < steps.size()-1; i++) {
			try {
				Field old = controller.getField(steps.get(i).getField());
				Field next = controller.getField(steps.get(i+1).getField());
				g.setColor(Color.black);
				g.drawLine(
						old.getX() + old.getWidth()/2 + getWidthDiff(),
						old.getY() + old.getHeight()/2 + getHeightDiff(),
						next.getX() + next.getWidth()/2 + getWidthDiff(),
						next.getY() + next.getHeight()/2 + getHeightDiff()
						);
			}
			catch(Exception ex) {}
		}
	}
	
	private int getWidthDiff() {
		return 8;
	}
	
	private int getHeightDiff() {
		return 30;
	}

}
