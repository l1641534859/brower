package com.example.demo.controller;

import com.example.demo.dto.BlockDetailDTO;
import com.example.demo.dto.BlockListDTO;
import com.example.demo.po.Block;
import com.example.demo.restAPI.BitcoinApi;
import com.example.demo.restAPI.BitcoinJsonRpcClient;
import com.example.demo.servicer.MiscService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/block")
@CrossOrigin
public class BlockController {
    @Autowired
    private BitcoinApi bitcoinApi;

    @Autowired
    private BitcoinJsonRpcClient bitcoinJsonRpcClient;

    @Autowired
    private MiscService miscService;

    @Value("${blockchain.recentCount}")
    private Integer recentCount;
    @GetMapping("/getRecentBlocksById")
    public List<BlockListDTO> getRecentBlocksById(@RequestParam(defaultValue = "5") Integer page1){
        List<Block> blocks = miscService.selectRecent(page1);
        List<BlockListDTO> blockListDTOS = blocks.stream().map(block -> {
            BlockListDTO blockListDTO = new BlockListDTO();
            blockListDTO.setHeight(block.getHeight());
            blockListDTO.setTime(block.getTime().getTime());
            blockListDTO.setTxSize(block.getTxSize());
            blockListDTO.setSizeOnDisk(block.getSizeOnDisk());
            blockListDTO.setHash(block.getBlockhash());
            return blockListDTO;
        }).collect(Collectors.toList());

        return blockListDTOS;
    }

    @GetMapping("/getRecentBlocksByNameType")
    public List<BlockListDTO> getRecentBlocksByNameType(@RequestParam String name,@RequestParam String type){
        return null;
    }

    @GetMapping("/getBlockDetailByHash")
    public BlockDetailDTO getBlockDetailByHash(@RequestParam String blockhash){

        return null;
    }

    @GetMapping("/getBlockDetailByHeight")
    public BlockDetailDTO getBlockDetailByHeight(@RequestParam Integer blockheight){

        return null;
    }
    @GetMapping("/getBlockByAddress")
    public BlockDetailDTO getBlockByAddress(@RequestParam String address){
        return miscService.getBlockByAddress(address);
    }
}
