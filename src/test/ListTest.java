package test;

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
		list.add("Emanuel");
		list.add("Minhkhanh");
		list.add("Patric");
		list.add("Johannes");
		list.add("Leon");
		assertTrue(list.size() == 5);
		list.print();
	}
	
	@Test
	public void btestRemoveIndex() {
		assertTrue(list.remove(3));
		list.print();
		assertTrue(list.remove(2));
		list.print();
		boolean remove = list.remove(4);
		System.out.println("removing 4th element: " + remove);
		assertTrue(!remove);
	}
	
	@Test
	public void ctestGet() {
		String johannes = list.get(2);
		System.out.println(johannes);
		assertTrue(johannes.equals("Johannes"));
	}
	
	@Test
	public void dtestRemoveElement() {
		assertTrue(list.remove("Leon"));
		list.print();
		assertTrue(!list.remove("Minhkhanh"));
		list.print();
	}

}
