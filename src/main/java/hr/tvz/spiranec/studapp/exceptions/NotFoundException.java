package hr.tvz.spiranec.studapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NotFoundException extends RuntimeException{

    public NotFoundException(final String message){
        super(message);
    }
}
