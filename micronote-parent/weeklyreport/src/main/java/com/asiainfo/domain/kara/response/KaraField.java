package com.asiainfo.domain.kara.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by eason on 2017/1/10.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KaraField {
    private String title;
    private String value;
//    @JsonProperty(value = "short",)
//    private boolean karaShort;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

//    public boolean isKaraShort() {
//        return karaShort;
//    }
//
//    public void setKaraShort(boolean karaShort) {
//        this.karaShort = karaShort;
//    }
}
