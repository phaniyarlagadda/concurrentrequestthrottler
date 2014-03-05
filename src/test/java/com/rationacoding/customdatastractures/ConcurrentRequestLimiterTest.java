package com.rationacoding.customdatastractures;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.rationalcoding.customdatastractures.ConcurrentRequestLimiter;
import com.rationalcoding.customdatastractures.TooManyConcurrentRequestsException;


public class ConcurrentRequestLimiterTest {
	ConcurrentRequestLimiter requestLimiter;
	private String userName1 = "micky";
	private String userName2 = "mouse";
	private int concurrentRequestLimit=20;
	
	@Before
	public void beforeTest() {
		requestLimiter = new ConcurrentRequestLimiter(concurrentRequestLimit);
	}

	@After
	public void afterTest() {
		requestLimiter.close();
	}

	@Test
	public void testSingleUser() {

		acquirePermits(userName1,concurrentRequestLimit);
		try {
			requestLimiter.acquire(userName1);
			Assert.fail("Failed to block the request for "+userName1);
		} catch (TooManyConcurrentRequestsException e) {
			// expected

		}catch (InterruptedException e) {
			e.printStackTrace();
			Assert.fail("Test interrupted for "+userName2);
		}

	}
	
	@Test
	public void testAcquireLockAfterRelease() {

		acquirePermits(userName1,concurrentRequestLimit);
		try {
			requestLimiter.acquire(userName1);
		  Assert.fail("Failed to block the request for "+userName1);
		} catch (TooManyConcurrentRequestsException e) {
			// expected

		}catch (InterruptedException e) {
			e.printStackTrace();
			Assert.fail("Test interrupted for "+userName2);
		}
		
		requestLimiter.release(userName1);
		try {
			requestLimiter.acquire(userName1);
		  
		} catch (TooManyConcurrentRequestsException e) {
			Assert.fail("Failed to acquire lock for "+userName1);
		}catch (InterruptedException e) {
			e.printStackTrace();
			Assert.fail("Test interrupted for "+userName2);
		}

	}
	
	
	@Test
	public void testTwoUsers() {
		
		acquirePermits(userName1,concurrentRequestLimit);
		try {
			requestLimiter.acquire(userName2);
		} catch (TooManyConcurrentRequestsException e) {
			e.printStackTrace();
			Assert.fail("Unable to acquire for second user "+userName2);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Assert.fail("Test interrupted for "+userName2);
		}

	}
	
	private void acquirePermits(String userId, int permitCount) {
		try {

			for (int i = 0; i < concurrentRequestLimit; i++) {
				requestLimiter.acquire(userName1);
			}

		} catch (TooManyConcurrentRequestsException e) {
			e.printStackTrace();
			Assert.fail("Failed to acquire permit for "+userId);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Assert.fail("Test interrupted for "+userName2);
		}
	}
	

}
