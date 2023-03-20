package com.example.springbootapp.controller;

import com.example.springbootapp.model.User;
import com.example.springbootapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getAllUsers(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("userList", userList);
        return "index";
    }

    @GetMapping("/search")
    public String getUserById(@RequestParam(required = false) Long id, Model model) {
        User user = userService.findById(id);
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("user", user);
        return "search";
    }

    @GetMapping("/new")
    public String showCreateUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "new_user";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("user", user);
        return "edit_user";
    }

    @PostMapping("/edit/{id}")
    public String updateUserSubmit(@PathVariable Long id, @ModelAttribute User user) {
        userService.update(id, user);
        return "redirect:/";
    }


//как в буте настроить, чтобы таймлиф видел patch и delete запросы я так и не нашел, если можно подскажите
    @GetMapping("/delete/{id}")
    public String deleteUserSubmit(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/";
    }
}
