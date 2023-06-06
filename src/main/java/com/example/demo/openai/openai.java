package com.example.demo.openai;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.get.get_info;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class openai{
    public static void main(String[] args) {
        String apiKey = "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";
        String endpoint =
                "https://api.openai.com/v1/chat/completions";  // 根据您的需求选择适当的端点
        String proxyHost = "127.0.0.1";  // 替换为您的代理主机名
        int proxyPort = 7890;  // 替换为您的代理端口
        try {

            HttpHost proxy = new HttpHost(proxyHost, proxyPort);
            RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
            CloseableHttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
            HttpPost request = new HttpPost(endpoint);

            // 设置请求头
            request.setHeader("Authorization", "Bearer " + apiKey);
            request.setHeader("Content-Type", "application/json");


                String prompt=new get_info().get_information();
            // 设置请求体
            JSONObject requestBodyJson = new JSONObject();
            requestBodyJson.put("prompt",prompt);
            requestBodyJson.put("max_tokens",50);
            requestBodyJson.put("model","gpt-3.5-turbo");
            String requestBody = requestBodyJson.toString();

            request.setEntity(new StringEntity(requestBody));

            // 发送请求并获取响应
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            String responseBody = EntityUtils.toString(entity);


            // 处理响应
            System.out.println(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
