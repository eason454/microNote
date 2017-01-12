package com.asiainfo.microNote.notify.adapter.kara;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asiainfo.microNote.notify.adapter.NotifyAdapter;
import com.asiainfo.microNote.notify.pojo.message.weeklyReport.WeeklyReportNotifyReportMessage;
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

	private String weeklyReportReportContent = "<http://localhost:9001/microNote/comment/weeklyReport/{userId}/{week}|点击填写周报>";

	private String weeklyReportReportCheck = "<http://localhost:9001/microNote/comment/weeklyReport/{userId}|点击周报>";

	/**
	 * 推送到kara
	 */
	@Override
	public boolean weeklyReportNotifyReport(WeeklyReportNotifyReportMessage message) {
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
		KaraIncoming karaIncoming = new KaraIncoming();
		karaIncoming.text = new StringBuffer().append("there is ").append(messages.size())
				.append(" persons already submit weeklyReport to you ...");
		StringBuffer userId = messages.get(0).getNotifyUser().getId();
		logger.info("weeklyReportNotifyAuditing " + messages.get(0).getNotifyUser().getName());
		karaIncoming.channel = new StringBuffer("@").append(userId);
		StringBuffer title = new StringBuffer(weeklyReportReportCheck.replace("{userId}", userId));
		karaIncoming.setAttachments(karaIncoming.new Attachment(title));
		karaService.incoming(karaIncoming);
		return true;
	}
}
