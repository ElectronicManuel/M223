package com.ubs.test.shop;


import java.sql.SQLException;
import java.util.Random;

import org.junit.Test;

import com.ubs.main.shop.beans.Artikel;
import com.ubs.main.shop.database.DynamicDAO;

public class ShopTest {

	@Test
	public void test() throws IllegalArgumentException, IllegalAccessException, SQLException {
		DynamicDAO<Artikel> dao = new DynamicDAO<Artikel>(Artikel.class);
		dao.insert(new Artikel(new Random().nextInt(1000), "Guter Artikel", new Random().nextInt(1000)));
	}

}
