package ua.opnu.springlab3.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.opnu.springlab3.model.MembershipType;

import java.util.List;

@Repository
public interface MembershipTypeRepository extends JpaRepository<MembershipType, Long> {
    @Query("SELECT DISTINCT m FROM MembershipType m LEFT JOIN FETCH m.members")
    List<MembershipType> findAllWithMembers();
}
