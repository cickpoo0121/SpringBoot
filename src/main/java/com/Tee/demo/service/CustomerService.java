package com.Tee.demo.service;

import com.Tee.demo.entity.CustomerEntity;
import com.Tee.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public CustomerEntity createCustomer(CustomerEntity customerObj){
        try{
            return customerRepository.save(customerObj);
        }catch (Exception e){
            throw e;
        }
    }

    public CustomerEntity updateCustomer(CustomerEntity customerUpdate){
        try {
            Optional<CustomerEntity> customerOptional = customerRepository.findById(customerUpdate.getCustomerId());
            if(customerOptional.isPresent()){
                return customerRepository.save(customerUpdate);
            }
            return null; //else case
        }catch (Exception e){
            throw e;
        }
    }

    public List<CustomerEntity> findAllCustomer(){
        return (List<CustomerEntity>) customerRepository.findAll();
    }

    public boolean deleteCusteomer(int customerId){
        try {
            customerRepository.deleteById(customerId);
            return true;
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }

    public Optional<CustomerEntity> findById (int customerId){
        try{
            return customerRepository.findById(customerId);
        }catch (Exception e){
            throw e;
        }
    }

    public List<CustomerEntity> findCustomerByName(String name){
        try {
            return customerRepository.findByCustomerName(name);

        }catch (Exception e){
            throw e;
        }
    }

    public List<CustomerEntity> findByAddress(String address){
        try {
            return customerRepository.findAddress(address);
        }catch (Exception e){
            throw e;
        }
    }

    public Integer customerAmount(){
        List<CustomerEntity> allCustomer= ((List<CustomerEntity>)customerRepository.findAll());
        return allCustomer.size();

    }


















}
