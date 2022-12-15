package ru.vladislav_smirnov.spring_boot_security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.vladislav_smirnov.spring_boot_security.model.User;
import ru.vladislav_smirnov.spring_boot_security.service.RoleService;
import ru.vladislav_smirnov.spring_boot_security.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminsControllers {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminsControllers(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping
    public String showAllUsers(ModelMap modelMap, Principal principal) {
        modelMap.addAttribute("principalUser", userService.findByEmail(principal.getName())); //авторизованный юзер

        modelMap.addAttribute("listUsers", userService.getAllUsers()); // Получение всех юзеров

        modelMap.addAttribute("user",new User()); // создание юзера

        modelMap.addAttribute("roles", roleService.getAllRoles()); // получение ролей для юзера
        return "/admin/index";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUsers(user);
        return "redirect:/admin";
    }

    @PostMapping ("/updateUser/{id}")
    public String updateUser(@ModelAttribute("iUs") User user) {
        userService.saveUsers(user);
        return "redirect:/admin";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

}