package app.controller;

import app.services.UserService;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private static final String ERROR_MESSAGE = "something is wrong";

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String printUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user_list";
    }

    @GetMapping("/add_user")
    public String showAddUserPage(ModelMap model) {
        model.addAttribute("user", new User());
        return "add_user";
    }

    @PostMapping("/add_user")
    public String addUser(ModelMap model,
                          @ModelAttribute("user") User user) {
        if (userService.saveUser(user)) {
            return "redirect:/users";
        }
        model.addAttribute("errorMessage", ERROR_MESSAGE);
        return "add_user";
    }

    @PostMapping(value = "/delete_user", params = "id")
    public String deleteUser(@RequestParam int id) {
        userService.removeUserById(id);
        return "redirect:/users";
    }

    @GetMapping(value = "/edit_user", params = "id")
    public String showEditUserPage(ModelMap model, @RequestParam int id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("userId", id);
        return "edit_user";
    }

    @PostMapping(value = "/edit_user", params = "id")
    public String editUser(ModelMap model, @ModelAttribute("user") User user, @RequestParam int id) {
        if (userService.updateUserById(id, user)) {
            return "redirect:/users";
        }
        model.addAttribute("errorMessage", ERROR_MESSAGE);
        return "edit_user";
    }
}