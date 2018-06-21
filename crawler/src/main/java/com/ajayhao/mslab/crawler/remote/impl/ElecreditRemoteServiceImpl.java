package com.ajayhao.mslab.crawler.remote.impl;

import com.ajayhao.mslab.core.util.HMACUtil;
import com.ajayhao.mslab.core.util.JsonUtil;
import com.ajayhao.mslab.core.util.UnicodeUtil;
import com.ajayhao.mslab.crawler.remote.ElecreditRemoteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @ClassName ElecreditRemoteServiceImpl
 * @Description 元数企业信息实现类
 * @Author Ajay Hao
 * @Date 2018/6/21 15:14
 * @Version 1.0
 **/
@Service("elecreditRemoteService")
public class ElecreditRemoteServiceImpl implements ElecreditRemoteService{

    private String charset = "UTF-8";

    private String url = "http://open.elecredit.com/elsaic/?isjson=1";

    //private String userId = "Z2HO3fdC";

    //private String userKey = "Uw2pVB9";

    @Override
    public String getEnterpriceInfo(final String userKey,final Map<String,String> paramMap) {
        //加签加密
        String authStr = getSignRawStr(userKey, paramMap);
        HttpHeaders requestHeaders = buildRequestHeader(authStr);
        MultiValueMap<String, String> requestBody = buildRequestBody(paramMap);
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<>(requestBody, requestHeaders);

        //post
        RestTemplate restTemplate = new RestTemplate();
        //restTemplate.getMessageConverters().clear();
        //write application/x-www-form-urlencoded request
        //restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        //read and write application/json
        //restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter(JsonUtil.JsonObjectMapperFactory.getInstance()));
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
        String respEntityBody = responseEntity.getBody();
        return UnicodeUtil.unicodeToString(respEntityBody);
    }

    private MultiValueMap<String,String> buildRequestBody(Map<String, String> paramMap) {
        MultiValueMap<String,String> body = new LinkedMultiValueMap<>();
        for(String key : paramMap.keySet()){
            body.add(key, paramMap.get(key));
        }
        return body;
    }

    private HttpHeaders buildRequestHeader(String auth) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        requestHeaders.add("authorization", auth);
        requestHeaders.add("charset", charset);
        return requestHeaders;
    }

    private String getSignRawStr(String userKey, final Map<String,String> params){
        Map<String,String> sortedParams = new TreeMap<>(params);
        StringBuffer sb = new StringBuffer();
        for(String key : sortedParams.keySet()){
            sb = sb.append(key).append(sortedParams.get(key));
        }
        String signRawStr = sb.append(params.get("userid")).toString();
        String authStr = null;
        try {
            authStr = HMACUtil.encode(userKey, signRawStr);
        }catch(Exception e){
            e.printStackTrace();
        }
        return authStr;
    }
}
