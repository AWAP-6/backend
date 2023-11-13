package org.example.Controller;

import org.example.Model.Entity.User;
import org.example.Model.LocationRepo;
import org.example.Service.LocationService;
import org.example.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private LocationService locationService;

    //    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        model.addAttribute("locations", locationService.getAllLocations());
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(@ModelAttribute("userForm") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!usersService.createUser(user)) {
            model.addAttribute("errorMessage", "Email " + user.getEmail() + " already exists");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/hello")
    public String securityUrl() {
        return "hello";
    }

}
