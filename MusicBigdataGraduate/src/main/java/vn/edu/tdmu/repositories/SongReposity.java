package vn.edu.tdmu.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.tdmu.models.Song;

import java.util.List;

/**
 * Created by NguyenLinh on 1/29/2018.
 *
 */
public interface SongReposity extends BaseRepository<Song, Integer>{
    Page<Song> findByIsPublished(Boolean isPublished, Pageable pageable);

    Page<Song> findByIsPublishedAndGenreName(Boolean ispublished, String genreName, Pageable pageable);

    List<Song> findByNameContainsIgnoreCase(String name);

    List<Song> findTop20ByOnHomeOrderByIdDesc(Boolean onHome);

    List<Song> findTop6ByIsPublishedTrueAndArtistsNameOrderByIdDesc(String name);

    List<Song> findByIsPublishedFalseAndType(String type);
}
