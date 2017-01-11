package com.ubs.springer.controller;

import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.event.ChangeListener;

import com.ubs.springer.data.Field;
import com.ubs.springer.data.Step;

public interface SController extends ActionListener, ChangeListener {
	
	public List<Step> getSteps();
	public Field[][] getFields();
	public Field getField(Point p);
	
	public long getDelay();

}
