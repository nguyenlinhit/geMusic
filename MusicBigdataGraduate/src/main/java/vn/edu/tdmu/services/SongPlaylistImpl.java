package vn.edu.tdmu.services;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.tdmu.exceptions.PlaylistNotFoundException;
import vn.edu.tdmu.models.Playlist;
import vn.edu.tdmu.models.SongPlaylist;
import vn.edu.tdmu.repositories.SongPlaylistRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by NguyenLinh on 2/1/2018.
 *
 */
@Service
public class SongPlaylistImpl implements SongPlaylistService{

    private static final Logger LOGGER = LoggerFactory.getLogger(SongPlaylistImpl.class);

    private final SongPlaylistRepository repository;

    @Autowired
    public SongPlaylistImpl(SongPlaylistRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SongPlaylist> findAll() {
        LOGGER.info("Finding all songPlaylist entries.");

        List<SongPlaylist> songPlaylistEntries = this.repository.findAll();
        LOGGER.info("Found {} songPlaylist entries", songPlaylistEntries.size());

        return songPlaylistEntries;
    }

    @Override
    @Transactional(readOnly = true)
    public SongPlaylist findById(Integer id) {
        LOGGER.info("Findinf songPlaylist entry by using id: {}", id);

        SongPlaylist songPlaylistEntry = findSongPlaylistEntryById(id);

        LOGGER.info("Found songPlaylist entry: {}", songPlaylistEntry);
        return songPlaylistEntry;
    }

    private SongPlaylist findSongPlaylistEntryById(Integer id) {
        Optional<SongPlaylist> songPlaylistResult = this.repository.findOne(id);
        return songPlaylistResult.orElseThrow(() -> new PlaylistNotFoundException(id));
    }

    @Override
    @Transactional
    public SongPlaylist create(SongPlaylist newEntry) {

        LOGGER.info("Creating a new songPlaylist entry by using information: {}", newEntry);

        SongPlaylist created = repository.save(newEntry);
        LOGGER.info("Created a new songPlaylist entry: {}", created);

        return created;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        LOGGER.info("Deleting a songPlaylist entry with id: {}", id);

        SongPlaylist deleted = findSongPlaylistEntryById(id);
        LOGGER.debug("Found songPlaylist entry: {}", deleted);

        repository.delete(deleted);
        LOGGER.info("Deleted user entry: {}");
    }

    @Override
    @Transactional
    public SongPlaylist update(SongPlaylist update) {
        LOGGER.info("Updating the information of a songPlaylist entry by using information: {}", update);

        //
        repository.flush();

        //LOGGER.info("Updated the information of the songPlaylist entry: {}", updated);
        return null;
    }

    @Override
    @Transactional
    public List<SongPlaylist> create(List<SongPlaylist> entities) {
        LOGGER.info("Creating new {} songPlaylist entries", entities.size());

        Iterable<SongPlaylist> created = repository.save(entities);
        ArrayList<SongPlaylist> songPlaylists = null;
        //LOGGER.info("Created new {} songPlaylist entries", songPlaylists.size());

        return songPlaylists;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SongPlaylist> findByPlaylist(Playlist playlist) {
        LOGGER.info("Finding all songPlaylist entries by using playlist: {}", playlist);

        List<SongPlaylist> lsSongPlaylist = repository.findByPlaylist(playlist);
        LOGGER.info("Found songPlaylist entries: {}", lsSongPlaylist);

        for (SongPlaylist sp : lsSongPlaylist) {
            Hibernate.initialize(sp.getSong().getArtists());
        }

        return lsSongPlaylist;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SongPlaylist> findPlaylistOrderByOrderAsc(Playlist playlist) {
        LOGGER.info("Finding all songPlaylist entries by using playlist: {}", playlist);

        List<SongPlaylist> lsSongPlaylist = repository.findByPlaylistOrderByOrderAsc(playlist);
        LOGGER.info("Found songPlaylist entries: {}", lsSongPlaylist);

        for (SongPlaylist sp : lsSongPlaylist) {
            Hibernate.initialize(sp.getSong().getArtists());
        }

        return lsSongPlaylist;
    }

    @Override
    @Transactional
    public SongPlaylist changeOrder(Integer songPlaylistId, Integer order) {
        LOGGER.info("Updating the order of a songPlaylist entry id: {}", songPlaylistId);

        SongPlaylist sp = findById(songPlaylistId);
        sp.updateOrder(order);
        repository.flush();

        LOGGER.info("Updated the order of the songPlaylist entry: {}", sp);
        return sp;
    }

    @Override
    @Transactional
    public SongPlaylist remove(Integer id) {
        LOGGER.info("Deleting a songPlaylist entry with id: {}", id);

        SongPlaylist deleted = findSongPlaylistEntryById(id);
        LOGGER.debug("Found songPlaylist entry: {}", deleted);

        repository.delete(deleted);
        LOGGER.info("Deleted user entry: {}");

        return deleted;
    }
}
