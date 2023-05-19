package com.ssafy.greenplate.model.dao;

import java.util.List;

import com.ssafy.greenplate.model.dto.Coupon;

public interface CouponDao {
    int insert(Coupon coupon);

    int delete(Integer couponId);

    Coupon select(Integer couponId);

    List<Coupon> selectAll(String userId);
}
