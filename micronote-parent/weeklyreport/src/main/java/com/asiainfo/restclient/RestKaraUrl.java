package com.asiainfo.restclient;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by eason on 2017/1/11.
 */
public class RestKaraUrl {
    @Value("#{kara.url.getStaffByAccountId}")
    public static String staffInfoByIdUrl;
    @Value("#{kara.url.getStaffByStaffId}")
    public static String StaffInfoByStaffId;
}
