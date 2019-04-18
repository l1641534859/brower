package com.example.demo.restAPI;

import com.alibaba.fastjson.JSONObject;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;

@Service
public class BitcoinJsonRpcClient {


    private JsonRpcHttpClient jsonRpcHttpClient;

    public BitcoinJsonRpcClient() throws MalformedURLException {
        HashMap<String, String> headers = new HashMap<>();
        String authStrOrig = String.format("%s:%s","lsh","123456");
        String authStr = Base64.getEncoder().encodeToString(authStrOrig.getBytes());
        String authStrResult = String.format("Basic %s",authStr);
        headers.put("Authorization",authStrResult);
        jsonRpcHttpClient = new JsonRpcHttpClient(new URL("http://localhost:18332"),headers);
    }

    public String getBlockHashByHeight(Integer blockHeight) throws Throwable {
        String blockhash = jsonRpcHttpClient.invoke("getblockhash", new Integer[]{blockHeight}, String.class);
        return blockhash;
    }
}
