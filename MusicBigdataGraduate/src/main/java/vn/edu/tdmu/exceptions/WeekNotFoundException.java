package vn.edu.tdmu.exceptions;

/**
 * Created by NguyenLinh on 1/29/2018.
 */
public class WeekNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
	private final  Integer id;

    public WeekNotFoundException(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
