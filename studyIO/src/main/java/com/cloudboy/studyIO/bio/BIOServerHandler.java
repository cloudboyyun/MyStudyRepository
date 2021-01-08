package com.cloudboy.studyIO.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BIOServerHandler implements Runnable {
	private static Logger logger = LoggerFactory.getLogger(BIOServerHandler.class);
	private Socket socket;
	private String threadName;

	public BIOServerHandler(Socket socket, String threadName) {
		this.socket = socket;
		this.threadName = threadName;
	}

	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			String expression;
			String result;
			while (true) {
				// 通过BufferedReader读取一行
				// 如果已经读到输入流尾部，返回null,退出循环
				// 如果得到非空值，就尝试计算结果并返回
				if ((expression = in.readLine()) == null) {
					break;
				}
				logger.info("({})BIO服务器收到消息：{}", threadName, expression);
				result = "hello " + expression;
				out.println(result);
			}
			logger.info("(" + threadName + ") is quitting.");
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			// 一些必要的清理工作
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("", e);
				}
				in = null;
			}
			if (out != null) {
				out.close();
				out = null;
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					logger.error("", e);
				}
				socket = null;
			}
		}
	}
}
