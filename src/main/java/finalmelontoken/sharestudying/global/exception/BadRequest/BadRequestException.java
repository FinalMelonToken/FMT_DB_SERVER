package finalmelontoken.sharestudying.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotFoundException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}