package com.elasticsearch.elasticsearch.service;

import com.elasticsearch.elasticsearch.entity.Groups;
import com.elasticsearch.elasticsearch.entity.User;

import java.util.List;


public interface GroupsService {

    // Find group by group ID
    Groups findGroupById(Long groupId);

    // Create a new group
    Groups createGroup(Groups group);

    // Get the admin of a group
    User getAdminOfGroup(Long groupId);

    // Update a group
    Groups updateGroup(Groups group);

    // Delete a group by group ID
    void deleteGroupById(Long groupId);

     Groups assignUserToGroup(Long groupId, Long userId);

     List<User> removeUserFromGroup(Long groupId, Long userId);

     List<Groups> getAllGroupsOfAUser(User user);

     Groups makeAUserAdmin(Long groupId, Long userId);

    List<Groups> getGroupsByAdmin(User admin);



}
