package com.ssafy.greenplate.service;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.greenplate.model.dao.OrderDao;
import com.ssafy.greenplate.model.dao.OrderDetailDao;
import com.ssafy.greenplate.model.dao.ProductDao;
import com.ssafy.greenplate.model.dao.UserDao;
import com.ssafy.greenplate.model.dto.User;
import com.ssafy.greenplate.model.service.CouponService;
import com.ssafy.greenplate.model.service.OrderService;
import com.ssafy.greenplate.model.service.ProductService;
import com.ssafy.greenplate.model.service.StampService;
import com.ssafy.greenplate.model.service.UserService;

/**
 * @author itsmeyjc
 * @since 2021. 6. 23.
 */
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
// @Transactional
public abstract class AbstractServiceTest {

    public static User user = new User("id 01", "name 01", "pass 01", 0);

    /*
     * public static UserService userService;
     * 
     * @BeforeAll
     * static void setUpBeforeClass() throws Exception {
     * userService = UserServiceImpl.getInstance();
     * userService.join(user);
     * }
     * 
     * @AfterAll
     * static void tearDownAfterClass() throws Exception {
     * userService.leave(user.getId());
     * }
     */

    @Autowired
    public UserService userService;
    @Autowired
    public ProductService prodService;
    @Autowired
    public OrderService orderService;
    @Autowired
    public CouponService cService;
    @Autowired
    public StampService sService;

    @Autowired
    OrderDao oDao;
    @Autowired
    OrderDetailDao dDao;

}
