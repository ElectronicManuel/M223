package com.ubs.springer.controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;

import com.ubs.springer.data.Field;
import com.ubs.springer.data.Step;
import com.ubs.springer.gui.InfoBox;
import com.ubs.springer.gui.SpringerGUI;

public class SpringerControllerRecursive implements SController  {
	
	public SpringerGUI gui;
	private InfoBox info;
	private List<Step> steps = new ArrayList<Step>();
	private static final int SIZE_X = 8;
	private static final int SIZE_Y = 8;
	
	private static final int START_X = 0;
	private static final int START_Y = 0;
	
	private Field[][] fields;
	
	private long started;
	private long ended;
	
	private int stepsTotal = 0;
	private int rollbacks = 0;
	
	public long delay = 500;
	
	private boolean closed = false;
	
	private boolean resume = false;
	private boolean solutionFound = false;
	
	public static void main(String[] args) {
		new SpringerControllerRecursive();
	}
	
	public SpringerControllerRecursive() {
		// init
		fields = new Field[SIZE_X][SIZE_Y];
		gui = new SpringerGUI(this, SIZE_X, SIZE_Y);
		info = new InfoBox(SIZE_X*SIZE_Y, gui, this);
		started = System.nanoTime();
		ended = System.nanoTime();
		
		// Run
		doRound(new Step(new Point(START_X, START_Y)));
		
		
	}
	
	public void doRound(Step step) {
		// Set flags for current
		Field currentField = getField(step.getField());
		currentField.setVisited(true);
		if(steps.size() == 0) {
			currentField.setStart(true);
		}
		steps.add(step);
		gui.update();
		stepsTotal++;
		ended = System.nanoTime();
		info.updateInfo(steps.size(), stepsTotal, rollbacks, started, ended, closed, solutionFound);
		
		if(steps.size() >= SIZE_X*SIZE_Y) {
			
			// Check if tour is closed
			Point first = steps.get(0).getField();
			for(Point toCheck : step.getPossibilities()) {
				if(getField(toCheck) != null) {
					if(toCheck.x == first.x && toCheck.y == first.y) {
						closed = true;
						System.out.println("Tour is closed");
					}
				}
			}
			
			solutionFound = true;
			info.updateInfo(steps.size(), stepsTotal, rollbacks, started, ended, closed, solutionFound);
			while(!resume) {
				System.out.println("PAUSING - " + resume);
			}
		}
		
		resume = false;
		
		for(Point p : step.getPossibilities()) {
			currentField.setCurrent(true);
			Field f = getField(p);
			if(f != null) {
				if(!f.isVisited()) {
					try {
						Thread.sleep(delay);
					} catch (InterruptedException e) {
					}
					currentField.setCurrent(false);
					doRound(new Step(p));
				}
			}
		}
		steps.remove(step);
		rollbacks++;
		currentField.setVisited(false);
		currentField.setCurrent(false);
	}
	
	public Field[][] getFields() { return fields; }
	
	public Field getField(Point p) {
		try {
			return getFields()[p.x][p.y];
		}
		catch(Exception ex) {
			return null;
		}
	}
	
	public List<Step> getSteps() { return steps; }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(!resume) {
			resume = true;
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider slider = (JSlider)e.getSource();
		int value = slider.getValue();
		setDelay((slider.getMaximum()-value)*10);
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}
	
}