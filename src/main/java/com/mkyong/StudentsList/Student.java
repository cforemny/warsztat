package com.mkyong.StudentsList;

/**
 * Created by Cyprian on 2017-06-04.
 */
public class Student {

    private String name;
    private String lastName;
    private String age;
    private String email;
    private String parentName;
    private String telephone;
    private String id;

    public Student(String name, String lastName, String age, String email, String parentName, String telephone, String id) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.parentName = parentName;
        this.telephone = telephone;
        this.id = id;
    }

    public Student() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
