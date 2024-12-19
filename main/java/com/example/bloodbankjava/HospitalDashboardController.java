package com.example.bloodbankjava;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class HospitalDashboard extends Application {
    private User hospital;

    public HospitalDashboard(User hospital) {
        this.hospital = hospital;
    }
    @Override
    public void start(Stage primaryStage) {
        BorderPane mainLayout = new BorderPane();
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #610b0b;");
        Label menuTitle = new Label("Menu");
        menuTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        menuTitle.setTextFill(Color.WHITE);
        Button pendingRequestsButton = new Button("Pending Requests");
        Button completedRequestsButton = new Button("Completed Requests");
        Button statisticsButton = new Button("Statistics");
        Button contactInfoButton = new Button("Contact Info");
        Button searchRequestsButton = new Button("Search Requests");
        Button logoutButton = new Button("Logout");

        String sidebarButtonStyle = "-fx-background-color: #610b0b; -fx-text-fill: white; -fx-font-size: 14; -fx-padding: 10;";
        pendingRequestsButton.setStyle(sidebarButtonStyle);
        completedRequestsButton.setStyle(sidebarButtonStyle);
        statisticsButton.setStyle(sidebarButtonStyle);
        contactInfoButton.setStyle(sidebarButtonStyle);
        contactInfoButton.setOnAction(e->{
        });
        searchRequestsButton.setStyle(sidebarButtonStyle);
        logoutButton.setStyle(sidebarButtonStyle);

        pendingRequestsButton.setMaxWidth(Double.MAX_VALUE);
        completedRequestsButton.setMaxWidth(Double.MAX_VALUE);
        statisticsButton.setMaxWidth(Double.MAX_VALUE);
        contactInfoButton.setMaxWidth(Double.MAX_VALUE);
        searchRequestsButton.setMaxWidth(Double.MAX_VALUE);
        logoutButton.setMaxWidth(Double.MAX_VALUE);
        sidebar.getChildren().addAll(menuTitle, pendingRequestsButton, completedRequestsButton, statisticsButton, contactInfoButton, searchRequestsButton, logoutButton);

        HBox header = new HBox();
        header.setPadding(new Insets(10));
        header.setAlignment(Pos.CENTER);
        header.setStyle("-fx-background-color: #610b0b;");
        Label titleLabel = new Label("Hospital Dashboard - " + hospital.getName());
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        titleLabel.setTextFill(Color.WHITE);
        header.getChildren().add(titleLabel);
        VBox contentArea = new VBox(20);
        contentArea.setPadding(new Insets(20));
        contentArea.setAlignment(Pos.CENTER);
        Label welcomeLabel = new Label("Welcome to the Hospital Dashboard");
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        welcomeLabel.setStyle("-fx-text-fill: #610b0b;");
        Image hospitalImage = new Image("C:\\Users\\amina\\Documents\\OOPS!!!\\PRACTICE\\bloodbankjava\\src\\main\\java\\com\\example\\bloodbankjava\\hospitalicon.png");
        ImageView imageView = new ImageView(hospitalImage);
        imageView.setFitWidth(300);
        imageView.setFitHeight(300);
        imageView.setPreserveRatio(true);

        contentArea.getChildren().addAll(welcomeLabel, imageView);
        pendingRequestsButton.setOnAction(e -> showPendingRequests(contentArea));
        completedRequestsButton.setOnAction(e -> showCompletedRequests(contentArea));
        contactInfoButton.setOnAction(e -> showContactInfo(contentArea));
        statisticsButton.setOnAction(e -> {
            StatisticsScreen statisticsScreen = new StatisticsScreen();
            statisticsScreen.start(new Stage());
        });
        searchRequestsButton.setOnAction(e -> showSearchRequests(contentArea));
        logoutButton.setOnAction(e -> {
            primaryStage.close();
            new Main().start(new Stage());
        });
        mainLayout.setLeft(sidebar);
        mainLayout.setTop(header);
        mainLayout.setCenter(contentArea);
        Scene scene = new Scene(mainLayout, 1000, 600);
        primaryStage.setTitle("Hospital Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void showPendingRequests(VBox contentArea) {
        contentArea.getChildren().clear();
        Label label = new Label("Pending Requests");
        label.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        contentArea.getChildren().add(label);
        List<String> requests = filterRequestsByStatus("Pending");
        for (String request : requests) {
            Label requestLabel = new Label(request);
            requestLabel.setFont(Font.font("Arial", 14));
            contentArea.getChildren().add(requestLabel);
        }
    }
    private void showCompletedRequests(VBox contentArea) {
        contentArea.getChildren().clear();
        Label label = new Label("Completed Requests");
        label.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        contentArea.getChildren().add(label);
        List<String> requests = filterRequestsByStatus("Completed");
        for (String request : requests) {
            Label requestLabel = new Label(request);
            requestLabel.setFont(Font.font("Arial", 14));
            contentArea.getChildren().add(requestLabel);
        }
    }

    private void showContactInfo(VBox contentArea) {
        contentArea.getChildren().clear();
        Label contactTitle = new Label("Hospital Contact Information");
        contactTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        contentArea.getChildren().add(contactTitle);
        Label hospitalAddress = new Label("Address: 123 Health St, City, Country");
        Label hospitalPhone = new Label("Phone: +1234567890");
        Label hospitalEmail = new Label("Email: contact@hospital.com");
        hospitalAddress.setFont(Font.font("Arial", 14));
        hospitalPhone.setFont(Font.font("Arial", 14));
        hospitalEmail.setFont(Font.font("Arial", 14));
        VBox contactInfoLayout = new VBox(10);
        contactInfoLayout.setAlignment(Pos.TOP_LEFT);
        contactInfoLayout.getChildren().addAll(hospitalAddress, hospitalPhone, hospitalEmail);

        Label feedbackTitle = new Label("We value your feedback!");
        feedbackTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        TextArea feedbackText = new TextArea();
        feedbackText.setPromptText("Enter your feedback here...");
        feedbackText.setPrefHeight(100);
        Button submitFeedbackButton = new Button("Submit Feedback");
        submitFeedbackButton.setStyle("-fx-background-color: #610b0b; -fx-text-fill: white; -fx-font-size: 14;");

        submitFeedbackButton.setOnAction(e -> {
            String feedback = feedbackText.getText().trim();
            if (!feedback.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Feedback Submitted");
                alert.setHeaderText("Thank you for your feedback!");
                alert.setContentText("Your feedback has been submitted successfully.");
                alert.showAndWait();
                feedbackText.clear();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Feedback Error");
                alert.setHeaderText("Feedback Empty");
                alert.setContentText("Please provide feedback before submitting.");
                alert.showAndWait();
            }
        });

        Label signInTitle = new Label("Sign In Using Email");
        signInTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        TextField emailField = new TextField();
        emailField.setPromptText("Enter your email");
        Button signInButton = new Button("Sign In");
        signInButton.setStyle("-fx-background-color: #610b0b; -fx-text-fill: white; -fx-font-size: 14;");
        signInButton.setOnAction(e -> {
            String email = emailField.getText().trim();
            if (!email.isEmpty() && email.contains("@")) {
                // Simulate sign-in process
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sign In Successful");
                alert.setHeaderText("Welcome!");
                alert.setContentText("You have successfully signed in with: " + email);
                alert.showAndWait();
                emailField.clear();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Sign In Error");
                alert.setHeaderText("Invalid Email");
                alert.setContentText("Please enter a valid email address.");
                alert.showAndWait();
            }
        });

        VBox signInLayout = new VBox(10);
        signInLayout.setAlignment(Pos.CENTER_LEFT);
        signInLayout.getChildren().addAll(signInTitle, emailField, signInButton);
        VBox feedbackLayout = new VBox(10);
        feedbackLayout.setAlignment(Pos.CENTER_LEFT);
        feedbackLayout.getChildren().addAll(feedbackTitle, feedbackText, submitFeedbackButton);
        VBox finalLayout = new VBox(20);
        finalLayout.setPadding(new Insets(20));
        finalLayout.setAlignment(Pos.CENTER);
        finalLayout.getChildren().addAll(contactInfoLayout, feedbackLayout, signInLayout);
        contentArea.getChildren().add(finalLayout);
    }

    private void showSearchRequests(VBox contentArea) {
        contentArea.getChildren().clear();
        Label label = new Label("Search Requests by Blood Group");
        label.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        TextField bloodGroupField = new TextField();
        bloodGroupField.setPromptText("Enter Blood Group (e.g., A+)");
        Button searchButton = new Button("Search");
        searchButton.setStyle("-fx-background-color: #610b0b; -fx-text-fill: white; -fx-font-size: 14;");
        VBox searchLayout = new VBox(10);
        searchLayout.setAlignment(Pos.CENTER);
        searchLayout.getChildren().addAll(label, bloodGroupField, searchButton);
        contentArea.getChildren().add(searchLayout);
        searchButton.setOnAction(e -> {
            String bloodGroup = bloodGroupField.getText().trim();
            if (!bloodGroup.isEmpty()) {
                List<String> filteredRequests = filterRequestsByBloodGroup(bloodGroup);
                contentArea.getChildren().clear();
                Label resultLabel = new Label("Search Results for Blood Group: " + bloodGroup);
                resultLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                contentArea.getChildren().add(resultLabel);

                for (String request : filteredRequests) {
                    Label requestLabel = new Label(request);
                    requestLabel.setFont(Font.font("Arial", 14));
                    contentArea.getChildren().add(requestLabel);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Blood Group Missing");
                alert.setContentText("Please enter a valid blood group to search.");
                alert.showAndWait();
            }
        });
    }
    private List<String> filterRequestsByStatus(String status) {
        List<String> filteredRequests = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\amina\\Documents\\OOPS!!!\\PRACTICE\\bloodbankjava\\src\\main\\java\\com\\example\\bloodbankjava\\dons.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Status: " + status)) {
                    filteredRequests.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filteredRequests;
    }
    private List<String> filterRequestsByBloodGroup(String bloodGroup) {
        List<String> filteredRequests = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\amina\\Documents\\OOPS!!!\\PRACTICE\\bloodbankjava\\src\\main\\java\\com\\example\\bloodbankjava\\dons.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Blood Group: " + bloodGroup)) {
                    filteredRequests.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filteredRequests;
    }
}
