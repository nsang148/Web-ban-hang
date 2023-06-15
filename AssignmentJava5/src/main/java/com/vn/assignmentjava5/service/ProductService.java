package com.vn.assignmentjava5.service;


import com.vn.assignmentjava5.entities.Product;
import com.vn.assignmentjava5.entities.Users;
import com.vn.assignmentjava5.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getListProducts(){return productRepository.findAll();}


    public Product savePro(Product product){
        return productRepository.save(product);
    }

    public void deletePro(Integer id){
        productRepository.deleteById(id);
    }

    public Optional<Product> findProById(Integer id){
        return productRepository.findById(id);
    }
}
