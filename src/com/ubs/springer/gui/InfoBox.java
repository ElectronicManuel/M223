package com.ubs.springer.gui;

import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class InfoBox extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3448102831241010912L;
	private String currentText = "Progress: ";
	private String totalText = "Total Steps: ";
	private String rollbacksText = "Rollbacks: ";
	private String timeText = "Elapsed Time: ";
	private String tourText = "Tour closed: ";
	
	private int max;
	
	private JLabel currentStepsLabel, totalStepsLabel, rollbacksLabel, time, tour;
	private JProgressBar progress;
	
	private Locale l = new Locale("de", "CH");
	
	private JFrame gui;
	
	public InfoBox(int max, JFrame gui) {
		this.max = max;
		this.gui = gui;
		initComponents();
	}
	
	private void initComponents() {
		setTitle("Info");
		setLayout(new GridLayout(6, 0));
		currentStepsLabel = new JLabel(currentText);
		totalStepsLabel = new JLabel(totalText);
		rollbacksLabel = new JLabel(rollbacksText);
		time = new JLabel(timeText);
		tour = new JLabel(tourText);
		progress = new JProgressBar();
		progress.setMaximum(max);
		add(currentStepsLabel);
		add(progress);
		add(totalStepsLabel);
		add(rollbacksLabel);
		add(time);
		add(tour);
		setSize(300, 100);
		setLocationRelativeTo(gui);
		setVisible(true);
	}

	public void updateInfo(int currentSteps, int totalSteps, int rollbacks, long started, long ended, boolean closed) {
		currentStepsLabel.setText(currentText + NumberFormat.getNumberInstance(l).format(currentSteps) + "/" + max);
		totalStepsLabel.setText(totalText + NumberFormat.getNumberInstance(l).format(totalSteps));
		rollbacksLabel.setText(rollbacksText + NumberFormat.getNumberInstance(l).format(rollbacks));
		progress.setValue(currentSteps);
		long t = ended - started;
		long seconds = t / 1000000000;
		long minutes = seconds / 60;
		seconds = seconds % 60;
		time.setText(timeText + minutes + ":" + seconds);
		tour.setText(tourText + closed);
		repaint();
	}
	
}
