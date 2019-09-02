package com.szjz.sell.repository;

import com.szjz.sell.dataobject.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author szjz
 * @date 2019/5/8 16:28
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    List<OrderDetail> findByOrderId(String orderId);

    Page<OrderDetail> findByOrderId(String orderId, Pageable pageable);
}
