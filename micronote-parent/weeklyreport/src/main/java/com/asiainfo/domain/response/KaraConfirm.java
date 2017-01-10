package com.asiainfo.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by eason on 2017/1/10.
 */
public class KaraConfirm {
    //标题
    private String title;
    private String text;
    //确认按钮
    @JsonProperty(value = "ok_text")
    private String okText;
    //取消按钮
    @JsonProperty(value = "dismiss_text")
    private String dismissText;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOkText() {
        return okText;
    }

    public void setOkText(String okText) {
        this.okText = okText;
    }

    public String getDismissText() {
        return dismissText;
    }

    public void setDismissText(String dismissText) {
        this.dismissText = dismissText;
    }

    public KaraInput[] getInputs() {
        return inputs;
    }

    public void setInputs(KaraInput[] inputs) {
        this.inputs = inputs;
    }
//输入返回信息
    private KaraInput[] inputs;

}
