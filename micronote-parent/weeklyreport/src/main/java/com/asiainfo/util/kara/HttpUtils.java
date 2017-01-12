package com.asiainfo.util.kara;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * Created by eason on 2017/1/12.
 */
public class HttpUtils {
    /**
     * 大多数kara请求的header中只有content-type和authorazation，因此提供一个方法
     * @param token
     * @return
     */
    public static HttpEntity getKaraHttpEntityForGet(String token){
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION,String.format("bearer %s",token));
        HttpEntity requestEntity=new HttpEntity(httpHeaders);
        return requestEntity;
    }
    public static HttpEntity getKaraHttpEntityForPost(String token){
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.AUTHORIZATION,String.format("bearer %s",token));
        HttpEntity requestEntity=new HttpEntity(httpHeaders);
        return requestEntity;
    }
}
