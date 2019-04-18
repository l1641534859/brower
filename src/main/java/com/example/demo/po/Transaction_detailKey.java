package com.example.demo.po;

public class Transaction_detailKey {
    private String txid;

    private String address;

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid == null ? null : txid.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}