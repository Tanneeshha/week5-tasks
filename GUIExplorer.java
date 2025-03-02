package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUIExplorer extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create UI components
        Label label = new Label("Enter your name:");
        TextField textField = new TextField();
        Button button = new Button("Submit");
        
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Option 1", "Option 2", "Option 3");
        comboBox.setPromptText("Select an option");
        
        Label resultLabel = new Label();
        
        // Additional UI components
        CheckBox checkBox = new CheckBox("Agree to terms");
        RadioButton radioButton1 = new RadioButton("Choice A");
        RadioButton radioButton2 = new RadioButton("Choice B");
        ToggleGroup toggleGroup = new ToggleGroup();
        radioButton1.setToggleGroup(toggleGroup);
        radioButton2.setToggleGroup(toggleGroup);
        
        // Button action
        button.setOnAction(_ -> {
            String name = textField.getText().trim();
            String option = comboBox.getValue();
            RadioButton selectedRadio = (RadioButton) toggleGroup.getSelectedToggle();
            boolean isChecked = checkBox.isSelected();
            
            if (name.isEmpty()) {
                resultLabel.setText("Please enter your name.");
            } else if (option == null) {
                resultLabel.setText("Hello, " + name + "! Please select an option.");
            } else if (!isChecked) {
                resultLabel.setText("Please agree to the terms.");
            } else {
                String radioSelection = (selectedRadio != null) ? selectedRadio.getText() : "None";
                resultLabel.setText("Hello, " + name + "! You selected: " + option + ", " + radioSelection);
            }
        });

        // Layout setup
        VBox root = new VBox(10, label, textField, comboBox, radioButton1, radioButton2, checkBox, button, resultLabel);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 20px;");

        // Scene setup
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("JavaFX GUI Explorer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}