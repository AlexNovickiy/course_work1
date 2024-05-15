package ua.opnu.springlab3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.opnu.springlab3.model.MembershipType;
import ua.opnu.springlab3.repo.MembershipTypeRepository;

import java.util.List;

@Service
public class MembershipTypeService {
    @Autowired
    private MembershipTypeRepository membershipTypeRepository;

    public MembershipType addMembershipType(MembershipType membershipType) {
        return membershipTypeRepository.save(membershipType);
    }

    public List<MembershipType> getAllMembershipTypes() {
        return membershipTypeRepository.findAll();
    }

    public List<MembershipType> getAllMembershipTypesWithMembers() {
        return membershipTypeRepository.findAllWithMembers();
    }
}
