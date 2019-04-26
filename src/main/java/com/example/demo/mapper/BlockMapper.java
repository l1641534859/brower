package com.example.demo.mapper;

import com.example.demo.po.Block;

import java.util.List;

public interface BlockMapper {
    int deleteByPrimaryKey(String blockhash);

    int insert(Block block);

    int insertSelective(Block record);

    Block selectByPrimaryKey(String address);

    int updateByPrimaryKeySelective(Block record);

    int updateByPrimaryKey(Block record);

    List<Block> selectAll();
}