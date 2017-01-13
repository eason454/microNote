package com.asiainfo.microNote.notify.adapter;

import java.util.List;

import com.asiainfo.microNote.notify.pojo.message.weeklyReport.WeeklyReportRemaindReportMessage;
import com.asiainfo.microNote.notify.pojo.message.weeklyReport.WeeklyReportSubmitReportMessage;

/**
 * 推送适配器接口
 * @author yi
 */
public interface NotifyAdapter {

	/**
	 * 向其他接口推送信息
	 * @param message
	 * @return
	 */
	boolean weeklyReportNotifyReport(WeeklyReportRemaindReportMessage message);
	
	/**
	 * 
	 * @param messages
	 * @return
	 */
	public boolean weeklyReportNotifyAuditing(List<WeeklyReportSubmitReportMessage> messages);
	
}
