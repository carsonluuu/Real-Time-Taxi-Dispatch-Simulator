package com.taxi.DispatchSimulator;

import com.taxi.DispatchSimulator.object.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

}