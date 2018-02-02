package vn.edu.tdmu.services;

import vn.edu.tdmu.models.Lyric;

/**
 * Created by NguyenLinh on 1/29/2018.
 *
 */
public interface LyricService extends BaseService<Lyric, Integer>{
    Lyric update(Lyric update);
}
