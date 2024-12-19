package com.example.bloodbankjava;


import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class RequestService {
    private static final String REQUEST_FILE = "C:\\Users\\amina\\Documents\\OOPS!!!\\PRACTICE\\bloodbankjava\\src\\main\\java\\com\\example\\bloodbankjava\\Request.txt";


    public static List<Request> getAllRequests() {
        List<Request> requests = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(REQUEST_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 7) {
                    int requestId = Integer.parseInt(data[0]);
                    String hospitalName = data[1];
                    String bloodType = data[2];
                    int quantity = Integer.parseInt(data[3]);
                    String urgency = data[4];
                    String status = data[5];
                    LocalDate requestDate = LocalDate.parse(data[6]);
                    requests.add(new Request(requestId, hospitalName, bloodType, quantity, urgency, status, requestDate));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return requests;
    }


    public static void updateRequestStatus(Request request) {
        List<Request> requests = getAllRequests();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REQUEST_FILE))) {
            for (Request req : requests) {
                if (req.getRequestId() == request.getRequestId()) {
                    writer.write(req.getRequestId() + "," + req.getHospitalName() + "," + req.getBloodType() + ","
                            + req.getQuantity() + "," + req.getUrgency() + "," + request.getStatus() + "," + req.getRequestDate());
                } else {
                    writer.write(req.getRequestId() + "," + req.getHospitalName() + "," + req.getBloodType() + ","
                            + req.getQuantity() + "," + req.getUrgency() + "," + req.getStatus() + "," + req.getRequestDate());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final String FILE_PATH ="C:\\Users\\amina\\Documents\\OOPS!!!\\PRACTICE\\bloodbankjava\\src\\main\\java\\com\\example\\bloodbankjava\\Request.txt";



    public String getAllRequestsFormatted() throws IOException {
        StringBuilder report = new StringBuilder();
        for (Request request : getAllRequests()) {
            report.append("Request ID: ").append(request.getRequestId())
                    .append(", Blood Type: ").append(request.getBloodType())
                    .append(", Quantity: ").append(request.getQuantity())
                    .append(", Priority: ").append(request.getPriority())
                    .append(", Status: ").append(request.getStatus())
                    .append("\n");
        }
        return report.toString();


    }



    public int getNextRequestId() throws IOException {
        return getAllRequests().stream()
                .mapToInt(BloodRequest::getRequestId)
                .max()
                .orElse(0) + 1;
    }

    public void addRequest(BloodRequest request) throws IOException {
        List<String> lines = FileReaderWriter.readFile(FILE_PATH);
        String newRequest = request.getRequestId() + "," +
                request.getHospitalId() + "," +
                request.getBloodType() + "," +
                request.getQuantity() + "," +
                request.getPriority() + "," +
                request.getStatus() + "," +
                request.getRequestDate();
        lines.add(newRequest);
        FileReaderWriter.writeFile(FILE_PATH, lines);
    }

    public String getRequestStatusForHospital(int hospitalId) throws IOException {
        StringBuilder statusReport = new StringBuilder();
        for (Request request : getAllRequests()) {
            if (request.getRequestId() == hospitalId) {
                statusReport.append("Request ID: ").append(request.getRequestId())
                        .append(", Blood Type: ").append(request.getBloodType())
                        .append(", Quantity: ").append(request.getQuantity())
                        .append(", Status: ").append(request.getStatus())
                        .append(", Priority: ").append(request.getPriority())
                        .append("\n");
            }
        }
        return statusReport.length() > 0 ? statusReport.toString() : "No requests found for this hospital.";
    }


}
