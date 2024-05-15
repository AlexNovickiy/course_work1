package ua.opnu.springlab3.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.opnu.springlab3.model.Trainer;

import java.util.List;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    List<Trainer> findBySpecialization(String specialization);
}
