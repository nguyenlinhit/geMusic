package vn.edu.tdmu.services;

import vn.edu.tdmu.models.Genre;

/**
 * Created by NguyenLinh on 1/29/2018.
 *
 */
public interface GenreService extends BaseService<Genre, Integer>{
    Genre update(Genre update);

    boolean isGenreNameUnique(Integer id, String name);
}
