package com.example.becomemillionairegame.questionsdata;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class QuestionsBank {
    public List<Question> allQuestions;
    public int numberOfLevels;
    public Map<Integer, List<Question>> mapForSpecificLevelsQuestions;

    public QuestionsBank() {
        QuestionsCollector questionsCollector = new QuestionsCollector();
        try {
            this.allQuestions = questionsCollector.collectQuestions();
            this.numberOfLevels = questionsCollector.findNumberOfLevels();
            this.mapForSpecificLevelsQuestions = questionsCollector.getMapForSpecificLevelsQuestions();
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

    public Map<Integer, List<Question>> getMapForSpecificLevelsQuestions() {
        return mapForSpecificLevelsQuestions;
    }
}
