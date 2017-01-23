package com.ubs.main.springer.controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.ubs.main.springer.data.Field;
import com.ubs.main.springer.data.Step;
import com.ubs.main.springer.gui.InfoBox;
import com.ubs.main.springer.gui.ResultBox;
import com.ubs.main.springer.gui.SpringerGUI;

public class SpringerControllerRecursive implements ActionListener, ChangeListener {
	
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
	private int amountSolutions = 0;
	
	public long delay = 500;
	
	private boolean closed = false;
	
	private boolean resume = false;
	private boolean auto = false;
	
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
		info.updateInfo(steps.size(), stepsTotal, rollbacks, started, ended, closed, solutionFound, amountSolutions);
		gui.update();
		
		info.setVisible(false);
		gui.setVisible(false);
		
		System.out.println("Ended, opening result frame");
		new ResultBox(stepsTotal, rollbacks, amountSolutions, started, ended);
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
		info.updateInfo(steps.size(), stepsTotal, rollbacks, started, ended, closed, solutionFound, amountSolutions);
		
		if(steps.size() >= SIZE_X*SIZE_Y) {
			amountSolutions++;
			
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
			info.updateInfo(steps.size(), stepsTotal, rollbacks, started, ended, closed, solutionFound, amountSolutions);
			while(!resume && !auto) {
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
		if(e.getSource() instanceof JButton) {
			if(!resume) {
				resume = true;
			}
		}
		else if(e.getSource() instanceof JCheckBox) {
			boolean checked = ((JCheckBox)e.getSource()).isSelected();
			auto = checked;
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider slider = (JSlider)e.getSource();
		int value = slider.getValue();
		setDelay((slider.getMaximum()-value)*10);
	}
	
	public void setResume(boolean resume) {
		this.resume = resume;
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}
	
}
