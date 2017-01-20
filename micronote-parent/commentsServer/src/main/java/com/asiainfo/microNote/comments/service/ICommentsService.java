package com.asiainfo.microNote.comments.service;

import org.springframework.data.domain.Page;

import com.asiainfo.microNote.comments.domain.entity.CommentRecord;
import com.asiainfo.microNote.comments.pojo.CommentRecordInfo;

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
	public Page<CommentRecordInfo> getComment(long targetId, String targetType,int page, int size); 
	
	/**
	 * 添加評論
	 * @param targetId
	 * @param targetType
	 * @param commentRecord
	 * @return
	 */
	public boolean addComment(long targetId,String targetType, CommentRecord commentRecord);
	
	public boolean updateComment(CommentRecord commentRecord);
	/**
	 * 刪除評論
	 * @param commentRecordId
	 * @return
	 */
	public boolean deleteComment(long commentRecordId);
	
}
