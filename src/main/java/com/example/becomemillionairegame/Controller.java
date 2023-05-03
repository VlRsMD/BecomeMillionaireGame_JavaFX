package com.example.becomemillionairegame;

import com.example.becomemillionairegame.questions_data.Question;
import com.example.becomemillionairegame.questions_data.QuestionsBank;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML private Button signInButton;
    @FXML private Button option1Button;
    @FXML private Button option2Button;
    @FXML private Button option3Button;
    @FXML private Button option4Button;
    @FXML private Button nextButton;
    @FXML private Text question;
    @FXML private Text result;

    @FXML public void displayQuestionData() {
        QuestionsBank questionBank = new QuestionsBank();
        Question question1 = questionBank.allQuestions.get(0);
        String questionText = question1.getQuestion();
        String answerOption1 = question1.getAnswersOptions()[0].getAnswer();
        String answerOption2 = question1.getAnswersOptions()[1].getAnswer();
        String answerOption3 = question1.getAnswersOptions()[2].getAnswer();
        String answerOption4 = question1.getAnswersOptions()[3].getAnswer();
        nextButton.setStyle("-fx-background-color: #DCDCDC");
        question.setText(questionText);
        option1Button.setText(answerOption1);
        option2Button.setText(answerOption2);
        option3Button.setText(answerOption3);
        option4Button.setText(answerOption4);
    }

    @FXML protected void handleSubmitButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) signInButton.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Game.fxml"));
        Parent root = fxmlLoader.load();
        Controller controller = fxmlLoader.<Controller>getController();
        controller.displayQuestionData();
        Stage newStage = new Stage();
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setOpacity(1);
        newStage.setTitle("Play the game!");
        newStage.setScene(new Scene(root, 450, 450));
        newStage.show();
    }

    public void handleChooseOption1Action(ActionEvent actionEvent) {
        option1Button.setStyle("-fx-background-color: #ff0000");
        result.setText("Unfortunately you have answered incorrectly. You may wish to try the game once again!");
        nextButton.setStyle("-fx-background-color: #DCDCDC");
        option2Button.setStyle("-fx-background-color: #dcdcdc");
        option3Button.setStyle("-fx-background-color: #dcdcdc");
        option4Button.setStyle("-fx-background-color: #dcdcdc");
    }

    public void handleChooseOption2Action(ActionEvent actionEvent) {
        option2Button.setStyle("-fx-background-color: #ff0000");
        result.setText("Unfortunately you have answered incorrectly. You may wish to try the game once again!");
        // result.setStyle("-fx-text-fill: #ff0000");
        nextButton.setStyle("-fx-background-color: #DCDCDC");
        option1Button.setStyle("-fx-background-color: #dcdcdc");
        option3Button.setStyle("-fx-background-color: #dcdcdc");
        option4Button.setStyle("-fx-background-color: #dcdcdc");
    }

    public void handleChooseOption3Action(ActionEvent actionEvent) {
        option3Button.setStyle("-fx-background-color: #32cd32");
        QuestionsBank questionBank = new QuestionsBank();
        Question question1 = questionBank.allQuestions.get(0);
        int correctAnswerOnQuestion1Score = question1.getScore();
        result.setText("Congratulations! You have answered this question correctly. Your current score is " + correctAnswerOnQuestion1Score + " points.");
        nextButton.setStyle("-fx-background-color: #90EE90");
        option1Button.setStyle("-fx-background-color: #dcdcdc");
        option2Button.setStyle("-fx-background-color: #dcdcdc");
        option4Button.setStyle("-fx-background-color: #dcdcdc");
    }

    public void handleChooseOption4Action(ActionEvent actionEvent) {
        option4Button.setStyle("-fx-background-color: #ff0000");
        result.setText("Unfortunately you have answered incorrectly. You may wish to try the game once again!");
        nextButton.setStyle("-fx-background-color: #DCDCDC");
        option1Button.setStyle("-fx-background-color: #dcdcdc");
        option2Button.setStyle("-fx-background-color: #dcdcdc");
        option3Button.setStyle("-fx-background-color: #dcdcdc");
    }

    public void handleMoveToNextQuestionAction(ActionEvent actionEvent) {
    }
}