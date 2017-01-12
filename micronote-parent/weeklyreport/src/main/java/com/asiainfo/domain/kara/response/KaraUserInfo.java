package com.asiainfo.domain.kara.response;

/**
 * Created by eason on 2017/1/11.
 */
public class KaraUserInfo {
    private String accountId;
    private String staffId;
    private String staffName;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
}
