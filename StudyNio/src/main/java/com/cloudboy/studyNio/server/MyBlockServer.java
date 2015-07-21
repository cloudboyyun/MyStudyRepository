package com.cloudboy.studyNio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import com.cloudboy.studyNio.NioUtils;

public class MyBlockServer {
	
	private ServerSocketChannel serverSocketChannel = null;
	
	/**
	 * 初始化Server
	 * @throws IOException 
	 */
	public MyBlockServer(int port) throws IOException {
		serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.socket().bind(new InetSocketAddress(port));
		// 阻塞模式
		serverSocketChannel.configureBlocking(true);
	}
	
	/**
	 * 开始监听端口
	 */
	public void listen() {
		SocketChannel socketChannel = null;
		try {
			while (true) {
				// 在阻塞方式下，accept()方法会一直阻塞到有新连接到达
				socketChannel = serverSocketChannel.accept();
				if (socketChannel != null) {
					String input = NioUtils.read(socketChannel);
					System.out.println(input);
					NioUtils.write(socketChannel, "收到消息：" + input);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (socketChannel != null) {
				try {
					socketChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println("The MyBlockServer is starting.");
		MyBlockServer server;
		try {
			server = new MyBlockServer(9999);
			server.listen();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("The MyBlockServer has exit.");
	}
}
