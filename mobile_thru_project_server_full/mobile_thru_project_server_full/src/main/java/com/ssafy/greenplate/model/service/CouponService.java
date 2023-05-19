package com.ssafy.greenplate.model.service;

import java.util.List;

import com.ssafy.greenplate.model.dto.Coupon;

public interface CouponService {
    /**
     * Coupon을 등록한다.
     * 
     * @param coupon
     */
    void addCoupon(Coupon coupon);

    /**
     * id에 해당하는 Coupon을 반환한다.
     * 
     * @param id
     * @return
     */
    Coupon selectCoupon(Integer id);

    /**
     * id에 해당하는 Coupon을 삭제한다.
     * 
     * @param id
     */
    void removeCoupon(Integer id);

    /**
     * Coupon 목록읅 반환한다.
     * 
     * @param id
     * @return
     */
    List<Coupon> selectAllCoupons(String userId);
}
