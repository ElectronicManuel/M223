package com.ubs.test.phantom;

import java.sql.Connection;
import java.sql.SQLException;

import com.ubs.main.daotest.jdbc.DBConnector;
import com.ubs.main.phantom.dao.LagerDAO;
import com.ubs.main.phantom.data.Artikel;

public class LagerTest {
	
	public static void main(String[] args) {
		LagerTest lt1 = new LagerTest(Connection.TRANSACTION_READ_COMMITTED);
		LagerTest lt2 = new LagerTest(Connection.TRANSACTION_SERIALIZABLE);
		
		try {
			System.out.println("#### TRANSACTION_READ_COMMITTED ####");
			lt1.test();
			System.out.println("#### TRANSACTION_SERIALIZABLE ####");
			lt2.test();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private int isolationLevel;
	
	public LagerTest(int isolationLevel) {
		this.isolationLevel = isolationLevel;
	}
	
	private void test() throws SQLException {
		Connection mainCon = new DBConnector().openConnection(isolationLevel);
		Artikel a1 = new Artikel(1, "HP Laptop", 3);
		LagerDAO.insert(mainCon, a1);
		Artikel a2 = new Artikel(3, "Lenovo Laptop", 5);
		LagerDAO.insert(mainCon, a2);
		Artikel a3 = new Artikel(100, "Mac", 4);
		LagerDAO.insert(mainCon, a3);
		mainCon.commit();
		mainCon.close();
		
		Artikel a4 = new Artikel(2, "Razer", 7);
		Thread th1, th2;
		th1 = new Thread(new Transaction1());
		th2 = new Thread(new Transaction2(a4));
		
		th1.start();
		th2.start();
		
		try {
			Thread.sleep(3500);
			System.out.println("Warte auf Löschung");
			Thread.sleep(11500);
		} catch (InterruptedException e) {
		}
		System.out.println("Lösche Artikel");
		Connection delCon = new DBConnector().openConnection(isolationLevel);
		LagerDAO.delete(delCon, a1);
		LagerDAO.delete(delCon, a2);
		LagerDAO.delete(delCon, a3);
		LagerDAO.delete(delCon, a4);
		
		delCon.commit();
		delCon.close();
	}
	
	// Berechnet Durchschnittslagerbestand
	class Transaction1 implements Runnable {

		@Override
		public void run() {
			try {
				Connection con = new DBConnector().openConnection(isolationLevel);
				int sum = LagerDAO.getBestandSum(con);
				System.out.println("Summe: " + sum);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
				}
				int count = LagerDAO.getArtikelCount(con);
				System.out.println("Anzahl: " + count);
				con.commit();
				con.close();
				int result = sum / count;
				System.out.println(sum + " / " + count + " = " + result);
			}
			catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		
	}
	
	// Fügt neuen Artikel ein
	class Transaction2 implements Runnable {
		
		private Artikel art;
		
		public Transaction2(Artikel a) {
			art = a;
		}

		@Override
		public void run() {
			try {
				Connection con = new DBConnector().openConnection(isolationLevel);
				Thread.sleep(500);
				try {
					LagerDAO.insert(con, art);
					con.commit();
					con.close();
					System.out.println(art.getName() + " zur Datenbank hinzugefügt");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (InterruptedException e) {
			}
		}
	}
	
}
