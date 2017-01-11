package com.asiainfo.microNote.notify.service.weeklyReport;

import java.util.List;
import java.util.concurrent.Executor;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.asiainfo.microNote.notify.adapter.NotifyAdapter;
import com.asiainfo.microNote.notify.pojo.Message;
import com.asiainfo.microNote.notify.pojo.NotifyUser;
import com.asiainfo.microNote.notify.user.service.IUserService;

import ch.qos.logback.core.util.ExecutorServiceUtil;

/**
 * 周报通知服务，定时每周五推送
 * 
 * @author yi
 */
@Service
public class WeeklyReportOnTimeNotifyService {

	private static final Logger logger = Logger.getLogger(WeeklyReportOnTimeNotifyService.class);

	@Autowired
	IUserService userService;
	// 推送適配器 第一版只有kara
	@Autowired
	NotifyAdapter notifyAdapter;

	// 推送消息定義
	@Value("weeklyReport.user.noitfy.onEveryWeek")
	String notifyContentEveryWeek;

	// 推送線程數量
//	@Value("weeklyReport.user.noitfy.notifyThreadNumber")
	int notifyThreadNumber = 4;

	/**
	 * 每周五通知所有用户填写周报
	 */
	@Scheduled(cron = "0/30 * * * * ?")
	public void notifyUserSubimtWeeklyReportOnEveryWeekend() {
		// TODO 每周五通知所有用户填写周报代碼
		Executor executor = ExecutorServiceUtil.newExecutorService();
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
							// 查询当前线程负责分页
							logger.info("線程["+handlePage+"]正在處理分頁["+ page +"]的數據...");
							List<NotifyUser> users = userService.getUserByPageAndSort(page, 2, "id");
							// 如果查询当前页已经为空退出线程
							if (users.isEmpty()){
								logger.info("線程["+handlePage+"]已經完成工作退出...");
								return;
							}
							// 循環向用戶通知填寫周報消息
							for (NotifyUser user : users) {
								StringBuffer content = new StringBuffer(notifyContentEveryWeek);
								try {
									notifyAdapter.notify(new Message(user.getId() + "", user.getName() + "", content));
								} catch (Exception ex) {
									// TODO 推送錯誤處理代碼
									logger.error("推送[" + user.getId() + "," + user.getName() + "," + content + "]遇到错误："
											+ ex.getCause());
									ex.printStackTrace();
								}
							}
							Thread.sleep(100);
							// 獲取當前頁
							page = page + notifyThreadNumber;
						} catch (Exception ex) {
							// TODO 添加分頁查詢錯誤處理代碼
							logger.error("查询分页[" + page + "]遇到错误：" + ex.getCause());
							ex.printStackTrace();
						}
					}
				}
			});
		}
	}

	/**
	 * 通知用户查看填写的周报
	 */
	@Scheduled(cron = "0/5 * * * * ?")
	public void notifyUserCheckWeeklyReport() {
		// TODO 第一版全部实时推送需要在第二版实现统一推送
	}
}
