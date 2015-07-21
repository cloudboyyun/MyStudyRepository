package com.cloudboy.studyNio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import com.cloudboy.studyNio.NioUtils;

public class MyNonBlockServer {
	
	// 通道管理器
	private Selector selector;
	
	public MyNonBlockServer(int port) throws IOException {
		// 获得一个ServerSocket通道
		ServerSocketChannel serverChannel = ServerSocketChannel.open();

		// 设置通道为非阻塞
		serverChannel.configureBlocking(false);

		// 将该通道对应的ServerSocket绑定到port端口
		serverChannel.socket().bind(new InetSocketAddress(port));

		// 获得一个通道管理器
		this.selector = Selector.open();

		// 将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件,注册该事件后，
		// 当该事件到达时，selector.select()会返回，如果该事件没到达selector.select()会一直阻塞。
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
	}
	
	/**
	 * 开始监听端口
	 */
	public void startListen() throws IOException {
		System.out.println("服务端启动成功");
		// 轮询访问selector
		while (true) {
			// 当注册的事件到达时，方法返回；否则,该方法会一直阻塞
			selector.select();

			// 获得selector中选中的项的迭代器，选中的项为注册的事件
			Iterator<SelectionKey> ite = this.selector.selectedKeys().iterator();

			while (ite.hasNext()) {
				SelectionKey key = (SelectionKey) ite.next();

				// 删除已选的key,以防重复处理
				ite.remove();

				if (key.isAcceptable()) {
					System.out.println("获得了客户端请求连接事件");
					ServerSocketChannel server = (ServerSocketChannel) key.channel();

					// 获得和客户端连接的通道
					SocketChannel channel = server.accept();

					// 设置成非阻塞
					channel.configureBlocking(false);

					// 在这里可以给客户端发送信息哦
					channel.write(ByteBuffer.wrap(new String("我是服务端，已接受你的连接").getBytes()));

					// 在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读的权限。
					channel.register(this.selector, SelectionKey.OP_READ);
				} else if (key.isReadable()) { // 获得了可读的事件
					SocketChannel channel = (SocketChannel) key.channel();
					try {
						String msg = NioUtils.read(channel);
						System.out.println("客户端：" + msg);
						String responseMsg = "我是服务端，你的消息已收到(" + msg + ")";
						responseMsg.replace('\n', ' ');
						ByteBuffer outBuffer = ByteBuffer.wrap(responseMsg.getBytes());
						channel.write(outBuffer);// 将消息回送给客户端
					} catch(IOException e) {
						channel.close();
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		int PORT = 9999;
		try {
			MyNonBlockServer server = new MyNonBlockServer(PORT);
			server.startListen();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}