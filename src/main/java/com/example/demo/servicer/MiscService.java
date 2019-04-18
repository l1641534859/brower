package com.example.demo.servicer;

public interface MiscService {

    void importFromHeight(Integer blockHeight, Boolean isClean);

    void importFromHash(String blockHash, Boolean isClean);
}
