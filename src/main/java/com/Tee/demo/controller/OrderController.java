package com.Tee.demo.controller;

import com.Tee.demo.entity.CustomerEntity;
import com.Tee.demo.entity.OrderEntity;
import com.Tee.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping()
    public ResponseEntity<?> createOrder(@RequestBody OrderEntity oderObj){
        Optional<OrderEntity> oderOptional = Optional.ofNullable(orderService.createOrder(oderObj));
        return ResponseEntity.status(HttpStatus.CREATED).body(oderOptional);
    }

    @PutMapping
    public ResponseEntity<?> updateOrder(@RequestBody OrderEntity orderObj){
        Optional<OrderEntity> oderOptional = Optional.ofNullable(orderService.updateOrder(orderObj));

        if(oderOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(oderOptional);
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping
    public ResponseEntity<?> findAllOrder(){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findAllOrder());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteOrderById(@PathVariable int id){
        boolean status = orderService.deleteOrder(id);
        if(status==true){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findOrderId(@PathVariable int id){
        Optional<OrderEntity> orderOption = orderService.findOderById(id);
        if(orderOption.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(orderOption);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(path = "/name/{name}")
    public ResponseEntity<?> findCustomerByName(@PathVariable String name){
        List<OrderEntity> customerList = orderService.findCustomerByName(name);
        if(customerList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(customerList);
    }

    @GetMapping(path = "/year/{yearStart}/{yearEnd}")
    public ResponseEntity<?> findOrderByYears(@PathVariable @DateTimeFormat(pattern = "yyyy") String yearStart,
            @PathVariable @DateTimeFormat(pattern = "yyyy" )String yearEnd){
        List<OrderEntity> orderList = orderService.findOrderBetwwenYears(yearStart,yearEnd);
        if (orderList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(orderList);
    }

    @GetMapping(path = "/amount/{yearStart}/{yearEnd}")
    public ResponseEntity<?> findOrderAmountByOrderDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date yearStart,
                                              @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd" ) Date yearEnd){

        return ResponseEntity.status(HttpStatus.OK).body(orderService.findAmountOrder(yearStart,yearEnd));
    }



}
