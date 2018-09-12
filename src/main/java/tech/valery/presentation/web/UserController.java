package tech.valery.presentation.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tech.valery.presentation.model.User;
import tech.valery.presentation.service.UserService;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class UserController {

    private final static String VIEWS_USER_CREATE_OR_UPDATE_FORM = "/users/createOrUpdateUserForm.html";

    @Autowired
    private UserService userService;

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }


    //from list?
    @GetMapping("users/new")
    public String initCreationForm(Map<String, Object> model){
        User user = new User();
        model.put("user", user);
        return VIEWS_USER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("users/new")
    public String processCreationForm(@Valid User user, BindingResult result){
        if(result.hasErrors()){
            return VIEWS_USER_CREATE_OR_UPDATE_FORM;
        }else {
            this.userService.save(user);
            return "redirect:/users/" + user.getId();
        }
    }


    @GetMapping(value = "/users")
    public String userList(Model model) {
        model.addAttribute("userList", userService.findAll());
        return "users/userList";
    }

    @GetMapping("/users/{userId}/edit")
    public String initUpdateOwnerForm(@PathVariable("userId") long userId, Model model) {
        User user = this.userService.findOne(userId);
        model.addAttribute(user);
        model.addAttribute("presentations", user.getPresentations());
        return VIEWS_USER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/users/{userId}/edit")
    public String processUpdateOwnerForm(@Valid User user, BindingResult result,
                                         @PathVariable("userId") long userId) {
        if (result.hasErrors()) {
            return VIEWS_USER_CREATE_OR_UPDATE_FORM;
        } else {
            // todo refactor
            User user2Change = userService.findOne(userId);
            //white changed fields
            user2Change.setRole(user.getRole());
            user2Change.setUsername(user.getUsername());
            this.userService.save(user2Change);
            return "redirect:/users/{userId}";
        }
    }

    @RequestMapping(value = "/users/delete/{userId}", method = RequestMethod.GET)
    public String userDelete(Model model, @PathVariable(required = true, name = "userId") Long id) {
        userService.deleteUser(id);
        model.addAttribute("userList", userService.findAll());
        return "redirect:/users";
    }

    @GetMapping("/users/{userId}")
    public ModelAndView showOwner(@PathVariable("userId") long userId) {
        ModelAndView mav = new ModelAndView("users/userDetails");
        mav.addObject(this.userService.findOne(userId));
        return mav;
    }
}
