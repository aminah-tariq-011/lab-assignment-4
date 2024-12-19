package com.example.bloodbankjava;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StatisticsScreen extends Application {

    @Override
    public void start(Stage primaryStage) {

        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.TOP_CENTER);


        Label titleLabel = new Label("Blood Bank Statistics");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2a4b63;");


        PieChart pieChart = new PieChart();
        pieChart.setTitle("Blood Group Distribution");
        pieChart.getData().addAll(
                new PieChart.Data("A+", 30),
                new PieChart.Data("B+", 20),
                new PieChart.Data("O+", 40),
                new PieChart.Data("AB+", 10)
        );


        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Month");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Donations");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Monthly Donations");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("2024");
        series.getData().addAll(
                new XYChart.Data<>("January", 50),
                new XYChart.Data<>("February", 65),
                new XYChart.Data<>("March", 80),
                new XYChart.Data<>("April", 70),
                new XYChart.Data<>("May", 90)
        );

        barChart.getData().add(series);


        NumberAxis lineChartXAxis = new NumberAxis();
        lineChartXAxis.setLabel("Days");

        NumberAxis lineChartYAxis = new NumberAxis();
        lineChartYAxis.setLabel("Requests");

        LineChart<Number, Number> lineChart = new LineChart<>(lineChartXAxis, lineChartYAxis);
        lineChart.setTitle("Blood Requests Over Time");

        XYChart.Series<Number, Number> lineSeries = new XYChart.Series<>();
        lineSeries.setName("Requests");
        lineSeries.getData().addAll(
                new XYChart.Data<>(1, 10),
                new XYChart.Data<>(2, 15),
                new XYChart.Data<>(3, 20),
                new XYChart.Data<>(4, 18),
                new XYChart.Data<>(5, 25)
        );

        lineChart.getData().add(lineSeries);


        mainLayout.getChildren().addAll(titleLabel, pieChart, barChart, lineChart);


        Scene scene = new Scene(mainLayout, 800, 600);
        primaryStage.setTitle("Blood Bank Statistics");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
