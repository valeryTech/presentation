package tech.valery.presentation;

import org.springframework.context.annotation.Bean;
import tech.valery.presentation.service.*;

@org.springframework.context.annotation.Configuration
public class ConferenceTestConfiguration {

    @Bean
    public UserService userService(){
        return new UserServiceImpl();
    }

    @Bean
    public PresentationService presentationService(){
        return new PresentationServiceImpl();
    }

    @Bean
    public PerformanceService performanceService(){return new PerformanceServiceImpl();}

    @Bean RoomService roomService(){return new RoomServiceImpl();}
}
