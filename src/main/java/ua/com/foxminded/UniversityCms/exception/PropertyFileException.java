package ua.com.foxminded.UniversityCms.exception;

import java.io.IOException;

public class PropertyFileException extends IOException {
    public PropertyFileException(String errorMessage) {
        super(errorMessage);
    }

}
