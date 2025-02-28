package com.example.truthOrDare.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.truthOrDare.dao.Question;
import com.example.truthOrDare.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
	
	public String getQuestion() {
		List<Question> questions = questionRepository.findByCompleted(false);
        // Check if the list is empty to avoid errors
        if (questions.isEmpty()) {
            return "No available questions at the moment!";
        }

        // Get a random dare from the list
        int position = new Random().nextInt(questions.size());
        Question question = questions.get(position);
        
        // Mark the dare as completed
        question.setCompleted(true);
        questionRepository.save(question);
        
        return question.getQuestion();
	}
	
	@Transactional
	public void resetAllQuestions() {
        questionRepository.updateAllQuestionsCompleted(false);
    }
}
