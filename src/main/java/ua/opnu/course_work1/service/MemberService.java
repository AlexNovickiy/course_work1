package ua.opnu.course_work1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.opnu.course_work1.model.Member;
import ua.opnu.course_work1.model.MembershipType;
import ua.opnu.course_work1.model.Trainer;
import ua.opnu.course_work1.repo.MemberRepository;
import ua.opnu.course_work1.repo.MembershipTypeRepository;
import ua.opnu.course_work1.repo.TrainerRepository;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MembershipTypeRepository membershipTypeRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    public Member addMember(Member member, Long membershipTypeId, Long trainerId) {
        // Проверка наличия membershipTypeId
        if (membershipTypeId == null) {
            throw new IllegalArgumentException("MembershipType ID must be provided");
        }

        // Проверка наличия trainerId
        if (trainerId == null) {
            throw new IllegalArgumentException("Trainer ID must be provided");
        }

        // Загрузка MembershipType из базы данных по ID
        MembershipType existingMembershipType = membershipTypeRepository.findById(membershipTypeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid MembershipType ID"));

        // Установка MembershipType для Member
        member.setMembershipType(existingMembershipType);

        // Загрузка тренера из базы данных по ID
        Trainer existingTrainer = trainerRepository.findById(trainerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Trainer ID"));

        // Установка тренера для Member
        member.setTrainer(existingTrainer);

        return memberRepository.save(member);
    }
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public List<Member> getMembersByMembershipType(Long membershipTypeId) {
        return memberRepository.findByMembershipTypeId(membershipTypeId);
    }

    public List<Member> getMembersByTrainerSpecialization(String specialization) {
        return memberRepository.findByTrainerSpecialization(specialization);
    }

    public List<Member> getMembersAboveAge(int age) {
        return memberRepository.findByAgeGreaterThan(age);
    }

    public List<Member> getTop3PopularMembershipTypes() {
        return memberRepository.findTop3ByOrderByMembershipTypeDesc();
    }

    public List<Member> getMembersByTrainer(Long trainerId) {
        return memberRepository.findByTrainerId(trainerId);
    }

    public List<Member> getMembersByMembershipTypeName(String membershipTypeName) {
        return memberRepository.findByMembershipTypeTypeName(membershipTypeName);
    }

}