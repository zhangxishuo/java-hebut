package service;

import config.CONFIG;
import entity.JsonAble;
import exception.CustomException;

/**
 * @author zhangxishuo on 2018/11/14
 * 消息服务
 */
public class MessageService {

    /**
     * 将可JSON化的对象转换为
     * @param jsonAble  可JSON化的对象
     * @return          客户端服务端交互的消息
     */
    public static String toMessage(JsonAble jsonAble) throws CustomException {
        // 对象转化为json字符串
        String json = jsonAble.toJsonString();
        // 根据json计算首部
        String header = getHeaderFromJson(json);
        // 根据json计算校验码
        String validate = getValidateFromJson(json);
        // 拼接消息
        return header + json + validate;
    }

    /**
     * 从消息中获取Json数据
     * @param message  消息
     * @return         Json数据
     * @throws CustomException  数据错误异常
     */
    public static String fromMessage(String message) throws CustomException {
        // 获取头部
        String header   = subHeader(message);
        // 获取json
        String json     = subJson(message, Integer.valueOf(header));
        // 获取校验码
        String validate = subValidate(message, json.length() + CONFIG.headerLength);
        // 如果数据错误，抛出异常
        if (!hash(json).toString().equals(validate)) {
            throw new CustomException(CONFIG.MESSAGE_IS_WRONG);
        }
        // 返回json
        return json;
    }

    /**
     * 从消息中截取校验码
     * @param message  消息
     * @param start    校验码起始位
     * @return   截取的校验码
     */
    private static String subValidate(String message, Integer start) {
        return message.substring(start);
    }

    /**
     * 从消息中截取Json
     * @param message  消息
     * @param length   数据长度
     * @return  截取的Json
     */
    private static String subJson(String message, Integer length) {
        return message.substring(CONFIG.headerLength, CONFIG.headerLength + length);
    }

    /**
     * 从消息字符串中截取首部
     * @param message  消息
     * @return         首部(即JSON长度)
     */
    private static String subHeader(String message) {
        // 截取前三位符号
        return message.substring(0, CONFIG.headerLength);
    }

    /**
     * 根据当前JSON字符串计算首部总长度字符串
     * @param json   JSON字符串
     * @return       首部总长度字符串
     * 首部长度为3
     */
    private static String getHeaderFromJson(String json) throws CustomException {
        // 获取总长度
        int length = json.length();
        // 如果长度大于等于1000，抛异常
        if (length >= 1000) {
            throw new CustomException(CONFIG.MESSAGE_TOO_LONG);
        }
        // 初始化首部
        String header = String.valueOf(length);
        // 补齐不够的位数
        StringBuilder prefix = new StringBuilder();
        for (int i = header.length(); i < CONFIG.headerLength; i ++) {
            prefix.append("0");
        }
        return prefix + header;
    }

    /**
     * 根据当前JSON字符串计算校验哈希码
     * @param json   JSON字符串
     * @return       校验哈希码
     */
    private static String getValidateFromJson(String json) {
        return hash(json).toString();
    }

    /**
     * 计算字符串哈希值
     * @param string 字符串
     * @return       哈希值
     */
    private static Integer hash(String string) {
        Integer hash = string.hashCode();
        return hash;
    }
}
