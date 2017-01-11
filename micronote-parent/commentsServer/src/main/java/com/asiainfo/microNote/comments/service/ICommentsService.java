package com.asiainfo.microNote.comments.service;

import com.asiainfo.microNote.comments.domain.entity.Comment;
import com.asiainfo.microNote.comments.domain.entity.CommentRecord;

/**
 * 評論服務接口
 * @author yi
 *
 */
public interface ICommentsService {
	
	/**
	 * 查詢評論
	 * @param targetId
	 * @param targetType
	 * @return
	 */
	public Comment getComment(long targetId,String targetType); 
	
	/**
	 * 添加評論
	 * @param targetId
	 * @param targetType
	 * @param commentRecord
	 * @return
	 */
	public boolean addComment(long targetId,String targetType, CommentRecord commentRecord);
	
	/**
	 * 刪除評論
	 * @param commentRecordId
	 * @return
	 */
	public boolean deleteComment(long commentRecordId);
	
}
