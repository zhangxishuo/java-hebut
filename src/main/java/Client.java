import config.CONFIG;
import entity.Student;
import exception.CustomException;
import service.MessageService;
import service.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangxishuo on 2018/11/14
 * 客户端程序
 */
public class Client {

    private static List<Student> students = new ArrayList<Student>();

    /**
     * 客户端主方法
     */
    public static void main(String[] args) throws IOException {
        // 创建客户端套接字
        Socket socket = new Socket(CONFIG.HOSTS, CONFIG.PORT);

        // 客户端初始化
        init();

        // 初始化读取与写入
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        final PrintWriter printWriter = new PrintWriter(socket.getOutputStream());

        // 执行客户端业务逻辑
        service(bufferedReader, printWriter);
    }

    /**
     * 初始化
     */
    private static void init() {
        Student xiaoshuo = new Student();
        xiaoshuo.setName("小硕");
        xiaoshuo.setAge(20);
        xiaoshuo.setClazz("软件162班");
        xiaoshuo.setEmail("xiaoming@gmail.com");
        students.add(xiaoshuo);

        Student xiaomei = new Student();
        xiaomei.setName("小梅");
        xiaomei.setAge(19);
        xiaomei.setClazz("软件163班");
        xiaomei.setEmail("xiaomei@gmai.com");
        students.add(xiaomei);

        Student xiaochao = new Student();
        xiaochao.setName("小超");
        xiaochao.setAge(20);
        xiaochao.setClazz("软件161班");
        xiaochao.setEmail("xiaochao@gmail.com");
        students.add(xiaochao);
    }

    /**
     * 客户端业务逻辑
     */
    private static void service(BufferedReader bufferedReader, PrintWriter printWriter) {
        // 发送实体数量
        Utils.sendMessage(printWriter, String.valueOf(students.size()));
        // 获取相应
        String response = Utils.getMessage(bufferedReader);
        // 响应错误，终止连接
        if (!response.equals(CONFIG.SUCCESS_RESPONSE)) {
            System.out.println("连接服务器失败");
            return;
        }
        // 循环发送学生实体数据
        for (Student student : students) {
            try {
                // 对学生实体进行封装
                String message = MessageService.toMessage(student);
                // 发送数据
                Utils.sendMessage(printWriter, message);
                System.out.println("发送数据实体");
                // 获取相应
                response = Utils.getMessage(bufferedReader);
                // 获取成功
                if (response.equals(CONFIG.SUCCESS_RESPONSE)) {
                    System.out.println("服务端接收成功");
                }
                // 线程休眠
                Thread.sleep(3000);
            } catch (CustomException e) {
                System.out.println("ERROR: " + e.getMessage());
            } catch (InterruptedException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
        // 客户端结束
        System.out.println("客户端结束");
    }
}
