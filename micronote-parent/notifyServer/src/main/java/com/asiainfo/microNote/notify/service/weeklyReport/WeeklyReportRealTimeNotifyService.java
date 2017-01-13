package com.asiainfo.microNote.notify.service.weeklyReport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.asiainfo.microNote.notify.adapter.NotifyAdapter;
import com.asiainfo.microNote.notify.pojo.message.weeklyReport.WeeklyReportSubmitReportMessage;

import ch.qos.logback.core.util.ExecutorServiceUtil;

/**
 * 实时推送服务
 * 
 * @author yi
 */
@Service
public class WeeklyReportRealTimeNotifyService {

	Logger logger = Logger.getLogger(WeeklyReportRealTimeNotifyService.class);

	private static final Queue<WeeklyReportSubmitReportMessage> submitNotifyQueen = new ConcurrentLinkedQueue<WeeklyReportSubmitReportMessage>();

	private static final Queue<List<WeeklyReportSubmitReportMessage>> notifyQueen = new ConcurrentLinkedQueue<List<WeeklyReportSubmitReportMessage>>();

	private static final Map<String, List<WeeklyReportSubmitReportMessage>> userNotifyMap = new ConcurrentHashMap<String, List<WeeklyReportSubmitReportMessage>>();

	private static final Executor onWeeklyReportSubmitExecutor = ExecutorServiceUtil.newExecutorService();

	private static final Executor notifyAuditingUserExecutor = ExecutorServiceUtil.newExecutorService();

	private static final CountDownLatch startSingle = new CountDownLatch(5);

	@Autowired
	NotifyAdapter notifyAdapter;

	/**
	 * 提交周报信息写入处理队列
	 * 
	 * @param message
	 */
	public static void notifyAuditing(WeeklyReportSubmitReportMessage message) {
		submitNotifyQueen.add(message);
	}

	/**
	 * 处理提交的周报生成综合周报提交信息
	 */
	@Scheduled(cron = "0/30 * * * * ?")
	public void onWeeklyReportSubmit() {

		for (int i = 0; i < 5; i++) {
			onWeeklyReportSubmitExecutor.execute(new Runnable() {
				@Override
				public void run() {
					while (true) {
						try {
							WeeklyReportSubmitReportMessage message = submitNotifyQueen.poll();
							if(message == null)
								break;
							String key = message.getNotifyUser().getId().toString();
							List<WeeklyReportSubmitReportMessage> reportMessages = userNotifyMap
									.containsKey(key)
											? userNotifyMap.get(key)
											: new ArrayList<WeeklyReportSubmitReportMessage>();
							reportMessages.add(message);
							userNotifyMap.put(key, reportMessages);
						} catch (Exception e) {
							// TODO 添加容錯代碼
							e.printStackTrace();
						}
					}
					startSingle.countDown();
				}
			});
		}

		try {
			startSingle.await();
			notifyQueen.addAll(userNotifyMap.values());
			userNotifyMap.clear();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 处理提交的周报生成综合周报提交信息
	 */
	@Scheduled(cron = "0/15 * * * * ?")
	public void notifyAuditingUser() {
		for (int i = 0; i < 1; i++) {
			notifyAuditingUserExecutor.execute(new Runnable() {
				@Override
				public void run() {
					while (true) {
						List<WeeklyReportSubmitReportMessage> messages = notifyQueen.poll();
						if(messages == null )
							break;
						notifyAdapter.weeklyReportNotifyAuditing(messages);
					}
				}
			});
		}
	}
}
