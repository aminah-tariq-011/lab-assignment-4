package com.example.bloodbankjava;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class BloodInventory {
    private static final String INVENTORY_FILE ="C:\\Users\\amina\\Documents\\OOPS!!!\\PRACTICE\\bloodbankjava\\src\\main\\java\\com\\example\\bloodbankjava\\inventory.txt";

    public Map<String, Integer> getBloodStock() throws IOException {
        Map<String, Integer> inventory = new HashMap<>();
        List<String> lines = Files.readAllLines(Paths.get(INVENTORY_FILE));
        for (String line : lines) {
            String[] parts = line.split(",");
            inventory.put(parts[0], Integer.parseInt(parts[1]));
        }
        return inventory;
    }
    public void updateStock(String bloodType, int quantity) throws IOException {
        Map<String, Integer> inventory = getBloodStock();
        inventory.put(bloodType, inventory.getOrDefault(bloodType, 0) + quantity);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(INVENTORY_FILE))) {
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        }
    }
    public void reduceStock(String bloodType, int quantity) throws IOException {
        Map<String, Integer> inventory = getBloodStock();
        if (inventory.containsKey(bloodType)) {
            int newQuantity = inventory.get(bloodType) - quantity;
            if (newQuantity < 0) throw new IllegalArgumentException("Insufficient stock.");
            inventory.put(bloodType, newQuantity);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(INVENTORY_FILE))) {
                for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                    writer.write(entry.getKey() + "," + entry.getValue());
                    writer.newLine();
                }
            }
        } else {
            throw new IllegalArgumentException("Blood type not found.");
        }
    }
}
