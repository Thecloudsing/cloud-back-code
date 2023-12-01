package com.example.luoanforum.pojo;

/**
 * @author 落扶苏
 * @version 1.1
 */
public class PersonalInformation {
    private String id;
    private String username;
    private String gender;
    private Integer age;
    private String ip_address;
    private String email;
    private String birthday;

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public String getIp_address() {
        return ip_address;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return "PersonalInformation{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", ip_address='" + ip_address + '\'' +
                ", email='" + email + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
