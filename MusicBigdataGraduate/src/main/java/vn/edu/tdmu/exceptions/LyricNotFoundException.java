package vn.edu.tdmu.exceptions;

/**
 * Created by NguyenLinh on 1/29/2018.
 *
 */
public class LyricNotFoundException extends RuntimeException {

    private final Integer id;

    public LyricNotFoundException(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
