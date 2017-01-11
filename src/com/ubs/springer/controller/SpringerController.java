package com.ubs.springer.controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.ubs.springer.data.Field;
import com.ubs.springer.data.Step;
import com.ubs.springer.gui.InfoBox;
import com.ubs.springer.gui.SpringerGUI;

public class SpringerController {
	
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
	
	private long delay = 1000;
	
	private boolean closed = false;
	
	public static void main(String[] args) {
		new SpringerController();
	}
	
	public SpringerController() {
		// init
		fields = new Field[SIZE_X][SIZE_Y];
		gui = new SpringerGUI(this, SIZE_X, SIZE_Y);
		info = new InfoBox(SIZE_X*SIZE_Y, gui);
		steps.add(new Step(new Point(START_X, START_Y)));
		getField(steps.get(0).getField()).setStart(true);
		started = System.nanoTime();
		ended = System.nanoTime();
		
		// Run
		while(steps.size() < SIZE_X*SIZE_Y) {
			try {
				Thread.sleep(delay);
				doRound();
			} catch (InterruptedException e) {
			}
		}
		
		// Check if tour is closed
		Point first = steps.get(0).getField();
		for(Point toCheck : steps.get(0).getPossibilities()) {
			if(getField(toCheck) != null) {
				if(toCheck.x == first.x && toCheck.y == first.y) {
					closed = true;
					System.out.println("Tour is closed");
				}
			}
		}
	}
	
	public void doRound() {
		// Set flags for current
		Step current = steps.get(steps.size()-1);
		Field currentField = getField(current.getField());
		currentField.setVisited(true);
	
		// Get next field
		
		boolean found = false;
		List<Point> possibilities = current.getPossibilities();
		for(int i = current.getTried(); i<possibilities.size(); i++) {
			if(getField(possibilities.get(i)) != null) {
				if(!getField(possibilities.get(i)).isVisited()) {
					current.setTried(i+1);
					Step nextStep = new Step(possibilities.get(i));
					steps.add(nextStep);
					found = true;
					break;
				}
			}
		}
		/*while(true) {
			next = getField(current.getNextField());
			if(next == null) {
				// Check for next
				if(current.hasNext()) {
					current.next();
				}
				else {
					break;
				}
			}
			else {
				if(next.isVisited()) {
					// Check for next
					if(current.hasNext()) {
						current.next();
					}
					else {
						break;
					}
				}
				// Valid field
				else {
					Step nextStep = new Step(current.getNextField());
					steps.add(nextStep);
					found = true;
					break;
				}
			}
		}*/
		if(!found) {
			rollback();
		}
		gui.update();
		stepsTotal++;
		ended = System.nanoTime();
		info.updateInfo(steps.size(), stepsTotal, rollbacks, started, ended, closed);		
	}
	
	/*public void doRound1() {
		// Make last not current
		if(steps.size() >= 2) {
			Step last = steps.get(steps.size()-2);
			getField(last.getField()).setCurrent(false);
		}
		
		// Set flags for current
		Step current = steps.get(steps.size()-1);
		Field currentField = getField(current.getField());
		currentField.setCurrent(true);
		currentField.setVisited(true);
		
		// Get next field
		Field next = null;
		// Next valid field
		//boolean found = false;
		while(next == null && current.hasNext()) {
		//while((next == null || next.isVisited()) && current.hasNext()) {
			current.next();
			next = getField(current.getNextField());
			/*if(next != null) {
				if(!next.isVisited()) {
					Step nextStep = new Step(current.getNextField());
					steps.add(nextStep);
					found = true;
				}
				else {
					current.next();
				}
			}
			else {
				current.next();
			}
		}
		if(!found) {
			rollback();
		}
		if(next != null) {
			// Next not visited
			while((next == null || next.isVisited()) && current.hasNext()) {
				current.next();
				next = getField(current.getNextField());
			}
			// Next valid and not visited
			if(next != null && !next.isVisited()) {
				Step nextStep = new Step(current.getNextField());
				steps.add(nextStep);
			}
			// No valid field found
			else {
				rollback();
			}
		}
		// No valid field found
		else {
			rollback();
		}
		stepsTotal++;
		ended = System.nanoTime();
		info.updateInfo(steps.size(), stepsTotal, rollbacks, started, ended, closed);
	}*/
	
	public void rollback() {
		rollbacks++;
		Step current = steps.get(steps.size()-1);
		Field currentField = getField(current.getField());
		currentField.setVisited(false);
		steps.remove(current);
		
		current = steps.get(steps.size()-1);
		currentField = getField(current.getField());
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
	
}
