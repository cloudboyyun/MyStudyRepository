package com.cloudboy.studyJMX.server;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.rmi.registry.Registry;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

import org.apache.log4j.Logger;

import com.cloudboy.util.json.JsonUtil;
import com.cloudboy.util.rmi.RMIUtil;

public class MyBeanManagement {

	private static Logger logger = Logger.getLogger(MyBeanManagement.class);

	private static final String DEFAULT_NAME = "default";
	private static final int DEFAULT_AGE = 10;
	public final static int PORT = 6666;
	public final String RMI_NAME = "jmxrmi";
	
	private JMXConnectorServer connectorServer;
	private Thread shutdownHookThread;

	public void startUp() throws Exception {
		// register the MBean
		MyBean myBean = new MyBean(DEFAULT_NAME, DEFAULT_AGE);
		registerBean(myBean);

		do {
			Thread.sleep(3000);
			logger.info("Name=" + myBean.getName() + ":::Age=" + myBean.getAge());
		} while (myBean.getAge() != 0);
	}
	
	private void registerBean(MyBean myBean) throws Exception {
		// Get the MBean server
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		ObjectName name = new ObjectName("CloudBoy:type=MyBean");
		mbs.registerMBean(myBean, name);
		
		String hostName = null;
		try {
			InetAddress addr = InetAddress.getLocalHost();
			hostName = addr.getHostName();
		} catch (IOException e) {
			logger.info("", e);
			hostName = "localhost";
		}

		Registry registry = RMIUtil.startLocalRMIRegistry(PORT);
		String[] names1 = registry.list();
		logger.info("names1:" + JsonUtil.convert2String(names1));

		String serverURL = "service:jmx:rmi:///jndi/rmi://" + hostName + ":"
				+ PORT + "/" + RMI_NAME;
		JMXServiceURL url = new JMXServiceURL(serverURL);
		connectorServer = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbs);
		connectorServer.start();
		
		String[] names2 = registry.list();
		logger.info("names2:" + JsonUtil.convert2String(names2));
		
		shutdownHookThread = new Thread() {
			@Override
			public void run() {
				try {
					logger.info("ShutdownHookThread is invoked.");
					if (connectorServer.isActive()) {
						connectorServer.stop();
						logger.warn("JMXConnector stop");
					}
				} catch (IOException e) {
					logger.error("Shutdown MBean server error", e);
				}
			}
		};
		
		Runtime.getRuntime().addShutdownHook(shutdownHookThread);
		logger.warn("jmx url: " + serverURL);
	}

	public static void main(String[] args) throws Exception {
		MyBeanManagement management = new MyBeanManagement();
		management.startUp();
	}
}