package com.example.bloodbankjava;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DonationRecord {
    private final StringProperty date;
    private final StringProperty center;
    private final StringProperty bloodType;

    public DonationRecord(String date, String center, String bloodType) {
        this.date = new SimpleStringProperty(date);
        this.center = new SimpleStringProperty(center);
        this.bloodType = new SimpleStringProperty(bloodType);
    }
    public StringProperty dateProperty() {
        return date;
    }
    public StringProperty centerProperty() {
        return center;
    }
    public StringProperty bloodTypeProperty() {
        return bloodType;
    }
}
