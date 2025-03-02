package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InventoryApp extends Application {
    private final ObservableList<String> inventory = FXCollections.observableArrayList();
    private final ListView<String> listView = new ListView<>(inventory);
    private final TextField itemField = new TextField();

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Enter item:");
        Button addButton = new Button("Add");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");
        Button readButton = new Button("Read");

        addButton.setOnAction(_ -> addItem());
        updateButton.setOnAction(_ -> updateItem());
        deleteButton.setOnAction(_ -> deleteItem());
        readButton.setOnAction(_ -> readItem());

        VBox root = new VBox(10, label, itemField, addButton, updateButton, deleteButton, readButton, listView);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 300, 450);
        primaryStage.setTitle("Inventory Management");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addItem() {
        String item = itemField.getText().trim();
        if (!item.isEmpty() && !inventory.contains(item)) {
            inventory.add(item);
            itemField.clear();
        }
    }

    private void updateItem() {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        String newItem = itemField.getText().trim();
        if (selectedIndex != -1 && !newItem.isEmpty()) {
            inventory.set(selectedIndex, newItem);
            itemField.clear();
        }
    }

    private void deleteItem() {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            inventory.remove(selectedIndex);
        }
    }

    private void readItem() {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            itemField.setText(inventory.get(selectedIndex));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
