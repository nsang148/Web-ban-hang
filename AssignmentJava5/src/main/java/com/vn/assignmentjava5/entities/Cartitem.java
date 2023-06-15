package com.vn.assignmentjava5.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cartitem {
    private int productId;
    private String image;
    private String name;
    private double price;
    private int qut=1;
}
