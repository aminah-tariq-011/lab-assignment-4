package com.example.bloodbankjava;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DonorService {
    private static final String DONOR_FILE = "C:\\Users\\amina\\Documents\\OOPS!!!\\PRACTICE\\bloodbankjava\\src\\main\\java\\com\\example\\bloodbankjava\\donor.txt";
    private static final String DONATION_FILE = "C:\\Users\\amina\\Documents\\OOPS!!!\\PRACTICE\\bloodbankjava\\src\\main\\java\\com\\example\\bloodbankjava\\donations.txt";
    public static List<Donor> getAllDonors() {
        List<String> lines = FileReaderWriter.readFile(DONOR_FILE);
        List<Donor> donors = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(",");
            donors.add(new Donor(parts[0], Integer.parseInt(parts[1]), parts[2], parts[3], parts[4], parts[5]));
        }
        return donors;
    }
    public static void addDonor(Donor donor) {
        FileReaderWriter.appendToFile(DONOR_FILE, donor.toString());
    }
    public static void addDonation(int donorId, String bloodType) throws IOException {
        String donationRecord = donorId + "," + bloodType + "," + LocalDate.now() + "\n";
        Files.write(Paths.get(DONATION_FILE), donationRecord.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
    public static String getDonationHistory(int donorId) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(DONATION_FILE));
        StringBuilder history = new StringBuilder();
        for (String line : lines) {
            String[] parts = line.split(",");
            if (Integer.parseInt(parts[0]) == donorId) {
                history.append("Blood Type: ").append(parts[1])
                        .append(", Date: ").append(parts[2]).append("\n");
            }
        }
        return history.length() > 0 ? history.toString() : "No donation history found.";
    }
    public static boolean canDonate(String lastDonationDate) {
        return true;
    }
}

