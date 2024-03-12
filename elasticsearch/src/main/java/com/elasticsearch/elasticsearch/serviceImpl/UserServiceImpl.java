    package com.elasticsearch.elasticsearch.serviceImpl;


    import com.elasticsearch.elasticsearch.elasticsearch.ElasticsearchIndexer;
    import com.elasticsearch.elasticsearch.entity.Groups;
    import com.elasticsearch.elasticsearch.entity.Profile;
    import com.elasticsearch.elasticsearch.entity.User;
    import com.elasticsearch.elasticsearch.repository.GroupsRepository;
    import com.elasticsearch.elasticsearch.repository.UserRepository;
    import com.elasticsearch.elasticsearch.service.ProfileService;
    import com.elasticsearch.elasticsearch.service.UserService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;
    import java.util.List;

    @Service
    public class UserServiceImpl implements UserService {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private GroupsRepository groupsRepository;

        @Autowired
        private ProfileService profileService;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        private ElasticsearchOperations elasticsearchOperations ;

        @Autowired
        private ProfileServiceImpl profileServiceImpl;
        @Autowired
       private ElasticsearchIndexer elasticsearchIndexer;


        @Override
        public User findByUserId(Long userId) {
            return userRepository.findById(userId).orElse(null);
        }

        @Override
        public User createUser(User user) throws Exception {
            User local = this.userRepository.findByEmail(user.getEmail());
            if(local!=null)
            {
                System.out.println("User already Exist");
                throw new Exception("User already Exist");
            }else {

                user.setPassword(passwordEncoder.encode(user.getPassword()));
                local = this.userRepository.save(user);
                elasticsearchIndexer.indexUser(local);
            }
            return local;

        }

        @Override
        public User updateUser(User user) {

            return userRepository.save(user);

        }

        @Override
        public void deleteUserById(Long userId) {
            User user = userRepository.findById(userId).orElse(null);

            if (user != null) {
                // Remove the user from associated groups
                if (user.getGroups() != null) {
                    user.getGroups().forEach(group -> group.getMembers().remove(user));
                }
                if (user.getProfile() != null) {
                    profileService.deleteProfile(user.getProfile().getProfileId());
                }

                // Delete the user
                userRepository.delete(user);

            }

        }

        @Override
        public List<User> getAllUser() {
            return userRepository.findAll();
        }

        @Override
        public User addProfileToUser(Long userId, Profile profile) {
            User user = userRepository.findById(userId).orElse(null);
            if(user!=null){

                user.setProfile(profile);
                userRepository.save(user);
            }
            return user;
        }

        @Override
        public User removeProfileFromUser(Long userId) {
            User user = userRepository.findById(userId).orElse(null);
            if (user != null) {
                user.setProfile(null);
                userRepository.save(user);
            }


            return user;
        }

        @Override
        public User addUserToGroup(Long userId, Long groupId) {
            return null;
        }

        @Override
        public User removeUserFromGroup(Long userId, Long groupId) {
            return null;
        }

        @Override
        public User addGroupToUserAsAdmin(Long userId, Long groupId) {
            User user = userRepository.findById(userId).orElse(null);
            Groups group = groupsRepository.findById(groupId).orElse(null);

            if (user != null && group != null) {
                group.setAdmin(user);
                user.getGroupsAdmin().add(group);
                userRepository.save(user);
            }

            return user;
        }

        @Override
        public List<User> getAllUsersOfAGroup(Groups group) {
            return userRepository.findByGroups(group);
        }

    }
