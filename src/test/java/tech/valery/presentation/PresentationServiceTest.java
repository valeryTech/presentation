package tech.valery.presentation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import tech.valery.presentation.model.Presentation;
import tech.valery.presentation.model.Room;
import tech.valery.presentation.model.User;
import tech.valery.presentation.repository.PresentationRepository;
import tech.valery.presentation.service.PresentationService;
import tech.valery.presentation.service.PresentationServiceImpl;
import tech.valery.presentation.service.UserService;
import tech.valery.presentation.service.UserServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest

public class PresentationServiceTest {

    @Autowired
    PresentationService presentationService;

    @Autowired
    UserService userService;


    @TestConfiguration
    static class PresentationServiceTestConfiguration{
        @Bean
        public PresentationService presentationService(){
            return new PresentationServiceImpl();
        }

        @Bean UserService userService(){
            return new UserServiceImpl();
        }
    }

    @MockBean
    private PresentationRepository presentationRepository;

    @Before
    public void init(){

    }

    @Test
    public void GetAllPresentationsByUser() {

        List<User> users = userService.findAll();
        List<Presentation> allByUser = presentationService.findAllByUsers(userService.findOne(2L));
        allByUser.stream().forEach(System.out::println);

    }

    @Test
    public void testThereIsNotIntersections() throws Exception{
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date perftime = formatter.parse("2018-02-03 10:40:00");

        Room room = new Room();
        room.setId(2l);

        Presentation presentation = new Presentation();
        presentation.setPerftime(perftime);
        presentation.setRoom(room);

        //presentationService.save(presentation);
        System.out.println(userService.findAll());
        System.out.println(presentationService.findOne(1l));

        //System.out.println(presentationService.findAllIntersected(presentation));
    }
}
