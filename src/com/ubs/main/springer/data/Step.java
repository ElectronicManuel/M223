package com.ubs.main.springer.data;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Step {
	
	private Point field;
	private Direction dirTaken = Direction.getById(0);
	
	private int tried = 0;
	
	public Step(Point field) {
		this.field = field;
	}
	
	private Direction next() {
		dirTaken = dirTaken.next();
		
		return dirTaken;
	}
	
	private Point getNextField() {
		if(dirTaken != null) {
			Point p = new Point(field.x + dirTaken.getAddX(), field.y + dirTaken.getAddY());
			return p;
		}
		else {
			return null;
		}
	}
	
	private boolean hasNext() {
		if(dirTaken == null) {
			return false;
		}
		if(dirTaken.next() == null) {
			return false;
		}
		return true;
	}
	
	public List<Point> getPossibilities() {
		List<Point> list = new ArrayList<Point>();
		for(int i = 0; i < Direction.count; i++) {
			list.add(getNextField());
			if(hasNext()) {
				next();
			}
		}
		return list;
	}
	
	public Point getField() {
		return field;
	}

	public void setField(Point field) {
		this.field = field;
	}

	public Direction getDirTaken() {
		return dirTaken;
	}

	public void setDirTaken(Direction dirTaken) {
		this.dirTaken = dirTaken;
	}

	public int getTried() {
		return tried;
	}

	public void setTried(int tried) {
		this.tried = tried;
	}
	
}
