package tech.valery.presentation.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tech.valery.presentation.config.ApplicationTestConfig;
import tech.valery.presentation.model.Presentation;
import tech.valery.presentation.model.Role;
import tech.valery.presentation.model.Room;
import tech.valery.presentation.model.User;
import tech.valery.presentation.service.PresentationService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RoomPresentationRestController.class)
@ContextConfiguration(classes = ApplicationTestConfig.class)
public class PresentationRestControllerTest {

    @Autowired
    RoomPresentationRestController presentationRestController;

    @MockBean
    protected PresentationService presentationService;

    private MockMvc mockMvc;

    private List<Presentation> presentations;

    @Before
    public void initPresentations(){
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(presentationRestController)
                .setControllerAdvice(new ExceptionControllerAdvice())
                .build();

        presentations = new ArrayList<>();

        Role role = new Role();
        role.setId(1l);
        role.setRole("USER");

        User user = new User("Evan Dean", "e@gmail.com", "pass", role);
        user.setId(1l);

        Room room = new Room();
        room.setId(1l);
        room.setNumber(100);

        Presentation presentation = new Presentation();
        presentation.setId(1l);
        presentation.setTopic("Topic 1");
        presentation.setRoom(room);
        presentation.setPerftime(new Date());
        user.addPresentation(presentation);
        presentations.add(presentation);

        presentation = new Presentation();
        presentation.setId(2l);
        presentation.setTopic("Topic 2");
        presentation.setRoom(room);
        presentation.setPerftime(new Date());
        user.addPresentation(presentation);
        presentations.add(presentation);

    }

    @Test
    public void testGetPresentationSuccess() throws Exception{

        given(this.presentationService.findOne(1l)).willReturn(presentations.get(0));
        this.mockMvc.perform(get("/api/rooms/2/presentations/1")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id").value(1l))
                .andExpect(jsonPath("$.topic").value("Topic 1"));

    }

    @Test
    public void testCreatePresentationSuccess() throws Exception{
        Presentation presentation = presentations.get(0);
        presentation.setId(999l);
        ObjectMapper mapper = new ObjectMapper();
        String newPresentationAsJson = mapper.writeValueAsString(presentation);
        System.out.println(newPresentationAsJson);

        this.mockMvc.perform(post("/api/rooms/1/presentations/")
                .content(newPresentationAsJson).accept(MediaType.APPLICATION_JSON_UTF8).contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isCreated());

    }



}
