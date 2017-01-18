package test.util;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.ubs.util.Nguyen;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ListTest {
	
	private static Nguyen<String> list;

	@BeforeClass
	public static void setUp() throws Exception {
		list = new Nguyen<String>();
	}

	@Test
	public void atestAdd() {
		System.out.println("#### Add ####");
		list.add("Emanuel");
		list.add("Minhkhanh");
		list.add("Patric");
		list.add("Johannes");
		list.add("Leon");
		assertTrue(list.size() == 5);
		list.print();
	}
	
	@Test
	public void btestIterate() {
		System.out.println("#### Iterate ####");
		int i = 0;
		for(String s : list) {
			i++;
			System.out.println(s);
		}
		for(String s : list) {
			i++;
			System.out.println(s);
		}
		assertTrue(i == 10);
	}
	
	@Test
	public void ctestRemoveIndex() {
		System.out.println("#### Remove Index ####");
		assertTrue(list.remove(3));
		list.print();
		assertTrue(list.remove(2));
		list.print();
		assertTrue(list.remove(0));
		list.print();
		boolean remove = list.remove(4);
		System.out.println("removing 4th element: " + remove);
		assertTrue(!remove);
	}
	
	@Test
	public void dtestGet() {
		System.out.println("#### Get ####");
		list.print();
		String mk = list.get(0);
		System.out.println(mk);
		assertTrue(mk.equals("Minhkhanh"));
	}
	
	@Test
	public void etestRemoveElement() {
		System.out.println("#### Remove Element ####");
		assertTrue(list.remove("Leon"));
		list.print();
		assertTrue(!list.remove("Johannes"));
		list.print();
	}

}
