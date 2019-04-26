package com.example.demo.controller;

import com.example.demo.dto.AddressInfo;
import com.example.demo.dto.BlockDetailDTO;
import com.example.demo.dto.TransactionInBlockDTO;
import com.example.demo.po.Transaction;
import com.example.demo.po.Transaction_detail;
import com.example.demo.servicer.MiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private MiscService miscService;
    @GetMapping("/getAddressInfo")
    public AddressInfo getAddressInfo(@RequestParam String address){
        return null;
    }

    @GetMapping("/getAddressTransactions")
    public List<Transaction_detail> getAddressTransactions(@RequestParam String address, @RequestParam(required = false, defaultValue = "1") Integer pageNum){
        List<Transaction_detail> transactionDetails = miscService.selectByAddress(address);
        return transactionDetails;
    }
}
