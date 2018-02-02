package vn.edu.tdmu.services;

import vn.edu.tdmu.models.Playlist;
import vn.edu.tdmu.models.SongPlaylist;

import java.util.List;

/**
 * Created by NguyenLinh on 2/1/2018.
 *
 */
public interface SongPlaylistService extends BaseService<SongPlaylist, Integer>{
    SongPlaylist update(SongPlaylist update);

    List<SongPlaylist> create(List<SongPlaylist> entities);

    List<SongPlaylist> findByPlaylist(Playlist playlist);

    List<SongPlaylist> findPlaylistOrderByOrderAsc(Playlist playlist);

    SongPlaylist changeOrder(Integer songPlaylistId, Integer order);

    SongPlaylist remove(Integer id);
}
