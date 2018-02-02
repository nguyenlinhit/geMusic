package vn.edu.tdmu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.tdmu.enums.PlaylistType;
import vn.edu.tdmu.exceptions.SongNotFoundException;
import vn.edu.tdmu.models.Artist;
import vn.edu.tdmu.models.Song;
import vn.edu.tdmu.repositories.SongReposity;

import java.util.List;
import java.util.Optional;

/**
 * Created by NguyenLinh on 2/1/2018.
 *
 */
@Service
public class SongServiceImpl implements SongService{
    private static final Logger LOGGER = LoggerFactory.getLogger(SongServiceImpl.class);

    private static final int PAGE_SIZE = 35;

    private final SongReposity repository;

    @Autowired
    SongServiceImpl(SongReposity repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Song> findAll() {
        LOGGER.info("Finding all song entries.");

        List<Song> songEntries = repository.findAll();
        for (Song song : songEntries) {
            Hibernate.initialize(song.getArtists());
        }
        LOGGER.info("Found {} song entries", songEntries.size());

        return songEntries;
    }

    @Override
    @Transactional(readOnly = true)
    public Song findById(Integer id) {
        LOGGER.info("Finding song entry by using id: {}", id);

        Song songEntry = findSongEntryById(id);
        if (songEntry != null) {
            Hibernate.initialize(songEntry.getArtists());
        }

        LOGGER.info("Found song entry: {}", songEntry);

        return songEntry;
    }

    @Override
    @Transactional
    public Song create(Song newEntry) {
        LOGGER.info("Creating a new song entry by using information: {}", newEntry);

        Song created = repository.save(newEntry);
        LOGGER.info("Created a new song entry: {}", created);

        return created;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        LOGGER.info("Deleting a song entry with id: {}", id);

        Song deleted = findSongEntryById(id);
        LOGGER.debug("Found song entry: {}", deleted);

        repository.delete(deleted);
        LOGGER.info("Deleted song entry: {}", deleted);
    }

    @Override
    @Transactional
    public Song delete(Song song) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional
    public Song update(Song updatedEntry) {
        LOGGER.info("Updating the information of a song entry by using information: {}", updatedEntry);

        Song updated = findSongEntryById(updatedEntry.getId());
        updated.update(updatedEntry.getName(), updatedEntry.getUrl(), updatedEntry.getTotalView(), updatedEntry.getWeekViews(),
                updatedEntry.getCountry(), updatedEntry.getDescription(), updatedEntry.getOnHome(), updatedEntry.getPublished(),
                updatedEntry.getGenre(), updatedEntry.getType());

        //We need to flush the changes or otherwise the returned object
        //doesn't contain the updated audit information.
        repository.flush();

        LOGGER.info("Updated the information of the song entry: {}", updated);
        return updated;
    }

    @Override
    @Transactional
    public Artist addArtist(Integer id, Artist artist) {
        Song song = findById(id);
        Artist added = song.addArtist(artist);
        repository.flush();

        return added;
    }

    @Override
    @Transactional
    public boolean removeArtist(Integer id, Artist artist) {
        Song song = findById(id);
        boolean removed = song.removeArtist(artist);
        repository.flush();

        return removed;
    }

    @Override
    public List<Song> findByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    private Song findSongEntryById(Integer id) {
        Optional<Song> songResult = repository.findOne(id);
        return songResult.orElseThrow(() -> new SongNotFoundException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Song> getAllSongs(int page) {
        LOGGER.info("Finding all song entries page: {}.", page);

        PageRequest request = new PageRequest(page - 1, PAGE_SIZE, Sort.Direction.DESC, "id");
        Page<Song> songEntries = repository.findByIsPublished(true, request);
        for (Song song : songEntries) {
            Hibernate.initialize(song.getArtists());
        }
        LOGGER.info("Found {} song entries", songEntries.getContent().size());

        return songEntries;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Song> getHomeSongs() {
        LOGGER.info("Finding top 20 song entries by onHome: true");

        List<Song> songEntries = repository.findTop20ByOnHomeOrderByIdDesc(true);
        for (Song song : songEntries) {
            Hibernate.initialize(song.getArtists());
        }
        LOGGER.info("Found {} song entries", songEntries.size());

        return songEntries;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Song> getSongsByGenreName(String genreName, int page) {
        LOGGER.info("Finding song entries by genre: {}, page: {}.", genreName, page);

        PageRequest request = new PageRequest(page - 1, PAGE_SIZE, Sort.Direction.DESC, "id");
        Page<Song> songEntries = repository.findByIsPublishedAndGenreName(true, genreName, request);
        for (Song song : songEntries) {
            Hibernate.initialize(song.getArtists());
        }
        LOGGER.info("Found {} song entries", songEntries.getContent().size());

        return songEntries;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Song> getRelatedSongs(Artist artist) {
        LOGGER.info("Finding Top 6 song entries of artist:");

        List<Song> songEntries = repository.findTop6ByIsPublishedTrueAndArtistsNameOrderByIdDesc(artist.getName());
        for (Song song : songEntries) {
            Hibernate.initialize(song.getArtists());
        }
        LOGGER.info("Found {} song entries", songEntries.size());

        return songEntries;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Song> getNewUserUploadSong() {
        LOGGER.info("Finding user upload song entries of a week:");

        List<Song> songEntries = repository.findByIsPublishedFalseAndType(PlaylistType.USER.getPlaylistType());
        for (Song song : songEntries) {
            Hibernate.initialize(song.getArtists());
        }
        LOGGER.info("Found {} song entries", songEntries.size());

        return songEntries;
    }

    @Override
    @Transactional
    public Song getById(Integer id) {
        LOGGER.info("Finding song entry by using id: {}", id);

        Song songEntry = findSongEntryById(id);
        if (songEntry != null) {
            Hibernate.initialize(songEntry.getArtists());
        }

        LOGGER.info("Found song entry: {}", songEntry);
        LOGGER.info("Increments Views of song entrie: {}", songEntry);
        songEntry.incrementViews();
        repository.flush();

        return songEntry;
    }
}
