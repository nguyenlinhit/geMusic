package vn.edu.tdmu.exceptions;

/**
 * Created by NguyenLinh on 1/29/2018.
 */
public class UserNotFoundException extends RuntimeException{
    private final Integer id;

    public UserNotFoundException(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
