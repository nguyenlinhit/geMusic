package vn.edu.tdmu.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.tdmu.exceptions.LyricNotFoundException;
import vn.edu.tdmu.models.Lyric;
import vn.edu.tdmu.repositories.LyricRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by NguyenLinh on 1/29/2018.
 *
 */
@Service
public class LyricServiceImpl implements LyricService{

    private static final Logger LOGGER = LoggerFactory.getLogger(LyricServiceImpl.class);

    private final LyricRepository repository;

    @Autowired
    public LyricServiceImpl(LyricRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Lyric update(Lyric update) {
        LOGGER.info("Updating the information of a lyric entry by using information: {}", update);

        Lyric updated = findLyricEntryById(update.getId());
        updated.update(update.getTitle(), update.getBody());
        repository.flush();

        LOGGER.info("Updated the information of the lyric entry: {}", updated);
        return updated;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Lyric> findAll() {
        LOGGER.info("Finding all lyric entries.");

        List<Lyric> lyricEntries = this.repository.findAll();
        LOGGER.info("Found {} lyric entries", lyricEntries.size());
        return lyricEntries;
    }

    @Override
    @Transactional(readOnly = true)
    public Lyric findById(Integer id) {
        LOGGER.info("Finding lyric entry by using id: {}", id);

        Lyric lyricEntry = findLyricEntryById(id);
        LOGGER.info("Found lyric entry: {}", lyricEntry);

        return lyricEntry;
    }

    @Override
    @Transactional
    public Lyric create(Lyric newEntry) {
        LOGGER.info("Creating a new lyric entry by using information: {}", newEntry);

        Lyric created = this.repository.save(newEntry);
        LOGGER.info("Created a new lyric entry: {}", created);
        return created;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        LOGGER.info("Deleting a lyric entry with id: {}", id);

        Lyric deleted = findLyricEntryById(id);
        LOGGER.debug("Found lyric entry: {}", deleted);

        repository.delete(deleted);
        LOGGER.info("Deleted user entry: {}");
    }

    private Lyric findLyricEntryById(Integer id){
        Optional<Lyric> lyricResult = this.repository.findOne(id);
        return lyricResult.orElseThrow(() -> new LyricNotFoundException(id));
    }
}
