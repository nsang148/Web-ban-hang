package com.vn.assignmentjava5.controller;


import com.vn.assignmentjava5.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = { "/dashboard", ""})
    public String dashboard() {
        return "/admin/index3";
    }
}
