package com.example.demo.dto;


import com.example.demo.po.Transaction_detail;

import java.sql.Date;
import java.util.List;

public class TransactionInBlockDTO {
    private String txid;

    private String txhash;

    private Long size;

    public List<Transaction_detail> getTransactionlist() {
        return transactionlist;
    }

    public void setTransactionlist(List<Transaction_detail> transactionlist) {
        this.transactionlist = transactionlist;
    }

    private Date time;
    private List<Transaction_detail> transactionlist;
    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public String getTxhash() {
        return txhash;
    }

    public void setTxhash(String txhash) {
        this.txhash = txhash;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}


