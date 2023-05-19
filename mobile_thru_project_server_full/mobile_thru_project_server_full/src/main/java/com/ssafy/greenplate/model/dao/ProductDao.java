package com.ssafy.greenplate.model.dao;

import java.util.List;
import java.util.Map;

import com.ssafy.greenplate.model.dto.Product;

public interface ProductDao {
    Product select(Integer productId);

    List<Product> selectAll();
}
