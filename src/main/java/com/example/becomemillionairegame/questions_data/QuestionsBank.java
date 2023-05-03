package com.example.becomemillionairegame.questions_data;

import java.io.IOException;
import java.util.List;

public class QuestionsBank {
    public List<Question> allQuestions;
    public int numberOfLevels;

    public QuestionsBank() {
        QuestionsCollector questionsCollector = new QuestionsCollector();
        try {
            this.allQuestions = questionsCollector.collectQuestions();
            this.numberOfLevels = questionsCollector.findNumberOfLevels();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public List<Question> getAllQuestions() {
        return allQuestions;
    }

    public int getNumberOfLevels() {
        return numberOfLevels;
    }
}
