package com.example.becomemillionairegame.questions_data;

import com.example.becomemillionairegame.questions_data.Question;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class QuestionsCollector {
    public List<Question> collectQuestions() throws IOException {
        List<Question> list = new ArrayList<Question>();
        String file = "src/main/resources/questions/questions.json";
        Scanner scanner = new Scanner(new File(file));
        ObjectMapper mapper = new ObjectMapper();
        List<Question> questionsList = new ArrayList<Question>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            try {
                questionsList = Arrays.asList(mapper.readValue(line, Question[].class));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        scanner.close();
        return questionsList;
    }

    public int findNumberOfLevels() throws IOException {
        int max = collectQuestions().get(0).getLevel();
        for (int i = 1; i < collectQuestions().size(); i ++) {
            if (collectQuestions().get(i).getLevel() > max) {
                max = collectQuestions().get(i).getLevel();
            }
        }
        return max;
    }
}
