package com.cloudboy.studyJMX.client;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import com.cloudboy.studyJMX.server.MyBeanMBean;

public class Client {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
         JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://127.0.0.1:6666/jmxrmi");
         JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
        
         MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

         ObjectName mbeanName = new ObjectName("CloudBoy:type=MyBean");

         MyBeanMBean mbeanProxy = JMX.newMBeanProxy(mbsc, mbeanName, MyBeanMBean.class, true);
         
         int age = mbeanProxy.getAge();
         System.out.println("Current age: " + age);

         mbeanProxy.setAge(100);
         age = mbeanProxy.getAge();
         System.out.println("Changed ThreadCount: " + age);

         Thread.sleep(3);
         jmxc.close();
    }
}
