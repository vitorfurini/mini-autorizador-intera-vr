package exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApiException extends Exception {

  private static final long serialVersionUID = -3882807453175214074L;

  String message;
  final HttpStatus status;

    public ApiException(String message, HttpStatus status) {
        this.status = status;
        this.message = message;
    }

}
