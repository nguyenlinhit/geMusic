package vn.edu.tdmu.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.tdmu.exceptions.GenreNotFoundException;
import vn.edu.tdmu.models.Genre;
import vn.edu.tdmu.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by NguyenLinh on 1/29/2018.
 *
 */
@Service
public class GenreServiceImpl implements GenreService{

    private static final Logger LOGGER =  LoggerFactory.getLogger(GenreServiceImpl.class);

    private final GenreRepository repository;

    @Autowired
    public GenreServiceImpl(GenreRepository repository) {
        this.repository = repository;
    }

    @Override
    public Genre update(Genre update) {
        LOGGER.info("Updating the information of a genre entry by using information: {}", update);

        Genre updated = findGenreEntryById(update.getId());
        updated.update(update.getName(),update.getDescription());
        this.repository.flush();

        LOGGER.info("Updated the information of the genre entry: {}", updated);
        return updated;
    }

    @Override
    public boolean isGenreNameUnique(Integer id, String name) {
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> findAll() {
        LOGGER.info("Finding all genre entries.");

        List<Genre> genreEntries = this.repository.findAll();
        LOGGER.info("Found {} genre entries", genreEntries.size());
        return genreEntries;
    }

    @Override
    @Transactional(readOnly = true)
    public Genre findById(Integer id) {
        LOGGER.info("Finding genre entry by using id: {}", id);

        Genre genreEntry = findGenreEntryById(id);
        LOGGER.info("Found genre entry: {}", genreEntry);
        return genreEntry;
    }

    @Override
    @Transactional
    public Genre create(Genre newEntry) {
        LOGGER.info("Creating a new genre entry by using information: {}", newEntry);

        Genre created = this.repository.save(newEntry);
        LOGGER.info("Created a new genre entry: {}", created);

        return created;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        LOGGER.info("Deleting a genre entry with id: {}", id);

        Genre deleted = findGenreEntryById(id);
        LOGGER.debug("Found genre entry: {}", deleted);

        this.repository.delete(deleted);
        LOGGER.info("Deleted user entry: {}");

    }

    private Genre findGenreEntryById(Integer id){
        Optional<Genre> genreResult = repository.findOne(id);
        return genreResult.orElseThrow(() -> new GenreNotFoundException(id));
    }
}
