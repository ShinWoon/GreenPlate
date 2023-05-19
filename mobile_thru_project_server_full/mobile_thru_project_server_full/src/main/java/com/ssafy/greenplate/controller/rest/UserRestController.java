package com.ssafy.greenplate.controller.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.greenplate.model.dto.Order;
import com.ssafy.greenplate.model.dto.User;
import com.ssafy.greenplate.model.service.OrderService;
import com.ssafy.greenplate.model.service.StampService;
import com.ssafy.greenplate.model.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rest/user")
@CrossOrigin("*")
public class UserRestController {

    @Autowired
    UserService uService;

    @Autowired
    StampService sService;

    @Autowired
    OrderService oService;

    @PostMapping
    @ApiOperation(value = "사용자 정보를 추가한다.", response = Boolean.class)
    public Boolean insert(@RequestBody User user) {
        uService.join(user);
        return true;
    }

    @GetMapping("/isUsed")
    @ApiOperation(value = "request parameter로 전달된 id가 이미 사용중인지 반환한다.", response = Boolean.class)
    public Boolean isUsedId(String id) {
        return uService.isUsedId(id);
    }

    @PostMapping("/login")
    @ApiOperation(value = "로그인 처리 후 성공적으로 로그인 되었다면 loginId라는 쿠키를 내려보낸다.", response = User.class)
    public User login(@RequestBody User user, HttpServletResponse response) throws UnsupportedEncodingException {
        User selected = uService.login(user.getId(), user.getPass());
        if (selected != null) {
            Cookie cookie = new Cookie("loginId", URLEncoder.encode(selected.getId(), "utf-8"));
            cookie.setMaxAge(1000 * 1000);
            response.addCookie(cookie);
        } else {
            selected = new User();
        }
        return selected;
    }

    @PostMapping("/info")
    @ApiOperation(value = "사용자의 정보와 함께 사용자의 주문 내역, 사용자 등급 정보를 반환한다.", response = Map.class)
    public Map<String, Object> getInfo(String id) {
        User selected = uService.selectUser(id);
        if (selected == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("user", new User());
            return map;
        } else {
            Map<String, Object> info = new HashMap<>();
            info.put("user", selected);
            List<Order> orders = oService.getOrdreByUser(selected.getId());
            info.put("order", orders);
            info.put("grade", getGrade(selected.getStamps()));
            return info;
        }
    }

    public Map<String, Object> getGrade(Integer stamp) {
        Map<String, Object> grade = new HashMap<>();
        int pre = 0;
        for (Level level : levels) {
            if (level.max >= stamp) {
                grade.put("title", level.title);
                grade.put("greetings", level.greetings);
                grade.put("max", level.max);
                grade.put("img", level.img);
            }
        }
        return grade;
    }

    private List<Level> levels;

    @PostConstruct
    public void setup() {
        levels = new ArrayList<>();
        levels.add(new Level("옥수수", "억수로 귀여운", 5, 5, "corn.png"));
        levels.add(new Level("당근", "바니바니", 10, 15, "carrot.png"));
        levels.add(new Level("토마토", "멋쟁이", Integer.MAX_VALUE, Integer.MAX_VALUE, "tomato.png"));
    }

    class Level {
        private String title;
        private String greetings;
        private int unit;
        private int max;
        private String img;

        private Level(String title, String greetings, int unit, int max, String img) {
            this.title = title;
            this.greetings = greetings;
            this.unit = unit;
            this.max = max;
            this.img = img;
        }
    }
}
