package com.minidwep.springcloud.serivce.impl;


import com.minidwep.springcloud.dao.StorageDao;
import com.minidwep.springcloud.serivce.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageDao storageDao;


    @Override
    public void decrease(Long productId, Integer count) {
        storageDao.decrease(productId, count);
    }
}