package vn.edu.tdmu.services;

import org.springframework.data.domain.Page;
import vn.edu.tdmu.models.Artist;
import vn.edu.tdmu.models.Playlist;
import vn.edu.tdmu.models.Song;

import java.util.List;

/**
 * Created by NguyenLinh on 1/30/2018.
 *
 */
public interface PlaylistService extends BaseService<Playlist, Integer>{
    Playlist update(Playlist update);

    Playlist addSong(Integer id, List<Song> songs);

    List<Playlist> getSlideActivePlaylist();

    Page<Playlist> getHomePlaylists();

    Playlist getLatestTopPlaylist(String country);

    List<Playlist> getTop3UserPlaylists(String username);

    List<Playlist> getAllUserPlaylists(String username);

    Page<Playlist> getAllOfficialAndCollectionPlaylists(int page);

    Page<Playlist> getPlaylistByGenreName(String genreName, int page);

    List<Playlist> getRelatedPlaylists(Artist artist);

    List<Playlist> getRecommendedPlaylists(String recentPlaylists);

    Playlist getById(Integer id);
}
