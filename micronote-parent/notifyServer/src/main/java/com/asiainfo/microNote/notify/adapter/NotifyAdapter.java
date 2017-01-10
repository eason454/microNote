package com.asiainfo.microNote.notify.adapter;

import com.asiainfo.microNote.notify.pojo.Message;
import com.asiainfo.microNote.notify.pojo.NotifyUser;

public interface NotifyAdapter {

	boolean notify(Message message, NotifyUser user);
}
