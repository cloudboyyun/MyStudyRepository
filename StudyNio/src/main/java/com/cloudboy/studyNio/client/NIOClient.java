package com.cloudboy.studyNio.client;  

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.cloudboy.studyNio.NioUtils;

public class NIOClient implements Runnable {  

    //通道管理器  
    private Selector selector = null;
    private ConcurrentLinkedQueue<String> messages = null;
    
    /** 
     * 获得一个Socket通道，并对该通道做一些初始化的工作 
     * @param ip 连接的服务器的ip 
     * @param port  连接的服务器的端口号          
     * @throws IOException 
     */  
    public NIOClient(String ip,int port) throws IOException {  
        // 获得一个Socket通道  
    	SocketChannel channel = SocketChannel.open();  

        // 设置通道为非阻塞  
        channel.configureBlocking(false);  

        // 获得一个通道管理器  
        this.selector = Selector.open();  

        // 客户端连接服务器,其实方法执行并没有实现连接，需要在listen（）方法中调 用channel.finishConnect();才能完成连接  
        channel.connect(new InetSocketAddress(ip,port));  

        //将通道管理器和该通道绑定，并为该通道注册SelectionKey事件。  
        channel.register(selector, 
        		SelectionKey.OP_CONNECT
        		| SelectionKey.OP_READ 
        		| SelectionKey.OP_WRITE);
        
        messages = new ConcurrentLinkedQueue<String>();
    }
    
    @Override
	public void run() {
    	try {
			startListen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    /** 
     * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理 
     * @throws IOException 
     */  
    private void startListen() throws IOException {  
        // 轮询访问selector  
        while (true) {  
            selector.select();  

            // 获得selector中选中的项的迭代器  
            Iterator<SelectionKey> ite = this.selector.selectedKeys().iterator();  

            while (ite.hasNext()) {
                SelectionKey key = (SelectionKey) ite.next();

                // 删除已选的key,以防重复处理  
                ite.remove();
                
                if (key.isConnectable()) {
                	System.out.println("连接事件发生  ");
                    SocketChannel channel = (SocketChannel) key.channel();  
                    // 如果正在连接，则完成连接  
                    if(channel.isConnectionPending()){  
                        channel.finishConnect();
                        System.out.println("客户端完成连接。");
                    }  
                } else if (key.isReadable()) {  // 获得了可读事件
                	// 服务器可读取消息:得到事件发生的Socket通道
                	SocketChannel channel = (SocketChannel) key.channel();
               		String msg = NioUtils.read(channel);
               		System.out.println("服务端：" + msg);
                } else if(key.isWritable()) {
                	if(!messages.isEmpty()) {
                		SocketChannel channel = (SocketChannel) key.channel();
                		NioUtils.write(channel, messages.remove());
                	}
                }
            }  
        }  
    }
    
	public void sendToServer(String words) {
		messages.add(words);
	}
}  