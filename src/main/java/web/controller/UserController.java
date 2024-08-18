package web.controller;

import db.service.UserService;
import db.service.UserServiceImpl;
import model.User;
import model.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final String errorMessage = "something is wrong";

    private final UserService userService = new UserServiceImpl();

    @RequestMapping(value = {"/users"}, method = RequestMethod.GET)
    public String printUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user_list";
    }

    @RequestMapping(value = { "/add_user" }, method = RequestMethod.GET)
    public String showAddUserPage(ModelMap model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "add_user";
    }

    @RequestMapping(value = { "/add_user" }, method = RequestMethod.POST)
    public String addUser(ModelMap model, //
                          @ModelAttribute("userForm") UserForm userForm) {
        String name = userForm.getName();
        String surname = userForm.getSurname();
        int age = userForm.getAge();

        if (name != null && !name.isEmpty()
                && surname != null && !surname.isEmpty()
                && age > 0) {
            userService.saveUser(name, surname, age);
            return "redirect:/users";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "add_user";
    }

    @RequestMapping(value = {"/delete_user"}, params = "id", method = RequestMethod.POST)
    public String deleteUser(@RequestParam int id) {
        userService.removeUserById(id);
        return "redirect:/users";
    }

    @RequestMapping(value = {"/edit_user"}, params = "id", method = RequestMethod.GET)
    public String showEditUserPage(ModelMap model, @RequestParam int id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("userId", id);
        return "edit_user";
    }

    @RequestMapping(value = {"/edit_user"}, params = "id", method = RequestMethod.POST)
    public String editUser(ModelMap model, @ModelAttribute("user") User user, @RequestParam int id) {
        String name = user.getName();
        String surname = user.getSurname();
        int age = user.getAge();

        if (name != null && !name.isEmpty()
                && surname != null && !surname.isEmpty()
                && age > 0) {
            userService.updateUserById(id, name, surname, age);
            return "redirect:/users";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "edit_user";
    }
}