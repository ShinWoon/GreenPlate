package com.ssafy.greenplate.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.List;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.greenplate.model.dao.ProductDao;
import com.ssafy.greenplate.model.dto.Coupon;
import com.ssafy.greenplate.model.dto.Product;

class ProductTest extends AbstractDaoTest {

    @Test
    @Order(1)
    public void selectTest() {
        Product selected = pDao.select(1);
        assertEquals(selected.getName(), "coffee1");

    }

    // @Test
    // @Order(2)
    // public void insertTest() {
    // Product data = new Product("양파", "", "salad_topping", "", 500, "hide.png");
    // int result = pDao.insert(data);
    // assertEquals(result, 1);
    // }

    static Product last;

    @Test
    @Order(3)
    public void selectAll() {
        List<Product> result = pDao.selectAll();
        assertEquals(result.size(), 38);
        last = result.get(0);

        assertEquals(last.getType(), "yogurt_dressing");
    }

    // @Test
    // @Order(4)
    // public void updateTest() {
    // last.setType("coffee");
    // int result = pDao.update(last);
    // assertEquals(result, 1);

    // Product selected = pDao.select(last.getId());
    // assertEquals(last.getType(), selected.getType());
    // }

    // @Test
    // @Order(5)
    // public void deleteTest() {
    // int result = pDao.delete(last.getId());
    // assertEquals(result, 1);

    // Product selected = pDao.select(last.getId());
    // assertNull(selected);
    // }

}
