package ru.vladislav_smirnov.spring_boot_security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.vladislav_smirnov.spring_boot_security.model.Role;
import ru.vladislav_smirnov.spring_boot_security.model.User;
import ru.vladislav_smirnov.spring_boot_security.service.RoleService;
import ru.vladislav_smirnov.spring_boot_security.service.UserService;

import java.security.Principal;
import java.util.Collection;

@Controller
@RequestMapping("/admin")
public class AdminsControllers {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminsControllers(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping
    public String showAllUsers(ModelMap modelMap, Principal principal) {
        modelMap.addAttribute("listUsers", userService.getAllUsers());
        User user = userService.findByEmail(principal.getName());
        modelMap.addAttribute("user", user);
        User newUser = new User();
        modelMap.addAttribute("newUser", newUser);
        Collection<Role> newRoles = roleService.getAllRoles();
        modelMap.addAttribute("newRoles", newRoles);
        return "/admin/index";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUsers(user);
        return "redirect:/admin";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Long id, Model modelMap) {
        User user = userService.getUserById(id);
        modelMap.addAttribute("user", user);
        Collection<Role> roles = roleService.getAllRoles();
        modelMap.addAttribute("roles", roles);
        return "/admin/update_user";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

}