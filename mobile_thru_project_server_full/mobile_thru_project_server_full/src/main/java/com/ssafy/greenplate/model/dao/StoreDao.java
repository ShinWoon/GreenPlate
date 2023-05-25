package com.ssafy.greenplate.model.dao;

import java.util.List;

import com.ssafy.greenplate.model.dto.Store;

public interface StoreDao {
    List<Store> selectStore();
}
