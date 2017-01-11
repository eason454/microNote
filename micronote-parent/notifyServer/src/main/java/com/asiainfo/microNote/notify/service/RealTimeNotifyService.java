package com.asiainfo.microNote.notify.service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.asiainfo.microNote.notify.pojo.Message;

import ch.qos.logback.core.util.ExecutorServiceUtil;

/**
 * 实时推送服务
 * @author yi
 */
@Service
public class RealTimeNotifyService {
	
	Logger logger = Logger.getLogger(RealTimeNotifyService.class);
	
	private static final BlockingQueue<Message> notifyQueen = new LinkedBlockingQueue<Message>();

	public static void notify(Message message) {
		notifyQueen.add(message);
	}

	public RealTimeNotifyService() {
		Executor executor = ExecutorServiceUtil.newExecutorService();
		for (int i = 0; i < 5; i++) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					while (true) {
						try {
							logger.info(notifyQueen.take().getContent());
						} catch (InterruptedException e) {
							// TODO 添加容錯代碼
							e.printStackTrace();
						}
					}
				}
			});
		}
	}

}
