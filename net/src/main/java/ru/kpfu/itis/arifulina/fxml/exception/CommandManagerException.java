package ru.kpfu.itis.arifulina.fxml.exception;

public class CommandManagerException extends Exception{
    public CommandManagerException() {
    }

    public CommandManagerException(String message) {
        super(message);
    }

    public CommandManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandManagerException(Throwable cause) {
        super(cause);
    }

    public CommandManagerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
