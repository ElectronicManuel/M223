package com.ubs.meeting.db;

public interface DBService<T> {
	
	public int insert(T toInsert);
	
	public int delete(T toDelete);
	
	public int update(T toUpdate);
	
	public T getById(int id);
	
	public T getByQuery(String query);

}
