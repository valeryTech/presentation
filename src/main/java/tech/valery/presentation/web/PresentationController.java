package tech.valery.presentation.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import tech.valery.presentation.model.Presentation;
import tech.valery.presentation.model.Room;
import tech.valery.presentation.model.User;
import tech.valery.presentation.service.PresentationService;
import tech.valery.presentation.service.RoomService;
import tech.valery.presentation.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("users/{userId}")
public class PresentationController {

    private static final String VIEWS_PRESENTATIONS_CREATE_OR_UPDATE_FORM = "/presentations/createOrUpdatePresForm.html";

    @Autowired
    PresentationService presentationService;

    @Autowired
    UserService userService;

    @Autowired
    RoomService roomService;

    @InitBinder("user")
    public void initUserBinder(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("user")
    public User findUser(@PathVariable("userId") Long userId) {
        return userService.findOne(userId);
    }

    @ModelAttribute("rooms")
    public List<Room> populateRooms() {
        return roomService.findAll();
    }


    // todo presentation validator
    @GetMapping("/presentations/new")
    public String initCreationForm(User user, ModelMap model) {
        Presentation presentation = new Presentation();
        user.addPresentation(presentation);
        model.put("presentation", presentation);
        return VIEWS_PRESENTATIONS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/presentations/new")
    public String ProcessCreationForm(User user, @Valid Presentation presentation,
                                      BindingResult result, ModelMap model) {
        user.addPresentation(presentation);
        if (result.hasErrors()) {
            model.put("presentation", presentation);
            return VIEWS_PRESENTATIONS_CREATE_OR_UPDATE_FORM;
        } else {
            // todo users?
            this.presentationService.save(presentation);
            return "redirect:/users/{userId}";
        }
    }

    @GetMapping("/presentations/{presentationId}/edit")
    public String initUpdateForm(@PathVariable("presentationId") Long presentationId, ModelMap model) {
        Presentation presentation = presentationService.findOne(presentationId);
        model.put("presentation", presentation);
        model.put("users", userService.findAllByPresentations(presentation));
        return VIEWS_PRESENTATIONS_CREATE_OR_UPDATE_FORM;
    }

    //todo edit?
    @PostMapping("/presentations/{presentationId}/edit")
    public String processUpdateForm(User user, @Valid Presentation presentation,
                                    BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.put("presentation", presentation);
            return VIEWS_PRESENTATIONS_CREATE_OR_UPDATE_FORM;
        } else {
            Presentation presentation1 = new Presentation();
            presentation1.setTopic(presentation.getTopic());
            user.addPresentation(presentation1);
            userService.save(user);
            return "redirect:/users/{userId}";
        }
    }
}
