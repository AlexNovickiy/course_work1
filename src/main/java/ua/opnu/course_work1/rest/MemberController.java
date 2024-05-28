package ua.opnu.course_work1.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.opnu.course_work1.model.Member;
import ua.opnu.course_work1.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Member> addMember(@RequestBody Member member, @RequestParam Long membershipTypeId, @RequestParam Long trainerId) {
        Member addedMember = memberService.addMember(member, membershipTypeId, trainerId);
        return new ResponseEntity<>(addedMember, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/by-membership-type/{membershipTypeName}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Member>> getMembersByMembershipType(@PathVariable String membershipTypeName) {
        List<Member> members = memberService.getMembersByMembershipTypeName(membershipTypeName);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/by-trainer-specialization/{specialization}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Member>> getMembersByTrainerSpecialization(@PathVariable String specialization) {
        List<Member> members = memberService.getMembersByTrainerSpecialization(specialization);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/above-age/{age}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Member>> getMembersAboveAge(@PathVariable int age) {
        List<Member> members = memberService.getMembersAboveAge(age);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/top3-popular")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Member>> getTop3PopularMembershipTypes() {
        List<Member> members = memberService.getTop3PopularMembershipTypes();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/by-trainer/{trainerId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Member>> getMembersByTrainer(@PathVariable Long trainerId) {
        List<Member> members = memberService.getMembersByTrainer(trainerId);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }
}
