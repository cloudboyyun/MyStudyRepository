package com.cloudboy.studyIO.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TestClient {
    // 默认的端口号
    private static String DEFAULT_SERVER_IP = "127.0.0.1";
    private static int PORT = 9999;
    private static String readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        System.out.println("Enter your value:");
        str = br.readLine();
        return str;
    }
    
    public static void send(int port) {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket(DEFAULT_SERVER_IP, port);
            while (true) {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                String words = readInput();
                if (words.equalsIgnoreCase("exit")) {
                    break;
                }
                out.println(words);
                System.out.println("___结果为：" + in.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 一下必要的清理工作
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
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
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }
    public static void main(String[] args) {
        send(PORT);
    }
}