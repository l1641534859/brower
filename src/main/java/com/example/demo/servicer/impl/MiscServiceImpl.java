package com.example.demo.servicer.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.mapper.BlockMapper;
import com.example.demo.mapper.BlockchainMapper;
import com.example.demo.po.Block;
import com.example.demo.restAPI.BitcoinApi;
import com.example.demo.servicer.MiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;

@Service
public class MiscServiceImpl implements MiscService {
        @Autowired(required = false)
        private BitcoinApi bitcoinApi;
    @Autowired
    private BlockchainMapper blockchain;
    @Autowired
    private BlockMapper blockMapper;
    @Async
    @Override
    public void importFromHeight(Integer blockHeight, Boolean isClean) {

    }

    @Async
    @Override
    public void importFromHash(String blockHash, Boolean isClean) {
            if(isClean){
                blockchain.delAll();
            }
        String hash=blockHash;
        while(hash!=null&&!hash.isEmpty()){
            JSONObject json=bitcoinApi.getBlock(hash);
            Block block=new Block();
            block.setBlockhash(json.getString("hash"));
            block.setBlockchainId(2);
            block.setHeight(json.getInteger("height"));
            Long time=json.getLong("time");
            Date date=new Date(time*100);
            block.setTime(date);
            JSONArray tx=json.getJSONArray("tx");
            block.setTxSize(tx.size());
            block.setSizeOnDisk(json.getLong("size"));
            block.setDifficulty(json.getDouble("difficulty"));
            block.setPrevBlockhash(json.getString("previousblockhash"));
            block.setNextBlockhash(json.getString("nextblockhash"));
            block.setMerkleRoot(json.getString("merkleroot"));
             blockMapper.insert(block);
            hash=json.getString("nextblockhash");
        }
    }
}
