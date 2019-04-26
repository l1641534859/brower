package com.example.demo.servicer;

import com.example.demo.dto.BlockDetailDTO;
import com.example.demo.dto.TransactionListDTO;
import com.example.demo.po.Block;
import com.example.demo.po.Transaction;
import com.example.demo.po.Transaction_detail;

import java.util.List;

public interface MiscService {

    void importFromHeight(Integer blockHeight, Boolean isClean);

    void importFromHash(String blockHash, Boolean isClean) throws Throwable;

    List<Transaction_detail> selectByAddress(String address);

    List<Block> selectRecent();

    List<Transaction_detail> getRecentTransactionsById();

    List<Transaction> getTransactionsAll();

    BlockDetailDTO getBlockByAddress(String address);
}
