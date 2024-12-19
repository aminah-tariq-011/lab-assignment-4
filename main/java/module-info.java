module com.example.bloodbankjava {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.bloodbankjava to javafx.fxml;
    exports com.example.bloodbankjava;
}