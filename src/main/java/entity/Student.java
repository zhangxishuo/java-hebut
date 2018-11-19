package entity;

import com.alibaba.fastjson.JSON;

/**
 * @author zhangxishuo on 2018/11/14
 * 学生实体
 */
public class Student implements JsonAble {

    private String name;        // 姓名

    private Integer age;        // 年龄

    private String clazz;       // 班级

    private String email;       // 邮箱

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

    public String toSQLString() {
        return "INSERT INTO student(name, age, clazz, email) VALUES('"
                + this.name + "', "
                + this.age + ", '"
                + this.clazz + "', '"
                + this.email + "');";
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
