package com.Tee.demo.repository;

import com.Tee.demo.entity.CustomerEntity;
import com.Tee.demo.entity.OrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity,Integer> {

    public List<CustomerEntity> findByCustomerName(String name);
    public List<CustomerEntity> findByCustomerNameAndActiveFlag(String name,String flag);

    @Query(value = "SELECT * FROM `customer`WHERE address LIKE %?1% ORDER BY customer_id",nativeQuery = true)
    public List<CustomerEntity> findAddress(String address);


}
