package com.ssafy.greenplate.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.greenplate.model.dao.StoreDao;
import com.ssafy.greenplate.model.dto.Store;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreDao sDao;

    @Override
    public List<Store> selectStore() {
        return sDao.selectStore();
    }

}
