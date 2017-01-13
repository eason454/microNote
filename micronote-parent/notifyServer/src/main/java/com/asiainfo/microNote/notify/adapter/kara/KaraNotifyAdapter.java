package com.asiainfo.microNote.notify.adapter.kara;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asiainfo.microNote.notify.adapter.NotifyAdapter;
import com.asiainfo.microNote.notify.pojo.message.weeklyReport.WeeklyReportRemaindReportMessage;
import com.asiainfo.microNote.notify.pojo.message.weeklyReport.WeeklyReportSubmitReportMessage;

/**
 * 
 * @author yi
 *
 */
@Component
public class KaraNotifyAdapter implements NotifyAdapter {

	private static final Logger logger = Logger.getLogger(KaraNotifyAdapter.class);

	@Autowired
	private IKaraService karaService;

	private String weeklyReportReportContent = "<http://localhost:9001/microNote/comment/weeklyReport/{userId}/{week}|填写周报>";

	private String weeklyReportCheckReportAll = "<http://localhost:9001/microNote/weeklyReport/{userId}|查看 {week} 周所有已提交的周报...>";

	private String weeklyReportCheckReport = "<http://localhost:9001/microNote/weeklyReport/{userId}|查看{week}周 @{userName} de周报>";

	/**
	 * 推送到kara
	 */
	@Override
	public boolean weeklyReportNotifyReport(WeeklyReportRemaindReportMessage message) {
		KaraIncoming karaIncoming = new KaraIncoming();
		karaIncoming.text = new StringBuffer("亲～ 该填周报了^O^");
		karaIncoming.channel = new StringBuffer("@").append(message.getNotifyUser().getId());
		StringBuffer title = new StringBuffer(weeklyReportReportContent
				.replace("{userId}", message.getNotifyUser().getId()).replace("{week}", "" + message.getWeek()));

		karaIncoming.setAttachments(karaIncoming.new Attachment(title));
		karaService.incoming(karaIncoming);
		return true;
	}

	@Override
	public boolean weeklyReportNotifyAuditing(List<WeeklyReportSubmitReportMessage> messages) {
		String week = "" + Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
		KaraIncoming karaIncoming = new KaraIncoming();
		karaIncoming.text = new StringBuffer().append("亲～ 剛才有").append(messages.size()).append(" 人向您提交了周報");
		StringBuffer userId = messages.get(0).getNotifyUser().getId();
		logger.info("weeklyReportNotifyAuditing " + karaIncoming.text);
		karaIncoming.channel = new StringBuffer("@").append(userId);
		// 添加提交人的周報
		for (WeeklyReportSubmitReportMessage message : messages) {
			karaIncoming.setAttachments(
					karaIncoming.new Attachment(new StringBuffer(weeklyReportCheckReport.replace("{userId}", userId)
							.replace("{week}", week).replace("{userName}", message.getWeeklyReport().getReportUserName()))));
		}
		// 添加查看更多
		karaIncoming.setAttachments(karaIncoming.new Attachment(
				new StringBuffer(weeklyReportCheckReportAll.replace("{userId}", userId).replace("{week}", week))));
		karaService.incoming(karaIncoming);
		return true;
	}
}
