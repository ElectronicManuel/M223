package com.ubs.springer.gui;

import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.UIManager;

import com.ubs.springer.controller.SpringerControllerRecursive;

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
	private String solAmountText = "Amount of Solutions: ";
	
	private JButton nextButton;
	private JSlider delaySlider;
	private JCheckBox auto;
	
	private int max;
	
	private JLabel currentStepsLabel, totalStepsLabel, rollbacksLabel, time, tour, solAmount;
	private JProgressBar progress;
	
	private Locale l = new Locale("de", "CH");
	
	private JFrame gui;
	private SpringerControllerRecursive controller;
	
	public InfoBox(int max, JFrame gui, SpringerControllerRecursive controller) {
		this.max = max;
		this.gui = gui;
		this.controller = controller;
		initComponents();
	}
	
	private void initComponents() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		setTitle("Info");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLayout(new GridLayout(10, 0));
		currentStepsLabel = new JLabel(currentText);
		totalStepsLabel = new JLabel(totalText);
		rollbacksLabel = new JLabel(rollbacksText);
		solAmount = new JLabel(solAmountText); 
		time = new JLabel(timeText);
		tour = new JLabel(tourText);
		progress = new JProgressBar();
		progress.setMaximum(max);
		
		delaySlider = new JSlider(JSlider.HORIZONTAL, 0, 200, new Long((2000-controller.getDelay())/10).intValue());
		delaySlider.addChangeListener(controller);
		delaySlider.setMajorTickSpacing(50);
		delaySlider.setMinorTickSpacing(10);
		delaySlider.setPaintTicks(true);
		delaySlider.setPaintLabels(true);
		
		nextButton = new JButton("Show next result");
		nextButton.setEnabled(false);
		nextButton.addActionListener(controller);
		
		auto = new JCheckBox("Automatic Mode");
		auto.addActionListener(controller);
		
		add(currentStepsLabel);
		add(progress);
		add(totalStepsLabel);
		add(rollbacksLabel);
		add(solAmount);
		add(delaySlider);
		add(time);
		add(tour);
		add(nextButton);
		add(auto);
		
		pack();
		setLocationRelativeTo(gui);
		setVisible(true);
	}

	public void updateInfo(int currentSteps, int totalSteps, int rollbacks, long started, long ended, boolean closed, boolean solutionFound, int amountSolutions) {
		currentStepsLabel.setText(currentText + NumberFormat.getNumberInstance(l).format(currentSteps) + "/" + max);
		totalStepsLabel.setText(totalText + NumberFormat.getNumberInstance(l).format(totalSteps));
		rollbacksLabel.setText(rollbacksText + NumberFormat.getNumberInstance(l).format(rollbacks));
		solAmount.setText(solAmountText + NumberFormat.getNumberInstance(l).format(amountSolutions));
		progress.setValue(currentSteps);
		nextButton.setEnabled(solutionFound);
		long t = ended - started;
		long seconds = t / 1000000000;
		long minutes = seconds / 60;
		seconds = seconds % 60;
		time.setText(timeText + minutes + ":" + seconds);
		tour.setText(tourText + closed);
		repaint();
	}
	
}
