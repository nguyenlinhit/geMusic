package vn.edu.tdmu.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.tdmu.exceptions.ArtistNotFoundException;
import vn.edu.tdmu.models.Artist;
import vn.edu.tdmu.repositories.ArtistRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by nguye on 1/29/2018.
 *
 */
@Service
public class ArtistServiceImpl implements ArtistService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ArtistServiceImpl.class);

    private ArtistRepository repository;

    @Autowired
    ArtistServiceImpl(ArtistRepository repository){
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Artist> findByNamẹ(String name) {
        LOGGER.info("Fidning artist entry by using name: {}", name);

        List<Artist> artistEntries = this.repository.findByNameContainsIgnoreCase(name);
        LOGGER.info("Found artist entry: {}", artistEntries);
        return artistEntries;
    }

    @Override
    public Artist update(Artist update) {
        LOGGER.info("Updating the information of a artist entry by using information: {}", update);

        Artist updated = findArtistEntryById(update.getId());
        updated.update(update.getName(),update.getRealName(), update.getBirthDate(), update.getSex(),
                update.getCountry(), update.getCareer());
        this.repository.flush();
        LOGGER.info("Updated the information of the artist entry: {}", updated);

        return updated;
    }

    @Override
    public boolean isArtistNameUnique(Integer id, String name) {
        return (this.repository.findByName(name) == null ||
                (id != null) && Objects.equals(this.repository.findByName(name).getId(), id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Artist> findAll() {
        LOGGER.info("Find all artist entries");

        List<Artist> artistEntries = this.repository.findAll();
        LOGGER.info("Found {} artist entries", artistEntries.size());

        return artistEntries;
    }

    @Override
    @Transactional(readOnly =  true)
    public Artist findById(Integer id) {
        LOGGER.info("Fidning artist entry by using id: {}", id);

        Artist artistEntry = findArtistEntryById(id);
        LOGGER.info("Found artist etry: {}", artistEntry);
        return artistEntry;
    }

    private Artist findArtistEntryById(Integer id) {
        Optional<Artist> artistResult = this.repository.findOne(id);

        return artistResult.orElseThrow(() -> new ArtistNotFoundException(id));
    }

    @Override
    @Transactional
    public Artist create(Artist newEntry) {
        LOGGER.info("Creating a new artist entry by using information: {}", newEntry);

        Artist created = this.repository.save(newEntry);
        LOGGER.info("Created a new artist entry: {}", created);
        return created;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        LOGGER.info("Deleting a artist entry with id: {}", id);

        Artist deleted = findArtistEntryById(id);

        LOGGER.debug("Found artist entry: {}", deleted);
        this.repository.delete(deleted);
        LOGGER.info("Deleted user entry: {}");
    }
}
