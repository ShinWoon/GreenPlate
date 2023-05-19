package com.ssafy.greenplate.model.dao;

import java.util.List;

import com.ssafy.greenplate.model.dto.Order;
import com.ssafy.greenplate.model.dto.OrderDetail;

public interface OrderDetailDao {
    int insert(OrderDetail detail);

    int delete(Integer detailId);

    OrderDetail select(Integer detailId);

    List<OrderDetail> selectAll();
}
