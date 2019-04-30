package com.example.demo.controller;

import com.example.demo.dto.ImportStateDTO;
import com.example.demo.servicer.MiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/misc")
@EnableAutoConfiguration
@CrossOrigin
public class MiscController {

    @Autowired
    private MiscService miscService;

    @GetMapping("/search")
    public Object search(@RequestParam String keyword){
        return null;
    }

    @GetMapping("/importFromHeight")
    public void importFromHeight(@RequestParam Integer blockHeight, Boolean isClean){
        miscService.importFromHeight(blockHeight, isClean);
    }

    @GetMapping("/importFromHash")
    public void importFromHash(@RequestParam String blockhash, Boolean isClean) throws Throwable {

        miscService.importFromHash(blockhash, isClean);
    }
    @GetMapping("/importFromnextHash")
    public void importFromnextHash(@RequestParam String blockhash, @RequestParam Boolean isClean) throws Throwable {

        miscService.importFromnextHash(blockhash,isClean);
    }
    @GetMapping("/getImportState")
    public ImportStateDTO getImportState(){
        return null;
    }
}
