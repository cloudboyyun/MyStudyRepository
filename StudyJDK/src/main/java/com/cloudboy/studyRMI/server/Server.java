package com.cloudboy.studyRMI.server;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import com.cloudboy.util.rmi.RMIUtil;
        
public class Server implements Hello {

    public String sayHello() {
        return "Hello, world!";
    }
    
    public static void startUp() {
        try {
            Server server = new Server();
            
            // 参数"0"是端口号，表示随便找个没有使用的端口。下面的代码，使远程对象监听该端口，然后生成一个存根(stub)。
            // 客户端将获取到这个stub，通过stub和访问服务。
            Hello stub = (Hello) UnicastRemoteObject.exportObject(server, 0);

            // RMI Registry是一个名称服务(Name Service), 它会监听一个端口，根据客户端传送来的name, 把对应的object(这里是stub)发送给客户端。
            Registry registry = RMIUtil.startLocalRMIRegistry();
            registry.bind("Hello", stub);
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
        
    public static void main(String args[]) {
        startUp();
    }
}