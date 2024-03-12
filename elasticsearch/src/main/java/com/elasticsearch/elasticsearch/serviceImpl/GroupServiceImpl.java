package com.elasticsearch.elasticsearch.serviceImpl;

import com.elasticsearch.elasticsearch.entity.Groups;
import com.elasticsearch.elasticsearch.entity.User;
import com.elasticsearch.elasticsearch.repository.GroupsRepository;
import com.elasticsearch.elasticsearch.repository.UserRepository;
import com.elasticsearch.elasticsearch.service.GroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupsService {

    @Autowired
    private GroupsRepository groupsRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Groups findGroupById(Long groupId) {
        return groupsRepository.findById(groupId).orElse(null);
    }

    @Override
    public Groups createGroup(Groups group) {
        return groupsRepository.save(group);
    }

    @Override
    public User getAdminOfGroup(Long groupId) {
        Groups group = groupsRepository.findById(groupId).orElse(null);
        return group != null ? group.getAdmin() : null;
    }

    @Override
    public Groups updateGroup(Groups group) {
        return groupsRepository.save(group);
    }

    @Override
    public void deleteGroupById(Long groupId) {
        groupsRepository.findById(groupId).ifPresent(group -> groupsRepository.delete(group));


    }

    @Override
    public Groups assignUserToGroup(Long groupId, Long userId) {
        List<User> users = null;
        Groups group = groupsRepository.findById(groupId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        assert group != null;
        users = group.getMembers();
        users.add(user);
        group.setMembers(users);
        return groupsRepository.save(group);
    }

    @Override
    public List<User> removeUserFromGroup(Long groupId, Long userId) {
        List<User> users = null;
        Groups group = groupsRepository.findById(groupId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        assert group != null;
        users = group.getMembers();
        users.remove(user);
        group.setMembers(users);
        groupsRepository.save(group);
        return userRepository.findByGroups(group);
    }

    @Override
    public List<Groups> getAllGroupsOfAUser(User user) {
        return groupsRepository.findByMembers(user);
    }

    @Override
    public Groups makeAUserAdmin(Long groupId, Long userId) {
        Groups group = groupsRepository.findById(groupId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        assert group != null;
        group.setAdmin(user);
        groupsRepository.save(group);

        return group;
    }

    @Override
    public List<Groups> getGroupsByAdmin(User admin) {
        return groupsRepository.findByAdmin(admin);
    }
}
