package com.example.demo.mapper;

import com.example.demo.po.Blockchain;

public interface BlockchainMapper {
    int deleteByPrimaryKey(Integer blockchainId);

    int insert(Blockchain record);

    int insertSelective(Blockchain record);

    Blockchain selectByPrimaryKey(Integer blockchainId);

    int updateByPrimaryKeySelective(Blockchain record);

    int updateByPrimaryKey(Blockchain record);
    void delAll();
}