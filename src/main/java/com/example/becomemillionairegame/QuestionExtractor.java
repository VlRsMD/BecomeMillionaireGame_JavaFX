package com.example.becomemillionairegame;

import com.example.becomemillionairegame.questions_data.Question;
import com.example.becomemillionairegame.questions_data.QuestionsBank;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionExtractor {
    public Question getSpecificLevelQuestion(int level) {
        QuestionsBank questionsBank = new QuestionsBank();
        List<Question> questionsList = questionsBank.getAllQuestions();
        Random rand = new Random();
        int r = rand.nextInt(3);
        List<Question> levelQuestions = new ArrayList<Question>();
        for (Question question : questionsList) {
            if (question.getLevel() == level) {
                levelQuestions.add(question);
            }
        }
        Question currentQuestion = levelQuestions.get(r);
        return currentQuestion;
    }
}
