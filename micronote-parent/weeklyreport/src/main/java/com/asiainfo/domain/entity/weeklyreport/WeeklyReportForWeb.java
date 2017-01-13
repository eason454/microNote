package com.asiainfo.domain.entity.weeklyreport;


import java.util.List;

/**提供给web端的周报对象
 * Created by eason on 2017/1/13.
 */
public class WeeklyReportForWeb {
    private long weeklyReportId;
    private int weekly;
    private String userId;
    private String auditUserId;

    public int getWeekly() {
        return weekly;
    }

    public void setWeekly(int weekly) {
        this.weekly = weekly;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(String auditUserId) {
        this.auditUserId = auditUserId;
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
