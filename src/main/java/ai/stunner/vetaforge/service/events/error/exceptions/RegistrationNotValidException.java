package ai.stunner.vetaforge.service.events.error.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RegistrationNotValidException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RegistrationNotValidException(String message) {
        super(message);
    }
}
