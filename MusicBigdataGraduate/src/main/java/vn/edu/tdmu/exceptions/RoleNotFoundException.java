package vn.edu.tdmu.exceptions;

/**
 * Created by NguyenLinh on 1/29/2018.
 *
 */
public class RoleNotFoundException extends RuntimeException {
    private final Integer id;

    public RoleNotFoundException(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
