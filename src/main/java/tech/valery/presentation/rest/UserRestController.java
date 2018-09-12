package tech.valery.presentation.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tech.valery.presentation.model.Presentation;
import tech.valery.presentation.model.User;
import tech.valery.presentation.service.UserService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class UserRestController {

    @Autowired
    UserService userService;

    //@GetMapping("/users")
    public ResponseEntity<Collection<User>> getUsers(){
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<Collection<User>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
    }

    //todo add endpoint hierarchy
    //todo add default parameters configuration file

    @GetMapping("/users")
    public Page<User> showPage(@PageableDefault(page=0, size=10) Pageable pageable){

        //Pageable pageable = PageRequest.of(page, size, Sort.by("username"));

        return userService.findAll(pageable);

    }

}
