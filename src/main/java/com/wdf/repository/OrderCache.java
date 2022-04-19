package com.wdf.repository;

import com.wdf.entity.Order;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class OrderCache {

    private final ConcurrentMap<Integer, Order> orderMap;

    protected OrderCache(){
        orderMap = new ConcurrentHashMap<>();
    }

    public Order putIfAbsent(Integer clientId, Order order){
       return orderMap.putIfAbsent(clientId, order);
    }

    public Order get(Integer clientId){
        return orderMap.get(clientId);

    }

    public Boolean contains(Integer clientId){
        return orderMap.containsKey(clientId);
    }

    public Order delete(Integer clientId){
        return orderMap.remove(clientId);
    }

    public List<Order> getAllOrders(){
        return new ArrayList<>(orderMap.values());
    }

    public int size(){
        return orderMap.size();
    }


}
