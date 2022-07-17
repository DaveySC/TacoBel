package com.example.tacobel.repository;

import com.example.tacobel.entity.TacoOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoOrderRepository extends JpaRepository<TacoOrder, Long> {
}
