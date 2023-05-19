package com.ssafy.greenplate.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.ssafy.greenplate.model.dto.Coupon;

class CommentServiceTest extends AbstractServiceTest {
    private Integer productId = 1;
    private Coupon comment = new Coupon("id 01", productId, 5.0, new Date().toString());

    @Test
    @Order(1)
    void addCommentTest() {
        cService.addComment(comment);
        List<Coupon> comments = cService.selectByProduct(productId);
        last = comments.get(0);
        assertEquals(last.getComment(), comment.getComment());
    }

    static Coupon last;

    @Test
    @Order(2)
    void updateCommentTest() {
        System.out.println(last);
        last.setComment("더 좋다");
        cService.updateComment(last);

        Coupon selected = cService.selectComment(last.getId());
        assertEquals(selected.getComment(), last.getComment());
    }

    @Test
    @Order(3)
    void removeCommentTest() {
        Coupon selected = cService.selectComment(last.getId());

        cService.removeComment(selected.getId());
    }

}
