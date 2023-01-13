package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Roles;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RolesService;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RolesService rolesService;

    @Autowired
    public AdminController(UserService userService, RolesService rolesService) {
        this.userService = userService;
        this.rolesService = rolesService;
    }

    @GetMapping
    public String getUser(Model model) {
        model.addAttribute("userList", userService.getList());
        return "admin";
    }

    @GetMapping("/newUserAdmin")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("newUser", user);

        model.addAttribute("roleList", rolesService.getList());

        return "newUsers";
    }

    @PostMapping("/newUserAdmin")
    public String saveNewUser(User user) {
        List<String> list = user.getRoles().stream().map(r -> r.getName()).collect(Collectors.toList());
        List<Roles> roles = rolesService.listByName(list);
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/editUser/{id}")
    public String editUser(Model model, @PathVariable("id") Long id) {

        model.addAttribute("editUser", userService.getUserId(id));
        model.addAttribute("roleList", rolesService.getList());
        return "editUsers";
    }

    @PatchMapping("/{id}")
    public String userSaveEdit(User user) {
        List<String> list = user.getRoles().stream().map(r -> r.getName()).collect(Collectors.toList());
        List<Roles> roles = rolesService.listByName(list);
        user.setRoles(roles);
        userService.editUser(user);
        return "redirect:/admin";
    }


}