package com.asiainfo.microNote.comments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asiainfo.microNote.comments.domain.entity.Comment;
import com.asiainfo.microNote.comments.domain.entity.CommentRecord;
import com.asiainfo.microNote.comments.repository.ICommentRecordRepository;
import com.asiainfo.microNote.comments.repository.ICommentRepository;

/**
 * 
 * @author yi
 *
 */
@Component
public class SimpleCommentService implements ICommentsService{
	@Autowired
	ICommentRecordRepository commentRecordRepository;
	
	@Autowired
	ICommentRepository commentRepository;
	
	@Override
	public Comment getComment(long targetId, String targetType) {
		return commentRepository.findByCommentTargetIdAndTargetType(targetId, targetType);
	}
	
	@Override
	public boolean addComment(long targetId, String targetType, CommentRecord commentRecord) {
		Comment comment;
		//TODO 判断是否已经有的对象的评论
		comment = commentRepository.findByCommentTargetIdAndTargetType(targetId, targetType);
		//TODO 没有增加一个评论
		if(comment == null)
			comment = commentRepository.save(new Comment(targetId, targetType));
		//TODO 对评论进行增加评论项目
		commentRecord.setComment(comment);
		comment.getRecords().add(commentRecord);
		commentRepository.save(comment);
		return true;
	}

	@Override
	public boolean deleteComment(long commentRecordId) {
//		Comment comment = commentRepository.findByCommentRecordId(commentRecordId);
//		comment.getRecords().remove(o)
		commentRecordRepository.delete(commentRecordId);
		return true;
	}


}
