package org.feign.client;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Service
public class TcpSocketServer {


    // @Autowired
    // private StarterProper starterProper;

    // @Autowired
    // private TestRemote testRemote;


    // @PostConstruct
    // public void test1() {
    //     try {
    //         ServerSocket serverSocket = new ServerSocket(80);
    //         //2. 接受客户端连接：accept方法
    //         System.out.println("等待客户端的连接~~");
    //         Thread thread = new Thread() {
    //             @Override
    //             public void run() {
    //                 while (true) {
    //                     test(serverSocket);
    //                 }
    //             }
    //         };
    //         thread.start();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }
    //
    // private static void test(ServerSocket serverSocket) {
    //     try {
    //         //1. 创建服务端对象，绑定端口
    //         Socket socket = serverSocket.accept();
    //         System.out.println("客户端连接成功：" + socket);
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }
    //
    // @PreDestroy
    // public void destroy() {
    //     System.out.println("程序结束了");
    // }


    @PostConstruct
    public void notifyServer() {
        System.out.println("发送完成");
        HashMap<String, Integer> map = new HashMap<>();

        map.put("127.0.0.5", 5869);
        map.put("192.0.0.5", 5869);
        map.put("135.0.0.5", 5869);
        // testRemote.demo("qqqq", map);
        System.out.println("发送完成");
    }
}
