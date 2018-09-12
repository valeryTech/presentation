package tech.valery.presentation.config;

import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import tech.valery.presentation.rest.RoomPresentationRestController;

@TestConfiguration
public class ApplicationTestConfig {
    public ApplicationTestConfig(){
        MockitoAnnotations.initMocks(this);
    }

    @Bean
    RoomPresentationRestController roomPresentationRestController(){
        return new RoomPresentationRestController();
    }
}
