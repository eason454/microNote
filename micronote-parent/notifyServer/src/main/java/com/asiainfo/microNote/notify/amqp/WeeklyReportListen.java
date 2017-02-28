package com.asiainfo.microNote.notify.amqp;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.microNote.notify.pojo.message.weeklyReport.WeeklyReportSubmitReportMessage;
import com.asiainfo.microNote.notify.service.weeklyReport.WeeklyReportRemaindReportNotifyService;
import com.asiainfo.microNote.notify.service.weeklyReport.WeeklyReportSubmitNotifyService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Service
public class WeeklyReportListen {

	AmqpAdmin amqpAdmin;

	Logger logger = Logger.getLogger(WeeklyReportListen.class);
	
	@Autowired
	public WeeklyReportListen(AmqpAdmin amqpAdmin) {
		this.amqpAdmin = amqpAdmin;
		// 初始化周报提交队列
		Queue notifyWeeklyReportSubmit = new Queue("notifyWeeklyReportSubmit");
		TopicExchange submitExchange = new TopicExchange("submitExchange");
		amqpAdmin.declareQueue(notifyWeeklyReportSubmit);
		amqpAdmin.declareExchange(submitExchange);
		amqpAdmin.declareBinding(BindingBuilder.bind(notifyWeeklyReportSubmit).to(submitExchange).with("route1"));
	}

	@RabbitListener(queues = "notifyWeeklyReportSubmit")
	public void submitWeeklyReport(String messageString) throws Exception {
		logger.info(messageString);
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.UNWRAP_ROOT_VALUE);
		mapper.disable(SerializationFeature.WRAP_ROOT_VALUE);
		WeeklyReportSubmitNotifyService
				.notifyAuditing(mapper.readValue(messageString, WeeklyReportSubmitReportMessage.class));
	}
}
