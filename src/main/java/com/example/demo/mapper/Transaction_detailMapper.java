package com.example.demo.mapper;

import com.example.demo.po.Transaction_detail;
import com.example.demo.po.Transaction_detailKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Transaction_detailMapper {
    int deleteByPrimaryKey(Transaction_detailKey key);

    int insert(Transaction_detail record);

    int insertSelective(Transaction_detail record);

    Transaction_detail selectByPrimaryKey(Transaction_detailKey key);

    int updateByPrimaryKeySelective(Transaction_detail record);

    int updateByPrimaryKey(Transaction_detail record);
    List<Transaction_detail> selectByAddress(@Param("address") String address);
    List<Transaction_detail> selectAll();
}