package com.asiainfo.service.weeklyreport.interfaces;
import com.asiainfo.domain.entity.weeklyreport.ReportRecord;
import org.springframework.boot.actuate.endpoint.AutoConfigurationReportEndpoint;
/**
 * Created by eason on 2017/1/9.
 */
public interface IReportRecordService {
    /*
    修改周报项（成果类）
     */
     boolean modifyReportRecord(ReportRecord reportRecord);
    /*
    删除周报项（成果类）
     */
     boolean deleteReportRecord(long reportRecordId);
    /*
    增加周报项（成果类）
     */
     ReportRecord createReportRecord(ReportRecord reportRecord);

}
