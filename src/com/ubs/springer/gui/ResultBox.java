package com.ubs.springer.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class ResultBox extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1517230086620454071L;
	
	private Locale l = new Locale("de", "CH");
	
	private JButton exit;
	
	private int stepsTotal;
	private int rollbacks;
	private int amountSolutions;
	private long started;
	private long ended;

	public ResultBox(int stepsTotal, int rollbacks, int amountSolutions, long started, long ended) {
		this.stepsTotal = stepsTotal;
		this.rollbacks = rollbacks;
		this.amountSolutions = amountSolutions;
		this.started = started;
		this.ended = ended;
		init();
	}
	
	private void init() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Result");
		setLayout(new BorderLayout());
		
		JPanel stats = new JPanel();
		stats.setLayout(new GridLayout(4, 0));
		
		JLabel steps, rollB, amountSol, time;
		steps = new JLabel("Steps: " + NumberFormat.getNumberInstance(l).format(stepsTotal));
		rollB = new JLabel("Rollbacks: " + NumberFormat.getNumberInstance(l).format(rollbacks));
		amountSol = new JLabel("Solutions found: " + NumberFormat.getNumberInstance(l).format(amountSolutions));
		
		
		long t = ended - started;
		long seconds = t / 1000000000;
		long minutes = seconds / 60;
		seconds = seconds % 60;
		time = new JLabel("Elapsed Time: " + minutes + ":" + seconds);
		
		stats.add(steps);
		stats.add(rollB);
		stats.add(amountSol);
		stats.add(time);
		
		add(stats, BorderLayout.CENTER);
		
		exit = new JButton("Exit Program");
		exit.addActionListener(this);
		add(exit, BorderLayout.SOUTH);
		
		setSize(200, 100);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exit) {
			System.exit(0);
		}
	}

}
