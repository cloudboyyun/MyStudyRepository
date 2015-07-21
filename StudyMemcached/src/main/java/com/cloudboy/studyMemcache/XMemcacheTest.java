package com.cloudboy.studyMemcache;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.CASOperation;
import net.rubyeye.xmemcached.Counter;
import net.rubyeye.xmemcached.GetsResponse;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class XMemcacheTest {
	private static String key = "CL_OBJECT_KEY";
	private MyModel originalData;
	private String SERVERS = "192.168.47.6:12000 192.168.47.6:12001";
	
	@Before
	public void setUp() {
		originalData = new MyModel();
		originalData.setName("cloudboy");
		originalData.setAge(3);
	}

	@Test
	public void basicTest() {
		String key = "MyKey";
		try {
			MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(SERVERS));
			MemcachedClient client = builder.build();
			
			// Make All connected memcached's data item invalid.
//			client.flushAll();
			
			if (!client.set(key, 0, "world")) {
				System.err.println("set error");
			}

			if (client.add(key, 0, "dennis")) {
				System.err.println("Add error,key is existed");
			}

			if (!client.replace(key, 0, "cloudboy")) {
				System.err.println("replace error");
			}
			client.append(key, " good");
			client.prepend(key, "hello ");
			String name = client.get(key);
			System.out.println(name);
			client.deleteWithNoReply("hello");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void casTest() {
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(SERVERS));
		MemcachedClient client;
		try {
			client = builder.build();

			// store a value for one hour(synchronously).
			client.set(key, 3600, originalData);

			// Retrieve a value.(synchronously),operation timeout two seconds.
			GetsResponse<MyModel> response = client.gets(key, 2000);
			long cas = response.getCas();
			System.out.println("cas:" + cas);
			MyModel data1 = response.getValue();
			System.out.println("value:" + data1.getName());
			Assert.assertTrue(data1 != null);
			
			client.cas(key, 0, new CASOperation<MyModel>() {
				public int getMaxTries() {
					return 1;
				}

				public MyModel getNewValue(long currentCAS, MyModel currentValue) {
					MyModel newData = new MyModel();
					newData.setName("Cloudboy's brother");
					newData.setAge(4);
					return newData;
				}
			});
			
			MyModel data2 = client.get(key, 2000);
			System.out.println("data2:" + data2.getName());
			Assert.assertTrue("Cloudboy's brother".equals(data2.getName()));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void counterTest() {
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(SERVERS));
		MemcachedClient client;
		try {
			client = builder.build();
			// create a counter, if it does not exist, create it and initial its value to 50.
			Counter counter = client.getCounter("counter",50);
			System.out.println("counter:" + counter.get());
			long newIndex = counter.incrementAndGet();
			System.out.println("newIndex:" + newIndex);
//			counter.decrementAndGet(); 
//			counter.addAndGet(-10);
			Counter counter2 = client.getCounter("counter",50);
			System.out.println("counter2: " + counter2.get());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void statTest() {
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(SERVERS));
		MemcachedClient client;
		try {
			client = builder.build();
			Map<InetSocketAddress,Map<String,String>> result = client.getStats();
			for(InetSocketAddress address : result.keySet()) {
				System.out.println("----------------------------------------");
				System.out.println(address.getHostName() + ":" + address.getPort());
				Map<String, String> keyValues = result.get(address); 
				for(String key : keyValues.keySet()) {
					System.out.println(key + " : " + keyValues.get(key));
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
	}
}
