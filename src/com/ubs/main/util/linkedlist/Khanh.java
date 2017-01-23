package com.ubs.main.util.linkedlist;

public class Khanh<T> {
	
	private T value;
	
	private Khanh<T> previous;
	private Khanh<T> next;
	
	public Khanh(T value) {
		this.value = value;
	}
	
	public boolean hasNext() {
		return next != null;
	}
	
	public boolean hasPrevious() {
		return previous != null;
	}
	
	public T getValue() {
		return value;
	}
	
	public void setValue(T value) {
		this.value = value;
	}
	
	public Khanh<T> next() {
		return next;
	}
	
	public Khanh<T> previous() {
		return previous;
	}
	
	public void setNext(Khanh<T> next) {
		this.next = next;
	}
	
	public void setPrevious(Khanh<T> previous) {
		this.previous = previous;
	}

}
