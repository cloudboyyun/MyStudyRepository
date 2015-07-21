package com.cloudboy.studyRMI.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.apache.log4j.Logger;

import com.cloudboy.studyRMI.server.Hello;

public class Client {
	private static Logger logger = Logger.getLogger(Client.class);
	
    public static void main(String[] args) {
        String host = "192.168.163.24";
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            Hello stub = (Hello) registry.lookup("Hello");
            String response = stub.sayHello();
            System.out.println("response: " + response);
        } catch (Exception e) {
        	logger.error("", e);
        }
    }
}

