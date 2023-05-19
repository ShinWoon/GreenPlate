package com.ssafy.greenplate.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.greenplate.model.dto.Product;

public interface ProductService {
    /**
     * 모든 상품 정보를 반환한다.
     * 
     * @return
     */
    List<Product> getProductList();

    /**
     * id에 해당하는 상품 정보를 반환한다.
     * 
     * @param productId
     * @return
     */
    Product getProduct(Integer productId);

}
