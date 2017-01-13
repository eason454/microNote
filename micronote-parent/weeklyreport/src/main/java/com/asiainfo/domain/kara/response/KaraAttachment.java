package com.asiainfo.domain.kara.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by eason on 2017/1/10.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    public KaraField[] getFields() {
        return fields;
    }

    public void setFields(KaraField[] fields) {
        this.fields = fields;
    }

    private KaraField[] fields;
    //不支持attachments时显示对内容
    private String fallback;
    //回传id
    @JsonProperty(value = "callback_id")
    private String callbackId;
    private String color;
    //按钮数据
    private KaraAction[] actions;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorIcon() {
        return authorIcon;
    }

    public void setAuthorIcon(String authorIcon) {
        this.authorIcon = authorIcon;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getFallback() {
        return fallback;
    }

    public void setFallback(String fallback) {
        this.fallback = fallback;
    }

    public String getCallbackId() {
        return callbackId;
    }

    public void setCallbackId(String callbackId) {
        this.callbackId = callbackId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public KaraAction[] getActions() {
        return actions;
    }

    public void setActions(KaraAction[] actions) {
        this.actions = actions;
    }
}
