package com.asiainfo.microNote.notify.adapter.kara;

import java.util.ArrayList;
import java.util.List;

/**
 * kara incoming对象
 * @author yi
 */
public class KaraIncoming {

	StringBuffer text;
	List<Attachment> attachments = new ArrayList<Attachment>();
	StringBuffer channel;

	public StringBuffer getText() {
		return text;
	}

	public void setText(StringBuffer text) {
		this.text = text;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(Attachment attachment) {
		this.attachments.add(attachment);
	}

	public StringBuffer getChannel() {
		return channel;
	}

	public void setChannel(StringBuffer channel) {
		this.channel = channel;
	}

	public class Attachment {

		StringBuffer title;

		public Attachment(StringBuffer title) {
			this();
			this.title = title;
		}

		public Attachment() {
			super();
		}

		public StringBuffer getTitle() {
			return title;
		}

		public void setTitle(StringBuffer title) {
			this.title = title;
		}

	}
}
