package com.vn.assignmentjava5.controller;

import com.vn.assignmentjava5.entities.Product;
import com.vn.assignmentjava5.entities.Users;
import com.vn.assignmentjava5.service.ProductService;
import com.vn.assignmentjava5.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/crudPro")
    public String adminProPage(Model model) {
        List<Product> proList = productService.getListProducts();
        model.addAttribute("productList", proList);
        return "admin/crudProduct";
    }

    @GetMapping(value = "/addPro")
    public String addPro(Model model){
        model.addAttribute("product", new Product());
        return "redirect:/crudPro";
    }

    @GetMapping("/editPro/{id}")
    public String showUpdateUsersForm(@PathVariable("id") Integer id, Model model) {
        Product product = productService.findProById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid pro Id:" + id));

        model.addAttribute("pro", product);
        return "admin/editPro";
    }

    @RequestMapping(value = "savePro", method = RequestMethod. POST)
    public String savePro (@Validated Product pro, BindingResult bindingResult, RedirectAttributes model) {
        if (bindingResult.hasErrors()) {
            return "admin/crudProduct";
        } else{
            productService.savePro(pro);
            model.addFlashAttribute("success", "Thêm moi tthành cong!");
            return "redirect:/crudPro";
        }}
    @PostMapping("/updatePro/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Validated Product pro,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            pro.setId(id);
            return "admin/crudProduct";
        }

        productService.savePro(pro);
        return "redirect:/crudPro";
    }

    @RequestMapping(value = "/deletePro", method = RequestMethod.GET)
    public String deleteUser (@RequestParam(name = "id") Integer proId, RedirectAttributes model) {
        productService.deletePro(proId);
        model.addFlashAttribute("success", "Xóa thành công !");
        return "redirect:/crudPro";
    }
}
