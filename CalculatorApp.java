package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CalculatorApp extends Application {
    private TextField display = new TextField();
    private StringBuilder input = new StringBuilder();

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        display.setEditable(false);
        display.setStyle("-fx-font-size: 18px;");
        display.setPrefHeight(50);
        grid.add(display, 0, 0, 4, 1);

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        int row = 1, col = 0;
        for (String text : buttons) {
            Button button = new Button(text);
            button.setStyle("-fx-font-size: 18px;");
            button.setPrefSize(50, 50);
            button.setOnAction(_ -> handleButtonClick(text));
            grid.add(button, col, row);
            col++;
            if (col > 3) {
                col = 0;
                row++;
            }
        }

        Scene scene = new Scene(grid, 230, 300);
        primaryStage.setTitle("Simple Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleButtonClick(String text) {
        if (text.equals("C")) {
            input.setLength(0);
            display.setText("");
        } else if (text.equals("=")) {
            try {
                double result = evaluateExpression(input.toString());
                display.setText(String.valueOf(result));
                input.setLength(0);
            } catch (Exception e) {
                display.setText("Error");
            }
        } else {
            input.append(text);
            display.setText(input.toString());
        }
    }

    private double evaluateExpression(String expression) {
        try {
            char[] tokens = expression.toCharArray();
            double result = 0;
            double currentNumber = 0;
            char lastOperator = '+';
            
            for (int i = 0; i < tokens.length; i++) {
                char token = tokens[i];
                if (Character.isDigit(token)) {
                    currentNumber = currentNumber * 10 + (token - '0');
                }
                if (!Character.isDigit(token) || i == tokens.length - 1) {
                    switch (lastOperator) {
                        case '+': result += currentNumber; break;
                        case '-': result -= currentNumber; break;
                        case '*': result *= currentNumber; break;
                        case '/': result /= currentNumber; break;
                    }
                    lastOperator = token;
                    currentNumber = 0;
                }
            }
            return result;
        } catch (Exception e) {
            return Double.NaN;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
