package com.example.demo.controller;

import com.example.demo.dto.TransactionInfoDTO;
import com.example.demo.dto.TransactionListDTO;
import com.example.demo.po.Transaction;
import com.example.demo.po.Transaction_detail;
import com.example.demo.servicer.MiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transaction")
@CrossOrigin
public class TransactionController {
    @Autowired
    private MiscService miscService;
    @GetMapping("/getRecentTransactionsById")
    public List<TransactionListDTO> getRecentTransactionsById(@RequestParam(defaultValue = "2") Integer blockchainId){

        List<Transaction_detail> list=miscService.getRecentTransactionsById();
        List<Transaction> transactionList=miscService.getTransactionsAll();
        List<TransactionListDTO> transactionlist = list.stream().map(block -> {
            TransactionListDTO blockListDTO = new TransactionListDTO();
            blockListDTO.setAmount(block.getAmount());
            for (Transaction t:transactionList) {
                if(t.getTxid().equals(block.getTxid())) {
                    blockListDTO.setTime(t.getTime().getTime());
                }
            }
            blockListDTO.setTxhash(block.getAddress());
            blockListDTO.setTxid(block.getTxid());
            return blockListDTO;
        }).collect(Collectors.toList());
        return transactionlist;
    }

    @GetMapping("/getRecentTransactionsByNameType")
    public List<TransactionListDTO> getRecentTransactionsByNameType(@RequestParam String name,
                                                                    @RequestParam String type){
        return null;
    }

    @GetMapping("/getTransactionInfoByTxid")
    public TransactionInfoDTO getTransactionInfoByTxid(@RequestParam String txid){

        return null;
    }

    @GetMapping("/getTransactionInfoByTxhash")
    public TransactionInfoDTO getTransactionInfoByTxhash(@RequestParam String txhash){

        return null;
    }

}
