package com.ssafy.greenplate.model.dao;

import java.util.List;

import com.ssafy.greenplate.model.dto.Stamp;

public interface StampDao {
    int insert(Stamp stamp);

    Stamp select(Integer stampId);

    List<Stamp> selectAll();

    List<Stamp> selectByUserId(String userId);
}
