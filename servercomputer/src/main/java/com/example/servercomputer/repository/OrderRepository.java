package com.example.servercomputer.repository;

import com.example.servercomputer.entity.Order;
import com.example.servercomputer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> getOrderByUser(User user);

//    @Query(value = "SELECT orders.ngaydat, sum(order_detail.detail_price) as money FROM orders inner join order_detail on orders.id = order_detail.order_id \n" +
//            "WHERE DATE(orders.ngaydat) BETWEEN \"2022-12-07\" AND \"2022-12-12\"\n" +
//            "GROUP by ngaydat order by ngaydat", nativeQuery = true)
    @Query(value = "SELECT orders.ngaydat, sum(order_detail.detail_price) as money FROM `orders` inner join order_detail on orders.id = order_detail.order_id \n" +
            "WHERE DATE(orders.ngaydat) BETWEEN :startDate AND :endDate\n" +
            "GROUP by ngaydat order by ngaydat\n" +
            "\n", nativeQuery = true)
    List<Object[]> getReportbyDate(@Param(value = "startDate") String startDate, @Param(value = "endDate") String endDate);
}
