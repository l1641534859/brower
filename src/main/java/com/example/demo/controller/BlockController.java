package com.example.demo.controller;

import com.example.demo.dto.BlockDetailDTO;
import com.example.demo.dto.BlockListDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/block")
public class BlockController {

    @GetMapping("/getRecentBlocksById")
    public List<BlockListDTO> getRecentBlocksById(@RequestParam Integer blockchainId){
        return null;
    }

    @GetMapping("/getRecentBlocksByNameType")
    public List<BlockListDTO> getRecentBlocksByNameType(@RequestParam String name,
                                                        @RequestParam String type){
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
}
