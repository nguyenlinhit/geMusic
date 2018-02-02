package vn.edu.tdmu.services;

import vn.edu.tdmu.models.Week;

/**
 * Created by NguyenLinh on 2/1/2018.
 *
 */
public interface WeekService extends BaseService<Week, Integer>{
    Week update(Week updated);
}
