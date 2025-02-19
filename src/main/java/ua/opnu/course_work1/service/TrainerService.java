package ua.opnu.course_work1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.opnu.course_work1.model.Trainer;
import ua.opnu.course_work1.repo.TrainerRepository;

import java.util.List;

@Service
public class TrainerService {
    @Autowired
    private TrainerRepository trainerRepository;

    public Trainer addTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    public List<Trainer> getTrainersBySpecialization(String specialization) {
        return trainerRepository.findBySpecialization(specialization);
    }
}
