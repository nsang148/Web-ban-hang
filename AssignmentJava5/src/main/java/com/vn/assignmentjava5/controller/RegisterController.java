package com.vn.assignmentjava5.controller;


import com.vn.assignmentjava5.entities.Users;
import com.vn.assignmentjava5.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {
    @Autowired
    private UsersService usersService;



    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String save (@Validated Users users, BindingResult bindingResult, RedirectAttributes model){
        if (bindingResult.hasErrors()){
            return "/register";
        } else {

            usersService.saveUser(users);
            return "redirect:/login";
        }
    }
}
