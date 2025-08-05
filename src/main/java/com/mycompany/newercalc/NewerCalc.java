/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.newercalc;

/**
 *
 * @author alysamiller
 */


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class NewerCalc extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create UI elements
        Label unitLabel = new Label("Select Unit:");
        ComboBox<String> unitComboBox = new ComboBox<>();
        unitComboBox.getItems().addAll("English (lbs, inches)", "Metric (kg, meters)");
        unitComboBox.setValue("English (lbs, inches)");

        Label weightLabel = new Label("Weight:");
        TextField weightField = new TextField();

        Label heightLabel = new Label("Height:");
        TextField heightField = new TextField();

        Button calculateButton = new Button("Calculate BMI");
        Label resultLabel = new Label();
        TextArea bmiTable = new TextArea();
        bmiTable.setEditable(false);
        bmiTable.setText("BMI VALUES:\n"
                       + "Underweight: less than 18.5\n"
                       + "Normal:      between 18.5 and 24.9\n"
                       + "Overweight:  between 25 and 29.9\n"
                       + "Obese:       30 or greater");

        // Layout using VBox
        VBox layout = new VBox(10);  // spacing between elements
        layout.setPadding(new Insets(15));
        layout.getChildren().addAll(
            unitLabel, unitComboBox,
            weightLabel, weightField,
            heightLabel, heightField,
            calculateButton, resultLabel,
            bmiTable
        );

        // Event handler for button click
        calculateButton.setOnAction(e -> {
            try {
                double weight = Double.parseDouble(weightField.getText());
                double height = Double.parseDouble(heightField.getText());
                double bmi;

                // Determine formula based on selected unit
                if (unitComboBox.getValue().contains("English")) {
                    bmi = (weight * 703) / (height * height);
                } else {
                    bmi = weight / (height * height);
                }

                String category;
                if (bmi < 18.5) {
                    category = "Underweight";
                } else if (bmi < 25) {
                    category = "Normal weight";
                } else if (bmi < 30) {
                    category = "Overweight";
                } else {
                    category = "Obese";
                }

                resultLabel.setText(String.format("Your BMI is %.2f - %s", bmi, category));
            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter valid numeric values.");
            }
        });

        // Set scene and show stage
        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setTitle("BMI Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


