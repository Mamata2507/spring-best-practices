package tech.nerderbur.demospringsvc.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

// Combines commonly used lombok annotations like @Getter, @Setter, @AllArgsConstructor...
@Data
public class ApiException {
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;
}
