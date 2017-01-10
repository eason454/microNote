package com.asiainfo.microNote.comments.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.asiainfo.microNote.comments.domain.entity.Comment;


/**
 * 
 * @author yi
 *
 */
public interface ICommentRepository extends PagingAndSortingRepository<Comment, Long>{

	Comment findByCommentTargetIdAndTargetType(long targetId, String targetType);
}
