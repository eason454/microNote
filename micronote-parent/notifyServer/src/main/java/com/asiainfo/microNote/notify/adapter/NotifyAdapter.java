package com.asiainfo.microNote.notify.adapter;

import com.asiainfo.microNote.notify.pojo.Message;

/**
 * 推送适配器接口
 * @author yi
 *
 */
public interface NotifyAdapter {

	/**
	 * 向其他接口推送信息
	 * @param message
	 * @return
	 */
	boolean notify(Message message);
}
