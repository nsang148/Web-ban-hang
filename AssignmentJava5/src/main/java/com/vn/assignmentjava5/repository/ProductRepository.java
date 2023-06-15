package com.vn.assignmentjava5.repository;


import com.vn.assignmentjava5.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "Select p from Product p where p.category.id =:cate")
    public List<Product> findAllByCategoryId(@Param("cate") Integer categoryId);


}
