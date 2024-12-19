package com.example.bloodbankjava;
public class User {
    private int userId;
    private String name;
    private String role;
    private String email;
    private String password;
    private String bloodGroup;
    private String city;
    private String contact;
    private String gender;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
    public User(int userId, String name, String role, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.role = role;
        this.email = email;
        this.password = password;
    }
    public User(int newUserId, String name, String gender, String bloodGroup, String city, String contact, String role, String email, String password){
        this.userId = newUserId;
        this.name = name;
        this.role = role;
        this.email = email;
        this.password = password;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String toString() {
        return name + " (" + email + ")";
    }
    public String getBloodGroup() {
        return bloodGroup;
    }
    public void setBloodGroup( String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
    public String getCity() {
        return city;
    }
    public void setCity( String city) {
        this.city = city;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
}

