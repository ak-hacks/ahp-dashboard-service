/**
 * 
 */
package com.ft.ahp.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.ft.ahp.RemoteClient;

/**
 * @author anurag.kapur
 *
 */
public class RemoteClientTest {

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.ft.ahp.RemoteClient#play()}.
	 */
	@Test
	public void testPlay() {
		RemoteClient rc = new RemoteClient();
		rc.play();
		assertTrue(true);
	}

}
