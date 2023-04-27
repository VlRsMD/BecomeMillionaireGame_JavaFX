package com.example.becomemillionairegame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML private Button signInButton;
    @FXML private Button option1Button;
    @FXML private Button option2Button;
    @FXML private Button option3Button;
    @FXML private Button option4Button;

    @FXML protected void handleSubmitButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) signInButton.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Game.fxml"));
        Parent root = fxmlLoader.load();
        Stage newStage = new Stage();
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setOpacity(1);
        newStage.setTitle("Play the game!");
        newStage.setScene(new Scene(root, 450, 450));
        newStage.show();
    }

    public void handleChooseCorrectAnswerAction(ActionEvent actionEvent) {
        option3Button.setStyle("-fx-background-color: #32cd32");
        option1Button.setStyle("-fx-background-color: #dcdcdc");
        option2Button.setStyle("-fx-background-color: #dcdcdc");
        option4Button.setStyle("-fx-background-color: #dcdcdc");
    }

    public void handleChooseIncorrectAnswer1Action(ActionEvent actionEvent) {
        option1Button.setStyle("-fx-background-color: #ff0000");
        option2Button.setStyle("-fx-background-color: #dcdcdc");
        option3Button.setStyle("-fx-background-color: #dcdcdc");
        option4Button.setStyle("-fx-background-color: #dcdcdc");
    }

    public void handleChooseIncorrectAnswerOption2Action(ActionEvent actionEvent) {
        option2Button.setStyle("-fx-background-color: #ff0000");
        option1Button.setStyle("-fx-background-color: #dcdcdc");
        option3Button.setStyle("-fx-background-color: #dcdcdc");
        option4Button.setStyle("-fx-background-color: #dcdcdc");
    }

    public void handleChooseIncorrectAnswerOption4Action(ActionEvent actionEvent) {
        option4Button.setStyle("-fx-background-color: #ff0000");
        option1Button.setStyle("-fx-background-color: #dcdcdc");
        option2Button.setStyle("-fx-background-color: #dcdcdc");
        option3Button.setStyle("-fx-background-color: #dcdcdc");
    }

    public void handleMoveToNextQuestionAction(ActionEvent actionEvent) {
    }
}