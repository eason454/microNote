package com.asiainfo.microNote.notify.service.weeklyReport;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.asiainfo.microNote.notify.adapter.NotifyAdapter;
import com.asiainfo.microNote.notify.pojo.NotifyUser;
import com.asiainfo.microNote.notify.pojo.message.weeklyReport.WeeklyReportRemaindReportMessage;
import com.asiainfo.microNote.notify.user.service.IUserService;

import ch.qos.logback.core.util.ExecutorServiceUtil;

/**
 * 周报通知服务，定时每周五推送
 * 
 * @author yi
 */
@Service
public class WeeklyReportRemaindReportNotifyService {

	private static final Logger logger = Logger.getLogger(WeeklyReportRemaindReportNotifyService.class);

	@Autowired
	IUserService userService;
	//TODO 推送適配器 第一版只有kara 如果有多渠道推送需要更改为订阅模式
	@Autowired
	NotifyAdapter notifyAdapter;
	
	volatile public static Set<String> exceptNotifyUsers = new HashSet<String>();

	// 推送線程數量
	@Value("${weeklyReport.noitfy.notifyThreadNumber}")
	int notifyThreadNumber;

	/**
	 * 每周五通知所有用户填写周报
	 */
	@Scheduled(cron = "${weeklyReport.noitfy.cron.notifyUserSubimtWeeklyReportOnEveryWeekend}")
	public void notifyUserSubimtWeeklyReportOnEveryWeekend() {
		// 每周五通知所有用户填写周报代碼
		Executor executor = ExecutorServiceUtil.newExecutorService();
		int week = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
		// 定义工作线程数量是sendThreadNumber
		for (int i = 0; i < notifyThreadNumber; i++) {
			// 定義當前線程應該處理那個分頁段數據
			final int handlePage = i;
			executor.execute(new Runnable() {
				@Override
				public void run() {
					int page = handlePage;
					while (true) {
						try {
							Thread.sleep((long) (Math.random()*3000+1));
							// 查询当前线程负责分页
							logger.info("線程[" + handlePage + "]正在處理分頁[" + page + "]的數據...");
							List<NotifyUser> users = userService.getUserByPageAndSort(page, 5, "id");
							// 如果查询当前页已经为空退出线程
							if (users.isEmpty()) {
								logger.info("線程[" + handlePage + "]已經完成工作退出...");
								return;
							}
							// 循環向用戶通知填寫周報消息
							for (NotifyUser user : users) {
								try {
									//已經提交了周報的用戶不用在通知
									if(exceptNotifyUsers.contains(user.getId()))
										continue;
									logger.info("推送"+user.getName());
									WeeklyReportRemaindReportMessage message = new WeeklyReportRemaindReportMessage();
									message.setNotifyUser(user);
									message.setWeek(week);
									notifyAdapter.weeklyReportNotifyReport(message);
								} catch (Exception ex) {
									// TODO 推送錯誤處理代碼
									logger.error("推送[" + user.getId() + "," + user.getName() + "]遇到错误："
											+ ex.getCause());
									
									ex.printStackTrace();
									continue;
								}
							}
							
							// 獲取當前頁
							page = page + notifyThreadNumber;
						} catch (Exception ex) {
							// TODO 添加分頁查詢錯誤處理代碼
							logger.error("查询分页[" + page + "]遇到错误：" + ex.getCause());
							ex.printStackTrace();
							continue;
						}
					}
				}
			});
		}
	}

	/**
	 * 每周六清空提交周報用戶
	 */
	@Scheduled(cron = "0 0 23 * * 6")
	private void removeExceptionUsers(){
		exceptNotifyUsers.clear();
	}
	
}
