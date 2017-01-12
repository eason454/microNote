package com.asiainfo.controller.weeklyreport;

import com.asiainfo.domain.entity.user.User;
import com.asiainfo.domain.kara.KaraRequestObject;
import com.asiainfo.domain.kara.response.KaraField;
import com.asiainfo.domain.kara.response.KaraMessage;
import com.asiainfo.domain.kara.response.KaraUserResponseInfo;
import com.asiainfo.service.weeklyreport.interfaces.IWeeklyReportToService;
import com.asiainfo.service.weeklyreport.interfaces.IUserService;
import com.asiainfo.util.consts.CommonConst;
import com.asiainfo.util.kara.MessageConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.domain.entity.weeklyreport.WeeklyReportTo;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WeeklyReportToController {
	
	@Autowired
	private IWeeklyReportToService weeklyReportToService;
	@Autowired
	private IUserService userService;
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(path = "/setWeeklyReportTo", method = RequestMethod.POST)
	public KaraMessage setWeeklyReportTo(@RequestBody KaraRequestObject request){
        String resultMessage="";
		String userId=request.getUserId();
		String auditStaffNumber=request.getText();
        String auditUserId="";
        List<KaraField> karaFieldList=new ArrayList<KaraField>();
        KaraUserResponseInfo userResponseInfo=restTemplate.getForObject("http://10.19.15.28:8000/api/sdm/getStaffByStaffId/{staffId}",KaraUserResponseInfo.class,auditStaffNumber);
        if(!userResponseInfo.getResponseCode().equals(CommonConst.KaraInfo.responseSuccessCode) || StringUtils.isEmpty(userResponseInfo.getStaffResponseInfo().getAccountId())){
            resultMessage=CommonConst.KaraInfo.userNotExists;
            //// TODO: 2017/1/11 需要拼装karaMessage
            KaraField field=new KaraField();
            field.setTitle(CommonConst.KaraInfo.setWeeklyReportResult);
            field.setValue(String.format(CommonConst.KaraInfo.SetWeeklyReportResultFail,resultMessage));
            karaFieldList.add(field);
            return MessageConstructor.constructMessageWithFields("",karaFieldList);
        }else{
            auditUserId=userResponseInfo.getStaffResponseInfo().getAccountId();
        }
		WeeklyReportTo weeklyReportTo = weeklyReportToService.findByReportUserId(userId);
		weeklyReportTo.setAuditingUserId(auditUserId);
		//查询汇报对象是否已经在微记用户中存在
		User user=userService.queryUserById(auditUserId);

		boolean flag=weeklyReportToService.saveWeeklyReportTo(weeklyReportTo);
		if(flag){

		}
		//// TODO: 2017/1/11 增加拼装KaraMessage
        return null;
	}
}
