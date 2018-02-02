package vn.edu.tdmu.exceptions;

/**
 * Created by nguye on 1/29/2018.
 *
 */
public class SongNotFoundException extends RuntimeException{
    private final Integer id;

    public SongNotFoundException(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
