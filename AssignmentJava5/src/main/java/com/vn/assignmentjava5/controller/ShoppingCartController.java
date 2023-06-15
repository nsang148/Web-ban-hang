package com.vn.assignmentjava5.controller;

import com.vn.assignmentjava5.entities.Cartitem;
import com.vn.assignmentjava5.entities.Product;
import com.vn.assignmentjava5.service.ProductService;
import com.vn.assignmentjava5.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("shopping-cart")
public class ShoppingCartController {
    @Autowired
    ProductService productService;
    @Autowired
    ShoppingCartService shoppingCartService;

    @GetMapping("views")
    public String viewCart(Model model){
        model.addAttribute("CartItems", shoppingCartService.getAllItems());
        model.addAttribute("TOTAL", shoppingCartService.getAmount());
        return "user/cart";
    }
    @GetMapping("addCart/{id}")
    public String addCart(@PathVariable("id") Integer id,  Model model){
        Optional<Product> product = productService.findProById(id);
        if (product != null){
            Cartitem cartitem = new Cartitem();
            cartitem.setProductId(product.get().getId());
            cartitem.setImage(product.get().getProductImage());
            cartitem.setName(product.get().getProductName());
            cartitem.setPrice(product.get().getPrice());
            cartitem.setQut(1);
            shoppingCartService.add(cartitem);
        }
        return "redirect:/shopping-cart/views";
    }
    @GetMapping("clear-cart")
    public String clearCart(){
        shoppingCartService.clear();
        return "redirect:/shopping-cart/views";
    }
    @GetMapping("removeCart/{id}")
    public String clearCart(@PathVariable("id") Integer id){
        shoppingCartService.remove(id);
        return "redirect:/shopping-cart/views";
    }
    @PostMapping("updateCart")
    public String  update(@RequestParam("id")int id,@RequestParam("qty")Integer qty){
        shoppingCartService.update(id,qty);
        return "redirect:/shopping-cart/views";
    }
}
