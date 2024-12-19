package com.example.bloodbankjava;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;
import java.util.List;

public class AdminDashboard extends Application {
    private User admin;
    private VBox mainContent;
    private VBox homeScreen;
    public AdminDashboard(User admin) {
        this.admin = admin;
    }
    @Override
    public void start(Stage primaryStage) {
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(20));
        sidebar.setPrefWidth(225);
        sidebar.setStyle("-fx-background-color: #C41E3A;");
        Label titleLabel = new Label("Blood Bank Management");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        titleLabel.setTextFill(Color.WHITE);
        Button homeBtn = createSidebarButton("Home");
        Button donationsBtn = createSidebarButton("Blood Donations");
        Button requestsBtn = createSidebarButton("Requests");
        Button usersBtn = createSidebarButton("Users");
        Button logoutBtn = createSidebarButton("Logout");

        sidebar.getChildren().addAll(
                titleLabel, new Separator(), homeBtn, donationsBtn, requestsBtn,  usersBtn, logoutBtn
        );
        mainContent = new VBox(15);
        mainContent.setPadding(new Insets(20));
        mainContent.setAlignment(Pos.TOP_LEFT);
        homeScreen = createHomeScreen();
        mainContent.getChildren().setAll(homeScreen);
        BorderPane root = new BorderPane();
        root.setLeft(sidebar);
        root.setCenter(mainContent);

        Scene scene = new Scene(root, 900, 600);
        primaryStage.setTitle("Admin Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
        homeBtn.setOnAction(e -> mainContent.getChildren().setAll(homeScreen));
        donationsBtn.setOnAction(e -> mainContent.getChildren().setAll(createDonationManagementScreen()));
        requestsBtn.setOnAction(e -> mainContent.getChildren().setAll(createRequestManagementScreen()));
        usersBtn.setOnAction(e -> mainContent.getChildren().setAll(createManageUsersScreen()));
        logoutBtn.setOnAction(e -> {
            primaryStage.close();
            new Main().start(new Stage());
        });
    }


    private Button createSidebarButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-background-color: transparent;");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setAlignment(Pos.CENTER_LEFT);
        return button;
    }
    private VBox createHomeScreen() {
        VBox home = new VBox(15);
        home.setPadding(new Insets(20));
        home.setAlignment(Pos.TOP_LEFT);
        Label welcomeLabel = new Label("Welcome back Administrator!");
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Label bloodStockTitle = new Label("Available Blood per group in Liters");
        bloodStockTitle.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        GridPane bloodGrid = createBloodGrid();
        HBox statsBox = new HBox(20);
        statsBox.setAlignment(Pos.CENTER);
        VBox totalDonors = createStatBox("3", "Total Donors", "blue");
        VBox totalDonatedToday = createStatBox("1", "Total Donated Today", "red");
        VBox todaysRequests = createStatBox("1", "Today's Requests", "black");
        VBox approvedRequests = createStatBox("1", "Today's Approved Requests", "green");
        statsBox.getChildren().addAll(totalDonors, totalDonatedToday, todaysRequests, approvedRequests);
        home.getChildren().addAll(welcomeLabel, bloodStockTitle, bloodGrid, statsBox);
        return home;
    }
    private GridPane createBloodGrid() {
        GridPane bloodGrid = new GridPane();
        bloodGrid.setHgap(20);
        bloodGrid.setVgap(15);
        bloodGrid.setPadding(new Insets(10, 0, 10, 0));
        String[][] bloodData = {
                {"A+", "0"}, {"B+", "0"}, {"O+", "0.45"}, {"AB+", "0.45"},
                {"A-", "0"}, {"B-", "0.45"}, {"O-", "0"}, {"AB-", "0"}
        };
        int row = 0, col = 0;
        for (String[] bloodInfo : bloodData) {
            VBox bloodBox = createBloodBox(bloodInfo[0], bloodInfo[1]);
            bloodGrid.add(bloodBox, col, row);
            col++;
            if (col > 3) {
                col = 0;
                row++;
            }
        }
        return bloodGrid;
    }
    private VBox createBloodBox(String bloodType, String quantity) {
        VBox box = new VBox(10);
        box.setPrefWidth(200);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(10));
        box.setStyle("-fx-border-color: #DDDDDD; -fx-border-radius: 10; -fx-background-radius: 10;");
        ImageView iconView = new ImageView();
            Image bloodIcon = new Image("C:\\Users\\amina\\Documents\\OOPS!!!\\PRACTICE\\bloodbankjava\\src\\main\\java\\com\\example\\bloodbankjava\\medicine.png"); // Relative file path
            iconView.setImage(bloodIcon);
            iconView.setFitHeight(24);
            iconView.setFitWidth(24);
        Label quantityLabel = new Label(quantity);
        quantityLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        Label bloodTypeLabel = new Label(bloodType);
        bloodTypeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        box.getChildren().addAll(quantityLabel, bloodTypeLabel, iconView);
        return box;
    }
    private VBox createManageUsersScreen() {
        VBox manageUsersLayout = new VBox(20);
        manageUsersLayout.setPadding(new Insets(20));
        manageUsersLayout.setAlignment(Pos.CENTER);

        Label title = new Label("Manage Users");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        TableView<User> userTable = new TableView<>();
        TableColumn<User, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<User, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        TableColumn<User, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn<User, String> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        userTable.getColumns().addAll( nameColumn, roleColumn, emailColumn, passwordColumn);
        userTable.getItems().addAll(Userservice.getAllUsers());

        Button addUserBtn = new Button("Add User");
        Button editUserBtn = new Button("Edit User");
        Button removeUserBtn = new Button("Remove User");
        String buttonStyle = "-fx-background-color: #b81313; -fx-text-fill: white;";
        addUserBtn.setStyle(buttonStyle);
        editUserBtn.setStyle(buttonStyle);
        removeUserBtn.setStyle(buttonStyle);

        HBox buttonBox = new HBox(15, addUserBtn, editUserBtn, removeUserBtn);
        buttonBox.setAlignment(Pos.CENTER);
        manageUsersLayout.getChildren().addAll(title, userTable, buttonBox);
        addUserBtn.setOnAction(e -> {
            Dialog<User> dialog = createUserDialog("Add New User");
            dialog.showAndWait().ifPresent(user -> {
                Userservice.addUser(user);
                userTable.getItems().add(user);
            });
        });
        editUserBtn.setOnAction(e -> {
            User selectedUser = userTable.getSelectionModel().getSelectedItem();
            if (selectedUser == null) {
                showAlert(Alert.AlertType.WARNING, "Select a User", "Please select a user to edit.");
                return;
            }
            Dialog<User> dialog = createUserDialog("Edit User", selectedUser);
            dialog.showAndWait().ifPresent(editedUser -> {
                Userservice.updateUser(editedUser);
                userTable.getItems().set(userTable.getSelectionModel().getSelectedIndex(), editedUser);
            });
        });
        removeUserBtn.setOnAction(e -> {
            User selectedUser = userTable.getSelectionModel().getSelectedItem();
            if (selectedUser == null) {
                showAlert(Alert.AlertType.WARNING, "Select a User", "Please select a user to remove.");
                return;
            }
            if (showConfirmationDialog("Remove User", "Are you sure you want to remove this user?")) {
                Userservice.removeUser(selectedUser);
                userTable.getItems().remove(selectedUser);
            }
        });
        return manageUsersLayout;
    }
    private VBox createStatBox (String value, String description, String color){
            VBox statBox = new VBox(5);
            statBox.setAlignment(Pos.CENTER);
            statBox.setPadding(new Insets(10));
            statBox.setStyle("-fx-border-color: #CCCCCC; -fx-border-radius: 10; -fx-background-radius: 10;");
            Label valueLabel = new Label(value);
            valueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
            valueLabel.setTextFill(Color.web(color));
            Label descLabel = new Label(description);
            descLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
            statBox.getChildren().addAll(valueLabel, descLabel);
            return statBox;
        }
    private Dialog<User> createUserDialog(String title) {
        return createUserDialog(title, null);
    }
    private Dialog<User> createUserDialog(String title, User existingUser) {
        Dialog<User> dialog = new Dialog<>();
        dialog.setTitle(title);

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        ComboBox<String> roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll("Admin", "Donor", "Hospital");
        if (existingUser != null) {
            roleComboBox.setValue(existingUser.getRole());
        }
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        if (existingUser != null) {
            nameField.setText(existingUser.getName());
            emailField.setText(existingUser.getEmail());
            passwordField.setText(existingUser.getPassword());
        }

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Email:"), 0, 1);
        grid.add(emailField, 1, 1);
        grid.add(new Label("Role:"), 0, 2);
        grid.add(roleComboBox, 1, 2);
        grid.add(new Label("Password:"), 0, 3);
        grid.add(passwordField, 1, 3);
        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                return new User(
                        existingUser != null ? existingUser.getUserId() : getNextUserId(),
                      nameField.getText(),
                        roleComboBox.getValue(),
                          emailField.getText(),
                        passwordField.getText()
                );
            }
            return null;
        });

        return dialog;
    }
    private int getNextUserId() {

        List<User> users = Userservice.getAllUsers();
       return users.stream().mapToInt(User::getUserId).max().orElse(0) + 1;
    }
    private boolean showConfirmationDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, content, ButtonType.YES, ButtonType.NO);
      alert.setTitle(title);
        alert.showAndWait();
        return alert.getResult() == ButtonType.YES;
    }
    public VBox createRequestManagementScreen() {
        VBox requestManagementLayout = new VBox(20);
      requestManagementLayout.setPadding(new Insets(20));
        requestManagementLayout.setAlignment(Pos.CENTER);
        Label title = new Label("Manage Requests");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        TableView<Request> requestTable = new TableView<>();
        TableColumn<Request, Integer> idColumn = new TableColumn<>("Request ID");
      idColumn.setCellValueFactory(new PropertyValueFactory<>("requestId"));
        TableColumn<Request, String> hospitalColumn = new TableColumn<>("Hospital");
        hospitalColumn.setCellValueFactory(new PropertyValueFactory<>("hospitalName"));
        TableColumn<Request, String> bloodTypeColumn = new TableColumn<>("Blood Type");
      bloodTypeColumn.setCellValueFactory(new PropertyValueFactory<>("bloodType"));
      TableColumn<Request, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        TableColumn<Request, String> urgencyColumn = new TableColumn<>("Urgency");
        urgencyColumn.setCellValueFactory(new PropertyValueFactory<>("urgency"));
      TableColumn<Request, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusColumn.setCellFactory(param -> new TableCell<Request, String>() {
            @Override
          protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    if (item.equals("Pending")) {
                        setStyle("-fx-background-color: yellow;");
                    } else if (item.equals("Approved")) {
                        setStyle("-fx-background-color: green;");
                    } else if (item.equals("Rejected")) {
                        setStyle("-fx-background-color: red;");
                    }
                }
            }
        });
        TableColumn<Request, String> dateColumn = new TableColumn<>("Request Date");
      dateColumn.setCellValueFactory(new PropertyValueFactory<>("requestDate"));
        requestTable.getColumns().addAll(idColumn, hospitalColumn, bloodTypeColumn, quantityColumn, urgencyColumn, statusColumn, dateColumn);
        requestTable.getItems().addAll(RequestService.getAllRequests());
        Button approveBtn = new Button("Approve Request");
        Button rejectBtn = new Button("Reject Request");
        String buttonStyle = "-fx-background-color: #9c0f0f; -fx-text-fill: white;";
      approveBtn.setStyle(buttonStyle);
        rejectBtn.setStyle(buttonStyle);
        HBox buttonBox = new HBox(15, approveBtn, rejectBtn);
        buttonBox.setAlignment(Pos.CENTER);
        requestManagementLayout.getChildren().addAll(title, requestTable, buttonBox);
        approveBtn.setOnAction(e -> {
            Request selectedRequest = requestTable.getSelectionModel().getSelectedItem();
            if (selectedRequest == null) {
                showAlert(Alert.AlertType.WARNING, "Select a Request", "Please select a request to approve.");
                return;
            }
            selectedRequest.setStatus("Approved");
          RequestService.updateRequestStatus(selectedRequest);
            requestTable.getItems().set(requestTable.getSelectionModel().getSelectedIndex(), selectedRequest);
        });
        rejectBtn.setOnAction(e -> {
            Request selectedRequest = requestTable.getSelectionModel().getSelectedItem();
            if (selectedRequest == null) {
                showAlert(Alert.AlertType.WARNING, "Select a Request", "Please select a request to reject.");
                return;
            }
            selectedRequest.setStatus("Rejected");
          RequestService.updateRequestStatus(selectedRequest);
            requestTable.getItems().set(requestTable.getSelectionModel().getSelectedIndex(), selectedRequest);
        });
        return requestManagementLayout;
    }
    public VBox createDonationManagementScreen() {
        VBox donationManagementLayout = new VBox(20);
      donationManagementLayout.setPadding(new Insets(20));
        donationManagementLayout.setAlignment(Pos.CENTER);
        Label title = new Label("Manage Donations");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        TableView<Donation> donationTable = new TableView<>();
        TableColumn<Donation, String> bloodTypeColumn = new TableColumn<>("Blood Type");
      bloodTypeColumn.setCellValueFactory(new PropertyValueFactory<>("bloodType"));
        TableColumn<Donation, String> dateColumn = new TableColumn<>("Donation Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("donationDate"));

        ObservableList<Donation> Data = FXCollections.observableArrayList(
                DonationService.getAllDonations()
        );
        donationTable.getColumns().addAll(bloodTypeColumn,  dateColumn);
        donationTable.setItems(Data);
        donationManagementLayout.getChildren().addAll(title, donationTable);
        return donationManagementLayout;
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
       alert.setHeaderText(null);
         alert.setContentText(message);
        alert.showAndWait();
    }
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
      alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
        public static void main (String[]args){
            launch(args);
        }
    }
