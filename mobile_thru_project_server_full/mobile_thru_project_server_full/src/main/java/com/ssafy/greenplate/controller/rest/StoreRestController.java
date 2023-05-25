package com.ssafy.greenplate.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.greenplate.model.dto.Store;
import com.ssafy.greenplate.model.service.StoreService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rest/store")
@CrossOrigin("*")
public class StoreRestController {
    @Autowired
    StoreService sService;

    @GetMapping()
    @ApiOperation(value = "가게의 목록을 반환한다.", response = List.class)
    public List<Store> selectStore() {
        return sService.selectStore();
    }

}
