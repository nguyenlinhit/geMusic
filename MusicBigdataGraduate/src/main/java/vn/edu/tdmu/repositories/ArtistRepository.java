package vn.edu.tdmu.repositories;

import vn.edu.tdmu.models.Artist;

import java.util.List;

/**
 * Created by nguye on 1/29/2018.
 *
 */
public interface ArtistRepository extends BaseRepository<Artist, Integer>{
    List<Artist> findByNameContainsIgnoreCase(String name);

    Artist findByName(String name);
}
