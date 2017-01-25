package com.ubs.main.shop.interfaces;

import java.util.List;

import com.ubs.main.shop.beans.Artikel;

public interface DataAccessObject {
	
	public int insert();
	public int update();
	public int delete();
	public Artikel selectById();
	public List<Artikel> selectByQuery();

}
