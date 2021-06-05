package com.Tee.demo.service;

import com.Tee.demo.entity.CustomerEntity;
import com.Tee.demo.entity.OrderEntity;
import com.Tee.demo.repository.OrderRepository;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public OrderEntity createOrder(OrderEntity orderObj){
        try {
            orderObj.setOrderReceivedDate(addDays(orderObj.getOrderDate(), 3));
            return orderRepository.save(orderObj);
        }catch (Exception e){
            throw e;
        }
    }

    public OrderEntity updateOrder(OrderEntity orderUpdate){
        try {
            Optional<OrderEntity> orderOptional = orderRepository.findById(orderUpdate.getOrderId());
            if(orderOptional.isPresent()){
                orderUpdate.setOrderReceivedDate(addDays(orderUpdate.getOrderDate(), 3));
                return orderRepository.save(orderUpdate);
            }
            return null; //else case
        }catch (Exception e){
            throw e;
        }
    }

    public List<OrderEntity> findAllOrder(){
        return  (List<OrderEntity>) orderRepository.findAll();
    }

    public boolean deleteOrder(int orderId){
        try {
            orderRepository.deleteById(orderId);
            return true;
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }

    public Optional<OrderEntity> findOderById(int orderId){
        try {
            return orderRepository.findById(orderId);
        }catch (Exception e){
            throw e;
        }
    }

    public List<OrderEntity> findCustomerByName(String name){
        try {
            return orderRepository.findByOrderName(name);

        }catch (Exception e){
            throw e;
        }
    }

    public List<OrderEntity> findOrderBetwwenYears(String yearStart, String yearEnd){
        try {
            return orderRepository.findOrdersBetweenYears(yearStart,yearEnd);
        }catch (Exception e){
            throw e;
        }
    }

    public Object findAmountOrder(Date startDate, Date endDate){
        try {
            List<OrderEntity> orderList=orderRepository.findOrderDateBetween(startDate,endDate);

            HashMap<String,Integer> orderAmount = new HashMap<String,Integer>();
            orderAmount.put("amount",orderList.size());
            return orderAmount;
        }catch (Exception e){
            throw e;
        }
    }

    public Date addDays(Date date, int days){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE,days);
        return cal.getTime();
    }
}
