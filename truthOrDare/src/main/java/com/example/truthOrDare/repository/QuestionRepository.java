package com.example.truthOrDare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.truthOrDare.dao.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{
	@Query("SELECT q FROM Question q WHERE q.completed = :completed")
	List<Question> findByCompleted(@Param("completed") boolean completed);
	
	@Modifying
    @Transactional
	@Query("UPDATE Question q SET q.completed = :completed")
	void updateAllQuestionsCompleted(@Param("completed") boolean completed);
}
