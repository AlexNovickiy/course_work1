package ua.opnu.course_work1.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.opnu.course_work1.model.MembershipType;
import ua.opnu.course_work1.service.MembershipTypeService;

import java.util.List;

@RestController
@RequestMapping("/membership-types")
public class MembershipTypeController {
    @Autowired
    private MembershipTypeService membershipTypeService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<MembershipType> addMembershipType(@RequestBody MembershipType membershipType) {
        MembershipType addedMembershipType = membershipTypeService.addMembershipType(membershipType);
        return new ResponseEntity<>(addedMembershipType, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MembershipType>> getAllMembershipTypes() {
        List<MembershipType> membershipTypes = membershipTypeService.getAllMembershipTypes();
        return new ResponseEntity<>(membershipTypes, HttpStatus.OK);
    }

    @GetMapping("/with-members")
    public ResponseEntity<List<MembershipType>> getAllMembershipTypesWithMembers() {
        List<MembershipType> membershipTypes = membershipTypeService.getAllMembershipTypesWithMembers();
        return new ResponseEntity<>(membershipTypes, HttpStatus.OK);
    }
}
