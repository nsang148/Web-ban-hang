package com.vn.assignmentjava5.service.Impl;

import com.vn.assignmentjava5.entities.Cartitem;
import com.vn.assignmentjava5.service.ShoppingCartService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    Map<Integer, Cartitem> maps = new HashMap<>();
    @Override
    public void add(Cartitem item){
        Cartitem cartitem = maps.get(item.getProductId());
        if (cartitem == null){
            maps.put(item.getProductId(), item);
        }else {
            cartitem.setQut(cartitem.getQut()+1);
        }
    }
    @Override
    public void remove(int id){
        maps.remove(id);
    }
    @Override
    public Cartitem update(int proD,int qty){
        Cartitem cartitem = maps.get(proD);
        cartitem.setQut(qty);
        return cartitem;
    }
    @Override
    public void clear(){
        maps.clear();
    }
    @Override
    public Collection<Cartitem> getAllItems(){
        return maps.values();
    }
    @Override
    public int getCount(){
        return maps.values().size();
    }
    @Override
    public double getAmount(){
        return maps.values().stream()
                .mapToDouble(item -> item.getQut() * item.getPrice())
                .sum();
    }
}
