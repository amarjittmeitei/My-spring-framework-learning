package com.yawolart.entity;

//POJO
public class Students {
    private long id;
    private String name;
    private String email;
    private int age;

    public Students(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public Students() {
    }


    public long getId() {
        return id;
    }

    public Students setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Students setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Students setEmail(String email) {
        this.email = email;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Students setAge(int age) {
        this.age = age;
        return this;
    }

    @Override
    public String toString() {
        return "Students{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
