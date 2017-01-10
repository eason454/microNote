package com.asiainfo.microNote.comments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.microNote.comments.domain.entity.Comment;
import com.asiainfo.microNote.comments.domain.entity.CommentRecord;
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
	
	@GetMapping(path = "/comment/{targetType}/{targetId}")
	public Comment getComment(@PathVariable("targetId") long targetId, @PathVariable("targetType") String targetType) {
		return commentsService.getComment(targetId, targetType);
	}

	@PostMapping(path = "/comment/{targetType}/{targetId}")
	public boolean addCommentRecord(@PathVariable("targetId") long targetId,
			@PathVariable("targetType") String targetType, @RequestBody CommentRecord commentRecord, @RequestHeader("userId") Long userId) {
		if(userId != null )
			commentRecord.setUserId(userId);
		return commentsService.addComment(targetId, targetType, commentRecord);
	}

	@DeleteMapping(path = "/comment/{commentId}")
	public boolean deleteCommentRecord(@PathVariable("commentId") long commentRecordId) {
		return commentsService.deleteComment(commentRecordId);
	}

}
