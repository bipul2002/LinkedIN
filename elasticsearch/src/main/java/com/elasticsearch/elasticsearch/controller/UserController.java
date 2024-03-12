package com.elasticsearch.elasticsearch.controller;

import com.elasticsearch.elasticsearch.entity.Groups;
import com.elasticsearch.elasticsearch.entity.Profile;
import com.elasticsearch.elasticsearch.entity.User;
import com.elasticsearch.elasticsearch.service.GroupsService;
import com.elasticsearch.elasticsearch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private GroupsService groupsService;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userService.findByUserId(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) throws Exception {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

        @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
            try {
                userService.deleteUserById(userId);
                return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error deleting user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }


    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/add-profile/{userId}")
    public ResponseEntity<User> addProfileToUser(@PathVariable Long userId, @RequestBody Profile profile) {
        User user = userService.addProfileToUser(userId, profile);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/remove-profile/{userId}")
    public ResponseEntity<User> removeProfileFromUser(@PathVariable Long userId) {
        User user = userService.removeProfileFromUser(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/add-group-as-admin/{userId}/{groupId}")
    public ResponseEntity<User> addGroupToUserAsAdmin(@PathVariable Long userId, @PathVariable Long groupId) {
        User user = userService.addGroupToUserAsAdmin(userId, groupId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/group/{groupId}")
    List<User> getAllUsersOfAGroup(@PathVariable("groupId") Long groupId){
       Groups tempGroup = new Groups();
       tempGroup.setGroupId(groupId);

        Groups group = groupsService.findGroupById(groupId);
        return userService.getAllUsersOfAGroup(group);
    }

    //current user


    @GetMapping("/current-user")
    public  User getLoggedInUser(Principal principal){
        return ((User)this.userDetailsService.loadUserByUsername(principal.getName()));
    }
}
