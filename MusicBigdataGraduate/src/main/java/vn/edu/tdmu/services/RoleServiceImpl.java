package vn.edu.tdmu.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.tdmu.exceptions.RoleNotFoundException;
import vn.edu.tdmu.models.Role;
import vn.edu.tdmu.repositories.RoleRepository;

import java.io.EOFException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by NguyenLinh on 2/1/2018.
 *
 */
@Service
public class RoleServiceImpl implements RoleService{
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    private final RoleRepository repository;

    @Autowired
    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Role findByType(String type) {
        LOGGER.info("Finding role entry by using type: {}", type);

        Role roleEntry = this.repository.findByType(type);
        LOGGER.info("Found role entry: {}", roleEntry);

        return roleEntry;
    }

    @Override
    @Transactional
    public Role update(Role update) {
        LOGGER.info("Updating the information of a role entry by using information: {}", update);

        Role updated = findRoleById(update.getId());
        updated.update(update.getType(),update.getDescription());

        this.repository.flush();

        LOGGER.info("Updated the information of the role entry: {}", updated);
        return updated;
    }

    private Role findRoleById(Integer id) {
        Optional<Role> roleResult = this.repository.findOne(id);
        return roleResult.orElseThrow(() -> new RoleNotFoundException(id));
    }

    @Override
    public boolean isTypeUnique(Integer id, String type) {
        return (this.repository.findByType(type) == null ||
                ((id != null) && (Objects.equals(this.repository.findByType(type).getId(), id))));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> findAll() {
        LOGGER.info("Finding all role entries.");

        List<Role> roleEntries = this.repository.findAll();
        LOGGER.info("Found {} role entries");

        return roleEntries;
    }

    @Override
    @Transactional(readOnly = true)
    public Role findById(Integer id) {

        LOGGER.info("Finding role entry by using id: {}", id);

        Role roleEntry = findRoleById(id);
        LOGGER.info("Found role entry: {}", roleEntry);

        return roleEntry;
    }

    @Override
    @Transactional
    public Role create(Role newEntry) {
        LOGGER.info("Creating a new role entry by using information: {}", newEntry);

        Role created = this.repository.save(newEntry);
        LOGGER.info("Created a new role entry: {}", created);

        return created;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        LOGGER.info("Deleting a role entry with id: {}", id);

        Role deleted = findRoleById(id);
        LOGGER.debug("Found role entry: {}", deleted);

        this.repository.delete(deleted);
        LOGGER.info("Deleted user entry: {}", deleted);
    }
}
