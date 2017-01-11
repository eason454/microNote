package com.asiainfo.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by eason on 2017/1/10.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KaraMessage {
    private String text;
    private String channel;
    private KaraAttachment[] attachments;
    @JsonProperty(value = "reponse_type")
    private String reponseType;
//    @JsonProperty(value = "replace_original")
//    private boolean replaceOriginal;
//    @JsonProperty(value = "delete_original")
//    private boolean deleteOriginal;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }


    public KaraAttachment[] getAttachments() {
        return attachments;
    }

    public void setAttachments(KaraAttachment[] attachments) {
        this.attachments = attachments;
    }

    public String getReponseType() {
        return reponseType;
    }

    public void setReponseType(String reponseType) {
        this.reponseType = reponseType;
    }

//    public boolean isReplaceOriginal() {
//        return replaceOriginal;
//    }
//
//    public void setReplaceOriginal(boolean replaceOriginal) {
//        this.replaceOriginal = replaceOriginal;
//    }
//
//    public boolean isDeleteOriginal() {
//        return deleteOriginal;
//    }
//
//    public void setDeleteOriginal(boolean deleteOriginal) {
//        this.deleteOriginal = deleteOriginal;
//    }
}
