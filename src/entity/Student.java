package entity;

/**
 * @author zhangxishuo on 2018/11/9
 * 学生实体
 */
public class Student {

    private Long number;

    private String name;

    private Integer age;

    public Student() {
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
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

    /**
     * 转换为Json字符串
     */
    public String toJsonString() {
        return "{number: " + number + ", " +
                "name: " + name + ", " +
                "age: " + age + "}";
    }
}
