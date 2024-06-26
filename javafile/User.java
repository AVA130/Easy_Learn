package com.example.easylearn;

public class User {
    public String userName;
    public String fullName;
    public String email;
    public String phone;

    public User() {
    }

    public User(String userName, String fullName, String email, String phone) {
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
