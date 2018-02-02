package vn.edu.tdmu.repositories;

import vn.edu.tdmu.models.Playlist;
import vn.edu.tdmu.models.SongPlaylist;

import java.util.List;

/**
 * Created by NguyenLinh on 1/29/2018.
 *
 */
public interface SongPlaylistRepository extends BaseRepository<SongPlaylist, Integer>{
    Iterable<SongPlaylist> save(Iterable<SongPlaylist> entities);

    List<SongPlaylist> findByPlaylist(Playlist playlist);

    List<SongPlaylist> findByPlaylistOrderByOrderAsc(Playlist playlist);
}
