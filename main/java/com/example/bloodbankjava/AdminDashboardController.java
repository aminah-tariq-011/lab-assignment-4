package com.example.bloodbankjava;
import javafx.scene.control.Alert;
import java.io.IOException;
import java.util.Map;

public class AdminDashboardController {

    private BloodInventoryService bloodInventoryService = new BloodInventoryService();

    public String viewBloodInventory() throws IOException {
        Map<String, Integer> stock = bloodInventoryService.getBloodStock();
        StringBuilder stockDetails = new StringBuilder();
        stock.forEach((bloodType, quantity) -> stockDetails.append("Blood Type: ")
                .append(bloodType)
                .append(", Quantity: ")
                .append(quantity)
                .append("\n"));
        return stockDetails.toString();
    }
    public void addStock(String bloodType, int quantity) throws IOException {
        try {
            bloodInventoryService.addStock(bloodType, quantity);
            showAlert("Success", "Stock updated successfully.", Alert.AlertType.INFORMATION);
        } catch (IllegalArgumentException ex) {
            showAlert("Error", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

