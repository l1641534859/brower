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
    public List<TransactionListDTO> getRecentTransactionsById(@RequestParam(defaultValue = "2") Integer blockchainId,@RequestParam(defaultValue = "5") Integer page2){

        List<Transaction_detail> list=miscService.getRecentTransactionsById();
        List<Transaction> transactionList=miscService.getTransactionsAll(page2);
        List<TransactionListDTO> transactionlist = transactionList.stream().map(block -> {
            TransactionListDTO blockListDTO = new TransactionListDTO();
            for (Transaction_detail t:list) {
                if(t.getTxid().equals(block.getTxid())) {
                    blockListDTO.setAmount(t.getAmount());
                    blockListDTO.setTime(block.getTime().getTime());
                    blockListDTO.setTxhash(block.getTxhash());
                    blockListDTO.setTxid(block.getTxid());
                }
            }
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
