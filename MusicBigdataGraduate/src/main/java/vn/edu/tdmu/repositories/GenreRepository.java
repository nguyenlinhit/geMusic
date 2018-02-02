package vn.edu.tdmu.repositories;

import vn.edu.tdmu.models.Genre;

/**
 * Created by nguye on 1/29/2018.
 *
 */
public interface GenreRepository extends BaseRepository<Genre, Integer>{
    Genre findByName(String name);
}
