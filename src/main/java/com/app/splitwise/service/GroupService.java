package com.app.splitwise.service;

import com.app.splitwise.dto.MemberRequestDTO;
import com.app.splitwise.mapper.Mapper;
import com.app.splitwise.modal.Group;
import com.app.splitwise.modal.Member;
import com.app.splitwise.repository.GroupRepository;
import com.app.splitwise.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GroupService {

    private final GroupRepository groupRepository;
    private final MemberRepository memberRepository;

    private final Mapper<MemberRequestDTO, Member> mapper;


    public GroupService(GroupRepository groupRepository,
                        MemberRepository memberRepository,
                        Mapper<MemberRequestDTO, Member> mapper) {
        this.groupRepository = groupRepository;
        this.memberRepository = memberRepository;
        this.mapper = mapper;
    }

    public void createGroup(String groupName) {
        groupRepository.save(Group.builder().groupName(groupName).members(new ArrayList<>()).build());
    }

    public void addMembersInGroup(String groupName, MemberRequestDTO memberRequestDTO) {
        if (Objects.nonNull(memberRequestDTO)) {
            Optional<Group> optionalGroup = groupRepository.findByGroupName(groupName);
            optionalGroup.ifPresentOrElse(group -> {
                Optional<Member> memberOptional = memberRepository.findByUserName(memberRequestDTO.getUserName());
                Member member = mapper.map(memberRequestDTO);
                if (!memberOptional.isPresent()) {
                    log.error("Member Not found");
                    memberRepository.save(member);
                }
                group.getMembers().add(member);
                group.setGroupSize(group.getGroupSize() + 1);
                groupRepository.save(group);
            }, () -> log.error("Group {} doesn't exit", groupName));
        }
    }

    public List<String> getNames() {
        return groupRepository.findAll().stream().map(group -> group.getGroupName()).collect(Collectors.toList());
    }
}
