package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.User;

public interface OrderRepository extends JpaRepository<Order, Long>{

	
	
}
