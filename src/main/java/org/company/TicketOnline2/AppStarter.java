package org.company.TicketOnline2;

import lombok.extern.log4j.Log4j2;
import org.company.ticketonline2.utils.TicketOnlineInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
public class AppStarter {

    @Autowired
    private TicketOnlineInitializer ticketOnlineInitializer;

    @Bean
    public ApplicationRunner init() {
        log.info("ApplicationRunner has started");
        return args -> {
            ticketOnlineInitializer.initialize();
        };
    }
}
