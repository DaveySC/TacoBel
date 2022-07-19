package com.example.deliveryservice.repository;

import com.example.deliveryservice.entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourierRepository extends JpaRepository<Courier, Long> {

    @Query(value = "select * from Courier where COUNT_OF_MAILS  = (select min(COUNT_OF_MAILS ) from Courier) Limit 0, 1",
            nativeQuery = true)
    Courier findCourierWithMinMails();


}
