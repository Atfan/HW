package org.company.TicketOnline2.utils;

import lombok.extern.log4j.Log4j2;
import org.company.ticketonline2.exception.PropertyFileException;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Log4j2
@Service
public class PropertyFactory {

    private static PropertyFactory propertyFactory;
    private static Properties properties;
    private static final PropertyReader PROPERTY_READER;

    static {
        PROPERTY_READER = new PropertyReader();
        try {
            properties = PROPERTY_READER.readProperties();
        } catch (PropertyFileException e) {
            log.error("Error open property file");
        }
    }

    public static PropertyFactory getInstance() {
        if (propertyFactory == null) {
            propertyFactory = new PropertyFactory();
        }
        return propertyFactory;
    }

    public Properties getProperty() {
        return properties;
    }

}
