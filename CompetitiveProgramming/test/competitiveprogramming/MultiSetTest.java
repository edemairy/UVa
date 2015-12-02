/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package competitiveprogramming;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Erwan Demairy <Erwan.Demairy@inria.fr>
 */
public class MultiSetTest {
	
	public MultiSetTest() {
	}

	/**
	 * Test of add method, of class MultiSet.
	 */
	@Test
	public void testAdd() {
		System.out.println("add");
		Object e = null;
		MultiSet instance = new MultiSet();
		boolean expResult = false;
		boolean result = instance.add(5);
		assertEquals(false, result);
		result = instance.add(5);
		assertEquals(true, result);
		// TODO review the generated test code and remove the default call to fail.
	}

	/**
	 * Test of remove method, of class MultiSet.
	 */
	@Test
	public void testRemove() {
		System.out.println("remove");
		Object e = null;
		MultiSet instance = new MultiSet();
		boolean expResult = false;
		boolean result = instance.remove(e);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of pollFirst method, of class MultiSet.
	 */
	@Test
	public void testPollFirst() {
		System.out.println("pollFirst");
		MultiSet instance = new MultiSet();
		Object expResult = null;
		Object result = instance.pollFirst();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of pollLast method, of class MultiSet.
	 */
	@Test
	public void testPollLast() {
		System.out.println("pollLast");
		MultiSet instance = new MultiSet();
		Object expResult = null;
		Object result = instance.pollLast();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
	
}
