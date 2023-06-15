package com.vn.assignmentjava5.controller;

import com.vn.assignmentjava5.entities.Users;
import com.vn.assignmentjava5.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
//@RequestMapping("/users/")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/crudUsers")
    public String adminUsersPage(Model model) {
        List<Users> usersList = usersService.getAllUser();
        model.addAttribute("list", usersList);
        return "admin/crudUsers";
    }

    @GetMapping(value = "/add")
    public String addUsers(Model model){
        model.addAttribute("users", new Users());
        return "redirect:/crudUsers";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateUsersForm(@PathVariable("id") Integer id, Model model) {
        Users user = usersService.findUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("user", user);
        return "admin/editUsers";
    }

    @RequestMapping(value = "saveUsers", method = RequestMethod. POST)
    public String saveUsers (@Validated Users user, BindingResult bindingResult, RedirectAttributes model) {
        if (bindingResult.hasErrors()) {
            return "admin/crudUsers";
        } else{
            usersService.saveUser(user);
        model. addFlashAttribute("success", "Thêm moi tthành cong!");
        return "redirect:/crudUsers";
        }}
    @PostMapping("/updateUsers/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Validated Users user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "admin/crudUsers";
        }

        usersService.saveUser(user);
        return "redirect:/crudUsers";
    }

    @RequestMapping(value = "/deleteUsers", method = RequestMethod.GET)
    public String deleteUser (@RequestParam(name = "id") Integer userId, RedirectAttributes model) {
        usersService.deleteUser(userId);
        model.addFlashAttribute("success", "Xóa thành công !");
        return "admin/crudUsers";
    }

}
