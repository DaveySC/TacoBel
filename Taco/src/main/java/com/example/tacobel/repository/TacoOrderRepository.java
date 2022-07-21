package com.example.tacobel.repository;

import com.example.tacobel.entity.TacoOrder;
import com.example.tacobel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TacoOrderRepository extends JpaRepository<TacoOrder, Long> {

    List<TacoOrder> findTacoOrdersByUser(User user);
}
