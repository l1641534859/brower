package com.example.demo.servicer.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.BlockDetailDTO;
import com.example.demo.dto.TransactionInBlockDTO;
import com.example.demo.dto.TransactionListDTO;
import com.example.demo.enumeration.TransactionDetailType;
import com.example.demo.mapper.BlockMapper;
import com.example.demo.mapper.BlockchainMapper;
import com.example.demo.mapper.TransactionMapper;
import com.example.demo.mapper.Transaction_detailMapper;
import com.example.demo.po.Block;
import com.example.demo.po.Transaction;
import com.example.demo.po.Transaction_detail;
import com.example.demo.restAPI.BitcoinApi;
import com.example.demo.restAPI.BitcoinJsonRpcClient;
import com.example.demo.servicer.MiscService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MiscServiceImpl implements MiscService {
        @Autowired(required = false)
        private BitcoinApi bitcoinApi;
    @Autowired
    private BlockchainMapper blockchain;
    @Autowired
    private BlockMapper blockMapper;
    @Autowired
    private Transaction_detailMapper transaction_detailMapper;
    @Autowired
    private BitcoinJsonRpcClient bitcoinJsonRpcClient;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private Transaction_detailMapper transactionDetailMapper;
    @Async
    @Override
    public void importFromHeight(Integer blockHeight, Boolean isClean) {

    }
    @Async
    @Override
    public void importFromnextHash(String blockHash, Boolean isClean) throws Throwable {
        String hash=blockHash;
        JSONObject json1=bitcoinApi.getBlock(hash);
        hash=json1.getString("nextblockhash");
        while(hash!=null){
            JSONObject json=bitcoinApi.getBlock(hash);
            Block block=new Block();
            block.setBlockhash(json.getString("hash"));
            block.setBlockchainId(2);
            block.setHeight(json.getInteger("height"));
            Long time=json.getLong("time");
            Date date=new Date(time*1000);
            block.setTime(date);
            JSONArray tx=json.getJSONArray("tx");
            for (int i = 0; i < tx.size(); i++) {
                if(tx!=null){
                    importTx(tx.getJSONObject(i),hash,date);
                }
            }
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
    @Async
    @Override
    public void importFromHash(String blockHash, Boolean isClean) throws Throwable {
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
            Date date=new Date(time*1000);
            block.setTime(date);
            JSONArray tx=json.getJSONArray("tx");
            for (int i = 0; i < tx.size(); i++) {
                if(tx!=null){
                    importTx(tx.getJSONObject(i),hash,date);
                }
            }
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

    @Override
    public List<Transaction_detail> selectByAddress(String address) {
        return transactionDetailMapper.selectByAddress(address);
    }

    @Override
    public List<Block> selectRecent(Integer page1) {
        PageHelper.startPage(1,page1);
        Page page=blockMapper.selectAll();
        return page.getResult();
    }

    @Override
    public List<Transaction_detail> getRecentTransactionsById() {

        return transactionDetailMapper.selectAll();
    }

    @Override
    public List<Transaction> getTransactionsAll(Integer page2) {
        PageHelper.startPage(1,page2);
        Page page = transactionMapper.selectAll();
        return      page.getResult();
    }

    @Override
    public BlockDetailDTO getBlockByAddress(String address) {
        Block block=blockMapper.selectByPrimaryKey(address);
        BlockDetailDTO blockDetailDTO=new BlockDetailDTO();
        blockDetailDTO.setBlockhash(block.getBlockhash());
        blockDetailDTO.setDifficulty(block.getDifficulty());
        blockDetailDTO.setHeight(block.getHeight());
        blockDetailDTO.setMerkleRoot(block.getMerkleRoot());
        blockDetailDTO.setNextBlockhash(block.getNextBlockhash());
        blockDetailDTO.setOutputTotal(block.getOutputTotal());
        blockDetailDTO.setPrevBlockhash(block.getPrevBlockhash());
        blockDetailDTO.setSizeOnDisk(block.getSizeOnDisk());
        blockDetailDTO.setTime(block.getTime());
        blockDetailDTO.setTxSize(block.getTxSize());
        blockDetailDTO.setTransactionFees(block.getTransactionFees());
        List<Transaction> list=transactionMapper.selectByAddress(address);
        List<TransactionInBlockDTO> tlist=list.stream().map(transaction -> {
            TransactionInBlockDTO t=new TransactionInBlockDTO();
            t.setSize(transaction.getSize());
            t.setTime(new Date(transaction.getTime().getTime()));
            t.setTxhash(transaction.getTxhash());
            t.setTxid(transaction.getTxid());
            List<Transaction_detail> transaction_details = transactionDetailMapper.selectByTxid(transaction.getTxid());
            t.setTransactionlist(transaction_details);
            return t;
        }).collect(Collectors.toList());;
        blockDetailDTO.setTransactions(tlist);
        return blockDetailDTO;
    }

    public void importTx(JSONObject tx, String blockhash, Date time) throws Throwable {
        Transaction transaction = new Transaction();
        String txid = tx.getString("txid");
        transaction.setTxid(txid);
        transaction.setTxhash(tx.getString("hash"));
        transaction.setBlockhash(blockhash);
        transaction.setSize(tx.getLong("size"));
        transaction.setWeight(tx.getInteger("weight"));
        transaction.setTime(time);
        JSONArray vouts = tx.getJSONArray("vout");
        for (int i = 0; i < vouts.size(); i++) {
            if (vouts!=null){
                importVoutDetail(vouts.getJSONObject(i),txid);
            }

        }
        JSONArray vins = tx.getJSONArray("vin");
        for (int i = 1; i < vins.size(); i++) {
            if(vins!=null){
                importVinDetail(vins.getJSONObject(i),txid);
            }
        }
            transactionMapper.insert(transaction);
    }

    public void importVoutDetail(JSONObject vout, String txid){
        Transaction_detail transactionDetail = new Transaction_detail();
        transactionDetail.setTxid(txid);
        JSONObject scriptPubKey = vout.getJSONObject("scriptPubKey");
        JSONArray addresses = scriptPubKey.getJSONArray("addresses");
        if (addresses != null && !addresses.isEmpty()){
            String address = addresses.getString(0);
            transactionDetail.setAddress(address);
            Double amount = vout.getDouble("value");
            transactionDetail.setAmount(amount);
            transactionDetail.setType((byte) TransactionDetailType.Receive.ordinal());
            transactionDetailMapper.insert(transactionDetail);
        }


    }

    public void importVinDetail(JSONObject vin, String txidOrigin) throws Throwable {
        String txid = vin.getString("txid");
        Integer vout = vin.getInteger("vout");
        JSONObject rawTransaxtion = bitcoinJsonRpcClient.getRawTransaxtion(txid);
        JSONArray vouts = rawTransaxtion.getJSONArray("vout");
        JSONObject jsonObject = vouts.getJSONObject(vout);
        Transaction_detail transactionDetail = new Transaction_detail();
        transactionDetail.setTxid(txidOrigin);
        transactionDetail.setType((byte) TransactionDetailType.Send.ordinal());
        Double amount = jsonObject.getDouble("value");
        transactionDetail.setAmount(amount);
        JSONObject scriptPubKey = jsonObject.getJSONObject("scriptPubKey");
        JSONArray addresses = scriptPubKey.getJSONArray("addresses");
        if (addresses != null){
            String address = addresses.getString(0);
            if(address!=null){
                transactionDetail.setAddress(address);
                transactionDetailMapper.insert(transactionDetail);
            }
        }

    }




}
