package vn.edu.tdmu.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.tdmu.models.Artist;
import vn.edu.tdmu.models.Playlist;
import vn.edu.tdmu.models.Week;

import java.util.Collection;
import java.util.List;

/**
 * Created by nguye on 1/29/2018.
 *
 */
public interface PlaylistRepository extends BaseRepository<Playlist, Integer>{
    List<Playlist> findByNameContainsIgnoreCase(String name);

    List<Playlist> findTop5BySlideActivedOrderByIdDesc(Boolean actived);

    Page<Playlist> findByOnHomeInAndTypeIn(Collection<Boolean> onHomes, Collection<String> types, Pageable pageable);

    Page<Playlist> findByTypeInAndGenreName(Collection<String> types, String genreName, Pageable pageable);

    Page<Playlist> findByTypeIn(Collection<String> types, Pageable pageable);

    Playlist findFirstByWeekAndTypeAndCountry(Week week, String type, String country);

    List<Playlist>  findByCreatedByUserOrderByIdDesc(String name);

    List<Playlist> findTop3ByCreatedByUserOrderByIdDesc(String name);

    List<Playlist> findTop4ByTypeInAndArtistOrderByIdDesc(Collection<String> types, Artist artist);

    List<Playlist> findTop3ByTypeInOrderByWeekViewsDesc(Collection<String> types);

    List<Playlist> findByUsersUsername(String username);
}
