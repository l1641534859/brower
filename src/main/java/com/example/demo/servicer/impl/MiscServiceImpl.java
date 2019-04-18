package com.example.demo.servicer.impl;

import com.example.demo.servicer.MiscService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MiscServiceImpl implements MiscService {

    @Async
    @Override
    public void importFromHeight(Integer blockHeight, Boolean isClean) {

    }

    @Async
    @Override
    public void importFromHash(String blockHash, Boolean isClean) {

    }
}
