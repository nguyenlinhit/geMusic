package vn.edu.tdmu.services;

import org.apache.commons.collections.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.tdmu.enums.PlaylistType;
import vn.edu.tdmu.exceptions.PlaylistNotFoundException;
import vn.edu.tdmu.models.*;
import vn.edu.tdmu.repositories.PlaylistRepository;
import vn.edu.tdmu.repositories.SongPlaylistRepository;
import vn.edu.tdmu.repositories.WeekRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by NguyenLinh on 1/30/2018.
 *
 */
@Service
public class PlaylistServiceImpl implements PlaylistService{

    private static final Logger LOGGER = LoggerFactory.getLogger(PlaylistServiceImpl.class);

    private static final int PAGE_SIZE = 25;

    private final PlaylistRepository repository;
    private final SongPlaylistRepository songPlaylistRepository;
    private final WeekRepository weekRepository;

    @Autowired
    public PlaylistServiceImpl(PlaylistRepository repository, SongPlaylistRepository songPlaylistRepository, WeekRepository weekRepository) {
        this.repository = repository;
        this.songPlaylistRepository = songPlaylistRepository;
        this.weekRepository = weekRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Playlist> findAll() {
        LOGGER.info("Finding all playlist entries.");

        List<Playlist> playlistEntries = repository.findAll();
        LOGGER.info("Found {} playlist entries", playlistEntries.size());
        return playlistEntries;
    }

    @Override
    @Transactional(readOnly = true)
    public Playlist findById(Integer id) {
        LOGGER.info("Finding playlist entry bu using id: {}", id);

        Playlist playlistEntry = findPlaylistEntryById(id);
        LOGGER.info("Found playlist entry: {}", playlistEntry);
        return playlistEntry;
    }

    @Override
    @Transactional
    public Playlist create(Playlist newEntry) {
        LOGGER.info("Createing a new playlist entry by using information: {}", newEntry);

        Playlist created = this.repository.save(newEntry);
        LOGGER.info("Created a new playlist entry: {}", created);
        return created;
    }

    @Override
    public void delete(Integer id) {
        LOGGER.info("Deleting a playlist entry id: {}", id);

        Playlist deleted = findPlaylistEntryById(id);

        LOGGER.debug("Found playlist entry: {}", deleted);

        this.repository.delete(deleted);
        LOGGER.info("Deleted user entry: {}");
    }

    @Override
    @Transactional
    public Playlist update(Playlist update) {
        LOGGER.info("Updating the information of a playlist entry by using information: {}", update);

        Playlist updated = findPlaylistEntryById((update.getId()));
        updated.update(update.getName(), update.getTotalViews(), update.getWeekViews(),update.getCountry(),
                update.getArtist(),update.getGenre(),update.getType(),update.getOnHome(),update.getWeek(),update.getSlideActived());
        this.repository.flush();
        LOGGER.info("Updated the information of the playlist entry: {}", update);

        return updated;
    }

    @Override
    @Transactional
    public Playlist addSong(Integer id, List<Song> songs) {
        Playlist playlist = findById(id);

        for (int i = 0; i < songs.size(); i++){
            SongPlaylist sp = new SongPlaylist();
            sp.setPlaylist(playlist);
            sp.setSong(songs.get(i));
            sp.setIndex(i + 1);

            this.songPlaylistRepository.save(sp);
        }

        LOGGER.info("Updated the information of the playlist entry: {}", playlist);

        return playlist;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Playlist> getSlideActivePlaylist() {
        LOGGER.info("Finding all playlist entries by slideActived: true.");

        List<Playlist> playlistEntries = this.repository.findTop5BySlideActivedOrderByIdDesc(true);
        LOGGER.info("Found {} playlist entries", playlistEntries.size());
        return playlistEntries;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Playlist> getHomePlaylists() {
        LOGGER.info("Finding home playlist entries");

        List<Boolean> onHomes = new ArrayList<>();
        onHomes.add(true);

        List<String> types = new ArrayList<>();
        types.add(PlaylistType.OFFICIAL.getPlaylistType());
        types.add(PlaylistType.COLLECTION.getPlaylistType());

        PageRequest request = new PageRequest(0, PAGE_SIZE, Sort.Direction.DESC, "id");

        Page<Playlist> playlistEntries = repository.findByOnHomeInAndTypeIn(onHomes, types, request);
        LOGGER.info("Found {} playlist entries", playlistEntries.getContent().size());

        return playlistEntries;
    }

    @Override
    @Transactional(readOnly = true)
    public Playlist getLatestTopPlaylist(String country) {
        LOGGER.info("Finding lastest top playlist entry by country: {}", country);

        Week lastestWeek = weekRepository.findFirstByOrderByEndDateDesc();
        Playlist playlistEntry = this.repository.findFirstByWeekAndTypeAndCountry(lastestWeek, PlaylistType.TOP.getPlaylistType(), country);
        LOGGER.info("Found playlist entry: {}", playlistEntry);

        return playlistEntry;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Playlist> getTop3UserPlaylists(String username) {

        LOGGER.info("Finding Top 3 playlist entries by username: {}", username);

        List<Playlist> playlistEntries = this.repository.findTop3ByCreatedByUserOrderByIdDesc(username);

        LOGGER.info("Found {} playlit entries", playlistEntries.size());

        return playlistEntries;
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Playlist> getAllUserPlaylists(String username) {
        LOGGER.info("Finding ALL playlist entries by username: {}", username);

        List<Playlist> playlistEntries = this.repository.findByCreatedByUserOrderByIdDesc(username);
        List<Playlist> playlistEntries2 = this.repository.findByUsersUsername(username);

        List<Playlist> playlistFinal = ListUtils.union(playlistEntries, playlistEntries2);

        return playlistFinal;
    }

    @Override
    public Page<Playlist> getAllOfficialAndCollectionPlaylists(int page) {
        LOGGER.info("Finding all playlists entries page {}", page);

        List<String> types = new ArrayList<>();
        types.add(PlaylistType.OFFICIAL.getPlaylistType());
        types.add(PlaylistType.COLLECTION.getPlaylistType());

        PageRequest request = new PageRequest(page - 1, PAGE_SIZE, Sort.Direction.DESC, "id");

        Page<Playlist> playlistEntries = this.repository.findByTypeIn(types, request);

        LOGGER.info("Found {} playlist entries", playlistEntries.getContent().size());

        return playlistEntries;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Playlist> getPlaylistByGenreName(String genreName, int page) {
        LOGGER.info("Finding all offcial and collection playlist entries page {}", page);

        List<String> types = new ArrayList<>();
        types.add(PlaylistType.OFFICIAL.getPlaylistType());
        types.add(PlaylistType.COLLECTION.getPlaylistType());

        PageRequest request = new PageRequest(page -1, PAGE_SIZE, Sort.Direction.DESC, "id");

        Page<Playlist> playlistEntries = this.repository.findByTypeInAndGenreName(types, genreName, request);
        LOGGER.info("Found {} playlist entries", playlistEntries.getContent().size());
        return playlistEntries;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Playlist> getRelatedPlaylists(Artist artist) {
        LOGGER.info("Finding top 4 related playlist entries: {}", artist);

        List<String> types = new ArrayList<>();
        types.add(PlaylistType.OFFICIAL.getPlaylistType());
        types.add(PlaylistType.COLLECTION.getPlaylistType());

        List<Playlist> playlistEntries = this.repository.findTop4ByTypeInAndArtistOrderByIdDesc(types, artist);
        LOGGER.info("Found {} playlist entries", playlistEntries.size());

        return playlistEntries;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Playlist> getRecommendedPlaylists(String recentPlaylists) {
        LOGGER.info("Finding top 3 recommened playlist entries");

        List<Playlist> returned = new ArrayList<>();

        //Get playlist from user cookie
        if (!recentPlaylists.equals("")){
            for (String retval : recentPlaylists.split("\n",3)){
                LOGGER.debug("Playlist History: ", retval);
            }
        }

        int no = returned.size();

        //Make sure you enough get 3 playlist
        if (no < 3){
            List<String> types = new ArrayList<>();
            types.add(PlaylistType.OFFICIAL.getPlaylistType());
            types.add(PlaylistType.COLLECTION.getPlaylistType());

            List<Playlist> weekTopPls = this.repository.findTop3ByTypeInOrderByWeekViewsDesc(types);
            int size = Math.min(3 - no, weekTopPls.size());
            for (int i = 0; i < size; i++){
                returned.add(weekTopPls.get(i));
            }
        }

        LOGGER.info("Found {}  playlist entries", returned.size());
        return returned;
    }

    @Override
    @Transactional
    public Playlist getById(Integer id) {
        LOGGER.info("Finding playlist entry bu using id: {}", id);

        Playlist playlistEntry = findPlaylistEntryById(id);
        LOGGER.info("Found playlist entry: {}", playlistEntry);

        LOGGER.info("Increment views of playlist entries: {}", playlistEntry);

        playlistEntry.incrementViews();
        this.repository.flush();
        return playlistEntry;
    }

    private Playlist findPlaylistEntryById(Integer id){
        Optional<Playlist> playlistResult = this.repository.findOne(id);
        return playlistResult.orElseThrow(() -> new PlaylistNotFoundException(id));
    }
}
