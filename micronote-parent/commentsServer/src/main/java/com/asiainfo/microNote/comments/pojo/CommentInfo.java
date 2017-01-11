package com.asiainfo.microNote.comments.pojo;

import java.util.List;

/**
 * 聚合用户信息提供给页面查询
 * @author yi
 *
 */
public class CommentInfo {

	List<CommentRecordInfo> comments;

	
	public List<CommentRecordInfo> getComments() {
		return comments;
	}

	public void setComments(List<CommentRecordInfo> comments) {
		this.comments = comments;
	}

	public class CommentRecordInfo {

		long commentRecordId;
		String userId;
		String userName;
		String content;

		public long getCommentRecordId() {
			return commentRecordId;
		}

		public void setCommentRecordId(long commentRecordId) {
			this.commentRecordId = commentRecordId;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

	}

}
