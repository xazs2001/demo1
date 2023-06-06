package com.example.demo.get;





import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URISyntaxException;

public class get_info {
    public static void main(String[] args) throws IOException, URISyntaxException {
//        System.out.println(get_information());
        String requestBody = "{\"prompt\": PROMPTs, \"max_tokens\": 50, \"model\":\"gpt-3.5-turbo\"}";
        String s="h k";

        String prompTs = requestBody.replace("PROMPTs", "please broadcast a news about the local weather in terms of the following information:" +
                " city: 510100 wheather 晴 temperature 26");
        System.out.println(prompTs);
    }
    public static String get_information() throws IOException, URISyntaxException {
        String key="5d7a836fcc9d639ffebfaa12381bf9b1";
        String url="https://restapi.amap.com/v3/weather/weatherInfo?parameters";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String city="510100";
        String extensions="base";
        URIBuilder uriBuilder = new URIBuilder(url);
        uriBuilder.addParameter("key",key);
        uriBuilder.addParameter("city",city);
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String s = EntityUtils.toString(entity);
//        System.out.println(s);

        JSONObject jsonObject = JSONObject.parseObject(s);
        JSONArray lives = jsonObject.getJSONArray("lives");
//        System.out.println(jsonObject);
//        System.out.println(lives);
//        for (int i = 0; i < lives.size(); i++){
        String weather = lives.getJSONObject(0).getString("weather");
        String city1=lives.getJSONObject(0).getString("city");
        String temperature = lives.getJSONObject(0).getString("temperature");
//        }
//        System.out.println(weather);
//        System.out.println(city1);
//        System.out.println(temperature);

        response.close();
        httpClient.close();
        String prompt="please broadcast a news about the local weather in terms of the following information:\n "
                +"city: "+city+ " weather: "+weather+" temperature: "+temperature;
        return prompt;
    }

}
