package com.asiainfo.controller.weeklyreport;

import com.asiainfo.domain.entity.user.User;
import com.asiainfo.domain.entity.weeklyreport.WeeklyReportTo;
import com.asiainfo.domain.kara.KaraRequestObject;
import com.asiainfo.domain.kara.response.KaraField;
import com.asiainfo.domain.kara.response.KaraMessage;
import com.asiainfo.domain.kara.response.KaraUserInfo;
import com.asiainfo.domain.kara.response.KaraUserResponseInfo;
import com.asiainfo.service.weeklyreport.interfaces.IUserService;
import com.asiainfo.service.weeklyreport.interfaces.IWeeklyReportToService;
import com.asiainfo.util.consts.CommonConst;
import com.asiainfo.util.kara.HttpUtils;
import com.asiainfo.util.kara.MessageConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WeeklyReportToController {
	private static Log logger= LogFactory.getLog(WeeklyReportToController.class);
	@Autowired
	private IWeeklyReportToService weeklyReportToService;
	@Autowired
	private IUserService userService;
	@Autowired
	private RestTemplate restTemplate;
    @Value("${karaUrl.getStaffByStaffIdUrl}")
    private String karaUrl;
	@RequestMapping(path = "/setWeeklyReportTo", method = RequestMethod.POST)
	public KaraMessage setWeeklyReportTo(@RequestBody KaraRequestObject request){
        String resultMessage="";
		String userId=request.getUserId();
		String auditStaffNumber=request.getText();
        String auditUserId="";
        List<KaraField> karaFieldList=new ArrayList<KaraField>();
        //根据工号查询微记用户
        User auditUser=userService.queryUserByNumber(auditStaffNumber);//查询审核人信息
        String auditUserName="";
        if (auditUser==null){
            //汇报对象信息为空，需要调用kara查询员工信息
            try {
                HttpEntity headers= HttpUtils.getKaraHttpEntityForGet(request.getToken());
                HttpEntity<KaraUserResponseInfo> response=restTemplate.exchange(karaUrl, HttpMethod.GET,headers,KaraUserResponseInfo.class,auditStaffNumber);//
                KaraUserResponseInfo userResponseInfo=response.getBody();
                if(!userResponseInfo.getResponseCode().equals(CommonConst.KaraInfo.responseSuccessCode) || StringUtils.isEmpty(userResponseInfo.getStaffResponseInfo().getAccountId())){
                    resultMessage=CommonConst.KaraInfo.userNotExists;
                    //// TODO: 2017/1/11 需要拼装karaMessage
                    KaraField field=new KaraField();
                    field.setTitle(CommonConst.KaraInfo.setWeeklyReportResult);
                    field.setValue(String.format(CommonConst.KaraInfo.SetWeeklyReportResultFail,resultMessage));
                    karaFieldList.add(field);
                    return MessageConstructor.constructMessageWithFields("",karaFieldList);
                }else{
                    KaraUserInfo userInfo=userResponseInfo.getStaffResponseInfo();
                    auditUserId=userInfo.getAccountId();
                    auditUserName=userInfo.getStaffName();
                    //生成用户资料
                    User user=new User();
                    user.setId(auditUserId);
                    user.setName(userInfo.getStaffName());
                    user.setUserNumber(userInfo.getStaffCode());
                    userService.createUser(user);
                }
            }catch (Exception e){
                logger.error(e.getMessage());
                resultMessage=e.getMessage();
                KaraField field=new KaraField();
                field.setTitle(CommonConst.KaraInfo.setWeeklyReportResult);
                field.setValue(String.format(CommonConst.KaraInfo.SetWeeklyReportResultFail,resultMessage));
                karaFieldList.add(field);
                return MessageConstructor.constructMessageWithFields("",karaFieldList);
            }
        }else{
            auditUserId=auditUser.getId();
            auditUserName=auditUser.getName();
        }
        //
		WeeklyReportTo weeklyReportTo = weeklyReportToService.findByReportUserId(userId);
        boolean flag=false;
		if(weeklyReportTo==null){
            WeeklyReportTo weeklyReportTo1=new WeeklyReportTo(userId,auditUserId);
            flag=weeklyReportToService.saveWeeklyReportTo(weeklyReportTo1);

        }else{
            weeklyReportTo.setAuditingUserId(auditUserId);
            flag=weeklyReportToService.saveWeeklyReportTo(weeklyReportTo);
        }
		if(flag){
            //设置成功
            KaraField field=new KaraField();
            field.setTitle(CommonConst.KaraInfo.setWeeklyReportResult);
            field.setValue(String.format(CommonConst.KaraInfo.SetWeeklyReportResultSuccess,auditUserName,auditStaffNumber));
            karaFieldList.add(field);
            return MessageConstructor.constructMessageWithFields("",karaFieldList);
		}else{
            KaraField field=new KaraField();
            field.setTitle(CommonConst.KaraInfo.setWeeklyReportResult);
            field.setValue(String.format(CommonConst.KaraInfo.SetWeeklyReportResultFail,resultMessage));
            karaFieldList.add(field);
            return MessageConstructor.constructMessageWithFields("",karaFieldList);
        }

	}
}
