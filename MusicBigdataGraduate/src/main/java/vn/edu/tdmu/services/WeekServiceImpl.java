package vn.edu.tdmu.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.tdmu.exceptions.WeekNotFoundException;
import vn.edu.tdmu.models.Week;
import vn.edu.tdmu.repositories.WeekRepository;

/**
 * Created by NguyenLinh on 2/1/2018.
 *
 */
@Service
public class WeekServiceImpl implements WeekService{
    private static final Logger LOGGER = LoggerFactory.getLogger(WeekServiceImpl.class);

    private final WeekRepository repository;

    @Autowired
    WeekServiceImpl(WeekRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Week> findAll() {
        LOGGER.info("Finding all week entries.");

        List<Week> weekEntries = repository.findAll();
        LOGGER.info("Found {} week entries", weekEntries.size());

        return weekEntries;
    }

    @Override
    @Transactional(readOnly = true)
    public Week findById(Integer id) {
        LOGGER.info("Finding week entry by using id: {}", id);

        Week weekEntry = findWeekEntryById(id);
        LOGGER.info("Found week entry: {}", weekEntry);

        return weekEntry;
    }

    @Override
    @Transactional
    public Week create(Week newEntry) {
        LOGGER.info("Creating a new week entry by using information: {}", newEntry);

        Week created = repository.save(newEntry);
        LOGGER.info("Created a new week entry: {}", created);

        return created;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        LOGGER.info("Deleting a week entry with id: {}", id);

        Week deleted = findWeekEntryById(id);
        LOGGER.debug("Found week entry: {}", deleted);

        repository.delete(deleted);
        LOGGER.info("Deleted user entry: {}");
    }

    @Override
    @Transactional
    public Week update(Week updatedEntry) {
        LOGGER.info("Updating the information of a week entry by using information: {}", updatedEntry);

        Week updated = findWeekEntryById(updatedEntry.getId());
        updated.update(updatedEntry.getNo(), updatedEntry.getName(), updatedEntry.getStartDate(), updatedEntry.getEndDate(),
                updatedEntry.getDescription());
        repository.flush();

        LOGGER.info("Updated the information of the week entry: {}", updated);
        return updated;
    }

    private Week findWeekEntryById(Integer id) {
        Optional<Week> weekResult = repository.findOne(id);
        return weekResult.orElseThrow(() -> new WeekNotFoundException(id));
    }

}
