package com.example.bloodbankjava;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DonationService {
    private static final String FILE_NAME = "C:\\Users\\amina\\Documents\\OOPS!!!\\PRACTICE\\bloodbankjava\\src\\main\\java\\com\\example\\bloodbankjava\\donations.txt";
public static List<Donation> getAllDonations() {
    List<Donation> donations = new ArrayList<>();
    File file = new File(FILE_NAME);
    if (!file.exists()) {
        return donations;
    }
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                String bloodType = parts[0].trim();
                int noOfBottles = Integer.parseInt(parts[1].trim());
                String dateOfDonation = parts[2].trim();
                donations.add(new Donation(bloodType, noOfBottles, dateOfDonation));
            } else {
                System.err.println("Invalid line format: " + line);
            }
        }
    } catch (IOException | NumberFormatException e) {
        e.printStackTrace();
    }
    return donations;
}
}
