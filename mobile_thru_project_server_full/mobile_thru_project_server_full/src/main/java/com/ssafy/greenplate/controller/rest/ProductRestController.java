package com.ssafy.greenplate.controller.rest;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.greenplate.model.dto.Product;
import com.ssafy.greenplate.model.service.ProductService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rest/product")
@CrossOrigin("*")
public class ProductRestController {
    @Autowired
    ProductService pService;

    @GetMapping()
    @ApiOperation(value = "전체 상품의 목록을 반환한다.", response = List.class)
    public List<Product> getProductList() {
        return pService.getProductList();
    }

    @GetMapping("/{productId}")
    @ApiOperation(value = "{productId}에 해당하는 상품의 정보반환한다.")
    public Product getProduct(@PathVariable Integer productId) {
        return pService.getProduct(productId);
    }

    @GetMapping("/topThreeMenu")
    @ApiOperation(value = "가장 주문량이 많은 메뉴 3가지에 해당하는 상품의 정보를 반환한다.")
    public List<Product> selectTop3Menu() {
        return pService.selectTop3Menu();
    }
}
