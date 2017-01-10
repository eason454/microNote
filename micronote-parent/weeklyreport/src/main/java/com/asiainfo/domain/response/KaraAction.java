package com.asiainfo.domain.response;

/**
 * Created by eason on 2017/1/10.
 * 按钮actions格式
 */
public class KaraAction {
    //名称，跟callback_id一起返回
    private String name;
    //显示
    private String text;
    //目前只能是button，后续扩展
    private String type;
    //跟name，callback_id一起返回，此处对报销系统可以传递调用业务地址url
    private String value;
    //default，primary，danger
    private String style;
    private KaraConfirm confirm;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public KaraConfirm getConfirm() {
        return confirm;
    }

    public void setConfirm(KaraConfirm confirm) {
        this.confirm = confirm;
    }
}
