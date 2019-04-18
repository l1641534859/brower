package com.example.demo.mapper;

import com.example.demo.po.Transaction;

public interface TransactionMapper {
    int deleteByPrimaryKey(String txid);

    int insert(Transaction record);

    int insertSelective(Transaction record);

    Transaction selectByPrimaryKey(String txid);

    int updateByPrimaryKeySelective(Transaction record);

    int updateByPrimaryKey(Transaction record);
}