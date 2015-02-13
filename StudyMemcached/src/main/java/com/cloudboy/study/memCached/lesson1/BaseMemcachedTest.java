package com.cloudboy.study.memCached.lesson1;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;

import net.spy.memcached.MemcachedClient;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

public class BaseMemcachedTest {
	private static XStream xs = new XStream();
	private MemcachedClient memcachedClient = null;
	
	@Before
	public void setUp() throws Exception {
		memcachedClient = new MemcachedClient(new InetSocketAddress("192.168.1.106", 11211));
	}

	@Test
	public void testSetAndGet() throws IOException {
		// Store a User object to cache for an hour
		User user1 = new User();
		user1.setUserName("张三");
		user1.setPassword("12345678");
		memcachedClient.set("user1", 3600, user1); // 3600表示3600秒
		
		// Get the user object
		User userRetrieved = (User)memcachedClient.get("user1");
		System.out.println("userRetrieved:\r\n" + xs.toXML(userRetrieved));
		assertTrue(userRetrieved != null);
	}
	
	@Test
	public void testDelete() throws IOException {
		// Store a User object to cache
		User user2 = new User();
		user2.setUserName("李四");
		user2.setPassword("12345678");
		memcachedClient.set("user2", 3600, user2);

		// Get the user object
		User userRetrieved1 = (User) memcachedClient.get("user2");
		System.out.println("userRetrieved1:\r\n" + xs.toXML(userRetrieved1));
		assertTrue(userRetrieved1 != null);
		
		// Delete the user object
		memcachedClient.delete("user2");
		
		// Get the user object
		User userRetrieved2 = (User) (memcachedClient.get("user2"));
		assertTrue(userRetrieved2 == null);
	}
	
	@Test
	public void testGetBulk() throws IOException {
		// Store multiple objects to cache
		User user1 = new User();
		user1.setUserName("张三-1");
		user1.setPassword("12345678");
		memcachedClient.set("user1", 3600, user1);
		
		User user2 = new User();
		user2.setUserName("张三-2");
		user2.setPassword("12345678");
		memcachedClient.set("user2", 3600, user2);
		
		User user3 = new User();
		user3.setUserName("张三-3");
		user3.setPassword("12345678");
		memcachedClient.set("user3", 3600, user3);
		
		// 一次性读取多个数据，只用和服务器交互一次，因此效率比一次次读取高
		String[] keys = {"user1", "user2", "user3"};
		Map<String, Object> usersRetrieved = memcachedClient.getBulk(keys);
		for(Object user : usersRetrieved.values()) {
			System.out.println("userRetrieved:\r\n" + xs.toXML(user));
		}
		assertTrue(usersRetrieved != null);
		assertTrue(usersRetrieved.size() == 3);
	}
	
	@Test
	public void testTimeout() throws IOException {
		// Store a User object to cache for an hour
		User user = new User();
		user.setUserName("张三");
		user.setPassword("12345678");
		memcachedClient.set("user", 10, user); // 10表示10秒
		
		// Get the user object
		User userRetrieved = (User)memcachedClient.get("user");
		System.out.println("userRetrieved:\r\n" + xs.toXML(userRetrieved));
		assertTrue(userRetrieved != null);
		
		try {
			Thread.sleep(11000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		userRetrieved = (User)memcachedClient.get("user");
		System.out.println("userRetrieved after timeout time:\r\n" + xs.toXML(userRetrieved));
		assertTrue(userRetrieved == null);
	}
}
