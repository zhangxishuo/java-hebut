package entity;

/**
 * @author zhangxishuo on 2018/11/9
 * 消息实体
 * 消息中封装协议
 */
public class Message {

    private String value;                 // 消息的值

    public Message() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 序列化
     * 当前消息对象根据协议序列化为字符串
     */
    public String serialize() {
        return "";
    }

    /**
     * 反序列化
     * 当前字符串根据协议反序列化为消息对象
     */
    public static Message deserialize(String input) {
        return new Message();
    }
}
