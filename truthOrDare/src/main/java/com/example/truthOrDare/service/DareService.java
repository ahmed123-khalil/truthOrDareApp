package com.example.truthOrDare.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.truthOrDare.dao.Dare;
import com.example.truthOrDare.repository.DareRepository;

@Service
public class DareService {

    @Autowired
    private DareRepository dareRepository;

    public String getDare() {
        List<Dare> dares = dareRepository.findByCompleted(false);
        
        // Check if the list is empty to avoid errors
        if (dares.isEmpty()) {
            return "No available dares at the moment!";
        }

        // Get a random dare from the list
        int position = new Random().nextInt(dares.size());
        Dare dare = dares.get(position);
        
        // Mark the dare as completed
        dare.setCompleted(true);
        dareRepository.save(dare);
        
        return dare.getName();
    }
    
    @Transactional
    public void resetAllDares() {
        dareRepository.updateAllDaresCompleted(false);
    }
}
