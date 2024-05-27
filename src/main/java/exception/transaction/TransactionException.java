package exception.transaction;

import org.springframework.http.HttpStatus;

public class TransactionException extends Exception {

    String message;
    final HttpStatus status;

    public TransactionException(String message, HttpStatus status){
        this.message = message;
        this.status = status;
    }
}
