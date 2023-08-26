package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onAddKitButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add_kit_window.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    //add postgre sql driver in project and in maven file
    @FXML
    protected void onAddUserButtonClick(){
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "elkin";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (name, email, country, password) VALUES ( ?, ?, ?, ?)")) {

            stmt.setString(1, "John Doe"); // Set the value for the "name" column
            stmt.setString(2, "john.doe@example.com"); // Set the value for the "email" column
            stmt.setString(3, "USA"); // Set the value for the "country" column
            stmt.setString(4, "password123"); // Set the value for the "password" column

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new row has been inserted successfully.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}