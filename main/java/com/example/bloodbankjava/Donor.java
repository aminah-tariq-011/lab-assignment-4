package com.example.bloodbankjava;

public class Donor extends User {
    private int age;
    private String gender;
    private String bloodType;
    private String contact;
    private String lastDonationDate;

    public Donor(int id, String name, String role, String email, String password, int age, String gender, String bloodType, String contact, String lastDonationDate) {
        super(id, name, role, email, password);
        this.age = age;
        this.gender = gender;
        this.bloodType = bloodType;
        this.contact = contact;
        this.lastDonationDate = lastDonationDate;
    }
    public Donor(String name, int id, String role, String email, String password, String lastDonationDate) {
        super(id, name, role, email, password);
        this.lastDonationDate= lastDonationDate;
    }

    public int getAge() {
        return age;
    }
    public String getGender() {
        return gender;
    }
    public String getBloodType() {
        return bloodType;
    }
    public String getContact() {
        return contact;
    }
    public String getLastDonationDate() {
        return lastDonationDate;
    }
    public void setLastDonationDate(String lastDonationDate) {
        this.lastDonationDate = lastDonationDate;
    }
    public void donateBlood(String donationDate) {
        setLastDonationDate(donationDate);
        System.out.println("Blood donation successful! Last donation date updated.");
    }
    @Override
    public String toString() {
        return super.toString() + "," + age + "," + gender + "," + bloodType + "," + contact + "," + lastDonationDate;
    }
}
