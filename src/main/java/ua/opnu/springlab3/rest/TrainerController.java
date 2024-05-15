package ua.opnu.springlab3.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.opnu.springlab3.model.Trainer;
import ua.opnu.springlab3.service.TrainerService;

import java.util.List;

@RestController
@RequestMapping("/trainers")
public class TrainerController {
    @Autowired
    private TrainerService trainerService;

    @PostMapping
    public ResponseEntity<Trainer> addTrainer(@RequestBody Trainer trainer) {
        Trainer addedTrainer = trainerService.addTrainer(trainer);
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

