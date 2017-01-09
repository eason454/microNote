package com.asiainfo.service.notify.impl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ch.qos.logback.core.util.ExecutorServiceUtil;

@Component
public class NotifyService {
	
	Logger logger = Logger.getLogger(NotifyService.class);

	private static final BlockingQueue<Message> notifyOnTimeQueen = new LinkedBlockingQueue<Message>();

	private static final BlockingQueue<Message> notifyQueen = new LinkedBlockingQueue<Message>();

	public static void notify(Message message) {
		notifyQueen.add(message);
	}

	public static void notifyOnTime(Message message) {
		notifyOnTimeQueen.add(message);
	}

	public NotifyService() {
		Executor executor = ExecutorServiceUtil.newExecutorService();
		for (int i = 0; i < 5; i++) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					while (true) {
						try {
//							System.out.println("==========================notify immediately=============================");
							System.out.println(notifyQueen.take().getContent());
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
		}
	}

	@Scheduled(cron = "* * 1  * * ? ")
	public static void notifyQueen() {
		try {
//			System.out.println("==========================notify=============================");
			if (!notifyOnTimeQueen.isEmpty())
				System.out.println(notifyOnTimeQueen.take().getContent());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
