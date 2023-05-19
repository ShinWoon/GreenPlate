package com.ssafy.greenplate.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.greenplate.model.dto.Coupon;
import com.ssafy.greenplate.model.service.CouponService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rest/coupon")
@CrossOrigin("*")
public class CouponRestController {

    @Autowired
    CouponService cService;

    @PostMapping
    @Transactional
    @ApiOperation(value = "coupon 객체를 추가한다.", response = Boolean.class)
    public Boolean insert(@RequestBody Coupon coupon) {
        cService.addCoupon(coupon);
        return true;
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ApiOperation(value = "{id}에 해당하는 사용자 정보를 삭제한다.", response = Boolean.class)
    public Boolean delete(@PathVariable Integer id) {
        cService.removeCoupon(id);
        return true;
    }

    // 쿠폰 정보 하나만 select

    // 쿠폰 정보를 모두 return

}
