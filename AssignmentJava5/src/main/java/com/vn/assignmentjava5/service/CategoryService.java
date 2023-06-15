package com.vn.assignmentjava5.service;


import com.vn.assignmentjava5.entities.Category;
import com.vn.assignmentjava5.entities.Users;
import com.vn.assignmentjava5.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategory(){return categoryRepository.findAll();}

    public void saveCate(Category category){
        categoryRepository.save(category);
    }

    public void deleteCate(Integer id){
        categoryRepository.deleteById(id);
    }

    public Optional<Category> findCateById(Integer id){
        return categoryRepository.findById(id);
    }
}
