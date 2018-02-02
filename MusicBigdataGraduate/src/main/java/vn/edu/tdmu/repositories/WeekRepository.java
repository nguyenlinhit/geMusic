package vn.edu.tdmu.repositories;

import vn.edu.tdmu.models.Week;

/**
 * Created by NguyenLinh on 1/29/2018.
 *
 */
public interface WeekRepository extends BaseRepository<Week, Integer>{
    Week findFirstByOOrderByEndDateDesc();
}
