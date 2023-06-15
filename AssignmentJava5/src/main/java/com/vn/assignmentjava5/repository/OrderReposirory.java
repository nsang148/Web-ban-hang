package com.vn.assignmentjava5.repository;

import com.vn.assignmentjava5.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderReposirory extends JpaRepository<Orders,Integer> {
}
