package com.example.demo.mapper;

import com.example.demo.po.Transaction;
import com.github.pagehelper.Page;

import java.util.List;

public interface TransactionMapper {
    int deleteByPrimaryKey(String txid);

    int insert(Transaction record);

    int insertSelective(Transaction record);

    Transaction selectByPrimaryKey(String txid);

    int updateByPrimaryKeySelective(Transaction record);

    int updateByPrimaryKey(Transaction record);
    List<Transaction> selectByAddress(String address);
    Page selectAll();
}