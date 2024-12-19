package com.example.bloodbankjava;
import java.time.LocalDate;
public class BloodRequest {
    private static int requestId;
    private int hospitalId;
    private String bloodType;
    private int quantity;
    private String priority;
    private String status;
    private LocalDate requestDate;

    public BloodRequest(int requestId, int hospitalId, String bloodType, int quantity, String priority, String status, LocalDate requestDate) {
        this.requestId = requestId;
        this.hospitalId = hospitalId;
        this.bloodType = bloodType;
        this.quantity = quantity;
        this.priority = priority;
        this.status = status;
        this.requestDate = requestDate;
    }
    public static int getRequestId() {
        return requestId;
    }
    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }
    public int getHospitalId() {
        return hospitalId;
    }
    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
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
    public String getPriority() {
        return priority;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDate getRequestDate() {
        return requestDate;
    }
    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }
    @Override
    public String toString() {
        return "BloodRequest{" +
                "Request ID=" + requestId +
                ", Hospital ID=" + hospitalId +
                ", Blood Type='" + bloodType + '\'' +
                ", Quantity=" + quantity +
                ", Priority='" + priority + '\'' +
                ", Status='" + status + '\'' +
                ", Request Date=" + requestDate +
                '}';
    }


    public static int getRequestId(Request request) {
        return request.getRequestId();
    }
}
