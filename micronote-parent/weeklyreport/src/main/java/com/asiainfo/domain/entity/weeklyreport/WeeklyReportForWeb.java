package com.asiainfo.domain.entity.weeklyreport;


import com.asiainfo.domain.entity.user.User;

import java.util.List;

/**提供给web端的周报对象
 * Created by eason on 2017/1/13.
 */
public class WeeklyReportForWeb {
    private long weeklyReportId;
    private int weekly;
    private String reportUserId;
    private String auditingUserId;
    private List<User> user;

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public int getWeekly() {
        return weekly;
    }

    public void setWeekly(int weekly) {
        this.weekly = weekly;
    }


    public String getReportUserId() {
        return reportUserId;
    }

    public void setReportUserId(String reportUserId) {
        this.reportUserId = reportUserId;
    }

    public String getAuditingUserId() {
        return auditingUserId;
    }

    public void setAuditingUserId(String auditingUserId) {
        this.auditingUserId = auditingUserId;
    }

    private List<ReportRecord> reportRecords;
    private List<Plan> lastWeekPlan;
    private List<Plan> nextWeekPlan;

    public long getWeeklyReportId() {
        return weeklyReportId;
    }

    public void setWeeklyReportId(long weeklyReportId) {
        this.weeklyReportId = weeklyReportId;
    }

    public List<ReportRecord> getReportRecords() {
        return reportRecords;
    }

    public void setReportRecords(List<ReportRecord> reportRecords) {
        this.reportRecords = reportRecords;
    }

    public List<Plan> getLastWeekPlan() {
        return lastWeekPlan;
    }

    public void setLastWeekPlan(List<Plan> lastWeekPlan) {
        this.lastWeekPlan = lastWeekPlan;
    }

    public List<Plan> getNextWeekPlan() {
        return nextWeekPlan;
    }

    public void setNextWeekPlan(List<Plan> nextWeekPlan) {
        this.nextWeekPlan = nextWeekPlan;
    }
}
