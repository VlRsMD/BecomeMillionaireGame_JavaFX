package com.example.becomemillionairegame;

import com.example.becomemillionairegame.questionsdata.Answer;
import com.example.becomemillionairegame.questionsdata.Question;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class Controller {
    @FXML public Text welcomeText;
    @FXML public Button playButton;
    @FXML private TextField playerName;
    @FXML private Text reminder;
    @FXML private Text question;
    @FXML private Button option1Button;
    @FXML private Button option2Button;
    @FXML private Button option3Button;
    @FXML private Button option4Button;
    @FXML private Button askAudienceButton;
    @FXML private Button phoneFriendButton;
    @FXML private Button fiftyFiftyButton;
    @FXML private Text helpText;
    @FXML private Text result;
    @FXML private Button nextButton;
    public static GameSession gameSession = new GameSession();
    public static Question currentQuestion;

    @FXML protected void startTheGame(ActionEvent actionEvent) throws IOException {
        if(playerName.getText().equals("")) {
            reminder.setFill(Color.RED);
            reminder.setText("Please introduce your name!");
        } else {
            Stage oldStage = (Stage) playButton.getScene().getWindow();
            oldStage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Game.fxml"));
            Parent root = fxmlLoader.load();
            Controller controller = fxmlLoader.<Controller>getController();
            controller.displayQuestionData(gameSession.getQuestionCounter());
            gameSession.setUserName(playerName.getText());
            String helpOptionButtonsBackgroundColorSetter = "-fx-background-color: #7fffd4";
            controller.helpText.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
            controller.askAudienceButton.setStyle(helpOptionButtonsBackgroundColorSetter);
            controller.phoneFriendButton.setStyle(helpOptionButtonsBackgroundColorSetter);
            controller.fiftyFiftyButton.setStyle(helpOptionButtonsBackgroundColorSetter);
            Stage newStage = new Stage();
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.setOpacity(1);
            newStage.setTitle("Play!");
            newStage.setScene(new Scene(root, 450, 620));
            newStage.show();
        }
    }

    public void displayQuestionData(int level) {
        currentQuestion = getSpecificLevelRandomQuestion(level);
        String questionText = currentQuestion.getQuestion();
        List<Answer> answerOptions = new ArrayList<>(Arrays.asList(currentQuestion.getAnswersOptions()));
        Collections.shuffle(answerOptions);
        String answerOption1 = answerOptions.get(0).getAnswer();
        String answerOption2 = answerOptions.get(1).getAnswer();
        String answerOption3 = answerOptions.get(2).getAnswer();
        String answerOption4 = answerOptions.get(3).getAnswer();
        question.setFont(Font.font("Calibri", FontWeight.BOLD, 14));
        question.setText(questionText);
        List<Button> buttons = getAllAnswerOptionButtons();
        List<String> answerOptionsText = new ArrayList<>() {{
            add(answerOption1);
            add(answerOption2);
            add(answerOption3);
            add(answerOption4);
        }};
        setAttributesOfOptionButtons(buttons, answerOptionsText);
        if(gameSession.isAskAudienceHelpOptionNotUsed()) {
            askAudienceButton.setDisable(false);
        }
        if(gameSession.isPhoneFriendHelpOptionNotUsed()) {
            phoneFriendButton.setDisable(false);
        }
        if(gameSession.isFiftyFiftyHelpOptionNotUsed()) {
            fiftyFiftyButton.setDisable(false);
        }
        result.setText("");
        nextButton.setDisable(true);
        nextButton.setStyle("-fx-background-color: #ffd700");
    }

    public List<Button> getAllAnswerOptionButtons() {
        return new ArrayList<>() {{
            add(option1Button);
            add(option2Button);
            add(option3Button);
            add(option4Button);
        }};
    }

    public Question getSpecificLevelRandomQuestion(int level) {
        List<Question> levelQuestions = new ArrayList<>();
        for(Map.Entry<Integer, List<Question>> entry : gameSession.getQuestionsBank().getMapForSpecificLevelsQuestions().entrySet()) {
            if(entry.getKey() == level) {
                levelQuestions.addAll(entry.getValue());
            }
        }
        Random rand = new Random();
        int r = rand.nextInt(3);
        return levelQuestions.get(r);
    }

    public void setAttributesOfOptionButtons (List<Button> buttons, List<String> answerOptions) {
        for (int i=0; i<buttons.size(); i++) {
            Button currentButton = buttons.get(i);
            currentButton.setDisable(false);
            currentButton.setStyle("-fx-background-color: #00ffff");
            currentButton.setText(answerOptions.get(i));
        }
    }

    public void handleChooseAnswerOption1Action(ActionEvent actionEvent) {
        handleAnsweringQuestion(option1Button);
        List<Button> buttons = new ArrayList<>() {{
            add(option2Button);
            add(option3Button);
            add(option4Button);
        }};
        disableButtons(buttons);
        disableHelpOptionsButtons();
    }

    public void handleChooseAnswerOption2Action(ActionEvent actionEvent) {
        handleAnsweringQuestion(option2Button);
        List<Button> buttons = new ArrayList<>() {{
            add(option1Button);
            add(option3Button);
            add(option4Button);
        }};
        disableButtons(buttons);
        disableHelpOptionsButtons();
    }

    public void handleChooseAnswerOption3Action(ActionEvent actionEvent) {
        handleAnsweringQuestion(option3Button);
        List<Button> buttons = new ArrayList<>() {{
            add(option1Button);
            add(option2Button);
            add(option4Button);
        }};
        disableButtons(buttons);
        disableHelpOptionsButtons();
    }

    public void handleChooseAnswerOption4Action(ActionEvent actionEvent) {
        handleAnsweringQuestion(option4Button);
        List<Button> buttons = new ArrayList<>() {{
            add(option1Button);
            add(option2Button);
            add(option3Button);
        }};
        disableButtons(buttons);
        disableHelpOptionsButtons();
    }

    public void handleAnsweringQuestion(Button button) {
        if(button.getText().contains(" -- ") && button.getText().contains("%")) {
            String[] separatedText = button.getText().split(" -- ");
            button.setText(separatedText[0]);
        }
        String correctAnswerOptionText = getCorrectAnswerOptionText();
        if (button.getText().equals(correctAnswerOptionText)) {
            handleCorrectAnswer(button, currentQuestion);
        } else {
            handleIncorrectAnswer(button);
        }
    }

    public String getCorrectAnswerOptionText() {
        String correctAnswerOptionText = null;
        for (int i=0; i<currentQuestion.getAnswersOptions().length; i++) {
            if(currentQuestion.getAnswersOptions()[i].isCorrect()) {
                correctAnswerOptionText = currentQuestion.getAnswersOptions()[i].getAnswer();
            }
        }
        return correctAnswerOptionText;
    }

    public void handleCorrectAnswer(Button button, Question question) {
        button.setStyle("-fx-background-color: #32cd32");
        gameSession.setScore(gameSession.getScore() + question.getScore());
        if(!gameSession.isLastQuestion()) {
            result.setFill(Color.GREEN);
            result.setText("Congratulations! You have answered this question correctly. Your current score is " + gameSession.getScore() + " points.");
            nextButton.setDisable(false);
        } else {
            result.setFill(Color.DARKGOLDENROD);
            result.setText("Congratulations, " + gameSession.getUserName() + "! You have just become a millionaire! Your current score is " + gameSession.getScore() + " points.");
        }
    }

    public void handleIncorrectAnswer(Button button) {
        button.setStyle("-fx-background-color: #ff0000");
        result.setFill(Color.RED);
        result.setText("Unfortunately you have answered incorrectly. You may wish to try the game once again!");
        if(!gameSession.isLastQuestion()) {
            nextButton.setDisable(true);
        }
    }

    public void disableButtons(List<Button> buttons) {
        for (Button button : buttons) {
            button.setDisable(true);
        }
    }

    public void disableHelpOptionsButtons() {
        askAudienceButton.setDisable(true);
        phoneFriendButton.setDisable(true);
        fiftyFiftyButton.setDisable(true);
    }

    public void displayAskAudienceHelpOption(ActionEvent actionEvent) {
        String correctAnswerOptionText = getCorrectAnswerOptionText();
        Button correctOptionButton = null;
        List<Button> incorrectOptionButtons = new ArrayList<>();
        List<Button> buttons = getAllAnswerOptionButtons();
        for (Button button : buttons) {
            if(button.getText().equals(correctAnswerOptionText)) {
                correctOptionButton = button;
            } else {
                incorrectOptionButtons.add(button);
            }
        }
        Random rand = new Random();
        assert correctOptionButton != null;
        correctOptionButton.setText(correctOptionButton.getText() + " -- " + String.valueOf(31 + rand.nextInt(4)) + "%");
        for (int i=0; i<incorrectOptionButtons.size(); i++) {
            incorrectOptionButtons.get(i).setText(incorrectOptionButtons.get(i).getText() + " -- " + String.valueOf(18 + rand.nextInt(4)) + "%");
        }
        gameSession.setAskAudienceHelpOptionNotUsed(false);
        askAudienceButton.setDisable(true);
        phoneFriendButton.setDisable(true);
        fiftyFiftyButton.setDisable(true);
    }

    public void displayPhoneFriendHelpOption(ActionEvent actionEvent) {
        String correctAnswerOptionText = getCorrectAnswerOptionText();
        Button correctOptionButton = null;
        List<Button> buttons = getAllAnswerOptionButtons();
        for (Button button : buttons) {
            if(button.getText().equals(correctAnswerOptionText)) {
                correctOptionButton = button;
                break;
            }
        }
        correctOptionButton.setStyle("-fx-background-color: #00ff00");
        gameSession.setPhoneFriendHelpOptionNotUsed(false);
        phoneFriendButton.setDisable(true);
        askAudienceButton.setDisable(true);
        fiftyFiftyButton.setDisable(true);
    }

    public void displayFiftyFiftyHelpOption(ActionEvent actionEvent) {
        String correctAnswerOptionText = getCorrectAnswerOptionText();
        Button correctOptionButton = null;
        List<Button> incorrectOptionButtons = new ArrayList<>();
        List<Button> buttons = getAllAnswerOptionButtons();
        for (Button button : buttons) {
            if(button.getText().equals(correctAnswerOptionText)) {
                correctOptionButton = button;
            } else {
                incorrectOptionButtons.add(button);
            }
        }
        Random rand = new Random();
        Button randomIncorrectOptionButton = incorrectOptionButtons.get(rand.nextInt(incorrectOptionButtons.size()));
        correctOptionButton.setStyle("-fx-background-color: #00ff00");
        randomIncorrectOptionButton.setStyle("-fx-background-color: #00ff00");
        gameSession.setFiftyFiftyHelpOptionNotUsed(false);
        fiftyFiftyButton.setDisable(true);
        askAudienceButton.setDisable(true);
        phoneFriendButton.setDisable(true);
    }

    public void handleMoveToNextQuestionAction(ActionEvent actionEvent) {
        gameSession.setQuestionCounter(gameSession.getQuestionCounter() + 1);
        if (gameSession.getQuestionCounter() == gameSession.getQuestionsBank().numberOfLevels) {
            nextButton.setDisable(true);
            gameSession.setLastQuestion(true);
        }
        displayQuestionData(gameSession.getQuestionCounter());
    }
}