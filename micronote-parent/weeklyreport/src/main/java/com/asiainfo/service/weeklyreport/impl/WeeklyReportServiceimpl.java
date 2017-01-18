package com.asiainfo.service.weeklyreport.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.asiainfo.domain.entity.user.User;
import com.asiainfo.domain.entity.weeklyreport.ReportRecord;
import com.asiainfo.domain.entity.weeklyreport.WeeklyReport;
import com.asiainfo.domain.entity.weeklyreport.WeeklyReportTo;
import com.asiainfo.repository.weeklyreport.ReportRecordRepository;
import com.asiainfo.repository.weeklyreport.WeeklyReportRepository;
import com.asiainfo.repository.weeklyreport.WeeklyReportToRepository;
import com.asiainfo.service.notify.impl.NotifyUser;
import com.asiainfo.service.notify.impl.WeeklyReportSubmitReportMessage;
import com.asiainfo.service.notify.impl.WeeklyReportSubmitReportMessage.WeeklyReportInfo;
import com.asiainfo.service.notify.interfaces.INotifyService;
import com.asiainfo.service.weeklyreport.interfaces.IUserService;
import com.asiainfo.service.weeklyreport.interfaces.IWeeklyReportService;
import com.asiainfo.util.consts.CommonConst.WeeklyReportReport;
import com.asiainfo.util.time.TimeUtil;

/**
 * Created by eason on 2017/1/6.
 */
@Service
public class WeeklyReportServiceimpl implements IWeeklyReportService {
	@Autowired
	private ReportRecordRepository reportRecordRepository;
	@Autowired
	private WeeklyReportRepository weeklyReportRepository;
	@Autowired
	WeeklyReportToRepository weeklyReportToRepository;
	//注入推送服務
	@Autowired
	private INotifyService notifyService;

	//讀取配置的推送信息
	@Value("${weeklyReport.notify.information}")
	private String notifyMessage;
	
	@Autowired
	private IUserService userService;
	
	@Override
	public List<ReportRecord> findByUserIdAndTime(String userId, long time) {
		//根据时间和使用者ID获取一周的记录内容

		//根据时间获取周数
		int weekOfYear = new Long(TimeUtil.getWeekOfYear(time)).intValue();
		//调用方法获取到周报对象
		WeeklyReport weeklyReport = weeklyReportRepository.findByReportUserIdAndWeekly(userId, weekOfYear);
		//获取该周报对应的记录
		return weeklyReport.getReportRecord();
	}
	

	@Override
	public WeeklyReport createWeeklyReport(String reportUserId) {
		// 构造WeeklyReport对象
		WeeklyReport weeklyReport = new WeeklyReport(reportUserId);
		return weeklyReportRepository.save(weeklyReport);
	}

	@Override 
	public List<ReportRecord> findByCreateDateBetween(String reportUserId,long currentTime) {
		long startDate = TimeUtil.getDayInWeek(currentTime, "MONDAY");
		long endDate = TimeUtil.getDayInWeek(currentTime, "SUNDAY");
		return reportRecordRepository.findByReportUserIdAndCreateDateBetweenOrderByCreateDateDesc(reportUserId, startDate, endDate);
	}


	public WeeklyReport queryWeeklyReportByUserId(String userId) {
		return weeklyReportRepository.findByReportUserId(userId);
	}

	/**
	 * 提交周報<br>
	 * 提醒審核人查看周報<br>
	 * 第一版本实现实时推送<br>
	 * 第二版检查提醒频率后考虑是否改造为集中推送
	 */
	@Override
	public boolean submitWeeklyReport(long weeklyReportId) throws Exception {
		// TODO 提交周報
		WeeklyReport weeklyReport = weeklyReportRepository.findOne(weeklyReportId);
		//設置周報匯報人，因爲本周以內可能變化過匯報人，提交的時候重新設置一次匯報人以免錯誤
		WeeklyReportTo weeklyReportTo = weeklyReportToRepository.findByReportUserId(weeklyReport.getReportUserId());
		weeklyReport.setAuditingUserId(weeklyReportTo.getAuditingUserId());
		//修改周報狀態到提交狀態
		weeklyReport.setState(WeeklyReportReport.SUBMMITED);
		weeklyReportRepository.save(weeklyReport);

		// 生成推送信息
		NotifyUser notifyUser = new NotifyUser();
		WeeklyReportSubmitReportMessage message = new WeeklyReportSubmitReportMessage();
		User author = userService.queryUserById(weeklyReport.getAuditingUserId());
		notifyUser.setId(weeklyReportTo.getAuditingUserId());
		notifyUser.setName(author.getName());
		message.setNotifyUser(notifyUser);
		WeeklyReportInfo weeklyReportInfo = message.new WeeklyReportInfo();
		User reportUser= userService.queryUserById(weeklyReport.getReportUserId());
		weeklyReportInfo.setReportUserId(reportUser.getId());
		weeklyReportInfo.setReportUserName(reportUser.getName());
		weeklyReportInfo.setSubmitTime(System.currentTimeMillis());
		weeklyReportInfo.setWeek(TimeUtil.getWeekOfYear());
		message.setWeeklyReport(weeklyReportInfo);
		notifyService.notify(message);
		return true;
	}

    /**
     * 返回一条周报记录，如果没有就创建一条
     * @param userId
     * @param weekly
     * @return
     */
	@Override
	public WeeklyReport queryWeeklyReportByUserIdAndWeekly(String userId, int weekly) {
       WeeklyReport weeklyReport= weeklyReportRepository.findByReportUserIdAndWeekly(userId, weekly);
        if(weeklyReport==null){
            WeeklyReport weeklyReport1=new WeeklyReport();
            weeklyReport1.setReportUserId(userId);
			//获取审批人信息
           WeeklyReportTo weeklyReportTo= weeklyReportToRepository.findByReportUserId(userId);
            if(weeklyReportTo!=null){
                weeklyReport1.setAuditingUserId(weeklyReportTo.getAuditingUserId());
            }
            weeklyReport1.setWeekly(weekly);
            return weeklyReportRepository.save(weeklyReport1);
        }else{
            return weeklyReport;
        }
	}

    @Override
    public WeeklyReport modifyWeeklyReport(WeeklyReport weeklyReport) {
        return weeklyReportRepository.save(weeklyReport);
    }

    /**
     * @author yi 2017-01-13
     */
	@Override
	public Page<User> getReportUsers(String authorId, Pageable pageable) {
		//獲取提交給審核人的周報
		Page<WeeklyReport>  weeklyReportPage = weeklyReportRepository
			 .findByAuditingUserIdAndWeeklyAndState(authorId, TimeUtil.getWeekOfYear(),
					 WeeklyReportReport.SUBMMITED , pageable);
		//獲取用戶信息   
		List<User>  users= new ArrayList<User>();
		for(WeeklyReport report : weeklyReportPage.getContent()){
			users.add(userService.queryUserById(report.getReportUserId()));
		}
		//生成用戶分頁
		Page<User> userPage = new PageImpl<User>(users);
		BeanUtils.copyProperties(weeklyReportPage, userPage);
		return userPage;
	}
}
