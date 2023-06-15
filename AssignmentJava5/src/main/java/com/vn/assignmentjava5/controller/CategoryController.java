package com.vn.assignmentjava5.controller;


import com.vn.assignmentjava5.entities.Category;
import com.vn.assignmentjava5.entities.Product;
import com.vn.assignmentjava5.entities.Users;
import com.vn.assignmentjava5.repository.CategoryRepository;
import com.vn.assignmentjava5.repository.ProductRepository;
import com.vn.assignmentjava5.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/crudCate")
    public String adminCatePage(Model model) {
        List<Category> cateList = categoryService.getAllCategory();
        model.addAttribute("list", cateList);
        return "admin/crudCategories";
    }

    @GetMapping(value = "/addCate")
    public String addCate(Model model){
        model.addAttribute("categories", new Category());
        return "redirect:/crudCate";
    }

    @GetMapping("/editCate/{id}")
    public String showUpdateCateForm(@PathVariable("id") Integer id, Model model) {
        Category cate = categoryService.findCateById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid cate Id:" + id));

        model.addAttribute("cate", cate);
        return "admin/editCategories";
    }

    @RequestMapping(value = "saveCate", method = RequestMethod. POST)
    public String saveCate (@Validated Category cate, BindingResult bindingResult, RedirectAttributes model) {
        if (bindingResult.hasErrors()) {
            return "admin/crudCategories";
        } else{
            categoryService.saveCate(cate);
            model. addFlashAttribute("success", "Thêm moi tthành cong!");
            return "redirect:/crudCate";
        }}
    @PostMapping("/update/{id}")
    public String updateCate(@PathVariable("id") Integer id, @Validated Category cate,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            cate.setId(id);
            return "admin/editCategories";
        }

        categoryService.saveCate(cate);
        return "redirect:/crudCate";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteCate (@RequestParam(name = "id") Integer cateId, RedirectAttributes model) {
        categoryService.deleteCate(cateId);
        model.addFlashAttribute("success", "Xóa thành công !");
        return "admin/crudCategories";
    }
}
