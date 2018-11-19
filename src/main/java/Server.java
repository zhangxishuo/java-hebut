import config.CONFIG;
import entity.Student;
import exception.CustomException;
import service.MessageService;
import service.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zhangxishuo on 2018/11/14
 * 服务端程序
 */
public class Server {

    /**
     * 服务端主方法
     */
    public static void main(String[] args) throws IOException {
        // 创建服务端套接字
        ServerSocket serverSocket = new ServerSocket(CONFIG.PORT);
        System.out.println("端口: " + CONFIG.PORT + ", 服务正在运行 ...");

        while (true) {

            // 接受连接
            Socket socket = serverSocket.accept();

            // 初始化读取与写入
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            final PrintWriter printWriter = new PrintWriter(socket.getOutputStream());

            // 创建线程，执行服务端业务逻辑
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    service(bufferedReader, printWriter);
                }
            });

            // 启动线程
            thread.start();
        }
    }

    /**
     * 服务端业务逻辑
     */
    private static void service(BufferedReader bufferedReader, PrintWriter printWriter) {
        // 获取实体数量
        String size = Utils.getMessage(bufferedReader);
        // 发送相应消息
        Utils.sendMessage(printWriter, CONFIG.SUCCESS_RESPONSE);
        // 循环接收数据
        for (int i = 0; i < Integer.valueOf(size); i ++) {
            try {
                // 读取数据
                String message = Utils.getMessage(bufferedReader);
                // 截取JSON
                String json = MessageService.fromMessage(message);
                // 从JSON反序列化为对象
                Student student = Student.fromJsonString(json);
                System.out.println(student.getName());
                System.out.println(student.getAge());
                System.out.println(student.getClazz());
                System.out.println(student.getEmail());
                // 发送相应数据
                Utils.sendMessage(printWriter, CONFIG.SUCCESS_RESPONSE);
            } catch (CustomException e) {
                System.out.println("数据异常: " + e.getMessage());
            }
        }
        System.out.println("服务端线程结束");
    }
}
