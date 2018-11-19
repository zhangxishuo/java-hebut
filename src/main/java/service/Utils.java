package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 工具
 */
public class Utils {

    /**
     * 获取消息
     */
    public static String getMessage(BufferedReader bufferedReader) {
        String message = null;
        try {
            message = bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println("发生异常, 读取消息失败");
        }
        return message;
    }

    /**
     * 发送消息
     */
    public static void sendMessage(PrintWriter printWriter, String message) {
        printWriter.println(message);
        printWriter.flush();
    }
}
