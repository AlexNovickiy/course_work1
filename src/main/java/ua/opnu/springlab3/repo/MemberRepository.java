package ua.opnu.springlab3.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.opnu.springlab3.model.Member;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByMembershipTypeId(Long membershipTypeId);
    List<Member> findByTrainerSpecialization(String specialization);
    List<Member> findByAgeGreaterThan(int age);
    List<Member> findTop3ByOrderByMembershipTypeDesc();
    List<Member> findByTrainerId(Long trainerId);
    @Query("SELECT m FROM Member m LEFT JOIN FETCH m.trainer WHERE m.membershipType.typeName = :membershipTypeName")
    List<Member> findByMembershipTypeTypeName(@Param("membershipTypeName") String membershipTypeName);

}
