package az.dyh.travel.controller;

import az.dyh.travel.bean.CustomUserDetail;
import az.dyh.travel.entity.User;
import az.dyh.travel.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String signup(User user) {
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(User user) {
        userService.createUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user/index")
    public String getCurrentUser(Model model, Authentication authentication) {
        model.addAttribute("user", userService.
                findByUsername(((CustomUserDetail) authentication.getPrincipal()).getUsername()));
        return "user/index";
    }

    @GetMapping("/admin/getuser")
    public String getCurrentUser(@RequestParam("username") String username, Model model) {
        model.addAttribute("user", userService.findByUsername(username));
        return "user/index";
    }

}
