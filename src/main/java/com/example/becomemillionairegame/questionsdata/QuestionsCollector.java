package com.example.becomemillionairegame.questionsdata;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

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

    public Map<Integer, List<Question>> getMapForSpecificLevelsQuestions() throws IOException {
        Map<Integer, List<Question>> specificLevelsQuestionsMap = new HashMap<>();
        for(int i=0; i<findNumberOfLevels(); i++) {
            int currentLevel = i+1;
            List<Question> currentLevelQuestions = new ArrayList<>();
            for (int j=0; j<collectQuestions().size(); j++) {
                if(collectQuestions().get(j).getLevel() == currentLevel) {
                    currentLevelQuestions.add(collectQuestions().get(j));
                }
            }
            specificLevelsQuestionsMap.put(currentLevel, currentLevelQuestions);
        }
        return specificLevelsQuestionsMap;
    }
}
