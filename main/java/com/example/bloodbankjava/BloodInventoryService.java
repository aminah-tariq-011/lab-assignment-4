package com.example.bloodbankjava;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.Map;

import java.io.IOException;
import java.util.Map;

public class BloodInventoryService {

    private BloodInventory bloodInventory = new BloodInventory();

    public Map<String, Integer> getBloodStock() throws IOException {
        return bloodInventory.getBloodStock();
    }

    public void addStock(String bloodType, int quantity) throws IOException {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0.");
        }
        bloodInventory.updateStock(bloodType, quantity);
    }

    public void reduceStock(String bloodType, int quantity) throws IOException {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0.");
        }
        bloodInventory.reduceStock(bloodType, quantity);
    }

    public String generateBloodInventoryReport() throws IOException {
        Map<String, Integer> bloodStock = getBloodStock();
        StringBuilder report = new StringBuilder();
        report.append("Blood Inventory Report\n");
        report.append("=====================\n");

        if (bloodStock.isEmpty()) {
            report.append("No blood available in the inventory.");
        } else {
            for (Map.Entry<String, Integer> entry : bloodStock.entrySet()) {
                report.append("Blood Type: ")
                        .append(entry.getKey())
                        .append(", Quantity: ")
                        .append(entry.getValue())
                        .append("\n");
            }
        }
        return report.toString();
    }
}
