package com.asiainfo.microNote.notify.adapter;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.asiainfo.microNote.notify.pojo.Message;

/**
 * 
 * @author yi
 *
 */
@Component
public class KaraNotifyAdapter implements NotifyAdapter {

	private static final Logger logger = Logger.getLogger(KaraNotifyAdapter.class);
	
	@Override
	public boolean notify(Message message) {
		// TODO Auto-generated method stub
		logger.info(message);
		return true;
	}

	
}
