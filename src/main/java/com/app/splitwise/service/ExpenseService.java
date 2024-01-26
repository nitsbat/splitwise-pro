package com.app.splitwise.service;

import com.app.splitwise.dto.ExpenseRequestBodyDTO;
import com.app.splitwise.modal.Group;
import com.app.splitwise.modal.Member;
import com.app.splitwise.repository.GroupRepository;
import com.app.splitwise.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ExpenseService {

    private final GroupRepository groupRepository;
    private final MemberRepository memberRepository;

    public ExpenseService(GroupRepository groupRepository,
                          MemberRepository memberRepository) {
        this.groupRepository = groupRepository;
        this.memberRepository = memberRepository;
    }

    public void addMoney(String groupName, ExpenseRequestBodyDTO expenseRequestBodyDTO) {
        Double money = Double.valueOf(expenseRequestBodyDTO.getMoney());
        String username = expenseRequestBodyDTO.getUsername();
        if (money == 0) {
            log.info("User : {} added expense of 0 rupees", username);
            return;
        }
        Optional<Group> optionalGroup = groupRepository.findByGroupName(groupName);
        if (optionalGroup.isPresent()) {
            Group group = optionalGroup.get();
            validateMemberPresence(group, username);
            List<Member> members = group.getMembers();
            double share = money / members.size();
            for (Member member : members) {
                if (member.getUserName().equals(username)) {
                    double paid = member.getPaid();
                    member.setPaid(paid + share);
                } else {
                    double owed = member.getOwed();
                    member.setOwed(owed + share);
                }
            }
            memberRepository.saveAll(members);
        } else {
            throw new RuntimeException("Group not found. Groupname : " + groupName);
        }
    }

    private void validateMemberPresence(Group group, String username) {
        Optional<Member> memberOptional = group.getMembers().stream().filter(member -> member.getUserName().equals(username)).findAny();
        if (memberOptional.isEmpty()) {
            log.error("Member : {} not present in group : {}", username, group.getGroupName());
            throw new RuntimeException("Member Not present");
        }
    }
}
