package com.cloudboy.studyIO.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BIOServer {
	
	private static Logger logger = LoggerFactory.getLogger(BIOServer.class);
	
	private static int PORT = 9999;
    
	private static ServerSocket server;
	
    public synchronized static void start(int port) throws IOException {
        if (server != null)
            return;
        try {
            // 通过构造函数创建ServerSocket
            // 如果端口合法且空闲，服务端就监听成功
            server = new ServerSocket(port);
            logger.info("BIO服务器已启动，端口号：" + port);
            // 通过无线循环监听客户端连接
            // 如果没有客户端接入，将阻塞在accept操作上。
            int count = 1;
            while (true) {
            	// 这个方法会阻塞，直到客户端有connection请求.
                Socket socket = server.accept();
                // 当有新的客户端接入时，会执行下面的代码, 然后创建一个新的线程处理这条Socket链路
                logger.info("One client connected." + socket.getRemoteSocketAddress());
                new Thread(new BIOServerHandler(socket, "" + count)).start();
                count = count + 1;
            }
        } finally {
            // 一些必要的清理工作
            if (server != null) {
                logger.info("服务器已关闭。");
                server.close();
                server = null;
            }
        }
    }
	
	public static void main(String[] args) {
		System.out.println("The BIOServer is starting.");
		try {
			BIOServer.start(PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("The BIOServer has exit.");
	}
}
