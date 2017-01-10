package com.asiainfo.microNote.comments.service;

import com.asiainfo.microNote.comments.domain.entity.Comment;
import com.asiainfo.microNote.comments.domain.entity.CommentRecord;

/**
 * 
 * @author yi
 *
 */
public interface ICommentsService {
	
	/**
	 * 
	 * @param targetId
	 * @param targetType
	 * @return
	 */
	public Comment getComment(long targetId,String targetType); 
	
	/**
	 * 
	 * @param targetId
	 * @param targetType
	 * @param commentRecord
	 * @return
	 */
	public boolean addComment(long targetId,String targetType, CommentRecord commentRecord);
	
	/**
	 * 
	 * @param commentRecordId
	 * @return
	 */
	public boolean deleteComment(long commentRecordId);
	
}
