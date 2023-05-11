package com.example.becomemillionairegame;

import com.example.becomemillionairegame.questions_data.Answer;
import com.example.becomemillionairegame.questions_data.Question;
import com.example.becomemillionairegame.questions_data.QuestionsBank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Controller {
    @FXML private Button signInButton;
    @FXML private Text question;
    @FXML private Button option1Button;
    @FXML private Button option2Button;
    @FXML private Button option3Button;
    @FXML private Button option4Button;
    @FXML private Text result;
    @FXML private Button nextButton;
    public static int questionCounter;
    public static boolean isLastQuestion;
    public static int score;

    @FXML protected void handleSubmitButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) signInButton.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Game.fxml"));
        Parent root = fxmlLoader.load();
        Controller controller = fxmlLoader.<Controller>getController();
        questionCounter = 1;
        isLastQuestion = false;
        score = 0;
        controller.displayQuestionData(questionCounter);
        Stage newStage = new Stage();
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setOpacity(1);
        newStage.setTitle("Play the game!");
        newStage.setScene(new Scene(root, 450, 450));
        newStage.show();
    }

    @FXML protected void displayQuestionData(int level) {
        QuestionExtractor questionExtractor = new QuestionExtractor();
        Question currentQuestion = questionExtractor.getSpecificLevelQuestion(level);
        String questionText =currentQuestion.getQuestion();
        List<Answer> answerOptions = new ArrayList<Answer>();
        for (int i=0; i<currentQuestion.getAnswersOptions().length; i++) {
            answerOptions.add(currentQuestion.getAnswersOptions()[i]);
        }
        Collections.shuffle(answerOptions);
        String answerOption1 = answerOptions.get(0).getAnswer();
        String answerOption2 = answerOptions.get(1).getAnswer();
        String answerOption3 = answerOptions.get(2).getAnswer();
        String answerOption4 = answerOptions.get(3).getAnswer();
        question.setFont(Font.font("Calibri", FontWeight.BOLD, 14));
        question.setText(questionText);
        option1Button.setDisable(false);
        option2Button.setDisable(false);
        option3Button.setDisable(false);
        option4Button.setDisable(false);
        option1Button.setStyle("-fx-background-color: #00ffff");
        option2Button.setStyle("-fx-background-color: #00ffff");
        option3Button.setStyle("-fx-background-color: #00ffff");
        option4Button.setStyle("-fx-background-color: #00ffff");
        option1Button.setText(answerOption1);
        option2Button.setText(answerOption2);
        option3Button.setText(answerOption3);
        option4Button.setText(answerOption4);
        result.setText("");
        nextButton.setDisable(true);
        nextButton.setStyle("-fx-background-color: #ffd700");
    }

    public void handleChooseAnswerOption1Action(ActionEvent actionEvent) {
        handleAnsweringQuestion(option1Button);
        option2Button.setDisable(true);
        option3Button.setDisable(true);
        option4Button.setDisable(true);
    }

    public void handleChooseAnswerOption2Action(ActionEvent actionEvent) {
        handleAnsweringQuestion(option2Button);
        option1Button.setDisable(true);
        option3Button.setDisable(true);
        option4Button.setDisable(true);
    }

    public void handleChooseAnswerOption3Action(ActionEvent actionEvent) {
        handleAnsweringQuestion(option3Button);
        option1Button.setDisable(true);
        option2Button.setDisable(true);
        option4Button.setDisable(true);
    }

    public void handleChooseAnswerOption4Action(ActionEvent actionEvent) {
        handleAnsweringQuestion(option4Button);
        option1Button.setDisable(true);
        option2Button.setDisable(true);
        option3Button.setDisable(true);
    }

    public void handleMoveToNextQuestionAction(ActionEvent actionEvent) {
        ++questionCounter;
        QuestionsBank questionsBank = new QuestionsBank();
        if (questionCounter == questionsBank.numberOfLevels) {
            nextButton.setDisable(true);
            isLastQuestion = true;
        }
        System.out.println(questionCounter);
        displayQuestionData(questionCounter);
    }

    public void handleAnsweringQuestion(Button button) {
        String questionText = question.getText();
        QuestionsBank questionBank = new QuestionsBank();
        Question currentQuestion = null;
        for (int i=0; i<questionBank.getAllQuestions().size(); i++) {
            if (questionText.equals(questionBank.getAllQuestions().get(i).getQuestion())) {
                currentQuestion = questionBank.getAllQuestions().get(i);
                break;
            }
        }
        Answer currentAnswerOption = null;
        for (int i=0; i<currentQuestion.getAnswersOptions().length; i++) {
            if (button.getText().equals(currentQuestion.getAnswersOptions()[i].getAnswer())) {
                currentAnswerOption = currentQuestion.getAnswersOptions()[i];
                break;
            }
        }
        if (currentAnswerOption.isCorrect()) {
            handleCorrectAnswer(button, currentQuestion);
        } else {
            handleIncorrectAnswer(button);
        }
    }

    public void handleCorrectAnswer(Button button, Question question) {
        button.setStyle("-fx-background-color: #32cd32");
        score = score + question.getScore();
        if(!isLastQuestion) {
            result.setFill(Color.GREEN);
            result.setText("Congratulations! You have answered this question correctly. Your current score is " + score + " points.");
            nextButton.setDisable(false);
        } else {
            result.setFill(Color.DARKGOLDENROD);
            result.setText("Congratulations! You have become a millionaire! Your current score is " + score + " points.");

        }
    }

    public void handleIncorrectAnswer(Button button) {
        button.setStyle("-fx-background-color: #ff0000");
        result.setFill(Color.RED);
        result.setText("Unfortunately you have answered incorrectly. You may wish to try the game once again!");
        if(!isLastQuestion) {
            nextButton.setDisable(true);
        }
    }
}