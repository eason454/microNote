package com.asiainfo.domain.kara.response;

/**
 * Created by eason on 2017/1/11.
 */
public class KaraUserResponseInfo {
    private String responseCode;
    private String resultInfo;
    private KaraUserInfo staffResponseInfo;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
    }

    public KaraUserInfo getStaffResponseInfo() {
        return staffResponseInfo;
    }

    public void setStaffResponseInfo(KaraUserInfo staffResponseInfo) {
        this.staffResponseInfo = staffResponseInfo;
    }
}
