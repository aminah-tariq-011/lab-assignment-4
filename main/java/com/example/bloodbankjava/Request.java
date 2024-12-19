package com.example.bloodbankjava;

import java.time.LocalDate;

public class Request {
    private int requestId;
    private String hospitalName;
    private String bloodType;
    private int quantity;
    private String urgency;
    private String status;
    private LocalDate requestDate;

    public Request(int requestId, String hospitalName, String bloodType, int quantity, String urgency, String status, LocalDate requestDate) {
        this.requestId = requestId;
        this.hospitalName = hospitalName;
        this.bloodType = bloodType;
        this.quantity = quantity;
        this.urgency = urgency;
        this.status = status;
        this.requestDate = requestDate;
    }
    public int getRequestId() { return requestId; }
    public void setRequestId(int requestId) { this.requestId = requestId; }
    public String getHospitalName() { return hospitalName; }
    public void setHospitalName(String hospitalName) { this.hospitalName = hospitalName; }
    public String getBloodType() { return bloodType; }
    public void setBloodType(String bloodType) { this.bloodType = bloodType; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getUrgency() { return urgency; }
    public void setUrgency(String urgency) { this.urgency = urgency; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDate getRequestDate() { return requestDate; }
    public void setRequestDate(LocalDate requestDate) { this.requestDate = requestDate; }

    public String getPriority() {
        return this.urgency;
    }


}
