package com.example.bloodbankjava;


public class Donation
{

    private String bloodType;
    private int quantity;
    private String donationDate;


    public Donation( String bloodType, int quantity, String donationDate) {

        this.bloodType = bloodType;
        this.quantity = quantity;
        this.donationDate = donationDate;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(String donationDate) {
        this.donationDate = donationDate;
    }

    @Override
    public String toString() {
        return


                ",quantity=" + quantity + ",bloodType=" + bloodType +
                ",donationDate=" + donationDate;
    }
}
