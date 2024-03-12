package com.elasticsearch.elasticsearch.service;

import com.elasticsearch.elasticsearch.entity.Groups;
import com.elasticsearch.elasticsearch.entity.Profile;
import com.elasticsearch.elasticsearch.entity.User;

import java.util.List;


public interface UserService {

    //find user by the user ID
    User findByUserId(Long userId);

    //create a new user
    User createUser(User user) throws Exception;

    //update a user
    User updateUser(User user);

    //delete a user
    void  deleteUserById(Long userId);

    //get All users
    List<User> getAllUser();

    // Add a profile to a user
    User addProfileToUser(Long userId, Profile profile);

    // Remove a profile from a user
    User removeProfileFromUser(Long userId);

     //Add a user to a group
    User addUserToGroup(Long userId, Long groupId);

     //Remove a user from a group
    User removeUserFromGroup(Long userId, Long groupId);

    // Add a group to a user as an admin
    User addGroupToUserAsAdmin(Long userId, Long groupId);

    List<User> getAllUsersOfAGroup(Groups group);












}


