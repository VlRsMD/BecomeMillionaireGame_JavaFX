package com.example.becomemillionairegame;

import com.example.becomemillionairegame.questionsdata.QuestionsBank;

public class GameSession {
    private String userName;
    private QuestionsBank questionsBank;
    private int questionCounter;
    private boolean lastQuestion;
    private int score;
    private boolean askAudienceHelpOptionNotUsed;
    private boolean phoneFriendHelpOptionNotUsed;
    private boolean fiftyFiftyHelpOptionNotUsed;

    public GameSession() {
        this.questionsBank = new QuestionsBank();
        this.questionCounter = 1;
        this.lastQuestion = false;
        this.score = 0;
        this.askAudienceHelpOptionNotUsed = true;
        this.phoneFriendHelpOptionNotUsed = true;
        this.fiftyFiftyHelpOptionNotUsed = true;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public QuestionsBank getQuestionsBank() {
        return questionsBank;
    }

    public void setQuestionsBank(QuestionsBank questionsBank) {
        this.questionsBank = questionsBank;
    }

    public int getQuestionCounter() {
        return questionCounter;
    }

    public void setQuestionCounter(int questionCounter) {
        this.questionCounter = questionCounter;
    }

    public boolean isLastQuestion() {
        return lastQuestion;
    }

    public void setLastQuestion(boolean lastQuestion) {
        this.lastQuestion = lastQuestion;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isAskAudienceHelpOptionNotUsed() {
        return askAudienceHelpOptionNotUsed;
    }

    public void setAskAudienceHelpOptionNotUsed(boolean askAudienceHelpOptionNotUsed) {
        this.askAudienceHelpOptionNotUsed = askAudienceHelpOptionNotUsed;
    }

    public boolean isPhoneFriendHelpOptionNotUsed() {
        return phoneFriendHelpOptionNotUsed;
    }

    public void setPhoneFriendHelpOptionNotUsed(boolean phoneFriendHelpOptionNotUsed) {
        this.phoneFriendHelpOptionNotUsed = phoneFriendHelpOptionNotUsed;
    }

    public boolean isFiftyFiftyHelpOptionNotUsed() {
        return fiftyFiftyHelpOptionNotUsed;
    }

    public void setFiftyFiftyHelpOptionNotUsed(boolean fiftyFiftyHelpOptionNotUsed) {
        this.fiftyFiftyHelpOptionNotUsed = fiftyFiftyHelpOptionNotUsed;
    }
}
