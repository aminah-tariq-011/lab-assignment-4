package com.example.bloodbankjava;
import javafx.scene.control.Alert;

public class DonorDashboardController {
    private DonorService donorService = new DonorService();

    public String getDonationHistory(int donorId) {
        try {
            return donorService.getDonationHistory(donorId);
        } catch (Exception ex) {
            ex.printStackTrace();
            showAlert("Error", "Could not load donation history.", Alert.AlertType.ERROR);
            return null;
        }
    }
    public void addDonation(int donorId, String bloodType) {
        try {
            donorService.addDonation(donorId, bloodType);
            showAlert("Success", "Donation added successfully.", Alert.AlertType.INFORMATION);
        } catch (Exception ex) {
            ex.printStackTrace();
            showAlert("Error", "Could not add donation.", Alert.AlertType.ERROR);
        }
    }
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
