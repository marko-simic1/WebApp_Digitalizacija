package hr.fer.progi.backend.exception;

public class ChangePasswordException extends RuntimeException {
    private static final long serialVersionId = 2;

    public ChangePasswordException(String message) {
        super(message);
    }
}
