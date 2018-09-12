package tech.valery.presentation;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import tech.valery.presentation.model.Presentation;
import tech.valery.presentation.model.User;
import tech.valery.presentation.repository.RoleRepository;
import tech.valery.presentation.repository.UserRepository;
import tech.valery.presentation.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void ShouldSaveUser() {
        User user = new User();
        user.setUsername("Jason Born");
        user.setPassword("asdf");
        user.setRole(roleRepository.findByRole("USER"));

        Presentation presentation = new Presentation("Spring Boot ripper");

        user.addPresentation(presentation);

        userService.save(user);

        System.out.println(userService.findAll());
    }

    @Test
    public void findPageWithUsers(){
        Pageable pageable = PageRequest.of(0, 3,
                Sort.by("username"));

        Page<User> page = userRepository.findAll(pageable);
        System.out.println(page);



    }

    @Test
    public void OrphanPresentationRemoval() throws IOException {

    }
}
