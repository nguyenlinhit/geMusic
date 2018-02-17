package vn.edu.tdmu.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.tdmu.exceptions.UserNotFoundException;
import vn.edu.tdmu.models.Playlist;
import vn.edu.tdmu.models.User;
import vn.edu.tdmu.repositories.UserRepository;

/**
 * Created by NguyenLinh on 2/1/2018.
 *
 */
@Service
public class UserServiceImpl implements UserService{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private  UserRepository repository;
    @Autowired
    private  PasswordEncoder passwordEncoder;


    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        LOGGER.info("Finding all user entries.");

        List<User> userEntries = repository.findAll();
        LOGGER.info("Found {} user entries", userEntries.size());

        return userEntries;
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Integer id) {
        LOGGER.info("Finding user entry by using id: {}", id);

        User userEntry = findUserEntryById(id);
        if (userEntry != null) {
            Hibernate.initialize(userEntry.getRoles());
        }

        LOGGER.info("Found user entry: {}", userEntry);

        return userEntry;
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        LOGGER.info("Finding user entry by using username: {}", username);

        User userEntry = repository.findByUsername(username);
        if (userEntry != null) {
            Hibernate.initialize(userEntry.getRoles());
        }

        LOGGER.info("Found user entry: {}", userEntry);

        return userEntry;
    }

    @Override
    @Transactional
    public User create(User newEntry) {
        newEntry.setPassword(this.passwordEncoder.encode(newEntry.getPassword()));
        LOGGER.info("Creating a new user entry by using information: {}", newEntry);

        User created = repository.save(newEntry);
        LOGGER.info("Created a new user entry: {}", created);

        return created;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        LOGGER.info("Deleting a user entry with id: {}", id);

        User deleted = findUserEntryById(id);
        LOGGER.debug("Found user entry: {}", deleted);

        repository.delete(deleted);
        LOGGER.info("Deleted user entry: {}", deleted);
    }

    @Override
    @Transactional
    public User update(User updatedEntry) {
        LOGGER.info("Updating the information of a user entry by using information: {}", updatedEntry);

        User updated = findUserEntryById(updatedEntry.getId());
        updated.update(updatedEntry.getFullname(), updatedEntry.getEmail(), updatedEntry.getBirthDate(), updatedEntry.getPhoneNumber(),
                updatedEntry.getSex(), updatedEntry.getState(), updatedEntry.getRoles());

        //We need to flush the changes or otherwise the returned object
        //doesn't contain the updated audit information.
        repository.flush();

        LOGGER.info("Updated the information of the user entry: {}", updated);
        return updated;
    }

    @Override
    @Transactional
    public User updateAvatar(Integer id, String newImageUrl) {
        User updated = findUserEntryById(id);

        updated.setImageUrl(newImageUrl);
        repository.flush();

        return updated;
    }

    @Override
    @Transactional
    public User updatePassword(Integer id, String newPassword) {
        User updated = findUserEntryById(id);

        updated.setPassword(this.passwordEncoder.encode(newPassword));
        repository.flush();

        return updated;
    }

    @Override
    public boolean isUsernameUnique(Integer id, String username) {
        return (repository.findByUsername(username) == null || ((id != null) && (repository.findByUsername(username).getId() == id)));
    }

    @Override
    public boolean isEmailUnique(Integer id, String email) {
        return (repository.findByEmail(email) == null || ((id != null) && (repository.findByEmail(email).getId() == id)));
    }

    private User findUserEntryById(Integer id) {
        Optional<User> userResult = repository.findOne(id);
        return userResult.orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    @Transactional
    public boolean addPlaylistToUser(String username, Playlist playlist) {
        User user = findByUsername(username);

        //Check if playlist is created by theirself
        if (playlist.getCreateByUser().equals(username)) {
            return false;
        }

        boolean b = user.addPlaylist(playlist);
        repository.flush();

        return b;
    }
}
