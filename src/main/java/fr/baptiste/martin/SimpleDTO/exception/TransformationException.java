package fr.baptiste.martin.SimpleDTO.exception;

/**
 * Exception throw if transformation error
 */
public class TransformationException extends Exception {
    public TransformationException() {
        super();
    }

    public TransformationException(String message) {
        super(message);
    }

    public TransformationException(String message, Throwable cause) {
        super(message, cause);
    }
}
