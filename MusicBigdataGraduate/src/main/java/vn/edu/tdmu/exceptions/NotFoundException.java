package vn.edu.tdmu.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by NguyenLinh on 1/29/2018.
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{
    private static final long serialVersionUID = 7582550023114509895L;

    public NotFoundException(String message){
        super(message);
    }
}
