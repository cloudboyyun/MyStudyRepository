package com.cloudboy.studyNio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioUtils {
	
	public static String read(SocketChannel socketChannel) throws IOException {
		ByteBuffer buf = ByteBuffer.allocate(1024);
		int bytesRead = socketChannel.read(buf);
		if(bytesRead == -1) {
			return null;
		} else {
			String result = new String(buf.array());
			return result;
		}
	}
	
	public static void write(SocketChannel channel, String words) throws IOException {
		byte[] bytes = words.getBytes();
		ByteBuffer buf = ByteBuffer.allocate(bytes.length);
		buf.put(words.getBytes());
		buf.flip();
		while(buf.hasRemaining()) {
			channel.write(buf);
		}
	}
}
