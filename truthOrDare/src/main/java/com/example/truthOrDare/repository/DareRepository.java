package com.example.truthOrDare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.truthOrDare.dao.Dare;

@Repository
public interface DareRepository extends JpaRepository<Dare, Long>{
	@Query("SELECT d FROM Dare d WHERE d.completed = :completed")
	List<Dare> findByCompleted(@Param("completed") boolean completed);
	
	@Modifying
    @Transactional
	@Query("UPDATE Dare d SET d.completed = :completed")
	void updateAllDaresCompleted(@Param("completed") boolean completed);
}
