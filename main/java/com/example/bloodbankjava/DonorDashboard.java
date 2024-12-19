package com.example.bloodbankjava;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import java.time.LocalDate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class DonorDashboard extends Application {

    private BorderPane rootLayout;
    private VBox sidebar;
    private VBox centralPanel;
    private User loggedInUser;

    public DonorDashboard(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
    @Override
    public void start(Stage primaryStage) {
        rootLayout = new BorderPane();
        sidebar = createSidebar();
        rootLayout.setLeft(sidebar);
        centralPanel = new VBox();
        centralPanel.setAlignment(Pos.CENTER);
        centralPanel.setSpacing(20);
        Label dashboardLabel = new Label("Donor Dashboard");
        dashboardLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        dashboardLabel.setStyle(
                "-fx-border-color: black; " +
                        "-fx-border-width: 2px; " +
                        "-fx-background-color: #c87d7d; " +
                        "-fx-padding: 10;"
        );
        Label welcomeLabel = new Label("Welcome, " + loggedInUser.getName() + "!");
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Image donorImage = new Image("C:\\Users\\amina\\Documents\\OOPS!!!\\PRACTICE\\bloodbankjava\\src\\main\\java\\com\\example\\bloodbankjava\\Blood-1.png"); // Ensure the image is placed in the correct folder
        ImageView imageView = new ImageView(donorImage);
        imageView.setFitWidth(600);
        imageView.setFitHeight(600);
        imageView.setPreserveRatio(true);
        StackPane imageContainer = new StackPane(imageView);
        imageContainer.setStyle(
                "-fx-border-color: #3c0505; " +
                        "-fx-border-width: 4px; " +
                        "-fx-padding: 10;"
        );
        centralPanel.getChildren().addAll(dashboardLabel, welcomeLabel, imageView);
        rootLayout.setCenter(centralPanel);
        Scene scene = new Scene(rootLayout, 900, 600);
        primaryStage.setTitle("Donor Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createSidebar() {
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #5A0A0A;");
        Button viewHistory = new Button("View Donation History");
        Button updateProfile = new Button("Update Profile");
        Button healthTips = new Button("Health Tips");
        Button events = new Button("Upcoming Events");
        Button appointment = new Button("Request Appointment");
        Button logout = new Button("Logout");

        styleSidebarButton(viewHistory);
        styleSidebarButton(updateProfile);
        styleSidebarButton(healthTips);
        styleSidebarButton(events);
        styleSidebarButton(appointment);
        styleSidebarButton(logout);

        updateProfile.setOnAction(e -> showUpdateProfilePanel());
        viewHistory.setOnAction(e -> showDonationHistory());
        healthTips.setOnAction(e -> showHealthTips());
        events.setOnAction(e -> showUpcomingEvents());
        appointment.setOnAction(e -> showRequestAppointmentForm());
        logout.setOnAction(e -> logout());
        sidebar.getChildren().addAll(viewHistory, updateProfile, healthTips, events, appointment, logout);
        return sidebar;
    }
    private void styleSidebarButton(Button button) {
        button.setPrefWidth(200);
        button.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-weight: bold;");
    }

    private void showUpdateProfilePanel() {
        GridPane formLayout = new GridPane();
        formLayout.setPadding(new Insets(20));
        formLayout.setVgap(10);
        formLayout.setHgap(10);
        Label banner = new Label("WELCOME JANE");
        banner.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        Label contactLabel = new Label("Contact:");
        TextField contactField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Label genderLabel = new Label("Gender:");
        ComboBox<String> genderCombo = new ComboBox<>();
        genderCombo.getItems().addAll("Male", "Female", "Other");
        Label bloodGroupLabel = new Label("Blood Group:");
        ComboBox<String> bloodGroupCombo = new ComboBox<>();
        bloodGroupCombo.getItems().addAll("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");
        Label cityLabel = new Label("City:");
        ComboBox<String> cityCombo = new ComboBox<>();
        cityCombo.getItems().addAll("Lahore", "Multan", "Islamabad", "Quetta", "Peshawar");
        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        saveButton.setOnAction(e -> handleSaveButton());

        formLayout.add(nameLabel, 0, 0);
        formLayout.add(nameField, 1, 0);
        formLayout.add(emailLabel, 0, 1);
        formLayout.add(emailField, 1, 1);
        formLayout.add(contactLabel, 0, 2);
        formLayout.add(contactField, 1, 2);
        formLayout.add(passwordLabel, 0, 3);
        formLayout.add(passwordField, 1, 3);
        formLayout.add(genderLabel, 0, 4);
        formLayout.add(genderCombo, 1, 4);
        formLayout.add(bloodGroupLabel, 0, 5);
        formLayout.add(bloodGroupCombo, 1, 5);
        formLayout.add(cityLabel, 0, 6);
        formLayout.add(cityCombo, 1, 6);
        formLayout.add(saveButton, 1, 7);
        centralPanel.getChildren().clear();
        centralPanel.getChildren().addAll(banner,formLayout);
    }
    private void handleSaveButton() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Profile Updated Successfully!");
        alert.showAndWait();
    }

    private void showDonationHistory() {
        VBox historyLayout = new VBox(20);
        historyLayout.setAlignment(Pos.CENTER);
        Label banner = new Label("DONOR DASHBOARD\n   WELCOME JANE");
        banner.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        TableView<DonationRecord> table = new TableView<>();
        table.setMaxWidth(600);
        table.setMaxHeight(300);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<DonationRecord, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(data -> data.getValue().dateProperty());
        TableColumn<DonationRecord, String> centerColumn = new TableColumn<>("Donation Center");
        centerColumn.setCellValueFactory(data -> data.getValue().centerProperty());
        TableColumn<DonationRecord, String> bloodTypeColumn = new TableColumn<>("Blood Type");
        bloodTypeColumn.setCellValueFactory(data -> data.getValue().bloodTypeProperty());
        table.getColumns().addAll(dateColumn, centerColumn, bloodTypeColumn);
        table.getItems().addAll(
                new DonationRecord("2024-03-15", "City Hospital", "A+"),
                new DonationRecord("2023-12-01", "Red Cross Center", "B+"),
                new DonationRecord("2023-08-19", "Health Clinic", "O+")
        );
        historyLayout.getChildren().add(table);
        centralPanel.getChildren().clear();
        centralPanel.getChildren().addAll(banner,historyLayout);
    }

    private void showHealthTips() {
        VBox healthTipsLayout = new VBox(15);
        Label banner = new Label("WELCOME JANE");
        banner.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        healthTipsLayout.setPadding(new Insets(20));
        healthTipsLayout.setAlignment(Pos.TOP_LEFT);
        Label title = new Label("Health Tips for Donors");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setTextFill(Color.DARKRED);
        Label tipsContent = new Label(
                "- Drink plenty of water before and after donation.\n" +
                        "- Eat a healthy, iron-rich meal before donating.\n" +
                        "- Avoid strenuous activities for at least 24 hours post-donation.\n" +
                        "- Inform the staff if you feel unwell or dizzy after donating.\n" +
                        "- Regular blood donation improves heart health and reduces harmful iron levels."
        );
        tipsContent.setFont(Font.font("Arial", 16));
        tipsContent.setWrapText(true);
        healthTipsLayout.getChildren().addAll(title, tipsContent);

        centralPanel.getChildren().clear();
        centralPanel.getChildren().addAll(banner,healthTipsLayout);
    }

    private void showUpcomingEvents() {
        VBox eventsLayout = new VBox(58);
        Label banner = new Label("WELCOME JANE");
        banner.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        eventsLayout.setAlignment(Pos.CENTER);
        eventsLayout.setStyle("-fx-background-color: #fde3e3; -fx-padding: 20px;");
        Label event1 = createStyledEventLabel("1. Blood Donation Drive - City Center\n   Date: 2024-12-21 | Time: 10:00 AM - 3:00 PM\n   Location: City Center Park, Multan");
        Label event2 = createStyledEventLabel("2. Health Awareness Camp\n   Date: 2024-12-23 | Time: 9:00 AM - 1:00 PM\n   Location: Health Center, Multan");
        Label event3 = createStyledEventLabel("3. Emergency Blood Drive - Urgent Need\n   Date: 2024-12-25 | Time: 12:00 PM - 6:00 PM\n   Location: Multan Blood Bank");
        eventsLayout.getChildren().addAll(banner, event1, event2, event3);
        centralPanel.getChildren().clear();
        centralPanel.getChildren().add(eventsLayout);
    }

    private Label createStyledEventLabel(String eventDetails) {
        Label banner = new Label("WELCOME JOHN");
        banner.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        Label eventLabel = new Label(eventDetails);
        eventLabel.setStyle(
                "-fx-font-size: 18px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-text-fill: #333333; " +
                        "-fx-background-color: #fde3e3; " +
                        "-fx-padding: 10px; " +
                        "-fx-border-radius: 10px; " +
                        "-fx-border-color: #ca7a7a; " +
                        "-fx-border-width: 2px; "

        );
        eventLabel.setMaxWidth(600);
        eventLabel.setWrapText(true);
        return eventLabel;
    }
    private void showRequestAppointmentForm() {
        Label banner = new Label("WELCOME JANE");
        banner.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        GridPane formLayout = new GridPane();
        formLayout.setPadding(new Insets(20));
        formLayout.setVgap(10);
        formLayout.setHgap(10);
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        Label contactLabel = new Label("Contact Number:");
        TextField contactField = new TextField();
        Label bloodTypeLabel = new Label("Blood Type:");
        ComboBox<String> bloodTypeCombo = new ComboBox<>();
        bloodTypeCombo.getItems().addAll("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-");
        Label centerLabel = new Label("Donation Center:");
        ComboBox<String> centerCombo = new ComboBox<>();
        centerCombo.getItems().addAll("City Hospital", "Red Cross Center", "Health Clinic");
        Label dateLabel = new Label("Preferred Date:");
        DatePicker datePicker = new DatePicker();
        Label timeLabel = new Label("Preferred Time:");
        ComboBox<String> timeCombo = new ComboBox<>();
        timeCombo.getItems().addAll("10:00 AM", "12:00 PM", "2:00 PM", "4:00 PM");

        Button submitButton = new Button("Request Appointment");
        submitButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        submitButton.setOnAction(e -> handleAppointmentRequest(nameField.getText(), emailField.getText(), contactField.getText(),
                bloodTypeCombo.getValue(), centerCombo.getValue(), datePicker.getValue(), timeCombo.getValue()));
        formLayout.add(nameLabel, 0, 0);
        formLayout.add(nameField, 1, 0);
        formLayout.add(emailLabel, 0, 1);
        formLayout.add(emailField, 1, 1);
        formLayout.add(contactLabel, 0, 2);
        formLayout.add(contactField, 1, 2);
        formLayout.add(bloodTypeLabel, 0, 3);
        formLayout.add(bloodTypeCombo, 1, 3);
        formLayout.add(centerLabel, 0, 4);
        formLayout.add(centerCombo, 1, 4);
        formLayout.add(dateLabel, 0, 5);
        formLayout.add(datePicker, 1, 5);
        formLayout.add(timeLabel, 0, 6);
        formLayout.add(timeCombo, 1, 6);
        formLayout.add(submitButton, 1, 7);
        centralPanel.getChildren().clear();
        centralPanel.getChildren().addAll(banner,formLayout);
    }
    private void handleAppointmentRequest(String name, String email, String contact, String bloodType, String center,
                                          LocalDate date, String time) {
        System.out.println("Appointment Requested: " + name + ", " + email + ", " + bloodType + ", " + center + ", "
                + date + " " + time);
        showConfirmationMessage();
    }
    private void showConfirmationMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Appointment Request");
        alert.setHeaderText("Your appointment has been successfully requested!");
        alert.setContentText("You will receive a confirmation email shortly.");
        alert.showAndWait();
    }

    private void logout() {
        Stage stage = (Stage) rootLayout.getScene().getWindow();
        stage.close();
        new Main().start(new Stage());
    }
    public static void main(String[] args) {
        User dummyUser = new User(1, "John Doe", "Donor", "john@example.com", "password123");
        new DonorDashboard(dummyUser).start(new Stage());
    }
}
