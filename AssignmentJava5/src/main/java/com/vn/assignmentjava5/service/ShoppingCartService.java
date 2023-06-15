package com.vn.assignmentjava5.service;

import com.vn.assignmentjava5.entities.Cartitem;

import java.util.Collection;

public interface ShoppingCartService {
    public void add(Cartitem item);
    public void remove(int id);

    public Cartitem update(int proD,int qty);
    public void clear();
    public Collection<Cartitem> getAllItems();
    public int getCount();
    public double getAmount();
}
