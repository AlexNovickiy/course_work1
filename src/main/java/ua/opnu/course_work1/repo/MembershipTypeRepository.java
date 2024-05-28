package ua.opnu.course_work1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.opnu.course_work1.model.MembershipType;

import java.util.List;

@Repository
public interface MembershipTypeRepository extends JpaRepository<MembershipType, Long> {
    @Query("SELECT DISTINCT m FROM MembershipType m LEFT JOIN FETCH m.members")
    List<MembershipType> findAllWithMembers();
}
