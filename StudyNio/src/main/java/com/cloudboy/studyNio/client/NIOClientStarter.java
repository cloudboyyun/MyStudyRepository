package com.cloudboy.studyNio.client;  

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NIOClientStarter {  

	/**
	 * 读取键盘输入
	 * @return
	 * @throws IOException
	 */
	public static String readInput() throws IOException {
		System.out.println("Enter your words:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String result = br.readLine();
		return result;
	}

    /** 
     * 启动客户端测试 
     * @throws IOException  
     */  
    public static void main(String[] args) {  
		try {
			NIOClient client = new NIOClient("192.168.15.180", 9999);
			Thread thread = new Thread(client);
			thread.start();
			while (true) {
				String inputs = readInput();
				client.sendToServer(inputs);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }  
}  