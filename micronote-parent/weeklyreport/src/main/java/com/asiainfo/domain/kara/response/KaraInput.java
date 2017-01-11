package com.asiainfo.domain.kara.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by eason on 2017/1/10.
 */
public class KaraInput {
    /**
     * 名称，类似html的标签id
     */
    private String name;
    /**
     * 默认值
     */
    private String value;
    /**
     * 类型，第一版只支持text
     */
    @JsonProperty(value = "type")
    private String karaType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKaraType() {
        return karaType;
    }

    public void setKaraType(String karaType) {
        this.karaType = karaType;
    }
}
