package entity;

import com.alibaba.fastjson.JSON;

/**
 * @author zhangxishuo on 2018/11/14
 * 学生实体
 */
public class Student implements JsonAble {

    private String name;

    private Integer age;

    private String clazz;

    private String email;

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toJsonString() {
        return JSON.toJSONString(this);
    }

    public static Student fromJsonString(String json) {
        return JSON.parseObject(json).toJavaObject(Student.class);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", clazz='" + clazz + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
