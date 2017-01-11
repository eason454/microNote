package com.asiainfo.microNote.comments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.microNote.comments.domain.entity.Comment;
import com.asiainfo.microNote.comments.domain.entity.CommentRecord;
import com.asiainfo.microNote.comments.pojo.CommentInfo;
import com.asiainfo.microNote.comments.service.ICommentsService;

/**
 * 提供评论服务
 * 
 * @author yi
 */
@RestController
public class CommentsController {
	
	@Autowired
	ICommentsService commentsService;
	
	/**
	 * 獲取品論，因爲可能評論對象ID在不同的服務可能衝突所以要提交評論類型
	 * @param targetId 評論對象ID
	 * @param targetType 評論對象類型
	 * @return
	 */
	@GetMapping(path = "/comment/{targetType}/{targetId}")
	public CommentInfo getComment(@PathVariable("targetId") long targetId, @PathVariable("targetType") String targetType) {
		return commentsService.getComment(targetId, targetType);
	}

	/**
	 * 添加品論
	 * @param targetId
	 * @param targetType
	 * @param commentRecord
	 * @param userId
	 * @return
	 */
	@PostMapping(path = "/comment/{targetType}/{targetId}")
	public boolean addCommentRecord(@PathVariable("targetId") long targetId,
			@PathVariable("targetType") String targetType, @RequestBody CommentRecord commentRecord, @RequestHeader("userId") String userId) {
		if(userId != null )
			commentRecord.setUserId(userId);
		return commentsService.addComment(targetId, targetType, commentRecord);
	}

	/**
	 * 刪除品論
	 * @param commentRecordId
	 * @return
	 */
	@DeleteMapping(path = "/comment/{commentId}")
	public boolean deleteCommentRecord(@PathVariable("commentId") long commentRecordId) {
		return commentsService.deleteComment(commentRecordId);
	}

}
