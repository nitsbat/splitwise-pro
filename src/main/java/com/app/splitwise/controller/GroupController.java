package com.app.splitwise.controller;

import com.app.splitwise.dto.MemberRequestDTO;
import com.app.splitwise.service.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping(value = "/groups")
    public ResponseEntity<List<String>> getGroupNames() {
        return ResponseEntity.ok().body(groupService.getNames());
    }

    @PostMapping(value = "/groups")
    public ResponseEntity<String> createGroup(@RequestBody String groupName) {
        groupService.createGroup(groupName);
        return ResponseEntity.ok("OK");
    }


    @PutMapping(value = "/add-member/{groupName}")
    public ResponseEntity<String> addMembersInGroup(@PathVariable String groupName, @RequestBody MemberRequestDTO memberRequestDTO) {
        groupService.addMembersInGroup(groupName, memberRequestDTO);
        return ResponseEntity.ok("OK");
    }
}
