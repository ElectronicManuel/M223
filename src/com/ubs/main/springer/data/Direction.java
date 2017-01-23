package com.ubs.main.springer.data;

public enum Direction {
	
	//      id  r   d
	UP_LEFT(0, -1, -2),
	UP_RIGHT(1, 1, -2),
	RIGHT_UP(2, 2, -1),
	RIGHT_DOWN(3, 2, 1),
	DOWN_RIGHT(4, 1, 2),
	DOWN_LEFT(5, -1, 2),
	LEFT_DOWN(6, -2, 1),
	LEFT_UP(7, -2, -1);
	
	public static int count = 8;
	
	private int id, addX, addY;
	
	private Direction(int id, int addX, int addY) {
		this.id = id;
		this.addX = addX;
		this.addY = addY;
	}
	
	public static Direction getById(int id) {
		return Direction.class.getEnumConstants()[id];
	}
	
	public Direction next() {
		if(id < 7) {
			return getById(id+1);
		}
		else {
			return null;
		}
	}

	public int getAddX() {
		return addX;
	}

	public void setAddX(int addX) {
		this.addX = addX;
	}

	public int getAddY() {
		return addY;
	}

	public void setAddY(int addY) {
		this.addY = addY;
	}

}
