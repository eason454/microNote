package com.asiainfo.repository.comments;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.asiainfo.domain.entity.comments.Comment;


public interface ICommentRepository extends PagingAndSortingRepository<Comment, Long>{

	public Comment findByCommentTargetId(long commentTargetId);
}
