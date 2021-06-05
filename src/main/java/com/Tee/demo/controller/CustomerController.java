package com.Tee.demo.controller;

import com.Tee.demo.entity.CustomerEntity;
import com.Tee.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping()
    public ResponseEntity<?> createCustomer(@RequestBody CustomerEntity customerObj){
       Optional<CustomerEntity> customerOptional= Optional.ofNullable(customerService.createCustomer(customerObj));
       return ResponseEntity.status(HttpStatus.CREATED).body(customerOptional);
    }

    @PutMapping
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerEntity customerUpdate){
        Optional<CustomerEntity> customerOptional = Optional.ofNullable(customerService.updateCustomer((customerUpdate)));
        if(customerOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(customerOptional);
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping
    public ResponseEntity<?> finAllCustomer(){
//        List<CustomerEntity> customerList=customerService.findAllCustomer();
//        return ResponseEntity.status(HttpStatus.OK).body(customerList);
        return  ResponseEntity.status(HttpStatus.OK).body(
                customerService.findAllCustomer());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int id){
        boolean status = customerService.deleteCusteomer(id);
        if(status==true){
            return  ResponseEntity.status(HttpStatus.OK).build() ;
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).build() ;

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findCustomerById(@PathVariable int id){
        Optional<CustomerEntity> customerOptional = customerService.findById(id);
        if(customerOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(customerOptional);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(path = "/name/{name}")
    public ResponseEntity<?> findCustomerByName(@PathVariable String name){
        List<CustomerEntity> customerList = customerService.findCustomerByName(name);
        if(customerList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(customerList);
    }

    @GetMapping(path = "/address/{address}")
    public ResponseEntity<?> findByAddress(@PathVariable String address){
        List<CustomerEntity> customerAddress=customerService.findByAddress(address);
        if(customerAddress.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND");
        }
        return ResponseEntity.status(HttpStatus.OK).body(customerAddress);
    }

    @GetMapping(path = "/amount")
    public ResponseEntity<?> customerAmount(){
      return ResponseEntity.status(HttpStatus.OK).body(customerService.customerAmount());
    }




















}
