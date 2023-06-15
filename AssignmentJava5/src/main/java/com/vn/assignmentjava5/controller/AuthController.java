package com.vn.assignmentjava5.controller;

import com.vn.assignmentjava5.entities.Product;
import com.vn.assignmentjava5.entities.Users;
import com.vn.assignmentjava5.repository.ProductRepository;
import com.vn.assignmentjava5.repository.UsersRepository;
import com.vn.assignmentjava5.service.CategoryService;
import com.vn.assignmentjava5.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AuthController {
    @Autowired
    HttpServletRequest request;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/login")
    public String getLoginForm(Model model){
        model.addAttribute("user" , new Users());
        return "/login";
    }

    @GetMapping("/home")
    public String getHOme(){
        return "/index";
    }

    @GetMapping("/index")
    public String getIndex(Model model, @Validated @ModelAttribute("user") Users user){


        return "/user/index";
    }

    @GetMapping("/about")
    public String getAbout(Model model){


        return "/user/about";
    }
    @GetMapping("/myAccount")
    public String getMyAccount(Model model){
        return "/user/my-account";
    }
    @GetMapping("/checkout")
    public String getCheckout(){
        return "/user/checkout";
    }


    @GetMapping("/shop")
    public String getShop(Model model,
                             @RequestParam(name = "pageSize", defaultValue = "9") Integer pageSize,
                             @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum){
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<Product> page = productRepository.findAll(pageable);


        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("list", page.getContent());

        return "/user/shop";
    }

    @GetMapping("/cart")
    public String getContact(Model model){
        model.addAttribute("user" , new Users());
        return "/user/cart";
    }

    @GetMapping("/register")
    public String getRegister(Model model){
        model.addAttribute("user" , new Users());
        return "/register";
    }
    @PostMapping({"/check"})
    public String checkLogin(Model model,
                             @Validated @ModelAttribute("user") Users user,
                             BindingResult bindingResult,
                             HttpSession session,
                             RedirectAttributes attributes){
        if (bindingResult.hasErrors()){
            return "/login";
        }
        Users userFromDB = usersRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (userFromDB != null){
            if(userFromDB.getIsAdmin().equalsIgnoreCase("admin")){
            session.setAttribute("userLogged", userFromDB);
            return "redirect:/admin/dashboard";} else {
                return "/user/index";
            }
        }
        model.addAttribute("message", "Failed -.-");
        return "/login";
    }
    @GetMapping("/logout")
    public String logOut(Model model){
        return "/index";
    }
}
