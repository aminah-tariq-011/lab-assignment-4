package com.example.bloodbankjava;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.List;

public class Main extends Application {
    private static final String FILE_PATH = "C:\\Users\\amina\\Documents\\OOPS!!!\\PRACTICE\\bloodbankjava\\src\\main\\java\\com\\example\\bloodbankjava\\user.txt";
    @Override
    public void start(Stage primaryStage) {
        HBox mainLayout = new HBox();
        mainLayout.setAlignment(Pos.CENTER_LEFT);
        mainLayout.setStyle("-fx-background-color: #000000;");
        VBox formLayout = new VBox();
        formLayout.setAlignment(Pos.CENTER_LEFT);
        formLayout.setSpacing(20);

        Label title = new Label("Blood Donation System");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        title.setTextFill(Color.web("#FFFFFF"));
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter Username");
        usernameField.setFont(Font.font(16));
        usernameField.setPrefWidth(300);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");
        passwordField.setFont(Font.font(16));
        passwordField.setPrefWidth(300);
        HBox buttonLayout = new HBox();
        buttonLayout.setAlignment(Pos.CENTER_LEFT);
        buttonLayout.setSpacing(20);

        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #0d47a1; -fx-text-fill: white; -fx-font-size: 18; -fx-padding: 10;");
        loginButton.setPrefWidth(200);
        Button signUpButton = new Button("Sign Up");
        signUpButton.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-font-size: 18; -fx-padding: 10;");
        signUpButton.setPrefWidth(200);
        buttonLayout.getChildren().addAll(loginButton, signUpButton);

        formLayout.setPadding(new Insets(50));
        formLayout.setSpacing(30);
        formLayout.getChildren().addAll(title, usernameField, passwordField, buttonLayout);
        Image image = new Image("C:\\Users\\amina\\Documents\\OOPS!!!\\PRACTICE\\bloodbankjava\\src\\main\\java\\com\\example\\bloodbankjava\\WhatsApp Image 2024-12-12 at 20.41.14_dca6454d.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(400);
        imageView.setFitHeight(600);
        imageView.setPreserveRatio(true);
        mainLayout.getChildren().addAll(formLayout, imageView);

        signUpButton.setOnAction(e -> {
            SignUpScreen signUpScreen = new SignUpScreen();
            signUpScreen.start(new Stage());
        });

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            User loggedInUser = getUserByUsername(username);
            if (loggedInUser != null && loggedInUser.getPassword().equals(password)) {
                if ("Hospital".equalsIgnoreCase(loggedInUser.getRole())) {
                    new HospitalDashboard(loggedInUser).start(new Stage());
                } else if ("Donor".equalsIgnoreCase(loggedInUser.getRole())) {
                    new DonorDashboard(loggedInUser).start(new Stage());
                } else if ("Admin".equalsIgnoreCase(loggedInUser.getRole())) {
                    new AdminDashboard(loggedInUser).start(new Stage());
                } else {
                    showAlert("Error", "Unknown role. Please check your credentials.", Alert.AlertType.ERROR);
                }
                primaryStage.close();
            } else {
                showAlert("Error", "Invalid username or password.", Alert.AlertType.ERROR);
            }
        });
        Scene scene = new Scene(mainLayout, 800, 500);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private User getUserByUsername(String username) {
        try {
            List<String> lines = FileReaderWriter.readFile(FILE_PATH);
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    String role = parts[2].trim();
                    String email = parts[3].trim();
                    String password = parts[4].trim();
                    if (username.equals(email)) {
                        return new User(id, name, role, email, password);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

