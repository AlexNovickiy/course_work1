package ua.opnu.course_work1.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.opnu.course_work1.model.Trainer;
import ua.opnu.course_work1.service.TrainerService;

import java.util.List;

@RestController
@RequestMapping("/trainers")
public class TrainerController {
    @Autowired
    private TrainerService trainerService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Trainer> addTrainer(@RequestBody Trainer trainer) {
        System.out.println("Received request to add trainer: " + trainer);
        Trainer addedTrainer = trainerService.addTrainer(trainer);
        System.out.println("Trainer added: " + addedTrainer);
        return new ResponseEntity<>(addedTrainer, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Trainer>> getAllTrainers() {
        List<Trainer> trainers = trainerService.getAllTrainers();
        return new ResponseEntity<>(trainers, HttpStatus.OK);
    }

    @GetMapping("/by-specialization/{specialization}")
    public ResponseEntity<List<Trainer>> getTrainersBySpecialization(@PathVariable String specialization) {
        List<Trainer> trainers = trainerService.getTrainersBySpecialization(specialization);
        return new ResponseEntity<>(trainers, HttpStatus.OK);
    }
}
