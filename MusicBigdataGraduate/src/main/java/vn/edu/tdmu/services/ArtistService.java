package vn.edu.tdmu.services;

import vn.edu.tdmu.models.Artist;

import java.util.List;

/**
 * Created by nguye on 1/29/2018.
 *
 */
public interface ArtistService extends BaseService<Artist, Integer>{
    List<Artist> findByNamẹ(String name);

    Artist update(Artist updated);

    boolean isArtistNameUnique(Integer id, String name);
}
