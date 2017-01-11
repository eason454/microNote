package com.asiainfo.util.kara;

import com.asiainfo.domain.response.KaraAttachment;
import com.asiainfo.domain.response.KaraField;
import com.asiainfo.domain.response.KaraMessage;
import com.asiainfo.util.consts.CommonConst;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eason on 2017/1/11.
 */
public class MessageConstructor {
    public static KaraMessage constructMessageWithFields(String attachTitle,List<KaraField> karaFields){
        KaraMessage message=new KaraMessage();
//        message.setChannel(body.get("channel_id"));
        message.setText(CommonConst.KaraInfo.querySuccess);
        KaraAttachment attach=new KaraAttachment();
        attach.setTitle(attachTitle);
//        attach.setCallbackId("testcallbackId");  //回调id填什么呢
        List<KaraField> fieldList=new ArrayList<KaraField>();
        for (KaraField karaField :
                karaFields) {
            KaraField field=new KaraField();
            field.setTitle(karaField.getTitle());
            field.setValue(karaField.getValue());
            fieldList.add(field);
        }
        attach.setFields(fieldList.toArray(new KaraField[fieldList.size()]));
        //不支持attachments时显示的内容
        List<KaraAttachment> attachList=new ArrayList<KaraAttachment>();
        attachList.add(attach);
        message.setAttachments(attachList.toArray(new KaraAttachment[0]));
        return message;
    }
}
