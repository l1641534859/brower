package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.restAPI.BitcoinApi;
import com.example.demo.restAPI.BitcoinJsonRpcClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temp")
public class TempController {

    @Autowired(required = false)
    private BitcoinApi bitcoinApi;

    @Autowired
    private BitcoinJsonRpcClient bitcoinJsonRpcClient;

    @GetMapping("/test")
    public void test() throws Throwable {
        JSONObject chainInfo = bitcoinApi.getChainInfo();
        String txhash = "f2d25c2b72cd0cc8420f4cc9f1bc46fd1f33c0882d4aff0c58446c87a8469c17";
        JSONObject transaction = bitcoinApi.getTransaction(txhash);
//        String blockhash = "000000000000004b1aecd12119a19e38efdce8c385f89a5d3a7698427108a2ee";
//        JSONObject block = bitcoinApi.getBlock(blockhash);
//        JSONObject noTxBlock = bitcoinApi.getNoTxBlock(blockhash);
//        String blockhash2 = "00000000000000a727d074e9d1cc0a225540eb40693d6aa15d702b0bd96d378b";
//        JSONArray blockHeaders = bitcoinApi.getBlockHeaders(10, blockhash2);
//        JSONObject mempoolInfo = bitcoinApi.getMempoolInfo();
//        JSONObject mempoolContents = bitcoinApi.getMempoolContents();
//        String blockHashByHeight = bitcoinJsonRpcClient.getBlockHashByHeight(1489445);
    }

    public JSONObject getblocks(String address){
        address="000000000000002e694f50a45e32043505d0d01bae572253ed48b4446bb34ce8";
        return null;
    }
}
