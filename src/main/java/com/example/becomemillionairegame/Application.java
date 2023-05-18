package com.example.becomemillionairegame;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Welcome.fxml"));
        Parent root = fxmlLoader.load();
        Controller controller = fxmlLoader.<Controller>getController();
        controller.welcomeText.setFont(Font.font("Calibri", FontWeight.BOLD, 14));
        controller.playButton.setStyle("-fx-background-color: #ffd700");
        Stage newStage = new Stage();
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setOpacity(1);
        newStage.setTitle("Welcome!");
        newStage.setScene(new Scene(root, 450, 360));
        newStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}