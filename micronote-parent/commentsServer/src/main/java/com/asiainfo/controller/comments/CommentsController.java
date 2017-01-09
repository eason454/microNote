package com.asiainfo.controller.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.domain.entity.comments.Comment;
import com.asiainfo.domain.entity.comments.CommentRecord;
import com.asiainfo.repository.comments.ICommentRecordRepository;
import com.asiainfo.repository.comments.ICommentRepository;

@RestController
public class CommentsController {
	
	@Autowired
	ICommentRecordRepository commentRecordRepository;
	@Autowired
	ICommentRepository commentRepository;
	
	@PostMapping(path = "/createComment")
	public void createComment(@RequestBody Comment comment){
		commentRepository.save(comment);
	}
	
	@GetMapping(path = "/getComment/{commentId}")
	public Comment getComment(@PathVariable("commentId") long commentId){
		Comment comment = commentRepository.findOne(commentId);
		comment.getRecords();
		return comment;
	}
	
	@PostMapping(path = "/addCommentRecord")
	public void addCommentRecord(@RequestBody CommentRecord commentRecord){
		commentRecordRepository.save(commentRecord);
	}
	
	@DeleteMapping(path = "/deleteCommentRecord/{commentRecordId}")
	public void deleteCommentRecord(@PathVariable("commentRecordId") long commentRecordId){
		commentRecordRepository.delete(commentRecordId);
	}
	
}
