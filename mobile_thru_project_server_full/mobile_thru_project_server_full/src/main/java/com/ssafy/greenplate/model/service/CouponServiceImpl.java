package com.ssafy.greenplate.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.greenplate.model.dao.CouponDao;
import com.ssafy.greenplate.model.dto.Coupon;

/**
 * @author itsmeyjc
 * @since 2021. 6. 23.
 */
@Service

public class CouponServiceImpl implements CouponService {

    @Autowired
    CouponDao cDao;

    @Override
    @Transactional
    public void addCoupon(Coupon coupon) {
        cDao.insert(coupon);
    }

    @Override
    @Transactional
    public void removeCoupon(Integer couponId) {
        cDao.delete(couponId);

    }

    @Override
    public List<Coupon> selectAllCoupons(String userId) {
        return cDao.selectAll(userId);
    }

    @Override
    public Coupon selectCoupon(Integer id) {
        return cDao.select(id);
    }

}
