package com.asiainfo.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by eason on 2017/1/10.
 */
public class KaraAttachment {
    //消息内容
    private String title;
    //名称
    @JsonProperty(value = "author_name")
    private String authorName;
    @JsonProperty(value = "author_icon")
    private String authorIcon;
    @JsonProperty(value = "image_url")
    private String imageUrl;
    private KaraField fields;
    //不支持attachments时显示对内容
    private String fallback;
    //回传id
    @JsonProperty(value = "callback_id")
    private String callbackId;
    private String color;
    //按钮数据
    private KaraAction[] actions;
}
