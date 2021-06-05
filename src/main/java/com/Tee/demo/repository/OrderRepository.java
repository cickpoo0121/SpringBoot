package com.Tee.demo.repository;

import com.Tee.demo.entity.CustomerEntity;
import com.Tee.demo.entity.OrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Integer> {

    public List<OrderEntity> findByOrderName(String name);

    @Query(value = "SELECT * FROM `orders` WHERE substring(order_date,1,4) BETWEEN ?1 and ?2",nativeQuery = true)
    public List<OrderEntity>findOrdersBetweenYears(String yearStart,String yearEnd);

    public List<OrderEntity> findOrderDateBetween(Date startDate, Date endDate);

}
