/**
 * 
 */
package com.ft.ahp.test;

import com.ft.ahp.remote.RemoteClient;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author anurag.kapur
 *
 */
public class RemoteClientTest {

	/**
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.ft.ahp.remote.RemoteClient#}.
	 */
	@Test
	public void testGetProgrammeStatus() {
		RemoteClient rc = new RemoteClient();
		rc.getProgrammeStatus("newsroomsystems");
		assertTrue(true);
	}

}
