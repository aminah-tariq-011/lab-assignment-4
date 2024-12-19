package com.example.bloodbankjava;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    private Userservice userService = new Userservice();

    @FXML
    private void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();
        try {
            User user = userService.login(email, password);
            if (user == null) {
                showAlert("Login Failed", "Invalid email or password.", Alert.AlertType.ERROR);
            } else {
                showAlert("Login Successful", "Welcome, " + user.getName(), Alert.AlertType.INFORMATION);

                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.close();
                if (user.getRole().equals("Admin")) {
                    openDashboard("admin_dashboard.fxml");
                } else if (user.getRole().equals("Donor")) {
                    openDashboard("donor_dashboard.fxml");
                } else if (user.getRole().equals("Hospital")) {
                    openDashboard("hospital_dashboard.fxml");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while logging in.", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void openDashboard(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
