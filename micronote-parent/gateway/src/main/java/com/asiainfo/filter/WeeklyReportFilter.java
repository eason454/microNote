
package com.asiainfo.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.asiainfo.domain.KaraUserResponseInfo;
import com.asiainfo.domain.User;
import com.asiainfo.feign.weeklyreport.WeeklyReportClient;
import com.asiainfo.utils.HttpUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * Created by eason on 2017/2/6.
 */
public class WeeklyReportFilter extends ZuulFilter {
    private static final Logger log= LoggerFactory.getLogger(WeeklyReportFilter.class);
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WeeklyReportClient weeklyReportClient;
    @Value("${karaUrl.getStaffByAccountId}")
    private String karaStaffUrl;
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 999;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        //check user information
        //parse key information
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request =ctx.getRequest();
        String url=request.getRequestURI();
        if(url.indexOf("/microNote/")>-1){
        	BufferedReader reader;
			try {
				
				try {
					reader = request.getReader();
					String requestBody=reader.readLine();
					Map<String,Object> map =JsonParserFactory.getJsonParser().parseMap(requestBody);
					if(StringUtils.isEmpty(map.get("user_id").toString())){
						return null;
					}
					 User userInfo = weeklyReportClient.getUser(map.get("user_id").toString());
					if(userInfo==null){
	     	        	//调用kara获取用户信息
	     	        	String token=map.get("token").toString();
	     	        	HttpEntity headers = HttpUtils.getKaraHttpEntityForGet(token);
	     	        	HttpEntity<KaraUserResponseInfo> response=restTemplate.exchange(karaStaffUrl, HttpMethod.GET,headers,KaraUserResponseInfo.class,map.get("user_id"));//
	                    KaraUserResponseInfo userResponseInfo=response.getBody();
	                    if(userResponseInfo.getStaffResponseInfo()==null){
	                    	return null;
	                    }
	                    User newUser=new User();
	                    newUser.setId(userResponseInfo.getStaffResponseInfo().getAccountId());
	                    newUser.setName(userResponseInfo.getStaffResponseInfo().getStaffName());
	                    newUser.setAvatar(userResponseInfo.getStaffResponseInfo().getHeadIcon());
	                    newUser.setAccount(userResponseInfo.getStaffResponseInfo().getStaffAccount());
	                    newUser.setUserNumber(userResponseInfo.getStaffResponseInfo().getStaffCode());
	                    weeklyReportClient.createUser(newUser);//通过feign创建用户
	                    log.debug(newUser.getName()+" created");
					}else{
						return null;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					log.error(e.getMessage());
					return null;
				}
     	       
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage());
				return null;
			}
        }
        return null;
    }
}

