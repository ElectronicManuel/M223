package test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.ubs.daotest.dao.MitarbeiterDAO;
import com.ubs.daotest.data.Mitarbeiter;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MitarbeiterTest {
	
	private static Mitarbeiter m;

	@BeforeClass
	public static void init() throws Exception {
		m = new Mitarbeiter();
		m.setName("Nguyen");
		m.setVorname("Minhkhanh");
		m.setAbteilung("Microsoft");
		m.setEmail("nguyenm@bzz.ch");
		m.setTelefon("666");
		m.setFax("333");
		m.setGebauede("Dieses Da");
		m.setGbdatum(new Date());
	}

	@Test
	public void atestInsert() throws SQLException {
		MitarbeiterDAO.insert(m);
		
		System.out.println("Mitarbeiter hat nun die ID: " + m.getPersid());
		assertNotNull(m.getPersid());
	}
	
	@Test
	public void btestUpdate() throws SQLException {
		m.setFax("666");
		assertTrue(MitarbeiterDAO.update(m) > 0);
	}
	
	@Test
	public void ctestById() throws SQLException {
		System.out.println("####");
		Mitarbeiter m2 = MitarbeiterDAO.getById(m.getPersid());
		System.out.println("Lokaler Mitarbeiter Fax: " + m.getFax());
		System.out.println("DB Mitarbeiter Fax: " + m2.getFax());
		assertTrue(m.getFax().equals(m2.getFax()));
	}
	
	@Test
	public void dtestByQuery() throws SQLException {
		System.out.println("####");
		Mitarbeiter m2 = MitarbeiterDAO.getByQuery("SELECT * FROM Mitarbeiter WHERE persid=" + m.getPersid());
		System.out.println("Lokaler Mitarbeiter Fax: " + m.getFax());
		System.out.println("DB Mitarbeiter Fax: " + m2.getFax());
		assertTrue(m.getFax().equals(m2.getFax()));
	}
	
	@Test
	public void etestCount() throws SQLException {
		System.out.println("####");
		int count = MitarbeiterDAO.getMitarbeiterCount();
		System.out.println("Es gibt " + count + " Mitarbeiter");
		assertTrue(count > 0);
	}
	
	@Test
	public void ftestDelete() throws SQLException {
		System.out.println("####");
		int dels = MitarbeiterDAO.delete(m);
		System.out.println("Es wurden " + dels + " Mitarbeiter gelöscht");
		assertTrue(dels > 0);
	}

}
