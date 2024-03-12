package com.elasticsearch.elasticsearch.controller;

import com.elasticsearch.elasticsearch.entity.Groups;
import com.elasticsearch.elasticsearch.entity.User;
import com.elasticsearch.elasticsearch.service.GroupsService;
import com.elasticsearch.elasticsearch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupsController {

    @Autowired
    private GroupsService groupsService;
    @Autowired
    private UserService userService;

    @GetMapping("/{groupId}")
    public ResponseEntity<Groups> getGroupById(@PathVariable Long groupId) {
        Groups group = groupsService.findGroupById(groupId);
        return ResponseEntity.ok(group);
    }

    @PostMapping("/create")
    public ResponseEntity<Groups> createGroup(@RequestBody Groups group) {
        Groups createdGroup = groupsService.createGroup(group);
        return ResponseEntity.ok(createdGroup);
    }

    @PutMapping("/update")
    public ResponseEntity<Groups> updateGroup(@RequestBody Groups group) {
        Groups updatedGroup = groupsService.updateGroup(group);
        return ResponseEntity.ok(updatedGroup);
    }

    @DeleteMapping("/delete/{groupId}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long groupId) {
        groupsService.deleteGroupById(groupId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/admin/group/{groupId}")
    public ResponseEntity<User> getAdminOfGroup(@PathVariable Long groupId) {
        User admin = groupsService.getAdminOfGroup(groupId);
        return ResponseEntity.ok(admin);
    }

    @PutMapping("/{groupId}/addUser/{userId}")
    public Groups assignUserToGroup(@PathVariable Long groupId, @PathVariable Long userId){
        return groupsService.assignUserToGroup(groupId, userId);
    }

    @PutMapping("/{groupId}/removeUser/{userId}")
    public List<User> removeUserFromGroup(@PathVariable Long groupId, @PathVariable Long userId){
        return groupsService.removeUserFromGroup(groupId, userId);
    }

    @PutMapping("/{groupId}/makeUserAsAdmin/{userId}")
    public Groups MakeUserGroupAdmin(@PathVariable Long groupId, @PathVariable Long userId){
        return groupsService.makeAUserAdmin(groupId,userId);
    }

    @GetMapping("/user/{userId}")
    public List<Groups> getAllGroupsOfAUser(@PathVariable("userId") Long tempUserId){
        User user = userService.findByUserId(tempUserId);
        return this.groupsService.getAllGroupsOfAUser(user);
    }

    //find groups by admin
    @GetMapping("/admin/{adminId}")
    public ResponseEntity<List<Groups>> getGroupsByAdmin(@PathVariable Long adminId) {
        User admin = userService.findByUserId(adminId);
        if (admin != null) {
            List<Groups> groups = groupsService.getGroupsByAdmin(admin);
            return ResponseEntity.ok(groups);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
